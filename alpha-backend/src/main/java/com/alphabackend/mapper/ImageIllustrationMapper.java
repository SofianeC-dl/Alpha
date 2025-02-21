package com.alphabackend.mapper;

import com.alpha.generated.model.ImageIllustrationDto;
import com.alphabackend.model.ImageIllustrationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImageIllustrationMapper {

    @Mapping(target = "tagSet", source = "tagEntitySet")
    ImageIllustrationDto mapImageIllustrationEntityToImageIllustrationDto(ImageIllustrationEntity imageIllustrationEntity);

    @Mapping(target = "tagEntitySet", source = "tagSet")
    ImageIllustrationEntity mapImageIllustrationDtoToImageIllustrationEntity(ImageIllustrationDto imageIllustrationDto);

    List<ImageIllustrationEntity> mapImageIllustrationDtoListToImageIllustrationEntityList(List<ImageIllustrationDto> projectDtoList);

    List<ImageIllustrationDto> mapImageIllustrationEntityListToImageIllustrationDtoList(List<ImageIllustrationEntity> projectEntityList);
}
