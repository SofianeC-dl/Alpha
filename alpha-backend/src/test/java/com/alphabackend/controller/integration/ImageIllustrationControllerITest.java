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
class ImageIllustrationControllerITest{

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetImageIllustrationById() throws Exception {
        mockMvc.perform(get("/imageIllustration/get/2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.datas").value("SGVsbG8="))
                .andExpect(jsonPath("$.tagSet").isArray())
                .andExpect(jsonPath("$.tagSet.length()").value(1));
    }

    @Test
    void testGetImageIllustrationNotFound() throws Exception {
        mockMvc.perform(get("/imageIllustration/get/9999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("ResourceNotFoundException: Image_illustration object id 9 999 : Not found in database"));
    }

    @Test
    void testGetImageIllustrationByIdProject() throws Exception {
        mockMvc.perform(get("/imageIllustration/get/byProject/3"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.datas").value("SGVsbG8="))
                .andExpect(jsonPath("$.tagSet").isArray())
                .andExpect(jsonPath("$.tagSet.length()").value(1));
    }

    @Test
    void testGetImageIllustrationByIdProjectNotFound() throws Exception {
        mockMvc.perform(get("/imageIllustration/get/byProject/9999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("ResourceNotFoundException: Project object id 9 999 : Not found in database"));
    }

    @Test
    void testGetAllImageIllustrations() throws Exception {
        mockMvc.perform(get("/imageIllustrations/get"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.imageIllustrationList").isArray())
                .andExpect(jsonPath("$.imageIllustrationList.length()").value(Matchers.greaterThan(0)));
    }

    @Test
    @Sql(scripts = {"/cleanup.sql", "/schema.sql"})
    void testGetAllImageIllustrationsNotFound() throws Exception {
        mockMvc.perform(get("/imageIllustrations/get")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("ResourceNotFoundException: No Image_illustration objects found in database"));

    }

    @Test
    void testAddImageIllustration() throws Exception {
        String imageIllustrationJson = """
                {
                    "datas": "SGVsbG8=",
                    "uploadDate": "2025-02-26T22:00:00Z",
                    "tagSet": [3]
                }
                """;

        mockMvc.perform(post("/imageIllustration/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(imageIllustrationJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.datas").value("SGVsbG8="))
                .andExpect(jsonPath("$.tagSet").isArray())
                .andExpect(jsonPath("$.tagSet.length()").value(1));
    }

    @Test
    void testUpdateImageIllustration() throws Exception {
        // Exemple de payload pour la mise à jour du projet
        String updateJson = """
        {
            "id": 2,
            "datas": "SGVsbG8=",
            "uploadDate": "2025-02-26T22:00:00Z",
            "tagSet": [3]
        }
        """;

        mockMvc.perform(put("/imageIllustration/put/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.datas").value("SGVsbG8="))
                .andExpect(jsonPath("$.uploadDate").value("2025-02-26T22:00:00Z"))
                .andExpect(jsonPath("$.tagSet").isArray())
                .andExpect(jsonPath("$.tagSet.length()").value(1));
    }

    @Test
    void testUpdateImageIllustrationNotFound() throws Exception {
        String updateJson = """
        {
            "id": 9999,
            "datas": "SGVsbG8=",
            "uploadDate": "2025-02-26T22:00:00Z",
            "tagSet": [3]
        }
        """;

        mockMvc.perform(put("/imageIllustration/put/9999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateJson))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("ResourceNotFoundException: Image_illustration object id 9 999 : Not found, could not be update"));
    }

    @Test
    void testDeleteImageIllustration() throws Exception {
        mockMvc.perform(delete("/imageIllustration/delete/{id}", 2))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.result").value("Validate"));
    }

    @Test
    void testDeleteImageIllustrationNotFound() throws Exception {
        mockMvc.perform(delete("/imageIllustration/delete/{id}", 9999)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("ResourceNotFoundException: Image_illustration object id 9 999 : Not found, could not be deleted"));
    }
}
