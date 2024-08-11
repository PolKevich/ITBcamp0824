-- Удаляем записи из таблиц, на которые ссылаются другие таблицы (зависимости)
DELETE FROM user_project;
DELETE FROM project;
DELETE FROM user;

-- Сбрасываем автогенерацию идентификаторов, если используется
ALTER TABLE user AUTO_INCREMENT = 1;
ALTER TABLE project AUTO_INCREMENT = 1;
ALTER TABLE user_project AUTO_INCREMENT = 1;