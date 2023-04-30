package com.sacidpak.product.controller;

import com.sacidpak.clients.product.DepotCloseResponse;
import com.sacidpak.product.dto.ProductDepotDTO;
import com.sacidpak.product.dto.ProductTransferDTO;
import com.sacidpak.product.service.IProductDepotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/product-depot")
public class ProductDepotController {

    @Autowired
    private IProductDepotService productDepotService;

    @PostMapping("/stock")
    public ResponseEntity<Void> stockProduct(@RequestBody ProductDepotDTO productDepotDTO){
        productDepotService.stock(productDepotDTO);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/transfer")
    public ResponseEntity<Void> transferProduct(@RequestBody ProductTransferDTO productTransferDTO){
        productDepotService.transfer(productTransferDTO);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/close/{depotId}")
    public ResponseEntity<DepotCloseResponse> depotClose(@PathVariable("depotId") Long depotId){
        return ResponseEntity.ok(productDepotService.transferFordepotClose(depotId));
    }
}
