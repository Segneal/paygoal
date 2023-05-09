# PAYGOAL

#SIMPLE CRUD APP
Resumen: Esta aplicacion genera CREATE , UPDATE, DELETE , ORDERBY  y las almacena en una base de datos en memoria

###Tecnologias utilizadas 
JAVA 17
H2 DB

-Abrir sitio web en localhost:8080


##ENDPOINTS
```
  /products/getAll
  /products/getAll/desc
  /products/getAll/asc
  /products/delete/{id}
  /products/update/{id}
  /products/getById/{id}
```


Prerrequisitos:
Pasos para instalar Java 17:
descargar Java en https://www.oracle.com/java/technologies/downloads/
"JDK Download" en la sección de "Java SE 17" para descargar el kit de desarrollo de Java 17.
Selecciona la versión que corresponda a tu sistema operativo y haz clic en el botón "Download" para comenzar la descarga.
Instalar el archivo descargado

Pasos para instalar Maven:

Descargar Maven en https://maven.apache.org/download.cgi
Descarga el archivo binario de Maven seleccionando la versión que corresponda a tu sistema operativo.
Una vez que se complete la descarga, descomprime el archivo en una ubicación de tu elección.
Configura la variable de entorno "PATH" de tu sistema operativo para incluir la ruta a la carpeta bin de Maven. 

en command line ejecutar los siguientes comandos
java --version
mvn --version

Si los comandos devuelven la versión de Java y Maven, respectivamente, entonces todo ha sido instalado correctamente.
