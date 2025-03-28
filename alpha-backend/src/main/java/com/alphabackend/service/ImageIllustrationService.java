package com.alphabackend.service;

import com.alpha.generated.model.ImageIllustrationDto;
import com.alpha.generated.model.ResultDto;
import com.alpha.generated.model.ResultEnum;
import com.alphabackend.exception.ResourceNotFoundException;
import com.alphabackend.mapper.ImageIllustrationMapper;
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
import java.util.Set;

@Data
@Builder
@Service
public class ImageIllustrationService {

    private final ImageIllustrationRepository   imageIllustrationRepository;
    private final ProjectRepository             projectRepository;

    private final TagRepository                 tagRepository;

    private final ImageIllustrationMapper       imageIllustrationMapper;

    /**
     * Renvoie l'objet "ImageIllustration" avec son ID
     * @param id id de l'objet "ImageIllustration"
     * @return Les données de l'objet "ImageIllustration"
     */
    public ImageIllustrationDto getImageIllustration(Long id) {

        return this.imageIllustrationMapper.mapImageIllustrationEntityToImageIllustrationDto(imageIllustrationRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        ParamsError.builder()
                        .errorText(ErrorTextEnum.OBJECT_NOT_FOUND)
                        .labelObject(NameObject.IMAGE_ILLUSTRATION_MAJ)
                        .typeRequestHttp(TypeRequestHttpEnum.GET_REQUEST)
                        .arg(id)
                        .build()
                )));
    }

    /**
     * Retourne tous les objets "ImageIllustration" présente dans la table "ImageIllustration"
     * @return La liste des objets "ImageIllustration" présents dans la table
     */
    public List<ImageIllustrationDto> getAllImageIllustrations() {
        List<ImageIllustrationEntity> imageIllustrationEntityList = this.imageIllustrationRepository.findAll();

        imageIllustrationEntityList.stream().findFirst().orElseThrow(() -> new ResourceNotFoundException(
                ParamsError.builder()
                        .errorText(ErrorTextEnum.ALL_OBJECTS_NOT_FOUND)
                        .labelObject(NameObject.IMAGE_ILLUSTRATION_MAJ)
                        .typeRequestHttp(TypeRequestHttpEnum.GET_REQUEST)
                        .build()
               ));

        return this.imageIllustrationMapper.mapImageIllustrationEntityListToImageIllustrationDtoList(imageIllustrationEntityList);
    }

    /**
     * Retourne tous les objets "ImageIllustration" présente dans la table "ImageIllustration" qui ont pour pour
     * @param idProject ID de l'objet "Project" dont l'objet "Image_Illustration" est recherché
     * @return Objet "Image_Illustration" recherché
     */
    public ImageIllustrationDto getImageIllustrationByProjectId(Long idProject) {
        ProjectEntity projectEntity = this.projectRepository.findById(idProject).orElseThrow(() -> new ResourceNotFoundException(
                ParamsError.builder()
                        .errorText(ErrorTextEnum.OBJECT_NOT_FOUND)
                        .labelObject(NameObject.PROJECT_MAJ)
                        .typeRequestHttp(TypeRequestHttpEnum.GET_REQUEST)
                        .arg(idProject)
                        .build()
              ));

        Long idImageIllustration = projectEntity.getImageIllustrationEntity().getId();

        ImageIllustrationEntity imageIllustrationEntityResult = this.imageIllustrationRepository.findById(idImageIllustration).orElseThrow(() -> new ResourceNotFoundException(
                ParamsError.builder()
                        .errorText(ErrorTextEnum.OBJECT_NOT_FOUND)
                        .labelObject(NameObject.IMAGE_ILLUSTRATION_MAJ)
                        .typeRequestHttp(TypeRequestHttpEnum.GET_REQUEST)
                        .arg(idImageIllustration)
                        .build()
               ));

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
                    throw new ResourceNotFoundException(
                            ParamsError.builder()
                                    .errorText(ErrorTextEnum.OBJECT_NONEXISTENT_DELETE)
                                    .labelObject(NameObject.IMAGE_ILLUSTRATION_MAJ)
                                    .typeRequestHttp(TypeRequestHttpEnum.DELETE_REQUEST)
                                    .arg(id)
                                    .build()
                           );
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

        ImageIllustrationEntity existingImageIllustration = this.imageIllustrationRepository.findById(idImageIllustration)
                .orElseThrow(() -> new ResourceNotFoundException(
                        ParamsError.builder()
                                .errorText(ErrorTextEnum.OBJECT_NONEXISTENT_UPDATE)
                                .labelObject(NameObject.IMAGE_ILLUSTRATION_MAJ)
                                .typeRequestHttp(TypeRequestHttpEnum.PUT_REQUEST)
                                .arg(imageIllustrationDto.getId())
                                .build()
                ));

        List<Long> tagIdList = imageIllustrationDto.getTagSet();
        Set<TagEntity> tagEntitySet = new HashSet<>(this.tagRepository.findAllById(tagIdList));

        existingImageIllustration.setDatas(imageIllustrationEntity.getDatas());
        existingImageIllustration.setTagEntitySet(tagEntitySet);

        ImageIllustrationEntity updatedImageIllustrationEntity = this.imageIllustrationRepository.save(existingImageIllustration);

        return this.imageIllustrationMapper.mapImageIllustrationEntityToImageIllustrationDto(updatedImageIllustrationEntity);
    }
}
