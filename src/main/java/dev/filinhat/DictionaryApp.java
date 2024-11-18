package dev.filinhat;

import dev.filinhat.configuration.DictionaryConfiguration;
import dev.filinhat.service.UserInteraction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DictionaryApp {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(DictionaryConfiguration.class);
        UserInteraction userInteraction = context.getBean(UserInteraction.class);
        userInteraction.start();
    }
}
