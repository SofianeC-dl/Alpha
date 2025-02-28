package com.alphabackend.mapper;

import com.alpha.generated.model.ImageIllustrationDto;
import com.alphabackend.model.entity.ImageIllustrationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = { TagMapper.class, ProjectMapper.class })
public interface ImageIllustrationMapper {

    @Mapping(target = "tagSet", source = "tagEntitySet", qualifiedByName = "mapTagsToIds")
    @Mapping(source = "uploadDate", target = "uploadDate", qualifiedByName = "mapInstantToOffSetDateTime")
    ImageIllustrationDto mapImageIllustrationEntityToImageIllustrationDto(ImageIllustrationEntity imageIllustrationEntity);

    @Mapping(target = "tagEntitySet", source = "tagSet", qualifiedByName = "mapIdsToTags")
    @Mapping(source = "uploadDate", target = "uploadDate", qualifiedByName = "mapOffSetDateTimeToInstant")
    ImageIllustrationEntity mapImageIllustrationDtoToImageIllustrationEntity(ImageIllustrationDto imageIllustrationDto);

    List<ImageIllustrationEntity> mapImageIllustrationDtoListToImageIllustrationEntityList(List<ImageIllustrationDto> projectDtoList);

    List<ImageIllustrationDto> mapImageIllustrationEntityListToImageIllustrationDtoList(List<ImageIllustrationEntity> projectEntityList);
}
