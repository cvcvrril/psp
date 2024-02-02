delete from user_roles;
delete from roles;
delete from users;
delete from personajes;
delete from videojuegos;

insert into roles (id,rol) values (1,'ROLE_ADMIN');
insert into roles (id,rol) values (2,'ROLE_USER');

insert into users (id, username, password) values (1, 'ines', '$2a$10$QmslVN.VYaukSrN03XSVSukpEiiG//MpvBh2KzG3mxk5kXlC4A5Em');

insert into user_roles (user_id,roles_id) values (1,1);

insert into videojuegos(id, titulo, descripcion) values (1, 'Grand Thief Auto V', 'Prueba prueba');

insert into personajes (id, nombre, descripcion, id_videojuego) values (1, 'Franklin', 'Eoooo', 1);








