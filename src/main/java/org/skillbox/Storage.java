package org.skillbox;

public interface Storage {
    void list();
    void save();
    void add(String record);
    void delete(String email);
}
