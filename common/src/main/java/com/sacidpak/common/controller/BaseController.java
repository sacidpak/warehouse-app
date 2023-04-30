package com.sacidpak.common.controller;

import com.sacidpak.common.service.IBaseEntityService;
import com.sacidpak.common.dto.BaseEntityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

public abstract class BaseController<S extends IBaseEntityService, D extends BaseEntityDTO> {

    @Autowired
    protected S entityService;

    @GetMapping("/{id}")
    public ResponseEntity<D> getById(@PathVariable Long id){
        return ResponseEntity.ok((D)entityService.getById(id));
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<D>> getListAll (){
        return ResponseEntity.ok(entityService.getAll());
    }

    @PostMapping
    public ResponseEntity<Long> save (@RequestBody D dto){
        return ResponseEntity.ok((Long) entityService.save(dto));
    }

    @PutMapping
    public void update (@RequestBody D dto){
        entityService.update(dto);
    }

    @DeleteMapping("/{id}")
    public void delete (@RequestParam Long id, @RequestParam Integer version){
        entityService.delete(id,version);
    }

}
