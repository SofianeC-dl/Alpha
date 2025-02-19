package com.alphabackend.mapper;

import com.alpha.generated.model.TagDto;
import com.alphabackend.model.TagEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TagMapper {
    TagDto mapTagEntityToTagDto(TagEntity tagEntity);

    TagEntity mapTagDtoToTagEntity(TagDto tagDto);

    List<TagEntity> mapTagDtoListToTagEntityList(List<TagDto> tagDtoList);

    List<TagDto> mapTagEntityListToTagDtoList(List<TagEntity> tagEntityList);
}
