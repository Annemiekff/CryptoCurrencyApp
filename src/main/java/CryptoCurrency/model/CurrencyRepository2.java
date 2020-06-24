package CryptoCurrency.model;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface CurrencyRepository2 extends PagingAndSortingRepository<Currency2, String> {
    /** This interface extends Spring Data PagingAndSortingRepository, specifying the domain type as Currency2 and the id type as String.
     * This interface, though empty on the surface, supports:
     *
     * Creating new instances
     * Updating existing ones
     * Deleting
     * Finding (one, all, by simple or complex properties)
     *
     * Spring Data’s repository solution makes it possible to sidestep data store specifics and
     * instead solve a majority of problems using domain-specific terminology.
     */
}
