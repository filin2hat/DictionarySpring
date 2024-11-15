package dev.filinhat.configuration;

import dev.filinhat.DictionaryApp;
import dev.filinhat.repository.MapRepository;
import dev.filinhat.service.DictionaryService;
import dev.filinhat.service.FileDictionaryService;
import dev.filinhat.util.FileUtils;
import dev.filinhat.validator.FiveDigitValidator;
import dev.filinhat.validator.FourLetterValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan(basePackages = "dev.filinhat")
public class DictionaryConfiguration {

    @Bean
    public DictionaryApp dictionaryApp() {
        return new DictionaryApp(dictionaryMap());
    }

    @Bean
    public Map<Integer, DictionaryService> dictionaryMap() {
        Map<Integer, DictionaryService> dictionaryMap = new HashMap<>();
        dictionaryMap.put(1, fourLetterDictionaryService());
        dictionaryMap.put(2, fiveDigitDictionaryService());
        return dictionaryMap;
    }

    // создание экземпляров Path
    @Bean
    @Primary
    public Path fourLetterDictionaryPath() {
        return Path.of("src/main/resources/fourLetterDictionary.txt");
    }

    @Bean
    public Path fiveDigitDictionaryPath() {
        return Path.of("src/main/resources/fiveDigitDictionary.txt");
    }

    // создание экземпляров репозиториев
    @Bean
    @Primary
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
    @Primary
    public FourLetterValidator fourLetterValidator() {
        return new FourLetterValidator();
    }


    //создание экземпляров сервисов
    @Bean
    public DictionaryService fourLetterDictionaryService() {
        return new FileDictionaryService(fourLetterValidator(), fourLetterDictionaryRepository());
    }

    @Bean
    public DictionaryService fiveDigitDictionaryService() {
        return new FileDictionaryService(fiveDigitValidator(), fiveDigitDictionaryRepository());
    }
}
