package com.PolKevich.ITBcamp0824.service.impl;

import com.PolKevich.ITBcamp0824.dto.*;
import com.PolKevich.ITBcamp0824.exception.ServiceException;
import com.PolKevich.ITBcamp0824.mappers.AppMapper;
import com.PolKevich.ITBcamp0824.model.Project;
import com.PolKevich.ITBcamp0824.model.User;
import com.PolKevich.ITBcamp0824.model.UserToProject;
import com.PolKevich.ITBcamp0824.repository.ProjectRepository;
import com.PolKevich.ITBcamp0824.repository.UserToProjectRepository;
import com.PolKevich.ITBcamp0824.repository.UserRepository;
import com.PolKevich.ITBcamp0824.service.ITBcapmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Log4j2
@Service
@RequiredArgsConstructor
public class ITBcapmServiceImpl implements ITBcapmService {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final UserToProjectRepository userToProjectRepository;
    private final AppMapper appMapper;


    @Override
    @Transactional
    public User create(UserRequest request) {

        log.info("Create new user: {}", request.toString());

        User createUser = appMapper.convertRequest(request);

        User createdUser = userRepository.save(createUser);

        log.info("User create successful {}", createdUser);

        return createdUser;
    }

    @Override
    @Transactional
    public Project create(ProjectRequest request) {

        log.info("Create new project: {}", request.toString());

        Project createProject = appMapper.convertRequest(request);

        Project createdProject = projectRepository.save(createProject);

        log.info("Project create successful {}", createdProject);

        return createdProject;
    }

    @Override
    @Transactional
    public UserToProject addUserToProject(UserToProjectRequest request) throws ServiceException {

        log.info("Adding a new user to the project: {}", request.toString());

        try {
            Project project = projectRepository.findById(request.getProjectId()).orElseThrow(()
                    -> new RuntimeException("Project not found"));

            log.info("There is such a project: {}", project);

            User user = userRepository.findById(request.getUserId()).orElseThrow(()
                    -> new RuntimeException("Employee not found"));

            log.info("There is such a user: {}", user);

        } catch (RuntimeException e) {

            throw new ServiceException(e);

        }

        UserToProject userToProject = appMapper.userToProjectRequest(request);

        UserToProject addUserProject = userToProjectRepository.save(userToProject);

        log.info("Adding a new user to the project successful {}", addUserProject.toString());

        return addUserProject;
    }

    @Override
    public Page<UserToProjectResponse> findAllProjectsAndUsers(Pageable pageable) {

        log.info("Request all projects with users");

        Page<UserToProject> userToProjectPage = userToProjectRepository.findAll(pageable);

        Map<Long, UserToProjectResponse> projectMap = new HashMap<>();

        for (UserToProject userToProject : userToProjectPage) {

            Optional<Project> projectOpt = projectRepository.findById(userToProject.getProjectId());

            if (projectOpt.isPresent()) {

                Project project = projectOpt.get();

                List<User> users = userRepository.
                        findAllById(Collections.singletonList(userToProject.getUserId()));
                List<UserResponse> userResponses = appMapper.usersToUserResponses(users);

                UserToProjectResponse projectResponse = projectMap.
                        computeIfAbsent(project.getProjectId(), id -> {

                    return appMapper.userToProjectResponse(project, new ArrayList<>());
                });

                projectResponse.getUsers().addAll(userResponses);
            }
        }

        List<UserToProjectResponse> userToProjectResponses = new ArrayList<>(projectMap.values());

        userToProjectResponses.sort(Comparator.comparing(UserToProjectResponse::getProjectName));

        return new PageImpl<>(userToProjectResponses, pageable, userToProjectPage.getTotalElements());
    }

}
