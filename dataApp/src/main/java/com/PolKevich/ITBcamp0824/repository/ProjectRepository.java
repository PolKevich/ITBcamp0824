package com.PolKevich.ITBcamp0824.repository;

import com.PolKevich.ITBcamp0824.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
}
