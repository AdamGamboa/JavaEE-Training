# ¿Qué es JSF ? #
Esta tecnología es un marco de trabajo del lado del servidor que permite construir aplicaciones Java basadas en Web. Se dice que es del lado del servidor, porque se instala en el servidor y este genera las páginas web.

## Partes de una aplicación JSF ##

Una aplicación típica de JavaServer Faces comprende lo siguiente:

- Un conjunto de páginas web en la que se distribuyen los componentes.
- Un conjunto de etiquetas (tags) para agregar componentes a las páginas.
- Un conjunto de beans administrados (ManagedBeans), que son administrados por el contenedor usando objetos. Estos beans administrados son los que están detrás de una página proporcionando propiedades y funciones a la interfaz de usuario.
- Un archivo descriptor web (web.xml)
- Adicionalmente, archivos de configuración de recursos (faces-config.xml)
- Además, objetos personalizados: validadores, convertidores, oidores (listeners) para mejorar la aplicación.
- Y etiquetas adicionales personalizadas.

## Ventajas de usar JSF ##

- El código JSF con el que creamos las vistas es muy parecido al HTML estándar. Lo pueden  utilizar fácilmente desarrolladores y diseñadores web.
 
- JSF resuelve validaciones, conversiones, mensajes de error e internacionalización (i18n)
 
- JSF permite introducir javascript en la página, para acelerar la respuesta de la interfaz en el cliente (navegador del usuario).
 
- JSF es extensible, por lo que se pueden desarrollar nuevos componentes a medida, También se puede modificar el comportamiento del framework mediante APIs que controlan su funcionamiento.


# Usando JSF #

## Componentes HTML ##
Java Server Faces provee de una serie de componentes HTML básicos sobre los cuales trabajar, muchos de estos componentes ya presentan caracteristicas Ajax. Además JSF permite personalizar y extender los componentes existentes creando nuevos componentes que se adapten a las necesidades.

### Componentes Básicos ###

<table>
	<tr>
		<th>Tag</th>
		<th>Descripción</th>
	</tr>
	<tr>
		<td>h:inputText</td>
		<td>Representa una entrada de HTML type = "text", cuadro de texto.</td>
	</tr>
	<tr>
		<td>h:inputSecret</td>
		<td>Representa una entrada de HTML type = "password", cuadro de texto.</td>
	</tr>
	<tr>
		<td>h:inputTextarea</td>
		<td>Representa un campo de área de texto HTML.</td>
	</tr>
	<tr>
		<td>h:inputHidden</td>
		<td>Representa una entrada de HTML type = "hidden"</td>
	</tr>
	<tr>
		<td>h:selectBooleanCheckbox</td>
		<td>Representa una casilla de verificación HTML sola.</td>
	</tr>
	<tr>
		<td>h:selectManyCheckbox</td>
		<td>Representa un grupo de casillas de verificación HTML.</td>
	</tr>
	<tr>
		<td>h:selectOneRadio</td>
		<td>Representa un único botón de opción HTML.</td>
	</tr>
	<tr>
		<td>h:selectOneMenu</td>
		<td>Representa una selección de "ComboBox" HTML.</td>
	</tr>
	<tr>
		<td>h:outputText</td>
		<td>Representa un texto HTML.</td>
	</tr>
	<tr>
		<td>h:graphicImage</td>
		<td>Representa una imagen.</td>
	</tr>
	<tr>
		<td>h:outputStylesheet</td>
		<td>Incluye una hoja de estilos CSS en la salida HTML.</td>
	</tr>
	<tr>
		<td>h:outputScript</td>
		<td>Incluye una secuencia de comandos en la salida HTML.</td>
	</tr>
	<tr>
		<td>h:commandButton</td>
		<td>Representa una entrada HTML de tipo = botón "submit".</td>
	</tr>
	<tr>
		<td>h:commandLink</td>
		<td>Representa un hipervinculo, similar a la funcionalidad del commandButton</td>
	</tr>
	<tr>
		<td>h:panelGrid</td>
		<td>Renderiza una tabla HTML en forma de cuadrícula.</td>
	</tr>
	<tr>
		<td>h:message</td>
		<td>Renderiza un mensaje a un componente en JSF</td>
	</tr>
	<tr>
		<td>h:messages</td>
		<td>Renderiza un grupo de mensajes a uno o mas componentes en JSF.</td>
	</tr>
</table>

### Data Table ###

    <h:dataTable value="#{userData.employees}" var="employee"
         styleClass="employeeTable"
         headerClass="employeeTableHeader"
         rowClasses="employeeTableOddRow,employeeTableEvenRow">
      <h:column> 
         <f:facet name="header">Sr. No</f:facet>
         #{userData.employees.rowIndex + 1}
      </h:column>
      <h:column>    				
         <f:facet name="header">Name</f:facet>    				
         #{employee.name}
      </h:column>
      <h:column>
         <f:facet name="header">Department</f:facet>
         #{employee.department}
      </h:column>
      <h:column>
         <f:facet name="header">Age</f:facet>
         #{employee.age}
      </h:column>
      <h:column>
         <f:facet name="header">Salary</f:facet>
         #{employee.salary}
      </h:column>
   	</h:dataTable>


### Facelets ###
Facelets es el sistema de **plantillas** utilizado por JSF. Proporciona gran flexibilidad para gestionar partes comunes de varias páginas desde un mismo lugar.

<table>
	<tr>
		<th>Tag</th>
		<th>Descripción</th>
	</tr>
	<tr>
		<td>ui:insert</td>
		<td>Se utiliza en el archivo de plantilla. Define contenido para ser colocados en una plantilla. ui: define la etiqueta se sustituye su contenido.</td>
	</tr>
	<tr>
		<td>ui:define</td>
		<td>Define el contenido para ser insertados en una plantilla.</td>
	</tr>
	<tr>
		<td>ui:include</td>
		<td>Incluye contenidos de una página XHTML en otra página XHTML.</td>
	</tr>
	<tr>
		<td>ui:composition</td>
		<td>Carga una plantilla mediante un atributo de cuadro. También se puede definir un grupo de componentes que se insertará en la página XHTML.</td>
	</tr>
	<tr>
		<td>ui:param</td>
		<td>Utilizado para enviar parámetros entre los distintos elementos de facelets, ui:include, ui:composite, ui:define </td>
	</tr>
	<tr>
		<td>ui:remove</td>
		<td>Permite que el código que se encuentra dentro de este tag, no sea mostrado o renderizado.</td>
	</tr>
</table>

Un ejemplo de uso de facelets

**Plantilla**

    <?xml version="1.0" encoding="UTF-8"?>
	<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
	<html xmlns="http://www.w3.org/1999/xhtml"   
	   xmlns:h="http://java.sun.com/jsf/html"
	   xmlns:ui="http://java.sun.com/jsf/facelets"> 
	   <h:head></h:head>
	   <h:body> 
	      <div style="border-width:2px; border-color:green; border-style:solid;">
	         <ui:insert name="header" >
	            <ui:include src="/templates/header.xhtml" />
	         </ui:insert> 
	      </div>
	      <br/>
	      <div style="border-width:2px; border-color:black; border-style:solid;">
	         <ui:insert name="content" >
	            <ui:include src="/templates/contents.xhtml" />
	         </ui:insert>    
	      </div>
	      <br/>
	      <div style="border-width:2px; border-color:red; border-style:solid;">
	         <ui:insert name="footer" >
	            <ui:include src="/templates/footer.xhtml" />
	         </ui:insert>
	      </div>
	   </h:body>
	</html>

Utilizando la plantilla

    <?xml version="1.0" encoding="UTF-8"?>
	<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
	<html xmlns="http://www.w3.org/1999/xhtml"   
	   xmlns:h="http://java.sun.com/jsf/html"
	   xmlns:ui="http://java.sun.com/jsf/facelets">
	   <h:body> 
	      <ui:composition template="templates/common.xhtml">
	         <ui:define name="header">
	            <h2>Page1 header</h2>
	         </ui:define>			
	         <ui:define name="content">
	            <h2>Page1 content</h2>
	             <h:link value="Back To Home" outcome="home" />
	         </ui:define> 
	         <ui:define name="footer">
	            <h2>Page1 Footer</h2>
	         </ui:define>			
	      </ui:composition> 
	   </h:body> 
	</html>	

### Navegación ###

Se conoce como regla de navegación la mecanismo utilizado por JSF para navegar de una página a otra, hay diferentes maneras de realizarlo, las 2 mas conocidas. 

**Navegación implicita:** 

Se coloca la ubicación de la página a la cual se desea navegar

    <h:form>
       <h3>Using JSF outcome</h3>
       <h:commandButton action="page2" value="Page2" />
    </h:form>

Tambien puede hacerse por medio una respuesta en un método en el ManagedBean.

	<h:form>
	   <h3>Using Managed Bean</h3>
	   <h:commandButton action="#{navigationController.moveToPage1}"
	   value="Page1" />
	</h:form>
	
	@ManagedBean(name = "navigationController", eager = true)
	@RequestScoped
	public class NavigationController implements Serializable {
	   public String moveToPage1(){
	  return "page1";
	   }
	}

**Reglas de Navegacion:** 

Por medio de reglas de navegacion definidas en el archivo faces-config.xml

	<navigation-rule>
	   <from-view-id>home.xhtml</from-view-id>
	   <navigation-case>
	  <from-outcome>sucess</from-outcome>
	  <to-view-id>page1.jsf</to-view-id>
	   </navigation-case>
	   <navigation-case>
	  <from-outcome>page</from-outcome>
	  <to-view-id>page2.jsf</to-view-id>
	   </navigation-case>
	</navigation-rule>



### Convertidores ###
JSF proporciona convertidores incorporados para convertir los datos de su componente de interfaz de usuario de objeto que se utiliza en un bean gestionado y vice versa.For ejemplo, estas etiquetas pueden convertir un texto en objeto de fecha y puede validar el formato de entrada también.
	
<table>
	<tr>
		<th>Tag</th>
		<th>Descripción</th>
	</tr>
	<tr>
		<td>f:convertNumber</td>
		<td>Convierte una cadena en un número de formato deseado</td>
	</tr>
	<tr>
		<td>f:convertDateTime</td>
		<td>Convierte una cadena en una Fecha de formato deseado</td>
	</tr>
	<tr>
		<td>f:convert</td>
		<td>Convertidor personalizado</td>
	</tr>
</table>

#### Convertidor Genérico ####

En Java

    @FacesConverter("com.tutorialspoint.test.UrlConverter")
    public class UrlConverter implements Converter {
		@Override
	   	public Object getAsObject(FacesContext facesContext, 
	      UIComponent component, String value) {
			.....
		}

		@Override
		public String getAsString(FacesContext facesContext,
	      UIComponent component, Object value) {
	         return value.toString();
	   	}
    }

En el xhtml

    <h:inputText id="urlInput" value="#{userData.data}" label="URL" >
	   <f:converter converterId="com.tutorialspoint.test.UrlConverter" />
	</h:inputText>


### Validadores
Los validadores son componentes de JSF que se encargan de validar que los datos han sido ingresados en el formato esperado.

<table>
	<tr>
		<th>Tag</th>
		<th>Descripción</th>
		<th>Atributos</th>
	</tr>
	<tr>
		<td>f:validateLength</td>
		<td>Valida longitud de una cadena</td>
		<td>minimum="5" maximum="8"</td>
	</tr>
	<tr>
		<td>f:validateLongRange</td>
		<td>Valida rango de valor numérico</td>
		<td>minimum="5" maximum="200"</td>
	</tr>
	<tr>
		<td>f:validateDoubleRange</td>
		<td>Valida gama de valor flotante</td>
		<td>minimum="1000.50" maximum="10000.50" </td>
	</tr>
	<tr>
		<td>f:validateRegex</td>
		<td>Valida los componentes JSF con una expresión regular dada.</td>
		<td>pattern="((?=.*[a-z]).{6,})"</td>
	</tr>
	<tr>
		<td>f:validator</td>
		<td>Validador Generico</td>
		<td>Utilizar la anotacion @FacesValidator e implementación de la interfaz javax.faces.validator.Validator
		</td>
	</tr>
</table>

### Etiquetas I18n
Los mensajes que se le muestran al usuario, las etiquetas y demas, pueden ser manejados como recursos internacionalizados utilizando el soporte i18n de JSF.

Se crea un archivo properties con las entradas. 

- Archivo messages_es.properties
	
	`mensaje.saludo = Hola mundo`

- Archivo messages_en.properties

	`mensaje.saludo = Hello world`

Se configura el faces-config.xml 

    <application>
	   <locale-config>
	      <default-locale>es</default-locale>
	      <supported-locale>en</supported-locale>
	   </locale-config>
	   <resource-bundle>
	      <base-name>com.tutorial.messages</base-name>
	      <var>msg</var>
	   </resource-bundle>
	</application>

Se utiliza en la aplicación.

    <h:outputText value="#{msg['mensaje.saludo']}" />


## ManagedBean Scopes ##

El controlador de JSF está basado en un Bean. Este es el ManagedBean. Antes se usaba la notación @ManagedBean, pero a partir de JavaEE7 se recomienda usar un @Name. Ya que técnicamente hacen lo mismo, esto es para reducir notaciones redundantes. 

Se tienen diferentes scopes 

- @RequestScoped: El Managed Bean esta vivo mientras dura el request.
- @ViewScoped: El Managed Bean está vivo mientras no cambiemos de pantalla.
- @SessionScoped: Ciclo de vida asociado a la sesión web del usuario.
- @ApplicationScoped: Ciclo de vida asociado a la aplicación, solo existe uno por maquina virtual.


## Configuración web.xml ##

Para activar JSF en nuestro proyecto se debe configurar el archivo web.xml con las siguientes entradas.

    <?xml version="1.0" encoding="UTF-8"?>
	<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns="http://java.sun.com/xml/ns/javaee"
        xmlns:web="http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd"
	xsi:schemaLocation="http://java.sun.com/xml/ns/javaee
	http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd"
	id="WebApp_ID" version="2.5">

		<display-name>JavaServerFaces</display-name>
	
		<!-- Change to "Production" when you are ready to deploy -->
		<context-param>
			<param-name>javax.faces.PROJECT_STAGE</param-name>
			<param-value>Development</param-value>
		</context-param>
	
		<!-- Welcome page -->
		<welcome-file-list>
			<welcome-file>faces/hello.xhtml</welcome-file>
		</welcome-file-list>
	
		<!-- JSF mapping -->
		<servlet>
			<servlet-name>Faces Servlet</servlet-name>
			<servlet-class>javax.faces.webapp.FacesServlet</servlet-class>
			<load-on-startup>1</load-on-startup>
		</servlet>
	
		<!-- Map these files with JSF -->
		<servlet-mapping>
			<servlet-name>Faces Servlet</servlet-name>
			<url-pattern>/faces/*</url-pattern>
		</servlet-mapping>
		<servlet-mapping>
			<servlet-name>Faces Servlet</servlet-name>
			<url-pattern>*.jsf</url-pattern>
		</servlet-mapping>
		<servlet-mapping>
			<servlet-name>Faces Servlet</servlet-name>
			<url-pattern>*.faces</url-pattern>
		</servlet-mapping>
		<servlet-mapping>
			<servlet-name>Faces Servlet</servlet-name>
			<url-pattern>*.xhtml</url-pattern>
		</servlet-mapping>

	</web-app>

## Configuración faces-config.xml ##
En este archivo es donde se hacen las configuraciones especificas de JSF.

	<?xml version="1.0" encoding="UTF-8"?>
	<faces-config xmlns="http://java.sun.com/xml/ns/javaee"
				xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
				xsi:schemaLocation="http://java.sun.com/xml/ns/javaee
				 http://java.sun.com/xml/ns/javaee/web-facesconfig_2_1.xsd"
				version="2.1">
	 
	</faces-config>

En el se agregan: 

- Archivos de Mensajes (i18n) 
- Convertidores (opcional)
- Validadores (opcional) 
- Modificaciones a componentes (opcional) 
- Reglas de navegacion (opcional)

# Primefaces #
PrimeFaces es una librería de componentes visuales open source desarrollada y mantenida por Prime Technology, que proporciona Componentes enriquecidos para desarrollar las aplicaciones web.

Las principales características de Primefaces son:

- Soporte nativo de Ajax, incluyendo Push/Comet.
- Kit para crear aplicaciones web para móviles.
- Es compatible con otras librerías de componentes, como JBoss RichFaces.
- Uso de javascript no intrusivo (no aparece en línea dentro de los elementos, sino dentro de un bloque `<script>`).
- Es un proyecto open source, activo y bastante estable entre versiones.


## Añadiendo soporte a primefaces ##
Para la versión actual de primefaces (v5.x y 6.x) solo es necesari agregar la dependencia de primefaces. 

    <!-- https://mvnrepository.com/artifact/org.primefaces/primefaces -->
	<dependency>
	    <groupId>org.primefaces</groupId>
	    <artifactId>primefaces</artifactId>
	    <version>6.0</version>
	</dependency>

	
En versiones anteriores era necesario configurar el archivo web.xml para que utilizara primefaces.


## Componentes Disponibles ##

Existe una gran variedad, muchos de ellos son extensiones de los componentes básicos de JSF, muchos otros son completamente nuevos, constantemente se están agregando nuevos componentes con cada versión. 

Para conocerlos mejor se puede acceder al showcase.

[Showcase de PrimeFaces](https://www.primefaces.org/showcase/)



# Referencias #
- http://www.apuntesdejava.com/2013/09/tutorial-javaserver-faces-22-java-ee-7.html
- https://www.adictosaltrabajo.com/tutoriales/introduccion-jsf-java/
- http://www.w3ii.com/es/jsf/default.html
- https://www.primefaces.org/showcase/