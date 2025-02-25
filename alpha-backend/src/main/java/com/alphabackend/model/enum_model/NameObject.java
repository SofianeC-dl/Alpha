package com.alphabackend.model.enum_model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NameObject {

    PROJECT("project"),
    IMAGE_ILLUSTRATION("image_illustration"),
    IMAGE_PROJECT("image_project"),
    TAG("tag"),
    AUTHENTICATION("authentication"),

    PROJECT_MAJ("Project"),
    IMAGE_ILLUSTRATION_MAJ("Image_illustration"),
    IMAGE_PROJECT_MAJ("Image_project"),
    TAG_MAJ("Tag"),
    AUTHENTICATION_MAJ("Authentication"),

    PROJECT_UPPER("PROJECT"),
    IMAGE_ILLUSTRATION_UPPER("IMAGE_ILLUSTRATION"),
    IMAGE_PROJECT_UPPER("IMAGE_PROJECT"),
    TAG_UPPER("TAG"),
    AUTHENTICATION_UPPER("AUTHENTICATION");

    private final String name;

}
