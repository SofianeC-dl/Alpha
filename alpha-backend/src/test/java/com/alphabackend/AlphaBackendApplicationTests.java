package com.alphabackend;

import com.alphabackend.filter.FilterConfig;
import com.alphabackend.filter.JWTFilter;
import com.alphabackend.security.SecurityConfig;
import com.alphabackend.service.*;
import com.alphabackend.utils.JWTUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = AlphaBackendApplication.class)
@ActiveProfiles("test")
@TestPropertySource(properties = {"spring.flyway.enabled=false"})
@ComponentScan(basePackages = "com.alphabackend")
@AutoConfigureMockMvc
class AlphaBackendApplicationTests {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private TagService tagService;

    @Autowired
    private ImageIllustrationService imageIllustrationService;

    @Autowired
    private ImageProjectService imageProjectService;

    @Autowired
    private AuthentificationService authentificationService;

    @Autowired
    private UserService userService;
    
    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    private FilterConfig filterConfig;

    @Autowired
    private JWTFilter jwtFilter;

    @Autowired
    private JWTUtil jwtUtil;

    @Test
    void contextLoads() {
        assertNotNull(projectService, "Le bean ProjectService doit être injecté");
        assertNotNull(tagService, "Le bean TagService doit être injecté");
        assertNotNull(imageIllustrationService, "Le bean ImageIllustrationService doit être injecté");
        assertNotNull(imageProjectService, "Le bean ImageProjectService doit être injecté");
        assertNotNull(authentificationService, "Le bean AuthentificationService doit être injecté");
        assertNotNull(userService, "Le bean UserService doit être injecté");
        assertNotNull(securityConfig, "Le bean SecurityConfig doit être injecté");
        assertNotNull(filterConfig, "Le bean FilterConfig doit être injecté");
        assertNotNull(jwtFilter, "Le bean JWTFilter doit être injecté");
        assertNotNull(jwtUtil, "Le bean JWTUtil doit être injecté");
    }
}
