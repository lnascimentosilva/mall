package com.mall.app.common.resource;

import javax.ws.rs.core.Response;

import com.mall.app.common.exception.FieldNotValidException;

public final class ResponseBuilder {

	private ResponseBuilder() {
	}

	public static Response getCreated(Long id) {
		String entity = String.format("{ \"id\": %s }", id);
		return Response.status(Response.Status.CREATED).entity(entity).build();
	}

	public static Response getInvalidField(String resource, FieldNotValidException e) {
		String entity = String.format("{ \"errorIdentification\": \"%s.invalidField.%s\", \"errorDescription\": \"%s\" }", resource, e.getFieldName(), e.getMessage());
		return Response.status(Response.Status.BAD_REQUEST).entity(entity).build();
	}
	
	public static Response getEmptyBody(String resource) {
		String entity = String.format("{ \"errorIdentification\": \"%s.emptyBody\", \"errorDescription\": \"Body may not be empty\" }", resource);
		return Response.status(Response.Status.BAD_REQUEST).entity(entity).build();
	}
	
	public static Response getNotFound(String resource) {
		String entity = String.format("{ \"errorIdentification\": \"%s.notFound\", \"errorDescription\": \"%s not found\" }", resource, resource);
		return Response.status(Response.Status.NOT_FOUND).entity(entity).build();	
	}
	
	public static Response getNotFoundField(String resource, String field) {
		String entity = String.format("{ \"errorIdentification\": \"%s.invalidField.%s\", \"errorDescription\": \"%s not found for %s\" }", resource, field, field, resource);
		return Response.status(Response.Status.BAD_REQUEST).entity(entity).build();	
	}
	
	public static Response getServerError() {
		String entity = "{ \"errorIdentification\": \"serverError\", \"errorDescription\": \"The server could not proccess the request\" }";
		return Response.serverError().entity(entity).build();	
	}

}
