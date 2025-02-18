package com.alphabackend.controller;

import com.alpha.generated.api.ImageProjectApi;
import com.alpha.generated.model.ImageIllustrationDto;
import com.alpha.generated.model.ImageProjectDto;
import com.alpha.generated.model.ImageProjectList;
import com.alpha.generated.model.ResultDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Table;

@RestController
public class ImageProjectController implements ImageProjectApi {
    @Override
    public ResponseEntity<ImageProjectDto> _addImageProject(ImageProjectDto imageProjectDto) {
        return null;
    }

    @Override
    public ResponseEntity<ResultDto> _deleteImageProject(String idImageProject) {
        return null;
    }

    @Override
    public ResponseEntity<ImageProjectList> _getAllImageProject() {
        return null;
    }

    @Override
    public ResponseEntity<ImageProjectList> _getAllImageProjectByIdProject(String idProject) {
        return null;
    }

    @Override
    public ResponseEntity<ImageIllustrationDto> _getImageIllustrationByIdProject(String idProject) {
        return null;
    }

    @Override
    public ResponseEntity<ImageProjectDto> _getImageProject(String idImageProject) {
        return null;
    }

    @Override
    public ResponseEntity<ImageProjectDto> _modifyImageProject(String idImageProject, ImageProjectDto imageProjectDto) {
        return null;
    }
}
