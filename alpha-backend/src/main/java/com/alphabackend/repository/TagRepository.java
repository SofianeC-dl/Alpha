package com.alphabackend.repository;

import com.alphabackend.model.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<TagEntity, Long> {
}
