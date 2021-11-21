CREATE TABLE if not exists authorities (
  id serial primary key,
  authority varchar(255)
);

CREATE TABLE if not exists users (
  id serial primary key,
  username varchar(255),
  password varchar(255),
  authority_id int references authorities(id),
  enabled boolean
);

CREATE TABLE if not exists posts (
  id serial primary key,
  text varchar(255),
  author_id int references users(id),
  created TIMESTAMP
);

CREATE TABLE if not exists topics (
  id serial primary key,
  name varchar(255),
  description varchar(255),
  created TIMESTAMP,
  author_id int references users(id)
);

CREATE TABLE if not exists topics_posts (
  id serial primary key,
  topic_id INT references topics(id),
  posts_id INT references posts(id)
);