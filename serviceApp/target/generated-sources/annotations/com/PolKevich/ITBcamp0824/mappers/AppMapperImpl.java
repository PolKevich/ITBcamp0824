package com.PolKevich.ITBcamp0824.mappers;

import com.PolKevich.ITBcamp0824.dto.ProjectRequest;
import com.PolKevich.ITBcamp0824.dto.UserRequest;
import com.PolKevich.ITBcamp0824.dto.UserResponse;
import com.PolKevich.ITBcamp0824.dto.UserToProjectRequest;
import com.PolKevich.ITBcamp0824.dto.UserToProjectResponse;
import com.PolKevich.ITBcamp0824.model.Project;
import com.PolKevich.ITBcamp0824.model.User;
import com.PolKevich.ITBcamp0824.model.UserToProject;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-10T14:36:59+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class AppMapperImpl implements AppMapper {

    @Override
    public User convertRequest(UserRequest userRequest) {
        if ( userRequest == null ) {
            return null;
        }

        User user = new User();

        user.setLastname( userRequest.getLastname() );
        user.setFirstname( userRequest.getFirstname() );
        user.setPatronymic( userRequest.getPatronymic() );
        user.setEmail( userRequest.getEmail() );
        user.setPost( userRequest.getPost() );

        return user;
    }

    @Override
    public Project convertRequest(ProjectRequest projectRequest) {
        if ( projectRequest == null ) {
            return null;
        }

        Project project = new Project();

        project.setProjectName( projectRequest.getProjectName() );
        project.setProjectDescription( projectRequest.getProjectDescription() );

        return project;
    }

    @Override
    public UserToProject userToProjectRequest(UserToProjectRequest userToProjectRequest) {
        if ( userToProjectRequest == null ) {
            return null;
        }

        UserToProject userToProject = new UserToProject();

        userToProject.setProjectId( userToProjectRequest.getProjectId() );
        userToProject.setUserId( userToProjectRequest.getUserId() );

        return userToProject;
    }

    @Override
    public UserToProjectResponse userToProjectResponse(Project project, List<UserResponse> users) {
        if ( project == null && users == null ) {
            return null;
        }

        UserToProjectResponse userToProjectResponse = new UserToProjectResponse();

        if ( project != null ) {
            userToProjectResponse.setProjectName( project.getProjectName() );
            userToProjectResponse.setProjectDescription( project.getProjectDescription() );
        }
        List<UserResponse> list = users;
        if ( list != null ) {
            userToProjectResponse.setUsers( new ArrayList<UserResponse>( list ) );
        }

        return userToProjectResponse;
    }

    @Override
    public UserResponse userToUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setFullname( user.getLastname() + " " + user.getFirstname() + " " + user.getPatronymic() );

        return userResponse;
    }

    @Override
    public List<UserResponse> usersToUserResponses(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserResponse> list = new ArrayList<UserResponse>( users.size() );
        for ( User user : users ) {
            list.add( userToUserResponse( user ) );
        }

        return list;
    }
}
