package com.dev.project.userapiproject.infraestructure.repository;

import com.dev.project.userapiproject.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User getUserById(long id);
    User deleteUserById(long id);
}
