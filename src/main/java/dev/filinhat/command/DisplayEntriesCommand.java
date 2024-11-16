package dev.filinhat.command;

import dev.filinhat.service.DictionaryService;

import java.util.Map;
import java.util.Scanner;

public class DisplayEntriesCommand implements DictionaryCommand {
    private final DictionaryService service;

    public DisplayEntriesCommand(DictionaryService service) {
        this.service = service;
    }

    @Override
    public void execute(Scanner scanner) {
        Map<String, String> entries = service.readEntries();
        if (entries.isEmpty()) {
            System.out.println("\nСловарь пуст.");
        } else {
            System.out.println("\nСодержимое словаря:");
            entries.forEach((key, value) -> System.out.println(key + " - " + value));
        }
    }
}
