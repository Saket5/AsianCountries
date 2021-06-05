package com.saket.AsianCountries.Repository;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.saket.AsianCountries.Dao.CountryDao;
import com.saket.AsianCountries.model.Country;

import java.util.List;

public class CountryRepository {

    private CountryDao countryDao;
    private LiveData<List<Country>> getAll;
    public CountryRepository(CountryDao countryDao)
    {
        this.countryDao = countryDao;
        getAll= countryDao.getCountryList();
    }
    public void  insertAll(List<Country> countryList)
    {
         new InsertAsyncTask(countryDao).execute(countryList);
    }

    public void deleteAll()
    {
        countryDao.deleteTable();
    }

    public LiveData<List<Country>> getCountryList()
    {
        return getAll;
    }

    class InsertAsyncTask extends AsyncTask<List<Country>, Void,Void>{

        private CountryDao countryDao;
        InsertAsyncTask(CountryDao countryDao)
        {
            this.countryDao = countryDao;
        }
        @Override
        protected Void doInBackground(List<Country>... Lists) {
            countryDao.insertAll(Lists[0]);
            return null;
        }
    }
}
