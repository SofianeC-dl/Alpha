package com.alphabackend.service;

import com.alpha.generated.model.ProjectDto;
import com.alpha.generated.model.ResultDto;
import com.alpha.generated.model.ResultEnum;
import com.alphabackend.exception.ResourceNotFoundException;
import com.alphabackend.mapper.ProjectMapper;
import com.alphabackend.model.ImageIllustrationEntity;
import com.alphabackend.model.ProjectEntity;
import com.alphabackend.model.enum_model.ErrorText;
import com.alphabackend.model.enum_model.NameObject;
import com.alphabackend.repository.ImageIllustrationRepository;
import com.alphabackend.repository.ProjectRepository;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Builder
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ImageIllustrationRepository imageIllustrationRepository;

    private final ProjectMapper projectMapper;

    /**
     * Renvoie l'objet "Project" avec son ID
     * @param id id de l'objet "Project"
     * @return Les données de l'objet "Project"
     */
    public ProjectDto getProject(Long id) {
        return this.projectMapper.mapProjectEntityToProjectDto(projectRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(ErrorText.OBJECT_NOT_FOUND, NameObject.PROJECT_MAJ.getName(), id)));
    }

    /**
     * Retourne tous les objets "Project" présente dans la table "Project"
     * @return La liste des objets "Project" présents dans la table
     */
    public List<ProjectDto> getAllProjects() {
        List<ProjectEntity> projectEntityList = projectRepository.findAll();

        projectEntityList.stream().findFirst().orElseThrow(() -> new ResourceNotFoundException(ErrorText.ALL_OBJECTS_NOT_FOUND, NameObject.PROJECT_MAJ.getName()));

        return this.projectMapper.mapProjectEntityListToProjectDtoList(projectEntityList);
    }

    /**
     * Ajoute un objet "Project" dans la table "Project"
     * @param projectDto Objet "Project" à ajouter dans la table "Project"
     * @return Le nouvel objet "Project" avec son ID
     */
    public ProjectDto addProject(ProjectDto projectDto) {
        ProjectEntity projectEntity = this.projectMapper.mapProjectDtoToProjectEntity(projectDto);

        if(projectEntity.getImageIllustrationEntity() != null && projectEntity.getImageIllustrationEntity().getId() != null) {
            Optional<ImageIllustrationEntity> optImage = this.imageIllustrationRepository.findById(projectEntity.getImageIllustrationEntity().getId());
            optImage.ifPresent(projectEntity::setImageIllustrationEntity);
        }

        return this.projectMapper.mapProjectEntityToProjectDto(this.projectRepository.save(projectEntity));
    }

    /**
     * Supprime l'objet "Project" de la table "Project"
     * @param id ID de l'object" Project" à supprimer
     * @return "Validate" si l'objet "Project" à bien été supprimé, sinon "Invalidate" si non supprimé
     */
    public ResultDto deleteProject(Long id) {
        this.projectRepository.findById(id).ifPresentOrElse(
                this.projectRepository::delete,
                () -> {
                    throw new ResourceNotFoundException(ErrorText.OBJECT_NONEXISTENT_DELETE, NameObject.PROJECT_MAJ.getName(), id);
                });

        ResultDto resultDto = new ResultDto();

        resultDto.setResult(!this.projectRepository.existsById(id) ? ResultEnum.VALIDATE : ResultEnum.INVALIDATE);

        return resultDto;
    }

    /**
     * Mise à jour de l'objet "Project" dans la table "Project"
     * @param idProject ID de l'objet "Project" à modifier
     * @param projectDto Nouvelles données de l'objet "Project"
     * @return Le nouvel objet "Project"
     */
    public ProjectDto updateProject(Long idProject, ProjectDto projectDto) {
        ProjectEntity projectEntity = this.projectMapper.mapProjectDtoToProjectEntity(projectDto);

        if (!this.projectRepository.existsById(idProject)) {
            throw new ResourceNotFoundException(ErrorText.OBJECT_NONEXISTENT_UPDATE, NameObject.PROJECT_MAJ.getName(), projectDto.getId());
        }

        ProjectEntity updatedProjectEntity = this.projectRepository.save(projectEntity);

        return this.projectMapper.mapProjectEntityToProjectDto(updatedProjectEntity);
    }
}
