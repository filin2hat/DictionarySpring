package dev.filinhat.command;

import dev.filinhat.service.DictionaryService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

/**
 * Класс команды удаления записи словаря
 */
@Component
public class DeleteEntryCommand implements DictionaryCommand {

    private static final int DELETE_ENTRY_COMMAND_KEY = 4;
    private DictionaryService service;

    @Override
    public void setService(DictionaryService service) {
        this.service = service;
    }

    @Override
    public int getActionKey() {
        return DELETE_ENTRY_COMMAND_KEY;
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
