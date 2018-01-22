package com.mall.app.segment.resources;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mall.app.common.exception.FieldNotValidException;
import com.mall.app.common.resource.ResponseBuilder;
import com.mall.app.segment.exception.SegmentNotFoundException;
import com.mall.app.segment.model.Segment;
import com.mall.app.segment.services.SegmentServices;

@Path("/segments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SegmentResources {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private static final String RESOURCE = "segment";

	@Inject
	private SegmentServices segmentServices;

	@GET
	public Response findAll() {
		List<Segment> segments = segmentServices.findAll();
		return Response.ok(segments).build();
	}

	@POST
	public Response add(Segment segment) {
		Response response = null;

		try {
			if (segment != null) {
				segment = segmentServices.add(segment);
				response = ResponseBuilder.getCreated(segment.getId());
			} else {
				logger.error("The body may not be empty");
				response = ResponseBuilder.getEmptyBody(RESOURCE);
			}
		} catch (FieldNotValidException e) {
			logger.error("The field \"{}\" of the segment is not valid", e.getFieldName(), e);
			response = ResponseBuilder.getInvalidField(RESOURCE, e);
		}

		return response;
	}

	@PUT
	@Path("/{id}")
	public Response update(@PathParam("id") Long id, Segment segment) {

		Response response = null;
		try {
			if (segment != null) {
				segment.setId(id);
				segmentServices.update(segment);
				response = Response.noContent().build();
			} else {
				logger.error("The body may not be empty");
				response = ResponseBuilder.getEmptyBody(RESOURCE);
			}
		} catch (FieldNotValidException e) {
			logger.error("The field \"{}\" of the segment is not valid", e.getFieldName(), e);
			response = ResponseBuilder.getInvalidField(RESOURCE, e);
		} catch (SegmentNotFoundException e) {
			logger.error("No segment found for the given id \"{}\"", id, e);
			response = ResponseBuilder.getNotFound(RESOURCE);
		}

		return response;
	}

	@GET
	@Path("/{id}")
	public Response findById(@PathParam("id") Long id) {
		Response response = null;

		try {
			Segment segment = segmentServices.findById(id);
			response = Response.ok(segment).build();
		} catch (SegmentNotFoundException e) {
			logger.error("No segment found for the given id \"{}\"", id, e);
			response = ResponseBuilder.getNotFound(RESOURCE);
		}

		return response;
	}

}
