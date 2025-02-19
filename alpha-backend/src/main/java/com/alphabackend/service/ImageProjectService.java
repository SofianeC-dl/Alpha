package com.alphabackend.service;

import com.alpha.generated.model.ImageProjectDto;
import com.alpha.generated.model.ResultDto;
import com.alpha.generated.model.ResultEnum;
import com.alphabackend.exception.ResourceNotFoundException;
import com.alphabackend.mapper.ImageProjectMapper;
import com.alphabackend.model.ImageProjectEntity;
import com.alphabackend.model.enum_model.ErrorText;
import com.alphabackend.model.enum_model.NameObject;
import com.alphabackend.repository.ImageProjectRepository;
import com.alphabackend.repository.ProjectRepository;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Builder
@Service
public class ImageProjectService {

    private final ImageProjectRepository   imageProjectRepository;
    private final ProjectRepository             projectRepository;

    private final ImageProjectMapper       imageProjectMapper;

    /**
     * Renvoie l'objet "ImageProject" avec son ID
     * @param id id de l'objet "ImageProject"
     * @return Les données de l'objet "ImageProject"
     */
    public ImageProjectDto getImageProject(Long id) {
        return this.imageProjectMapper.mapImageProjectEntityToImageProjectDto(imageProjectRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(ErrorText.OBJECT_NOT_FOUND, NameObject.IMAGE_ILLUSTRATION_MAJ.getName(), id)));
    }

    /**
     * Retourne tous les objets "ImageProject" présente dans la table "ImageProject"
     * @return La liste des objets "ImageProject" présents dans la table
     */
    public List<ImageProjectDto> getAllImageProjects() {
        List<ImageProjectEntity> projectEntityList = this.imageProjectRepository.findAll();

        projectEntityList.stream().findFirst().orElseThrow(() -> new ResourceNotFoundException(ErrorText.ALL_OBJECTS_NOT_FOUND, NameObject.IMAGE_ILLUSTRATION_MAJ.getName()));

        return this.imageProjectMapper.mapImageProjectEntityListToImageProjectDtoList(projectEntityList);
    }

    /**
     * Retourne tous les objets "ImageProject" présente dans la table "ImageProject" qui ont pour pour
     * @param projectId ID de l'objet "Project" dont l'objet "Image_Project" est recherché
     * @return Objet "Image_Project" recherché
     */
    public List<ImageProjectDto> getAllImageProjectByProjectId(Long projectId) {
        List<ImageProjectEntity> imageProjectEntityList = this.imageProjectRepository.findByProjectEntity_Id(projectId);

        if (imageProjectEntityList.isEmpty()) {
            throw new ResourceNotFoundException(ErrorText.ALL_OBJECTS_NOT_FOUND, NameObject.IMAGE_PROJECT_MAJ.getName());
        }

        return this.imageProjectMapper.mapImageProjectEntityListToImageProjectDtoList(imageProjectEntityList);
    }

    /**
     * Ajoute un objet "ImageProject" dans la table "ImageProject"
     * @param projectDto Objet "ImageProject" à ajouter dans la table "ImageProject"
     * @return Le nouvel objet "ImageProject" avec son ID
     */
    public ImageProjectDto addImageProject(ImageProjectDto projectDto) {
        ImageProjectEntity projectEntity = this.imageProjectMapper.mapImageProjectDtoToImageProjectEntity(projectDto);

        return this.imageProjectMapper.mapImageProjectEntityToImageProjectDto(this.imageProjectRepository.save(projectEntity));
    }

    /**
     * Supprime l'objet "ImageProject" de la table "ImageProject"
     * @param id ID de l'object" ImageProject" à supprimer
     * @return "Validate" si l'objet "Projetc" à bien été supprimé, sinon "Invalidate" si non supprimé
     */
    public ResultDto deleteImageProject(Long id) {
        this.imageProjectRepository.findById(id).ifPresentOrElse(
                this.imageProjectRepository::delete,
                () -> {
                    throw new ResourceNotFoundException(ErrorText.OBJECT_NONEXISTENT_DELETE, NameObject.IMAGE_ILLUSTRATION_MAJ.getName(), id);
                });

        ResultDto resultDto = new ResultDto();

        resultDto.setResult(this.imageProjectRepository.existsById(id) ? ResultEnum.VALIDATE : ResultEnum.INVALIDATE);

        return resultDto;
    }

    /**
     * Mise à jour de l'objet "ImageProject" dans la table "ImageProject"
     * @param idImageProject ID de l'objet "ImageProject" à modifier
     * @param projectDto Nouvelles données de l'objet "ImageProject"
     * @return Le nouvel objet "ImageProject"
     */
    public ImageProjectDto updateImageProject(Long idImageProject, ImageProjectDto projectDto) {
        ImageProjectEntity projectEntity = this.imageProjectMapper.mapImageProjectDtoToImageProjectEntity(projectDto);

        if (!this.imageProjectRepository.existsById(idImageProject)) {
            throw new ResourceNotFoundException(ErrorText.OBJECT_NONEXISTENT_UPDATE, NameObject.IMAGE_ILLUSTRATION_MAJ.getName(), projectDto.getId());
        }

        ImageProjectEntity updatedImageProjectEntity = this.imageProjectRepository.save(projectEntity);

        return this.imageProjectMapper.mapImageProjectEntityToImageProjectDto(updatedImageProjectEntity);
    }
}
