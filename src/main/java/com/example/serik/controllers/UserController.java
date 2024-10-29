package com.example.serik.controllers;

import com.example.serik.entity.UserEntity;
import com.example.serik.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @PostMapping
    public ResponseEntity registration(@RequestBody UserEntity user) {
        try {
            userRepo.save(user);
            return ResponseEntity.ok("Пользователь добавлен!");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Сервак не запущен!");
        }
    }

    @GetMapping
    public ResponseEntity getUser(@RequestBody Map<String, Long> requestBody) {
        try {
            Long userId = requestBody.get("user_id"); // Извлекаем user_id из JSON
            Optional<UserEntity> user = userRepo.findById(userId); // Ищем пользователя по ID

            if (user.isPresent()) {
                return ResponseEntity.ok(user.get()); // Возвращаем найденного пользователя
            } else {
                return ResponseEntity.notFound().build(); // Если пользователь не найден, возвращаем 404
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Сервак не запущен!");
        }
    }
}