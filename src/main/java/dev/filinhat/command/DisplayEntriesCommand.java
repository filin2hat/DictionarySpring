package dev.filinhat.command;

import dev.filinhat.service.DictionaryService;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Scanner;

/**
 * Команда для отображения всех записей словаря.
 */
@Component
public class DisplayEntriesCommand implements DictionaryCommand {
    private DictionaryService service;

    @Override
    public void setService(DictionaryService service) {
        this.service = service;
    }

    @Override
    public int getActionKey() {
        return 1; // Уникальный ключ для команды "Отобразить запись"
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

        Map<String, String> entries = service.readEntries();
        if (entries.isEmpty()) {
            System.out.println("\nСловарь пуст.");
        } else {
            System.out.println("\nСодержимое словаря:");
            entries.forEach((key, value) -> System.out.println(key + " - " + value));
        }
    }
}
