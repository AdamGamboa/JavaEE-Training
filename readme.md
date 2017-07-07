# Capacitación de Java EE #

Este repositorio ha sido creado para contener la información y código utilizado durante la capacitación para el desarrollo de aplicaciones Java.

## Objetivo
Es proveer de un conocimiento básico sobre el desarrollo de aplicaciones web en Java utilizando las principales tecnologias proveidas por Java EE. No se ven todas las tecnologias de Java EE, sin embargo se trabajaran sobre las que más uso tienen en las aplicaciones desarrolladas en la actualidad. 

Además, se ven algunos otras herramientas que no son parte de Java EE, pero que complementan en desarrollo de una aplicación web, tales como: Maven, Jasper Report.

## Temas

### Maven: 
Se tiene como objetivo dar una introducción a la creación de proyectos  la herramienta Maven. Estructura de un proyecto Maven y el manejo de dependencias en un proyecto. Además de profundizar en temas como proyectos multi-módulos, plugins y los profiles de compilación.

Los proyectos a utilizar durante las siguientes lecciones estarán basados en maven por lo cual es clave tener los conceptos y el manejo de esta herramienta claros.

Temas a tratar:

- Definición general de Maven
- Manejo de Dependencias
- Proyectos multi-módulos en Maven
- Profiles de Compilación
- Plugins 

### Java SE vs Java EE ###
Primeramente se intentará presentar las diferencias entre Java SE y Java EE, mostrando que es lo que incorpora un desarrollo llama al realizar sobre la plataforma JEE, sus ventajas y requisitos. 

Además se comenzará a inducir a una de las tecnologías que sirve de pilar de Java EE, la cual es CDI, utilizada en los siguientes ejercicios y tecnologías que se verán más adelante. Como parte de los ejercicios se hará un repaso de Servlets en Java y trabajar con CDI en dichos Servlets.

Temas a tratar: 

- Definición de Java SE 
- Definición de Java EE
- Requisitos para tener un proyecto Java EE 
- Trabajando con Servlets 
- CDI y su importancia en JEE.

### Java Persistence Api (JPA) e Hibernate ###
Se hará un paréntesis en el desarrollo JEE para realizar una introducción al tema de Java Persistence API (JPA). La idea en esta lección es sentar las bases de cómo se utiliza JPA y Hibernate desde una aplicación sin las facilidades de una aplicación JEE. 

El asistente configurar una conexión de base de datos, consultar, insertar, modificar y eliminar registros, haciendo uso de comandos explícitos en código para obtener la conexión a base de datos y sus transacciones. 

Temas a tratar:  

- ¿Qué es JPA?
- Entidades, Columnas, Id y demás anotaciones en JPA.
- Acciones a las bases de datos con JPA, Consultar, Insertar, Modificar y Eliminar. 
- Configuración de archivo persistence.xml 
- ¿Qué es Hibernate y cómo rol tiene en JPA?

### Profundizando JEE con EJBs ###
Se retoma el tema de aplicaciones JEE profundizando el conocimiento en EJBs y su razón de ser en este tipo de aplicaciones, exponiendo objetivo y ventajas al ser utilizados en el desarrollo de la lógica de negocio de un producto. Para este punto las lecciones de JPA, CDI y Servlets vistas en las lecciones anteriores serán de vital importancia ya que se irá relacionando la utilización de todos estos conceptos en un proyecto.

Temas a tratar

- Evolución de los EJBs
- Funcionamiento y características de los EJBs 
- EJBs: Stateless, Stateful, Singleton 
- JPA(Hibernate) en EJBs y manejo de Transacciones 
- Métodos Asíncronos
- Métodos Calendarizados

### Java Server Faces (JSF) y PrimeFaces
El objetivo de esta lección es mostrar el Framework de Java para desarrollo de interfaz Gráfica de Usuario a nivel Web el cual es Java Server Faces. El asistente debe ser capaz de crear páginas web que permitan una interacción enriquecida con el usuario desde Java, haciendo uso de los componentes de JSF y Primefaces. 

Se explicara la configuración básica para JSF y Primefaces en un proyecto y como utilizar sus componentes, así como la interacción de JSF con otros elementos con lógica de negocio como EJBs, JPA y CDI.

Temas a tratar:

- ¿Características de JSF?
- Componentes de JSF
- Scope de Managed Bean en JSF
- ¿Qué es Primefaces?
- Componentes de Primefaces

### Servicios Web SOAP  (JAX-WS) ###
Los servicios web de tipo SOAP son de vital importancia en el desarrollo de proyectos web, las aplicaciones se ven necesitadas de publicar a otras aplicaciones y consumir servicios web de diferentes fuentes para completar su funcionalidad, por lo que en esta lección se enseñara a cómo desarrollar e interactuar con servicios web de tipo SOAP desde Java.

Temas a tratar:

- ¿Qué es SOAP y JAX-WS?
- Publicar un Servicio web SOAP
- Consumir un Servicio web SOAP

### Servicios Web Restful (JAX-RS) ###
Además de los servicios web de tipo SOAP, actualmente los servicios Restful se encuentran ampliamente difundidos y utilizados en los sistemas web, brindando algunas ventajas sobre los primeros mencionados. Por lo cual en esta lección se mostraran conceptos para el desarrollo de servicios web Restful utilizando la tecnología JAX-RS, permitiendo publicar y consumir estos servicios desde una aplicación Java.

Temas a tratar: 

- ¿Qué es Restful y JAX-RS? 
- Publicar un Servicio Web de tipo Restful 
- Consumir un Servicio Web de tipo Restful
- Buenas prácticas en el desarrollo de un Rest API

### Reportes en Jasper Reports 
Se dará una breve introducción sobre la herramienta Jasper Reports y generación de reportes con ella. El objetivo de esta lección es que el asistente tenga la capacidad de enfrentarse al desarrollo de un reporte y su respectiva generación desde un proyecto Java.

Temas a tratar:

- ¿Qué es Jasper Reports?
- Creando un reporte en Jasper Reports
- Diferentes componentes a utilizar dentro del Reporte
- SubReportes
- Como generar y descargar un reporte desde una aplicación Java.

### Introducción a Java 8
El objetivo de esta sesión es presentar algunas de las nuevas características presentes con la versión 8 de Java, y que el asistente pueda utilizarlas y aplicarlas en algunos ejercicios para que sea capaz de emplear estos nuevos conceptos en desarrollos actuales.

Temas a tratar

- Nuevo Date/Time API
- Introducción a programación funcional con Lambda API
- Stream API
- Interfaces funcionales






