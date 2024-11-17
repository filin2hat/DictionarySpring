package dev.filinhat.configuration;

import dev.filinhat.command.*;
import dev.filinhat.repository.DictionaryRepository;
import dev.filinhat.repository.MapRepository;
import dev.filinhat.service.DictionaryService;
import dev.filinhat.service.FileDictionaryService;
import dev.filinhat.validator.FiveDigitValidator;
import dev.filinhat.validator.FourLetterValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan(basePackages = "dev.filinhat")
public class DictionaryConfiguration {

    @Bean
    public Map<Integer, DictionaryService> dictionaryServices(
            @Qualifier("fourLetterDictionaryService") DictionaryService fourLetterDictionaryService,
            @Qualifier("fiveDigitDictionaryService") DictionaryService fiveDigitDictionaryService
    ) {
        Map<Integer, DictionaryService> dictionaryMap = new HashMap<>();
        dictionaryMap.put(1, fourLetterDictionaryService);
        dictionaryMap.put(2, fiveDigitDictionaryService);
        return dictionaryMap;
    }

    @Bean
    public Map<Integer, DictionaryCommand> dictionaryCommands(
            DisplayEntriesCommand displayEntriesCommand,
            SearchEntryCommand searchEntryCommand,
            AddEntryCommand addEntryCommand,
            DeleteEntryCommand deleteEntryCommand) {
        Map<Integer, DictionaryCommand> commandMap = new HashMap<>();
        commandMap.put(1, displayEntriesCommand);
        commandMap.put(2, searchEntryCommand);
        commandMap.put(3, addEntryCommand);
        commandMap.put(4, deleteEntryCommand);
        return commandMap;
    }

    @Bean
    public DictionaryRepository fourLetterDictionaryRepository(
    ) {
        return new MapRepository(Path.of("src/main/resources/fourLetterDictionary.txt"));
    }

    @Bean
    public DictionaryRepository fiveDigitDictionaryRepository(
    ) {
        return new MapRepository(Path.of("src/main/resources/fiveDigitDictionary.txt"));
    }


    @Bean
    public DictionaryService fourLetterDictionaryService(
            FourLetterValidator fourLetterValidator,
            @Qualifier("fourLetterDictionaryRepository") DictionaryRepository fourLetterDictionaryRepository
    ) {
        return new FileDictionaryService(fourLetterValidator, fourLetterDictionaryRepository);
    }

    @Bean
    public DictionaryService fiveDigitDictionaryService(
            FiveDigitValidator fiveDigitValidator,
            @Qualifier("fiveDigitDictionaryRepository") DictionaryRepository fiveDigitDictionaryRepository
    ) {
        return new FileDictionaryService(fiveDigitValidator, fiveDigitDictionaryRepository);
    }
}
