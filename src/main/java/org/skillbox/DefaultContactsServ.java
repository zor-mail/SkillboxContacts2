package org.skillbox;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DefaultContactsServ implements ContactsServ {

    @Value("${app.saved-contacts-file-path}")
    String saveFilePath;

    private final ContactsRepo contactsRepo;

    public DefaultContactsServ(ContactsRepo contactsRepo) {
        this.contactsRepo = contactsRepo;
    }

    @Override
    public void list() {
        contactsRepo.list();
    }

    @Override
    public void save() {
        contactsRepo.save(saveFilePath);
    }

    @Override
    public void add(String record) {
        contactsRepo.add(record);
    }

    @Override
    public void delete(String email) {
        contactsRepo.delete(email);
    }
}
