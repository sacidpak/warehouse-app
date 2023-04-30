package com.sacidpak.product.repository;

import com.sacidpak.common.repository.IBaseRepository;
import com.sacidpak.product.domain.ProductDepot;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductDepotRepository extends IBaseRepository<ProductDepot,Long> {

    List<ProductDepot> findProductDepotByDepotId(Long depotId);

    @Query("select pd from ProductDepot pd where pd.depotId = :depotId and pd.product.id = :productId")
    ProductDepot findProductDepot(@Param("depotId") Long depotId, @Param("productId") Long productId);
}
