package com.sacidpak.clients.depot;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("depot")
public interface DepotClient {

    @GetMapping(path = "api/v1/depot/{id}")
    ResponseEntity<DepotResponse> getById(@PathVariable("id") Long id);

    @GetMapping(path = "api/v1/depot/main-depot")
    ResponseEntity<DepotResponse> getMainDepot();

    @GetMapping("api/v1/depot/distance/from/{fromId}/to/{toId}")
    ResponseEntity<DepotDistanceResponse> getDistance(@PathVariable("fromId") Long fromId, @PathVariable("toId") Long toId);

}
