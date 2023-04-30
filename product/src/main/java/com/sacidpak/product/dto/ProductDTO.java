package com.sacidpak.product.dto;

import com.sacidpak.common.dto.BaseEntityDTO;
import com.sacidpak.product.enums.ProductUnitType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDTO extends BaseEntityDTO {
    private String name;
    private String sku;
    private String barcode;
    private ProductTypeDTO productType;
    private Boolean isFrozen;
    private BigDecimal price;
    private ProductUnitType unitType;
}
