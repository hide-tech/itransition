package com.yazukov.proj.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDtoComp {
    private Long id;
    private String name;
    private Map<String, String> fields;

    public ItemDtoComp(Long id, String name, Object object){
        this.id = id;
        this.name = name;
        this.fields = (Map<String, String>) object;
    }
}
