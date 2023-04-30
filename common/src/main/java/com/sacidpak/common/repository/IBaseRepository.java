package com.sacidpak.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface IBaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

    default T findOne(ID id){
        return findById(id).orElse(null);
    };

    List<T> save(Iterable<T> entities);

}
