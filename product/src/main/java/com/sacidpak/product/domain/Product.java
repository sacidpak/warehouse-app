package com.sacidpak.product.domain;

import com.sacidpak.common.domain.BaseEntity;
import com.sacidpak.product.dto.ProductDTO;
import com.sacidpak.product.enums.ProductUnitType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Where(clause = "deleted = 0")
public class Product extends BaseEntity<Product, ProductDTO> {

    private String name;

    private String sku;

    private String barcode;

    @ManyToOne
    @JoinColumn(name = "product_type_id")
    private ProductType productType;

    private Boolean isFrozen;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private ProductUnitType unitType;

}
