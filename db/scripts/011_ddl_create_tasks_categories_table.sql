CREATE TABLE IF NOT EXISTS tasks_categories (
   task_id int references tasks(id),
   categories_id int references categories(id)
);