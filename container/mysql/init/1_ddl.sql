CREATE DATABASE todo_list_scala DEFAULT CHARSET utf8mb4;

CREATE TABLE todo_list_scala.todo (
  id bigint auto_increment NOT NULL PRIMARY KEY ,
  title VARCHAR (255),
  body TEXT,
  status INT ,
  created_at DATETIME,
  updated_at DATETIME
);
