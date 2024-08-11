package com.PolKevich.ITBcamp0824.controller;

import com.PolKevich.ITBcamp0824.dto.ProjectRequest;
import com.PolKevich.ITBcamp0824.dto.UserRequest;
import com.PolKevich.ITBcamp0824.dto.UserToProjectRequest;
import com.PolKevich.ITBcamp0824.dto.UserToProjectResponse;
import com.PolKevich.ITBcamp0824.exception.ServiceException;
import com.PolKevich.ITBcamp0824.model.Project;
import com.PolKevich.ITBcamp0824.model.User;
import com.PolKevich.ITBcamp0824.model.UserToProject;
import com.PolKevich.ITBcamp0824.service.ITBcapmService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
public class ITBcampController {

@Autowired
private ITBcapmService itBcapmService;

    @PostMapping("/user")
    public ResponseEntity<User> addUser(@Valid @RequestBody UserRequest request) {

        User createdUser = itBcapmService.create(request);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PostMapping("/project")
    public ResponseEntity<Project> addProject(@Valid @RequestBody ProjectRequest request) {

        Project createdProject = itBcapmService.create(request);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    @PostMapping("/projectid/{projectId}/userid/{userId}")
    public ResponseEntity<UserToProject> addUserToProject(@Valid @RequestBody UserToProjectRequest request) throws ServiceException {

        UserToProject addUserToProject = itBcapmService.addUserToProject(request);
        return new ResponseEntity<>(addUserToProject, HttpStatus.OK);
    }

    @GetMapping("/getproject")
    public ResponseEntity<Object> findAllProjectsAndUsers(Pageable pageable) {

        Page<UserToProjectResponse> usersToProject = itBcapmService.findAllProjectsAndUsers(pageable);

        if(usersToProject.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(usersToProject, HttpStatus.OK);
    }
}
