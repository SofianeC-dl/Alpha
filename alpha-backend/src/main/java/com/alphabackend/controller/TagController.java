package com.alphabackend.controller;

import com.alpha.generated.api.TagApi;
import com.alpha.generated.model.ResultDto;
import com.alpha.generated.model.TagDto;
import com.alpha.generated.model.TagList;
import com.alphabackend.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@AllArgsConstructor
public class TagController implements TagApi {
    public final TagService tagService;

    @Override public ResponseEntity<TagList> _addManyTag(TagList tagList){
        TagList tagListResult = new TagList(this.tagService.addManyTag(tagList.getTagList()));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(tagListResult.getTagList())
                .toUri();

        return ResponseEntity.created(location).body(tagListResult);
    }

    @Override
    @CrossOrigin
    public ResponseEntity<TagDto> _addTag(TagDto tagDto) {
        TagDto tagDtoResult = this.tagService.addTag(tagDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(tagDtoResult.getId())
                .toUri();

        return ResponseEntity.created(location).body(tagDtoResult);
    }

    @Override
    @CrossOrigin
    public ResponseEntity<ResultDto> _deleteTag(Long idTag) {
        return ResponseEntity.ok(this.tagService.deleteTag(idTag));
    }

    @Override
    @CrossOrigin
    public ResponseEntity<TagList> _getAllTag() {
        return ResponseEntity.ok(new TagList(this.tagService.getAllTags()));
    }

    @Override
    @CrossOrigin
    public ResponseEntity<TagDto> _getTag(Long idTag) {
        return ResponseEntity.ok(this.tagService.getTag(idTag));
    }

    @Override
    @CrossOrigin
    public ResponseEntity<TagDto> _updateTag(Long idTag, TagDto tagDto) {
        return ResponseEntity.ok(this.tagService.updateTag(idTag, tagDto));
    }
}
