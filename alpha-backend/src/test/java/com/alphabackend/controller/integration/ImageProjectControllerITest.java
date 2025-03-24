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
class ImageProjectControllerITest{

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetImageProjectById() throws Exception {
        mockMvc.perform(get("/imageProject/get/3"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.datas").value("V29ybGQ="))
                .andExpect(jsonPath("$.tagSet").isArray())
                .andExpect(jsonPath("$.tagSet.length()").value(1));
    }

    @Test
    void testGetImageProjectNotFound() throws Exception {
        mockMvc.perform(get("/imageProject/get/9999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("ResourceNotFoundException: Image_project object id 9 999 : Not found in database"));
    }

    @Test
    void testGetAllImageProjectByIdProject() throws Exception {
        mockMvc.perform(get("/imageProjects/get/3"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.imageProjectList").isArray())
                .andExpect(jsonPath("$.imageProjectList.length()").value(Matchers.greaterThan(0)));
    }

    @Test
    void testGetAllImageProjectNotFound() throws Exception {
        mockMvc.perform(get("/imageProjects/get/9999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("ResourceNotFoundException: No Image_project objects found in database"));
    }

    @Test
    void testGetAllImageProjects() throws Exception {
        mockMvc.perform(get("/imageProjects/get"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.imageProjectList").isArray())
                .andExpect(jsonPath("$.imageProjectList.length()").value(Matchers.greaterThan(0)));
    }

    @Test
    @Sql(scripts = {"/cleanup.sql", "/schema.sql"})
    void testGetAllImageProjectsNotFound() throws Exception {
        mockMvc.perform(get("/imageProjects/get")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("ResourceNotFoundException: No Image_project objects found in database"));

    }

    @Test
    void testAddImageProject() throws Exception {
        String imageProjectJson = """
                {
                    "datas": "SGVsbG8=",
                    "uploadDate": "2025-02-26T22:00:00Z",
                    "idProject": 3,
                    "tagSet": [3]
                }
                """;

        mockMvc.perform(post("/imageProject/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(imageProjectJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.datas").value("SGVsbG8="))
                .andExpect(jsonPath("$.idProject").value(3))
                .andExpect(jsonPath("$.tagSet").isArray())
                .andExpect(jsonPath("$.tagSet.length()").value(1));
    }

    @Test
    void testAddImageProjectNotFound() throws Exception {
        String projectJson =
                """
                {
                    "datas": "SGVsbG8=",
                    "uploadDate": "2025-02-26T22:00:00Z",
                    "idProject": 9999,
                    "tagSet": [3]
                }
                """;

        mockMvc.perform(post("/imageProject/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(projectJson))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("ResourceNotFoundException: Project object id 9 999 : Not found in database"));
    }

    @Test
    void testAddManyImageProject() throws Exception {
        String imageProjectJson = """
                {
                    "imageProjectList": [
                        {
                            "datas": "SGVsbG8=",
                            "uploadDate": "2025-02-26T22:00:00Z",
                            "idProject": 3,
                            "tagSet": [3]
                        },
                        {
                            "datas": "SGVsbG8=",
                            "uploadDate": "2025-02-26T22:00:00Z",
                            "idProject": 3,
                            "tagSet": [3]
                        }
                    ]
                }
                """;

        mockMvc.perform(post("/imageProject/post/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(imageProjectJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.imageProjectList").isArray())
                .andExpect(jsonPath("$.imageProjectList.length()").value(Matchers.greaterThan(0)));
    }

    @Test
    void testUpdateImageProject() throws Exception {
        // Exemple de payload pour la mise à jour du projet
        String updateJson = """
        {
            "id": 3,
            "datas": "SGVsbG8=",
            "uploadDate": "2025-02-26T22:00:00Z",
            "idProject": 3,
            "tagSet": [3]
        }
        """;

        mockMvc.perform(put("/imageProject/put/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.datas").value("SGVsbG8="))
                .andExpect(jsonPath("$.uploadDate").value("2025-02-26T22:00:00Z"))
                .andExpect(jsonPath("$.idProject").value(3))
                .andExpect(jsonPath("$.tagSet").isArray())
                .andExpect(jsonPath("$.tagSet.length()").value(1));
    }

    @Test
    void testUpdateImageProjectNotFoundProject() throws Exception {
        String updateJson = """
        {
            "id": 3,
            "datas": "SGVsbG8=",
            "uploadDate": "2025-02-26T22:00:00Z",
            "idProject": 9999,
            "tagSet": [3]
        }
        """;

        mockMvc.perform(put("/imageProject/put/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateJson))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("ResourceNotFoundException: Project object id 9 999 : Not found, could not be update"));
    }

    @Test
    void testUpdateImageProjectNotFoundImageProject() throws Exception {
        String updateJson = """
        {
            "id": 9999,
            "datas": "SGVsbG8=",
            "uploadDate": "2025-02-26T22:00:00Z",
            "idProject": 3,
            "tagSet": [3]
        }
        """;

        mockMvc.perform(put("/imageProject/put/9999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateJson))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("ResourceNotFoundException: Image_project object id 9 999 : Not found, could not be update"));
    }

    @Test
    void testDeleteImageProject() throws Exception {
        mockMvc.perform(delete("/imageProject/delete/{id}", 3))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.result").value("Validate"));
    }

    @Test
    void testDeleteImageIllustrationNotFound() throws Exception {
        mockMvc.perform(delete("/imageProject/delete/{id}", 9999)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("ResourceNotFoundException: Image_project object id 9 999 : Not found, could not be deleted"));
    }
}
