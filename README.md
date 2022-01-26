# Nombre de la aplicación
RotomNet

# Descripción de la temática
La aplicación web es una página con temática de Pokémon, orientada a mostrar las
últimas noticias de la franquicia, crear equipos pseudoaleatorios para 
"Pokémon Showdown" y coleccionar cartas con el objetivo de completar la primera Pokédex.

# Funcionalidades públicas
* Tablón de anuncios y noticias sobre Pokémon.
* Creación pseudoaleatoria de equipos para "Pokémon Showdown".
* Visualización de las características de todas las cartas.

# Funcionalidades privadas
* Visualización de la baraja de cartas coleccionables del usuario.
* Combate contra la máquina para obtener cartas aleatorias.
* Obtención de cartas en base a la cantidad de accesos consecutivos a la web.
* Registro de equipos previamente creados.

# Nombre y descripción de las entidades principales
1. Usuario: contiene el correo electrónico, la contraseña, (opcionalmente) una Baraja de cartas y uno o varios Equipos.
2. Pokémon: contiene 4 ataques, uno o dos tipos, estadísticas, habilidad, naturaleza, EVs y un ítem.
3. Carta: contiene un Pokémon y su representación gráfica.
4. Equipo: contiene 6 Pokémon.
5. Noticia: contiene un titular, un texto y una o varias imágenes.
6. Pokédex: contiene 151 Cartas.
7. Baraja (podría ir en Usuario o por separado): contiene un número variable de Cartas que posea un Usuario.

# Descripción de las funcionalidades del servicio interno
* Mostrar la información de todas las cartas de la Pokédex: se accede a las 151 cartas y se muestran en una lista. En caso de seleccionar una, se ven sus datos por pantalla.
* Generar un equipo pseudoaleatorio: se seleccionan ciertos parámetros que determinarán las características del equipo final. Teniendo esto en cuenta, se seleccionan 6 Pokémon aleatorios y se plasman sus datos en un fichero de texto, adaptado para importarse desde "Pokemon Showdown"
* Enviar equipo por correo (¿Externo?)
* Login con Facebook o correo electrónico (¿Externo?)
* Mostrar una lista con todas las noticias recientes.
* Registrar accesos diarios consecutivos a la aplicación.
* Realizar un combate simplificado contra el jugador.
* Dar una carta aleatoria al jugador si es necesario.
* Mostrar una lista con todos los equipos creados para Showdown anteriormente.
