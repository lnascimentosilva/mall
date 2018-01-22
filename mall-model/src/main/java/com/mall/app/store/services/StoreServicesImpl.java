package com.mall.app.store.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

import com.mall.app.common.utils.ValidationUtils;
import com.mall.app.segment.model.Segment;
import com.mall.app.segment.services.SegmentServices;
import com.mall.app.store.exception.StoreNotFoundException;
import com.mall.app.store.model.Store;
import com.mall.app.store.repository.StoreRepository;

@Stateless
public class StoreServicesImpl implements StoreServices {

	@Inject
	private Validator validator;

	@Inject
	private StoreRepository storeRepository;
	
	@Inject
	private SegmentServices segmentServices;

	@Override
	public Store add(Store store) {
		validate(store);
		
		checkSegmentsAndSetThemOnStore(store);

		return storeRepository.add(store);
	}

	private void validate(Store store) {
		ValidationUtils.validateEntityFields(validator, store);
	}

	@Override
	public void update(Store store) {
		this.validate(store);

		if (!storeRepository.existsById(store.getId())) {
			throw new StoreNotFoundException();
		}
		
		checkSegmentsAndSetThemOnStore(store);

		storeRepository.update(store);
	}

	@Override
	public Store findById(Long id) {
		Store store = storeRepository.findById(id);
		if (store == null) {
			throw new StoreNotFoundException();
		}
		return store;
	}

	@Override
	public List<Store> findAll() {
		return storeRepository.findAll("name");
	}

	@Override
	public void delete(Long id){
		storeRepository.delete(this.findById(id));	
	}
	
	private void checkSegmentsAndSetThemOnStore(Store store) {
		List<Segment> newSegmentList = new ArrayList<>();
		for (Segment segment : store.getSegments()) {
			Segment segmentExistent = segmentServices.findById(segment.getId());
			newSegmentList.add(segmentExistent);
		}
		store.setSegments(newSegmentList);
	}

}
