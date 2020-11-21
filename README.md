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

- Poner recursos a disposición: facilitar a los usuarios y aplicaciones acceder recursos remotos de manera eficiente.

Al estar trabajando de manera local, no se está poninendo a disposición ningún recurso remoto así que no cumpliría este punto. Por otro lado respecto a la seguridad del sistema, se está haciendo uso de identificadores más complejos (UUID) pero ningún dato que se envíe desde front hacia el back o hacia la base de datos se encripta. Finalmente, no se tiene ningún factor de autenticación implementado.

- Transparencia en la distribución: esconder el hecho que procesos y recursos están fisicamente distribuidos en múltiples computadores.
 
No es transparente. Nuevamente, al estar trabajando de manera local todo los recursos se encuentran en una misma máquina y no están distribuidos en multiples computadores. La unibicación, migración y re-localización se ven afectadas por el uso de localhost como nombre de dominio. Para el caso de replicación, el back está preparado sólo para utilizar una base de datos no distribuida o replicada. La concurrencia no aplica ya que no se pueden ingresar dos formularios al mismo tiempo en una sola máquina. Por último, si algo falla el sistema deja de funcionar de inmediato al no haber respaldos del front, back o base de datos.

- Apertura: ofrece servicios bajo reglas estándar.

El sistema cumple con ofrecer servicios bajo reglas estándar. El front y back se comunican mediante una API REST utlizando JSON como formato de texto, mientras el back y la base de datos se comunican mediante un protocolo para ingresar o extraer información. También se ofrece parcialmente fácil extensión, ya que el back tiene la capacidad de ofrecer nuevos endpoints en medida que se vayan solicitando nuevas funcionalidades.

- Escalabilidad: modificar sin bajar el servicio las características del sistema.

El sistema no está preparado para la escalabilidad horizontal, ya que ningún elemento fue diseñado considerando la distribución en varias máquinas. Por otro lado, se podría ver beneficiado si se escalara de forma vertical, mejorando el computador en el que está desplegado. El particionamiento y replicamiento tampoco está presente en este sistema, la información se guarda en una sola base de datos. Tampoco se hace utilización de Cache, no se guardan datos para su lectura más rápida ni en front ni back.
