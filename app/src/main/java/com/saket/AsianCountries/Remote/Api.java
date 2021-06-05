package com.saket.AsianCountries.Remote;

import com.saket.AsianCountries.model.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("asia")
    Call <List<Country>> getCountryListRetro();
}
