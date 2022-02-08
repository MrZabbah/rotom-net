# Nombre de la aplicación

RotomNet

## Descripción de la temática

La aplicación web es una página con temática de Pokémon, orientada a mostrar las
últimas noticias de la franquicia, crear equipos pseudoaleatorios para 
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

1. Usuario: contiene el correo electrónico, la contraseña, su imagen, una lista de Cartas y uno o varios Equipos.
2. Pokémon: contiene 4 ataques, uno o dos tipos, habilidad, naturaleza, EVs y un ítem.
3. Carta: contiene un Pokémon, su representación gráfica y sus estadísticas.
4. Equipo: contiene 6 Pokémon.

## Descripción de las funcionalidades del servicio interno

* Generar un equipo pseudoaleatorio: se seleccionan ciertos parámetros que determinarán las características del equipo final. Teniendo esto en cuenta, se seleccionan 6 Pokémon aleatorios y se plasman sus datos en un fichero de texto, adaptado para importarse en "Pokemon Showdown".
* Enviar notificaciones por correo.
* Publicar en redes sociales cuando consigues una carta. 
* Realizar un combate simplificado contra el jugador.
* Dar una carta aleatoria al jugador si es necesario.

## Organización del proyecto

https://github.com/MrZabbah/rotom-net/projects/1

## Diagrama UML
![UML](https://user-images.githubusercontent.com/60218308/152993552-48e7408f-217e-4e14-8a54-866733067c6f.PNG)


