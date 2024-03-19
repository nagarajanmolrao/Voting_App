package com.sample.apps.voting_app.service.interfaces;

import com.sample.apps.voting_app.entities.Picture;
import com.sample.apps.voting_app.entities.dto.PictureDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface PictureServiceInterface {

    public Picture uploadPicture(MultipartFile file, String tag) throws IOException;

    public byte[] getPicture(String id) throws IOException;

    public Map<String, PictureDto> getPicturesInfo();

    public PictureDto getPictureInfo(String id);

    public PictureDto  updatePictureInfo(String id, PictureDto picInfo);

    public Picture updatePicture(String id, MultipartFile file);

    public Picture deletePicture(String id);

}
