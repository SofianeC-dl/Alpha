package com.alphabackend.service;

import com.alpha.generated.model.*;
import com.alphabackend.exception.ResourceNotFoundException;
import com.alphabackend.mapper.TagMapper;
import com.alphabackend.model.entity.ParamsError;
import com.alphabackend.model.entity.TagEntity;
import com.alphabackend.model.enum_model.ErrorTextEnum;
import com.alphabackend.model.enum_model.NameObject;
import com.alphabackend.model.enum_model.TypeRequestHttpEnum;
import com.alphabackend.repository.TagRepository;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Builder
@Service
public class TagService {

    private final TagRepository tagRepository;

    private final TagMapper tagMapper;

    /**
     * Renvoie l'objet "Tag" avec son ID
     * @param id id de l'objet "Tag"
     * @return Les données de l'objet "Tag"
     */
    public TagDto getTag(Long id) {
        return this.tagMapper.mapTagEntityToTagDto(tagRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        ParamsError.builder()
                                .errorText(ErrorTextEnum.OBJECT_NOT_FOUND)
                                .labelObject(NameObject.TAG_MAJ)
                                .typeRequestHttp(TypeRequestHttpEnum.GET_REQUEST)
                                .arg(id)
                                .build()
                        )));
    }

    /**
     * Retourne tous les objets "Tag" présente dans la table "Tag"
     * @return La liste des objets "Tag" présents dans la table
     */
    public List<TagDto> getAllTags() {
        List<TagEntity> projectEntityList = tagRepository.findAll();

        projectEntityList.stream().findFirst().orElseThrow(() -> new ResourceNotFoundException(
                ParamsError.builder()
                        .errorText(ErrorTextEnum.ALL_OBJECTS_NOT_FOUND)
                        .labelObject(NameObject.TAG_MAJ)
                        .typeRequestHttp(TypeRequestHttpEnum.GET_REQUEST)
                        .build()
                ));

        return this.tagMapper.mapTagEntityListToTagDtoList(projectEntityList);
    }

    /**
     * Ajoute un objet "Tag" dans la table "Tag"
     * @param tagDto Objet "Tag" à ajouter dans la table "Tag"
     * @return Le nouvel objet "Tag" avec son ID
     */
    public TagDto addTag(TagDto tagDto) {
        TagEntity projectEntity = this.tagMapper.mapTagDtoToTagEntity(tagDto);

        return this.tagMapper.mapTagEntityToTagDto(this.tagRepository.save(projectEntity));
    }

    /**
     * Ajoute un objet "Tag" dans la table "Tag"
     * @param tagDtoList List objets "Tag" à ajouter dans la table "Tag"
     * @return La liste de nouveaux objets "Tag" avec son ID
     */
    public List<TagDto> addManyTag(List<TagDto> tagDtoList) {
        List<TagEntity> tagEntityList = this.tagMapper.mapTagDtoListToTagEntityList(tagDtoList);

        return this.tagMapper.mapTagEntityListToTagDtoList(this.tagRepository.saveAll(tagEntityList));
    }

    /**
     * Supprime l'objet "Tag" de la table "Tag"
     * @param id ID de l'object" Tag" à supprimer
     * @return "Validate" si l'objet "Tag" à bien été supprimé, sinon "Invalidate" si non supprimé
     */
    public ResultDto deleteTag(Long id) {
        this.tagRepository.findById(id).ifPresentOrElse(
                this.tagRepository::delete,
                () -> {
                    throw new ResourceNotFoundException(
                            ParamsError.builder()
                                    .errorText(ErrorTextEnum.OBJECT_NONEXISTENT_DELETE)
                                    .labelObject(NameObject.TAG_MAJ)
                                    .typeRequestHttp(TypeRequestHttpEnum.DELETE_REQUEST)
                                    .arg(id)
                                    .build()
                            );
                });

        ResultDto resultDto = new ResultDto();

        resultDto.setResult(!this.tagRepository.existsById(id) ? ResultEnum.VALIDATE : ResultEnum.INVALIDATE);

        return resultDto;
    }

    /**
     * Mise à jour de l'objet "Tag" dans la table "Tag"
     * @param idTag ID de l'objet "Tag" à modifier
     * @param tagDto Nouvelles données de l'objet "Tag"
     * @return Le nouvel objet "Tag"
     */
    public TagDto updateTag(Long idTag, TagDto tagDto) {
        TagEntity tagEntity = this.tagMapper.mapTagDtoToTagEntity(tagDto);

        TagEntity existingTag = this.tagRepository.findById(idTag)
                .orElseThrow(() -> new ResourceNotFoundException(
                        ParamsError.builder()
                                .errorText(ErrorTextEnum.OBJECT_NONEXISTENT_UPDATE)
                                .labelObject(NameObject.TAG_MAJ)
                                .typeRequestHttp(TypeRequestHttpEnum.PUT_REQUEST)
                                .arg(tagDto.getId())
                                .build()
                ));

        existingTag.setLabel(tagEntity.getLabel());
        existingTag.setColor(tagEntity.getColor());

        TagEntity updatedTagEntity = this.tagRepository.save(existingTag);

        return this.tagMapper.mapTagEntityToTagDto(updatedTagEntity);
    }
}
