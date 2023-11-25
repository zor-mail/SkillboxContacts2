package org.skillbox;

import java.io.IOException;
import java.util.List;

public interface Storage {
    String get(String email) throws IOException;
    void list();
    void save(String exportPath) throws IOException;
    void add(String record) throws IOException;
    void delete(String email) throws IOException;
}
