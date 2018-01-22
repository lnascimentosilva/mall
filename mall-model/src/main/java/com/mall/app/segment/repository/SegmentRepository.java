package com.mall.app.segment.repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.mall.app.common.repository.GenericRepository;
import com.mall.app.segment.model.Segment;

@Stateless
public class SegmentRepository extends GenericRepository<Segment> {

	@PersistenceContext
	EntityManager em;

	@Override
	protected Class<Segment> getPersistentClass() {
		return Segment.class;
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
