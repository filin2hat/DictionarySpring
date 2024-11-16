package dev.filinhat.command;

import dev.filinhat.service.DictionaryService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

/**
 * Команда поиска записи в словаре.
 */
@Component
public class SearchEntryCommand implements DictionaryCommand {
    private DictionaryService service;

    @Override
    public void setService(DictionaryService service) {
        this.service = service;
    }

    @Override
    public void removeService() {
        this.service = null;
    }

    @Override
    public void execute(Scanner scanner) {
        if (service == null) {
            System.out.println("\nСервис не установлен.");
            return;
        }

        System.out.print("\nВведите ключ для поиска: ");
        String key = scanner.nextLine();
        String value = service.searchEntry(key);
        if (value != null) {
            System.out.println("\nНайдена запись: " + key + " - " + value);
        } else {
            System.out.println("\nЗапись с ключом '" + key + "' не найдена.");
        }
    }
}
