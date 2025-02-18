package com.alphabackend.controller;

import com.alpha.generated.api.TagApi;
import com.alpha.generated.model.ResultDto;
import com.alpha.generated.model.TagDto;
import com.alpha.generated.model.TagList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TagController implements TagApi {
    @Override
    public ResponseEntity<TagDto> _addTag(TagDto tagDto) {
        return null;
    }

    @Override
    public ResponseEntity<ResultDto> _deleteTag(String idTag) {
        return null;
    }

    @Override
    public ResponseEntity<TagList> _getAllTag() {
        return null;
    }

    @Override
    public ResponseEntity<TagDto> _getTag(String idTag) {
        return null;
    }

    @Override
    public ResponseEntity<TagDto> _modifyTag(String idTag, TagDto tagDto) {
        return null;
    }
}
