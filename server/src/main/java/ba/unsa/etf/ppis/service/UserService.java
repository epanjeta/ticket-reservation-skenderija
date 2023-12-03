package ba.unsa.etf.ppis.service;

import ba.unsa.etf.ppis.dto.UserDTO;
import ba.unsa.etf.ppis.entity.UserEntity;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
    UserDTO getUserByEmailAndPassword(String email, String oldPassword);
    UserEntity getUserEntityByEmail(String email);
    UserDTO verifyUser(String email, String code);
    UserDTO getUserByEmail(String email);
    void createUser(UserDTO userDTO);
    UserEntity getAdminByLocation(Integer locationId);
}
