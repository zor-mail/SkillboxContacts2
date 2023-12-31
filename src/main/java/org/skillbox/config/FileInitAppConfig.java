package org.skillbox.config;

import org.skillbox.ContactsRepo;
import org.skillbox.ContactsServ;
import org.skillbox.StorageInitializer;
import org.skillbox.WithInitContactsServ;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application-file-init.properties")
@Profile("init")
public class FileInitAppConfig {

    @Bean
    public StorageInitializer storageInitializer() {
        return new StorageInitializer();
    }

    @Bean
    public ContactsServ contactsServ(ContactsRepo contactsRepo, StorageInitializer storageInitializer) {
        return new WithInitContactsServ(contactsRepo, storageInitializer);
    }

}
