package com.alphabackend.mapper;

import com.alpha.generated.model.ProjectDto;
import com.alphabackend.model.entity.ImageIllustrationEntity;
import com.alphabackend.model.entity.ProjectEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Mapper(componentModel = "spring", uses = { TagMapper.class })
public interface ProjectMapper {

    @Mapping(target = "tagSet", source = "tagEntitySet", qualifiedByName = "mapTagsToIds")
    @Mapping(source = "uploadDate", target = "uploadDate", qualifiedByName = "mapInstantToOffSetDateTime")
    @Mapping(source = "updateDate", target = "updateDate", qualifiedByName = "mapInstantToOffSetDateTime")
    @Mapping(source = "imageIllustrationEntity.id", target = "idImageIllustration")
    ProjectDto mapProjectEntityToProjectDto(ProjectEntity projectEntity);

    @Mapping(target = "tagEntitySet", source = "tagSet", qualifiedByName = "mapIdsToTags")
    @Mapping(source = "uploadDate", target = "uploadDate", qualifiedByName = "mapOffSetDateTimeToInstant")
    @Mapping(source = "updateDate", target = "updateDate", qualifiedByName = "mapOffSetDateTimeToInstant")
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

    @Named("mapInstantToOffSetDateTime")
    default OffsetDateTime mapInstantToOffSetDateTime(Instant value) {
        return value != null ? OffsetDateTime.ofInstant(value, ZoneOffset.UTC) : null;
    }

    @Named("mapOffSetDateTimeToInstant")
    default Instant mapOffSetDateTimeToInstant(OffsetDateTime value) {
        return value != null ? value.toInstant() : null;
    }
}
