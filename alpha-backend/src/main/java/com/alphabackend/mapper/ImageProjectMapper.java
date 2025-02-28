package com.alphabackend.mapper;

import com.alpha.generated.model.ImageProjectDto;
import com.alphabackend.model.entity.ImageProjectEntity;
import com.alphabackend.model.entity.ProjectEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = { TagMapper.class, ProjectMapper.class })
public interface ImageProjectMapper {

    @Mapping(target = "tagSet", source = "tagEntitySet", qualifiedByName = "mapTagsToIds")
    @Mapping(source = "uploadDate", target = "uploadDate", qualifiedByName = "mapInstantToOffSetDateTime")
    @Mapping(target = "idProject", source = "projectEntity.id")
    ImageProjectDto mapImageProjectEntityToImageProjectDto(ImageProjectEntity imageProject);

    @Mapping(target = "tagEntitySet", source = "tagSet", qualifiedByName = "mapIdsToTags")
    @Mapping(source = "uploadDate", target = "uploadDate", qualifiedByName = "mapOffSetDateTimeToInstant")
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
