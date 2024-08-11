package com.PolKevich.ITBcamp0824.repository;

import com.PolKevich.ITBcamp0824.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
