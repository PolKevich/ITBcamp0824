package com.PolKevich.ITBcamp0824.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode()
@ToString()
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "email")
    private String email;

    @Column(name = "post")
    private String post;
}
