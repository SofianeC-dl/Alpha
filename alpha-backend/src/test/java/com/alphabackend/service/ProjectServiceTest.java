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
class ProjectServiceTest {

    @InjectMocks
    private ProjectService projectService;

    @Mock
    private ImageIllustrationService imageIllustrationService;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private ImageIllustrationRepository imageIllustrationRepository;

    @Mock
    private TagRepository tagRepository;

    @Spy
    private ProjectMapper projectMapper = new ProjectMapperImpl();

    @Spy
    private TagMapper tagMapper = new TagMapperImpl();

    @Spy
    private ImageIllustrationMapper imageIllustrationMapper = new ImageIllustrationMapperImpl();

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(projectMapper, "tagMapper", tagMapper);
        ReflectionTestUtils.setField(imageIllustrationMapper, "tagMapper", tagMapper);
        ReflectionTestUtils.setField(imageIllustrationMapper, "projectMapper", projectMapper);
    }

    @Test
    void testGetProjectSuccess() {
        // Data
        Long idTest = 1L;

        ProjectDto projectDtoCompareTest = Utils.getProjectDtoTest();

        ProjectEntity projectEntityResultTest = Utils.getProjectEntityTest();

        // When
        when(this.projectRepository.findById(idTest)).thenReturn(Optional.of(projectEntityResultTest));

        // Test
        ProjectDto projectDtoResultTest = this.projectService.getProject(idTest);

        // Result
        assertEquals(projectDtoCompareTest, projectDtoResultTest);
    }

    @Test
    void testGetProjectFailure() {
        // Data
        Long idTest = 1L;

        // When
        when(this.projectRepository.findById(idTest)).thenReturn(Optional.empty());

        // Test
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            projectService.getProject(idTest);
        });

        // Result
        assertEquals("ResourceNotFoundException: Project object id 1 : Not found in database|Project|GET", exception.getMessage());
    }

    @Test
    void testGetAllProjectSuccess() {
        // Data

        List<ProjectDto> projectDtoListCompareTest = List.of(Utils.getProjectDtoTest());

        List<ProjectEntity> projectEntityListResultTest = List.of(Utils.getProjectEntityTest());

        // When
        when(this.projectRepository.findAll()).thenReturn(projectEntityListResultTest);

        // Test
        List<ProjectDto> projectDtoListResultTest = this.projectService.getAllProjects();

        // Result
        assertEquals(projectDtoListCompareTest, projectDtoListResultTest);
    }

    @Test
    void testGetAllProjectFailure() {
        // Data

        // When
        when(this.projectRepository.findAll()).thenReturn(List.of());

        // Test
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            projectService.getAllProjects();
        });

        // Result
        assertEquals("ResourceNotFoundException: No Project objects found in database|Project|GET", exception.getMessage());
    }

    @Test
    void testAddProjectSuccess() {
        // Data
        Long idImageIllustrationTest = 1L;

        ProjectDto projectDtoEntryTest = Utils.getProjectDtoTest();
        ProjectDto projectDtoCompareTest = Utils.getProjectDtoTest();

        ProjectEntity projectEntityResultTest = Utils.getProjectEntityTest();

        ImageIllustrationEntity imageIllustrationResultTest = Utils.getImageIllustrationEntityTest();

        // When
        when(this.projectRepository.save(any(ProjectEntity.class))).thenReturn(projectEntityResultTest);
        when(this.imageIllustrationRepository.findById(idImageIllustrationTest)).thenReturn(Optional.of(imageIllustrationResultTest));

        // Test

        ProjectDto projectDtoResultTest = this.projectService.addProject(projectDtoEntryTest);

        // Result
        assertEquals(projectDtoCompareTest, projectDtoResultTest);
    }

    @Test
    void testAddProjectFailure() {
        // Data
        Long idImageIllustrationTest = 1L;

        ProjectDto projectDtoEntryTest = Utils.getProjectDtoTest();

        // When
        when(this.imageIllustrationRepository.findById(idImageIllustrationTest)).thenReturn(Optional.empty());

        // Test
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            this.projectService.addProject(projectDtoEntryTest);
        });

        // Result
        assertEquals("ResourceNotFoundException: Image_illustration object id 1 : Not found in database|Image_illustration|GET", exception.getMessage());
    }

    @Test
    void testAddProjectWithImageIllustrationSuccess() {
        // Data
        Long idImageIllustrationTest = 1L;

        ProjectWithImageIllustrationDto projectWithImageIllustrationDtoEntryTest = Utils.getProjectWithImageIllustrationDtoTest();

        ProjectDto projectDtoCompareTest = Utils.getProjectDtoTest();

        ProjectEntity projectEntityResultTest = Utils.getProjectEntityTest();

        ImageIllustrationEntity imageIllustrationEntityTest = Utils.getImageIllustrationEntityTest();

        ImageIllustrationDto imageIllustrationResultDto = Utils.getImageIllustrationDtoTest();

        // When
        when(this.projectRepository.save(any(ProjectEntity.class))).thenReturn(projectEntityResultTest);
        when(this.imageIllustrationService.addImageIllustration(any(ImageIllustrationDto.class))).thenReturn(imageIllustrationResultDto);
        when(this.imageIllustrationRepository.findById(idImageIllustrationTest)).thenReturn(Optional.of(imageIllustrationEntityTest));

        // Test

        ProjectDto projectDtoResultTest = this.projectService.addProjectWithImageIllustration(projectWithImageIllustrationDtoEntryTest);

        // Result
        assertEquals(projectDtoCompareTest, projectDtoResultTest);
    }

    @Test
    void testAddProjectWithImageIllustrationFailure() {
        // Data
        Long idImageIllustrationTest = 1L;

        ProjectWithImageIllustrationDto projectWithImageIllustrationDtoEntryTest = Utils.getProjectWithImageIllustrationDtoTest();

        ImageIllustrationDto imageIllustrationResultDto = Utils.getImageIllustrationDtoTest();

        // When
        when(this.imageIllustrationRepository.findById(idImageIllustrationTest)).thenReturn(Optional.empty());
        when(this.imageIllustrationService.addImageIllustration(any(ImageIllustrationDto.class))).thenReturn(imageIllustrationResultDto);

        // Test
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            this.projectService.addProjectWithImageIllustration(projectWithImageIllustrationDtoEntryTest);
        });

        // Result
        assertEquals("ResourceNotFoundException: Image_illustration object id 1 : Not found in database|Image_illustration|GET", exception.getMessage());
    }

    @Test
    void testDeleteProjectSuccess() {
        // Data
        Long idTest = 1L;

        ResultDto resultDtoCompareTest = new ResultDto();
        resultDtoCompareTest.setResult(ResultEnum.VALIDATE);

        ProjectEntity projectEntityResultTest = Utils.getProjectEntityTest();

        // When
        when(this.projectRepository.findById(idTest)).thenReturn(Optional.of(projectEntityResultTest));
        doNothing().when(this.projectRepository).delete(projectEntityResultTest);
        when(this.projectRepository.existsById(idTest)).thenReturn(Boolean.FALSE);

        // Test
        ResultDto resultDtoResultTest = this.projectService.deleteProject(idTest);

        // Result
        assertEquals(resultDtoCompareTest, resultDtoResultTest);
    }

    @Test
    void testDeleteProjectFailure() {
        // Data
        Long idTest = 1L;

        // When
        when(this.projectRepository.findById(idTest)).thenReturn(Optional.empty());

        // Test
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            this.projectService.deleteProject(idTest);
        });

        // Result
        assertEquals("ResourceNotFoundException: Project object id 1 : Not found, could not be deleted|Project|DELETE", exception.getMessage());
    }

    @Test
    void testUpdateProjectSuccess() {
        // Data
        Long idTest = 1L;

        Long idImageIllustrationTest = 1L;

        ProjectDto projectDtoEntryTest = Utils.getProjectDtoTest();
        ProjectDto projectDtoCompareTest = Utils.getProjectDtoTest();

        ProjectEntity projectEntityResultTest = Utils.getProjectEntityTest();

        ImageIllustrationEntity imageIllustrationEntityTest = Utils.getImageIllustrationEntityTest();
        // When
        when(this.projectRepository.save(any(ProjectEntity.class))).thenReturn(projectEntityResultTest);
        when(this.projectRepository.findById(idTest)).thenReturn(Optional.of(projectEntityResultTest));
        when(this.imageIllustrationRepository.findById(idImageIllustrationTest)).thenReturn(Optional.of(imageIllustrationEntityTest));
        when(this.tagRepository.findAllById(projectDtoEntryTest.getTagSet())).thenReturn(List.of(
                Utils.getTagEntityTest()));

        Instant fixedInstant = Utils.getInstantTest();

        try (MockedStatic<Instant> mockedInstant = mockStatic(Instant.class)) {
            mockedInstant.when(Instant::now).thenReturn(fixedInstant);

            // Test
            ProjectDto projectDtoResultTest = this.projectService.updateProject(idTest, projectDtoEntryTest);

            // Result
            assertEquals(projectDtoCompareTest, projectDtoResultTest);
        }
    }

    @Test
    void testUpdateProjectFailureImageIllustrationNotFound() {
        // Data
        Long idTest = 1L;

        Long idImageIllustrationTest = 1L;

        ProjectDto projectDtoEntryTest = Utils.getProjectDtoTest();

        // When
        when(this.imageIllustrationRepository.findById(idImageIllustrationTest)).thenReturn(Optional.empty());

        // Test
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            this.projectService.updateProject(idTest, projectDtoEntryTest);
        });

        // Result
        assertEquals("ResourceNotFoundException: Image_illustration object id 1 : Not found, could not be update|Image_illustration|PUT", exception.getMessage());
    }

    @Test
    void testUpdateProjectFailureProjectNotFound() {
        // Data
        Long idTest = 1L;

        ProjectDto projectDtoEntryTest = Utils.getProjectDtoTest();

        Long idImageIllustrationTest = 1L;
        ImageIllustrationEntity imageIllustrationEntityTest = Utils.getImageIllustrationEntityTest();

        // When
        when(this.imageIllustrationRepository.findById(idImageIllustrationTest)).thenReturn(Optional.of(imageIllustrationEntityTest));
        when(this.projectRepository.findById(idTest)).thenReturn(Optional.empty());

        // Test
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            this.projectService.updateProject(idTest, projectDtoEntryTest);
        });

        // Result
        assertEquals("ResourceNotFoundException: Project object id 1 : Not found, could not be update|Project|PUT", exception.getMessage());
    }
}
