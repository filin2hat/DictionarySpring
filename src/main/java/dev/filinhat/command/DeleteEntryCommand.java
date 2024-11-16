package dev.filinhat.command;

import dev.filinhat.service.DictionaryService;

import java.util.Scanner;

public class DeleteEntryCommand implements DictionaryCommand {
    private final DictionaryService service;

    public DeleteEntryCommand(DictionaryService service) {
        this.service = service;
    }

    @Override
    public void execute(Scanner scanner) {
        System.out.print("\nВведите ключ для удаления: ");
        String key = scanner.nextLine();

        if (service.readEntries().containsKey(key)) {
            service.deleteEntry(key);
            System.out.println("\nЗапись удалена.");
        } else {
            System.out.println("\nЗапись с таким ключом не найдена.");
        }
    }
}
