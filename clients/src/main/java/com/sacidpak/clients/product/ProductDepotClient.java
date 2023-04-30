package com.sacidpak.clients.product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("product")
public interface ProductDepotClient {

    @GetMapping("api/v1/product-depot/close/{depotId}")
    ResponseEntity<DepotCloseResponse> depotClose(@PathVariable("depotId") Long depotId);
}
