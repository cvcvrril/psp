delete from personajes;
delete from videojuegos;
delete from mapas;

insert into mapas(id, nombre) values (1, 'Los Santos');
insert into mapas(id, nombre) values (2, 'Senda Arcoiris');
insert into mapas(id, nombre) values (3, 'Quarry');
insert into mapas(id, nombre) values (4, 'Hyrule');
insert into mapas(id, nombre) values (5, 'Gibraltar');
insert into mapas(id, nombre) values (6, 'Grecia');
insert into mapas(id, nombre) values (7, 'Los Reinos Del Norte');
insert into mapas(id, nombre) values (8, 'Mapa');
insert into mapas(id, nombre) values (9, 'Nether');
insert into mapas(id, nombre) values (10, 'Lejano Oeste');
insert into mapas(id, nombre) values (11, 'La Grieta Del Invocador');
insert into mapas(id, nombre) values (12, 'Santuario Del Enlace De Fuego');

insert into videojuegos(id, titulo, descripcion, mapa_id) values (1, 'GTA V', 'Explora Los Santos y disfruta de una historia de crimen y caos.', 1);
insert into videojuegos(id, titulo, descripcion, mapa_id) values (2, 'Mario Kart', '¡Compite con tus personajes favoritos de Nintendo en emocionantes carreras!', 2);
insert into videojuegos(id, titulo, descripcion, mapa_id) values (3, 'Call Of Duty Modern Warfare 2', '¡Prepárate para una intensa acción militar en este shooter de la exitosa serie Call of Duty!', 3);
insert into videojuegos(id, titulo, descripcion, mapa_id) values (4, 'The Legend of Zelda: Breath of the Wild', 'Aventura épica en Hyrule', 4);
insert into videojuegos(id, titulo, descripcion, mapa_id) values (5, 'Overwatch', 'Combate en equipo en un mundo futurista', 5);
insert into videojuegos(id, titulo, descripcion, mapa_id) values (6, 'Assassins Creed Odyssey', 'Explora la antigua Grecia como un asesino legendario', 6);
insert into videojuegos(id, titulo, descripcion, mapa_id) values (7, 'The Witcher 3: Wild Hunt', 'Sumérgete en un mundo de fantasía oscuro y peligroso mientras cazas monstruos y buscas a tu hija adoptiva.', 7);
insert into videojuegos(id, titulo, descripcion, mapa_id) values (8, 'Fortnite', 'Un frenético juego de batalla real donde construyes, disparas y sobrevives para ser el último en pie.', 8);
insert into videojuegos(id, titulo, descripcion, mapa_id) values (9, 'Minecraft', 'Crea y explora tu propio mundo en este juego de construcción y aventuras en bloques.', 9);
insert into videojuegos(id, titulo, descripcion, mapa_id) values (10, 'Red Dead Redemption 2', 'Vive la vida de un forajido en el salvaje oeste y lucha por la supervivencia y la libertad.', 10);
insert into videojuegos(id, titulo, descripcion, mapa_id) values (11, 'League of Legends', 'Enfréntate en emocionantes batallas de equipo en un juego de estrategia y habilidad.', 11);
insert into videojuegos(id, titulo, descripcion, mapa_id) values (12, 'Dark Souls III', 'Supera desafiantes enemigos y jefes en un oscuro mundo de fantasía donde cada paso puede ser tu último.', 12);

insert into personajes(id, nombre, descripcion, id_videojuego) values (1, 'Franklin Clinton', 'Un hábil y astuto delincuente que busca hacerse un nombre en Los Santos', 1);
insert into personajes(id, nombre, descripcion, id_videojuego) values (2, 'Michael De Santa', 'Un ex ladrón de bancos y estafador que intenta encontrar un equilibrio entre su vida familiar y sus instintos criminales', 1);
insert into personajes(id, nombre, descripcion, id_videojuego) values (3, 'Luigi', 'Hermano de Mario, siempre listo para la aventura', 2);
insert into personajes (id, nombre, descripcion, id_videojuego) values (4, 'Soap MacTavish', 'Un valiente soldado de las fuerzas especiales', 3);
insert into personajes (id, nombre, descripcion, id_videojuego) values (5, 'Link', 'Héroe de Hyrule en busca de la princesa Zelda', 4);
insert into personajes (id, nombre, descripcion, id_videojuego) values (6, 'Tracer', 'Veloz y ágil, controla el tiempo', 5);
insert into personajes (id, nombre, descripcion, id_videojuego) values (7, 'Alexios', 'Mercenario espartano en busca de su destino', 6);
insert into personajes(id, nombre, descripcion, id_videojuego) values (10, 'Geralt de Rivia', 'Un cazador de monstruos conocido como el brujo, experto en espadas y magia', 7);
insert into personajes(id, nombre, descripcion, id_videojuego) values (11, 'John Marston', 'Un antiguo forajido en busca de redención, luchando por su familia en el salvaje oeste', 10);
insert into personajes(id, nombre, descripcion, id_videojuego) values (12, 'Ezio Auditore', 'Un legendario asesino renacentista en busca de venganza y justicia', 6);
insert into personajes(id, nombre, descripcion, id_videojuego) values (13, 'Steve', 'El protagonista sin nombre de Minecraft, quien explora, construye y sobrevive en un mundo de bloques', 9);
insert into personajes(id, nombre, descripcion, id_videojuego) values (14, 'Arthur Morgan', 'Un forajido de corazón noble en busca de su lugar en un mundo que cambia rápidamente', 10);









