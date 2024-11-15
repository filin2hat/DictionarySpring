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
    private final Map<Integer, DictionaryService> dictionaryMap;
    private final Map<Integer, DictionaryCommand> commandMap = new HashMap<>();

    public DictionaryApp(Map<Integer, DictionaryService> dictionaryMap) {
        this.dictionaryMap = dictionaryMap;
    }

    private void initializeCommands(DictionaryService dictionary) {
        commandMap.clear();
        commandMap.put(1, new DisplayEntriesCommand(dictionary));
        commandMap.put(2, new SearchEntryCommand(dictionary));
        commandMap.put(3, new AddEntryCommand(dictionary));
        commandMap.put(4, new DeleteEntryCommand(dictionary));
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

            DictionaryService currentDictionary = dictionaryMap.get(choice);
            if (currentDictionary == null) {
                System.out.println("\nНеверный выбор, попробуйте снова.");
            } else {
                initializeCommands(currentDictionary);
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

            DictionaryCommand command = commandMap.get(action);
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
