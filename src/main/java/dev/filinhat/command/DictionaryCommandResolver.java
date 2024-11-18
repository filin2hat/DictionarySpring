package dev.filinhat.command;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс резолвера команд словаря.
 */
@Component
public class DictionaryCommandResolver {
    private final List<DictionaryCommand> commandList;
    private final Map<Integer, DictionaryCommand> commandMap = new HashMap<>();

    public DictionaryCommandResolver(List<DictionaryCommand> commandList) {
        this.commandList = commandList;
    }

    @PostConstruct
    private void init() {
        commandList.forEach(command -> commandMap.put(command.getActionKey(), command));
    }


    /**
     * Резолвит команду по ключу действия.
     *
     * @param action номер действия.
     * @return команда словаря
     */
    public DictionaryCommand resolve(int action) {
        return commandMap.get(action);
    }
}