package com.alphabackend.controller;

import com.alpha.generated.api.ProjectApi;
import com.alpha.generated.model.ProjectDto;
import com.alpha.generated.model.ProjectList;
import com.alpha.generated.model.ResultDto;
import com.alphabackend.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ProjectController implements ProjectApi {

    private final ProjectService projectService;

    @Override
    @CrossOrigin
    public ResponseEntity<ProjectDto> _addProject(ProjectDto projectDto) {
        return ResponseEntity.ok(this.projectService.addProject(projectDto));
    }

    @Override
    @CrossOrigin
    public ResponseEntity<ResultDto> _deleteProject(Long idProject) {
        return ResponseEntity.ok(this.projectService.deleteProject(idProject));
    }

    @Override
    @CrossOrigin
    public ResponseEntity<ProjectList> _getAllProject() {
        return ResponseEntity.ok(new ProjectList(projectService.getAllProjects()));
    }

    @Override
    @CrossOrigin
    public ResponseEntity<ProjectDto> _getProject(Long idProject) {
        return ResponseEntity.ok(this.projectService.getProject(idProject));
    }

    @Override
    @CrossOrigin
    public ResponseEntity<ProjectDto> _updateProject(Long idProject, ProjectDto projectDto) {
        return ResponseEntity.ok(this.projectService.updateProject(idProject, projectDto));
    }
}
