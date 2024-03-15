package com.sample.apps.voting_app.entities.DTO;

import com.sample.apps.voting_app.entities.Picture;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PictureDto {
    private String id;
    private String fileType;
    private String type;
    private Long linkedId;

    public PictureDto(Picture pic){
        this.id = pic.getId();
        this.fileType = pic.getFileType();
        this.type = pic.getType();
        this.linkedId = pic.getLinkedId();
    }
}
