package com.sacidpak.depot.service;

import com.sacidpak.clients.depot.DepotDistanceResponse;
import com.sacidpak.clients.product.DepotCloseResponse;
import com.sacidpak.clients.product.ProductDepotClient;
import com.sacidpak.common.exception.BusinessException;
import com.sacidpak.common.service.BaseServiceImpl;
import com.sacidpak.depot.util.DistanceUtil;
import com.sacidpak.depot.domain.Depot;
import com.sacidpak.depot.dto.DepotDTO;
import com.sacidpak.depot.enums.DepotStatus;
import com.sacidpak.depot.enums.DepotType;
import com.sacidpak.depot.repository.IDepotRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import static com.sacidpak.depot.enums.DepotValidation.*;

@Service
@AllArgsConstructor
public class DepotServiceImpl extends BaseServiceImpl<Depot, DepotDTO,Long> implements IDepotService {

    private final IDepotRepository depotRepository;

    private final ProductDepotClient productDepotClient;

    @Override
    protected void validateSave(DepotDTO dto) {
        if(dto.getType().equals(DepotType.MAIN_DEPOT)){
            Depot mainDepot = depotRepository.getMainDepot();
            if (mainDepot != null)
                throw new BusinessException(MAIN_DEPOT_SHOULD_BE_ONLY_ONE);
        }
    }

    @Override
    protected void validateUpdate(DepotDTO dto) {
        Depot depot = depotRepository.findOne(dto.getId());

        if(depot == null){
            throw new BusinessException(DEPOT_NOT_FOUND);
        }

        if(depot.getStatus().equals(DepotStatus.OPEN) && dto.getStatus().equals(DepotStatus.CLOSE)){
            if(depot.getType().equals(DepotType.MAIN_DEPOT))
                throw new BusinessException(MAIN_DEPOT_CANNOT_CLOSE);

            DepotCloseResponse closeResponse = productDepotClient.depotClose(dto.getId()).getBody();
            if (closeResponse != null && !closeResponse.isSuccess()){
                throw new BusinessException(DEPOT_STATUS_UPDATE_NOT_SUCCESS);
            }
        }

    }

    @Override
    public DepotDTO getMainDepot() {
        return new ModelMapper().map(depotRepository.getMainDepot(),DepotDTO.class);
    }

    @Override
    public DepotDistanceResponse getDistance(Long fromId, Long toId) {
        Depot fromDepot = depotRepository.findOne(fromId);
        Depot toDepot = depotRepository.findOne(toId);

        if(fromDepot == null || toDepot == null){
            throw new BusinessException(DEPOT_NOT_FOUND);
        }

        Double distance = DistanceUtil.calculateDistance(fromDepot, toDepot);

        return new DepotDistanceResponse(distance);
    }


}
