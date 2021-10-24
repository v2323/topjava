DELETE
FROM user_roles;
DELETE
FROM users;
DELETE
FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;
ALTER SEQUENCE global_seq_meals RESTART WITH 1;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (date_time, description, calories, user_id)
VALUES ('2020-01-30 10:00:00', 'Завтрак', 500, 100000),
       ('2020-01-30 13:00:00', 'Обед', 1000, 100000),
       ('2020-01-30 19:00:00', 'Ужин', 500, 100000),
       ('2020-01-31 10:00:00', 'Завтрак', 600, 100000),
       ('2020-01-31 13:00:00', 'Обед', 1100, 100000),
       ('2020-01-31 19:00:00', 'Ужин', 400, 100000),
       ('2020-01-30 10:00:00', 'Завтрак', 700, 100001),
       ('2020-01-30 13:00:00', 'Обед', 1200, 100001),
       ('2020-01-30 19:00:00', 'Ужин', 800, 100001),
       ('2020-01-31 10:00:00', 'Завтрак', 700, 100001),
       ('2020-01-31 13:00:00', 'Обед', 900, 100001),
       ('2020-01-31 19:00:00', 'Ужин', 450, 100001);

