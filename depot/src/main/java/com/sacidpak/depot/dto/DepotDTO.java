package com.sacidpak.depot.dto;

import com.sacidpak.common.dto.BaseEntityDTO;
import com.sacidpak.depot.enums.DepotStatus;
import com.sacidpak.depot.enums.DepotType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepotDTO extends BaseEntityDTO {
    private String name;
    private DepotType type;
    private String city;
    private Double latitude;
    private Double longitude;
    private DepotStatus status;
    private String costCenter;
}
