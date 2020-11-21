# SistemasDistribuidos-PEP1
PEP 1 de asignatura Sistemas Distribuidos 2-2020.

Roberto Lillo / Jorge Ayala

## Tecnologías utilizadas

Se utilizaron las siguientes tecnologías para el desarrollo de la aplicación.

- Backend - Spring Boot

- Base de datos - PostgreSQL

- Frontend - Vue.js

## Análisis del sistema

Características de un sistema distribuido.

- Poner recursos a disposición.
Facilitar a los usuarios y aplicaciones acceder recursos remotos de manera eficiente.

Al estar trabajando de manera local, no se está poninendo a disposición ningún recurso remoto así que no cumpliría este punto. Por otro lado respecto a la seguridad del sistema, se está haciendo uso de identificadores más complejos (UUID) pero ningún dato que se envíe desde front hacia el back o hacia la base de datos se encripta. Finalmente, no se tiene ningún factor de autenticación implementado.

- Transparencia en la distribución.
 Esconder el hecho que procesos y recursos están fisicamente distribuidos en múltiples computadores.
 

- Apertura.
Ofrece servicios bajo reglas estándar.


- Escalabilidad.
Modificar sin bajar el servicio las características del sistema.

