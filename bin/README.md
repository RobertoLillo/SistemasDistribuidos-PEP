# SistemasDistribuidos-PEP1
PEP 1 de asignatura Sistemas Distribuidos 2-2020.

Roberto Lillo / Jorge Ayala

## Análisis del sistema
```
Características de un sistema distribuido.

• Poner recursos a disposición.
    - Facilitar a los usuarios y aplicaciones acceder recursos remotos de manera eficiente.

• Transparencia en la distribución.
    - Esconder el hecho que procesos y recursos están fisicamente distribuidos en múltiples computadores.

• Apertura.
    - Ofrece servicios bajo reglas estándar.

• Escalabilidad.
    - Modificar sin bajar el servicio las características del sistema.

```
## JSON para ingresar formulario en Postman:
```
{
    "rut": "",
    "name": "",
    "address": "",
    "reason": "",
}
```
## Rutas actuales:
```
• (POST)  ip:8080/api/newTemporaryPermit
• (GET)   ip:8080/api/allTemporaryPermits
• (GET)   ip:8080/api/temporaryPermitById/{id}
```
