### Приложение для работы с почтовым REST API

Приложение сделано по тех. заданию:
[Task](./Task.md)

В приложение зашито заполнение данных для облегчения 
навигации по приложению:
[DataInitializer](./src/main/java/com/example/fabric/DataInitializer.java)

Логи выводятся в консоль

Все API прописаны в:
[Postman](./Postman.md)

настройки базы данных и порт сервера прописаны:
[application](./src/main/resources/application.yaml)

Собираем образ Docker:
~~~docker
docker build -t postApi .
~~~
