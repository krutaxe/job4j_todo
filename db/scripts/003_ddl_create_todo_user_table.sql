CREATE TABLE IF NOT EXISTS todo_user (
   id SERIAL PRIMARY KEY,
   name TEXT NOT NULL,
   login TEXT not null unique ,
   password TEXT not null
);
