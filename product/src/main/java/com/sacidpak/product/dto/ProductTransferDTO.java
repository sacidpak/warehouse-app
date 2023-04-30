package com.sacidpak.product.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ProductTransferDTO {
    @NotNull
    private Long productId;

    @NotNull
    private Long fromDepotId;

    @NotNull
    private Long toDepotId;

    @NotNull
    private Integer transferAmount;
}
