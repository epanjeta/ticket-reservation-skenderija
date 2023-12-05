package ba.unsa.etf.ppis.service;

import ba.unsa.etf.ppis.entity.ImageEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    ImageEntity uploadImage(MultipartFile file, String authorizationHeader) throws IOException;

    byte[] getImageWithId(Long id);

    ImageEntity getImageEntityWithId(Long id);
}
