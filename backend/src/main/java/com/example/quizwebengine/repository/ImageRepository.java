package com.example.quizwebengine.repository;

import com.example.quizwebengine.model.util.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<ImageModel, Long> {

    Optional<ImageModel> findByUserId(Long id);

    Optional<ImageModel> findByQuizId(Long id);

}
