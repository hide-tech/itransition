package com.yazukov.proj.dto;

import com.yazukov.proj.domain.help.Skin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangeSkinRequest {
    private Skin skin;
}
