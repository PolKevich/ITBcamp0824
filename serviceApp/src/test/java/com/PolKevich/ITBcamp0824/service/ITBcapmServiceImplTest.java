package com.PolKevich.ITBcamp0824.service;

import com.PolKevich.ITBcamp0824.dto.*;
import com.PolKevich.ITBcamp0824.mappers.AppMapper;
import com.PolKevich.ITBcamp0824.model.Project;
import com.PolKevich.ITBcamp0824.model.User;
import com.PolKevich.ITBcamp0824.model.UserToProject;
import com.PolKevich.ITBcamp0824.repository.ProjectRepository;
import com.PolKevich.ITBcamp0824.repository.UserRepository;
import com.PolKevich.ITBcamp0824.repository.UserToProjectRepository;
import com.PolKevich.ITBcamp0824.service.impl.ITBcapmServiceImpl;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ITBcapmServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private UserToProjectRepository userToProjectRepository;

    @Mock
    private AppMapper appMapper;

    @InjectMocks
    private ITBcapmServiceImpl itBcapmService;

    @Test
    void testCreateUser() {

        UserRequest userRequest = new UserRequest();
        userRequest.setLastname("Sergeev");
        userRequest.setFirstname("Sergei");
        userRequest.setPatronymic("Sergeevich");
        userRequest.setEmail("Serega@mail.com");
        userRequest.setPost("Project Manager");

        User mappedUser = new User();
        mappedUser.setLastname("Sergeev");
        mappedUser.setFirstname("Sergei");
        mappedUser.setPatronymic("Sergeevich");
        mappedUser.setEmail("Serega@mail.com");
        mappedUser.setPost("Project Manager");

        when(appMapper.convertRequest(userRequest)).thenReturn(mappedUser);
        when(userRepository.save(any(User.class))).thenReturn(mappedUser);

        User createdUser = itBcapmService.create(userRequest);

        assertEquals("Sergeev", createdUser.getLastname());
        assertEquals("Serega@mail.com", createdUser.getEmail());


        verify(appMapper, times(1)).convertRequest(userRequest);
        verify(userRepository, times(1)).save(any(User.class));

    }

    @Test
    void testCreateProject() {

        ProjectRequest projectRequest = new ProjectRequest();
        projectRequest.setProjectName("ITBCamp in java");
        projectRequest.setProjectDescription("This is a project with a three-level development architecture.");

        Project mappedProject = new Project();
        mappedProject.setProjectName("ITBCamp in java");
        mappedProject.setProjectDescription("This is a project with a three-level development architecture.");

        when(appMapper.convertRequest(projectRequest)).thenReturn(mappedProject);
        when(projectRepository.save(any(Project.class))).thenReturn(mappedProject);

        Project createdProject = itBcapmService.create(projectRequest);

        assertEquals("ITBCamp in java", createdProject.getProjectName());
        assertEquals("This is a project with a three-level development architecture.", createdProject.getProjectDescription());

        verify(appMapper, times(1)).convertRequest(projectRequest);
        verify(projectRepository, times(1)).save(any(Project.class));

    }

    @SneakyThrows
    @Test
    void testAddUserToProject() {

        UserToProjectRequest request = new UserToProjectRequest();
        request.setProjectId(1L);
        request.setUserId(2L);

        UserToProject userToProject = new UserToProject();
        userToProject.setProjectId(1L);
        userToProject.setUserId(2L);

        Project project = new Project();
        project.setProjectId(1L);
        project.setProjectName("Test Project");

        User user = new User();
        user.setUserId(2L);
        user.setFirstname("Test user");

        when(appMapper.userToProjectRequest(request)).thenReturn(userToProject);
        when(userToProjectRepository.save(any(UserToProject.class))).thenReturn(userToProject);
        when(projectRepository.findById(1l)).thenReturn(Optional.of(project));
        when(userRepository.findById(2l)).thenReturn(Optional.of(user));

        UserToProject result = itBcapmService.addUserToProject(request);

        assertEquals(1l, result.getProjectId());
        assertEquals(2l, result.getUserId());

        verify(appMapper, times(1)).userToProjectRequest(request);
        verify(userToProjectRepository, times(1)).save(any(UserToProject.class));
        verify(projectRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).findById(2L);

    }

    @Test
    void testFindAllProjectsAndUsers() {

        Pageable pageable = PageRequest.of(0, 10);

        UserToProject userToProject = new UserToProject();
        userToProject.setProjectId(1L);
        userToProject.setUserId(2L);

        Page<UserToProject> userToProjectPage = new PageImpl<>(Collections.singletonList(userToProject));

        Project project = new Project();
        project.setProjectName("ITBCamp in java");
        project.setProjectDescription("This is a project with a three-level development architecture.");

        User user = new User();
        user.setLastname("Ivanov");
        user.setFirstname("Ivan");
        user.setPatronymic("Ivanovich");

        when(userToProjectRepository.findAll(pageable)).thenReturn(userToProjectPage);

        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        when(userRepository.findAllById(Collections.singletonList(2L))).thenReturn(Collections.singletonList(user));

        when(appMapper.usersToUserResponses(anyList())).thenReturn(Collections.singletonList(new UserResponse("Ivanov Ivan Ivanovich")));

        when(appMapper.userToProjectResponse(eq(project), anyList())).thenReturn(new UserToProjectResponse("ITBCamp in java",
                "This is a project with a three-level development architecture.",
                new ArrayList<>(Collections.singletonList(new UserResponse("Ivanov Ivan Ivanovich")))));

        Page<UserToProjectResponse> result = itBcapmService.findAllProjectsAndUsers(pageable);

        assertEquals(1, result.getTotalElements());

        assertEquals("ITBCamp in java", result.getContent().get(0).getProjectName());

        assertEquals("Ivanov Ivan Ivanovich", result.getContent().get(0).getUsers().get(0).getFullname());

        verify(userToProjectRepository, times(1)).findAll(pageable);
        verify(projectRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).findAllById(Collections.singletonList(2L));
        verify(appMapper, times(1)).usersToUserResponses(anyList());
        verify(appMapper, times(1)).userToProjectResponse(eq(project), anyList());

    }

}
