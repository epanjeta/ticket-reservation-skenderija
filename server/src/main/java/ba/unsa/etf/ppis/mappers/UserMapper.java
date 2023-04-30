package ba.unsa.etf.ppis.mappers;

import ba.unsa.etf.ppis.dto.UserDTO;
import ba.unsa.etf.ppis.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserDTO mapToProjection(UserEntity entity) {
        if (entity == null) {
            return null;
        }
        UserDTO projection = new UserDTO();
        projection.setId(entity.getId());
        projection.setName(entity.getName());
        projection.setEmail(entity.getEmail());
        projection.setDisplayValue(entity.getName());
        projection.setUserType(entity.getUserType());
        return projection;
    }

    public static List<UserDTO> mapToProjections(List<UserEntity> entities) {
        return entities.stream().map(UserMapper::mapToProjection).collect(Collectors.toList());
    }
}
