package com.PolKevich.ITBcamp0824.controller;

import com.PolKevich.ITBcamp0824.model.Project;
import com.PolKevich.ITBcamp0824.model.User;
import com.PolKevich.ITBcamp0824.repository.ProjectRepository;
import com.PolKevich.ITBcamp0824.repository.UserRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Paths;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SettingIntegrationTest
public class ITBcampControllerTest extends IntegrationTest{

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @SneakyThrows
    void testAddUser(){
        Map<?, ?> map = objectMapper.readValue(
                Paths.get("src/test/resources/json_for_test/userCreate.json").toFile(), Map.class);

        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(map))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstname").value("testFirstname"));
    }

    @Test
    @SneakyThrows
    void testAddProject() {
        Map<?, ?> map = objectMapper.readValue(
                Paths.get("src/test/resources/json_for_test/projectCreate.json").toFile(), Map.class);

        mockMvc.perform(post("/project")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(map))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.projectName").value("ITBCamp"));
    }

    @Test
    @SneakyThrows
    @Sql("/cleanup.sql")
    void testAddUserToProject(){

        Project projectTest = new Project();
        projectTest.setProjectId(1L);
        projectTest.setProjectName("Test Project");
        projectTest.setProjectDescription("Description of Test Project");
        projectRepository.save(projectTest);

        User userTest = new User();
        userTest.setUserId(1L);
        userTest.setLastname("Sergeev");
        userTest.setFirstname("Sergei");
        userTest.setPatronymic("Sergeevich");
        userTest.setEmail("Serega@mail.com");
        userTest.setPost("Project Manager");
        userRepository.save(userTest);

        Map<?, ?> map = objectMapper.readValue(
                Paths.get("src/test/resources/json_for_test/userToProject.json").toFile(), Map.class);

        mockMvc.perform(post("/projectid/1/userid/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(map))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.projectId").value("1"))
                .andExpect(jsonPath("$.userId").value("1"));
    }

    @Test
    @SneakyThrows
    void testFindAllProjectsAndUsers() {
        mockMvc.perform(get("/getproject")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }
}
