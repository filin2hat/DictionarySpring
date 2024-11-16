package dev.filinhat;


import dev.filinhat.command.*;
import dev.filinhat.configuration.DictionaryConfiguration;
import dev.filinhat.service.DictionaryService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Component
public class DictionaryApp {
    private final Map<Integer, DictionaryService> dictionaries;
    private final Map<Integer, DictionaryCommand> commands = new HashMap<>();

    public DictionaryApp(Map<Integer, DictionaryService> dictionaries) {
        this.dictionaries = dictionaries;
    }

    private void initializeCommands(DictionaryService service) {
        commands.clear();
        commands.put(1, new DisplayEntriesCommand(service));
        commands.put(2, new SearchEntryCommand(service));
        commands.put(3, new AddEntryCommand(service));
        commands.put(4, new DeleteEntryCommand(service));
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

            DictionaryService dictionaryService = dictionaries.get(choice);
            if (dictionaryService == null) {
                System.out.println("\nНеверный выбор, попробуйте снова.");
            } else {
                initializeCommands(dictionaryService);
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
                command.execute(scanner);
            } else {
                System.out.println("\nНеверный выбор, попробуйте снова.");
            }
        }
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(DictionaryConfiguration.class);
        DictionaryApp app = context.getBean(DictionaryApp.class);
        app.start();
    }
}
