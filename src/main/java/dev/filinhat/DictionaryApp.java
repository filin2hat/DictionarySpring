package dev.filinhat;


import dev.filinhat.command.DictionaryCommand;
import dev.filinhat.configuration.DictionaryConfiguration;
import dev.filinhat.service.DictionaryService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Scanner;

/**
 * Класс приложения словаря.
 */
@Component
public class DictionaryApp {
    private final Map<Integer, DictionaryService> dictionaries;
    private final Map<Integer, DictionaryCommand> commands;
    private DictionaryService dictionaryService;

    public DictionaryApp(
            Map<Integer, DictionaryService> dictionaries,
            Map<Integer, DictionaryCommand> commands) {
        this.dictionaries = dictionaries;
        this.commands = commands;
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(DictionaryConfiguration.class);
        DictionaryApp app = context.getBean(DictionaryApp.class);
        app.start();
    }

    public void start() {
        var scanner = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    \nВыберите словарь:
                    1. Словарь с 4-буквенными ключами (пример: test - тест).
                    2. Словарь с 5-цифровыми ключами (пример: 12345 - один два три четыре пять).
                    0. Выход.
                    \nВаш выбор:
                    """);
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) break;

            dictionaryService = dictionaries.get(choice);

            if (dictionaryService == null) {
                System.out.println("\nНеверный выбор, попробуйте снова.");
            } else {
                displayMenu(scanner);
            }
        }
        scanner.close();
    }

    private void displayMenu(Scanner scanner) {
        while (true) {
            System.out.println("""
                    \nМеню:
                    1. Просмотреть содержимое словаря
                    2. Найти запись по ключу
                    3. Добавить запись
                    4. Удалить запись
                    0. Назад
                    \nВаш выбор:
                    """);
            int action = scanner.nextInt();
            scanner.nextLine();

            if (action == 0) break;

            DictionaryCommand command = commands.get(action);
            if (command != null) {
                command.removeService();
                command.setService(dictionaryService);
                command.execute(scanner);
            } else {
                System.out.println("\nНеверный выбор, попробуйте снова.");
            }
        }
    }
}
