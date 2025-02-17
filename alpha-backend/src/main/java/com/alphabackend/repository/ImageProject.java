package com.alphabackend.repository;

import com.alphabackend.model.ImageProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageProject extends JpaRepository<ImageProjectEntity, Long> {
}
