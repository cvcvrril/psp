delete from users;
delete from roles;

insert into roles (id, rol) values (1, 'ADMIN');
insert into roles (id, rol) values (2, 'USER');

insert into users (id, username, password, roles_id) values (1, 'ines', '$2a$10$QmslVN.VYaukSrN03XSVSukpEiiG//MpvBh2KzG3mxk5kXlC4A5Em', 1);









