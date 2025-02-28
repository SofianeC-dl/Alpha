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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@AllArgsConstructor
public class ImageProjectController implements ImageProjectApi {

    private final ImageProjectService imageProjectService;

    @Override
    @CrossOrigin
    public ResponseEntity<ImageProjectDto> _addImageProject(ImageProjectDto imageProjectDto) {
        ImageProjectDto imageProjectDtoResult = imageProjectService.addImageProject(imageProjectDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(imageProjectDtoResult.getId())
                .toUri();

        return ResponseEntity.created(location).body(imageProjectDtoResult);
    }

    @Override
    @CrossOrigin
    public ResponseEntity<ImageProjectList> _addManyImageProject(ImageProjectList imageProjectList){
        ImageProjectList imageProjectListResult = new ImageProjectList(this.imageProjectService.addManyImageProject(imageProjectList.getImageProjectList()));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(imageProjectListResult.getImageProjectList())
                .toUri();

        return ResponseEntity.created(location).body(imageProjectListResult);
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
