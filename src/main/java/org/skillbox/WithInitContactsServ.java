package org.skillbox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import javax.annotation.PostConstruct;

@Primary
public class WithInitContactsServ implements ContactsServ {

    private final ContactsRepo contactsRepo;
    private final StorageInitializer storageInitializer;

    @Value("${app.init-contacts-file-path}")
    String initFilePath;


    @Autowired
    public WithInitContactsServ(ContactsRepo contactsRepo, StorageInitializer storageInitializer) {
        this.storageInitializer = storageInitializer;
        this.contactsRepo = contactsRepo;
    }

    @PostConstruct
    private void initPostConstruct() {
        this.contactsRepo.initStorage(
                this.storageInitializer.getInitializedStorage(initFilePath)
        );
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
