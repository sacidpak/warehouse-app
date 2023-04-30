package com.sacidpak.product.domain;

import com.sacidpak.common.domain.BaseEntity;
import com.sacidpak.product.dto.ProductTypeDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
@Where(clause = "deleted = 0")
public class ProductType extends BaseEntity<ProductType, ProductTypeDTO> {
    private String name;
}
