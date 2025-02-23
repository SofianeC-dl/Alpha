package com.alphabackend.controller;

import com.alpha.generated.api.ImageProjectApi;
import com.alpha.generated.model.ImageProjectDto;
import com.alpha.generated.model.ImageProjectList;
import com.alpha.generated.model.ResultDto;
import com.alphabackend.service.ImageProjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ImageProjectController implements ImageProjectApi {

    private final ImageProjectService imageProjectService;

    @Override
    @CrossOrigin
    public ResponseEntity<ImageProjectDto> _addImageProject(ImageProjectDto imageProjectDto) {
        return ResponseEntity.ok(this.imageProjectService.addImageProject(imageProjectDto));
    }

    @Override
    @CrossOrigin
    public ResponseEntity<ImageProjectList> _addManyImageProject(ImageProjectList imageProjectList){
        return ResponseEntity.ok(new ImageProjectList(this.imageProjectService.addManyImageProject(imageProjectList.getImageProjectList())));
    }

    @Override
    @CrossOrigin
    public ResponseEntity<ResultDto> _deleteImageProject(Long idImageProject) {
        return ResponseEntity.ok(this.imageProjectService.deleteImageProject(idImageProject));
    }

    @Override
    @CrossOrigin
    public ResponseEntity<ImageProjectList> _getAllImageProject() {
        return ResponseEntity.ok(new ImageProjectList(this.imageProjectService.getAllImageProjects()));
    }

    @Override
    @CrossOrigin
    public ResponseEntity<ImageProjectList> _getAllImageProjectByIdProject(Long idProject) {
        return ResponseEntity.ok(new ImageProjectList(this.imageProjectService.getAllImageProjectByProjectId(idProject)));
    }

    @Override
    @CrossOrigin
    public ResponseEntity<ImageProjectDto> _getImageProject(Long idImageProject) {
        return ResponseEntity.ok(this.imageProjectService.getImageProject(idImageProject));
    }

    @Override
    @CrossOrigin
    public ResponseEntity<ImageProjectDto> _updateImageProject(Long idImageProject, ImageProjectDto imageProjectDto) {
        return ResponseEntity.ok(this.imageProjectService.updateImageProject(idImageProject, imageProjectDto));
    }
}
