package com.yazukov.proj.dto;

import com.yazukov.proj.domain.help.Language;
import com.yazukov.proj.domain.help.Role;
import com.yazukov.proj.domain.help.Skin;
import com.yazukov.proj.domain.help.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private Status status;
    private Role role;
    private Language language;
    private Skin skin;
}
