package com.sacidpak.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class LightEntityDTO {
    private Long id;
    private Long version;
    private String name;
}
