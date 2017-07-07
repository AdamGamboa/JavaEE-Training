# ¿Qué es Maven? 
Maven es una herramienta de software para la gestión y construcción de proyectos.

- Construir y estructurar un proyecto
- Manejar Dependencias
- Compilación de código y empaquetado
- Reportes y Documentación

Fue lanzado en 2004, con el objetivo de mejorar algunos aspectos de Apache Ant. 

## Mas Sobre Maven ##
- Basado en el principio de Convención sobre Configuración
- Configuración por XML, por medio del archivo POM.xml
- Tiene un ciclo de vida definido (compile, test, package, install, deploy)
- Permite la configuración de plugins, para realizar tareas.
- Proyectos Agnosticos. Utilizables en cualquier Herramienta de Desarrollo (IDE: Netbeans, Eclipse, InteliJ, etc)

## Maven vs Ant vs Gradle ##
### Apache ANT ###
- ANT fue la primera herramienta “moderna” de construcción de proyectos. Lanzado en el año 2000.
- Más orientado a crear scripts, para ejecutar las tareas.
- Configuración por archivos XML. Muy procedimental.
### Gradle ###
- Fue lanzado en el ano 2012. 
- Trata de potenciar las ventajas de Ant y Maven
- Configuración por formato Groovy.
- Utilizado por google para proyectos Android.

# Repositorios Maven #

## Repositorio Central Maven ##
Es un sitio web donde se encuentra un gran número de librerías disponibles para ser utilizadas por cualquier proyecto.
 
Por defecto un proyecto Maven buscará en este sitio si no encuentra las dependencias en repositorios locales.

Se puede acceder en: [https://search.maven.org/](https://search.maven.org/)

## Repositorios Remotos ##
En determinados entornos, Maven puede ser más que suficiente pero en grandes organizaciones puede que no.

- Acceso a Internet. 
- Consumo de ancho de banda.
- Uso de librerias propietarias excluidas de repositories.
- Publicar librerias propieas de la empresa.
- Facilitar el proceso de Integración Continua (CI)

Se puede instalar un Administrador/Gestor de Repositorios Maven.
- Sonatype Nexus
- Artifactory
- Archiva


## Repositorio Local ##
¿Dónde deja maven los jar que se baja?

Además de los repositorios remotos también existe un repositorio local que lo utiliza como caché evitando la descarga en las siguientes generaciones del proyecto. 

Se reduce el tiempo que supondría volver a descargarse todos las librerías.
Todos los artefactos y librerias que se manejen en maven se guardarán en la carpeta `.m2/repository` ubicada en el directorio `/home` del usuario.


# Ciclo de Vida #
El ciclo de vida de Maven esta conformado por Goals/Metas. Las partes del ciclo de vida principal del proyecto Maven son:

- **compile:** Genera los ficheros .class compilando los fuentes .java

- **test:** Ejecuta los test automáticos de JUnit existentes, abortando el proceso si alguno de ellos falla.
 
- **package:** Genera el fichero .jar con los .class compilados
 
- **install:** Copia el fichero .jar a un directorio de nuestro ordenador donde maven deja todos los .jar. De esta forma esos .jar pueden utilizarse en otros proyectos maven en el mismo ordenador.
 
- **deploy:** Copia el fichero .jar a un servidor remoto, poniéndolo disponible para cualquier proyecto maven con acceso a ese servidor remoto.

Algunas metas están fuera del ciclo de vida por defecto pero pueden ser llamadas si se requiere.

- clean
- assembly
- site
- site-deploy
- Y mas….

Maven tiene una arquitectura de plugins, para poder ampliar su funcionalidad, aparte de los que ya trae por defecto.


#Dependencias 
Uno de los puntos fuertes de maven son las dependencias y su manejo.
Para indicarle a maven que necesitamos un jar determinado, debemos editar el fichero pom.xml

    <dependencies>
    	<dependency>
      		<groupId>junit</groupId>
      		<artifactId>junit</artifactId>
      		<version>3.8.1</version>
      		<scope>test</scope>
    	</dependency>
  	</dependencies>

Una dependencia puede ser: una librería (jar, war, ear) u otro proyecto maven.


## Grupos, Artefactos, Version (GAV) ##
Las dependencias se identifican por un Grupo, Artefacto y versión.

### Artefacto ####
Es un componente de software que podemos incluir en un proyecto como dependencia. 
Normalmente será un jar, pero podría ser de otro tipo, como un war por ejemplo.
 
Los artefactos pueden tener dependencias entre sí, por lo tanto, si incluimos un artefacto en un proyecto, también obtendremos sus dependencias.

### Grupo
Un grupo es un conjunto de artefactos. Es una manera de organizarlos. Así por ejemplo todos los artefactos de Spring Framewok se encuentran en el grupo org.springframework.

### Versión
Es la versión del artefacto.



## Scope
El scope sirve para indicar el alcance de nuestra dependencia y su transitividad. Hay 6 tipos:

- **compile:** es la que tenemos por defecto sino especificamos scope. Indica que la dependencia es necesaria para compilar. La dependencia además se propaga en los proyectos dependientes.

- **provided:** Es como la anterior, pero esperas que el contenedor ya tenga esa librería. Un claro ejemplo es cuando desplegamos en un servidor de aplicaciones, que por defecto, tiene bastantes librerías que utilizaremos en el proyecto, así que no necesitamos desplegar la dependencia.

- **runtime:** La dependencia es necesaria en tiempo de ejecución pero no es necesaria para compilar.

- **test:** La dependencia es solo para testing que es una de las fases de compilación con maven. JUnit es un claro ejemplo de esto.

- **system:** Es como provided pero tienes que incluir la dependencia explicitamente. Maven no buscará este artefacto en tu repositorio local. Habrá que especificar la ruta de la dependencia mediante la etiqueta `<systemPath>`

- **import:** este solo se usa en la sección dependencyManagement. 






# Estructura de Carpetas en un Proyecto Java con Maven #

Maven nos ayuda a solventar el problema que cada proyecto, IDE, desarrollador puede crear una estructura de un proyecto. Proporciona una estructura de carpetas COMÚN para todos los proyectos.

![Image](http://www.arquitecturajava.com/wp-content/uploads/00420.png)

- **src/main/java:** Contiene el código fuente con nuestras clases Java incluida la estructura de paquetes


- **src/main/resources:** Contiene ficheros de recursos como imagenes ficheros .properties etc


- **src/test/java:** Contiene el código fuente con nuestras clases Java para realizar test


- **src/test/resource:** Contiene ficheros de recursos como imagenes ficheros .properties etc para nuestros test


- **target:** Contiene los desplegables que generamos con Maven jar,war,ear etc


- **pom.xml:** Ya hemos hablado de el y es el fichero encargado de definir el concepto de Artefacto. 

## Archivo POM.XML ##
Este es un ejemplo básico de un archivo POM.xml, debido al principio de Convencion sobre Configuración con este minimo archivo ya se toma por hecho la configuración de estructura carpetas del proyecto sin necesidad de indicarlo.

    <project xmlns="http://maven.apache.org/POM/4.0.0"
    		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    		xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    
		<modelVersion>4.0.0</modelVersion>
    	
		<groupId>com.hop2croft.examples</groupId>
    	<artifactId>example-post</artifactId>
    	<version>0.0.1-SNAPSHOT</version>
    
		<name>EjemploMaven</name>
    	<description>Ejemplo de proyecto con Maven</description>
	</project>

Al archivo pom.xml se le pueden agregar secciones para: 

- Propiedades: `<properties>...</propierties>`
- Dependencias: `<dependencies>...<dependencies/>`
- Módulos: `<modules>...</modules>`
- Construcción: `<build/>`
- Plugins: `</plugins>`
- Profiles: `<profiles/>`
- Información de Desarrollo: `<developers>, <mail-list>`


# Proyecto multi-módulo en Maven #
Maven soporta agregración ademas de herencia de proyectos, con los cual se permite la creación de proyectos con multiples módulos. Un proyecto multi-modulo esta definido por un POM padre referenciando uno o más sub-módulos.El proyecto padre no crea un jar/war como los proyectos ya vistos. En lugar de eso, el solamente hace referencia a otros proyectos maven. 

Las configuraciones del pom padre, son heredadas a todos los submodulos. Esto nos permite configurar dependencias/plugins y comportamientos para todos los proyectos. Los sub-módulos deben hacer referencias ademas al POM padre.


## POM PADRE ##

    <project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                             http://maven.apache.org/maven-v4_0_0.xsd">
    	<modelVersion>4.0.0</modelVersion>

	    <groupId>com.example.myapp</groupId>
	    <artifactId>simple-parent</artifactId>
	    <packaging>pom</packaging>
	    <version>1.0</version>
	    <name>Proyecto Padre</name>
	
	    <modules>
	        <module>simple-module1</module>
	        <module>simple-module2</module>
	    </modules>
		
		<dependencies> 
			...
		</dependencies>
		
		<build>
			...
		</build>
	</project>


## POM SUB-MÓDULO ##

    <project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                             http://maven.apache.org/maven-v4_0_0.xsd">
	    <modelVersion>4.0.0</modelVersion>
	    <parent>
	        <groupId>com.example.myapp</groupId>
	        <artifactId>simple-parent</artifactId>
	        <version>1.0</version>
	    </parent>
	
	    <artifactId>simple-module1</artifactId>
	    <packaging>jar</packaging>
	    <name>Simple Submodule 1</name>
	
	    <build>
			...
		</build>
	</project>

# Plugins #
En Maven tenemos los siguientes tipos de plugins: 

- Build Plugins: Ejecutados durante la fase de build 
- Reporting Plugins: Ejecutados durante la generación del sitio (site-deploy)

Un plugin permite extender el comportamiento de maven y realizar tareas especificas. Cada tarea en maven es un plugin, maven trae algunos instalados, se pueden instalar nuevos.

- Crear servicios-web
- Crear clases nuevas a partir de un xml 
- Generar reportes estadisticas de codigo
- Copiar archivos en otra direccion.


# Profiles #
Los profiles es una forma de agregar un subconjunto de elementos disponibles en el POM.xml, para tener diferentes configuraciones, ambientes, parámetros, según las necesidades, manteniendo la portabilidad del proyecto. El pom resultante es construido al momento de construir el proyecto. Esto cuando se indica utilizer un profile específico. 

El uso más comun es para generar un build con configuración y propiedades diferentes según el tipo de ambiente: desarrollo, pruebas, producción.

## Tipos de Profiles ##
**Por Proyecto**
- Definido en el mismo POM `(pom.xml)`.

**Por Usuario**
- Definido en las configuraciones de Maven/Maven-settings `(%USER_HOME%/.m2/settings.xml)`.

**Global**
- Definido en la configuración global de Maven/global Maven-settings `(${maven.home}/conf/settings.xml)`.

# Referencias #
- https://hop2croft.wordpress.com/2011/04/28/estructura-basica-de-un-proyecto-con-maven/
- http://www.arquitecturajava.com/maven-ii/
- https://www.adictosaltrabajo.com/tutoriales/repositorios-maven/
- http://chuwiki.chuidiang.org/index.php?title=Dependencias_con_maven
- http://books.sonatype.com/mvnex-book/reference/multimodule.html
- http://maven.apache.org/guides/introduction/introduction-to-profiles.html