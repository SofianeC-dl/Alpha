package com.alphabackend.service;

import com.alpha.generated.model.*;
import com.alphabackend.exception.ResourceNotFoundException;
import com.alphabackend.mapper.ImageIllustrationMapper;
import com.alphabackend.mapper.ProjectMapper;
import com.alphabackend.model.entity.ImageIllustrationEntity;
import com.alphabackend.model.entity.ParamsError;
import com.alphabackend.model.entity.ProjectEntity;
import com.alphabackend.model.entity.TagEntity;
import com.alphabackend.model.enum_model.ErrorTextEnum;
import com.alphabackend.model.enum_model.NameObject;
import com.alphabackend.model.enum_model.TypeRequestHttpEnum;
import com.alphabackend.repository.ImageIllustrationRepository;
import com.alphabackend.repository.ProjectRepository;
import com.alphabackend.repository.TagRepository;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Data
@Builder
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    private final ImageIllustrationService imageIllustrationService;
    private final ImageIllustrationRepository imageIllustrationRepository;

    private final TagRepository tagRepository;

    private final ProjectMapper           projectMapper;
    private final ImageIllustrationMapper imageIllustrationMapper;

    /**
     * Renvoie l'objet "Project" avec son ID
     * @param id id de l'objet "Project"
     * @return Les données de l'objet "Project"
     */
    public ProjectDto getProject(Long id) {
        return this.projectMapper.mapProjectEntityToProjectDto(projectRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        ParamsError.builder()
                                .errorText(ErrorTextEnum.OBJECT_NOT_FOUND)
                                .labelObject(NameObject.PROJECT_MAJ)
                                .typeRequestHttp(TypeRequestHttpEnum.GET_REQUEST)
                                .arg(id)
                                .build()
                        )));
    }

    /**
     * Retourne tous les objets "Project" présente dans la table "Project"
     * @return La liste des objets "Project" présents dans la table
     */
    public List<ProjectDto> getAllProjects() {
        List<ProjectEntity> projectEntityList = projectRepository.findAll();

        projectEntityList.stream().findFirst().orElseThrow(() -> new ResourceNotFoundException(
                ParamsError.builder()
                        .errorText(ErrorTextEnum.ALL_OBJECTS_NOT_FOUND)
                        .labelObject(NameObject.PROJECT_MAJ)
                        .typeRequestHttp(TypeRequestHttpEnum.GET_REQUEST)
                        .arg(NameObject.IMAGE_ILLUSTRATION_MAJ.getName())
                        .build()
                ));

        return this.projectMapper.mapProjectEntityListToProjectDtoList(projectEntityList);
    }

    /**
     * Ajoute un objet "Project" dans la table "Project"
     * @param projectDto Objet "Project" à ajouter dans la table "Project"
     * @return Le nouvel objet "Project" avec son ID
     */
    public ProjectDto addProject(ProjectDto projectDto) {
        ProjectEntity projectEntity = this.projectMapper.mapProjectDtoToProjectEntity(projectDto);

        return addProject(projectEntity);
    }

    /**
     * Ajoute un objet "Project" dans la table "Project"
     * @param projectWithImageIllustrationDto Objet "Project" avec l'objet "ImageIllustration" à ajouter dans la table "Project"
     * @return Le nouvel objet "Project" avec son ID
     */
    public ProjectDto addProjectWithImageIllustration(ProjectWithImageIllustrationDto projectWithImageIllustrationDto) {
        ImageIllustrationDto imageIllustrationDtoResult = this.imageIllustrationService.addImageIllustration(projectWithImageIllustrationDto.getImageIllustrationDto());

        ImageIllustrationEntity imageIllustrationEntityResult = this.imageIllustrationMapper.mapImageIllustrationDtoToImageIllustrationEntity(imageIllustrationDtoResult);

        ProjectEntity projectEntity = this.projectMapper.mapProjectDtoToProjectEntity(projectWithImageIllustrationDto.getProjectDto());

        projectEntity.setImageIllustrationEntity(imageIllustrationEntityResult);

        return addProject(projectEntity);
    }

    /**
     * Enregistre en base un objet "Project"
     * @param projectEntity Entity "Project" à enregistrer
     * @return L'objet "Project" enregistré
     */
    private ProjectDto addProject(ProjectEntity projectEntity){
        projectEntity.setUploadDate(Instant.now());
        projectEntity.setUpdateDate(Instant.now());

        if(projectEntity.getImageIllustrationEntity() != null && projectEntity.getImageIllustrationEntity().getId() != null) {
            Optional<ImageIllustrationEntity> optImage = this.imageIllustrationRepository.findById(projectEntity.getImageIllustrationEntity().getId());
            optImage.ifPresentOrElse(projectEntity::setImageIllustrationEntity, () -> {
                throw new ResourceNotFoundException(
                        ParamsError.builder()
                                .errorText(ErrorTextEnum.OBJECT_NOT_FOUND)
                                .labelObject(NameObject.IMAGE_ILLUSTRATION_MAJ)
                                .typeRequestHttp(TypeRequestHttpEnum.GET_REQUEST)
                                .arg(projectEntity.getImageIllustrationEntity().getId())
                                .build()
                        );
            });
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
                    throw new ResourceNotFoundException(
                            ParamsError.builder()
                                    .errorText(ErrorTextEnum.OBJECT_NONEXISTENT_DELETE)
                                    .labelObject(NameObject.PROJECT_MAJ)
                                    .typeRequestHttp(TypeRequestHttpEnum.DELETE_REQUEST)
                                    .arg(id)
                                    .build()
                            );
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

        ImageIllustrationEntity imageIllustrationEntity = this.imageIllustrationRepository.findById(projectEntity.getImageIllustrationEntity().getId()).orElseThrow(() -> new ResourceNotFoundException(
                ParamsError.builder()
                        .errorText(ErrorTextEnum.OBJECT_NONEXISTENT_UPDATE)
                        .labelObject(NameObject.IMAGE_ILLUSTRATION_MAJ)
                        .typeRequestHttp(TypeRequestHttpEnum.PUT_REQUEST)
                        .arg(projectEntity.getImageIllustrationEntity().getId())
                        .build()
        ));

        ProjectEntity existingProject = this.projectRepository.findById(idProject)
                .orElseThrow(() -> new ResourceNotFoundException(
                    ParamsError.builder()
                            .errorText(ErrorTextEnum.OBJECT_NONEXISTENT_UPDATE)
                            .labelObject(NameObject.PROJECT_MAJ)
                            .typeRequestHttp(TypeRequestHttpEnum.PUT_REQUEST)
                            .arg(projectDto.getId())
                            .build()
                ));

        List<Long> tagIdList = projectDto.getTagSet();
        Set<TagEntity> tagEntitySet = new HashSet<>(this.tagRepository.findAllById(tagIdList));

        existingProject.setDescription(projectEntity.getDescription());
        existingProject.setUpdateDate(Instant.now());
        existingProject.setImageIllustrationEntity(imageIllustrationEntity);
        existingProject.setTagEntitySet(tagEntitySet);

        ProjectEntity updatedProjectEntity = this.projectRepository.save(existingProject);

        return this.projectMapper.mapProjectEntityToProjectDto(updatedProjectEntity);
    }
}
