package com.mall.app.store.services;

import java.util.List;

import javax.ejb.Local;

import com.mall.app.common.exception.FieldNotValidException;
import com.mall.app.segment.exception.SegmentNotFoundException;
import com.mall.app.store.exception.StoreNotFoundException;
import com.mall.app.store.model.Store;

@Local
public interface StoreServices {

	Store add(Store Store) throws FieldNotValidException, SegmentNotFoundException;

	void update(Store Store) throws FieldNotValidException, SegmentNotFoundException, StoreNotFoundException;

	Store findById(Long id) throws StoreNotFoundException;

	List<Store> findAll();

	void delete(Long id) throws StoreNotFoundException;

}
