package com.alphabackend.mapper;

import com.alpha.generated.model.ImageIllustrationDto;
import com.alphabackend.model.ImageIllustrationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ImageIllustrationMapper {

    ImageIllustrationDto mapImageIllustrationEntityToImageIllustrationDto(ImageIllustrationEntity imageIllustrationEntity);

    ImageIllustrationEntity mapImageIllustrationDtoToImageIllustrationEntity(ImageIllustrationDto imageIllustrationDto);
}
