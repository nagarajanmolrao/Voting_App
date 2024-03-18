package com.sample.apps.voting_app.service.interfaces;

import com.sample.apps.voting_app.entities.Picture;
import com.sample.apps.voting_app.entities.dto.PictureDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PictureServiceInterface {

    public Picture uploadPicture(MultipartFile file) throws IOException;

    public byte[] getPicture(String id) throws IOException;

    public PictureDto getPictureInfo(String id);

    public Picture updatePicture(String id, Picture picture);

    public Picture deletePicture(String id);

}
