package com.training.javaee.jaxws.webservices;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 *
 * @author training
 */
@WebService(name = "HolaMundoService", 
        serviceName = "HolaMundoWS")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class WebserviceHolaMundo {

    
    @WebMethod(operationName = "saludar")
    public String saludar(@WebParam(name = "sujeto") String sujeto) {
        String s = "Desconocido";
        if(sujeto != null && !sujeto.trim().isEmpty()){
            s = sujeto;
        }
        return "Hola Mundo "+s;
    }
  
}
