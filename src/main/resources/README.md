______________________________

 *** Программа "Контакты" ***
______________________________

1. Программа "Контакты" при помощи консольных команд выполняет задачи 
хранения, редактирования и экспорта списка контактов 
в формате - ФИО ; номер телефона ; email
(н-р, Иванов Иван Иванович;+890999999;someEmail@example.example)

2. Команды c параметрами:
А) NEW - добавление нового контакта. После нажатия клавиши ВВОД 
можно ввести данные нового контакта.
B) DEL - Удаление существующего контакта. 
После нажатия клавиши ВВОД необходимо ввести емейл записи, 
которая будет удалена.
C) EXIT - Выход из программы. 
Необходимо подтвердить выход вводом буквы Y.
После ввода параметра необходимо нажать клавишу ВВОД.

3. Команды без параметров:
А) LIST - выводит все имеющиеся контакты в виде списка
B) SAVE - сохраняет все имеющиеся контакты в файл по пути, 
указанном в файле application.properties
После ввода команды необходимо нажать клавишу ВВОД.

4. Программа позволяет инициализировать список контактов 
значениями из файла, хранящегося по пути, указанном в файле 
application-file-init.properties
Для активации этой опции необходимо в файле 
application.properties выставить параметр spring.profiles.active
в значение "init" без кавычек
spring.profiles.active=init
