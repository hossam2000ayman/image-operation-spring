package com.example.storagesystem.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.zip.DataFormatException;

public interface StorageService {

    String uploadImage(MultipartFile file) throws IOException;

    byte[] downloadImage(String fileName) throws DataFormatException, IOException;

}
