insert into Profile (id, description) values (1, 'ADMIN');
insert into Profile (id, description) values (2, 'USER');
insert into User (id, email, password, name, active, profile_id)
values (1, 'a@a.com', 'a', 'tales', true, 1);