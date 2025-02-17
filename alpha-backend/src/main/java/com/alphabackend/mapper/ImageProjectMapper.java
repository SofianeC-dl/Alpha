package com.alphabackend.mapper;

import com.alpha.generated.model.ImageProjectDto;
import com.alphabackend.model.ImageProjectEntity;
import com.alphabackend.repository.ImageProject;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageProjectMapper {
    ImageProjectDto mapImageProjectToImageProjectDto(ImageProjectEntity imageProject);

    ImageProjectEntity mapImageProjectToImageProjectEntity(ImageProjectDto imageProjectDto);
}
