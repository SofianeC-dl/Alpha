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
    public ResponseEntity<ProjectDto> _addProject(ProjectDto projectDto) {
        return null;
    }

    @Override
    public ResponseEntity<ProjectDto> _deleteProject(String idProject) {
        return null;
    }

    @Override
    public ResponseEntity<ProjectList> _getAllProject() {
        return null;
    }

    @Override
    @CrossOrigin
    public ResponseEntity<ProjectDto> _getProject(String idProject) {
        ProjectEntity projectEntity = this.projectService.getProject(Long.parseLong(idProject));

        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(projectEntity.getId().intValue());
        projectDto.setDescription(projectEntity.getDescription());
        projectDto.setIdImageIllustration(projectEntity.getImageIllustration().getId().intValue());

        return ResponseEntity.ok(projectDto);
    }

    @Override
    public ResponseEntity<ProjectDto> _modifyProject(String idProject, ProjectDto projectDto) {
        return null;
    }
}
