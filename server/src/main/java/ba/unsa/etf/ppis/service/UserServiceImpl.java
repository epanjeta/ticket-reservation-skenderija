package ba.unsa.etf.ppis.service;

import ba.unsa.etf.ppis.dto.UserDTO;
import ba.unsa.etf.ppis.mappers.UserMapper;
import ba.unsa.etf.ppis.repository.UserRepository;
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
}
