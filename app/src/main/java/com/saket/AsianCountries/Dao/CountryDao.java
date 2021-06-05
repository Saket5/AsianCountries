package com.saket.AsianCountries.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.saket.AsianCountries.model.Country;

import java.util.List;

@Dao
public interface CountryDao {

    @Query("SELECT * FROM Country_Table")
    LiveData<List<Country>> getCountryList();
    @Insert( onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Country> countryList);
    @Query("DELETE FROM Country_Table")
    void deleteTable();
}
