package ba.unsa.etf.ppis.mappers;

import ba.unsa.etf.ppis.dto.LocationDTO;
import ba.unsa.etf.ppis.dto.TaskDTO;
import ba.unsa.etf.ppis.entity.LocationEntity;
import ba.unsa.etf.ppis.entity.TaskEntity;

public class LocationMapper {

    public LocationMapper() {
    }

    public static LocationEntity toEntity(LocationDTO projection){
        if (projection == null){
            return null;
        }
        LocationEntity entity = new LocationEntity();
        entity.setId(projection.getId());
        entity.setName(projection.getName());

        return entity;
    }

    public static LocationDTO toProjection(LocationEntity entity){
        if (entity == null){
            return null;
        }
        LocationDTO projection = new LocationDTO();
        projection.setId(entity.getId());
        projection.setName(entity.getName());

        return projection;
    }
}
