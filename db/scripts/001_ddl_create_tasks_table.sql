CREATE TABLE IF NOT EXISTS tasks (
   id SERIAL PRIMARY KEY,
   name TEXT NOT NULL,
   description TEXT,
   created TIMESTAMP,
   done BOOLEAN
);