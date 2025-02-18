package com.alphabackend.mapper;

import com.alpha.generated.model.ProjectDto;
import com.alphabackend.controller.ProjectController;
import com.alphabackend.model.ImageIllustrationEntity;
import com.alphabackend.model.ProjectEntity;
import com.alphabackend.service.ProjectService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    @Mapping(source = "imageIllustration.id", target = "idImageIllustration")
    ProjectDto mapProjectEntityToProjectDto(ProjectEntity projectEntity);

    @Mapping(source = "idImageIllustration", target = "imageIllustration")
    ProjectEntity mapProjectDtoToProjectEnity(ProjectDto projectDto);

    default ImageIllustrationEntity map(Long idImageIllustration) {
        if (idImageIllustration == null) {
            return null;
        }
        ImageIllustrationEntity sub = new ImageIllustrationEntity();
        sub.setId(idImageIllustration);
        return sub;
    }
}
