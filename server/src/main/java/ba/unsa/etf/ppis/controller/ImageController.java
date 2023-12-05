package ba.unsa.etf.ppis.controller;

import ba.unsa.etf.ppis.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

@RestController
@RequestMapping("/api/image")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping("/upload")
    public ResponseEntity<Long> uploadImage(@RequestHeader(name = "Authorization", required = false) String authorizationHeader, @RequestParam("image") MultipartFile file) throws IOException {
        return new ResponseEntity<>(imageService.uploadImage(file, authorizationHeader).getId(), HttpStatus.OK) ;
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable Long fileId) {
        byte[] image = imageService.getImageWithId(fileId);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
    }
}
