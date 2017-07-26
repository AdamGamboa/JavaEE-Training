# Jasper Reports #
Jasper Reports es un motor de reportería de codigo libre para Java. Es una librería que se agrega a nuestros proyectos para generar reportes. JasperReports no es una herramienta para usuarios finales, sin embargo el resultado final (Los reportes) es de vital importancia para ellos.

Jasper Report tiene la habilidad de proporcionar contenido en pantalla, a la impresora y en archivos en formatos PDF, HTML, XLS, RTF, ODT, CSV, y XML.

## Principales caracteristicas de Jasper Reports ##

- Tiene un diseño de reportes flexible
- Puede representar datos en forma de texto y graficamente
- Los desarrolladores pueden proporcionar la información de múltiples maneras.
- Puede generar marcas de agua en los reportes.
- Puede generar subreportes
- Tiene la capacidad de exportar reportes en múltiples formatos.



## Estructura de un Reporte ##

<table style="text-align:center; width: 100%; max-width: 100%; margin-bottom: 20px;border: 1px solid #ddd;">
	<tbody>
		<tr><td>TITLE</td></tr>
		<tr><td>PAGEHEADER</td></tr>
		<tr><td>COLUMNHEADER</td></tr>
		<tr><td>DETAIL</td></tr>
		<tr><td>COLUMNFOOTER</td></tr>
		<tr><td>PAGEFOOTER</td></tr>
		<tr><td>SUMMARY</td></tr>
		<tr><td>NO DATA</td></tr>
	</tbody>
</table>

**Titulo:** Contiene el titulo del reporte. Esta sección aparece solamente 1 vez al inicio de el reporte.

**Encabezado de Pagina:** Aparece al inicio de cada página del reporte. Puede contener fechas, encabezados, etc.

**Encabezado de Columna:** Se muestra una vez para el detalle de la consulta o datos del reporte. Puede mostrar los nombres campos especificos del reporte.

**Detalle:** Es la parte donde se muestran los valores de los campos de cada registro obtenido para dicho reporte. 

**Pie de Columna:** Se muestra una vez luego del detalle (registros) del reporte. Puede contener sumatorias, totales de cualquier campo.

**Pie de Página:** Se muestra al final de cada página del reporte. Puede contener por ejemplo, la númeración de página. 

**Resumen:** Se muestra solamente 1 vez en el reporte al final de este. 

**No Data:** Esta sección se muestra si se activa la propiedad *When No Data Print report* con el valor *No Data*. Y si el data source no ha obtenido datos para mostrar.


## Ciclo de Vida de un Reporte ##

**Diseño:** En este paso se crea un archivo JRXML, que contiene la estructura del reporte. Usualmente se utiliza una herramienta de diseño como IReport o Jasper Report Studios.

**Compilado:** En este paso el archivo JRXML es compilado a un objeto binario *.jasper. La compilación de este archivo es realizada para mejorar el rendimiento. Estos son los archivos que se suelen utilizar en la aplicación Java.

**Ejecutando:** En este paso se llena de información el reporte. Se crea un archivo *.jrprint, usualmente se maneja en memoria.

**Exportado:** En este paso se toma el objeto/archivo jrprint que se encuentra lleno con la información y se exporta al formato seleccionado. 

# Creando un reporte #
Para crear un reporte se utilizará principalmente una herramienta de diseño para estos reportes, esta puede ser iReport (para versiones menores a la 6) o Jasper Studio para versiones de Jasper Report 6 o superior.


## Origenes de Datos ##
Cuando se crea un reporte, existen diferentes formas de indicar el origen de los datos con los cuales se crea dicho reporte. 

- **JDBC:** Se proporciona una conexión a base de datos para hacer una consulta.
- **JavaBean:** Se envian objetos java, o un listado de un objeto.
- **TableModel:** Para mostrar información de forma tabular.
- **XML:** Se envia un documento XML con la información del reporte.
- **CSV:** Se envia un documento CSV con la información del reporte.
- **XLS:** Se envia un documento XLS con la información del reporte.
- **Empty:** No existe un origen de datos para el reporte.

En esta lección nos enfocaremos en el origen de datos JDBC, consultando directamente a la base de datos desde el reporte.

## Parámetros ##
Los parámetros son aquellos datos que son enviados desde el exterior (la aplicación java) hacia el reporte. Pueden ser utilizados para filtrar información, toma de desiciones en el reporte, o para ser mostrados. 

![](http://community-static.jaspersoft.com/sites/default/files/static/tutorials/ir_report_parameters/figure_1.png)

Cada parámetro debe tener un nombre único, se le debe indicar un tipo de dato(String, Integer, Double, Collection, etc), y además se le puede indicar un valor por defecto para ser utilizado en caso que no se reciba su valor.

![](http://community-static.jaspersoft.com/sites/default/files/static/tutorials/ir_report_parameters/figure_2.png)

## Campos / Fields ##

Los campos / fields de un reporte, son los elementos que representan el mapeo entre el origen de datos y la plantilla del reporte.  Un reporte puede contener 0 o más campos declarados. Cuando se declara un campo el origen de datos debe proveer la información correspondiente a ese campo (al menos estar mapeado).

Cada campo, tiene asociado una clase, que especifica el tipo de datos de dicho campo (String, Double, Boolean, Integer, Stream, etc).



## Variables ##
Las variables y expresiones son uno de los puntos fuertes de Jasper Reports, y nos permiten realizar calculos, operaciones, concatenar o generar nuevos valores para nuestro reporte. 

Las expresiones puede pueden utilizar valores de los parámetros, de los campos y de otras variables tambien.

Cada variable puede estar definida para "vivir" en una sección especifica del reporte: todo el reporte, un grupo, en el detalle, por página, o ninguno. 

## Componentes ##

Jasper reporte nos permite agregar diferentes componentes en cualquier sección de nuestro reporte. Estos componentes nos ayuda a mostrar la informacion que deseamos en nuestro reporte. Algunos componentes son:

- Texto estático
- Campo de Texto
- Imagenes
- Lineas
- Figuras (Cuadrados, rectangulos, circulos)
- Códigos de barras
- Tablas
- Subreportes

## Grupos ##
Los grupos en Jasper Report ayudan a organizar los datos en el reporte de una manera más lógica. Un grupo representa una secuencia de registros consecutivos en el origen de datos, tal como un campo del reporte.

Un grupo tiene 3 elementos: 

- **Expresión del Grupo:** Indica la condición por la cual se agrupa.
- **Encabezado del Grupo:** Ayuda a colocar componentes al inicio del grupo.
- **Pie del Grupo:** Ayuda a colocar componentes al final del grupo

![](https://www.tutorialspoint.com/jasper_reports/images/report_groups_example.jpg)


## Sub reporte ##
Los sub reportes son una de las mejores caracteristicas de Jasper Reports. Esta funcionalidad nos permite agregar un reporte dentro de otro reporte. Los sub reportes nos ayudan a mantener un diseño simple, de manera que podemos crear varios reportes y encapsularlos en un reporte maestro. 

A un subreporte se le pueden indicar diferentes atributos: 

- Dirección del archivo del subreporte
- Parámetros a enviar al subreporte
- Conexión al subreporte, cuando el subreporte necesita de una conexión a base de datos.
- Origen de datos.

![](https://www.tutorialspoint.com/jasper_reports/images/subreport_example.jpg)

# Generando/Exportando un reporte desde Java #

## Dependencias ##

	<dependency>
	    <groupId>net.sf.jasperreports</groupId>
	    <artifactId>jasperreports</artifactId>
	    <version>6.1.0</version>
	</dependency>

Opcionales

	<dependency>
        <groupId>net.sf.jasperreports</groupId>
        <artifactId>jasperreports-functions</artifactId>
        <version>${jasperreports.version}</version>
    </dependency>
    <dependency>
        <groupId>net.sf.jasperreports</groupId>
        <artifactId>jasperreports-fonts</artifactId>
        <version>6.0.0</version>
    </dependency>

## Generando el reporte 
Con los siguientes pasos se puede generar un reporte que será almacenado en el OutputStream output, para un reporte que se conecta a la base de datos y que genera un archivo en formato PDF como resultado final.

	Connection conn = ConnectionUtils.getConnection();
	String reportSrcFile = "F:/RUTA/A/EL/REPORTE/mireporte.jrxml";
         
    // Compila el archivo jrxml.
    JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);
 
    // Indica los parámetros
    Map<String, Object> parameters = new HashMap<String, Object>();
 
	//Llena el reporte
    JasperPrint print = JasperFillManager.fillReport(jasperReport,
            parameters, conn);
 
    // Elige el exporter segun el formato que deseemos.
    JRPdfExporter exporter = new JRPdfExporter();
	
	//Indico donde se guardara el reporte.
	OutputStream output = new FileOutputStream(reportName + ".pdf");
	exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, output ); 
	
	//Exporto el reporte
	exporter.exportReport();
			

# Referencias #

- https://www.tutorialspoint.com/jasper_reports/
- http://community.jaspersoft.com/wiki/using-report-parameters
- http://o7planning.org/en/10293/calling-jasper-report-from-java-application