package com.alphabackend.service;

import com.alpha.generated.model.ProjectDto;
import com.alpha.generated.model.ResultDto;
import com.alpha.generated.model.ResultEnum;
import com.alphabackend.exception.ResourceNotFoundException;
import com.alphabackend.mapper.ProjectMapper;
import com.alphabackend.model.ImageIllustrationEntity;
import com.alphabackend.model.ProjectEntity;
import com.alphabackend.model.enum_model.ErrorText;
import com.alphabackend.model.enum_model.NameObject;
import com.alphabackend.repository.ImageIllustrationRepository;
import com.alphabackend.repository.ProjectRepository;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Builder
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ImageIllustrationRepository imageIllustrationRepository;

    private final ProjectMapper projectMapper;

    public ProjectDto getProject(Long id) {
        return this.projectMapper.mapProjectEntityToProjectDto(projectRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(ErrorText.OBJECT_NOT_FOUND, NameObject.PROJECT_MAJ, id)));
    }

    public List<ProjectDto> getAllProjects() {
        List<ProjectEntity> projectEntityList = projectRepository.findAll();

        projectEntityList.stream().findFirst().orElseThrow(() -> new ResourceNotFoundException(ErrorText.OBJECT_NOT_FOUND));

        return this.projectMapper.mapProjectEntityListToProjectDtoList(projectEntityList);
    }

    public ProjectDto addProject(ProjectDto projectDto) {
        ProjectEntity projectEntity = this.projectMapper.mapProjectDtoToProjectEntity(projectDto);

        if(projectEntity.getImageIllustrationEntity() != null && projectEntity.getImageIllustrationEntity().getId() != null) {
            Optional<ImageIllustrationEntity> optImage = imageIllustrationRepository.findById(projectEntity.getImageIllustrationEntity().getId());
            optImage.ifPresent(projectEntity::setImageIllustrationEntity);
        }

        return this.projectMapper.mapProjectEntityToProjectDto(this.projectRepository.save(projectEntity));
    }

    public ResultDto deleteProject(Long id) {
        this.projectRepository.findById(id).ifPresentOrElse(
                this.projectRepository::delete,
                () -> {
                    throw new ResourceNotFoundException(ErrorText.OBJECT_NOT_DELETE, id);
                });

        ResultDto resultDto = new ResultDto();

        this.projectRepository.findById(id).ifPresentOrElse(
                project -> resultDto.setResult(ResultEnum.INVALIDATE),
                () -> resultDto.setResult(ResultEnum.VALIDATE)
            );

        return resultDto;
    }
}
