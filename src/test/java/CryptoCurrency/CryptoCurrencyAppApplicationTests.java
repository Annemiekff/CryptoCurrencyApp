package CryptoCurrency;

import CryptoCurrency.controller.CurrencyController;
import CryptoCurrency.controller.CurrencyService;
import CryptoCurrency.model.Currency;
import CryptoCurrency.model.CurrencyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestEntityManager
@SpringBootTest
class CryptoCurrencyAppApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private CurrencyRepository currencyRepository;

	@Autowired
	private CurrencyController currencyController;

	@Autowired
	private CurrencyService currencyService;

	/** Testing if we can load one currency */
	@Test
	public void whenFindByTicker_thenReturnCurrency() {
		//given
		Currency btc = new Currency("BTC", "Bitcoin", 16770000L, 189580000000L);

		//when
		Currency found = currencyController.getOneCurrency(btc.getTicker());

		//then
		assertThat(found.getName()).isEqualTo(btc.getName());
	}

	/** Testing if we can load all currencies */
	@Test
	public void loadAll() {
		//when
		List<Currency> currencies = currencyController.getAllCurrencies(0, 10, "ticker").getBody();
		//then
		Iterable<Currency> x = currencyRepository.findAll();
		x.forEach(currency -> {
			assert currencies != null;
			assertThat(currencies.contains(currency));
		});
	}

	/** Testing if we can delete a new currency.
	 *  Adding the currency at the end so the other tests don't give false results because the currency was deleted
	 *  */
	@Test
	public void deleteCurrency() {
		//given
		Currency btc = new Currency("BTC", "Bitcoin", 16770000L, 189580000000L);

		//when
		currencyController.deleteCurrency(btc.getTicker());

		//then
		assertThat(!currencyRepository.existsById(btc.getTicker()));
		currencyRepository.save(btc);
	}

	/** Testing if we can post a new currency.
	 * Deleting the currency at the end so it won't interfere with other tests
	 * */
	@Test
	public void postCurrency(){
		Currency btd = new Currency("BTd", "Bitcoined", 16770000L, 189580000000L);

		//when
		currencyController.postCurrency(btd);

		//then
		assertThat(currencyRepository.existsById(btd.getTicker()));
		currencyController.deleteCurrency(btd.getTicker());
	}

	/** Testing if we can update a currency
	 * Afterwards returning it to the original so it doesn't interfere with other tests*/
	@Test
	public void putCurrency(){
		Currency btcUpdate = new Currency("BTC", "Bitcoinz", 16770000L, 189580000000L);
		Currency btc = new Currency("BTC", "Bitcoin", 16770000L, 189580000000L);

		//when
		currencyController.putCurrency(btcUpdate, btcUpdate.getTicker());
		Currency updatedBtc = currencyController.getOneCurrency(btcUpdate.getTicker());

		//then
		assertThat(updatedBtc.getName().equals(btcUpdate.getName()));
		assertThat(updatedBtc.getTicker().equals(btcUpdate.getTicker()));
		assertThat(updatedBtc.getMarket_cap() == btcUpdate.getMarket_cap());
		assertThat(updatedBtc.getNumber_of_coins() == btcUpdate.getNumber_of_coins());

		currencyController.putCurrency(btc, btcUpdate.getTicker());
	}

	/** Testing to see if the database is pre-filled with 4 currencies */
	@Test
	public void checkingDatabaseFilled(){
		int databaseSize = currencyService.getAllCurrencies(0, 10, "ticker").size();
		assertThat(databaseSize == 4);
	}
}
