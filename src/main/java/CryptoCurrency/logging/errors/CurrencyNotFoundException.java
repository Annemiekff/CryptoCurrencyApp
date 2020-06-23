package CryptoCurrency.logging.errors;

import CryptoCurrency.logging.LoggingController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CurrencyNotFoundException extends RuntimeException{
/** an error that can be thrown when the user tries to find a currency that is not in the database. */

    Logger logger = LoggerFactory.getLogger(LoggingController.class);

    public CurrencyNotFoundException(String ticker) {
        super("Could not find currency " + ticker);
        logger.info("Currency not found. Ticker {}", ticker);
    }
}
