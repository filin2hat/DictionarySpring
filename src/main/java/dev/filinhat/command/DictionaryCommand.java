package dev.filinhat.command;

import dev.filinhat.service.DictionaryService;

import java.util.Scanner;

/**
 * Интерфейс команд сервиса словаря
 */
public interface DictionaryCommand {

    /**
     * Возвращает уникальный ключ действия команды.
     *
     * @return ключ действия
     */
    int getActionKey();

    /**
     * Удаляет сервис словаря.
     */
    void removeService();

    /**
     * Устанавливает сервис словаря.
     *
     * @param service сервис словаря
     */
    void setService(DictionaryService service);

    /**
     * Выполняет команду словаря.
     *
     * @param scanner сканер ввода
     */
    void execute(Scanner scanner);
}
