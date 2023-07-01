package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.Training;
import com.bezkoder.springjwt.models.TrainingFile;
import com.bezkoder.springjwt.repository.TrainingRepository;
import com.bezkoder.springjwt.service.StorageService;
import com.bezkoder.springjwt.service.TrainingFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;

import java.io.IOException;
import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/skills")
public class FileUploadController {

    @Autowired
    TrainingFileService trainingFileService;

    @Autowired
    TrainingRepository trainingRepository;

    private final StorageService storageService;

    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/upload")
    @PreAuthorize("hasRole('USER') or hasRole('TRAINER') or hasRole('ADMIN')")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("trainingId") Integer trainingId) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("Please select a file!", HttpStatus.BAD_REQUEST);
        }
        try {
            String originalFileName = file.getOriginalFilename();
            String newFileName = trainingId + "_" + originalFileName;

            // convert multipart file to path
            Path path = Paths.get(newFileName);
            file.transferTo(path);

            // upload the file and return the public download link
            String downloadLink = storageService.uploadFile(path);

            TrainingFile trainingFile = new TrainingFile();
            trainingFile.setFileName(newFileName);
            Training training = trainingRepository.findById(trainingId).orElseThrow(() -> new Exception("Training not found"));
            trainingFile.setTraining(training);
            trainingFileService.save(trainingFile);

            return new ResponseEntity<>(downloadLink, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/download/{filename}")
    @PreAuthorize("hasRole('USER') or hasRole('TRAINER') or hasRole('ADMIN')")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
        try {
            Resource fileResource = storageService.downloadFile(filename);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileResource.getFilename() + "\"")
                    .body(fileResource);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping("/files/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('TRAINER') or hasRole('ADMIN')")
    public ResponseEntity<List<TrainingFile>> getTrainingFilesStartingWith(@PathVariable("id") Integer id) {
        try {
            List<TrainingFile> files = trainingFileService.findTrainingFilesStartingWith(id + "_");
            return new ResponseEntity<>(files, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}