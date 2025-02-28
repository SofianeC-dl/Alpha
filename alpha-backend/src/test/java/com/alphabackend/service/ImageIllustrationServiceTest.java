package com.alphabackend.service;

import com.alpha.generated.model.*;
import com.alphabackend.UtilsTest.Utils;
import com.alphabackend.exception.ResourceNotFoundException;
import com.alphabackend.mapper.*;
import com.alphabackend.model.entity.ImageIllustrationEntity;
import com.alphabackend.model.entity.ProjectEntity;
import com.alphabackend.repository.ImageIllustrationRepository;
import com.alphabackend.repository.ProjectRepository;
import com.alphabackend.repository.TagRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImageIllustrationServiceTest {

    @InjectMocks
    private ImageIllustrationService imageIllustrationService;

    @Mock
    private ImageIllustrationRepository imageIllustrationRepository;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private TagRepository           tagRepository;

    @Spy
    private ImageIllustrationMapper imageIllustrationMapper = new ImageIllustrationMapperImpl();

    @Spy
    private TagMapper tagMapper = new TagMapperImpl();

    @Spy
    private ProjectMapper projectMapper = new ProjectMapperImpl();

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(imageIllustrationMapper, "tagMapper", tagMapper);
        ReflectionTestUtils.setField(imageIllustrationMapper, "projectMapper", projectMapper);
    }

    @Test
    void testGetImageIllustration() {
        // Data
        Long idTest = 1L;

        ImageIllustrationDto imageIllustrationDtoCompareTest = Utils.getImageIllustrationDtoTest();

        ImageIllustrationEntity imageIllustrationEntityResultTest = Utils.getImageIllustrationEntityTest();

        // When
        when(this.imageIllustrationRepository.findById(idTest)).thenReturn(Optional.of(imageIllustrationEntityResultTest));

        // Test
        ImageIllustrationDto imageIllustrationDtoResultTest = this.imageIllustrationService.getImageIllustration(idTest);

        // Result
        assertEquals(imageIllustrationDtoCompareTest, imageIllustrationDtoResultTest);
    }

    @Test
    void testGetImageIllustrationFailure() {
        // Data
        Long idTest = 1L;

        // When
        when(this.imageIllustrationRepository.findById(idTest)).thenReturn(Optional.empty());

        // Test
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            imageIllustrationService.getImageIllustration(idTest);
        });

        // Result
        assertEquals("ResourceNotFoundException: Image_illustration object id 1 : Not found in database|Image_illustration|GET", exception.getMessage());
    }

    @Test
    void testGetAllImageIllustrationSuccess() {
        // Data

        List<ImageIllustrationDto> imageIllustrationDtoListCompareTest = List.of(Utils.getImageIllustrationDtoTest());

        List<ImageIllustrationEntity> imageIllustrationEntityListResultTest = List.of(Utils.getImageIllustrationEntityTest());

        // When
        when(this.imageIllustrationRepository.findAll()).thenReturn(imageIllustrationEntityListResultTest);

        // Test
        List<ImageIllustrationDto> imageIllustrationDtoListResultTest = this.imageIllustrationService.getAllImageIllustrations();

        // Result
        assertEquals(imageIllustrationDtoListCompareTest, imageIllustrationDtoListResultTest);
    }

    @Test
    void testGetAllImageIllustrationFailure() {
        // Data

        // When
        when(this.imageIllustrationRepository.findAll()).thenReturn(List.of());

        // Test
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            imageIllustrationService.getAllImageIllustrations();
        });

        // Result
        assertEquals("ResourceNotFoundException: No Image_illustration objects found in database|Image_illustration|GET", exception.getMessage());
    }

    @Test
    void testGetImageIllustrationByProjectIdSuccess() {
        // Data
        Long idTest = 1L;

        Long idProjectTest = 1L;

        ImageIllustrationDto imageIllustrationDtoCompareTest = Utils.getImageIllustrationDtoTest();

        ImageIllustrationEntity imageIllustrationEntityResultTest = Utils.getImageIllustrationEntityTest();

        ProjectEntity projectEntityResultTest = Utils.getProjectEntityTest();

        // When
        when(this.projectRepository.findById(idProjectTest)).thenReturn(Optional.of(projectEntityResultTest));
        when(this.imageIllustrationRepository.findById(idTest)).thenReturn(Optional.of(imageIllustrationEntityResultTest));

        // Test
        ImageIllustrationDto imageIllustrationDtoResultTest = this.imageIllustrationService.getImageIllustrationByProjectId(idProjectTest);

        // Result
        assertEquals(imageIllustrationDtoCompareTest, imageIllustrationDtoResultTest);
    }

    @Test
    void testGetImageIllustrationByProjectIdFailureProjectNotFound() {
        // Data
        Long idProjectTest = 1L;

        // When
        when(this.projectRepository.findById(idProjectTest)).thenReturn(Optional.empty());

        // Test
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            imageIllustrationService.getImageIllustrationByProjectId(idProjectTest);
        });

        // Result
        assertEquals("ResourceNotFoundException: Project object id 1 : Not found in database|Project|GET", exception.getMessage());
    }

    @Test
    void testGetImageIllustrationByProjectIdFailureImageIllustrationNotFound() {
        // Data
        Long idTest = 1L;

        Long idProjectTest = 1L;

        ProjectEntity projectEntityResultTest = Utils.getProjectEntityTest();

        // When
        when(this.projectRepository.findById(idProjectTest)).thenReturn(Optional.of(projectEntityResultTest));
        when(this.imageIllustrationRepository.findById(idTest)).thenReturn(Optional.empty());

        // Test
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            imageIllustrationService.getImageIllustrationByProjectId(idProjectTest);
        });

        // Result
        assertEquals("ResourceNotFoundException: Image_illustration object id 1 : Not found in database|Image_illustration|GET", exception.getMessage());
    }

    @Test
    void testAddImageIllustrationSuccess() {
        // Data
        ImageIllustrationDto imageIllustrationDtoEntryTest = Utils.getImageIllustrationDtoTest();
        ImageIllustrationDto imageIllustrationDtoCompareTest = Utils.getImageIllustrationDtoTest();

        ImageIllustrationEntity imageIllustrationEntityResultTest = Utils.getImageIllustrationEntityTest();
        // When
        when(this.imageIllustrationRepository.save(any(ImageIllustrationEntity.class))).thenReturn(imageIllustrationEntityResultTest);

        // Test

        ImageIllustrationDto imageIllustrationDtoResultTest = this.imageIllustrationService.addImageIllustration(imageIllustrationDtoEntryTest);

        // Result
        assertEquals(imageIllustrationDtoCompareTest, imageIllustrationDtoResultTest);
    }

    @Test
    void testDeleteImageIllustrationSuccess() {
        // Data
        Long idTest = 1L;

        ResultDto resultDtoCompareTest = new ResultDto();
        resultDtoCompareTest.setResult(ResultEnum.VALIDATE);

        ImageIllustrationEntity imageIllustrationEntityResultTest = Utils.getImageIllustrationEntityTest();

        // When
        when(this.imageIllustrationRepository.findById(idTest)).thenReturn(Optional.of(imageIllustrationEntityResultTest));
        doNothing().when(this.imageIllustrationRepository).delete(imageIllustrationEntityResultTest);
        when(this.imageIllustrationRepository.existsById(idTest)).thenReturn(Boolean.FALSE);

        // Test
        ResultDto resultDtoResultTest = this.imageIllustrationService.deleteImageIllustration(idTest);

        // Result
        assertEquals(resultDtoCompareTest, resultDtoResultTest);
    }

    @Test
    void testDeleteImageIllustrationFailure() {
        // Data
        Long idTest = 1L;

        // When
        when(this.imageIllustrationRepository.findById(idTest)).thenReturn(Optional.empty());

        // Test
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            this.imageIllustrationService.deleteImageIllustration(idTest);
        });

        // Result
        assertEquals("ResourceNotFoundException: Image_illustration object id 1 : Not found, could not be deleted|Image_illustration|DELETE", exception.getMessage());
    }

    @Test
    void testUpdateImageIllustrationSuccess() {
        // Data
        Long idTest = 1L;

        ImageIllustrationDto imageIllustrationDtoEntryTest = Utils.getImageIllustrationDtoTest();
        ImageIllustrationDto imageIllustrationDtoCompareTest = Utils.getImageIllustrationDtoTest();

        ImageIllustrationEntity imageIllustrationEntityResultTest = Utils.getImageIllustrationEntityTest();

        // When
        when(this.imageIllustrationRepository.save(any(ImageIllustrationEntity.class))).thenReturn(imageIllustrationEntityResultTest);
        when(this.imageIllustrationRepository.findById(idTest)).thenReturn(Optional.of(imageIllustrationEntityResultTest));
        when(this.tagRepository.findAllById(imageIllustrationDtoEntryTest.getTagSet())).thenReturn(List.of(
                Utils.getTagEntityTest()));

        Instant fixedInstant = Utils.getInstantTest();

        try (MockedStatic<Instant> mockedInstant = mockStatic(Instant.class)) {
            mockedInstant.when(Instant::now).thenReturn(fixedInstant);

            // Test
            ImageIllustrationDto imageIllustrationDtoResultTest = this.imageIllustrationService.updateImageIllustration(idTest, imageIllustrationDtoEntryTest);

            // Result
            assertEquals(imageIllustrationDtoCompareTest, imageIllustrationDtoResultTest);
        }
    }

    @Test
    void testUpdateImageIllustrationFailure() {
        // Data
        Long idTest = 1L;

        ImageIllustrationDto imageIllustrationDtoEntryTest = Utils.getImageIllustrationDtoTest();

        // When
        when(this.imageIllustrationRepository.findById(idTest)).thenReturn(Optional.empty());

        // Test
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            this.imageIllustrationService.updateImageIllustration(idTest, imageIllustrationDtoEntryTest);
        });

        // Result
        assertEquals("ResourceNotFoundException: Image_illustration object id 1 : Not found, could not be update|Image_illustration|PUT", exception.getMessage());
    }
}
