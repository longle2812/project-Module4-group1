package com.codegym.project.controller;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.codegym.project.model.Image;
import com.codegym.project.model.Picture;
import com.codegym.project.service.fileUpload.FilesStorageService;
import com.codegym.project.service.fileUpload.ResponseMessage;
import com.codegym.project.service.image.IImageService;
import com.codegym.project.service.picture.IPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@Controller
@CrossOrigin("http://localhost:8080")
public class FilesController {
    @Autowired
    FilesStorageService storageService;

    @Autowired
    private IImageService imageService;

    @Autowired
    private IPictureService pictureService;

    @PostMapping("/upload/image")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            String fileName = file.getOriginalFilename();
            String currentDate = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
            currentDate ="("+currentDate+")";
            int index = fileName.length() - 4;
            StringBuffer newString = new StringBuffer(fileName);
            newString.insert(index, currentDate);
            fileName = newString.toString();
            storageService.save(file);
            Image newFile = new Image(fileName, "http://localhost:8080/files/" + fileName);
            imageService.save(newFile);
            message = "Uploaded the file successfully: " + fileName;
            return new ResponseEntity<>(fileName, HttpStatus.OK);
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<Image>> getListFiles() {
        List<Image> fileInfos = storageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();
            return new Image(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/upload/picture")
    public ResponseEntity<ResponseMessage> uploadPicture(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            storageService.save(file);
            pictureService.save(new Picture(file.getOriginalFilename(), "http://localhost:8080/files/" + file.getOriginalFilename()));
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }
}
