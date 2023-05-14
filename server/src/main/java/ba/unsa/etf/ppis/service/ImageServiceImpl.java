package ba.unsa.etf.ppis.service;

import ba.unsa.etf.ppis.entity.ImageEntity;
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

    @Override
    public ImageEntity uploadImage(MultipartFile file) throws IOException {
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
