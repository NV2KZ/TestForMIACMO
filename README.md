# TestForMIACMO
Тестовое задание для ГБУ МО МОМИАЦ.

Описание структуры БД:
Каждая строка записи в БД отражает сущность Socks (одна пара носков) с атрибутами socks_id - уникальный идентификатор, socks_color - цвет, socks_cp = содержание хлопка. 
Структутура БД также размещена в репозитории - файл StructureOfDB. 
Метод POST /api/socks/income создает записи в БД в количестве равным полю quantity в теле запроса. 
Метод POST /api/socks/outcome удаляет записи в БД в количестве равным полю quantity в теле запроса.
Метод GET /api/socks возвращает общее количество носков на складе, путем подсчета записей, соответствующих переданным в параметрах критериям запроса.
