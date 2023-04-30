package ba.unsa.etf.ppis.service;

import ba.unsa.etf.ppis.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
}
