package dev.filinhat.command;

import dev.filinhat.service.DictionaryService;

import java.util.Scanner;

public class SearchEntryCommand implements DictionaryCommand {
    private final DictionaryService service;

    public SearchEntryCommand(DictionaryService service) {
        this.service = service;
    }

    @Override
    public void execute(Scanner scanner) {
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
