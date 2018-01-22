package com.mall.app.segment.services;

import java.util.List;

import javax.ejb.Local;

import com.mall.app.common.exception.FieldNotValidException;
import com.mall.app.segment.exception.SegmentNotFoundException;
import com.mall.app.segment.model.Segment;

@Local
public interface SegmentServices {

	Segment add(Segment Segment) throws FieldNotValidException;

	void update(Segment Segment) throws FieldNotValidException, SegmentNotFoundException;

	Segment findById(Long id) throws SegmentNotFoundException;

	List<Segment> findAll();

}
