package com.alphabackend.controller;

import com.alpha.generated.api.ProjectApi;
import com.alpha.generated.model.ProjectDto;
import org.springframework.http.ResponseEntity;

public class ProjectController implements ProjectApi {

    @Override public ResponseEntity<ProjectDto> _getProject(String idProject){
        return null;
    }
}
