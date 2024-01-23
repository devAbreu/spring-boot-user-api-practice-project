package com.dev.project.userapiproject.application.impl;

import com.dev.project.userapiproject.application.UserService;
import com.dev.project.userapiproject.domain.User;
import com.dev.project.userapiproject.infraestructure.repository.UserRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public Iterable<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User getUserById(long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    @Override
    public User deleteUserById(long id) {
        Optional<User> optionalUser = this.userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User userToDelete = optionalUser.get();
            this.userRepository.delete(userToDelete);
            return userToDelete;
        }
        return null;
    }

    @Override
    public Map<String, Object> updateUserById(long id, User user) {
        Optional<User> optionalUser = this.userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User userToUpdate = optionalUser.get();
            if (user.getAge() != null) {
                userToUpdate.setAge(user.getAge());
            }

            if (user.getEmail() != null) {
                userToUpdate.setEmail(user.getEmail());
            }
            if (user.getName() != null) {
                userToUpdate.setName(user.getName());
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", true);
            jsonObject.put("message", String.format("User with id %d was updated", id));

            this.userRepository.save(userToUpdate);
            return jsonObject.toMap();
        }else{
            return null;
        }

    }


}
