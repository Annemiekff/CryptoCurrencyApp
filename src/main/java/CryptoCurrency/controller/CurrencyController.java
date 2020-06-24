package CryptoCurrency.controller;

import CryptoCurrency.logging.LoggingController;
import CryptoCurrency.model.Currency;
import CryptoCurrency.model.Currency2;
import CryptoCurrency.model.CurrencyRepository;
import CryptoCurrency.logging.errors.CurrencyNotFoundException;
import CryptoCurrency.model.CurrencyRepository2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** \@RestController indicates that the data returned by each method will be written straight into the response body instead of rendering a template.
 */

@RestController
public class CurrencyController {

    private Logger logger = LoggerFactory.getLogger(LoggingController.class);

    @Autowired
    private CurrencyService service;

    private final CurrencyRepository repository;

    private final CurrencyRepository2 repository2;
    /**
     An CurrencyRepository and CurrencyRepository2 is injected by constructor into the controller.
     */
    public CurrencyController(CurrencyRepository repository, CurrencyRepository2 repository2){
        this.repository = repository;
        this.repository2 = repository2;
    }

    /**
     * We have routes for each operations (@GetMapping, @PostMapping, @PutMapping and @DeleteMapping,
     * corresponding to HTTP GET (all or one), POST, PUT, and DELETE calls)
     */
    //Root
    @GetMapping("/api/currencies")
    public ResponseEntity<List<Currency>> getAllCurrencies(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "ticker") String sortBy)
    {
        List<Currency> list = service.getAllCurrencies(pageNo, pageSize, sortBy);
        logger.info("Request for all currencies starting page {}, page size {} & sorted by {}", pageNo, pageSize, sortBy);
        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/api/currencies")
    public Currency postCurrency(@RequestBody Currency newCurrency) {
        logger.info("Adding new Currency with ticker {}, name {}, number of coins {} and market value {}",
                newCurrency.getTicker(), newCurrency.getName(),
                newCurrency.getNumber_of_coins(), newCurrency.getMarket_cap());

        return repository.save(newCurrency);
    }

    //Single item
    @GetMapping("/api/currencies/{ticker}")
    public Currency getOneCurrency(@PathVariable String ticker) {
        logger.info("Request for currency with ticker {}", ticker);

        return repository.findById(ticker.toUpperCase())
                .orElseThrow(() -> new CurrencyNotFoundException(ticker));
    }

    /** CurrencyNotFoundException is an exception used to indicate when a currency is looked up but not found */
    @PutMapping("/api/currencies/{ticker}")
    public Currency putCurrency(@RequestBody Currency newCurrency, @PathVariable String ticker){

        return repository.findById(ticker)
                .map(currency -> {
                    currency.setName(newCurrency.getName());
                    currency.setNumber_of_coins(newCurrency.getNumber_of_coins());
                    currency.setMarket_cap(newCurrency.getMarket_cap());
                    logger.info("updated currency ticker {} with name {}, number of coins {} and market cap {}",
                            newCurrency.getTicker(), newCurrency.getName(),
                            newCurrency.getNumber_of_coins(), newCurrency.getMarket_cap());
                    return repository.save(currency);
                })
                .orElseGet(() -> {
                    newCurrency.setTicker(ticker);
                    logger.info("added a new currency with only a ticker{}", ticker);
                    return repository.save(newCurrency);
                });
    }

    @DeleteMapping("/api/currencies/{ticker}")
    public void deleteCurrency(@PathVariable String ticker){
        logger.info("Deleted currency with ticker{}", ticker);
        repository.deleteById(ticker);
    }

    /**
     * We have routes for each operations (@GetMapping, @PostMapping, @PutMapping and @DeleteMapping,
     * corresponding to HTTP GET (all or one), POST, PUT, and DELETE calls) for the currency2 with the builder pattern
     */
    //Root
    @GetMapping("/api/currencies2")
    public ResponseEntity<List<Currency2>> getAllCurrencies2(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "ticker") String sortBy)
    {
        List<Currency2> list = service.getAllCurrencies2(pageNo, pageSize, sortBy);
        logger.info("Request for all currencies2 starting page {}, page size {} & sorted by {}", pageNo, pageSize, sortBy);
        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/api/currencies2")
    public Currency2 postCurrency2(@RequestBody Currency2 newCurrency) {
        logger.info("Adding new Currency2 with ticker {}, name {}, number of coins {} and market value {}",
                newCurrency.getTicker(), newCurrency.getName(),
                newCurrency.getNumber_of_coins(), newCurrency.getMarket_cap());

        return repository2.save(newCurrency);
    }

    //Single item
    @GetMapping("/api/currencies2/{ticker}")
    public Currency2 getOneCurrency2(@PathVariable String ticker) {
        logger.info("Request for currency2 with ticker {}", ticker);

        return repository2.findById(ticker.toUpperCase())
                .orElseThrow(() -> new CurrencyNotFoundException(ticker));
    }

    /** CurrencyNotFoundException is an exception used to indicate when a currency is looked up but not found */
    @PutMapping("/api/currencies2/{ticker}")
    public Currency2 putCurrency2(@RequestBody Currency2 newCurrency, @PathVariable String ticker){

        return repository2.findById(ticker)
                .map(currency -> {
                    currency = new Currency2.CurrencyBuilder(newCurrency.getTicker(), newCurrency.getName()).number_of_coins(newCurrency.getNumber_of_coins()).market_cap(newCurrency.getMarket_cap()).build();
                    logger.info("updated currency2 ticker {} with name {}, number of coins {} and market cap {}",
                            newCurrency.getTicker(), newCurrency.getName(),
                            newCurrency.getNumber_of_coins(), newCurrency.getMarket_cap());
                    return repository2.save(currency);
                })
                .orElseGet(() -> {
                    logger.info("no currency2 in the database with requested ticker {}, currency added", ticker);
                    return repository2.save(newCurrency);
                });
    }

    @DeleteMapping("/api/currencies2/{ticker}")
    public void deleteCurrency2(@PathVariable String ticker){
        logger.info("Deleted currency2 with ticker{}", ticker);
        repository2.deleteById(ticker);
    }
}
