package com.yazukov.proj.controller;

import com.yazukov.proj.dto.ChangeLanguageRequest;
import com.yazukov.proj.dto.ChangeSkinRequest;
import com.yazukov.proj.dto.UserDto;
import com.yazukov.proj.exception.UserAlreadyExistException;
import com.yazukov.proj.exception.UserNotFoundException;
import com.yazukov.proj.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Long userId)
            throws UserNotFoundException {
        return ResponseEntity.ok(userService.findUserById(userId));
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/name/{username}")
    public ResponseEntity<UserDto> getUserByName(@PathVariable("username") String username)
            throws UserNotFoundException {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @PostMapping
    public ResponseEntity<?> createNewUser(@RequestBody UserDto userDto) throws UserAlreadyExistException {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createNewUser(userDto));
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PatchMapping
    public ResponseEntity<?> updateUser(@RequestBody UserDto userDto) throws UserNotFoundException {
        return ResponseEntity.ok(userService.updateUser(userDto));
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PostMapping("/{userId}/skin")
    public ResponseEntity<?> changeSkin(@PathVariable("userId") Long userId,
                                        @RequestBody ChangeSkinRequest changeSkinRequest)
            throws UserNotFoundException {
        return ResponseEntity.ok(userService.changeSkin(userId, changeSkinRequest.getSkin()));
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PostMapping("/{userId}/language")
    public ResponseEntity<?> changeLanguage(@PathVariable("userId") Long userId,
                                            @RequestBody ChangeLanguageRequest changeLanguageRequest)
            throws UserNotFoundException {
        return ResponseEntity.ok(userService.changeLanguage(userId, changeLanguageRequest.getLanguage()));
    }
}
