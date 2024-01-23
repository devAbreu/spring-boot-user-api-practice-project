package com.dev.project.userapiproject.application;

import com.dev.project.userapiproject.domain.User;
import org.json.JSONObject;

import java.util.Map;

public interface UserService {
    User saveUser(User user);
    Iterable<User> getAllUsers();
    User getUserById(long id);
    User deleteUserById(long id);
    Map<String, Object> updateUserById(long id, User user);
}
