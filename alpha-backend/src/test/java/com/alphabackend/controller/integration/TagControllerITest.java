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
class TagControllerITest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetTagById() throws Exception {
        mockMvc.perform(get("/tag/get/3"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.label").value("Tag A"));

    }

    @Test
    void testGetTagNotFound() throws Exception {
        mockMvc.perform(get("/tag/get/9999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("ResourceNotFoundException: Tag object id 9 999 : Not found in database"));
    }

    @Test
    void testGetAllTags() throws Exception {
        mockMvc.perform(get("/tags/get"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.tagList").isArray())
                .andExpect(jsonPath("$.tagList.length()").value(Matchers.greaterThan(0)));
    }

    @Test
    @Sql(scripts = {"/cleanup.sql", "/schema.sql"})
    void testGetAllTagsNotFound() throws Exception {
        mockMvc.perform(get("/tags/get")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("ResourceNotFoundException: No Tag objects found in database"));

    }

    @Test
    void testAddTag() throws Exception {
        String tagJson =
                """
                {
                    "label": "Tag C",
                    "color": "#eeE"
                }
                """;

        mockMvc.perform(post("/tag/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(tagJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.label").value("Tag C"));
    }

    @Test
    void testAddManyTag() throws Exception {
        String tagJson = """
                {
                    "tagList": [
                        {
                            "label": "Tag C",
                            "color": "#eeE"
                        },
                        {
                            "label": "Tag D",
                            "color": "#eeE"
                        }
                    ]
                }
                """;

        mockMvc.perform(post("/tag/post/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(tagJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.tagList").isArray())
                .andExpect(jsonPath("$.tagList.length()").value(Matchers.greaterThan(0)));
    }

    @Test
    void testUpdateTag() throws Exception {
        // Exemple de payload pour la mise à jour du projet
        String updateJson = """
        {
            "id": 3,
            "label": "Tag Update",
            "color": "#eeE"
        }
       \s""";

        mockMvc.perform(put("/tag/put/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.label").value("Tag Update"));
    }

    @Test
    void testDeleteTag() throws Exception {
        mockMvc.perform(delete("/tag/delete/{id}", 4))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.result").value("Validate"));
    }

    @Test
    void testDeleteImageIllustrationNotFound() throws Exception {
        mockMvc.perform(delete("/tag/delete/{id}", 9999)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("ResourceNotFoundException: Tag object id 9 999 : Not found, could not be deleted"));
    }
}
