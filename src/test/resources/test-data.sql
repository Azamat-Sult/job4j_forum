INSERT INTO authorities (authority) VALUES ('ROLE_MODERATOR');
INSERT INTO authorities (authority) VALUES ('ROLE_USER');

INSERT INTO users (username, enabled, password, authority_id)
VALUES ('admin', true, '$2a$10$YBaEs/51Df1U1/MSUO/gPexO3wNK6JUYCpyifiRMwi67R8SO0XAY6',
(select id from authorities where authority = 'ROLE_MODERATOR'));

INSERT INTO topics (name, description, created, author_id) VALUES ('О чем и для чего этот форум?',
 'Стартовый пост 1', '2021-11-16 19:44:58', (select id from users where username = 'admin'));

INSERT INTO posts (text, author_id, created) VALUES ('Post 1. Bla-bla-bla Bla-bla-bla Bla-bla-bla Bla-bla-bla',
 (select id from users where username = 'admin'), '2021-11-17 19:44:58');

INSERT INTO topics_posts (topic_id, posts_id) VALUES (
(select id from topics where name = 'О чем и для чего этот форум?'),
 (select id from posts where text = 'Post 1. Bla-bla-bla Bla-bla-bla Bla-bla-bla Bla-bla-bla')
);