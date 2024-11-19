package dev.filinhat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Запуск Spring Boot приложения
@SpringBootApplication
public class DictionaryApp {
    public static void main(String[] args) {
        SpringApplication.run(DictionaryApp.class, args);
    }
}

// Запуск консольного приложения
//public class DictionaryApp {
//    public static void main(String[] args) {
//        ApplicationContext context = new AnnotationConfigApplicationContext(DictionaryConfiguration.class);
//        UserInteraction userInteraction = context.getBean(UserInteraction.class);
//        userInteraction.start();
//    }
//}
