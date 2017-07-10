# ¿Qué es JPA? #
Java Persistence API, más conocida por sus siglas JPA, es la API de persistencia desarrollada para la plataforma Java EE.

Es un framework del lenguaje de programación Java que maneja datos relacionales en aplicaciones usando la Plataforma Java en sus ediciones Standard (Java SE) y Enterprise (Java EE).

El objetivo que persigue el diseño de esta API es no perder las ventajas de la orientación a objetos al interactuar con una base de datos y permitir usar objetos regulares.

## ¿Por qué JPA?
Objetos relacionales están representados en un formato tabular, mientras que modelos de objetos son representados en un gráfico de interconexión formato de objeto. 

## JPA Proveedores ##
JPA es una API open source, por lo tanto distintos proveedores como Oracle, Redhat, Eclipse, etc. proporcionar nuevos productos mediante la adición de la persistencia JPA sabor en ellas. Algunos de estos productos incluyen:

- Hibernate
- Eclipselink (Implementación de Referencia)
- Toplink
- Muelle Datos JPA
- etc.

## Conceptos en JPA ##
En la siguiente sección se aclaran los siguientes conceptos

- Persistence.xml
- EntityManagerFactory
- EntityManager
- Entity
- Query


### Persistence.xml ###
El primer concepto es del fichero persistence.xml que se encuentra ubicado en la carpeta META-INF . Este fichero se encarga de conectarnos a la base de datos y define el conjunto de entidades que vamos a gestionar.

	<persistence xmlns="http://java.sun.com/xml/ns/persistence"
	 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	 xsi:schemaLocation="http://java.sun.com/xml/ns/persistence
	 http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd"
	 version="2.0">
		 <persistence-unit name="UnidadPersonas">
			<class>es.curso.bo.Persona</class>
			 
			<properties>
				 <property name= "hibernate.show_sql" value="true" />
				 <property name="hibernate.dialect" value="org.hibernate.dialect.MySQLDialect" />
				 <property name="javax.persistence.jdbc.driver" value="com.mysql.jdbc.Driver" />
				 <property name="javax.persistence.jdbc.user" value="root" />
				 <property name="javax.persistence.jdbc.password" value="jboss" />
				 <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost/jpa" />
			</properties>
		 
		</persistence-unit>
	</persistence>

### Entity Manager Factory ###
En un primer lugar un *EntityManagerFactory* es único y es con el que nosotros gestionamos todas las entidades. Si tenemos conexiones a diferentes base de datos deberemos definir un nuevo concepto que nos permite clarificar que tenemos dos *EntityManagerFactories* distintos. Este concepto es el que se conoce como **PersistenceUnit**.

Es decir, existe un *EntityManagerFactory* con su respectivo Persistence Unit, para cada origen de datos distinto.

### Entity Manager ###
Una vez disponemos de un *EntityManagerFactory* este será capaz de construir un objeto de tipo **EntityManager** que como su nombre indica gestiona un conjunto de entidades u objetos.

En principio estas entidades son objetos POJO normales con los cuales estamos trabajando en nuestro programa Java .El **EntityManager** será el encargado de guardarlos, consultarlos, modificarlos y eliminarlos de la base de datos. 

Por medio del Entity Manager es que realizamos una conexión a la base de datos y su respectiva transacción.

    public class ServicioPersona {
 
		public Persona guardar(Persona persona) {
			 EntityManagerFactory emf =Persistence.createEntityManagerFactor("UnidadPersonas");
			 EntityManager em = emf.createEntityManager();
			 try {
				 em.getTransaction().begin();
				 em.persist(persona);
				 em.getTransaction().commit();
			 } catch (Exception e) {
			 	e.printStackTrace();
			 }finally {
			 	em.close();
			 }
		 }
	}

### Entity/Entidad ###
Se conoce como entidad a los objetos en Java que estamos mapeando a la base de datos. Una Entidad puede ser una tabla o una vista de base de datos. 

### Query ###
Las consultas o queries es la manera de realizar enviar instrucciones a la base de datos. Usualmente se maneja un dialecto de SQL orientado a objetos conocido como JPQL, se indican los objetos y sus atributos en lugar de tablas y columnas.

Las consultas también se pueden hacer en SQL Nativo, si es necesario.


## Métodos en el Entity Manager ##
El *Entity Manager* proporciona las funciones para interactuar con la base se datos, insertar, modificar, encontrar, consultar listados, eliminar registros, ademas de crear transacciones y realizar commit.

**Persist:** Es el encargado de almacenar nuevas entidades en base de datos. 

`em.persist(<entidad>)`

**Merge:** Es el encargado de actualizar un entidad en base de datos. 

`em.merge(<entidad>)`

**Contains:** Comprueba si una entidad esta gestionada por el EntityManager devolviendo true o false según corresponda.

`em.contains(<entidad>)`

**Find:** Este método se encarga de localizar una Entidad a traves de su clave primaria. Para ello necesita que le pasemos la clave y el tipo de Entidad a buscar. Eso si recordemos que para que nos encuentre la entidad debemos haber sobrecargado los métodos equals y hashcode de forma correcta .

 `em.find(Entidad.class, entidad.getId())`    

**Remove:** Este método se encarga de eliminar una entidad de la base de datos.

`em.remove(<entidad>)`


## Entity y Mapeo de Tablas ##

- @Entity: Indica que un objeto no es una entidad
- @Table: Mapea el objeto a una tabla/vista de base de datos
- @Column: Mapeo un campo del objeto a una columna de la base de datos
- @Id: Indica cual de los campos de la tabla es la llave primaria


	    @Entity
    	@Table(name="persona")
    	public class Persona{
			
			@Id 
			@Column
			private String cedula;

			@Column("nombre_completo")
			private String nombreCompleto

			@Column
			private int edad;

			private String telefono;
    	}

### Relaciones ###
 
**@ManyToOne:** Es la relación de muchos a 1. En JPA se representa como un objeto que contiene a otro. 

En base de datos
	
	departamento: 
	departamento_id, nombre_departamento, compania_id
	
	empleado: 
	cedula, nombre, departamento_id

Mapeado a objetos
 
	@Entity
	public class Departamento{
		...
	}

	@Entity
	public class Empleado{
		
		@ManyToOne
 		//@JoinColumn(name="departamento_id") es opcional
		private Departamento departamento;
	}


**@OneToOne:** Es la relación 1 a 1. En JPA se representa como un objeto que contiene a otro.

En base de datos
	
	empleado: 
	cedula, nombre, empleado_estado_id

	empleado_estado: 
	empleado_estado_id, estado, fecha

Mapeado a objetos
 
	@Entity
	public class Estado{
		...
	}

	@Entity
	public class Empleado{
		
		@OneToOne
		@Column("empleado_estado_id)
		private EmpleadoEstado empleadoEstado;
	}

**@OneToMany:** Es la relación de 1 a muchos. En JPA se representa como un objeto que contiene un listado de otra Entidad. 

En base de datos
	
	departamento: 
	departamento_id, nombre_departamento, compania_id
	
	empleado: 
	cedula, nombre, departamento_id

Mapeado a objetos
 
	@Entity
	public class Departamento{
		@OneToMany
		@JoinColumn(name="empleado_id")
		private List<Empleado> empleados;
	}

	@Entity
	public class Empleado{
		
	}

**@ManyToMany:** Es la relación muchos a muchos. En JPA ambas entidades tienen un listado de la otra. No existe un mapeo a la tabla intermedia. 

En base de datos
	
	tarea: 
	tarea_id, nombre_tarea, duracion

	tarea_empleado: 
	tarea_id, empleado_id
	
	empleado: 
	cedula, nombre, departamento_id

Mapeado a objetos
 
	@Entity
	public class Tarea{
		
		@ManyToMany
		@JoinTable(name="tarea_empleado", 
		  joinColumns=@JoinColumn(name="tarea_id"),
		  inverseJoinColumns=@JoinColumn(name="empleado_id"))
		private List<Empleado> empleados
	}

	@Entity
	public class Empleado{
		
		@ManyToMany
		@JoinTable(name="tarea_empleado", 
		  joinColumns=@JoinColumn(name="empleado_id"),
		  inverseJoinColumns=@JoinColumn(name="tarea_id"))
		private List<Tarea> tareas;
	}


### Llaves Primarias compuestas
- **@EmbeddedId:** Indica el Id del objeto cuando la llave primaria es compuesta en base de datos
- **@Embeddable:** Se agrega a un objeto Java para indicar que todos sus campos son incrustables en una entidad. Es utilizado para llaves compuestas.


## Queries y  Tipos de Queries ##
Los queries son los objetos por los cuales podemos enviar sentencias o instrucciones a la base de datos. Un Query puede retornar un listado de valores o solamente 1 registro, ademas se puede utilizar el objeto query para ejecutar un comando de insert, update, delete. 



### Query ##
Crea queries en JPQL, usando los objetos y sus atributos para indicar las tablas y columnas a consultar.

    em.createQuery("Select u from Usuario where u.name = :name")
		.setParameter("name","Adam")
		.getResultList();

### Named Query ##
Son queries en JPQL, que ya se encuentran disponibles y analizadas durante el despliegue de la aplicación. De esta manera proporcionan un mejor rendimiento, ya que no deben ser analizadas de nuevo.

    @Entity
	@NamedQuery(name="Entidad.findByType", "select e Entidad e where type=?")
	public Entidad{
	    ...
	
		private String type;
	}
	
	//Usarlo consultar
	em.createNamedQuery("Entidad.findByType").setParameter(1,"tipo1").getResultList();

### Native Query ##
Son queries en lenguaje SQL, se suelen utilizar cuando la complejidad de la consulta supera las caracteristicas de JPQL. 

    String sql = "Select distinct name usuario u left join .... 
				+ "....." 
				+ "and u.age IN (select top 1 from table2 where ....)"
				+ "having u.age > 30 " 
	em.createNativeQuery(sql).getResultList();

### Métodos en un Query ###

- *`setParameter(int index, Object parameter) :`* Setea un parámetro por el indice de este en el query. Ejm ?1
- *`setParameter(String name, Object parameter) :`* Setea un parámetro según el nombre del parámetro en el query. Ejm :name
- *`setMaxResults(int maxResults) :`* Setea la cantidad de resultados a traer en el listado.
- *`setFirstResult(int firstResult):`* Setea el indice desde el cual se obtendran resultados en la consulta.
- *`getResultList() :`* Obtiene un listado con el resultado de la consulta.
- *`getSingleResult() :`* Obtiene solamente un objeto de la consulta.
- *`executeUpdate() :`* Ejecuta el query sin esperar un resultado de consulta.


# Hibernate y JPA #

Hibernate es una framework de mapeo objeto-relacional (ORM) para la plataforma Java. 

Es compatible con la especificación de JPA, por lo cual puede utilizarse como una implementación en lugar de la implementación de referencia JPA. 
 
Esto no quiere decir que Hibernate simplemente implemente el standard de JPA. Hibernate es mucho más grande que la especificación de JPA y añade más funcionalidad.

Se puede utilizar hibernate sin JPA, sin embargo ese no es el objetivo y enfoque de este curso.


# Referencias
https://es.wikipedia.org/wiki/Java_Persistence_API
https://www.tutorialspoint.com/es/jpa/jpa_introduction.htm
http://www.arquitecturajava.com/ejemplo-de-jpa/
http://www.arquitecturajava.com/jpa-entitymanager-metodos/