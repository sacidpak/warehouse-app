package com.sacidpak.depot.domain;

import com.sacidpak.common.domain.BaseEntity;
import com.sacidpak.depot.enums.DepotStatus;
import com.sacidpak.depot.enums.DepotType;
import com.sacidpak.depot.dto.DepotDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Where(clause = "deleted = 0")
public class Depot extends BaseEntity<Depot, DepotDTO> {

    @NotNull
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private DepotType type;

    @NotNull
    private String city;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    @NotNull
    @Enumerated(EnumType.STRING)
    private DepotStatus status;

    private String costCenter;
}
