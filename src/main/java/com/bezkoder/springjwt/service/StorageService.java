package com.bezkoder.springjwt.service;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;
import org.springframework.core.io.FileSystemResource;
import java.io.FileNotFoundException;
import com.google.cloud.storage.BlobId;

@Service
public class StorageService {

    @Value("${google.cloud.storage.bucket}")
    private String bucketName;

    @Value("classpath:skillspoint-65c283428257.json")
    private Resource gcpCredentials;

    public String uploadFile(Path uploadFilePath) {
        try {
            Storage storage = StorageOptions.newBuilder()
                    .setProjectId("skillspoint")
                    .setCredentials(ServiceAccountCredentials.fromStream(gcpCredentials.getInputStream()))
                    .build()
                    .getService();

            Blob blob = storage.create(BlobInfo.newBuilder(bucketName, uploadFilePath.getFileName().toString())
                            .setContentType(Files.probeContentType(uploadFilePath)).build(),
                    Files.readAllBytes(uploadFilePath));
            return blob.getMediaLink();
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload file", e);
        }
    }

    public Resource downloadFile(String filename) throws IOException {
        Storage storage = StorageOptions.getDefaultInstance().getService();
        Blob blob = storage.get(bucketName, filename);

        if (blob == null) {
            throw new FileNotFoundException("File not found in bucket: " + filename);
        }

        BlobId blobId = blob.getBlobId();
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

        Path tempFilePath = Files.createTempFile("download-", filename);

        blob.downloadTo(tempFilePath);

        return new FileSystemResource(tempFilePath.toFile());
    }
}
