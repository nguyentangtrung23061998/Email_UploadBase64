package com.spring.file.springangularreadfile.ImageRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.file.springangularreadfile.model.ImageModel;

public interface ImageRepository extends JpaRepository<ImageModel, Long> {
	Optional<ImageModel> findByName(String name);
}
