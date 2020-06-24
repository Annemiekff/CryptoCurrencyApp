package CryptoCurrency.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

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

    /** This will create 4 currencies and preload them in the database */
    @Bean
    CommandLineRunner initDatabase2(CurrencyRepository2 repository2) {
        return args -> {
            log.info("Preloading " + repository2.save(new Currency2.CurrencyBuilder("BTC", "Bitcoin").number_of_coins(16770000L).market_cap(189580000000L).build()));
            log.info("Preloading " + repository2.save(new Currency2.CurrencyBuilder("ETH", "Ethereum").number_of_coins(96710000L).market_cap(69280000000L).build()));
            log.info("Preloading " + repository2.save(new Currency2.CurrencyBuilder("XRP", "Ripple").number_of_coins(38590000000L).market_cap(64750000000L).build()));
            log.info("Preloading " + repository2.save(new Currency2.CurrencyBuilder("BCH", "BitcoinCash").number_of_coins(16670000L).market_cap(69020000000L).build()));
        };
    }
}
