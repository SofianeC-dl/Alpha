package com.alphabackend.mapper;

import com.alpha.generated.model.TagDto;
import com.alphabackend.model.entity.TagEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TagMapper {
    
    TagDto mapTagEntityToTagDto(TagEntity tagEntity);

    @Mapping(target = "projectEntitySet", ignore = true)
    @Mapping(target = "imageProjectEntitySet", ignore = true)
    @Mapping(target = "imageIllustrationEntitySet", ignore = true)
    TagEntity mapTagDtoToTagEntity(TagDto tagDto);

    List<TagEntity> mapTagDtoListToTagEntityList(List<TagDto> tagDtoList);

    List<TagDto> mapTagEntityListToTagDtoList(List<TagEntity> tagEntityList);

    @Named("mapTagsToIds")
    default List<Long> mapTagsToIds(Set<TagEntity> tags) {
        if (tags == null) {
            return null;
        }
        return tags.stream()
                .map(TagEntity::getId)
                .toList();
    }

    @Named("mapIdsToTags")
    default Set<TagEntity> mapIdsToTags(List<Long> ids) {
        if (ids == null) {
            return null;
        }
        return ids.stream()
                .map(id -> {
                    TagEntity tag = new TagEntity();
                    tag.setId(id);
                    return tag;
                })
                .collect(Collectors.toSet());
    }
}
