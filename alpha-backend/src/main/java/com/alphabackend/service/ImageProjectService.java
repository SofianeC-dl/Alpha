package com.alphabackend.service;

import com.alpha.generated.model.ImageProjectDto;
import com.alpha.generated.model.ResultDto;
import com.alpha.generated.model.ResultEnum;
import com.alphabackend.exception.ResourceNotFoundException;
import com.alphabackend.mapper.ImageProjectMapper;
import com.alphabackend.model.entity.*;
import com.alphabackend.model.enum_model.ErrorTextEnum;
import com.alphabackend.model.enum_model.NameObject;
import com.alphabackend.model.enum_model.TypeRequestHttpEnum;
import com.alphabackend.repository.ImageProjectRepository;
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
public class ImageProjectService {

    private final ImageProjectRepository    imageProjectRepository;
    private final ProjectRepository         projectRepository;

    private final TagRepository             tagRepository;

    private final ImageProjectMapper        imageProjectMapper;

    /**
     * Renvoie l'objet "ImageProject" avec son ID
     * @param id id de l'objet "ImageProject"
     * @return Les données de l'objet "ImageProject"
     */
    public ImageProjectDto getImageProject(Long id) {
        return this.imageProjectMapper.mapImageProjectEntityToImageProjectDto(imageProjectRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        ParamsError.builder()
                                .errorText(ErrorTextEnum.OBJECT_NOT_FOUND)
                                .labelObject(NameObject.IMAGE_PROJECT_MAJ)
                                .typeRequestHttp(TypeRequestHttpEnum.GET_REQUEST)
                                .arg(id)
                                .build()
                        )));
    }

    /**
     * Retourne tous les objets "ImageProject" présente dans la table "ImageProject"
     * @return La liste des objets "ImageProject" présents dans la table
     */
    public List<ImageProjectDto> getAllImageProjects() {
        List<ImageProjectEntity> imageProjectEntityList = this.imageProjectRepository.findAll();

        imageProjectEntityList.stream().findFirst().orElseThrow(() -> new ResourceNotFoundException(
                ParamsError.builder()
                        .errorText(ErrorTextEnum.ALL_OBJECTS_NOT_FOUND)
                        .labelObject(NameObject.IMAGE_PROJECT_MAJ)
                        .typeRequestHttp(TypeRequestHttpEnum.GET_REQUEST)
                        .build()
                ));

        return this.imageProjectMapper.mapImageProjectEntityListToImageProjectDtoList(imageProjectEntityList);
    }

    /**
     * Retourne tous les objets "ImageProject" présente dans la table "ImageProject" qui ont pour pour
     * @param projectId ID de l'objet "Project" dont l'objet "Image_Project" est recherché
     * @return Objet "Image_Project" recherché
     */
    public List<ImageProjectDto> getAllImageProjectByProjectId(Long projectId) {
        List<ImageProjectEntity> imageProjectEntityList = this.imageProjectRepository.findByProjectEntity_Id(projectId);

        if (imageProjectEntityList.isEmpty()) {
            throw new ResourceNotFoundException(
                    ParamsError.builder()
                            .errorText(ErrorTextEnum.ALL_OBJECTS_NOT_FOUND)
                            .labelObject(NameObject.IMAGE_PROJECT_MAJ)
                            .typeRequestHttp(TypeRequestHttpEnum.GET_REQUEST)
                            .arg(NameObject.IMAGE_PROJECT_MAJ.getName())
                            .build()
                   );
        }

        return this.imageProjectMapper.mapImageProjectEntityListToImageProjectDtoList(imageProjectEntityList);
    }

    /**
     * Ajoute un objet "ImageProject" dans la table "ImageProject"
     * @param imageProjectDto Objet "ImageProject" à ajouter dans la table "ImageProject"
     * @return Le nouvel objet "ImageProject" avec son ID
     */
    public ImageProjectDto addImageProject(ImageProjectDto imageProjectDto) {
        ImageProjectEntity imageProjectEntity = this.imageProjectMapper.mapImageProjectDtoToImageProjectEntity(imageProjectDto);
        imageProjectEntity.setUploadDate(Instant.now());

        if(imageProjectEntity.getProjectEntity() != null && imageProjectEntity.getProjectEntity().getId() != null) {
            Optional<ProjectEntity> optImage = this.projectRepository.findById(imageProjectEntity.getProjectEntity().getId());
            optImage.ifPresentOrElse(imageProjectEntity::setProjectEntity, () -> {
                throw new ResourceNotFoundException(
                        ParamsError.builder()
                                .errorText(ErrorTextEnum.OBJECT_NOT_FOUND)
                                .labelObject(NameObject.PROJECT_MAJ)
                                .typeRequestHttp(TypeRequestHttpEnum.GET_REQUEST)
                                .arg(imageProjectEntity.getProjectEntity().getId())
                                .build()
                );
            });
        }

        return this.imageProjectMapper.mapImageProjectEntityToImageProjectDto(this.imageProjectRepository.save(imageProjectEntity));
    }

    /**
     * Ajoute un objet "ImageProject" dans la table "ImageProject"
     * @param imageProjectDtoList List objets "ImageProject" à ajouter dans la table "ImageProject"
     * @return La liste de nouveaux objets "ImageProject" avec son ID
     */
    public List<ImageProjectDto> addManyImageProject(List<ImageProjectDto> imageProjectDtoList) {
        List<ImageProjectEntity> imageProjectEntityList = this.imageProjectMapper.mapImageProjectDtoListToImageProjectEntityList(imageProjectDtoList);

        Instant now = Instant.now();
        imageProjectEntityList.forEach(imageProjectEntity -> imageProjectEntity.setUploadDate(now));

        return this.imageProjectMapper.mapImageProjectEntityListToImageProjectDtoList(this.imageProjectRepository.saveAll(imageProjectEntityList));
    }

    /**
     * Supprime l'objet "ImageProject" de la table "ImageProject"
     * @param id ID de l'object" ImageProject" à supprimer
     * @return "Validate" si l'objet "ImageProject" à bien été supprimé, sinon "Invalidate" si non supprimé
     */
    public ResultDto deleteImageProject(Long id) {
        this.imageProjectRepository.findById(id).ifPresentOrElse(
                this.imageProjectRepository::delete,
                () -> {
                    throw new ResourceNotFoundException(
                            ParamsError.builder()
                                    .errorText(ErrorTextEnum.OBJECT_NONEXISTENT_DELETE)
                                    .labelObject(NameObject.IMAGE_PROJECT_MAJ)
                                    .typeRequestHttp(TypeRequestHttpEnum.DELETE_REQUEST)
                                    .arg(id)
                                    .build()
                            );
                });

        ResultDto resultDto = new ResultDto();

        resultDto.setResult(!this.imageProjectRepository.existsById(id) ? ResultEnum.VALIDATE : ResultEnum.INVALIDATE);

        return resultDto;
    }

    /**
     * Mise à jour de l'objet "ImageProject" dans la table "ImageProject"
     * @param idImageProject ID de l'objet "ImageProject" à modifier
     * @param imageProjectDto Nouvelles données de l'objet "ImageProject"
     * @return Le nouvel objet "ImageProject"
     */
    public ImageProjectDto updateImageProject(Long idImageProject, ImageProjectDto imageProjectDto) {
        ImageProjectEntity imageProjectEntity = this.imageProjectMapper.mapImageProjectDtoToImageProjectEntity(imageProjectDto);

        ProjectEntity projectEntity = this.projectRepository.findById(imageProjectEntity.getProjectEntity().getId()).orElseThrow(() -> new ResourceNotFoundException(
                ParamsError.builder()
                        .errorText(ErrorTextEnum.OBJECT_NONEXISTENT_UPDATE)
                        .labelObject(NameObject.PROJECT_MAJ)
                        .typeRequestHttp(TypeRequestHttpEnum.PUT_REQUEST)
                        .arg(imageProjectEntity.getProjectEntity().getId())
                        .build()
        ));

        ImageProjectEntity existingImageProject = this.imageProjectRepository.findById(idImageProject)
                .orElseThrow(() -> new ResourceNotFoundException(
                        ParamsError.builder()
                                .errorText(ErrorTextEnum.OBJECT_NONEXISTENT_UPDATE)
                                .labelObject(NameObject.IMAGE_PROJECT_MAJ)
                                .typeRequestHttp(TypeRequestHttpEnum.PUT_REQUEST)
                                .arg(imageProjectDto.getId())
                                .build()
                ));

        List<Long> tagIdList = imageProjectDto.getTagSet();
        Set<TagEntity> tagEntitySet = new HashSet<>(this.tagRepository.findAllById(tagIdList));

        existingImageProject.setDatas(imageProjectEntity.getDatas());
        existingImageProject.setProjectEntity(imageProjectEntity.getProjectEntity());
        existingImageProject.setProjectEntity(projectEntity);
        existingImageProject.setTagEntitySet(tagEntitySet);

        ImageProjectEntity updatedImageProjectEntity = this.imageProjectRepository.save(existingImageProject);

        return this.imageProjectMapper.mapImageProjectEntityToImageProjectDto(updatedImageProjectEntity);
    }
}
