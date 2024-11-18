package dev.filinhat.command;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DictionaryCommandResolver {
    private final List<DictionaryCommand> commandList; // Список всех команд
    private final Map<Integer, DictionaryCommand> commandMap = new HashMap<>(); // Мапа стратегий

    public DictionaryCommandResolver(List<DictionaryCommand> commandList) {
        this.commandList = commandList;
    }

    @PostConstruct
    private void init() {
        for (DictionaryCommand command : commandList) {
            commandMap.put(command.getActionKey(), command);
        }
    }

    public DictionaryCommand resolve(int action) {
        return commandMap.get(action);
    }
}
