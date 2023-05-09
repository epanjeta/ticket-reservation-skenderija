package ba.unsa.etf.ppis.mappers;

import ba.unsa.etf.ppis.dto.UserDTO;
import ba.unsa.etf.ppis.entity.UserEntity;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Random;
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

    public static UserEntity mapToEntity(UserDTO projection, UserEntity entity) {
        if (projection == null) {
            return null;
        }

        if (entity == null) {
            entity = new UserEntity();
        }

        entity.setName(projection.getName());
        entity.setUserType(projection.getUserType());
        entity.setEmail(projection.getEmail());
        entity.setPassword(projection.getPassword());
        return entity;
    }

    public static String getRandomCode() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }
}
