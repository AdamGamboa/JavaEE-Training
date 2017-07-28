
package com.training.javaee.jaxws.webservices.client.actorws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.training.javaee.jaxws.webservices.client.actorws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetActor_QNAME = new QName("http://webservices.jaxws.javaee.training.com/", "getActor");
    private final static QName _GetActorResponse_QNAME = new QName("http://webservices.jaxws.javaee.training.com/", "getActorResponse");
    private final static QName _Consultar_QNAME = new QName("http://webservices.jaxws.javaee.training.com/", "consultar");
    private final static QName _GuardarActorResponse_QNAME = new QName("http://webservices.jaxws.javaee.training.com/", "guardarActorResponse");
    private final static QName _GuardarActor_QNAME = new QName("http://webservices.jaxws.javaee.training.com/", "guardarActor");
    private final static QName _CrearActorResponse_QNAME = new QName("http://webservices.jaxws.javaee.training.com/", "crearActorResponse");
    private final static QName _Actor_QNAME = new QName("http://webservices.jaxws.javaee.training.com/", "actor");
    private final static QName _CrearActor_QNAME = new QName("http://webservices.jaxws.javaee.training.com/", "crearActor");
    private final static QName _ConsultarResponse_QNAME = new QName("http://webservices.jaxws.javaee.training.com/", "consultarResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.training.javaee.jaxws.webservices.client.actorws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Actor }
     * 
     */
    public Actor createActor() {
        return new Actor();
    }

    /**
     * Create an instance of {@link CrearActor }
     * 
     */
    public CrearActor createCrearActor() {
        return new CrearActor();
    }

    /**
     * Create an instance of {@link CrearActorResponse }
     * 
     */
    public CrearActorResponse createCrearActorResponse() {
        return new CrearActorResponse();
    }

    /**
     * Create an instance of {@link ConsultarResponse }
     * 
     */
    public ConsultarResponse createConsultarResponse() {
        return new ConsultarResponse();
    }

    /**
     * Create an instance of {@link GuardarActorResponse }
     * 
     */
    public GuardarActorResponse createGuardarActorResponse() {
        return new GuardarActorResponse();
    }

    /**
     * Create an instance of {@link GuardarActor }
     * 
     */
    public GuardarActor createGuardarActor() {
        return new GuardarActor();
    }

    /**
     * Create an instance of {@link Consultar }
     * 
     */
    public Consultar createConsultar() {
        return new Consultar();
    }

    /**
     * Create an instance of {@link GetActor }
     * 
     */
    public GetActor createGetActor() {
        return new GetActor();
    }

    /**
     * Create an instance of {@link GetActorResponse }
     * 
     */
    public GetActorResponse createGetActorResponse() {
        return new GetActorResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetActor }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.jaxws.javaee.training.com/", name = "getActor")
    public JAXBElement<GetActor> createGetActor(GetActor value) {
        return new JAXBElement<GetActor>(_GetActor_QNAME, GetActor.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetActorResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.jaxws.javaee.training.com/", name = "getActorResponse")
    public JAXBElement<GetActorResponse> createGetActorResponse(GetActorResponse value) {
        return new JAXBElement<GetActorResponse>(_GetActorResponse_QNAME, GetActorResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Consultar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.jaxws.javaee.training.com/", name = "consultar")
    public JAXBElement<Consultar> createConsultar(Consultar value) {
        return new JAXBElement<Consultar>(_Consultar_QNAME, Consultar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GuardarActorResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.jaxws.javaee.training.com/", name = "guardarActorResponse")
    public JAXBElement<GuardarActorResponse> createGuardarActorResponse(GuardarActorResponse value) {
        return new JAXBElement<GuardarActorResponse>(_GuardarActorResponse_QNAME, GuardarActorResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GuardarActor }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.jaxws.javaee.training.com/", name = "guardarActor")
    public JAXBElement<GuardarActor> createGuardarActor(GuardarActor value) {
        return new JAXBElement<GuardarActor>(_GuardarActor_QNAME, GuardarActor.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CrearActorResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.jaxws.javaee.training.com/", name = "crearActorResponse")
    public JAXBElement<CrearActorResponse> createCrearActorResponse(CrearActorResponse value) {
        return new JAXBElement<CrearActorResponse>(_CrearActorResponse_QNAME, CrearActorResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Actor }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.jaxws.javaee.training.com/", name = "actor")
    public JAXBElement<Actor> createActor(Actor value) {
        return new JAXBElement<Actor>(_Actor_QNAME, Actor.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CrearActor }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.jaxws.javaee.training.com/", name = "crearActor")
    public JAXBElement<CrearActor> createCrearActor(CrearActor value) {
        return new JAXBElement<CrearActor>(_CrearActor_QNAME, CrearActor.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultarResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.jaxws.javaee.training.com/", name = "consultarResponse")
    public JAXBElement<ConsultarResponse> createConsultarResponse(ConsultarResponse value) {
        return new JAXBElement<ConsultarResponse>(_ConsultarResponse_QNAME, ConsultarResponse.class, null, value);
    }

}
