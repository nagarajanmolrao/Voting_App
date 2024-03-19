package com.sample.apps.voting_app.rest_controllers;

import com.sample.apps.voting_app.entities.Picture;
import com.sample.apps.voting_app.entities.dto.PictureDto;
import com.sample.apps.voting_app.exceptions.DataNotFoundException;
import com.sample.apps.voting_app.exceptions.PictureException;
import com.sample.apps.voting_app.service.PictureService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin
@RestController
@Tag(name = "Picture API", description = "API endpoints of Picture")
@RequestMapping("/pictures")
@Slf4j
public class PictureRestController {

    private final PictureService pictureService;

    @Autowired
    public PictureRestController(PictureService pictureService){
        this.pictureService = pictureService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Add a picture to the server")
    public ResponseEntity<PictureDto> uploadPicture(@RequestParam("image") MultipartFile file,
                                                    @RequestParam("tag") String tag) throws IOException {
        if(file.getSize()>=500000){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Picture pic = pictureService.uploadPicture(file, tag);
        return ResponseEntity.status(HttpStatus.OK).body(new PictureDto(pic));
    }

    @GetMapping
    @Operation(summary = "Get all pictures info")
    public ResponseEntity<Map<String, PictureDto>> getPicturesInfo(){
        return ResponseEntity.ok(pictureService.getPicturesInfo());
    }

    @GetMapping(value = "/{Id}")
    @Operation(summary = "View a picture based on its ID")
    public ResponseEntity<byte[]> getPicture(@PathVariable("Id") String id) throws PictureException {
        byte[] image;
        try {
            image = pictureService.getPicture(id);
        } catch (IOException e) {
            log.debug(e.getLocalizedMessage());
            throw new PictureException("An exception occurred when getting data from the database");
        }
        if(image == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }

    @GetMapping(value="/info/{Id}")
    @Operation(summary = "Get all data of a picture based on its ID")
    public ResponseEntity<PictureDto> getPictureInfo(@PathVariable("Id") String id){
        Optional<PictureDto> picInfo = Optional.of(pictureService.getPictureInfo(id));
        return picInfo.map(value -> ResponseEntity.status(HttpStatus.OK).body(value))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping(value="/info/{Id}")
    @Operation(summary = "Update Picture information type/linked ID")
    public ResponseEntity<PictureDto> updatePictureInfo(@PathVariable("Id") String id,
                                                        @RequestBody PictureDto picInfo){
        PictureDto pic = pictureService.updatePictureInfo(id, picInfo);
        if(pic == null){
            throw new DataNotFoundException("No picture with the given ID found !!");
        }else{
            return ResponseEntity.ok(pic);
        }
    }

    @PostMapping(value="/{Id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Update an existing picture using ID")
    public ResponseEntity<PictureDto> updatePicture(@RequestParam("image") MultipartFile file,
                                                    @PathVariable("Id") String id) {
        if(file.getSize()>=500000){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Picture pic = pictureService.updatePicture(id, file);
        return ResponseEntity.status(HttpStatus.OK).body(new PictureDto(pic));
    }

    @DeleteMapping(value = "/{Id}")
    @Operation(summary = "Delete a picture using ID")
    public ResponseEntity<PictureDto> deletePicture(@PathVariable("Id") String id){
        return ResponseEntity.status(HttpStatus.OK)
            .body(new PictureDto(pictureService.deletePicture(id)));
        }
}

