# LogParser

`LogParser` — это Java-класс для парсинга логов и выполнения запросов к ним. Он реализует несколько интерфейсов для получения информации о пользователях, IP, событиях и статусах из логов, а также поддерживает выполнение запросов в формате QL.

---

## Описание

Класс `LogParser` читает файлы логов из указанной директории и парсит их в объекты `LogEntity`. Логи должны иметь формат:

```text
ip user date event status 
```

где:

- `ip` — IP-адрес пользователя
- `user` — имя пользователя
- `date` — дата и время события в формате `d.M.yyyy H:m:s`
- `event` — событие (например, LOGIN, SOLVE_TASK 18 и т.п.)
- `status` — статус события (OK, FAILED, ERROR)

---

## Основные возможности

- Получение уникальных IP за период
- Получение IP по пользователю, событию или статусу
- Получение пользователей по IP и другим параметрам
- Получение событий по IP, пользователю и статусу
- Получение дат по различным критериям
- Поддержка запросов в формате QL через метод `execute(String query)`

---

## Использование

### Инициализация

```java
import java.nio.file.Paths;

LogParser logParser = new LogParser(Paths.get("путь/к/директории/с/логами"));
```

### Примеры вызовов

```java
// Получить количество уникальных IP за весь период
int uniqueIPsCount = logParser.getNumberOfUniqueIPs(null, null);

// Получить все IP для пользователя "Eduard Petrovich Morozko"
Set<String> ips = logParser.getIPsForUser("Eduard Petrovich Morozko", null, null);

// Выполнить QL-запрос
String query1 = "get ip for user = \"Eduard Petrovich Morozko\" and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\"";
Set<Object> result = logParser.execute(query1);
```


---

## Формат QL-запросов

- `get <field1>` — получить все значения поля `<field1>` (`ip`, `user`, `date`, `event`, `status`)
- `get <field1> for <field2> = "<value>"` — получить значения `<field1>`, отфильтрованные по `<field2>`
- `get <field1> for <field2> = "<value>" and date between "<after>" and "<before>"` — то же, с фильтром по диапазону дат

---

## Требования к логам

- Формат даты: `d.M.yyyy H:m:s` (например, `11.12.2013 10:11:12`)
- Каждая запись должна содержать 5 полей, разделённых табуляцией (`\t`)
- События `SOLVE_TASK` и `DONE_TASK` могут содержать дополнительный параметр (номер задачи), например: `SOLVE_TASK 18`

---

## Пример лога

```text
127.0.0.1 Amigo 30.08.2012 16:08:13 LOGIN OK
192.168.100.2 Vasya Pupkin 30.08.2012 16:08:40 DONE_TASK 15 OK
146.34.15.5 Eduard Petrovich Morozko 13.09.2013 5:04:50 DOWNLOAD_PLUGIN OK
```

---
## Запуск через docker
```bash 
docker build -t .
docker images # Тут смотрим id образа
docker run -d --restart unless-stopped --name LogParser idImages # IdImages меняем на id образа
```

---
## Дополнительная информация

- Используется класс `SimpleDateFormat` для парсинга дат
- Внутри класса реализованы паттерн "Команда" (`Command`) для получения значений полей из `LogEntity`
- Метод `dateBetweenDates` проверяет, попадает ли дата события в заданный диапазон

---

