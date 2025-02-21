package com.alphabackend.service;

import com.alpha.generated.model.ImageIllustrationDto;
import com.alpha.generated.model.ResultDto;
import com.alpha.generated.model.ResultEnum;
import com.alphabackend.exception.ResourceNotFoundException;
import com.alphabackend.mapper.ImageIllustrationMapper;
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

@Data
@Builder
@Service
public class ImageIllustrationService {

    private final ImageIllustrationRepository   imageIllustrationRepository;
    private final ProjectRepository             projectRepository;

    private final ImageIllustrationMapper       imageIllustrationMapper;

    /**
     * Renvoie l'objet "ImageIllustration" avec son ID
     * @param id id de l'objet "ImageIllustration"
     * @return Les données de l'objet "ImageIllustration"
     */
    public ImageIllustrationDto getImageIllustration(Long id) {
        return this.imageIllustrationMapper.mapImageIllustrationEntityToImageIllustrationDto(imageIllustrationRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(ErrorText.OBJECT_NOT_FOUND, NameObject.IMAGE_ILLUSTRATION_MAJ.getName(), id)));
    }

    /**
     * Retourne tous les objets "ImageIllustration" présente dans la table "ImageIllustration"
     * @return La liste des objets "ImageIllustration" présents dans la table
     */
    public List<ImageIllustrationDto> getAllImageIllustrations() {
        List<ImageIllustrationEntity> projectEntityList = this.imageIllustrationRepository.findAll();

        projectEntityList.stream().findFirst().orElseThrow(() -> new ResourceNotFoundException(ErrorText.ALL_OBJECTS_NOT_FOUND, NameObject.IMAGE_ILLUSTRATION_MAJ.getName()));

        return this.imageIllustrationMapper.mapImageIllustrationEntityListToImageIllustrationDtoList(projectEntityList);
    }

    /**
     * Retourne tous les objets "ImageIllustration" présente dans la table "ImageIllustration" qui ont pour pour
     * @param projectId ID de l'objet "Project" dont l'objet "Image_Illustration" est recherché
     * @return Objet "Image_Illustration" recherché
     */
    public ImageIllustrationDto getImageIllustrationByProjectId(Long projectId) {
        ProjectEntity projectEntity = this.projectRepository.findById(projectId).orElseThrow(() -> new ResourceNotFoundException(ErrorText.OBJECT_NOT_FOUND, NameObject.PROJECT_MAJ, projectId));

        Long idImageIllustration = projectEntity.getImageIllustrationEntity().getId();
        ImageIllustrationEntity imageIllustrationEntity = this.imageIllustrationRepository.findById(idImageIllustration).orElseThrow(() -> new ResourceNotFoundException(ErrorText.OBJECT_NOT_FOUND, NameObject.IMAGE_ILLUSTRATION_MAJ, idImageIllustration));

        return this.imageIllustrationMapper.mapImageIllustrationEntityToImageIllustrationDto(imageIllustrationEntity);
    }

    /**
     * Ajoute un objet "ImageIllustration" dans la table "ImageIllustration"
     * @param projectDto Objet "ImageIllustration" à ajouter dans la table "ImageIllustration"
     * @return Le nouvel objet "ImageIllustration" avec son ID
     */
    public ImageIllustrationDto addImageIllustration(ImageIllustrationDto projectDto) {
        ImageIllustrationEntity projectEntity = this.imageIllustrationMapper.mapImageIllustrationDtoToImageIllustrationEntity(projectDto);

        return this.imageIllustrationMapper.mapImageIllustrationEntityToImageIllustrationDto(this.imageIllustrationRepository.save(projectEntity));
    }

    /**
     * Supprime l'objet "ImageIllustration" de la table "ImageIllustration"
     * @param id ID de l'object" ImageIllustration" à supprimer
     * @return "Validate" si l'objet "ImageIllustration" à bien été supprimé, sinon "Invalidate" si non supprimé
     */
    public ResultDto deleteImageIllustration(Long id) {
        this.imageIllustrationRepository.findById(id).ifPresentOrElse(
                this.imageIllustrationRepository::delete,
                () -> {
                    throw new ResourceNotFoundException(ErrorText.OBJECT_NONEXISTENT_DELETE, NameObject.IMAGE_ILLUSTRATION_MAJ.getName(), id);
                });

        ResultDto resultDto = new ResultDto();

        resultDto.setResult(this.imageIllustrationRepository.existsById(id) ? ResultEnum.VALIDATE : ResultEnum.INVALIDATE);

        return resultDto;
    }

    /**
     * Mise à jour de l'objet "ImageIllustration" dans la table "ImageIllustration"
     * @param idImageIllustration ID de l'objet "ImageIllustration" à modifier
     * @param projectDto Nouvelles données de l'objet "ImageIllustration"
     * @return Le nouvel objet "ImageIllustration"
     */
    public ImageIllustrationDto updateImageIllustration(Long idImageIllustration, ImageIllustrationDto projectDto) {
        ImageIllustrationEntity projectEntity = this.imageIllustrationMapper.mapImageIllustrationDtoToImageIllustrationEntity(projectDto);

        if (!this.imageIllustrationRepository.existsById(idImageIllustration)) {
            throw new ResourceNotFoundException(ErrorText.OBJECT_NONEXISTENT_UPDATE, NameObject.IMAGE_ILLUSTRATION_MAJ.getName(), projectDto.getId());
        }

        ImageIllustrationEntity updatedImageIllustrationEntity = this.imageIllustrationRepository.save(projectEntity);

        return this.imageIllustrationMapper.mapImageIllustrationEntityToImageIllustrationDto(updatedImageIllustrationEntity);
    }
}
