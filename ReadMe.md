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


Deploy на сервер:
Собираем образ
docker build -t authservice .

Сохраняем контейнер в файл (в ту же дирректорию где и образ)
sudo docker save -o authservice.tar authservice

Копируем на сервер по SSH (~ означает сохраниь в домашнюю директорию)
sudo scp authservice.tar root@85.159.231.215:~

Заходим на наш сервер:
ssh root@85.159.231.215

Импортируем образ из файла на сервере:
docker load < authservice.tar

Запускаем контейнер на сервере в режиме демона:
docker run -d -p 8000:8000 authservice

Удаляем файл на сервере
rm authservice.tar
