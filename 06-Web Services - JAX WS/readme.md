# Servicios Web SOAP (JAX-WS)

## ¿Qué es SOAP ?
SOAP (originalmente las siglas de Simple Object Access Protocol) es un protocolo estándar que define cómo dos objetos en diferentes procesos pueden comunicarse por medio de intercambio de datos XML. 

### Ejemplo de mensaje SOAP ###

    <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
       <soap:Body>
     		<getProductDetails xmlns="http://warehouse.example.com/ws">
       			<productId>827635</productId>
     		</getProductDetails>
       </soap:Body>
    </soap:Envelope>

## WSDL ##
Un documento WSDL define la interoperabilidad de los servicios Web e incluye la especificación sobre requerimientos de transporte y formato de los datos a través de la red. En general, un WSDL no impone ningún requerimiento sobre el modelo de programación del cliente o del servidor.

### Ejemplo de WSDL ###
	<definitions name="StockQuote" 
          targetNamespace="http://example.com/stockquote.wsdl"
          xmlns:tns="http://example.com/stockquote.wsdl"
          xmlns:xsd1="http://example.com/stockquote.xsd"
          xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/"
          xmlns="http://schemas.xmlsoap.org/wsdl/">
 
	    <types>
	       <schema targetNamespace="http://example.com/stockquote.xsd"
	              xmlns="http://www.w3.org/2000/10/XMLSchema">
	           <element name="TradePriceRequest">
	              <complexType>
	                  <all>
	                      <element name="tickerSymbol" type="string"/>
	                  </all>
	              </complexType>
	           </element>
	           <element name="TradePrice">
	              <complexType>
	                  <all>
	                      <element name="price" type="float"/>
	                  </all>
	              </complexType>
	           </element>
	       </schema>
	    </types>
	 
	    <message name="GetLastTradePriceInput">
	        <part name="body" element="xsd1:TradePriceRequest"/>
	    </message>
	    <message name="GetLastTradePriceOutput">
	        <part name="body" element="xsd1:TradePrice"/>
	    </message>

	    <portType name="StockQuotePortType">
	        <operation name="GetLastTradePrice">
	           <input message="tns:GetLastTradePriceInput"/>
	           <output message="tns:GetLastTradePriceOutput"/>
	        </operation>
	    </portType>
	    <binding name="StockQuoteSoapBinding" type="tns:StockQuotePortType">
	        <soap:binding style="document" transport="http://schemas.xmlsoap.org/soap/http"/>
	        <operation name="GetLastTradePrice">
	           <soap:operation soapAction="http://example.com/GetLastTradePrice"/>
	           <input>
	               <soap:body use="literal"/>
	           </input>
	           <output>
	               <soap:body use="literal"/>
	           </output>
	        </operation>
	    </binding>
	    <service name="StockQuoteService">
	        <documentation>My first service</documentation>
	        <port name="StockQuotePort" binding="tns:StockQuoteSoapBinding">
	           <soap:address location="http://example.com/stockquote"/>
	        </port>
	    </service>
 	</definitions>


## ¿Qué es JAX-WS? ##
JAX-WS *(Java API for XML Web Services)* es una interfaz de programación de aplicaciones (API) de Java en Extensible Markup Language (XML) para la creación de servicios web (WS). Es parte de la plataforma Java EE de Sun Microsystems. Al igual que las otras API de Java EE, JAX-WS utiliza anotaciones, introducidas en Java SE 5, para simplificar el desarrollo y despliegue de los clientes y puntos finales de servicios web

### ¿Por qué JAX-WS ? ###
Vamos a crear nuestros propios Servicios Web, que ofrecerán una serie de métodos a los que se podrá llamar mediante protocolos estándar (mensajes SOAP).

Deberemos por lo tanto ser capaces de interpretar en nuestras aplicaciones los mensajes SOAP entrantes de petición para la invocación de un método. Posteriormente, invocaremos el método solicitado, y con el resultado que nos devuelva deberemos construir un mensaje SOAP de respuesta y devolvérselo al cliente.

Si tuviésemos que introducir nosotros el código para interpretar este mensaje de entrada, y generar manualmente el mensaje de respuesta, el desarrollo de Servicios Web sería una tarea altamente costosa.

Es más, si se forzase al programador a componer el mensaje SOAP manualmente cada vez que desarrolle un Servicio Web, es muy probable que cometa algún error y no respete exactamente el estándar SOAP. Esto sería un grave problema para la interoperabilidad de los Servicios Web, que es una de las características que perseguimos con esta tecnología.

Para evitar estos problemas, utilizaremos librerías que nos permitan leer o generar mensajes SOAP para la invocación de métodos remotos, como es el caso de la API JAX-WS.


# Publicando un Servicio Web SOAP con JAX-WS #
Para desarrollar una implementación de un servicio web (web service endpoint) podemos optar por dos puntos de partida: 

- Una clase Java que implementa el servicio Web
- Un fichero WSDL.

En esta leccion vamos a centrarnos en producir un servicio web a partir de una clase Java que implemente las anotaciones y métodos necesario y el resto de artefactos sean generados por JAX-WS.

## Anotaciones ##
La especificación JAX-WS nos permite generar un servicio web a partir de una clase con anotaciones, estas anotacion son especificas para describir el comportamiento de este servicio web. De esta manera está especificación se encarga de generar las clases y archivos que implementen los artefactos encargados de la comunicación del servicio web, entre ellos el **archivo WSDL**.

Las anotaciones @WebService y @SOAPBinding son generales al servicio web generado, por lo que se asocian a la clase. Las anotaciones @WebMethod y @WebResult están relacionadas con el método y por tanto hay una por cada método a publicar. La anotación @WebParam está relacionada a los parámetros, por lo que habrá una por cada parámetro que haya en los métodos.

### @WebService ###
Indica que la clase define un servicio web. Se pueden especificar como parámetros los nombres del servicio (serviceName), del componente Port (portName), del SEI del servicio (name), de su espacio de nombres (targetNamespace), y de la ubicación del WSDL (wsdlLocation), que figurarán en el documento WSDL del servicio:

	@WebService(name="ConversionPortType", 
				serviceName="ConversionService",
				portName="ConversionPort",
				targetNamespace="http://jtech.ua.es",
				wsdlLocation="resources/wsdl/")


### @SOAPBinding ###
Permite especificar el estilo y la codificación de los mensajes SOAP utilizados para invocar el servicio. Por ejemplo:
		
	@SOAPBinding(style=SOAPBinding.Style.DOCUMENT,
			    use=SOAPBinding.Use.LITERAL,
			    parameterStyle=SOAPBinding.ParameterStyle.WRAPPED)</pre>


### @WebMethod ###
Indica que un determinado método debe ser publicado como operación del servicio. Si no se indica para ningún método, se considerará que deben ser publicados todos los métodos públicos. Si no, sólo se publicarán los métodos indicados. Además, de forma opcional se puede indicar como parámetro el nombre con el que queramos que aparezca la operación en el documento WSDL:

    @WebMethod(operationName="eurosAptas")
    public int euro2ptas(double euros) {
    	   ...
    }


### @Oneway ###
Indica que la llamada a la operación no debe esperar ninguna respuesta. Esto sólo lo podremos hacer con métodos que devuelvan void. Por ejemplo:

    @Oneway()
    @WebMethod()
    public void publicarMensaje(String mensaje) {
       ...
    }

### @WebParam ###
Permite indicar el nombre que recibirán los parámetros en el fichero WSDL:

    @WebMethod(operationName="eurosAptas")
    public int euro2ptas(
      @WebParam(name="CantidadEuros",targetNamespace="http://jtech.ua.es")
      double euros) {
    	...
    }


### @WebResult ###
Permite indicar el nombre que recibirá el mensaje de respuesta en el fichero WSDL:

    @WebMethod(operationName="eurosAptas")
    @WebResult(name="ResultadoPtas", targetNamespace="http://jtech.ua.es")
    public int euro2ptas(double euros) {
    	...
    }


### @WebFault ###
Se utiliza para anotar excepciones Java. Cuando utilizamos esta anotación en una
excepción estamos indicando que cuando sea lanzada por una operación del servicio web debe generar un mensaje SOAP de respuesta con un *SOAP Fault* que nos indique el error producido. En el lado del cliente la clase con dicha excepción se habrá generado en el *stub* para el acceso al servicio, y al recibir el mensaje SOAP con el error el *stub* lanzará la excepción correspondiente. 

Es decir, para el desarrollador será como si la excepción saltase directamente desde el servicio hasta el cliente.
	
	@WebFault
	public class ConversionFaultException extends Exception {
	    public ConversionFaultException(String msg) {
	        super(msg);
	    }
	}

## Tipos de Datos Compatibles ##

Cuando trabajamos con JAX-WS, los tipos de datos que podremos utilizar como tipo de los parámetros y de valor de retorno de los métodos de nuestro servicio serán los tipos soportados por JAXB.

Podremos utilizar cualquiera de los tipos básicos de Java:

- boolean
- byte
- double
- float
- int
- long
- short
- char

Además, también podremos utilizar cualquiera de los wrappers de estos tipos básicos:

- java.lang.Boolean
- java.lang.Byte
- java.lang.Double
- java.lang.Float
- java.lang.Integer
- java.lang.Long
- java.lang.Short
- java.lang.Character

Las siguientes clases de Java también son aceptadas como tipos válidos por JAX-WS:

- java.lang.String
- java.math.BigDecimal
- java.math.BigInteger
- java.util.Calendar
- java.util.Date
- javax.xml.namespace.QName
- java.net.URI

Además de estos datos, se permitirá el uso de colecciones cuyos elementos podrán ser de cualquiera de los tipos admitidos. Estas colecciones podrán ser arrays, tanto unidimensionales como multidimensionales, o clases del marco de colecciones de Java:

- Listas
	- List 
	- ArrayList 
	- LinkedList 
	- Stack 
	- Vector 
- Mapas: 
	- Map 
	- HashMap 
	- Hashtable 
	- Properties 
	- TreeMap 
- Conjuntos: 
	- Set 
	- HashSet 
	- TreeSet 

Podemos ademas utilizar las capacidades de JAXB, para el soporte de objetos/clases/modelos propios que se ajusten a nuestras necesidades. JAXB es Java Architecture for XML Binding y nos ayuda a convertir objetos java a XML.

### Ejemplo de Clase con Soporte a JAXB ###

    import javax.xml.bind.annotation.XmlElement;
    import javax.xml.bind.annotation.XmlRootElement;
    import javax.xml.bind.annotation.XmlType;
    
    @XmlRootElement(name = "book")
    // If you want you can define the order in which the fields are written
    // Optional
    @XmlType(propOrder = { "author", "name", "publisher", "isbn" })
    public class Book {
    
    	@XmlElement(name = "title")
	    private String name;
	    private String author;
	    private String publisher;
	    private String isbn;
	    
	    ... Getters y Setters
    } 

## Configuración Maven (Opcional) ##

Esta configuración es opcional, su objetivo es permitir configuracion personalizadas al ejecutar el goal ws-gen en tiempo de compilación cuando se crean los artefactos del servicio web.

	<dependencies>
        <dependency>
            <groupId>com.sun.xml.ws</groupId>
            <artifactId>jaxws-rt</artifactId>
            <version>2.1.3</version>
        </dependency>
    </dependencies>
 
    <build>
        <finalName>HelloWorldService</finalName>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.1</version>
                <configuration>
                    <source>1.6</source>
                    <target>1.6</target>
                    <encoding>UTF-8</encoding>
                </configuration>
            </plugin>
        </plugins>
    </build>




# Consumiendo un Servicio Web (SOAP) con JAX-WS #
Existen diferentes maneras de crear un cliente que consuma un servicio web. Entre ellas podemos mencionar:

- Crear las clases para consumir manualmente
- Utilizar los wizards que disponen cada IDE (Netbeans, Eclipse, etc) para generar las clases que consumen el servicio web
- Utilizar un plugin de Maven que nos genere estas clases a partir de un archivo WSDL.

Esta lección se enfocara en la última opción, utilizar un plugin de maven que nos genere las clases para consumir el servicio web a partir de un archivo WSDL.

## Configuración Maven ##

	<build>
        <plugins>
			<!-- Genera el codigo del webservice -->
            <plugin>
                <groupId>org.jvnet.jax-ws-commons</groupId>
                <artifactId>jaxws-maven-plugin</artifactId>
                <version>2.3</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>wsimport</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                    <verbose>true</verbose>
    				<packageName>com.training.javaee.webservices.webservice1</packageName>
                    <sourceDestDir>target/generated-code/src</sourceDestDir>
                    <wsdlDirectory>src/main/resources</wsdlDirectory>
                    <wsdlFiles>
                        <wsdlFile>myWsdl.wsdl</wsdlFile>
                    </wsdlFiles>
                    <wsdlLocation>http://localhost:8080/myapp/webservices/myWsdl.wsdl</wsdlLocation>
                </configuration>
            </plugin>

            <!-- Copia el codigo generado al proyecto -->
            <plugin>
                <groupId>org.codehaus.mojo</groupId>
                <artifactId>build-helper-maven-plugin</artifactId>
                <version>1.9.1</version>
                <executions>
                    <execution>
                        <id>add-source</id>
                        <phase>generate-sources</phase>
                        <goals>
                            <goal>add-source</goal>
                        </goals>
                        <configuration>
                            <sources>
                                <source>target/generated-code/src</source>
                            </sources>
                        </configuration>
                    </execution>
                </executions>
            </plugin>

            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>2.3.1</version>
                <configuration>
                    <source>1.6</source>
                    <target>1.6</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
    
 Esto nos genera una serie de clases de las cuales nos interesa.Supongamos que generamos un servicio web con el nombre HolaMundo.

	HelloWorldService helloWorldService = new HelloWorldService());
    HelloWorld helloWorld = helloWorldService.getHelloWorldPort();

    // Ya podemos usarlo
    System.out.println(helloWorld.sayHello()); 
    


# Referencias #

- http://www.jtech.ua.es/j2ee/publico/servc-web-2012-13/sesion02-apuntes.html
- http://chuwiki.chuidiang.org/index.php?title=Ejemplo_sencillo_de_web_service_con_jax-ws
- https://amalaka.wordpress.com/2010/02/25/111/
- https://eduvitoriatecnicomio.wordpress.com/2013/02/07/creando-un-servicio-web-mediante-anotaciones-jax-ws-utilizando-un-enfoque-ascendente-bottom-up/
- https://es.wikipedia.org/wiki/Simple_Object_Access_Protocol