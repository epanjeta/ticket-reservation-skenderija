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

@Service
public class UserServiceImpl implements UserService{
    @Autowired protected UserRepository userRepository;

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

}
