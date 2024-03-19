package com.sample.apps.voting_app.service;

import com.sample.apps.voting_app.entities.Picture;
import com.sample.apps.voting_app.entities.dto.PictureDto;
import com.sample.apps.voting_app.exceptions.DataNotFoundException;
import com.sample.apps.voting_app.exceptions.PictureException;
import com.sample.apps.voting_app.repositories.PictureRepository;
import com.sample.apps.voting_app.service.interfaces.PictureServiceInterface;
import com.sample.apps.voting_app.utils.CompressionUtility;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PictureService implements PictureServiceInterface {

    private final PictureRepository pictureRepo;

    @Autowired
    public PictureService(PictureRepository pictureRepo) {
        this.pictureRepo = pictureRepo;
    }

    @Override
    public Picture uploadPicture(MultipartFile file, String tag) throws IOException {
        Picture picture;
        try {
            picture = pictureRepo.save(Picture.builder()
                    .type("PROFILE")
                    .fileType(file.getContentType())
                    .imageData(CompressionUtility.compressData(file.getBytes()))
                    .tag(tag)
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
        Optional<Picture> picture = pictureRepo.findById(UUID.fromString(id));
        if (picture.isPresent()) {
            try{
                return CompressionUtility.decompressData(picture.get().getImageData());
            }catch (Exception ex){
                log.debug(ex.getMessage());
                throw new IOException("Exception in Decompression");
            }
        } else {
            return null;
        }
    }

    @Override
    public Map<String, PictureDto> getPicturesInfo(){
        List<Picture> pics = pictureRepo.findAll();
        return pics.stream()
                .collect(Collectors.toMap(item->item.getId().toString(),
                        PictureDto::new));
    }

    @Override
    public PictureDto getPictureInfo(String id){
        Optional<Picture> picture = pictureRepo.findById(UUID.fromString(id));
        return picture.map(value -> PictureDto.builder().id(value.getId())
                .type(value.getType())
                .fileType(value.getFileType())
                .linkedId(value.getLinkedId())
                .tag(value.getTag()).build()).orElse(null);
    }

    @Override
    public PictureDto updatePictureInfo(String id, PictureDto picInfo){
        Optional<Picture> picture = pictureRepo.findById(UUID.fromString(id));
        if(picture.isEmpty()){
            return null;
        }else{
            if(picInfo.getTag() != null){
                picture.get().setTag(picInfo.getTag());
            }
            if(picInfo.getType() != null) {
                picture.get().setType(picInfo.getType());
            }
            if(picInfo.getLinkedId() != null){
                picture.get().setLinkedId(picInfo.getLinkedId());
            }
            return new PictureDto(pictureRepo.save(picture.get()));
        }
    }

    @Override
    public Picture updatePicture(String id, MultipartFile file) {
        Optional<Picture> pic = pictureRepo.findById(UUID.fromString(id));
        if(pic.isEmpty()){
            throw new DataNotFoundException("No picture found with given ID !!");
        }else{
            try{
                pic.get().setImageData(CompressionUtility.compressData(file.getBytes()));
            }catch (Exception e){
                log.debug(e.getMessage());
                throw new PictureException("Exception while updating the picture");
            }
        }
        return pictureRepo.save(pic.get());
    }

    @Override
    public Picture deletePicture(String id) {
        Optional<Picture> pic = pictureRepo.findById(UUID.fromString(id));
        if(pic.isEmpty()){
            throw new DataNotFoundException("No picture found with given ID !!");
        }else{
            pictureRepo.delete(pic.get());
            return pic.get();
        }
    }
}
