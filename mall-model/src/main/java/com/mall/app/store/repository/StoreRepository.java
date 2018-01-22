package com.mall.app.store.repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.mall.app.common.repository.GenericRepository;
import com.mall.app.store.model.Store;

@Stateless
public class StoreRepository extends GenericRepository<Store> {

	@PersistenceContext
	EntityManager em;

	@Override
	protected Class<Store> getPersistentClass() {
		return Store.class;
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
