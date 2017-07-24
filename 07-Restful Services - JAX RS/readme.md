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

## Consumiendo un servicio Rest en Java ##



# Referencias #

http://www.i2factory.com/es/integracion/qu%C3%A9-es-un-servicio-restful