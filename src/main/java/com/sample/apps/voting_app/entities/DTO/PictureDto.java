package com.sample.apps.voting_app.entities.dto;

import com.sample.apps.voting_app.entities.Picture;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PictureDto {
    private UUID id;
    private String fileType;
    private String type;
    private String linkedId;

    private String tag;

    public PictureDto(Picture pic){
        this.id = pic.getId();
        this.fileType = pic.getFileType();
        this.type = pic.getType();
        this.linkedId = pic.getLinkedId();
        this.tag = pic.getTag();
    }
}
