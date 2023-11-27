package org.skillbox;

import org.skillbox.config.DefaultAppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(DefaultAppConfig.class);
        applicationContext.getBean(ContactsWorker.class).initApp();
    }
}