package com.sacidpak.common.service;

import com.sacidpak.common.domain.BaseEntity;
import com.sacidpak.common.dto.BaseEntityDTO;

import java.util.List;

public interface IBaseEntityService<T extends BaseEntity, D extends BaseEntityDTO, ID> {

    ID save (D dto);

    void update(D dto);

    void delete(ID id, Integer version);

    D getById(ID id);

    List<D> getAll();
}
