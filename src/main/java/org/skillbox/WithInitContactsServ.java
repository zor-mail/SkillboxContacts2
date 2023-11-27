package org.skillbox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import javax.annotation.PostConstruct;
import java.io.IOException;

@Primary
public class WithInitContactsServ implements ContactsServ {

    private final ContactsRepo contactsRepo;
    private final StorageInitializer storageInitializer;

    @Value("${app.init-contacts-file-path}")
    String initFilePath;

    @Value("${app.saved-contacts-file-path}")
    String saveFilePath;

    @Autowired
    public WithInitContactsServ(ContactsRepo contactsRepo, StorageInitializer storageInitializer) throws IOException {
        this.storageInitializer = storageInitializer;
        this.contactsRepo = contactsRepo;
    }

    @PostConstruct
    private void initPostConstruct() throws IOException {
        this.contactsRepo.initStorage(
                this.storageInitializer.getInitializedStorage(initFilePath)
        );
    }

    @Override
    public void list() {
        contactsRepo.list();
    }

    @Override
    public void save() throws IOException {
        contactsRepo.save(saveFilePath);
    }

    @Override
    public void add(String record) throws IOException {
        contactsRepo.add(record);
    }

    @Override
    public void delete(String email) throws IOException {
        contactsRepo.delete(email);
    }
}
