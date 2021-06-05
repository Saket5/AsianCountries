package com.saket.AsianCountries.ViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.saket.AsianCountries.Repository.CountryRepository;

import org.jetbrains.annotations.NotNull;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    CountryRepository countryRepository;

    public ViewModelFactory(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @NonNull
    @Override
    public <T extends androidx.lifecycle.ViewModel> T create(@NonNull @NotNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(CountryViewModel.class)) {
            return (T) new CountryViewModel(countryRepository);
        }
        return null;

    }
}
