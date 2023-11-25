package org.skillbox.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
@ComponentScan("org.skillbox")
@Configuration
@PropertySource("classpath:application.properties")
public class DefaultAppConfig {
/*    @Bean
    public ContactsRepo contactsRepo() {
        return new ContactsRepo();
    }*/
}
