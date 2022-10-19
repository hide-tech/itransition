package com.yazukov.proj.service;

import com.yazukov.proj.domain.User;
import com.yazukov.proj.domain.help.*;
import com.yazukov.proj.dto.UserDto;
import com.yazukov.proj.exception.UserAlreadyExistException;
import com.yazukov.proj.exception.UserNotFoundException;
import com.yazukov.proj.mapper.UserMapper;
import com.yazukov.proj.repository.UserRepository;
import com.yazukov.proj.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User projectUser =  userRepository.findByEmail(username)
                .orElseThrow(()->new UsernameNotFoundException("User not found"));
        return new CustomUserDetails(projectUser);
    }

    public void processLogin(String email) throws UserAlreadyExistException {
        User existUser = userRepository.findByEmail(email).get();
        if (existUser != null) throw new UserAlreadyExistException("");
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setProvider(Provider.GOOGLE);
        newUser.setLanguage(Language.ENGLISH);
        newUser.setRole(Role.ROLE_USER);
        newUser.setStatus(Status.ACTIVE);
        newUser.setSkin(Skin.DARK);

        userRepository.save(newUser);
    }

    public List<UserDto> findAllUsers(){
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = users.stream()
                .map(userMapper::userToUserDto)
                .collect(Collectors.toList());
        return userDtos;
    }

    public UserDto findUserById(Long id) throws UserNotFoundException {
        return userMapper.userToUserDto(userRepository.findById(id).orElseThrow(()->
                new UserNotFoundException("User not found")));
    }

    public UserDto createNewUser(UserDto userDto) throws UserAlreadyExistException {
        Optional<User> existUser = userRepository.findByEmail(userDto.getEmail());
        if (existUser.isPresent()) throw new UserAlreadyExistException("");
        User newUser = new User();
        newUser.setEmail(userDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        newUser.setProvider(Provider.LOCAL);
        newUser.setLanguage(Language.ENGLISH);
        newUser.setRole(Role.ROLE_USER);
        newUser.setStatus(Status.ACTIVE);
        newUser.setSkin(Skin.DARK);
        User user = userRepository.save(newUser);
        return userMapper.userToUserDto(user);
    }

    @Transactional
    public UserDto updateUser(UserDto userDto) throws UserNotFoundException {
        User user = userRepository.findById(userDto.getId()).orElseThrow(()->
                new UserNotFoundException("User not found"));
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setSkin(userDto.getSkin());
        user.setLanguage(userDto.getLanguage());
        User updatedUser = userRepository.save(user);
        return userMapper.userToUserDto(updatedUser);
    }

    @Transactional
    public boolean banUser(Long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(()->
                new UserNotFoundException("User not found"));
        user.setStatus(Status.BANNED);
        userRepository.save(user);
        return true;
    }

    @Transactional
    public boolean unbanUser(Long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(()->
                new UserNotFoundException("User not found"));
        user.setStatus(Status.ACTIVE);
        userRepository.save(user);
        return true;
    }

    @Transactional
    public boolean changeLanguage(Long userId, Language language) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(()->
                new UserNotFoundException("User not found"));
        user.setLanguage(language);
        userRepository.save(user);
        return true;
    }

    @Transactional
    public boolean changeSkin(Long userId, Skin skin) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(()->
                new UserNotFoundException("User not found"));
        user.setSkin(skin);
        userRepository.save(user);
        return true;
    }

    @Transactional
    public boolean setAdmin(Long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(()->
                new UserNotFoundException("User not found"));
        user.setRole(Role.ROLE_ADMIN);
        userRepository.save(user);
        return true;
    }

    @Transactional
    public boolean setUser(Long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(()->
                new UserNotFoundException("User not found"));
        user.setRole(Role.ROLE_USER);
        userRepository.save(user);
        return true;
    }

    public UserDto getUserByUsername(String username) {
        User user = userRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("Not found"));
        return userMapper.userToUserDto(user);
    }
}
