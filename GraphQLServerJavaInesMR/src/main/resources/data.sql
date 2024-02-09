delete from user_roles;
delete from personajes;
delete from videojuegos;
delete from roles;
delete from users;

insert into roles (id, rol) values (1, 'ROLE_ADMIN');
insert into roles (id, rol) values (2, 'ROLE_USER');

insert into users (id, username, password) values (1, 'ines', '$2a$10$QmslVN.VYaukSrN03XSVSukpEiiG//MpvBh2KzG3mxk5kXlC4A5Em');

insert into videojuegos(id, titulo, descripcion) values (1, 'Prueba', 'Prueba prueba');
insert into videojuegos(id, titulo, descripcion) values (2, 'Más pruebas', 'Pipipipi');
insert into videojuegos(id, titulo, descripcion) values (3, 'Blablablabla', 'Loloolololo');

insert into personajes (id, nombre, descripcion, id_videojuego) values (1, 'Fiuuuuuuum', 'Eoooo', 1);








