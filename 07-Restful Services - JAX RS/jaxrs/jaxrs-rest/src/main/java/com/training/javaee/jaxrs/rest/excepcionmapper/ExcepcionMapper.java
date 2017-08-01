/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.training.javaee.jaxrs.rest.excepcionmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author training
 */
@Provider
public class ExcepcionMapper implements ExceptionMapper<Exception>{

    @Override
    public Response toResponse(Exception exception) {
        return Response
                .serverError()
                .entity("Ha ocurrido un error inesperado. "+exception.getMessage())
                .build();
    }
    
}
