package org.skillbox;

import java.io.IOException;

public interface ContactsServ {
    void list();
    void save() throws IOException;
    void add(String record) throws IOException;
    void delete(String email) throws IOException;

}
