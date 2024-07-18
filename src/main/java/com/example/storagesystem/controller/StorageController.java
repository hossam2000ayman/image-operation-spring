package com.example.storagesystem.controller;

import com.example.storagesystem.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.zip.DataFormatException;

@RestController
@RequestMapping("api/v1/storages")
@RequiredArgsConstructor
public class StorageController {

    private final StorageService storageService;


    @PostMapping("upload")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(storageService.uploadImage(file));
    }

    @GetMapping("download/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName) throws DataFormatException, IOException {
        return ResponseEntity.status(HttpStatus.FOUND).contentType(MediaType.IMAGE_PNG).body(storageService.downloadImage(fileName));
    }
}
