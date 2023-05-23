package ba.unsa.etf.ppis.service;

import ba.unsa.etf.ppis.constants.ApiResponseMessages;
import ba.unsa.etf.ppis.dto.UserDTO;
import ba.unsa.etf.ppis.entity.UserEntity;
import ba.unsa.etf.ppis.exception.NotValidException;
import ba.unsa.etf.ppis.mappers.UserMapper;
import ba.unsa.etf.ppis.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    @Autowired protected UserRepository userRepository;
    @Autowired protected EmailService emailService;

    @Override
    public List<UserDTO> getAllUsers() {
        return UserMapper.mapToProjections(userRepository.findAll());
    }

    @Override
    public UserDTO getUserByEmailAndPassword(String email, String password) {
        UserEntity userEntity = getUserEntityByEmail(email);
        if (!userEntity.comparePasswords(password)) {
            throw new NotValidException(ApiResponseMessages.WRONG_EMAIL_OR_PASSWORD);
        }
        return UserMapper.mapToProjection(userEntity);
    }

    @Override
    public UserEntity getUserEntityByEmail(String email) {
        UserEntity userEntity = userRepository.findUserEntityByEmailAndVerifiedIsTrue(email);
        if (userEntity == null) {
            throw new EntityNotFoundException(ApiResponseMessages.USER_NOT_FOUND_WITH_EMAIL);
        }
        return userEntity;
    }

    @Override
    public UserDTO verifyUser(String email, String code) {
        UserEntity userEntity = userRepository.findUserEntityByEmail(email);
        if (userEntity.isVerified()) {
            throw new NotValidException(ApiResponseMessages.USER_IS_ALREADY_VERIFIED);
        }
        if (!code.equals(userEntity.getCode())) {
            throw new NotValidException(ApiResponseMessages.WRONG_VERIFICATION_CODE);
        }
        userEntity.setVerified(true);
        userRepository.save(userEntity);
        return getUserByEmail(email);
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        return UserMapper.mapToProjection(getUserEntityByEmail(email));
    }

    @Override
    public void createUser(UserDTO userDTO) {
        if (userRepository.findUserEntityByEmail(userDTO.getEmail()) != null) {
            throw new NotValidException(ApiResponseMessages.USER_ALREADY_EXISTS);
        }
        UserEntity userEntity = UserMapper.mapToEntity(userDTO, null);
        userEntity.setVerified(false);
        setCodeAndSend(userEntity);
        UserMapper.mapToProjection(userRepository.save(userEntity));
    }

    @Override
    public UserEntity getAdminByLocation(Integer locationId) {
        Optional<UserEntity> admin = userRepository.findAll().stream().filter(u -> u.getLocation()!= null && u.getLocation().getId() == locationId).collect(Collectors.toList()).stream().findFirst();
        if(admin.isPresent())
            return admin.get();
        return null;
    }

    private void setCodeAndSend(UserEntity userEntity) {
        String code = UserMapper.getRandomCode();
        userEntity.setCode(code);

        emailService.sendEmail("Verification code", "Your verification code is " +  code, userEntity);
    }
}
