package com.sample.apps.voting_app.rest_controllers;

import com.sample.apps.voting_app.entities.Picture;
import com.sample.apps.voting_app.entities.dto.PictureDto;
import com.sample.apps.voting_app.exceptions.ImageReadException;
import com.sample.apps.voting_app.service.PictureService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@CrossOrigin(origins = "*", allowCredentials="true")
@RestController
@Tag(name = "Picture API", description = "API endpoints of Picture")
@RequestMapping("/picture")
@Slf4j
public class PictureRestController {

    private final PictureService pictureService;

    @Autowired
    public PictureRestController(PictureService pictureService){
        this.pictureService = pictureService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Add a picture to the server")
    public ResponseEntity<PictureDto> uploadPicture(@RequestParam("image") MultipartFile file) throws IOException {
        if(file.getSize()>=500000){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Picture pic = pictureService.uploadPicture(file);
        return ResponseEntity.status(HttpStatus.OK).body(new PictureDto(pic));
    }

    @GetMapping(value = "/{Id}")
    @Operation(summary = "View a picture based on its ID")
    public ResponseEntity<byte[]> getPicture(@PathVariable("Id") String id) throws ImageReadException{
        byte[] image;
        try {
            image = pictureService.getPicture(id);
        } catch (IOException e) {
            log.debug(e.getLocalizedMessage());
            throw new ImageReadException("An exception occurred when getting data from the database");
        }
        if(image == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }

    @GetMapping(value="/info/{id}")
    @Operation(summary = "Get all data of a picture based on its ID")
    public ResponseEntity<PictureDto> getPictureInfo(@PathVariable("id") String id){
        Optional<PictureDto> picInfo = Optional.of(pictureService.getPictureInfo(id));
        return picInfo.map(value -> ResponseEntity.status(HttpStatus.OK).body(value))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping(value="/info/{id}")
    @Operation(summary = "Update Picture information type/linked ID")
    public ResponseEntity<PictureDto> updatePictureInfo(@PathVariable("id") String id,
                                                        @RequestBody PictureDto picInfo){

    }

}
