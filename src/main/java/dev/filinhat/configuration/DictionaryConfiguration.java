package dev.filinhat.configuration;

import dev.filinhat.repository.DictionaryRepository;
import dev.filinhat.repository.MapRepository;
import dev.filinhat.service.DictionaryService;
import dev.filinhat.service.FileDictionaryServiceImpl;
import dev.filinhat.validator.FiveDigitValidator;
import dev.filinhat.validator.FourLetterValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс конфигурации компонентов Spring, требующих ручной настройки.
 */
@Configuration
@ComponentScan(basePackages = "dev.filinhat")
public class DictionaryConfiguration {

    /**
     * Создает Map целочисленных ключей для экземпляров {@link DictionaryService},
     * позволяя пользователю выбирать, какой сервис использовать для данной операции.
     * Ключ для четырехбуквенного сервиса — 1, а ключ для пятизначного сервиса - 2.
     *
     * @param fourLetterDictionaryService сервис для четырехбуквенных ключей
     * @param fiveDigitDictionaryService  сервис для пятицифренных ключей
     * @return мапа ключей и соответствующих сервисов
     */
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

    /**
     * Создает репозиторий словаря для хранения записей с четырехбуквенными ключами.
     *
     * @return экземпляр {@link DictionaryRepository}, использующий файл fourLetterDictionary.txt
     */
    @Bean
    public DictionaryRepository fourLetterDictionaryRepository() {
        return new MapRepository(Path.of("src/main/resources/fourLetterDictionary.txt"));
    }

    /**
     * Создает репозиторий словаря для хранения записей с пятицифровыми ключами.
     *
     * @return экземпляр {@link DictionaryRepository}, использующий файл fiveDigitDictionary.txt
     */
    @Bean
    public DictionaryRepository fiveDigitDictionaryRepository() {
        return new MapRepository(Path.of("src/main/resources/fiveDigitDictionary.txt"));
    }


    /**
     * Создает сервис словаря для четырехбуквенных ключей.
     * <p>
     * {@link FileDictionaryServiceImpl} будет использоваться для реализации методов,
     * а {@link FourLetterValidator} - для валидации ключей.
     * <p>
     * Репозиторием словаря будет {@link DictionaryRepository}, созданный методом
     * {@link #fourLetterDictionaryRepository()}.
     *
     * @param fourLetterValidator            валидатор для четырехбуквенных ключей
     * @param fourLetterDictionaryRepository репозиторий словаря для четырехбуквенных ключей
     * @return экземпляр {@link DictionaryService} для четырехбуквенных ключей
     */
    @Bean
    @Qualifier("fourLetterDictionaryService")
    public DictionaryService fourLetterDictionaryService(
            FourLetterValidator fourLetterValidator,
            @Qualifier("fourLetterDictionaryRepository") DictionaryRepository fourLetterDictionaryRepository
    ) {
        return new FileDictionaryServiceImpl(fourLetterValidator, fourLetterDictionaryRepository);
    }

    /**
     * Создает сервис словаря для пятицифровых ключей.
     * <p>
     * {@link FileDictionaryServiceImpl} будет использоваться для реализации методов,
     * а {@link FiveDigitValidator} - для валидации ключей.
     * <p>
     * Репозиторием словаря будет {@link DictionaryRepository}, созданный методом
     * {@link #fiveDigitDictionaryRepository()}.
     *
     * @param fiveDigitValidator            валидатор для пятицифровых ключей
     * @param fiveDigitDictionaryRepository репозиторий словаря для пятицифровых ключей
     * @return экземпляр {@link DictionaryService} для пятицифровых ключей
     */
    @Bean
    @Qualifier("fiveDigitDictionaryService")
    public DictionaryService fiveDigitDictionaryService(
            FiveDigitValidator fiveDigitValidator,
            @Qualifier("fiveDigitDictionaryRepository") DictionaryRepository fiveDigitDictionaryRepository
    ) {
        return new FileDictionaryServiceImpl(fiveDigitValidator, fiveDigitDictionaryRepository);
    }
}
