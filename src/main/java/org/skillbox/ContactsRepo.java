package org.skillbox;

import org.springframework.stereotype.Component;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Component
public class ContactsRepo  implements Storage {
    Map<String, String> storage = new HashMap<>();

    @Override
    public void list() {
        if (storage.isEmpty())
            System.out.println("-- Список контактов пуст -- ");
        else {
            for(String record : storage.values()) {
                System.out.println(record);
            }
        }
    }

    @Override
    public void save(String saveFilePath) throws IOException {
        try {
            File file = new File(saveFilePath);
            if (!file.createNewFile()) {
                if (!file.delete()) {
                    System.out.println("Ошибка экспорта контактов. Возможно, файл открыт в другом приложении.");
                    return;
                } else {
                    if (!file.createNewFile()) {
                        System.out.println("Ошибка экспорта контактов. Возможно, файл открыт в другом приложении.");
                        return;
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Ошибка при создании файла. Проверьте путь в конфигурации.");
        }
        try {
            FileWriter writer = new FileWriter(saveFilePath);
            for (String record : this.storage.values()) {
                writer.write(record + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Ошибка при записи контакта в файл " + e.getMessage());
        }
    }

    @Override
    public void add(String record) throws IOException {
        String email = parseRecordString(record, this.storage);
        if (email != null) {
            storage.put(email, record);
            System.out.println(MessageFormat.format("Запись с email {0} успешно добавлена", email));
        }
    }

    public static String parseRecordString(String record, Map<String, String> storage) {
        String[] recordParts = record.split(";");
        if (recordParts == null || recordParts.length != 3) {
            System.out.println("В контакте должны быть 3 части, разделенные точкой с запятой. Запись: " + record);
            return null;
        }
        String FIOPart = recordParts[0].trim();
        String phone = recordParts[1].trim();
        String email = recordParts[2].trim();

        if (storage != null) {
            String oldRecord = storage.get(email);
            if (oldRecord != null) {
                System.out.println(MessageFormat.format(
                        "Ошибка ввода. Запись не произведена. В контактах уже присутствует запись с email {0} ", email));
                return null;
            }
        }

        if (FIOPart.isEmpty()) {
            System.out.println("В подстроке ФИО должны быть 3 части, разделенные пробелами. Запись: " + record);
            return null;
        }

        String[] fioParts = FIOPart.split(" ");
        if (fioParts.length != 3) {
            System.out.println("В подстроке ФИО должны быть 3 части, разделенные пробелами. Запись: " + record);
            return null;
        }

        String phoneRegex = "^\\+\\d{1,3}\\d*$";
        if (!Pattern.matches(phoneRegex, phone)) {
            System.out.println("Некорректный формат номера телефона в строке: " + record);
            return null;
        }

        String emailRegex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        if (Pattern.matches(emailRegex, email))
            return email;
        else
            System.out.println("Некорректный формат емайл в строке: " + record);
        return null;
    }

    @Override
    public void delete(String email) throws IOException {
        String removed = storage.remove(email);
        if (removed == null)
            System.out.println(MessageFormat.format("Запись для удаления с email {0} не найдена", email));
        else
            System.out.println(MessageFormat.format("Запись с email {0} успешно удалена", email));
    }

    public void initStorage(Map<String, String> initializedStorage) throws IOException {
        if (initializedStorage != null) {
            this.storage = initializedStorage;
            System.out.println("Хранилище успешно инициализировано из файла");
        } else
            System.out.println("Инициализация хранилища не произведена из-за ошибки чтения файла");
    }
}
