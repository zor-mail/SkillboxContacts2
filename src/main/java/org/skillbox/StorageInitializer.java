package org.skillbox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StorageInitializer {

    Map<String, String> getInitializedStorage(String initFilePath) throws IOException {
        Map<String, String> initializedStorage = new HashMap<>();
        String newRecord;
        BufferedReader reader = null;
        if (initFilePath == null) {
            System.out.println("Не задан путь файла инициализации в файле свойств *.properties");
            return null;
        }
        try {
            File initFile = new File(initFilePath);
            reader = new BufferedReader(new FileReader(initFile));
            String line = reader.readLine();
            while (line != null) {
                String email = ContactsRepo.parseRecordString(line, null);
                if (email != null)
                    initializedStorage.put(email, line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла инициализации. Проверьте правильность пути и формата запиcей");
            if (reader != null)
                reader.close();
            return null;
        }
        return initializedStorage;
    }
}
