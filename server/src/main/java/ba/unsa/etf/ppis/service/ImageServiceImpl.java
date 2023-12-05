package ba.unsa.etf.ppis.service;

import ba.unsa.etf.ppis.constants.ApiResponseMessages;
import ba.unsa.etf.ppis.entity.ImageEntity;
import ba.unsa.etf.ppis.exception.NoAccessException;
import ba.unsa.etf.ppis.repository.ImageRepository;
import ba.unsa.etf.ppis.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    protected ImageRepository imageRepository;
    @Autowired protected AuthService authService;

    @Override
    public ImageEntity uploadImage(MultipartFile file, String authorizationHeader) throws IOException {

        if(authorizationHeader == null || authorizationHeader.isEmpty())
            throw new NoAccessException(ApiResponseMessages.NO_ACCESS);

        if(!authService.isAdminRole(authorizationHeader))
            throw new NoAccessException(ApiResponseMessages.NO_ACCESS);

        ImageEntity pImage = new ImageEntity();
        pImage.setName(file.getOriginalFilename());
        pImage.setType(file.getContentType());
        pImage.setImageData(ImageUtils.compressImage(file.getBytes()));
        return imageRepository.save(pImage);
    }

    @Override
    public byte[] getImageWithId(Long id){
        Optional<ImageEntity> imageData = imageRepository.findById(id);
        return ImageUtils.decompressImage(imageData.get().getImageData());
    }

    @Override
    public ImageEntity getImageEntityWithId(Long id) {
        Optional<ImageEntity> imageData = imageRepository.findById(id);
        if(imageData.isPresent()) return imageData.get();
        else return null;
    }


}
