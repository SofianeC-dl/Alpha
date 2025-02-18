package com.alphabackend.controller;

import com.alpha.generated.api.ProjectApi;
import com.alpha.generated.model.ProjectDto;
import com.alpha.generated.model.ProjectList;
import com.alphabackend.model.ProjectEntity;
import com.alphabackend.repository.ProjectRepository;
import com.alphabackend.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectController implements ProjectApi {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Override
    @CrossOrigin
    public ResponseEntity<ProjectDto> _addProject(ProjectDto projectDto) {
        ProjectDto projectDtoAdded = this.projectService.addProject(projectDto);

        return ResponseEntity.ok(projectDtoAdded);
    }

    @Override
    @CrossOrigin
    public ResponseEntity<ProjectDto> _deleteProject(String idProject) {
        return null;
    }

    @Override
    @CrossOrigin
    public ResponseEntity<ProjectList> _getAllProject() {
        return null;
    }

    @Override
    @CrossOrigin
    public ResponseEntity<ProjectDto> _getProject(String idProject) {
        ProjectDto projectDto = this.projectService.getProject(Long.parseLong(idProject));
        return ResponseEntity.ok(projectDto);
    }

    @Override
    @CrossOrigin
    public ResponseEntity<ProjectDto> _modifyProject(String idProject, ProjectDto projectDto) {
        return null;
    }
}
