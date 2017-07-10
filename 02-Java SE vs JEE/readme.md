# Java SE y JAVA EE #

## ¿Qué es Java SE? ##
Cuando la mayoria de personas piensan en el lenguaje de programación piensan en Java SE API. El API de Java SE provee la funcionalidad "core" del lenguaje de programación. Define todo desde los tipos básicos y objetos del lenguaje de programación hasta las clases de alto nivel utilizadas para redes, seguridad, acceso a base de datos, desarrollo interface gráfica de usuario (GUI), trabajo con XMLs, entre otras funcionalidades.

## ¿Qué es Java EE? ##

La plataforma Java EE está construida sobre la plataforma Java SE. Esta plataforma provee un API y ambiente para desarrollar y ejecutar aplicaciones escalables, multi capas, robustas, seguras, distribuidas, proporcionando grupo de diferentes especificaciones para esto.

## Malentendidos comunes

- Java SE es solo aplicaciones de Escritorio.
- Si desarrollo web en Java, ya estoy usando Java EE.
- Uso Java EE X por que uso el JDK X.



## Conceptos Importantes ##

### JRE (Java Runtime Environment) ###

Como su nombre lo indica este ambiente ("KIT") es utilizado solo para ejecutar ("Runtime") programas en Java. Esta situación se da cuando empresas de Software diseñan alguna interfase gráfica o aplicación en Java para su producto. 

### JDK (Java Development Kit) ###
Java Development Kit o (JDK), es un software que provee herramientas de desarrollo para la creación de programas en java. Puede instalarse en una computadora local o en una unidad de red.

- El interprete de Java
- El compilador de Java
- Java Doc, para generar documentación
- AppletViewer
- y más.  

### RI (Reference Implementation) ###
Cuando hablamos de especificaciones Java, ejemplo: JAX-RS, JPA, JSF, con mucha frecuencia se escucha el termino implementación de referencia. Debido a que estas tecnologias son especificaciones, significa que hay un estandar publicado, pero cualquier puede crear su implementación propia de la tecnologia siguiendo dicho estandar. 

La implementación de referencia es la implementación proporcionada por Oracle/Sun para dicha tecnologia, en muchos casos puede no ser la mejor, pero es la que se proporciona por defecto. 

# Java EE #
Como se mencionó anteriormente Java EE es una especificación que agrupa un conjunto de otras especificaciones Java para el desarrollo de aplicaciones empresariales. 

- Servlets
- EJB
- JPA
- JTA
- CDI 
- JSF
- JMS
- Java Mail
- Web Services
- JSon
- Java WebSocket
- Concurrency Utils
- Batch Applications
- muchas otras mas.

Mediante Java EE Platform Enterprise Edition (Java EE), puede desarrollar aplicaciones más rápidamente y más cómodamente que en versiones anteriores. Java EE mejora considerablemente la facilidad de uso gracias a:

- Reducción del tiempo de desarrollo
- Reducción de la complejidad de las aplicaciones
- Mejora en el rendimiento de las aplicaciones

## Requisitos para una aplicación Java EE ##
- Utilizar la libreria API de java EE.
- Desplegarse en un Servidor de Aplicaciones Java compatible.
- El servidor de aplicaciones debe tener los contenedores de las tecnologias utilizadas.

#Iniciando Java EE
El primer paso que daremos para desarrollar aplicaciones Java EE es conocimiento las especificaciones/tecnologías Servlets y CDI.

##Servlets
Los servlets no son más que una clase más dentro del cada vez mayor árbol de paquetes de Java. La clase Servlet fue diseñada para permitir desarrollar de una forma sencilla aplicaciones cliente-servidor.

Los servlets son objetos que corren dentro del contexto de un servidor web (por ejemplo, Apache Tomcat).

Un servlet es un programa que se ejecuta en un servidor web, mostrando al navegador el resultado de su ejecución, por lo que, el uso más común de los servlets es generar páginas web de forma dinámica.

### Creacion de un Servlet ###
Un Servlet de ejemplo puede ser creado de 3 maneras.
Implementando la interface Servlet 
Heredando la clase GenericServlet 
Heredando la clase HttpServlet

El enfoque mas usado es extendiendo de las clase HttpServlet porque esto provee los metodos de solicitud http tales como: doGet(), doPost(), doHead() etc.


## CDI (Context and Dependency Injection) 
CDI o Context Dependency Injection define el mecanismo para resolver dependencias entre servicios dentro del estándar JEE, a partir de la versión 6. CDI te da posibilidad de inyectar componentes en una aplicación de modo typesafe, incluyendo la posibilidad de seleccionar en tiempo de despliegue que implementación en particular a usar.

Similar a los conceptos de Dependency Injection proporcionados por: Spring o Google Guice.

Basicamente podemos decir que con CDI, nos olvidamos de crear las instancias por nosotros mismos y delegamos la tarea de crearlos, administrar su ciclo de vida y eliminar instancias al contenedor.

Antes: 

	public class MyClass {
		private MyService myService;

		public void myMethod(){
			myService = new MyService();
			myService.executeMethod();
		}
	} 

Ahora:
 
	public class MyClass {
		
		@Inject
		private MyService myService;

		public void myMethod(){
			myService.executeMethod();
		}
	} 

###Inject 
Es la manera por la cual es obtiene la instancia de un objeto. Pueden ser instancias de clases creadas por nosotros, o de instancias administradas por un contenedor, EJBs, Recursos, DataSources, etc.

- Se busca si existe una instancia disponible
- Si existe se regresa, si no existe se crea.
- Cuando el contenedor lo considere necesario eliminará dicha instancia.

La anotación `@Inject` se puede aplicar a variables y a métodos. El uso más común es a variables.

###Managed Bean
Una Managed Bean es una clase cuyas instancias son administradas por el contenedor de Managed Beans. 

Para ello tiene que seguir las siguientes condiciones: 
- No es una clase interna
- Es una clase concreta
- No esta anotada como un EJB (puesto que ya son administrado por otro contenedor)
- Tiene un constructor apropiado. Constructor sin parámetros o el constructor esta anotado con @Inject


###Scopes
Un scope define el ciclo de vida de una instancia de un bean administrado. 

- Request `@RequestScoped`
- Session `@SessionScoped`
- Application `@ApplicationScoped`
- Dependent `@DependentScoped`
- Conversation `@ConversationScoped`

###Qualifiers
Cuando tenemos una hacemos una injección a una clase que tiene varias implementaciones, debemos indicar un calificador para conocer la implementación especifica a la cual se desea crear la instancia.

    public class Moneda{}
	public class MonedaColon extends Moneda {}
	public class MonedaDolar extends Moneda {}

	public class Servicio {
		@Inject
		private Moneda moneda;
	}

Un Qualifier es una anotación creada, que luego se agrega a la clase a injectar y luego al objeto injectado, para poder discenir su implementación.

    @Qualifier
	@Retention(RUNTIME)
	@Target({TYPE, METHOD, FIELD, PARAMETER})
	public @interface Colon{}

	public class Moneda{}
	
	@Colon
	public class MonedaColon extends Moneda {}
	@Dolar
	public class MonedaDolar extends Moneda {}

	public class Servicio {
		@Inject 
		@Colon
		private Moneda moneda;
	}

###Named
Con la anotación @Named es un qualifier que le permite al bean ser accedido por un nombre. Este es muy importante cuando queremos accederlo atravez de EL (Expression Language), disponible en JSP y JSF. 

###PostConstruct 
Todos serán inyectados luego del constructor, por lo cual si se accede a dicha variable dentro del constructor su valor será `null`.  

Para ello se utiliza la anotación @PostConstruct a un método, este método será llamado uno vez se haya ejecutado el constructor de la clase, y en este punto si tenemos las variables inyectadas con sus valores correspondientes.


# Referencias
http://docs.oracle.com/javaee/6/firstcup/doc/gkhoy.html
https://parasitovirtual.wordpress.com/2010/12/09/el-salto-de-java-se-a-java-ee/
http://gl-epn-programacion-ii.blogspot.com/2010/03/jvm-jdk-jre-conceptos-fundamentales-de.html
http://www.oracle.com/technetwork/java/javaee/tech/index.html
https://www.ibm.com/support/knowledgecenter/es/SSRTLW_7.5.5/com.ibm.jee5.doc/topics/cjee5overview.html
http://flanagan.ugr.es/docencia/2005-2006/2/servlets/
