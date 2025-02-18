package com.alphabackend.service;

import com.alpha.generated.model.ProjectDto;
import com.alphabackend.mapper.ProjectMapper;
import com.alphabackend.model.ImageIllustrationEntity;
import com.alphabackend.model.ProjectEntity;
import com.alphabackend.repository.ImageIllustrationRepository;
import com.alphabackend.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ImageIllustrationRepository imageIllustrationRepository;

    private final ProjectMapper projectMapper;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, ImageIllustrationRepository imageIllustrationRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.imageIllustrationRepository = imageIllustrationRepository;
        this.projectMapper = projectMapper;
    }

    public ProjectDto getProject(Long id) {
        return this.projectMapper.mapProjectEntityToProjectDto(projectRepository.findById(id).orElseThrow());
    }

    public ProjectDto addProject(ProjectDto projectDto) {
        ProjectEntity projectEntity = this.projectMapper.mapProjectDtoToProjectEnity(projectDto);

        if(projectEntity.getImageIllustration() != null && projectEntity.getImageIllustration().getId() != null) {
            Optional<ImageIllustrationEntity> optImage = imageIllustrationRepository.findById(projectEntity.getImageIllustration().getId());
            optImage.ifPresent(projectEntity::setImageIllustration);
        }

        return this.projectMapper.mapProjectEntityToProjectDto(this.projectRepository.save(projectEntity));
    }
}
