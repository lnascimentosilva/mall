package com.mall.app.segment.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

import com.mall.app.common.utils.ValidationUtils;
import com.mall.app.segment.exception.SegmentNotFoundException;
import com.mall.app.segment.model.Segment;
import com.mall.app.segment.repository.SegmentRepository;

@Stateless
public class SegmentServicesImpl implements SegmentServices {

	@Inject
	private Validator validator;

	@Inject
	private SegmentRepository segmentRepository;

	@Override
	public Segment add(Segment segment) {
		validate(segment);

		return segmentRepository.add(segment);
	}

	private void validate(Segment segment) {
		ValidationUtils.validateEntityFields(validator, segment);
	}

	@Override
	public void update(Segment segment) {
		this.validate(segment);

		if (!segmentRepository.existsById(segment.getId())) {
			throw new SegmentNotFoundException();
		}

		segmentRepository.update(segment);
	}

	@Override
	public Segment findById(Long id) {
		Segment segment = segmentRepository.findById(id);
		if (segment == null) {
			throw new SegmentNotFoundException();
		}
		return segment;
	}

	@Override
	public List<Segment> findAll() {
		return segmentRepository.findAll("name");
	}

}
