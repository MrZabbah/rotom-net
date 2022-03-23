# Nombre de la aplicación

RotomNet

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
