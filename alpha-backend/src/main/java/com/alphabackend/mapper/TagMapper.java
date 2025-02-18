package com.alphabackend.mapper;

import com.alpha.generated.model.TagDto;
import com.alphabackend.model.TagEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagMapper {
    TagDto mapTagEntityToTag(TagEntity tagEntity);

    TagEntity mapTagDtoToTagEntity(TagDto tagDto);
}
