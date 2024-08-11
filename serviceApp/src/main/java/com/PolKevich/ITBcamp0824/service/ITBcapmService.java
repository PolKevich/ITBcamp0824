package com.PolKevich.ITBcamp0824.service;

import com.PolKevich.ITBcamp0824.dto.ProjectRequest;
import com.PolKevich.ITBcamp0824.dto.UserToProjectRequest;
import com.PolKevich.ITBcamp0824.dto.UserToProjectResponse;
import com.PolKevich.ITBcamp0824.dto.UserRequest;
import com.PolKevich.ITBcamp0824.exception.ServiceException;
import com.PolKevich.ITBcamp0824.model.Project;
import com.PolKevich.ITBcamp0824.model.User;
import com.PolKevich.ITBcamp0824.model.UserToProject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITBcapmService {

    User create(UserRequest request);
    Project create(ProjectRequest request);
    UserToProject addUserToProject(UserToProjectRequest request) throws ServiceException;
    Page<UserToProjectResponse> findAllProjectsAndUsers(Pageable pageable);
}
