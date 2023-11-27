package org.skillbox;

public interface ContactsServ {
    void list();
    void save();
    void add(String record);
    void delete(String email);

}
