package com.alphabackend.mapper;

import com.alpha.generated.model.ImageIllustrationDto;
import com.alphabackend.model.ImageIllustrationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = { TagMapper.class })
public interface ImageIllustrationMapper {

    @Mapping(target = "tagSet", source = "tagEntitySet", qualifiedByName = "mapTagsToIds")
    ImageIllustrationDto mapImageIllustrationEntityToImageIllustrationDto(ImageIllustrationEntity imageIllustrationEntity);

    @Mapping(target = "tagEntitySet", source = "tagSet", qualifiedByName = "mapIdsToTags")
    ImageIllustrationEntity mapImageIllustrationDtoToImageIllustrationEntity(ImageIllustrationDto imageIllustrationDto);

    List<ImageIllustrationEntity> mapImageIllustrationDtoListToImageIllustrationEntityList(List<ImageIllustrationDto> projectDtoList);

    List<ImageIllustrationDto> mapImageIllustrationEntityListToImageIllustrationDtoList(List<ImageIllustrationEntity> projectEntityList);
}
