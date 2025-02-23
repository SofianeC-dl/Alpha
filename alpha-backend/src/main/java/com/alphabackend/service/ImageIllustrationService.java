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

import java.time.Instant;
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
        List<ImageIllustrationEntity> imageIllustrationEntityList = this.imageIllustrationRepository.findAll();

        imageIllustrationEntityList.stream().findFirst().orElseThrow(() -> new ResourceNotFoundException(ErrorText.ALL_OBJECTS_NOT_FOUND, NameObject.IMAGE_ILLUSTRATION_MAJ.getName()));

        return this.imageIllustrationMapper.mapImageIllustrationEntityListToImageIllustrationDtoList(imageIllustrationEntityList);
    }

    /**
     * Retourne tous les objets "ImageIllustration" présente dans la table "ImageIllustration" qui ont pour pour
     * @param imageIllustrationDto ID de l'objet "Project" dont l'objet "Image_Illustration" est recherché
     * @return Objet "Image_Illustration" recherché
     */
    public ImageIllustrationDto getImageIllustrationByProjectId(Long imageIllustrationDto) {
        ProjectEntity imageIllustrationEntity = this.projectRepository.findById(imageIllustrationDto).orElseThrow(() -> new ResourceNotFoundException(ErrorText.OBJECT_NOT_FOUND, NameObject.PROJECT_MAJ, imageIllustrationDto));

        Long idImageIllustration = imageIllustrationEntity.getImageIllustrationEntity().getId();
        ImageIllustrationEntity imageIllustrationEntityResult = this.imageIllustrationRepository.findById(idImageIllustration).orElseThrow(() -> new ResourceNotFoundException(ErrorText.OBJECT_NOT_FOUND, NameObject.IMAGE_ILLUSTRATION_MAJ, idImageIllustration));

        return this.imageIllustrationMapper.mapImageIllustrationEntityToImageIllustrationDto(imageIllustrationEntityResult);
    }

    /**
     * Ajoute un objet "ImageIllustration" dans la table "ImageIllustration"
     * @param imageIllustrationDto Objet "ImageIllustration" à ajouter dans la table "ImageIllustration"
     * @return Le nouvel objet "ImageIllustration" avec son ID
     */
    public ImageIllustrationDto addImageIllustration(ImageIllustrationDto imageIllustrationDto) {
        ImageIllustrationEntity imageIllustrationEntity = this.imageIllustrationMapper.mapImageIllustrationDtoToImageIllustrationEntity(imageIllustrationDto);

        imageIllustrationEntity.setUploadDate(Instant.now());

        imageIllustrationEntity.setUploadDate(Instant.now());
        return this.imageIllustrationMapper.mapImageIllustrationEntityToImageIllustrationDto(this.imageIllustrationRepository.save(imageIllustrationEntity));
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

        resultDto.setResult(!this.imageIllustrationRepository.existsById(id) ? ResultEnum.VALIDATE : ResultEnum.INVALIDATE);

        return resultDto;
    }

    /**
     * Mise à jour de l'objet "ImageIllustration" dans la table "ImageIllustration"
     * @param idImageIllustration ID de l'objet "ImageIllustration" à modifier
     * @param imageIllustrationDto Nouvelles données de l'objet "ImageIllustration"
     * @return Le nouvel objet "ImageIllustration"
     */
    public ImageIllustrationDto updateImageIllustration(Long idImageIllustration, ImageIllustrationDto imageIllustrationDto) {
        ImageIllustrationEntity imageIllustrationEntity = this.imageIllustrationMapper.mapImageIllustrationDtoToImageIllustrationEntity(imageIllustrationDto);

        if (!this.imageIllustrationRepository.existsById(idImageIllustration)) {
            throw new ResourceNotFoundException(ErrorText.OBJECT_NONEXISTENT_UPDATE, NameObject.IMAGE_ILLUSTRATION_MAJ.getName(), imageIllustrationDto.getId());
        }

        ImageIllustrationEntity updatedImageIllustrationEntity = this.imageIllustrationRepository.save(imageIllustrationEntity);

        return this.imageIllustrationMapper.mapImageIllustrationEntityToImageIllustrationDto(updatedImageIllustrationEntity);
    }
}
