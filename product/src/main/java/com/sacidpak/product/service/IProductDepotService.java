package com.sacidpak.product.service;

import com.sacidpak.clients.product.DepotCloseResponse;
import com.sacidpak.common.service.IBaseEntityService;
import com.sacidpak.product.domain.ProductDepot;
import com.sacidpak.product.dto.ProductDepotDTO;
import com.sacidpak.product.dto.ProductTransferDTO;

public interface IProductDepotService extends IBaseEntityService<ProductDepot, ProductDepotDTO,Long> {
    void stock(ProductDepotDTO productDepotDTO);
    void transfer(ProductTransferDTO productTransferDTO);

    DepotCloseResponse transferFordepotClose(Long depotId);
}
