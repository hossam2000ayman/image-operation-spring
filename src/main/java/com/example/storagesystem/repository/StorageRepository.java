package com.example.storagesystem.repository;

import com.example.storagesystem.entity.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StorageRepository extends JpaRepository<ImageData, Long> {
    Optional<ImageData> findImageDataByName(String fileName);

    boolean existsImageDataByImageData(byte[] date);

}
