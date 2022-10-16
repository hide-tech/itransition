package com.yazukov.proj.dto;

import com.yazukov.proj.domain.help.Theme;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CollectionDto {
    private Long id;
    private String name;
    private String description;
    private Theme theme;
    private String photoUri;
}
