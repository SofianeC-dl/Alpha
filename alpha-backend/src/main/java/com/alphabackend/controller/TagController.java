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

@RestController
@AllArgsConstructor
public class TagController implements TagApi {
    public final TagService tagService;

    @Override
    @CrossOrigin
    public ResponseEntity<TagDto> _addTag(TagDto tagDto) {
        return ResponseEntity.ok(this.tagService.addTag(tagDto));
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
