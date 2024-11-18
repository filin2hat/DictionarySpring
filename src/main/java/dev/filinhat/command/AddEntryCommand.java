package dev.filinhat.command;

import dev.filinhat.service.DictionaryService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddEntryCommand implements DictionaryCommand {
    private DictionaryService service;

    @Override
    public int getActionKey() {
        return 3; // Уникальный ключ для команды "Добавить запись"
    }

    @Override
    public void removeService() {
        this.service = null;
    }

    @Override
    public void setService(DictionaryService service) {
        this.service = service;
    }

    @Override
    public void execute(Scanner scanner) {
        if (service == null) {
            System.out.println("\nСервис не установлен.");
            return;
        }
        System.out.print("\nВведите ключ: ");
        String key = scanner.nextLine();
        System.out.print("\nВведите значение: ");
        String value = scanner.nextLine();

        try {
            service.addEntry(key, value);
            System.out.println("\nЗапись добавлена.");
        } catch (IllegalArgumentException e) {
            System.out.println("\nОшибка: " + e.getMessage());
        }
    }
}
