package com.PolKevich.ITBcamp0824.repository;

import com.PolKevich.ITBcamp0824.model.UserToProject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserToProjectRepository extends JpaRepository<UserToProject, Long> {
    Page<UserToProject> findAll(Pageable pageable);

}
