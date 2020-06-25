package CryptoCurrency.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity //JPA annotation to make the object ready for storage in a JPA-based data store
@Table(name = "currencies2")
@EntityListeners(AuditingEntityListener.class)
public class Currency2 {
    /** Entity is a JPA annotation to make the object ready for storage in a JPA-based data store
     * Currency will be the object type that is stored in the database.
     * It has the fields ticker (Which is the id), name, number_of_coins and market_cap.
     */

    @Id //Marked as primary key
    @Column (name = "Ticker", nullable = false)
    private  String ticker;

    @Column (name = "Name", nullable = false)
    private  String name;

    @Column (name = "Number_of_coins", nullable = false)
    private  long number_of_coins;

    @Column (name = "Market_cap")
    private  long market_cap;

    public Currency2() {}
    /** a constructor without parameters and one with 4 parameters. I know without shouldn't be there, but for some reason I couldn't get the database preloaded without it */

    private Currency2(CurrencyBuilder builder){
        this.ticker = builder.ticker;
        this.name = builder.name;
        this.number_of_coins = builder.number_of_coins;
        this.market_cap = builder.market_cap;
    }
    // the getters and setters
    /** Gets the ticker, which is the id.
     *
     * @return the ticker */
    public String getTicker(){
        return ticker;
    }

    /** Gets the name
     *
     * @return the name */
    public String getName() {
        return name;
    }

    /** Gets the number of  coins
     *
     * @return the number of coins */

    public long getNumber_of_coins() {
        return number_of_coins;
    }

    /** Gets the market cap, which is the number of coins x the last known value of the coin
     *
     * @return the market cap */

    public long getMarket_cap() {
        return market_cap;
    }

    /** A helper class needed for the builder design. You "fill" the values of the currency here, then build it into a currency where the values are final */
    public static class CurrencyBuilder{
        private final String ticker;
        private final String name;
        private long number_of_coins;
        private long market_cap;

        /** Constructor with name and ticker so every currency has at least those values */
    public CurrencyBuilder(String ticker, String name){
        this.ticker = ticker;
        this.name = name;
    }

    /** with .number_of_coins you can add the number of coins to the currency you are building */
    public CurrencyBuilder number_of_coins(long number_of_coins) {
        this.number_of_coins = number_of_coins;
        return this;
    }

        /** with .market_cap you can add the market cap to the currency you are building */
    public CurrencyBuilder market_cap(long market_cap) {
        this.market_cap = market_cap;
        return this;
    }

    /** this actually builds the currency */
    public Currency2 build() {
        return new Currency2(this);
    }
    }
}
