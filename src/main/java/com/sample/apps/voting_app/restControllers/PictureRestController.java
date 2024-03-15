package com.sample.apps.voting_app.restControllers;

import com.sample.apps.voting_app.entities.Picture;
import com.sample.apps.voting_app.entities.DTO.PictureDto;
import com.sample.apps.voting_app.service.PictureService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/picture")
@Slf4j
public class PictureRestController {

    private final PictureService pictureService;

    @Autowired
    public PictureRestController(PictureService pictureService){
        this.pictureService = pictureService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadPicture(@RequestParam("image") MultipartFile file) throws IOException {
        if(file.getSize()>=500000){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("File size must be less than 500KB");
        }
        Picture pic = pictureService.uploadPicture(file);
        return ResponseEntity.status(HttpStatus.OK).body(new PictureDto(pic));
    }

    @GetMapping(value = "/{Id}")
    public ResponseEntity<?> getPicture(@PathVariable("Id") String id) throws IOException{
        byte[] image = pictureService.getPicture(id);
        if(image == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }
}
