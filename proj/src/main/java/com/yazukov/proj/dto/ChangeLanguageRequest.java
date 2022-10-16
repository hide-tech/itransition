package com.yazukov.proj.dto;

import com.yazukov.proj.domain.help.Language;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangeLanguageRequest {
    private Language language;
}
