package com.alphabackend.mapper;

import com.alpha.generated.model.ImageProjectDto;
import com.alphabackend.model.ImageProjectEntity;
import com.alphabackend.model.ProjectEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImageProjectMapper {

    @Mapping(target = "tagSet", source = "tagEntitySet")
    @Mapping(target = "idProject", source = "projectEntity")
    ImageProjectDto mapImageProjectEntityToImageProjectDto(ImageProjectEntity imageProject);

    @Mapping(target = "tagEntitySet", source = "tagSet")
    @Mapping(target = "projectEntity.id", source = "idProject")
    ImageProjectEntity mapImageProjectDtoToImageProjectEntity(ImageProjectDto imageProjectDto);

    List<ImageProjectEntity> mapImageProjectDtoListToImageProjectEntityList(List<ImageProjectDto> projectDtoList);

    List<ImageProjectDto> mapImageProjectEntityListToImageProjectDtoList(List<ImageProjectEntity> projectEntityList);

    default Integer mapProjectEntityToIdProject(ProjectEntity projectEntity) {
        if (projectEntity == null || projectEntity.getId() == null) {
            return null;
        }

        return projectEntity.getId().intValue();
    }
}
