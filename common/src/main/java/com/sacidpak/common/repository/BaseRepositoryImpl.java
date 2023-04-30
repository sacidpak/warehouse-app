package com.sacidpak.common.repository;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoRepositoryBean
public class BaseRepositoryImpl<T, ID  extends Serializable> extends SimpleJpaRepository<T,ID> implements IBaseRepository<T,ID> {

    private EntityManager entityManager;

    public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.entityManager = em;
    }

    @Override
    public List<T> save(Iterable<T> entities) {
        return saveAll(entities);
    }
}
