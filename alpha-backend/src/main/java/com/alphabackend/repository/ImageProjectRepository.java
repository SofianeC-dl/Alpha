package com.alphabackend.repository;

import com.alphabackend.model.ImageProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageProjectRepository extends JpaRepository<ImageProjectEntity, Long> {
    List<ImageProjectEntity> findByProjectEntity_Id(Long projectId);
}
