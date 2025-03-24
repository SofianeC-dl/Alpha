package com.alphabackend.service;

import com.alpha.generated.model.*;
import com.alphabackend.UtilsTest.Utils;
import com.alphabackend.exception.ResourceNotFoundException;
import com.alphabackend.model.entity.TagEntity;
import com.alphabackend.repository.TagRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TagServiceTest {

    @InjectMocks
    private TagService tagService;

    @Mock
    private TagRepository tagRepository;
    

    @Test
    void testGetTag() {
        // Data
        Long idTest = 1L;

        TagDto tagDtoCompareTest = Utils.getTagDtoTest();

        TagEntity tagEntityResultTest = Utils.getTagEntityTest();

        // When
        when(this.tagRepository.findById(idTest)).thenReturn(Optional.of(tagEntityResultTest));

        // Test
        TagDto tagDtoResultTest = this.tagService.getTag(idTest);

        // Result
        assertEquals(tagDtoCompareTest, tagDtoResultTest);
    }

    @Test
    void testGetTagFailure() {
        // Data
        Long idTest = 1L;

        // When
        when(this.tagRepository.findById(idTest)).thenReturn(Optional.empty());

        // Test
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> tagService.getTag(idTest));

        // Result
        assertEquals("ResourceNotFoundException: Tag object id 1 : Not found in database|Tag|GET", exception.getMessage());
    }

    @Test
    void testGetAllTagSuccess() {
        // Data

        List<TagDto> tagDtoListCompareTest = List.of(Utils.getTagDtoTest());

        List<TagEntity> tagEntityListResultTest = List.of(Utils.getTagEntityTest());

        // When
        when(this.tagRepository.findAll()).thenReturn(tagEntityListResultTest);

        // Test
        List<TagDto> tagDtoListResultTest = this.tagService.getAllTags();

        // Result
        assertEquals(tagDtoListCompareTest, tagDtoListResultTest);
    }

    @Test
    void testGetAllTagFailure() {
        // Data

        // When
        when(this.tagRepository.findAll()).thenReturn(List.of());

        // Test
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> tagService.getAllTags());

        // Result
        assertEquals("ResourceNotFoundException: No Tag objects found in database|Tag|GET", exception.getMessage());
    }

    @Test
    void testAddTagSuccess() {
        // Data
        TagDto tagDtoEntryTest = Utils.getTagDtoTest();
        TagDto tagDtoCompareTest = Utils.getTagDtoTest();

        TagEntity tagEntityResultTest = Utils.getTagEntityTest();
        // When
        when(this.tagRepository.save(any(TagEntity.class))).thenReturn(tagEntityResultTest);

        // Test

        TagDto tagDtoResultTest = this.tagService.addTag(tagDtoEntryTest);

        // Result
        assertEquals(tagDtoCompareTest, tagDtoResultTest);
    }

    @Test
    void testAddManyTagSuccess() {
        // Data
        TagDto tagDtoEntryTest = Utils.getTagDtoTest();

        List<TagDto> tagDtoListEntryTest = List.of(tagDtoEntryTest);

        TagDto tagDtoCompareTest = Utils.getTagDtoTest();

        List<TagDto> tagDtoListCompareTest = List.of(tagDtoCompareTest);

        TagEntity tagEntityResultTest = Utils.getTagEntityTest();

        List<TagEntity> tagEntryListEntryTest = List.of(tagEntityResultTest);
        // When
        when(this.tagRepository.saveAll(any())).thenReturn(tagEntryListEntryTest);

        // Test
        List<TagDto> tagDtoListResultTest = this.tagService.addManyTag(tagDtoListEntryTest);

        // Result
        assertEquals(tagDtoListCompareTest, tagDtoListResultTest);
    }

    @Test
    void testDeleteTagSuccess() {
        // Data
        Long idTest = 1L;

        ResultDto resultDtoCompareTest = new ResultDto();
        resultDtoCompareTest.setResult(ResultEnum.VALIDATE);

        TagEntity tagEntityResultTest = Utils.getTagEntityTest();

        // When
        when(this.tagRepository.findById(idTest)).thenReturn(Optional.of(tagEntityResultTest));
        doNothing().when(this.tagRepository).delete(tagEntityResultTest);
        when(this.tagRepository.existsById(idTest)).thenReturn(Boolean.FALSE);

        // Test
        ResultDto resultDtoResultTest = this.tagService.deleteTag(idTest);

        // Result
        assertEquals(resultDtoCompareTest, resultDtoResultTest);
    }

    @Test
    void testDeleteTagFailure() {
        // Data
        Long idTest = 1L;

        // When
        when(this.tagRepository.findById(idTest)).thenReturn(Optional.empty());

        // Test
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> this.tagService.deleteTag(idTest));

        // Result
        assertEquals("ResourceNotFoundException: Tag object id 1 : Not found, could not be deleted|Tag|DELETE", exception.getMessage());
    }

    @Test
    void testUpdateTagSuccess() {
        // Data
        Long idTest = 1L;

        TagDto tagDtoEntryTest = Utils.getTagDtoTest();
        TagDto tagDtoCompareTest = Utils.getTagDtoTest();

        TagEntity tagEntityResultTest = Utils.getTagEntityTest();

        // When
        when(this.tagRepository.save(any(TagEntity.class))).thenReturn(tagEntityResultTest);
        when(this.tagRepository.findById(idTest)).thenReturn(Optional.of(tagEntityResultTest));

        // Test

        TagDto tagDtoResultTest = this.tagService.updateTag(idTest, tagDtoEntryTest);

        // Result
        assertEquals(tagDtoCompareTest, tagDtoResultTest);
    }

    @Test
    void testUpdateTagFailure() {
        // Data
        Long idTest = 1L;

        TagDto tagDtoEntryTest = Utils.getTagDtoTest();

        // When
        when(this.tagRepository.findById(idTest)).thenReturn(Optional.empty());

        // Test
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> this.tagService.updateTag(idTest, tagDtoEntryTest));

        // Result
        assertEquals("ResourceNotFoundException: Tag object id 1 : Not found, could not be update|Tag|PUT", exception.getMessage());
    }
}
