package com.alphabackend.mapper;

import com.alpha.generated.model.ProjectDto;
import com.alphabackend.model.entity.ImageIllustrationEntity;
import com.alphabackend.model.entity.ProjectEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = { TagMapper.class })
public interface ProjectMapper {

    @Mapping(target = "tagSet", source = "tagEntitySet", qualifiedByName = "mapTagsToIds")
    @Mapping(source = "imageIllustrationEntity.id", target = "idImageIllustration")
    ProjectDto mapProjectEntityToProjectDto(ProjectEntity projectEntity);

    @Mapping(target = "tagEntitySet", source = "tagSet", qualifiedByName = "mapIdsToTags")
    @Mapping(source = "idImageIllustration", target = "imageIllustrationEntity")
    ProjectEntity mapProjectDtoToProjectEntity(ProjectDto projectDto);

    List<ProjectEntity> mapProjectDtoListToProjectEntityList(List<ProjectDto> projectDtoList);

    List<ProjectDto> mapProjectEntityListToProjectDtoList(List<ProjectEntity> projectEntityList);

    default ImageIllustrationEntity mapIdImageIllustrationToImageIllustrationEntity(Integer idImageIllustration) {
        if (idImageIllustration == null) {
            return null;
        }
        ImageIllustrationEntity imageIllustrationEntity = new ImageIllustrationEntity();
        imageIllustrationEntity.setId(idImageIllustration.longValue());
        return imageIllustrationEntity;
    }
}
