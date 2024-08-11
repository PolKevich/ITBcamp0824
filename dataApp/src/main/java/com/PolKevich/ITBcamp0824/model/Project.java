package com.PolKevich.ITBcamp0824.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "project")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode()
@ToString()
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "projectname")
    String projectName;

    @Column(name = "projectdescription")
    String projectDescription;
}
