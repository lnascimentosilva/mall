package com.mall.app.store.resources;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
import com.mall.app.store.exception.StoreNotFoundException;
import com.mall.app.store.model.Store;
import com.mall.app.store.services.StoreServices;

@Path("/stores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StoreResources {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private static final String RESOURCE = "store";

	@Inject
	private StoreServices storeServices;

	@GET
	public Response findAll() {
		List<Store> stores = storeServices.findAll();
		return Response.ok(stores).build();
	}

	@POST
	public Response add(Store store) {
		Response response = null;

		try {
			if (store != null) {
				store = storeServices.add(store);
				response = ResponseBuilder.getCreated(store.getId());
			} else {
				logger.error("The body may not be empty");
				response = ResponseBuilder.getEmptyBody(RESOURCE);
			}
		} catch (FieldNotValidException e) {
			logger.error("The field \"{}\" of the store is not valid", e.getFieldName(), e);
			response = ResponseBuilder.getInvalidField(RESOURCE, e);
		} catch (SegmentNotFoundException e) {
			logger.error("Segment not found for store", e);
			response = ResponseBuilder.getNotFoundField(RESOURCE, "segments");
		}

		return response;
	}

	@PUT
	@Path("/{id}")
	public Response update(@PathParam("id") Long id, Store store) {

		Response response = null;
		try {
			if (store != null) {
				store.setId(id);
				storeServices.update(store);
				response = Response.noContent().build();
			} else {
				logger.error("The body may not be empty");
				response = ResponseBuilder.getEmptyBody(RESOURCE);
			}
		} catch (FieldNotValidException e) {
			logger.error("The field \"{}\" of the store is not valid", e.getFieldName(), e);
			response = ResponseBuilder.getInvalidField(RESOURCE, e);
		} catch (StoreNotFoundException e) {
			logger.error("No store found for the given id \"{}\"", id, e);
			response = ResponseBuilder.getNotFound(RESOURCE);
		} catch (SegmentNotFoundException e) {
			logger.error("Segment not found for store", e);
			response = ResponseBuilder.getNotFoundField(RESOURCE, "segments");
		}

		return response;
	}

	@GET
	@Path("/{id}")
	public Response findById(@PathParam("id") Long id) {
		Response response = null;

		try {
			Store store = storeServices.findById(id);
			response = Response.ok(store).build();
		} catch (StoreNotFoundException e) {
			logger.error("No store found for the given id \"{}\"", id, e);
			response = ResponseBuilder.getNotFound(RESOURCE);
		}

		return response;
	}

	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") Long id) {
		Response response = null;

		try {
			storeServices.delete(id);
			response = Response.noContent().build();
		} catch (StoreNotFoundException e) {
			logger.error("No store found for the given id \"{}\"", id, e);
			response = ResponseBuilder.getNotFound(RESOURCE);
		}

		return response;
	}

}
