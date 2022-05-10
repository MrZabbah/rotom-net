# Nombre de la aplicación

RotomNet

## Enlace de video de YouTube (Práctica 4)

[![image](https://user-images.githubusercontent.com/60218308/167568533-2aae329d-b7f9-4685-9063-3feaf1d9a4bb.png)](https://www.youtube.com/watch?v=ildhpboq_gw)

## Descripción de la temática

La aplicación web es una página con temática de Pokémon, orientada a crear equipos pseudoaleatorios para 
"Pokémon Showdown" y coleccionar cartas con el objetivo de completar la primera Pokédex.

## Funcionalidades públicas

* Creación pseudoaleatoria de equipos para "Pokémon Showdown".
* Visualización de las características de todas las cartas disponibles.

## Funcionalidades privadas

* Visualización de la baraja de cartas coleccionables del usuario.
* Combate contra la máquina para obtener cartas aleatorias (contiene límite diario).
* Obtención de cartas en base a la cantidad de accesos consecutivos a la web.
* Registro de equipos previamente creados.

## Nombre y descripción de las entidades principales

1. User: contiene el nombre de usuario, la contraseña, su imagen, un registro de su última entrada en la web, una lista de objetos UserRotomCard y una lista de objetos Team.
2. Pokémon: contiene un número de la Pokédex, un nombre, 4 ataques, uno o dos tipos, habilidad, naturaleza, EVs, un ítem y dos indicadores para saber si es legendario y si es compatible com Pokémon Showdown.
3. RotomCard: contiene un Pokémon, sus estadísticas y una lista de objetos UserRotomCard.
4. UserRotomCard: relaciona un usuario con una carta. Contiene el número de ejemplares que el usuario tiene de dicha carta y un indicador de si es shiny o no.
5. Team: contiene un nombre, 6 Pokémon y el usuario al que está vinculado.

## Descripción de las funcionalidades de la aplicación web

* Generar un equipo pseudoaleatorio: se seleccionan ciertos parámetros que determinarán las características del equipo final. Teniendo esto en cuenta, se seleccionan 6 Pokémon aleatorios aptos para competir en Pokémon Showdown y, si un usuario lo desea, se almacena de forma peristente.
* Realizar un combate simplificado contra el jugador.
* Dar una carta aleatoria al jugador si es necesario.
* Mostrar la Pokédex completa.
* Mostrar las cartas de la baraja de un usuario.

## Descripción de las funcionalidades del servicio interno

* Plasmar los datos de un equipo en texto y enviarlo por correo a un mail especificado.
* Enviar notificaciones por correo.
* Publicar en redes sociales cuando consigues una carta. 

## Organización del proyecto

https://github.com/MrZabbah/rotom-net/projects/1

## Diagrama UML
![diagrama_final png](https://user-images.githubusercontent.com/60218308/155217556-0e204848-e783-4499-81c1-f34d2cbcad06.png)

## Diagrama de navegación

Se realiza una división principal entre páginas públicas y privadas (estas últimas diferenciadas por un candado arriba a la izquierda).

1. Pantalla principal: información de uso de la aplicación (1b sería la misma pantalla, pero la cabecera muestra los enlaces de las funcionalidades privadas de cada uno de los usuarios).
2. Formulario de creación de equipos: selección de un nombre de equipo y diferentes parámetros para la creación del equipo de forma pseudoaleatoria. 
3. Mostrado del equipo creado: información sobre los parámetros seleccionados en el formulario y los Pokemon obtenidos. Posibilidad de envío a un determinado correo electrónico (siempre que sea válido), intentar de nuevo y salida a la pantalla principal. (3b sería la misma página, pero ofrece al usuario loggeado la posibilidad de guardar el equipo en su lista de equipos). 
4. Pokedex: todas las cartas que puede obtener el usuario. 
5. Login: pantalla para loggeo de un usuario siempre que exista, además de un enlace que redirige al registro para nuevos usuarios.
6. Registro de usuarios: formulario para creación de usuarios. 
7. Usuario registrado: información sobre el usuario registado desde la pantalla 6, así como un botón de enlace al login (pantalla 5).
8. Equipos del usuario: todos los equipos del usuario (en formato enlace para poder ver su información en detalle en otra página) y posibilidad del borrado de los mismos.
9. Selección de un equipo del usuario: información en detalle del equipo seleccionado desde la pantalla 8. También permite su borrado y envío a un determinado correo (por defecto, el correo con el que se registró). 
10. Baraja del usuario: cartas que tiene el usuario actualmente, cuántas ha descubierto y cuántas de éstas son shiny (especiales).
11. Combate: se muestran dos equipos generados aleatoriamente (primero el enemigo y luego el del usuario) que combaten al pulsar el botón.
12. Resultado del combate (perdido): información de los equipos previamente generados en la pantalla 11 y aparece un texto explicativo de que ha perdido.
13. Resultado del combate (ganado): información de los equipos previamente generados en la pantalla 11 y la carta ganada por el usuario, además de la opción de poder publicar, si es la primera vez que la obtiene, un Tweet en @ RotomNetForum. 
14. Modificación de usuario: información sobre el usuario, así como un formulario para modificar sus datos.
15. Logout: permite desloggearse al usuario actual.

![diagrama_pantallas_2](https://user-images.githubusercontent.com/60218308/159799244-3a7e1493-98fd-4a62-9202-60959281688a.png)

## Diagrama de clases

Se divide por un esquema de colores:
* Verde: controladores.
* Azul: servicios.
* Morado: repositorios.

![diagrama_clases](https://user-images.githubusercontent.com/60218308/159800807-7cf4ca1c-a9cf-4081-9abf-d80a09d34e0b.png)

## Enlace del repositorio con el servicio interno

https://github.com/MrZabbah/rotom-net-api-rest

## Despliegue de la Máquina Virtual
La aplicación web y la API REST van a ser ejecutadas en una Máquina Virtual Linux limpia. Los pasos para la creación y configuración de dicha Máquina Virtual son los siguientes:
1. Crear la Máquina Virtual
  - Descargar VirtualBox desde el siguiente [enlace](https://www.oracle.com/es/virtualization/technologies/vm/downloads/virtualbox-downloads.html)
  - Instalar VirtualBox.
  - Descargar la imagen de Ubuntu 20.04 LTS desde el siguiente [enlace](https://ubuntu.com/download/desktop)
  - Desde VirtualBox, crear una nueva Máquina Virtual, estableciendo como Sistema Operativo Ubuntu x64.
  - Establecer la configuración para 2GB de memoria y 10GB de disco dinámico.
  - Configurar la imagen de Ubuntu previamente descargada en la Máquina Virtual, desde Configuración -> Almacenamiento -> Controlador:IDE -> Añadir unidad óptica.
  - Iniciar la Máquina Virtual y seguir el proceso de configuración e instalación predeterminado.
3. Instalar Java
  - Ejecutar el siguiente comando para instalar Java mediante el jdk-17.
  ```
  sudo apt install openjdk-17-jre-headless
  ```
5. Instalar MySQL Server
  - Ejecutar los siguientes comandos para actualizar el entorno e instalar el Servidor MySQL.
  ```
  sudo apt update
  sudo apt install mysql-server
  ```
  - Iniciar la instalación de MySQL. 
    - Se ha seleccionado "No" para una contraseña del sistema.
    - Se ha establecido "root" como contraseña de root.
    - Se han aceptado las opciones de seguridad y se ha configurado la autenticación y los privilegios.
  ```
  sudo mysql_secure_installation
  ```
7. Configurar la base de datos
  - Ejecutar el siguiente comando para poder continuar la configuración:
  ```
  sudo mysql
  ```
  - Finalizar la configuración de la base de datos y el acceso a root.
  ```
  ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'root';
  CREATE DATABASE pokemon_schema;
  GRANT ALL PRIVILEGES ON pokemon_schema.* TO 'root'@'localhost';
  FLUSH PRIVILEGES
  EXIT;
  ```
9. Ejecutar las aplicaciones:
```
sudo java -jar nombreDeEjecutable.jar
```

## Configuración de la base de datos replicada
Se la implementado una base de datos replicada con un modelo Master/Slave, apoyada en ProxySQL. Las escrituras son realizadas exclusivamente sobre la base de datos Master y replicadas automáticamente a la Slave, mientras que las lecturas pueden ser ejecutadas sobre cualquiera de las dos.
1. Configuración del esquema Master/Slave
  - En caso de que exista, eliminar la subcarpeta 'data' de las carpetas 'master_config', 'slave_config' y 'proxysql'.
  - En las carpetas 'master_config' y 'slave_config' cambiar los permisos del fichero 'my.cnf' a "Solo Lectura":
    * Click derecho
    * Propiedades
    * En Atributos, marcar casilla de "Solo lectura"
  - En el fichero 'docker-compose.yml' conectar la primera instancia de la aplicación a la base de datos y al puerto 3306.
```
environment:
    MYSQL_HOST: db
    MYSQL_PORT: 3306
    SPRING_JPA_HIBERNATE_DDL-AUTO: create
    ROTOMNET_API_HOST: lb-api
 ```
  - Lanzar la ejecución de los contenedores en segundo plano
```
docker compose up -d
```
  - Entrar en la terminal de la base de datos Master
```
docker-compose exec db bash
```
  - Entrar en la terminal de MySQL. Es posible que se deniegue la conexión varias veces porque los contenedores aún no estén preparados. Continuar ejecutando el comando de forma periódica hasta tener éxito.
```
mysql -u root -proot
```
  - Mostrar la información de la base de datos Master. Memorizar el fichero binario que se muestra en el apartado 'File', puesto que debería mostrarse igual posteriormente en la base de datos Slave.
```
show master status\G;
```
  - Comprobar que las tablas han sido creadas correctamente.
```
use pokemon_schema
show tables;
```
  - Comprobar que la replicación se realiza correctamente. Crear una tabla e insertar valores.
```
create table test (a int, b int);
insert into test values (2,2)
```
  - Abrir una nueva consola o salir de MySQL y de la base de datos Master ejecutando 'exit' dos veces.
  - Entrar en la terminal de la base de datos Slave.
```
docker-compose exec dbslave bash
```
  - Entrar en la terminal de MySQL. De nuevo es posible que se deniegue el acceso varias veces porque los contenedores aún no estén preparados. Continuar ejecutando el comando de forma periódica hasta tener éxito.
```
mysql -u root -proot
```
  - Mostrar la información de la base de datos Slave. Observar el campo 'Master_Log_File' y comparar con el campo 'File' observado previamente en la base de datos Master. El fichero mostrado debería ser el mismo. Adicionalmente, los campos 'Slave_IO_Running' y 'Slave_SQL_Running' deberían ser "Yes".
```
show slave status\G;
```
  - Comprobar que las tablas han sido creadas correctamente. Deberían mostrarse todas las tablas, incluida la nueva tabla "test"
```
use pokemon_schema
show tables;
```
  - Comprobar que la replicación se ha realizado adecuadamente. Se deberían devolver los datos insertados previamente en la base de datos Master.
```
select * from test;
```
  - Salir de MySQL y la base de datos Slave ejecutando 'exit' dos veces.
  - Parar la ejecución de los contenedores.
```
docker compose down
```
2. Configuración de ProxySQL
  - En el fichero 'docker-compose.yml', cambiar los parámetros de la primera aplicación para que se conecte a ProxySQL y al puerto 6033. Además, se debe comentar la propiedad 'JPA_HIBERNATE_DDL-AUTO = create', puesto que las tablas ya están creadas.
```
environment:
      MYSQL_HOST: proxysql
      MYSQL_PORT: 6033 
      #SPRING_JPA_HIBERNATE_DDL-AUTO: create 
      ROTOMNET_API_HOST: lb-api
```
  - Lanzar la ejecución de los contenedores en segundo plano
```
docker compose up -d
```
  - Entrar en la terminal de la base de datos Slave
```
docker-compose exec dbslave bash
```
  - Entrar en la terminal de MySQL. Es posible que se deniegue la conexión varias veces porque los contenedores aún no estén preparados. Continuar ejecutando el comando de forma periódica hasta tener éxito.
```
mysql -u root -proot
```
  - Mostrar la información de la base de datos Slave. Se puede observar que, en esta ocasión, los campos 'Slave_IO_Running' y 'Slave_SQL_Running' muestran el valor "No". Esto se debe a que, al ejecutarse por segunda vez, se han desincronizado de la base de datos Master.
```
show slave status\G;
```
  - Reiniciar la base de datos Slave para sincronizarla de nuevo con la base de datos Master.
```
stop slave;
reset slave;
start slave;
```
  - Mostrar de nuevo la información de la base de datos Slave. Al reiniciarla, se ha sincronizado de nuevo con la base de datos Master y ahora los campos 'Slave_IO_Running' y 'Slave_SQL_Running' muestran el valor "Yes"
```
show slave status\G;
```

## Topología del despliegue de la aplicación con Docker Compose

![Esquema_infraestructura_DAD](https://user-images.githubusercontent.com/60218308/167568873-0922f57d-f6a9-434f-bc73-a926cc2157f9.png)

