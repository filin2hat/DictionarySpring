package dev.filinhat.service;

import dev.filinhat.command.DictionaryCommand;
import dev.filinhat.command.DictionaryCommandResolver;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Scanner;

/**
 * Сервис взаимодействия с пользователем в консоли.
 */
@Service
public class UserInteractionServiceImpl implements UserInteraction {
    private final Map<Integer, DictionaryService> dictionaryServices;
    private final DictionaryCommandResolver commandResolver;
    private DictionaryService dictionaryService;

    public UserInteractionServiceImpl(
            Map<Integer, DictionaryService> dictionaryServices,
            DictionaryCommandResolver commandResolver) {
        this.dictionaryServices = dictionaryServices;
        this.commandResolver = commandResolver;
    }

    @Override
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

            if (choice == 0) {
                break;
            }

            dictionaryService = dictionaryServices.get(choice);

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

            DictionaryCommand command = commandResolver.resolve(action);
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
