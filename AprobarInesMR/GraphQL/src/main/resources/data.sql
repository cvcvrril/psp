delete from personajes;
delete from videojuegos;
delete from consolas;
delete from mapas;

insert into videojuegos(id, titulo, descripcion) values (1, 'GTA V', 'Explora Los Santos y disfruta de una historia de crimen y caos.');
insert into videojuegos(id, titulo, descripcion) values (2, 'Mario Kart', '¡Compite con tus personajes favoritos de Nintendo en emocionantes carreras!');
insert into videojuegos(id, titulo, descripcion) values (3, 'Call Of Duty Modern Warfare 2', '¡Prepárate para una intensa acción militar en este shooter de la exitosa serie Call of Duty!');
insert into videojuegos(id, titulo, descripcion) values (4, 'The Legend of Zelda: Breath of the Wild', 'Aventura épica en Hyrule');
insert into videojuegos(id, titulo, descripcion) values (5, 'Overwatch', 'Combate en equipo en un mundo futurista');
insert into videojuegos(id, titulo, descripcion) values (6, 'Assassins Creed Odyssey', 'Explora la antigua Grecia como un asesino legendario');
insert into videojuegos(id, titulo, descripcion) values (7, 'The Witcher 3: Wild Hunt', 'Sumérgete en un mundo de fantasía oscuro y peligroso mientras cazas monstruos y buscas a tu hija adoptiva.');
insert into videojuegos(id, titulo, descripcion) values (8, 'Fortnite', 'Un frenético juego de batalla real donde construyes, disparas y sobrevives para ser el último en pie.');
insert into videojuegos(id, titulo, descripcion) values (9, 'Minecraft', 'Crea y explora tu propio mundo en este juego de construcción y aventuras en bloques.');
insert into videojuegos(id, titulo, descripcion) values (10, 'Red Dead Redemption 2', 'Vive la vida de un forajido en el salvaje oeste y lucha por la supervivencia y la libertad.');
insert into videojuegos(id, titulo, descripcion) values (11, 'League of Legends', 'Enfréntate en emocionantes batallas de equipo en un juego de estrategia y habilidad.');
insert into videojuegos(id, titulo, descripcion) values (12, 'Dark Souls III', 'Supera desafiantes enemigos y jefes en un oscuro mundo de fantasía donde cada paso puede ser tu último.');

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

insert into consolas(id, nombre) values (1, 'PlayStation');
insert into consolas(id, nombre) values (2, 'Switch');
insert into consolas(id, nombre) values (3, 'Xbox');
insert into consolas(id, nombre) values (4, 'PC');

insert into mapas(id, nombre) values (1, 'Los Santos');
insert into mapas(id, nombre) values (2, 'Senda Arcoiris');
insert into mapas(id, nombre) values (3, 'Los Santos');







