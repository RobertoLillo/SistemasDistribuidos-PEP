# PEP-1
PEP 1 de asignatura Sistemas Distribuidos 2-2020.

Roberto Lillo / Jorge Ayala

## Tecnologías utilizadas

Se utilizaron las siguientes tecnologías para el desarrollo de la aplicación.

- Backend - Spring Boot

- Base de datos - PostgreSQL

- Frontend - Vue.js

## Levantamiento del sistema

- Antes de levantar el back o el front, es necesario tener desplegada una base de datos PostgresSQL de forma local, esta se puede levantar con Docker con el comando:
```
$ docker run --name postgresql -h postgresql -e POSTGRES_PASSWORD=password -d postgres
```

- Luego, ingresando a Postgres mediante PSQL o PGAdmin4, utilizar el esquema SQL que se encuentra en la siguiente ruta para levantar la tabla necesaria para el funcionamiento del back.
```
/BACKEND/src/main/resources/db/migration/schema.sql
```

- Posteriormente se puede levantar el back, pero primero es necesario configurar el archivo application.properties en la siguiente ruta. Aquí es necesario agregar las credenciales de la base de datos (por defecto el usuario es postgres y la password la ingresada en el levantamiento del contenedor). También es necesario ingresar las credenciales para el envío del Email de confirmación.
```
/BACKEND/src/main/resources/application.properties
```

- Luego, ubicandose en la ruta de la carpeta BACKEND, levantar el back con el siguiente comando.
```
$ mvn spring-boot:run
```

- Por otro lado, ubicandose en la ruta de la carpeta FRONTEND, realizar los dos siguientes comandos.
```
$ npm install
$ npm run serve
```

- Finalmente, se podrá ingresar a la aplicación WEB mediante la siguiente ruta.
```
localhost:8080
```

## Análisis del sistema

### 1) Poner recursos a disposición
**Facilitar a los usuarios y aplicaciones acceder recursos remotos de manera eficiente.**

- Al estar trabajando de manera local, no se está poninendo a disposición ningún recurso remoto así que no cumple este punto. Por otro lado respecto a la seguridad del sistema, se está haciendo uso de identificadores más complejos (UUID) pero ningún dato que se envíe desde front hacia el back o hacia la base de datos se encripta. Finalmente, no se tiene ningún factor de autenticación implementado.

### 2) Transparencia en la distribución
**Esconder el hecho que procesos y recursos están fisicamente distribuidos en múltiples computadores.**
 
- No es transparente. Nuevamente, al estar trabajando de manera local todo los recursos se encuentran en una misma máquina y no están distribuidos en multiples computadores. La unificación, migración y re-localización se ven afectadas por el uso de localhost como nombre de dominio. Para el caso de replicación, el back está preparado sólo para utilizar una base de datos no distribuida o replicada. La concurrencia no aplica ya que no se pueden ingresar dos formularios al mismo tiempo en una sola máquina. Por último, si algo falla el sistema deja de funcionar de inmediato al no haber respaldos del front, back o base de datos.

### 3) Apertura
**Ofrece servicios bajo reglas estándar.**

- El sistema cumple con ofrecer servicios bajo reglas estándar. El front y back se comunican mediante una API REST utlizando JSON como formato de texto, mientras el back y la base de datos se comunican mediante un protocolo para ingresar o extraer información. También se ofrece parcialmente fácil extensión, ya que el back tiene la capacidad de ofrecer nuevos endpoints en medida que se vayan solicitando nuevas funcionalidades.

### 4) Escalabilidad
**Modificar sin bajar el servicio las características del sistema.**

- El sistema no está preparado para la escalabilidad horizontal, ya que ningún elemento fue diseñado considerando la distribución en varias máquinas. Por otro lado, se podría ver beneficiado si se escalara de forma vertical, mejorando el computador en el que está desplegado. El particionamiento y replicamiento tampoco está presente en este sistema, la información se guarda en una sola base de datos. Tampoco se hace utilización de Cache, no se guardan datos para su lectura más rápida ni en front ni back.

## Pruebas del sistema

- Se realizaron pruebas con el software Postman, utilizando el complemento Postman Runner, que permite enviar una cierta cantidad de request en un intervalo de tiempo. Se hicieron pruebas con 5, 25 y 100 request, todas se ingresaron correctamente a la base de datos y además, se enviaron correctamente todas estas veces los correos de confirmación al usuario.

# PEP-2
PEP 2 de asignatura Sistemas Distribuidos 2-2020.

## Arquitectura de la PEP anterior.

En la primera prueba se solicitó el desarrollo de una arquitectura computacional simple, basandose en el sitio de Comisaria Virtual.

![alt text](https://scontent.fzco2-1.fna.fbcdn.net/v/t1.15752-9/152861622_438127537302954_8799250674111428124_n.png?_nc_cat=110&ccb=3&_nc_sid=ae9488&_nc_ohc=IcxbTfNmw3MAX-UcTmo&_nc_ht=scontent.fzco2-1.fna&oh=5bd1af0296cec096bef3c2bbd26ef7c3&oe=605C015E)

Para la implementación de la arquitectura se utilizó:

- Frontend -> Vue.js
- Backend -> Spring-Boot
- Base de datos -> PostgreSQL

## Estado anterior e implementación de características.

Primero, se puede apreciar la siguiente lista de características que no fueron cumplidas en la primera arquitectura:

- Poner recursos a disposición
- Transparencia en la distribución
- Escalabilidad

Las dos primeras se deben principalmente a que se trabajó completamente de manera local, la naturaleza de esto no permitió cumplir con estas características. Por otro lado, la característica de escalabilidad horizontal se ve ocasionada al no diseñar desde un principio el sistema para tener esta capacidad y que se pueda distribuir facilmente entre distintas máquinas, ya sea frontend o backend como también la base de datos.

## Mejoras de la arquitectura para satisfacer las características faltantes.

1º Con respecto a la disposición de recursos. La mejora principal que se realizará será levantar los tres componentes (frontend, backend y base de datos) en servidores remotos, que puedan ser accedidos desde cualquier dirección.

2º Con respecto a la transparencia. Además de levantar los componentes en un servidor remoto, cada uno de estos será levantado en su propia máquina distribuida, entregandole a cada uno de ellos una dirección IP única para la comunicación.

3º Con respecto a la escalabilidad. La escalabilidad vertical sigue sin ser un problema, ya que la mayoría de las soluciones de servidores en la nube ofrecen la capacidad de agregar recursos a las máquinas virtuales sin perder la integridad del sistema. En el aspecto horizontal, se puede configurar la base de datos para que trabaje de manera distribuida y haga replicaciones de datos en las distintas máquinas.

## Mejoras que fueron implementadas.

Se solicitó seleccionar dos de las caracteristicas anteriores e implementarlas efectivamente, por lo que se decidió trabajar en implementar la número 1 y 2 con el fin de mejorar la disposición de recursos y la transparencia de estos.

Todo este proceso se realizó en Google Cloud Platform, con el fin de tener acceso remoto fácil a estos servidores y así cumplir con la característica de disposición de recursos. Además, cada uno de los tres componentes se levantó en una máquina virtual distinta con el fin de distribuirlos y mejorar la transparencia del sistema, teniendo en cuenta la seguridad, sólo se dejó con acceso público el servidor que contiene el frontend de la aplicación.

Considerando las mejoras anteriores, se llegó a la siguiente arquitectura.

![alt text](https://scontent.fzco2-1.fna.fbcdn.net/v/t1.15752-9/152420113_458674571940000_2048926380069075251_n.png?_nc_cat=109&ccb=3&_nc_sid=ae9488&_nc_ohc=RLnpkbn-cAIAX_0lPOG&_nc_ht=scontent.fzco2-1.fna&oh=687902e4bf82f612be1b1fcd6cfd1fde&oe=605C00F2)

## Análisis de los resultados.
