package com.sacidpak.product.dto;

import com.sacidpak.common.dto.BaseEntityDTO;
import com.sacidpak.common.dto.LightEntityDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ProductDepotDTO extends BaseEntityDTO {
    @NotNull
    private LightEntityDTO product;

    @NotNull
    private Long depotId;

    @NotNull
    private Integer amount;
}
