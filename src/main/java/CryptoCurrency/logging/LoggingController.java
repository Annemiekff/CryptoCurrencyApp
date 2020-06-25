package CryptoCurrency.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggingController {
    /** logs the types of actions to CryptoCurrencyLog.txt. */

    Logger logger = LoggerFactory.getLogger(LoggingController.class);

    @RequestMapping("/")
    public String index() {
        logger.trace("A TRACE message");
        logger.debug("A DEBUG message");
        logger.info("An INFO message");
        logger.warn("A WARN message");
        logger.error("An ERROR message");

        return "Hello! Check out the Logs to see the output...";
    }
}
