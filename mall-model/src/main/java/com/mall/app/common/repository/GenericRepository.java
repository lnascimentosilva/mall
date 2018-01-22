package com.mall.app.common.repository;

import java.util.List;

import javax.persistence.EntityManager;

public abstract class GenericRepository<T> {

	protected abstract Class<T> getPersistentClass();

	protected abstract EntityManager getEntityManager();

	public T add(T entity) {
		getEntityManager().persist(entity);
		return entity;
	}

	public T findById(Long id) {
		if (id == null) {
			return null;
		}
		return getEntityManager().find(getPersistentClass(), id);
	}

	public void update(T entity) {
		getEntityManager().merge(entity);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll(String orderField) {
		return getEntityManager().createQuery(
				String.format("Select e From %s e Order by e.%s", getPersistentClass().getSimpleName(), orderField))
				.getResultList();
	}

	public boolean existsById(Long id) {

		return getEntityManager()
				.createQuery(String.format("Select 1 From %s e where e.id = :id", getPersistentClass().getSimpleName()))
				.setParameter("id", id)
				.setMaxResults(1)
				.getResultList().size() > 0;
	}

	public void delete(T entity) {
		getEntityManager().remove(entity);
	}
}	