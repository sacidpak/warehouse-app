package com.sacidpak.depot.controller;

import com.sacidpak.clients.depot.DepotDistanceResponse;
import com.sacidpak.common.controller.BaseController;
import com.sacidpak.depot.dto.DepotDTO;
import com.sacidpak.depot.service.IDepotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/depot")
public class DepotController extends BaseController<IDepotService, DepotDTO> {

    @GetMapping("/main-depot")
    public ResponseEntity<DepotDTO> getMainDepot() {
        return ResponseEntity.ok(entityService.getMainDepot());
    }

    @GetMapping("/distance/from/{fromId}/to/{toId}")
    public ResponseEntity<DepotDistanceResponse> getDistance(@PathVariable("fromId") Long fromId, @PathVariable("toId") Long toId) {
        return ResponseEntity.ok(entityService.getDistance(fromId,toId));
    }
}
