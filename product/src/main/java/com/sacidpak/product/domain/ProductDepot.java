package com.sacidpak.product.domain;

import com.sacidpak.common.domain.BaseEntity;
import com.sacidpak.product.dto.ProductDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Where(clause = "deleted = 0")
public class ProductDepot extends BaseEntity<ProductDepot, ProductDTO> {

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "depot_id")
    private Long depotId;

    private Integer amount;
}
