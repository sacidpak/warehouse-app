package com.sacidpak.depot.service;

import com.sacidpak.clients.depot.DepotDistanceResponse;
import com.sacidpak.common.service.IBaseEntityService;
import com.sacidpak.depot.domain.Depot;
import com.sacidpak.depot.dto.DepotDTO;

public interface IDepotService extends IBaseEntityService<Depot, DepotDTO,Long> {
    DepotDTO getMainDepot();

    DepotDistanceResponse getDistance(Long fromId, Long toId);
}
