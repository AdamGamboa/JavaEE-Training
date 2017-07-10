# ¿Que son los EJB? #

EJB (Enterprise JavaBeans) es un modelo de programación que nos permite construir aplicaciones Java mediante objetos ligeros (como POJO's). Cuando construimos una aplicación, son muchas las responsabilidades que se deben tener en cuenta, como la seguridad, transaccionalidad, concurrencia, etc. 

El estandar EJB nos permite centrarnos en el código de la **lógica de negocio** del problema que deseamos solucionar y deja el resto de responsabilidades al contenedor de aplicaciones donde se ejecutará la aplicación.

## El Contenedor de EJBs ##
Un contenedor de EJBs es un entorno (en si mismo no es más que una aplicación) que provee los servicios comunes a la aplicacion que deseamos ejecutar, gestionándolos por nosotros. Dichos servicios incluyen la creación/mantenimiento/destrucción de nuestros objetos de negocio, así como los servicios mencionados en el punto anterior, entre otros.

Una vez escrita nuestra aplicación EJB, podemos desplegarla en cualquier contenedor compatible con EJB, beneficiandonos de toda el trabajo tras bastidores que el contenedor gestiona por nosotros. De esta manera la lógica de negocio se mantiene independiente de otro código que pueda ser necesario, resultando en código que es más fácil de escribir y mantener (además de ahorrarnos mucho trabajo).

# Evolución de los EJBs #
La especificación EJB ha ido evolucionando a la par que lo hacía la propia especificación J2EE. Las diferentes versiones que han existido hasta la fecha son:

- EJB 1.0: la especificación original.
- EJB 1.1: la primera incluida dentro de J2EE.
- EJB 2.0: incluida en J2EE 1.3, añadía las interfaces locales y los Message-Driven beans.
- EJB 2.1: incluida en la última revisión de J2EE, la 1.4.
- EJB 3.0: Ahora con Clúster y está incluida en JEE 5.1.
- EJB 3.1: incluida en JavaEE 6 en diciembre de 2009.
- EJB 3.2: Incluida en JavaEE 7 en Setiembre de 2013.

## Tipos de EJB

**Por su ubicación**

- Local : Se ejecutan en la misma máquina virtual
- Remoto : Se ejecutan en una máquina virtual distinta, incluso otro servidor. No son de mucho uso actualmente debido a la existencia de servicios web.

**Por su estado**

- Stateless : No requieren mantener un estado entre diferentes invocaciones.
- Stateful : Mantienen estado entre distintas invocaciones realizadas por el mismo cliente.
- Singleton : puede ser compartido por muchos clientes, de manera que una y solo una instancia es creada.

## Características ##
- Contienen lógica de negocio
- Autogestionados
- Reusabilidad
- Transaccionalidad
- Métodos Calendarizados
- Métodos Asíncronos

## ¿Cómo usar un EJB? ##
A partir de JavaEE 6 los EJBs estan fuertemente acoplados con CDI, por lo cual para utilizarlos se utiliza la anotación @Inject 

Otras formas de utilizarlos es por medio de: 

- @EJB
- JNDI Lookup

Sin embargo estas ultimas 2 opciones están en desuso.

## Manejo de Transacciones ##
Por defecto los EJB implementan la especificación de **JTA** para el manejo de transacciones. Es decir, en tareas de conexión a Base de Datos, no es necesario crear transacciones, hacer commit y rollback de manera explicita ya que estas tareas son realizadas por el contenedor de EJBs.

### Tipos de Transacciones ###
Se puede utilizar la anotación  @TransactionType sobre un método de un EJB o sobre la clase del EJB, para modificar el comportamiento de la transacción. 

- **MANDATORY**: Requiere que exista una transacción para ejecutarse el método, si no existe no crea ninguna, y lanza una excepción.
- **NEVER**: El cliente requiere llamar a este método sin que exista una transacción presente.  Si existe una lanza una excepción.
- **NOT_SUPPORTED**: Para entornos en donde la transacción no es soportada.
- **REQUIRED**: Pregunta si existe una transacción y la utiliza, si no existe crea una nueva. Es el valor por defecto.
- **REQUIRES_NEW**:Crea un nueva transacción.
- **SUPPORTS**: Ejecuta los mismos pasos de REQUIRED.

### EJBs y JPA ###
Debido al manejo de transacciones de los EJBs el uso de JPA se simplifica de gran manera. Ya no es necesario obtener la transación del EntityManager, empezar la transacción, hacer commit, hacer rollaback, y el EntityManager se puede injectar por medio de la anotación @PersistenceContext

    @Stateless
	public class MyEJB{
		
		@PersistenceContext	
		private EntityManager em;

		public void save(MyEntity object){
			em.persist(object);
		}
	}

## Métodos Calendarizados ##
Los EJBs implementan la especificación del TimeService, por lo cual permiten crear tareas programadas. Métodos que se ejecutaran solos dada una condición de tiempo. 

Utiliza formato cron, para indicar el momento en que se ejecutará la tarea.

    
	@Startup
	@Singleton
	public class MyEJB {
		
		@Schedule(second="*/1", minute="*",hour="*", persistent=false)
		public void myScheduleMethod(){
			System.out.println("Hello World!");
		}
	}


## Métodos Asincronos ##

Esta es una de las características más interesantes, los métodos asincronos una vez llamados, el flujo principal retorna al cliente antes que se termine la ejecución del método, sin embargo su lógica es ejecutada en segundo plano. 

Un método se marca como asincrónico usando la anotación @Asynchronous, la cual puede aplicarse a un método o a una clase.

    @Stateless
	public class MyEJB {
		
		@Asynchronous
		public void myAsyncMethod(String param){
			for(int i=0; i>1000000; i++){
				//nothing
			}
		}
	}

El tipo de retorno de un método asincrónico debe ser void o Future, siendo V el tipo del valor del resultado. Si se usa void, entonces el método no puede declarar excepciones.

La interfaz Future se agregó a Java 5 y brinda cuatro métodos:

- cancel(boolean mayInterruptIfRunning): Intenta cancelar la ejecución del método asincrónico. El contenedor intentará cancelar la invocación si todavía no fue iniciada. Si la cancelación resulta exitosa el método devuelve true, sino false. 

- get(): Retorna el resultado del método cuando termina. Este método tiene dos versiones sobrecargadas, una que se bloquea hasta que termine la ejecución, y la otra que recibe un timeout como parámetro.
- isCancelled() : Indica si el método fue cancelado.

- isDone() : Indica si el método terminó exitosamente.

La especificación indica que el contenedor debe proveer la clase AsyncResult, que consiste en una implementación de la interfaz Future que toma el valor de resultado como parámetro del constructor.
	
	@Stateless
	public class MyEJB{
	    @Asynchronous
		public Future<String> sayBye() {
		    String bye = executeLongQuery();
		    return new AsyncResult<String>(bye);
		}
	}

	public class MyClient{
		@Inject MyEJB myEJB;
		
		public void method(){
			Future<String> byeFuture = myEJB.sayBye();
			String bye = byeFuture.get();	
		}
	}

#Referencias
- http://www.davidmarco.es/articulo/introduccion-a-ejb-3-1-i
- http://www.arquitecturajava.com/introduccion-a-ejb-3-1-i/
- https://es.wikipedia.org/wiki/Enterprise_JavaBeans 
- http://www.adam-bien.com/roller/abien/entry/simplest_possible_ejb_3_16
- https://dosideas.com/noticias/java/528-ejb-31-un-paso-importante-hacia-la-madurez
