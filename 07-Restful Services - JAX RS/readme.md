# Servicios Web Restful - JAX-RS #
Los restful services son otro tipo de recurso que existe para generar servicios web que publiquen nuestra lógica a un tercero que desee consumirlos. A diferencia de los servicios web SOAP los rest services no están basados en XML. 

Java provee funcionalidades para implementar este tipo de servicios de manera muy sencilla por medio de JAX-RS.


# Restful Services y Principios de REST API #
REST es el acrónimo de Representational State Transfer, y resumiendo, representa un tipo de arquitectura de interfaces de comunicación basado en un protocolo de cliente/servidor sin estado (protocolo Http), cacheable, con unas operaciones bien definidas en el que los recursos están identificados de forma única por URIs.

Es importante indicar que la arquitectura REST no es un estándar ni un protocolo, sino una serie de principios de arquitectura. 

## Métodos y Verbos ##
Para realizar las peticiones, el protocolo Http proporciona una serie de métodos (también conocidos como “verbos”) que indican acciones que se van a realizar sobre un recurso, que no es más que un elemento de información. 

Cuando accedemos a una página web desde nuestro navegador, estamos realizando una petición a un servidor con el método GET. Si enviamos un formulario desde una web, estaremos realizando una petición con el método POST. 

Los sistemas REST utilizan estos métodos para identificar operaciones sobre los distintos recursos. Los métodos más utilizados por las APIS REST son los siguientes:

- **GET:** Se utiliza para consultar, leer y en definitiva acceder a un recurso
- **POST:** Envía datos para crear un recurso. Como en cualquier petición POST, los datos no se envían en la URI, sino que deben ir incluidos en el cuerpo de la petición.
- **PUT:** Utilizado para editar un recurso. Al igual que el POST, los datos deben ir en el cuerpo de la petición.
- **DELETE:** Es la opción para eliminar un recurso
- **PATCH:** Se utiliza para modificar parcialmente un recurso, aunque se utiliza en muy pocas ocasiones. Normalmente se utiliza simplemente PUT.

## Códigos de Error ##

En cualquier tipo de API es necesario que la aplicación cliente detecte si las peticiones u operaciones que ha realizado han finalizado de forma correcta o por el contrario se ha producido algún tipo de error.

Es muy común que las APIs Restful definan unos códigos de error personalizados y no utilicen los códigos de error y estado propios del protocolo HTTP. Este comportamiento provoca que desde el punto de vista del cliente, sea necesario desarrollar un tratamiento de errores totalmente personalizado para cada API.

El protocolo HTTP define entre otros muchos, los siguientes códigos:

- 200 → OK : Petición recibida y procesada de forma correcta
- 201 → Created : Petición completada. El resultado ha sido la creación de un nuevo recurso
- 204 → No Content: La petición es correcta, pero la respuesta no tiene ningún contenido
- 401 → Unauthorized: La información de autenticación no es válida
- 404 → Not found: El recurso no ha sido encontrado

## Identificación de Recursos Mediante URIs ##

Este aspecto es fundamental si queremos diseñar una API REST que cumpla con los requisitos de dicha arquitectura.

Una URI se estructura de la siguiente forma:

    {protocolo}://{dominio o hostname}{:puerto(opcional)}/{ruta del recurso}?{parámetros}

Para asignar una URI a un recurso existen varias reglas básicas:

- Deben ser únicas, no pudiendo existir más de una URI para identificar un mismo recurso.
- Deben ser independientes del formato en el que queramos consultar el recurso
- Deben mantener una jerarquía en la ruta del recurso
- No deben indicar acciones, por lo que no debemos usar verbos a la hora de definir una URI

### Ejemplo de uso de URIs ###

- POST http://miapideejemplo.com/clientes → Para crear un cliente
- GET http://miapideejemplo.com/clientes/{id} → Para obtener la información de un cliente concreto
- PUT http://miapideejemplo.com/clientes/{id} → Para modificar los datos de un cliente concreto
- DELETE http://miapideejemplo.com/clientes/{id} → Para eliminar un cliente concreto
- GET http://miapideejemplo.com/clientes → Para obtener el listado de clientes.

Si queremos añadir funcionalidad de filtrado a la hora de obtener un listado de recursos, no debemos definir otra URI especial ni añadir nuevos elementos en la ruta de la URI, sino que podemos usar los parámetros de consulta de la query, que son una serie de pares (clave, valor) separados por el carácter “&” y que se sitúan en la URI tras el símbolo “?”.

Un ejemplo **incorrecto** a la hora de filtrar clientes sería:

- GET http://miapideejemplo.com/clientes/ciudad/Huelva

El ejemplo **correcto** sería

- GET http://miapideejemplo.com/clientes?ciudad=Huelva

## Formatos de Respuesta 

La descripción de la arquitectura REST no incluye la definición de un formato concreto de respuesta. Normalmente los formatos elegidos son XML o JSON, ya que son dos lenguajes que permiten describir fácilmente estructuras y jerarquías, a la vez que son ampliamente utilizados y conocidos en la actualidad. 


# Implementando Servicios REST con JAX-RS #

## ¿Qué es JAX-RS? ##
JAX-RS (Java Api for Restful Services) es la especificación de Java para el desarrollo de servicios Rest. 

La implementación de referencia es **Jersey**, esta es utilizada por servidores de aplicaciones como Glassfish, Payara y Weblogic. Tambien existe otra implementación ampliamente conocida **Rest-Easy** de JBoss, la cual es utilizada en los servidores de JBoss, Wildfly, entre otros.

## Anotaciones ##

### @Path ###
Esta anotación especifica una ruta URI. Puede ser colocada en una clase o en un método.

### @PathParam ###
Esta anotación especifica que es un parámetro indicado en la ruta URI. Solamente puede ser colocado en un parámetro de un método.

### @GET ###
Indica que el método va a ejecutar un solicitud GET.

### @POST ###
Indica que el método va a ejecutar un solicitud POST.

### @PUT ###
Indica que el método va a ejecutar un solicitud PUT.

### @HEAD ###
Indica que el método va a ejecutar un solicitud HEAD.

### @DELETE ###
Indica que el método va a ejecutar un solicitud DELETE.

### @OPTIONS ###
Indica que el método va a ejecutar un solicitud OPTIONS.

### @FormParam ###
Representa un parámetro de tipo Form.

### @QueryParam ###
Representa un parámetro del método que es tomado de un query string del URL. Son aquellos parámetros que se ubican luego de ? en un url.Solo puede ser agregada a los parámetros de un método.

### @HeaderParam ###
Representa un parámetro del método que es tomado del Header (cabecera) de la solicitud http. Solo puede ser agregada a los parámetros de un método.

### @CookieParam ###
Representa un paráetro del método que es tomado del cookie enviado en la solicitud http. Solo puede ser agregada a los parámetros de un método.

### @Produces ###
Define el tipo de respuesta a generar tal como XML, PLAIN, JSON, Stream, etc. Si no se indica que produce, entonces JSON es el valor por defecto.

### @Consumes ###
Define el tipo de objeto de solicitud (Media Type) que el método puede recibir como parámetro en el cuerpo.


## Publicando un servicio Rest en Java ##

- Clase
	- La clase debe incluir la anotacion @Path
	- Si a la anotación @Path("<value>") tiene un valor, ese valor será parte de la URI base de todos los métodos de dicha clase.
	- Si se desea inyectar algun servicio o instancia en el servicio Rest, esta clase debe de utilizar la anotación @RequestScoped de CDI.
- Métodos
	- Cada método que exponga o publique una funcionalidad en el servicio, debe de tener una anotación con el verbo HTTP respectivo: @GET, @POST, @PUT, @DELETE, etc.
	- Es opcional que el método utilice la anotación @Path, si la utiliza y esta anotación un valor @Path("<value>") este valor será parte de la URI de dicho método.
	- **No puede** haber dos métodos con el mismo verbo HTTP, y con la misma ruta URI
	- **Puede** haber 2 métodos con mismo URI y diferentes verbo http.
	- Si un método utiliza la @Produces, por defecto el resultado será en JSON.

- Parámetros
	- Un método solamente pueden recibir un parámetro en el body.
	- Un método puede recibir tantos parámetros @PathParam y @QueryParam como sean necesarios.
	- Todos los @PathParam requieren que el nombre del parámetro (indicado en la anotación) esté mapeado en el @Path del método. 
	- Los parámetros @PathParam y @QueryParam permiten tipos primitivos, String y Date. Estos no permiten objetos complejos.
	- Los metodos http @GET y @DELETE no permiten indicar un objeto en el body. 

Ejemplo de un servicio Rest:

	import javax.ws.rs.GET;
	import javax.ws.rs.Path;
	import javax.ws.rs.core.Response;

	@Path("/users")
	@RequestScoped
	public class UserRestService {
		
		@Inject 
		private UserService userService;

		@GET
		public Response getUser() {
			List<User> users = userService.list();
			GenericEntity<List<User>> list = 
				new GenericEntity<List<User>>(users) {};

			return Response.status(200)
				.entity(list)
				.build();
		}
	
		@GET
		@Path("/vip")
		public Response getUserVIP() {
			List<User> usersVip = userService.listVip();
			GenericEntity<List<User>> list = 
				new GenericEntity<List<User>>(usersVip) {};

			return Response.status(200)
				.entity(list)
				.build();
		}

		@GET
		@Path("/{userId}")
		@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
		public Response getUser(@PathParam("userId") int userId) {
			User user = userService.find(userId);
			return Response.status(200)
				.entity(user)
				.build();
		}

		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response saveUser(User user) {
			User savedUser = userService.save(user);
			return Response.status(200)
				.entity(savedUser)
				.build();
		}
		
		@POST
		@Path("/vip")
		@Consumes(MediaType.APPLICATION_JSON)
		public Response saveUserVIP(User user) {
			User savedUserVIP = userService.saveVIP(user); 
			return Response.status(200)
				.entity(savedUserVIP)
				.build();
		}

		@PUT
		@Consumes(MediaType.APPLICATION_JSON)
		public Response updateUser(User user) {
			userService.update(user);
			return Response.ok().build();
		}

		@DELETE
		@Path("/{userId}")
		public Response deleteUser(@PathParam("userId") int userId) {
			userService.delete(userId);
			return Response.status(200).build();
		}
	}

### Configurando con web.xml ###

Con el archivo web.xml podemos indicar al Jersey principalmente 2 cosas:

- En que paquete de nuestra aplicación buscar por nuestros servicios Rest (aquellos anotados con @Path)
- El **URL base** en el cual van a estar ubicados los servicio rest publicados

Ejemplo:

		<servlet>
				<servlet-name>jersey-serlvet</servlet-name>
				<servlet-class>org.glassfish.jersey.servlet.ServletContainer</servlet-class>
			<init-param>
				 <param-name>jersey.config.server.provider.packages</param-name>
				 <param-value>com.training.javaee.rest</param-value>
			</init-param>
			<load-on-startup>1</load-on-startup>
		</servlet>
		   
		<servlet-mapping>
			<servlet-name>jersey-serlvet</servlet-name>
			<url-pattern>/rest/*</url-pattern>
		</servlet-mapping>


### Configurando con @ApplicationPath ###

Se puede también crear una clase de Java que extienda de la clase `javax.ws.rs.core.Application` y que utilice la anotación `@ApplicationPath("resources")`.

Se puede utilizar la anotación `javax.ws.rs.ApplicationPath` para definir el URI base en donde se publicarán los servicios restful creados.

    import javax.ws.rs.core.Application;
    javax.ws.rs.ApplicationPath;
    
    @ApplicationPath("rest/api")
    public class MyApplication extends Application {
    	
    }


### Dependencias ###
Debido a que es parte del estandar de Java EE, no es necesario agregar dependencias para JAX-RS, sin embargo si queremos utilizar alguna particularidad de Jersey podemos incluir las dependencias.

		<!-- Jersey 2.19 -->
        <dependency><!-- Opcional -->
            <groupId>org.glassfish.jersey.containers</groupId>
            <artifactId>jersey-container-servlet</artifactId>
            <version>${jersey2.version}</version>
        </dependency>
        <dependency> <!-- Opcional -->
            <groupId>org.glassfish.jersey.core</groupId>
            <artifactId>jersey-server</artifactId>
            <version>${jersey2.version}</version>
        </dependency>

## Consumiendo un servicio Rest en Java ##

Para consumir un servicio web se utiliza el la clase Client y WebTarget proporcionados por jax-rs.

	Client client = ClientBuilder.newClient();
	String name = client.target("http://example.com/webapi/hello")
	        .request(MediaType.TEXT_PLAIN)
	        .get(String.class);
	client.close();


### Seteando parámetros
Se puede utilizar los métodos resolveTemplate(String, Object) para indicar valores en un @PathParam y queryParam(String, Object) para indicar los @QueryParam. 

Ejemplo para el recurso en el URL: http://example.com/webapi/read/janedoe?chapter=1

	WebTarget myResource = client.target("http://example.com/webapi/read")
        .path("{userName}")
        .resolveTemplate("userName", "janedoe")        
		.queryParam("chapter", "1");
	Response response = myResource.request(...).get();

### Enviando un objeto en el body 
Los métodos post(...), put(...), patch(...), permiten enviar un objeto en el body del request.

    Client client = ClientBuilder.newClient();
    StoreOrder order = new StoreOrder(...);
    WebTarget myResource = client.target("http://example.com/webapi/write");
    TrackingNumber trackingNumber = myResource
			.request(MediaType.APPLICATION_XML)
       		.post(order, TrackingNumber.class);
   
### Obtener el resultado de un Response ###
Todos los métodos http del objeto response, permiten indicar si deseamos obtener el resultado en una clase especifica. En caso de no indicarlo devuelve un objeto de tipo Response.

Obteniendo una respuesta de clase TrackingNumber:

	TrackingNumber trackingNumber = myResource
			.request(MediaType.APPLICATION_XML)
       		.post(order, TrackingNumber.class);

Obteniendo una respuesta de clase String:

	String response = myResource
		.request(MediaType.TEXT_PLAIN)
        .get(String.class);

Obteniendo una respuesta Response:

	Response response = myResource
		.request(MediaType.APPLICATION_JSON)
        .get();
	MyObject myObject = (MyObject) response.getEntity();

Obteniendo una lista:

	List<Customer> customers =
            client.target("http://localhost:8080/customer/webapi/Customer")
            .request(MediaType.APPLICATION_XML)
            .get(new GenericType<List<Customer>>() {
            });
    return customers;
    
# Referencias #

- http://www.i2factory.com/es/integracion/qu%C3%A9-es-un-servicio-restful
- https://www.javatpoint.com/jax-rs-annotations-example
- http://docs.oracle.com/javaee/6/tutorial/doc/giepu.html
- https://docs.oracle.com/cd/E24329_01/web.1211/e24983/configure.htm#RESTF191
- http://howtodoinjava.com/jersey/jersey-2-hello-world-application-tutorial/
- https://docs.oracle.com/javaee/7/tutorial/jaxrs-client001.htm