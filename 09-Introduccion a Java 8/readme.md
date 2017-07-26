# Introducción a Java 8 #
Las características más importantes de Java SE 8 son la adición de Expresiones Lambda y la API Stream, asi como el nuevo Date Time API. En esta lección se dara una **breve** introducción a estas nuevas caracteristicas. 

## Lambda ##
Con la adición de expresiones lambda podemos crear código más conciso y significativo, además de abrir la puerta hacia la programación funcional en Java, en donde las funciones juegan un papel fundamental. 

Uno de los  conceptos  de la programación funcional habla de que las  funciones  (métodos) sean definidas como entidades de primer nivel, es decir, que puedan aparecer en partes del código donde otras entidades de primer nivel, como valores primitivos u objetos, aparecen. Esto significa poder pasar funciones, en tiempo de ejecución, como valores de variables, valores de retorno o parámetros de otras funciones. 

### Comparemos 

Ejemplo con codigo actual

	List<Camisa> camisas  = …
	List<Camisa> camisasRojas = filtrar(camisas, "ROJO");
	List<Camisa> camisasXL = filtrar(camisas, "XL");

	public static List<Camisa> filtrarColor(List<Camisa>  inv, String color){ 
		List<Camisa>  sub = new ArrayList<>();
	   	for(Camisa camisa:  inv){
	   		if( camisa.getColor().equals(color) ) { 
				sub.add(camisa); 
			} 
	   } 
	   return sub;
	} 

	public static List<Camisa> filtrarTamano(List<Camisa>  inv, String tamano){ 
		List<Camisa>  sub = new ArrayList<>();
	   	for(Camisa camisa:  inv){
	   		if( camisa.getTamano().equals(tamano) ) { 
				sub.add(camisa); 
			} 
	   } 
	   return sub;
	}

Ejemplo utilizando lambda 

	List<Camisa> camisas  = …
	List<Camisa> camisasRojas = filtrar(camisas,
			((Camisa c) ­-> “ROJO”.equals(c.getColor()))); 
	List<Camisa> camisasXL = filtrar(camisas,
			((Camisa c) ­-> “XL”.equals(c.getTamano()))); 
	List<Camisa> camisasTShirt = filtrar(camisas,
			((Camisa c) ­-> “T-Shirt”.equals(c.getEstilo()))); 

	public static List<Camisa> filtrar(List<Camisa> inv, Predicate<String> predicado){
		List<Camisa>  sub = new ArrayList<>();
		for(Camisa camisa : inv){
			if(predicado.test()){
				sub.add(camisa)
			}
		}
		return sub;
	}

### Expresiones Lambda  ###
Por medio de expresiones lambda podemos referenciar métodos anónimos o métodos sin nombre, lo que nos permite escribir código más claro y conciso que  cuando usamos clases anónimas. Una expresión lambda se compone de:

- Listado de parámetros separados por comas y encerrados en paréntesis, por ejemplo: (a,b).
-  El símbolo de flecha hacia la derecha: ->
- Un cuerpo que puede ser un bloque de código encerrado entre llaves o una sola expresión.

A continuación algunos ejemplos de expresiones lambda:

	(int a, int b) ­->  a + b
	(int a) ­-> a +  1
	(int  a, int b) -­> { System.out.println(a + b);  return a + b; } 
	() -­> new ArrayList()

### Interfaces funcionales ###
Concepto nuevo en Java SE 8 y que es la base para que podamos escribir expresiones lambda. Una interface funcional se define como una interface que tiene uno y solo un método abstracto y que éste sea diferente a los métodos definidos en java.lang.Object (a saber: equals, hashcode, clone, etc.). La interface puede tener métodos por defecto y estáticos sin que esto afecte su condición de ser interface funcional.

Existe una nueva anotación denominada @FunctionalInterface que permite al compilador realizar la validación de que la interface tenga solamente un método abstracto. 

- **java.util.function.Predicate<T>:** Define  el  método  +test(T):boolean  y  es  usada  para validación de criterios.
- **java.util.function.Supplier<T>:** Define el método +get():T y es usada para la creación de objetos.
- **java.util.function.Consumer<T>:** Define el método +accept(T):void y es usada para consumir métodos del parámetro T, causando posibles efectos secundarios.
- **java.util.function.Function<T, R>:** Define el método +apply(T):R y es usada para convertir de un valor T a otro valor R.



## Stream API ##
la API Stream nos permite realizar operaciones de tipo filtro/mapeo/reducción sobre colecciones de datos de forma secuencial o paralela y que su implementación sea transparente para el desarrollador. 

Stream se define como una secuencia de elementos que provienen de una fuente que soporta operaciones para el procesamiento de sus datos:

- De forma declarativa usando expresiones lambda.
- Permitiendo el posible encadenamiento de varias operaciones, con lo que se logra tener un código fácil de leer y con un objetivo claro.
- De forma secuencial o paralela (Fork/Join).

Las estructuras que soportan esta nueva API se encuentran en el paquete java.util.stream y en especial, la interface java.util.stream.Stream define un Stream.

### Partes de un Stream ###

Un Stream se componen de 3 partes: 

	List transacciones = ...  
	int  sum = transacciones.stream()  	
     		.filter(t ->  t.getProveedor().getCiudad().equals(“Cali”))
     		.mapToInt(Transaccion::getPrecio)
     		.sum();

- **Fuente de información**: La colección que posee los datos a procesar. La lista de transacciones
- **Operaciones intermedias**: Se puede apreciar la operación filter y la operación mapToInt, operaciones que serán explicadas más adelante en este mismo artículo.
- **Operación terminal**:  Es la que produce un resultado o un efecto sobre los datos. Se puede apreciar la operación sum, la cual se explicará más adelante en este mismo artículo.

### ¿Cómo obtener instancias de Stream?	 ###

Existen varias formas de obtener instancias de un Stream, a continuación veremos algunas de ellas:

**Stream.of(T...): Stream<T>**
Retorna un Stream ordenado y secuencial de los elementos pasados por parámetro.

	Stream orquestas = Stream.of("Grupo Niche",  "Guayacán", "Son de Cali"); 

**Arrays.stream(T[]):Stream<T>**
Retorna un Stream secuencial del arreglo pasado por parámetro. Si T[] es un arreglo de datos “primitivos” entonces retorna: DoubleStream, IntStream o LongStream según el caso.

	int[]  enteros = new int[]{1,2,3,4,5};  
	IntStream  streamEnteros = Arrays.stream(enteros); 

**Collection<E>.stream():Stream<E>**
Retorna un Stream secuencial de los elementos de la colección, para obtener una versión en paralelo basta con usar: Collection<E>.parallelStream():Stream<E>

	List<String>  canciones = ...; 
	Stream<String>  streamCanciones = canciones.stream(); 

### Operaciones sobre colecciones de datos ###

Ahora que sabemos de qué trata la API Stream y cómo podemos crear/obtener objetos de tipo java.util.stream.Stream, es el momento de que veamos lo que podemos lograr con esta nueva API a la hora de realizar operaciones sobre colecciones de datos.

Iniciamos con la selección de los datos. Para filtrar los elementos de un Stream podemos usar los siguientes métodos:

**filter(Predicate<T>):Stream<T>**
Retorna un Stream que contiene sólo los elementos que cumplen con el predicado pasado por parámetro.

	List<String> ciudades = ... 
	//Stream de ciudades cuya primera letra es C de Cali 
	Stream  stream = ciudades.stream() 
		.filter(s ->  s.charAt(0) == 'C'); 

**distinct():Stream<T>**
Retorna un Stream sin elementos duplicados. Depende de la implementación de  equals(Object):boolean.

	List<String> ciudades =  Arrays.asList("Cali", "Bogotá", "Medellín",  "Cali"); 
	//Stream sin ciudades repetidas: Cali, Bogotá,  Medellín 
	Stream  stream = ciudades.stream().distinct(); 

**limit(long):Stream<T>**
Retorna un Stream cuyo tamaño no es mayor al número pasado por parámetro. Los elementos son cortados hasta ese tamaño.

	List<String> ciudades =  Arrays.asList("Cali", "Bogotá", "Medellín"); 
	//Stream limitado a los dos primeros elementos: Cali,  Bogotá 
	Stream  stream = ciudades.stream().limit(2); 

**skip(long):Stream<T>**
Retorna un Stream que descarta los primeros N elementos, donde N es el número pasado por parámetro. Si el Stream contiene menos elementos que N, entonces retorna un Stream vacío.

	List<String> ciudades =  Arrays.asList("Cali", "Bogotá", "Medellín"); 
	//Stream que ha saltado los dos primeros elementos,  quedando solo: Medellín 
	Stream  stream = ciudades.stream().skip(2); 

El siguiente paso es el mapeo de datos. Una vez hemos realizado la selección (filtro), podemos transformar los elementos de un Stream al extraer información de éstos. Para lograrlo usamos alguno de los siguientes métodos:

**map(Function<T, R>): Stream<R>**
Retorna un Stream que contiene el resultado de aplicar la función pasada por parámetro a todos los elementos del Stream. Transforma los elementos del tipo T al tipo R.

El siguiente ejemplo muestra cómo transformar un Stream de cadenas a otro Stream de esas mismas cadenas pero en mayúsculas. La función usada en este caso es una función (expresión lambda) dónde T y R son de tipo java.lang.String:

	List<String>  paises = Arrays.asList("Colombia", "Perú",  "Panamá"); 
	//Stream cuyos elementos son los países en mayúsculas 
	Stream<String>  stream = paises.stream().map(String::toUpperCase);

**flatMap(Function<T, Stream<R>):Stream<R>**
Permite transformar cada elemento del Stream en otro Stream y al final concatenarlos todos en uno solo. Es un poco confuso, pero pensemos que cuando tengamos un Stream<Stream<R>>, este método nos permitirá transformarlo en Stream<R>.

	List<String> lista = Arrays.asList("Taller", "Taller Lambdas y API  Stream"); 
	Stream stream = lista.stream() 
               .map(s ->  s.split(" ")) // Stream<String[]> 
               .map(Arrays::stream) //  Stream<Stream<String>> 
               .flatMap(Function.identity()) // Stream<String> 
               .distinct(); //  Stream<String> de 5 elementos 


### Operaciones Terminales ###

El último paso son las operaciones terminales, las cuales provocan que todas las operaciones intermedias sean ejecutadas.

**count():long**
Retorna la cantidad de elementos en el Stream . En el siguiente ejemplo, vemos como podemos obtener la cantidad de transacciones que existen en el Stream cuyo valor es mayor a dos mil:

	List<Transaccion> trxs = ... 
	 long  count = trxs.stream() 
             .filter(t -> t.getValor()  > 2000) 
             .count(); 

**max(Comparator<T>):Optional<T>**
Retorna el elemento máximo del Stream basado en el comparador pasado por parámetro. 

	List<Transaccion> trxs = ... 
	Optional<Transaccion>  max = 
         trxs.stream() 
             .max(Comparator.comparingDouble(Transaccion::getValor));

**min(Comparator<T>):Optional<T>**
Retorna el elemento mínimo del Stream basado en el comparador pasado por parámetro.

**allMatch(Predicate<T>):boolean**
Verifica si todos los elementos del Stream satisfacen el predicado pasado por parámetro. 

**anyMatch(Predicate<T>):boolean**
Verifica si alguno de los elementos del Stream satisface el predicado pasado por parámetro.

**noneMatch(Predicate<T>):boolean**
Contrario a allMatch(Predicate<T>):boolean, verifica si todos los elementos del Stream NO satisfacen el predicado pasado por parámetro.

**Collectors:**
Este método permite operaciones de redución y transformacion de datos a colecciones, tales como listas, map, sets, arreglos. 

Listas

	// Accumulate names into a List
     List<String> list = people.stream()
				.map(Person::getName)
				.collect(Collectors.toList());

Sets

     // Accumulate names into a TreeSet
     Set<String> set = people.stream()
				.map(Person::getName)
				.collect(Collectors.toCollection(TreeSet::new));

Mapas 

	Map<Department, List<Employee>> byDept
         = employees.stream()
                    .collect(Collectors.groupingBy(Employee::getDepartment));


## Date Time API ##
Llevamos mas de de una década trabajando con la clase Date y la clase Calendar en Java. Mucha gente cuando empieza se ha encontrado con verdaderos problemas a la hora de gestionar estas clases unidas a la clase DateFormat. A partir de Java 8 las cosas se van a simplificar ya que tenemos un nuevo API de DateTime mucho más intuitivo. 

Las clases definidas en este paquete representan los principales conceptos de fecha - hora, incluyendo instantes, fechas, horas, periodos, zonas de tiempo, etc. Están basados en el sistema de calendario ISO, el cual el calendario mundial de-facto que sigue las reglas del calendario Gregoriano

### Clases de fecha  ###
Las clases de fecha como el java.time.LocalDate manejan la fecha, pero, a diferencia del java.util.Date, es que es solo trabaja fecha, y no hora. Esto nos permitirá manipular la fecha para registrar fechas específicas como el día de cumpleaños o de matrimonio. Aquí unos ejemplos:

	LocalDate date = LocalDate.of(1999, Month.AUGUST, 23); 

Mas ejemplos: 

    LocalDate date = LocalDate.of(1989, 11, 11); //1989-11-11
    System.out.println(date.getYear()); //1989
    System.out.println(date.getMonth()); //NOVEMBER
    System.out.println(date.getDayOfMonth()); //11

También, se puede hacer uso del enum Month para dar legibilidad al código.

    LocalDate date = LocalDate.of(1989, Month.NOVEMBER, 11);

Finalmente, para capturar el LocalDate actual se puede usar el método now():

	LocalDate date = LocalDate.now();        


### Clase de Hora ###
La clase java.time.LocalTime es similar a las otras cosas que comienza con el prefijo Local, pero se centra únicamente en la hora. Esta clase es muy útil para representar horas y tiempos de un día, tales como la hora de inicio de una película o el horario de atención de una biblioteca. Se centra únicamente en la hora de un día cualquiera, pero no en una fecha específica.

Con el java.util.Date solo podemos manipular la hora de un día de un año en especial, de una zona de horario en especial, pero  con el LocalTime solo nos centramos en la hora en sí, sin importar que día sea. Normalmente lo manipularíamos con una cadena y de ahí hacemos raros algoritmos para saber si esa cadena está dentro de la hora actual.

Aquí un pequeño ejemplo de su uso:

	LocalTime justoAhora = LocalTime.now(); 
    System.out.printf("En este momento son las %d horas con %d minutos y %d
						segundos\n", justoAhora.getHour(),  
    justoAhora.getMinute(), justoAhora.getSecond()); 

### La clase de hora/fecha ###
La clase java.time.LocalDateTime manipula la fecha y la hora sin importar la zona horaria. Esta clase es usada para representar la fecha (año, mes, día) junto con la hora (hora, minuto, segundo, nanosegundo) y es - en efecto - la combinación de LocalDate y LocalTime. 

    LocalDateTime dateTime = LocalDateTime.of(1989, 11, 11, 5, 30, 45, 35); 
	//1989-11-11T05:30:45.000000035

La clase LocalDateTime tiene varios métodos of (o métodos con prefijo of) que crean una instancia de LocalDateTime. 

	LocalDateTime algunDia = LocalDateTime.of(1976, Month.MARCH, 27, 6, 10); 
    System.out.printf("Yo nací el %s%n", algunDia); 

Se puede capturar el LocalDateTime exacto de la ejecución usando el método now(), como se muestra a continuación.

	LocalDateTime ahora = LocalDateTime.now(); 
    System.out.printf("La hora es: %s%n", ahora); 

También hay métodos para agregar y quitar horas, minutos, días, semanas y meses.

	System.out.printf("Hace seis meses fue %s%n", LocalDateTime.now().minusMonths(6)); 

### Instant ###
Instant, representa el número de segundos desde 1 de Enero de 1970. Es un modelo de fecha y tiempo fácil de interpretar para una máquina.

    Instant instant = Instant.ofEpochSecond(120);
    System.out.println(instant);

El código anterior da como resultado:

    1970-01-01T00:02:00Z

De igual manera que las clases ya mencionadas, Instant provee el método now().

    Instant instant = Instant.now();

### Duration and Period ###

**Duration**, hace referencia a la diferencia que existe entre dos objetos de tiempo.
En el siguiente ejemplo, la duración se calcula haciendo uso de dos objetos LocalTime:

    LocalTime localTime1 = LocalTime.of(12, 25);
    LocalTime localTime2 = LocalTime.of(17, 35);
    Duration duration = Duration.between(localTime1, localTime2);

Otra opción de calcular la duración entre dos objetos es usando dos objetos LocalDateTime:

    LocalDateTime localDateTime1 = LocalDateTime.of(2016, Month.JULY, 18, 14, 13);
    LocalDateTime localDateTime2 = LocalDateTime.of(2016, Month.JULY, 20, 12, 25);
    Duration duration = Duration.between(localDateTime1, localDateTime2);


**Period**, hace referencia a la diferencia que existe entre dos fechas.

    LocalDate localDate1 = LocalDate.of(2016, Month.JULY, 18);
    LocalDate localDate2 = LocalDate.of(2016, Month.JULY, 20);
    Period period = Period.between(localDate1, localDate2);

Se puede crear Period basado en el método of(int years, int months, int days). En el siguiente ejemplo, se crea un período de 1 año 2 meses y 3 días:

    Period period = Period.of(1, 2, 3);

Del mismo modo que Duration, se puede crear Period basado en los métodos ofDays(int days), ofMonths(int months), ofWeeks(int weeks), ofYears(int years).

    Period period = Period.ofYears(1);

### ZonedDateTime ###
Representa una fecha completa con su zona horaria.

    ZonedDateTime today = ZonedDateTime.now();
    System.out.println("It's " + today);

Resultado:

    It is 2015-10-01T19:27:54.163+01:00[Atlantic/Canary]


# Referencias #
- http://www.oracle.com/technetwork/es/articles/java/expresiones-lambda-api-stream-java-2633852-esa.html
- http://www.oracle.com/technetwork/es/articles/java/expresiones-lambda-api-stream-java-2737544-esa.html
- http://www.arquitecturajava.com/java-8-date-time-api/
- http://blog.eddumelendez.me/2016/07/conociendo-la-nueva-date-api-en-java-8-parte-i/


