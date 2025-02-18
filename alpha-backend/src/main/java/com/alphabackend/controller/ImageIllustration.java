package com.alphabackend.controller;

import com.alpha.generated.api.ImageIllustrationApi;
import com.alpha.generated.model.ImageIllustrationDto;
import com.alpha.generated.model.ImageIllustrationList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageIllustration implements ImageIllustrationApi {
    @Override
    public ResponseEntity<ImageIllustrationDto> _addImageIllustration(ImageIllustrationDto imageIllustrationDto) {
        return null;
    }

    @Override
    public ResponseEntity<ImageIllustrationDto> _deleteImageIllustration(String idImageIllustration) {
        return null;
    }

    @Override
    public ResponseEntity<ImageIllustrationList> _getAllImageIllustration() {
        return null;
    }

    @Override
    public ResponseEntity<ImageIllustrationDto> _getImageIllustration(String idImageIllustration) {
        return null;
    }

    @Override
    public ResponseEntity<ImageIllustrationDto> _modifyImageIllustration(String idImageIllustration, ImageIllustrationDto imageIllustrationDto) {
        return null;
    }
}
