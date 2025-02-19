package com.alphabackend.controller;

import com.alpha.generated.api.ImageIllustrationApi;
import com.alpha.generated.model.ImageIllustrationDto;
import com.alpha.generated.model.ImageIllustrationList;
import com.alpha.generated.model.ResultDto;
import com.alphabackend.service.ImageIllustrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class ImageIllustrationController implements ImageIllustrationApi {

    private final ImageIllustrationService imageIllustrationService;

    @Override
    @CrossOrigin
    public ResponseEntity<ImageIllustrationDto> _addImageIllustration(ImageIllustrationDto imageIllustrationDto) {
        return ResponseEntity.ok(this.imageIllustrationService.addImageIllustration(imageIllustrationDto));
    }

    @Override
    @CrossOrigin
    public ResponseEntity<ResultDto> _deleteImageIllustration(Long idImageIllustration) {
        return ResponseEntity.ok(this.imageIllustrationService.deleteImageIllustration(idImageIllustration));
    }

    @Override
    @CrossOrigin
    public ResponseEntity<ImageIllustrationList> _getAllImageIllustration() {
        return ResponseEntity.ok(new ImageIllustrationList(this.imageIllustrationService.getAllImageIllustrations()));
    }

    @Override
    @CrossOrigin
    public ResponseEntity<ImageIllustrationDto> _getImageIllustration(Long idImageIllustration) {
        return ResponseEntity.ok(this.imageIllustrationService.getImageIllustration(idImageIllustration));
    }

    @Override
    @CrossOrigin
    public ResponseEntity<ImageIllustrationDto> _getImageIllustrationByIdProject(Long idProject){
        return ResponseEntity.ok(this.imageIllustrationService.getImageIllustrationByProjectId(idProject));
    }

    @Override
    @CrossOrigin
    public ResponseEntity<ImageIllustrationDto> _updateImageIllustration(Long idImageIllustration, ImageIllustrationDto imageIllustrationDto) {
        return ResponseEntity.ok(this.imageIllustrationService.updateImageIllustration(idImageIllustration, imageIllustrationDto));
    }
}
