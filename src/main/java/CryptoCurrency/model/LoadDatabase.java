package CryptoCurrency.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    /** This will create 4 currencies and preload them in the database */
    @Bean
    CommandLineRunner initDatabase(CurrencyRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Currency("BTC", "Bitcoin", 16770000L, 189580000000L)));
            log.info("Preloading " + repository.save(new Currency("ETH", "Ethereum", 96710000L, 69280000000L)));
            log.info("Preloading " + repository.save(new Currency("XRP", "Ripple", 38590000000L, 64750000000L)));
            log.info("Preloading " + repository.save(new Currency("BCH", "BitcoinCash", 16670000L, 69020000000L)));
        };
    }
}
