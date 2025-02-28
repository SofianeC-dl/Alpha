package com.alphabackend.controller.integration;

import com.alphabackend.AlphaBackendApplication;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = AlphaBackendApplication.class)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
@Sql(scripts = {"/cleanup.sql", "/schema.sql", "/data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class ProjectControllerITest{

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetProjectById() throws Exception {
        mockMvc.perform(get("/project/get/3"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.description").value("Description du projet de test"))
                .andExpect(jsonPath("$.tagSet").isArray())
                .andExpect(jsonPath("$.tagSet.length()").value(2));

    }


    @Test
    void testGetProjectNotFound() throws Exception {
        mockMvc.perform(get("/project/get/9999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("ResourceNotFoundException: Project object id 9 999 : Not found in database"));
    }

    @Test
    void testGetAllProjects() throws Exception {
        mockMvc.perform(get("/projects/get"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.projectList").isArray())
                .andExpect(jsonPath("$.projectList.length()").value(Matchers.greaterThan(0)));
    }

    @Test
    @Sql(scripts = {"/cleanup.sql", "/schema.sql"})
    void testGetAllProjectsNotFound() throws Exception {
        mockMvc.perform(get("/projects/get")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("ResourceNotFoundException: No Project objects found in database"));

    }

    @Test
    void testAddProject() throws Exception {
        String projectJson =
                """
                {
                    "description": "Nouveau projet de test",
                    "idImageIllustration": 2,
                    "uploadDate": "2025-02-26T22:00:00Z",
                    "updateDate": "2025-02-26T22:00:00Z",
                    "tagSet": [2, 3]
                }
                """;

        mockMvc.perform(post("/project/post")
                .contentType(MediaType.APPLICATION_JSON)
                .content(projectJson))
               .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.description").value("Nouveau projet de test"));
    }

    @Test
    void testAddProjectNotFound() throws Exception {
        String projectJson =
                """
                {
                    "description": "Nouveau projet de test",
                    "idImageIllustration": 9999,
                    "uploadDate": "2025-02-26T22:00:00Z",
                    "updateDate": "2025-02-26T22:00:00Z",
                    "tagSet": [2, 3]
                }
                """;

        mockMvc.perform(post("/project/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(projectJson))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("ResourceNotFoundException: Image_illustration object id 9 999 : Not found in database"));
    }

    @Test
    void testAddProjectWithImageIllustration() throws Exception {
        String projectJson = """
                {
                    "projectDto": {
                        "description": "Nouveau projet de test",
                        "uploadDate": "2025-02-26T22:00:00Z",
                        "updateDate": "2025-02-26T22:00:00Z",
                        "tagSet": [2, 3]
                    },
                    "imageIllustrationDto": {
                        "datas": "SGVsbG8=",
                        "uploadDate": "2025-02-26T22:00:00Z",
                        "tagSet": [2]
                    }
                }
                """;

        mockMvc.perform(post("/project/post/withImageIllustration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(projectJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.description").value("Nouveau projet de test"));
    }

    @Test
    void testUpdateProject() throws Exception {
        String updateJson = """
        {
            "id": 3,
            "description": "Projet mis à jour",
            "idImageIllustration": 2,
            "uploadDate": "2025-02-26T22:00:00Z",
            "updateDate": "2025-02-26T22:00:00Z",
            "tagSet": [2]
        }
        """;

        mockMvc.perform(put("/project/put/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.description").value("Projet mis à jour"))
                .andExpect(jsonPath("$.tagSet").isArray())
                .andExpect(jsonPath("$.tagSet.length()").value(1));
    }

    @Test
    void testUpdateProjectNotFoundImageIllustration() throws Exception {
        String updateJson = """
        {
            "id": 3,
            "description": "Projet mis à jour",
            "idImageIllustration": 9999,
            "uploadDate": "2025-02-26T22:00:00Z",
            "updateDate": "2025-02-26T22:00:00Z",
            "tagSet": [2]
        }
        """;

        mockMvc.perform(put("/project/put/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateJson))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("ResourceNotFoundException: Image_illustration object id 9 999 : Not found, could not be update"));
    }

    @Test
    void testUpdateProjectNotFoundProject() throws Exception {
        String updateJson = """
        {
            "id": 9999,
            "description": "Projet mis à jour",
            "idImageIllustration": 2,
            "uploadDate": "2025-02-26T22:00:00Z",
            "updateDate": "2025-02-26T22:00:00Z",
            "tagSet": [2]
        }
        """;

        mockMvc.perform(put("/project/put/9999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateJson))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("ResourceNotFoundException: Project object id 9 999 : Not found, could not be update"));
    }

    @Test
    void testDeleteProject() throws Exception {
       mockMvc.perform(delete("/project/delete/{id}", 3))
               .andExpect(status().isOk())
               .andExpect(content().contentType("application/json"))
               .andExpect(jsonPath("$.result").value("Validate"));
    }

    @Test
    void testDeleteProjectNotFound() throws Exception {
        mockMvc.perform(delete("/project/delete/{id}", 9999)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("ResourceNotFoundException: Project object id 9 999 : Not found, could not be deleted"));
    }
}
