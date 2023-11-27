package org.skillbox;

import java.io.IOException;

public interface ContactsServ {
    void list();
    void save();
    void add(String record);
    void delete(String email);

}
