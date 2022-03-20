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

1. Pantalla principal: información de uso de la aplicación.
2. Formulario de creación de equipos: selección de un nombre de equipo y diferentes parámetros para la creación del equipo de forma pseudoaleatoria. 
3. Mostrado del equipo creado: información sobre los parámetros seleccionados en el formulario y los Pokemon obtenidos. Posibilidad de guardado en un usuario, intentar de nuevo y salida a la pantalla principal. 
4. Pokedex: todas las cartas que puede obtener el usuario. 
5. Selección de usuario: para mostrar sus equipos, su baraja y realizar combates.
6. Equipos del usuario: todos los equipos del usuario (en formato enlace para poder ver su información en detalle en otra página) y posibilidad del borrado de los mismos.
7. Selección de un equipo del usuario: información en detalle del equipo seleccionado desde la pantalla 6. También permite su borrado. 
8. Baraja del usuario: cartas que tiene el usuario actualmente, cuántas ha descubierto y cuántas de éstas son shiny (especiales).
9. Combate: se muestran dos equipos generados aleatoriamente (primero el enemigo y luego el del usuario) que combaten al pulsar el botón. 
10. Resultado del combate:  información de los equipos previamente generados en la pantalla 9 y, si se ha ganado, la carta ganada por el usuario. En caso de que pierda, aparece un texto explicativo de que ha perdido. 
11. Lista de usuarios registrados: usuarios registrados en la aplicación, así como botones que permiten tanto su modificación como su borrado. 
12. Modificación de usuario: información sobre el usuario seleccionado, así como un formulario para modificar sus datos. 
13. Registro de usuario: formulario para creación de usuarios. 
14. Usuario registrado: información sobre el usuario registado desde la pantalla 13, así como un botón de enlace a todos los usuarios registrados (pantalla 11).

![diagrama_pantallas](https://user-images.githubusercontent.com/60218308/155235046-ebeff775-e8f9-4137-a1d5-4a4623246ee0.png)
![captura_pantallas](https://user-images.githubusercontent.com/60218308/155365950-39e47ece-90cd-47d0-80b8-6857ca993a94.png)

