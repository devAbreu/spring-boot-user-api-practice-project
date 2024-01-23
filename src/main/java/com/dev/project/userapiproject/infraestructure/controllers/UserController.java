package com.dev.project.userapiproject.infraestructure.controllers;

import com.dev.project.userapiproject.application.UserService;
import com.dev.project.userapiproject.domain.User;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api/users")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public Map<String, Object> save(@RequestBody User user) {
        //return ResponseEntity.ok(user);
        Map<String, Object> jsonObject = new HashMap<>();
        try {
            User userCreated = this.userService.saveUser(user);
            if (userCreated == null) {
                jsonObject.put("status", false);
                jsonObject.put("message", "Can't create user");
                return jsonObject;
            }

            jsonObject.put("status", true);
            jsonObject.put("message", "User created");
            return jsonObject;
            //return new ResponseEntity<>(userCreated, HttpStatus.OK);
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            jsonObject.put("status", true);
            jsonObject.put("message", "Bad request");
            return jsonObject;
        }

    }

    @GetMapping
    public Map<String, Object> getAllUser() {
        Map<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("status", true);
        jsonObject.put("result", this.userService.getAllUsers());
        return jsonObject;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") long id) {
        return this.userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public User deleteUserById(@PathVariable("id") long id) {
        return this.userService.deleteUserById(id);
    }

    @PutMapping("/{id}")
    public Map<String, Object> updateUserById(@PathVariable("id") long id, @RequestBody User user) {
        Map<String, Object> jsonObject = new HashMap<>();
        if (this.userService.updateUserById(id, user) != null) {
            return this.userService.updateUserById(id, user);

        }
        jsonObject.put("status", false);
        jsonObject.put("message", "User not found");
        return jsonObject;
    }
}
