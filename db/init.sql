INSERT INTO authorities VALUES(1, 'ROLE_MODERATOR');
INSERT INTO authorities VALUES(2, 'ROLE_USER');

INSERT INTO users VALUES(1, 'admin', '123456', 1, true);
INSERT INTO users VALUES(2, 'user1', '123456', 2, true);
INSERT INTO users VALUES(3, 'user2', '123456', 2, true);
INSERT INTO users VALUES(4, 'user3', '123456', 2, true);

INSERT INTO topics VALUES(1, 'О чем и для чего этот форум?', 'Стартовый пост 1', '2021-11-16 19:44:58', 1);
INSERT INTO topics VALUES(2, 'Правила для участников форума', 'Стартовый пост 2', '2021-11-17 19:44:58', 1);
INSERT INTO topics VALUES(3, 'Все что связано с Java Core', 'Стартовый пост 3', '2021-11-18 19:44:58', 2);
INSERT INTO topics VALUES(4, 'Все что связано с Java Collection', 'Стартовый пост 4', '2021-11-19 19:44:58', 3);
INSERT INTO topics VALUES(5, 'Все что связано с Java Spring', 'Стартовый пост 5', '2021-11-20 19:44:58', 4);

INSERT INTO posts VALUES(1, 'Post 1. Bla-bla-bla Bla-bla-bla Bla-bla-bla Bla-bla-bla', 2, '2021-11-17 19:44:58');
INSERT INTO posts VALUES(2, 'Post 2. Bla-bla-bla Bla-bla-bla Bla-bla-bla Bla-bla-bla', 3, '2021-11-17 19:44:58');
INSERT INTO posts VALUES(3, 'Post 3. Bla-bla-bla Bla-bla-bla Bla-bla-bla Bla-bla-bla', 4, '2021-11-17 19:44:58');

INSERT INTO topics_posts VALUES(1, 1, 1);
INSERT INTO topics_posts VALUES(2, 1, 2);
INSERT INTO topics_posts VALUES(3, 1, 3);