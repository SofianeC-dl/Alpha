package com.alphabackend.UtilsTest;

import com.alpha.generated.model.*;
import com.alphabackend.model.entity.*;
import com.alphabackend.model.enum_model.RoleEnum;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.*;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class Utils {


    public static OffsetDateTime getOffsetDateTimeTest() {
        int yearTest = 2023;
        int dayTest = 1;

        return OffsetDateTime.of(LocalDate.of(yearTest, Calendar.MARCH, dayTest), LocalTime.of(0, 0), ZoneOffset.UTC);
    }

    public static Instant getInstantTest() {
        String instantTest = "2023-02-01T00:00:00.00Z";

        return Instant.parse(instantTest);
    }

    public static ProjectDto getProjectDtoTest() {
        Long idTest = 1L;

        Integer idImageIllustrationTest = 1;

        Long idTagTest = 1L;

        OffsetDateTime updateDateTest = Utils.getOffsetDateTimeTest();
        OffsetDateTime uploadDateTest = Utils.getOffsetDateTimeTest();

        ProjectDto projectDtoResultTest = new ProjectDto();
        projectDtoResultTest.setId(idTest.intValue());
        projectDtoResultTest.setUpdateDate(updateDateTest);
        projectDtoResultTest.setDescription("Test Description");
        projectDtoResultTest.setUploadDate(uploadDateTest);
        projectDtoResultTest.setIdImageIllustration(idImageIllustrationTest);
        projectDtoResultTest.addTagSetItem(idTagTest);

        return projectDtoResultTest;
    }

    public static ProjectWithImageIllustrationDto getProjectWithImageIllustrationDtoTest() {
        ProjectDto projectDtoTest = Utils.getProjectDtoTest();
        ImageIllustrationDto imageIllustrationDtoTest = Utils.getImageIllustrationDtoTest();

        ProjectWithImageIllustrationDto projectWithImageIllustrationDto = new ProjectWithImageIllustrationDto();
        projectWithImageIllustrationDto.setProjectDto(projectDtoTest);
        projectWithImageIllustrationDto.setImageIllustrationDto(imageIllustrationDtoTest);

        return projectWithImageIllustrationDto;
    }

    public static ProjectEntity getProjectEntityTest() {
        Long idTest = 1L;

        ImageIllustrationEntity imageIllustrationEntity = Utils.getImageIllustrationEntityTest();

        Set<TagEntity> tagEntitySetTest = new HashSet<>();
        tagEntitySetTest.add(Utils.getTagEntityTest());

        Instant updateInstantTest = Utils.getInstantTest();
        Instant uploadInstantTest = Utils.getInstantTest();

        ProjectEntity projectEntityTest = new ProjectEntity();
        projectEntityTest.setId(idTest);
        projectEntityTest.setUpdateDate(updateInstantTest);
        projectEntityTest.setDescription("Test Description");
        projectEntityTest.setUploadDate(uploadInstantTest);
        projectEntityTest.setImageIllustrationEntity(imageIllustrationEntity);
        projectEntityTest.setTagEntitySet(tagEntitySetTest);

        return projectEntityTest;
    }

    public static ImageIllustrationDto getImageIllustrationDtoTest() {
        Integer idTest = 1;

        byte[] datasTest = { 10, 20, 30, 40, 50 };

        OffsetDateTime uploadInstantTest = Utils.getOffsetDateTimeTest();

        Long idTagTest = 1L;

        ImageIllustrationDto imageIllustrationDtoResultTest = new ImageIllustrationDto();
        imageIllustrationDtoResultTest.setId(idTest);
        imageIllustrationDtoResultTest.setDatas(datasTest);
        imageIllustrationDtoResultTest.setUploadDate(uploadInstantTest);
        imageIllustrationDtoResultTest.addTagSetItem(idTagTest);

        return imageIllustrationDtoResultTest;
    }

    public static ImageIllustrationEntity getImageIllustrationEntityTest() {
        Long idTest = 1L;

        byte[] datasTest = { 10, 20, 30, 40, 50 };

        Instant uploadInstantTest = Utils.getInstantTest();

        Set<TagEntity> tagEntitySetTest = new HashSet<>();
        tagEntitySetTest.add(Utils.getTagEntityTest());

        ImageIllustrationEntity imageIllustrationEntity = new ImageIllustrationEntity();
        imageIllustrationEntity.setId(idTest);
        imageIllustrationEntity.setDatas(datasTest);
        imageIllustrationEntity.setUploadDate(uploadInstantTest);
        imageIllustrationEntity.setTagEntitySet(tagEntitySetTest);

        return imageIllustrationEntity;
    }

    public static ImageProjectDto getImageProjectDtoTest() {
        Integer idTest = 1;

        byte[] datasTest = { 10, 20, 30, 40, 50 };

        OffsetDateTime uploadInstantTest = Utils.getOffsetDateTimeTest();

        Long idProjectTest = 1L;

        Long idTagTest = 1L;

        ImageProjectDto imageProjectDtoResultTest = new ImageProjectDto();
        imageProjectDtoResultTest.setId(idTest);
        imageProjectDtoResultTest.setDatas(datasTest);
        imageProjectDtoResultTest.setUploadDate(uploadInstantTest);
        imageProjectDtoResultTest.setIdProject(idProjectTest.intValue());
        imageProjectDtoResultTest.addTagSetItem(idTagTest);

        return imageProjectDtoResultTest;
    }

    public static ImageProjectEntity getImageProjectEntityTest() {
        Long idTest = 1L;

        byte[] datasTest = { 10, 20, 30, 40, 50 };

        Instant uploadInstantTest = Utils.getInstantTest();

        ProjectEntity projectEntityTest = Utils.getProjectEntityTest();

        Set<TagEntity> tagEntitySetTest = new HashSet<>();
        tagEntitySetTest.add(Utils.getTagEntityTest());

        ImageProjectEntity imageProjectEntity = new ImageProjectEntity();
        imageProjectEntity.setId(idTest);
        imageProjectEntity.setDatas(datasTest);
        imageProjectEntity.setUploadDate(uploadInstantTest);
        imageProjectEntity.setProjectEntity(projectEntityTest);
        imageProjectEntity.setTagEntitySet(tagEntitySetTest);

        return imageProjectEntity;
    }

    public static TagDto getTagDtoTest() {
        TagDto tagDtoTest = new TagDto();
        tagDtoTest.setId(1);
        tagDtoTest.setLabel("Label Test");

        return tagDtoTest;
    }

    public static TagEntity getTagEntityTest() {
        TagEntity tagEntityTest = new TagEntity();
        tagEntityTest.setId(1L);
        tagEntityTest.setLabel("Label Test");

        return tagEntityTest;
    }

    public static UserEntity getUserEntityUserTest() {
        UserEntity userEntityTest = new UserEntity();
        userEntityTest.setId(1L);
        userEntityTest.setUsername("Test User");
        userEntityTest.setPassword("Test Password");
        userEntityTest.setRole(RoleEnum.USER);

        return userEntityTest;
    }

    public static UserEntity getUserEntityAdminTest() {
        UserEntity userEntityTest = new UserEntity();
        userEntityTest.setId(1L);
        userEntityTest.setUsername("Test Username");
        userEntityTest.setPassword("Test Password");
        userEntityTest.setRole(RoleEnum.ADMIN);

        return userEntityTest;
    }

    public static UserDetails getUserDetailsUserTest() {
        UserEntity userEntityTest = Utils.getUserEntityUserTest();

        return User
                .withUsername(userEntityTest.getUsername())
                .password(userEntityTest.getPassword())
                .authorities(userEntityTest.getRole().name())
                .build();
    }

    public static UserDetails getUserDetailsAdminTest() {
        UserEntity userEntityTest = Utils.getUserEntityAdminTest();

        return User
                .withUsername(userEntityTest.getUsername())
                .password(userEntityTest.getPassword())
                .authorities(userEntityTest.getRole().name())
                .build();
    }

    public static AuthRequest getAuthRequestTest() {
        AuthRequest authRequestTest = new AuthRequest();
        authRequestTest.setUsername("Test Username");
        authRequestTest.setPassword("Test Password");

        return authRequestTest;
    }

    public static AuthResponse getAuthResponseTest() {
        AuthResponse authResponseTest = new AuthResponse();
        authResponseTest.setToken("Test JWT");

        return authResponseTest;
    }

    public static UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationTokenTest() {
        return new UsernamePasswordAuthenticationToken("Test Username", "Test Password");
    }
}
