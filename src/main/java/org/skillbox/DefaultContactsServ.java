package org.skillbox;

import org.springframework.stereotype.Component;

@Component
public class DefaultContactsServ implements ContactsServ {

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
        contactsRepo.save();
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
