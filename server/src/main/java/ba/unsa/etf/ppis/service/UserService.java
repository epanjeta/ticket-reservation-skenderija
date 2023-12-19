package ba.unsa.etf.ppis.service;

import ba.unsa.etf.ppis.dto.ForgottenPasswordDTO;
import ba.unsa.etf.ppis.dto.LogInUserDTO;
import ba.unsa.etf.ppis.dto.UserDTO;
import ba.unsa.etf.ppis.entity.UserEntity;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
    LogInUserDTO getUserByEmailAndPassword(String email, String oldPassword);
    UserEntity getUserEntityByEmail(String email);
    UserDTO verifyUser(String email, String code);
    UserDTO getUserByEmail(String email);
    void createUser(UserDTO userDTO);
    UserEntity getAdminByLocation(Integer locationId);

    UserDTO changePassword(Integer userId, String newPassword);

    UserEntity checkPassword(Integer userId, String currentPassword);

    void changeForgottenPassword(ForgottenPasswordDTO email);
}
