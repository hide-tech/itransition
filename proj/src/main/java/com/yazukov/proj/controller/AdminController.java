package com.yazukov.proj.controller;

import com.yazukov.proj.exception.UserNotFoundException;
import com.yazukov.proj.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/ban/{userId}")
    public ResponseEntity<?> banUser(@PathVariable("userId") Long userId) throws UserNotFoundException {
        return ResponseEntity.ok(userService.banUser(userId));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/unban/{userId}")
    public ResponseEntity<?> unbanUser(@PathVariable("userId") Long userId) throws UserNotFoundException {
        return ResponseEntity.ok(userService.unbanUser(userId));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/set-admin/{userId}")
    public ResponseEntity<?> setAdminUser(@PathVariable("userId") Long userId) throws UserNotFoundException {
        return ResponseEntity.ok(userService.setAdmin(userId));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/set-user/{userId}")
    public ResponseEntity<?> setUserAdmin (@PathVariable("userId") Long userId) throws UserNotFoundException {
        return ResponseEntity.ok(userService.setUser(userId));
    }
}
