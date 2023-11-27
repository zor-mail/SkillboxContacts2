package org.skillbox;

import java.io.IOException;

public interface Storage {
    void list();
    void save(String exportPath);
    void add(String record);
    void delete(String email);
}
