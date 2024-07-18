package com.example.storagesystem.service.serviceImplementation;

import com.example.storagesystem.entity.ImageData;
import com.example.storagesystem.exception.ImageAlreadyExistException;
import com.example.storagesystem.repository.StorageRepository;
import com.example.storagesystem.service.StorageService;
import com.example.storagesystem.utils.ImageUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.zip.DataFormatException;

@Service
@RequiredArgsConstructor
public class StorageServiceImplementation implements StorageService {

    private final StorageRepository storageRepository;

    @Override
    public String uploadImage(MultipartFile file) throws IOException {

        ImageData imageData = new ImageData();
        imageData.setType(file.getContentType());
        imageData.setName(file.getOriginalFilename());
        imageData.setImageData(ImageUtils.compressImage(file.getBytes()));
        if (storageRepository.existsImageDataByImageData(ImageUtils.compressImage(file.getBytes()))) {
            throw new ImageAlreadyExistException("Image already existed and it's name is :: " + file.getOriginalFilename());
        }

        storageRepository.save(imageData);
        return "File uploaded successfully with name :: " + file.getOriginalFilename();
    }

    @Override
    public byte[] downloadImage(String fileName) throws DataFormatException, IOException {
        ImageData imageData = storageRepository.findImageDataByName(fileName).orElseThrow(() -> new EntityNotFoundException("Image not Found with file name :: " + fileName));
        return ImageUtils.decompressImage(imageData.getImageData());
    }
}
