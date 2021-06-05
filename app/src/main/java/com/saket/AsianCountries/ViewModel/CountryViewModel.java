package com.saket.AsianCountries.ViewModel;

import androidx.lifecycle.LiveData;

import com.saket.AsianCountries.Repository.CountryRepository;
import com.saket.AsianCountries.model.Country;

import java.util.List;

public class CountryViewModel extends androidx.lifecycle.ViewModel {
    private CountryRepository countryRepository;
    private LiveData<List<Country>> getAll;

    public CountryViewModel(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
        getAll= countryRepository.getCountryList();
    }

    public void insertAll(List<Country> countryList)
    {
        countryRepository.insertAll(countryList);
    }
    public LiveData<List<Country>> getCountryList()
    {
        return getAll;
    }
    public void deleteALl(){
        countryRepository.deleteAll();}
}
