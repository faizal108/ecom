package com.faizal.ecom.dao;

import jakarta.persistence.EntityManager;

public class GenericDao<T>{

    private final Class<T> entityClass;
    private final EntityManager entityManager;

    public GenericDao(Class<T> entityClass,EntityManager entityManager){
        this.entityClass = entityClass;
        this.entityManager = entityManager;
    }


}
