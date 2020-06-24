package CryptoCurrency.controller;

import CryptoCurrency.model.Currency;
import CryptoCurrency.model.Currency2;
import CryptoCurrency.model.CurrencyRepository;
import CryptoCurrency.model.CurrencyRepository2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CurrencyService {
    @Autowired
    CurrencyRepository repository;
    @Autowired
    CurrencyRepository2 repository2;

    /** A helperClass to make the results able to return paged and sorted results */
    public List<Currency> getAllCurrencies(Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Currency> pagedResult = repository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }

    /** A helperClass to make the results able to return paged and sorted results */
    public List<Currency2> getAllCurrencies2(Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Currency2> pagedResult = repository2.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }
}
