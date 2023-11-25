package org.skillbox;

import org.skillbox.config.DefaultAppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DefaultAppConfig.class);
        applicationContext.getBean(ContactsWorker.class).initApp();
    }
}