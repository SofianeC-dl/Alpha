package com.alphabackend.service;

import com.alphabackend.model.ProjectEntity;
import com.alphabackend.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public ProjectEntity getProject(Long id) {
        return projectRepository.findById(id).orElseThrow();
    }
}
