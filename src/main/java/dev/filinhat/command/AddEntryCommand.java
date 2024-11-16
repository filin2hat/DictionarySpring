package dev.filinhat.command;

import dev.filinhat.service.DictionaryService;

import java.util.Scanner;

public class AddEntryCommand implements DictionaryCommand {
    private final DictionaryService service;

    public AddEntryCommand(DictionaryService service) {
        this.service = service;
    }

    @Override
    public void execute(Scanner scanner) {
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
