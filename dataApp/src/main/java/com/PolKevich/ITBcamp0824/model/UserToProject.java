package com.PolKevich.ITBcamp0824.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_project")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode()
@ToString()
public class UserToProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_project_id")
    private Long id;

    @Column(name="project_id")
    private Long projectId;

    @Column(name="user_id")
    private Long userId;

}
