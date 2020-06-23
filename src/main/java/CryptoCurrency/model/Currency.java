package CryptoCurrency.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity //JPA annotation to make the object ready for storage in a JPA-based data store
@Table(name = "currencies")
@EntityListeners(AuditingEntityListener.class)
public class Currency {
    /** Entity is a JPA annotation to make the object ready for storage in a JPA-based data store
     * Currency will be the object type that is stored in the database.
     * It has the fields ticker (Which is the id), name, number_of_coins and market_cap.
     */

    @Id //Marked as primary key
    @Column (name = "Ticker", nullable = false)
    private String ticker;

    @Column (name = "Name", nullable = false)
    private String name;

    @Column (name = "Number_of_coins", nullable = false)
    private long number_of_coins;

    @Column (name = "Market_cap")
    private long market_cap;

    // the getters and setters
    /** Gets the ticker, which is the id.
     *
     * @return the ticker */
    public String getTicker(){
        return ticker;
    }

    /** sets the ticker, which is the id.
     *
     * @param ticker the ticker */
    public void setTicker(String ticker) {
        this.ticker = ticker.toUpperCase();
    }

    /** Gets the name
     *
     * @return the name */
    public String getName() {
        return name;
    }

    /** sets the name
     *
     * @param name the name */
    public void setName(String name) {
        this.name = name;
    }

    /** Gets the number of  coins
     *
     * @return the number of coins */

    public long getNumber_of_coins() {
        return number_of_coins;
    }

    /** sets the number of coins
     *
     * @param number_of_coins the number of coins */
    public void setNumber_of_coins(long number_of_coins) {
        this.number_of_coins = number_of_coins;
    }

    /** Gets the market cap, which is the number of coins x the last known value of the coin
     *
     * @return the market cap */

    public long getMarket_cap() {
        return market_cap;
    }

    /** sets the market cap
     *
     * @param market_cap the market cap */
    public void setMarket_cap(long market_cap) {
        this.market_cap = market_cap;
    }

    /** a constructor without parameters and one with 4 parameters */
    Currency(){}

    Currency(String ticker, String name, long number_of_coins, long market_cap){
        this.ticker = ticker;
        this.name = name;
        this.number_of_coins = number_of_coins;
        this.market_cap = market_cap;
    }
}
