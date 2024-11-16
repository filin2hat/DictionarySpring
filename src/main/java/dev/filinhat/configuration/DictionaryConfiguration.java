package dev.filinhat.configuration;

import dev.filinhat.DictionaryApp;
import dev.filinhat.repository.MapRepository;
import dev.filinhat.service.DictionaryService;
import dev.filinhat.service.FileDictionaryService;
import dev.filinhat.validator.FiveDigitValidator;
import dev.filinhat.validator.FourLetterValidator;
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
    public DictionaryApp dictionaryApp() {
        return new DictionaryApp(dictionaries());
    }

    @Bean
    public Map<Integer, DictionaryService> dictionaries() {
        Map<Integer, DictionaryService> dictionaryMap = new HashMap<>();
        dictionaryMap.put(1, fourLetterDictionaryService());
        dictionaryMap.put(2, fiveDigitDictionaryService());
        return dictionaryMap;
    }

    // создание экземпляров Path
    @Bean
    public Path fourLetterDictionaryPath() {
        return Path.of("src/main/resources/fourLetterDictionary.txt");
    }

    @Bean
    public Path fiveDigitDictionaryPath() {
        return Path.of("src/main/resources/fiveDigitDictionary.txt");
    }

    // создание экземпляров репозиториев
    @Bean
    public MapRepository fourLetterDictionaryRepository() {
        return new MapRepository(fourLetterDictionaryPath());
    }

    @Bean
    public MapRepository fiveDigitDictionaryRepository() {
        return new MapRepository(fiveDigitDictionaryPath());
    }


    // создание экземпляров валидаторов
    @Bean
    public FiveDigitValidator fiveDigitValidator() {
        return new FiveDigitValidator();
    }

    @Bean
    public FourLetterValidator fourLetterValidator() {
        return new FourLetterValidator();
    }


    //создание экземпляров сервисов
    @Bean
    public DictionaryService fourLetterDictionaryService() {
        FileDictionaryService service = new FileDictionaryService();
        service.setValidator(fourLetterValidator());
        service.setRepository(fourLetterDictionaryRepository());
        return service;
    }

    @Bean
    public DictionaryService fiveDigitDictionaryService() {
        FileDictionaryService service = new FileDictionaryService();
        service.setValidator(fiveDigitValidator());
        service.setRepository(fiveDigitDictionaryRepository());
        return service;
    }
}
