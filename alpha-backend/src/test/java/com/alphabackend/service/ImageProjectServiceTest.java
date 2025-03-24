package com.alphabackend.service;

import com.alpha.generated.model.*;
import com.alphabackend.UtilsTest.Utils;
import com.alphabackend.exception.ResourceNotFoundException;
import com.alphabackend.mapper.*;
import com.alphabackend.model.entity.ImageProjectEntity;
import com.alphabackend.model.entity.ProjectEntity;
import com.alphabackend.repository.ImageProjectRepository;
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
class ImageProjectServiceTest {

    @InjectMocks
    private ImageProjectService imageProjectService;

    @Mock
    private ImageProjectRepository imageProjectRepository;

    @Mock
    private ProjectRepository           projectRepository;

    @Mock
    private ProjectService projectService;

    @Mock
    private TagRepository      tagRepository;

    @Spy
    private ImageProjectMapper imageProjectMapper = new ImageProjectMapperImpl();

    @Spy
    private TagMapper tagMapper = new TagMapperImpl();

    @Spy
    private ProjectMapper projectMapper = new ProjectMapperImpl();

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(imageProjectMapper, "tagMapper", tagMapper);
        ReflectionTestUtils.setField(imageProjectMapper, "projectMapper", projectMapper);
    }

    @Test
    void testGetImageProject() {
        // Data
        Long idTest = 1L;

        ImageProjectDto imageProjectDtoCompareTest = Utils.getImageProjectDtoTest();

        ImageProjectEntity imageProjectEntityResultTest = Utils.getImageProjectEntityTest();

        // When
        when(this.imageProjectRepository.findById(idTest)).thenReturn(Optional.of(imageProjectEntityResultTest));

        // Test
        ImageProjectDto imageProjectDtoResultTest = this.imageProjectService.getImageProject(idTest);

        // Result
        assertEquals(imageProjectDtoCompareTest, imageProjectDtoResultTest);
    }

    @Test
    void testGetImageProjectFailure() {
        // Data
        Long idTest = 1L;

        // When
        when(this.imageProjectRepository.findById(idTest)).thenReturn(Optional.empty());

        // Test
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> imageProjectService.getImageProject(idTest));

        // Result
        assertEquals("ResourceNotFoundException: Image_project object id 1 : Not found in database|Image_project|GET", exception.getMessage());
    }

    @Test
    void testGetAllImageProjectSuccess() {
        // Data

        List<ImageProjectDto> imageProjectDtoListCompareTest = List.of(Utils.getImageProjectDtoTest());

        List<ImageProjectEntity> imageProjectEntityListResultTest = List.of(Utils.getImageProjectEntityTest());

        // When
        when(this.imageProjectRepository.findAll()).thenReturn(imageProjectEntityListResultTest);

        // Test
        List<ImageProjectDto> imageProjectDtoListResultTest = this.imageProjectService.getAllImageProjects();

        // Result
        assertEquals(imageProjectDtoListCompareTest, imageProjectDtoListResultTest);
    }

    @Test
    void testGetAllImageProjectFailure() {
        // Data

        // When
        when(this.imageProjectRepository.findAll()).thenReturn(List.of());

        // Test
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> imageProjectService.getAllImageProjects());

        // Result
        assertEquals("ResourceNotFoundException: No Image_project objects found in database|Image_project|GET", exception.getMessage());
    }

    @Test
    void testGetAllImageProjectByProjectIdSuccess() {
        // Data
        Long idProjectTest = 1L;

        List<ImageProjectDto> imageProjectDtoListCompareTest = List.of(Utils.getImageProjectDtoTest());

        List<ImageProjectEntity> imageProjectEntityListResultTest = List.of(Utils.getImageProjectEntityTest());

        // When
        when(this.imageProjectRepository.findByProjectEntity_Id(idProjectTest)).thenReturn(imageProjectEntityListResultTest);

        // Test
        List<ImageProjectDto> imageProjectDtoListResultTest = this.imageProjectService.getAllImageProjectByProjectId(idProjectTest);

        // Result
        assertEquals(imageProjectDtoListCompareTest, imageProjectDtoListResultTest);
    }

    @Test
    void testGetAllImageProjectByProjectIdFailure() {
        // Data
        Long idProjectTest = 1L;

        // When
        when(this.imageProjectRepository.findByProjectEntity_Id(idProjectTest)).thenReturn(List.of());

        // Test
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> this.imageProjectService.getAllImageProjectByProjectId(idProjectTest));

        // Result
        assertEquals("ResourceNotFoundException: No Image_project objects found in database|Image_project|GET", exception.getMessage());
    }

    @Test
    void testAddImageProjectSuccess() {
        // Data
        Long idProjectTest = 1L;

        ImageProjectDto imageProjectDtoEntryTest = Utils.getImageProjectDtoTest();
        ImageProjectDto imageProjectDtoCompareTest = Utils.getImageProjectDtoTest();

        ProjectEntity projectEntityResultTest = Utils.getProjectEntityTest();

        ImageProjectEntity imageProjectEntityResultTest = Utils.getImageProjectEntityTest();
        // When
        when(this.imageProjectRepository.save(any(ImageProjectEntity.class))).thenReturn(imageProjectEntityResultTest);
        when(this.projectRepository.findById(idProjectTest)).thenReturn(Optional.of(projectEntityResultTest));
        // Test

        ImageProjectDto imageProjectDtoResultTest = this.imageProjectService.addImageProject(imageProjectDtoEntryTest);

        // Result
        assertEquals(imageProjectDtoCompareTest, imageProjectDtoResultTest);
    }

    @Test
    void testAddImageProjectFailure() {
        // Data
        ImageProjectDto imageProjectDtoEntryTest = Utils.getImageProjectDtoTest();

        // When

        // Test
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> this.imageProjectService.addImageProject(imageProjectDtoEntryTest));

        // Result
        assertEquals("ResourceNotFoundException: Project object id 1 : Not found in database|Project|GET", exception.getMessage());
    }

    @Test
    void testAddManyImageProjectSuccess() {
        // Data
        Long idProjectTest = 1L;

        ImageProjectDto imageProjectDtoEntryTest = Utils.getImageProjectDtoTest();

        List<ImageProjectDto> imageProjectDtoListEntryTest = List.of(imageProjectDtoEntryTest);

        ImageProjectDto imageProjectDtoCompareTest = Utils.getImageProjectDtoTest();

        List<ImageProjectDto> imageProjectDtoListCompareTest = List.of(imageProjectDtoCompareTest);

        ProjectEntity projectEntityResultTest = Utils.getProjectEntityTest();

        ImageProjectEntity imageProjectEntityResultTest = Utils.getImageProjectEntityTest();

        List<ImageProjectEntity> imageProjectEntityListEntryTest = List.of(imageProjectEntityResultTest);

        // When
        when(this.imageProjectRepository.saveAll(any())).thenReturn(imageProjectEntityListEntryTest);
        when(this.projectRepository.findById(idProjectTest)).thenReturn(Optional.of(projectEntityResultTest));
        // Test
        List<ImageProjectDto> imageProjectDtoListResultTest = this.imageProjectService.addManyImageProject(imageProjectDtoListEntryTest);

        // Result
        assertEquals(imageProjectDtoListCompareTest, imageProjectDtoListResultTest);
    }

    @Test
    void testDeleteImageProjectSuccess() {
        // Data
        Long idTest = 1L;

        ResultDto resultDtoCompareTest = new ResultDto();
        resultDtoCompareTest.setResult(ResultEnum.VALIDATE);

        ImageProjectEntity imageProjectEntityResultTest = Utils.getImageProjectEntityTest();

        // When
        when(this.imageProjectRepository.findById(idTest)).thenReturn(Optional.of(imageProjectEntityResultTest));
        doNothing().when(this.imageProjectRepository).delete(imageProjectEntityResultTest);
        when(this.imageProjectRepository.existsById(idTest)).thenReturn(Boolean.FALSE);

        // Test
        ResultDto resultDtoResultTest = this.imageProjectService.deleteImageProject(idTest);

        // Result
        assertEquals(resultDtoCompareTest, resultDtoResultTest);
    }

    @Test
    void testDeleteImageProjectFailure() {
        // Data
        Long idTest = 1L;

        // When
        when(this.imageProjectRepository.findById(idTest)).thenReturn(Optional.empty());

        // Test
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> this.imageProjectService.deleteImageProject(idTest));

        // Result
        assertEquals("ResourceNotFoundException: Image_project object id 1 : Not found, could not be deleted|Image_project|DELETE", exception.getMessage());
    }

    @Test
    void testUpdateImageProjectSuccess() {
        // Data
        Long idTest = 1L;

        Long idProject = 1L;

        ImageProjectDto imageProjectDtoEntryTest = Utils.getImageProjectDtoTest();
        ImageProjectDto imageProjectDtoCompareTest = Utils.getImageProjectDtoTest();

        ImageProjectEntity imageProjectEntityResultTest = Utils.getImageProjectEntityTest();

        ProjectEntity projectEntityTest = Utils.getProjectEntityTest();

        // When
        when(this.imageProjectRepository.save(any(ImageProjectEntity.class))).thenReturn(imageProjectEntityResultTest);
        when(this.imageProjectRepository.findById(idTest)).thenReturn(Optional.of(imageProjectEntityResultTest));
        when(this.projectRepository.findById(idProject)).thenReturn(Optional.of(projectEntityTest));
        when(this.tagRepository.findAllById(imageProjectDtoEntryTest.getTagSet())).thenReturn(List.of(
                Utils.getTagEntityTest()));

        Instant fixedInstant = Utils.getInstantTest();

        try (MockedStatic<Instant> mockedInstant = mockStatic(Instant.class)) {
            mockedInstant.when(Instant::now).thenReturn(fixedInstant);

            // Test
            ImageProjectDto imageProjectDtoResultTest = this.imageProjectService.updateImageProject(idTest, imageProjectDtoEntryTest);

            // Result
            assertEquals(imageProjectDtoCompareTest, imageProjectDtoResultTest);
        }
    }

    @Test
    void testUpdateProjectFailureImageIllustrationNotFound() {
        // Data
        Long idTest = 1L;

        Long idProject = 1L;

        ImageProjectDto imageProjectDtoEntryTest = Utils.getImageProjectDtoTest();

        // When
        when(this.projectRepository.findById(idProject)).thenReturn(Optional.empty());

        // Test
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> this.imageProjectService.updateImageProject(idTest, imageProjectDtoEntryTest));

        // Result
        assertEquals("ResourceNotFoundException: Project object id 1 : Not found, could not be update|Project|PUT", exception.getMessage());
    }

    @Test
    void testUpdateImageProjectFailure() {
        // Data
        Long idTest = 1L;

        Long idProject = 1L;

        ImageProjectDto imageProjectDtoEntryTest = Utils.getImageProjectDtoTest();
        ProjectEntity projectEntityTest = Utils.getProjectEntityTest();

        // When
        when(this.projectRepository.findById(idProject)).thenReturn(Optional.of(projectEntityTest));
        when(this.imageProjectRepository.findById(idTest)).thenReturn(Optional.empty());

        // Test
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> this.imageProjectService.updateImageProject(idTest, imageProjectDtoEntryTest));

        // Result
        assertEquals("ResourceNotFoundException: Image_project object id 1 : Not found, could not be update|Image_project|PUT", exception.getMessage());
    }
}
