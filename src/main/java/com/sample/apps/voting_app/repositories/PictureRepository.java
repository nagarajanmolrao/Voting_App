package com.sample.apps.voting_app.repositories;

import com.sample.apps.voting_app.entities.Picture;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PictureRepository extends JpaRepository<Picture, String> {
}
