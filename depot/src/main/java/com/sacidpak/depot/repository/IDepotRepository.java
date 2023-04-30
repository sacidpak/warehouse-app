package com.sacidpak.depot.repository;

import com.sacidpak.common.repository.IBaseRepository;
import com.sacidpak.depot.domain.Depot;
import org.springframework.data.jpa.repository.Query;

public interface IDepotRepository extends IBaseRepository<Depot,Long> {

    @Query("select d from Depot d where d.type = com.sacidpak.depot.enums.DepotType.MAIN_DEPOT")
    Depot getMainDepot();
}
