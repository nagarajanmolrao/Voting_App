package com.sample.apps.voting_app.service;

import com.sample.apps.voting_app.entities.Picture;
import com.sample.apps.voting_app.entities.dto.PictureDto;
import com.sample.apps.voting_app.repositories.PictureRepository;
import com.sample.apps.voting_app.service.interfaces.PictureServiceInterface;
import com.sample.apps.voting_app.utils.CompressionUtility;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@Slf4j
public class PictureService implements PictureServiceInterface {

    private final PictureRepository pictureRepo;

    @Autowired
    public PictureService(PictureRepository pictureRepo) {
        this.pictureRepo = pictureRepo;
    }

    @Override
    public Picture uploadPicture(MultipartFile file) throws IOException {
        Picture picture;
        try {
            picture = pictureRepo.save(Picture.builder()
                    .type("PROFILE")
                    .fileType(file.getContentType())
                    .imageData(CompressionUtility.compressData(file.getBytes()))
                    .build());
        } catch (Exception ex) {
            throw new IOException("Exception in compression");
        }
        log.debug("Image stored in DB");
        return picture;
    }

    @Override
    @Transactional
    public byte[] getPicture(String id) throws IOException{
        Optional<Picture> picture = pictureRepo.findById(id);
        if (picture.isPresent()) {
            try{
                return CompressionUtility.decompressData(picture.get().getImageData());
            }catch (Exception ex){
                throw new IOException("Exception in Decompression");
            }
        } else {
            return null;
        }
    }

    @Override
    public PictureDto getPictureInfo(String id){
        Optional<Picture> picture = pictureRepo.findById(id);
        return picture.map(value -> PictureDto.builder().id(value.getId())
                .type(value.getType())
                .fileType(value.getFileType())
                .linkedId(value.getLinkedId()).build()).orElse(null);
    }

    @Override
    public Picture updatePicture(String id, Picture picture) {
        return null;
    }

    @Override
    public Picture deletePicture(String id) {
        return null;
    }
}
