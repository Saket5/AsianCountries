package com.saket.AsianCountries;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.saket.AsianCountries.Dao.CountryDao;
import com.saket.AsianCountries.Database.AppDatabase;
import com.saket.AsianCountries.Remote.Api;
import com.saket.AsianCountries.Repository.CountryRepository;
import com.saket.AsianCountries.model.Country;
import com.saket.AsianCountries.ViewModel.CountryViewModel;
import com.saket.AsianCountries.ViewModel.ViewModelFactory;
import com.saket.AsianCountries.adapters.CountryAdapter;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CountryAdapter countryAdapter;
    private CountryViewModel countryViewModel;
    private List<Country> countryList;
    private TextView noData;
    private MaterialToolbar toolbar;
    private ProgressBar progressBar;
    private int state =0;
    Activity activity;
    private String url = "https://restcountries.eu/rest/v2/region/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO) ;
        CountryDao dao= AppDatabase.getInstance(getApplicationContext()).countryDao();
        CountryRepository countryRepository = new CountryRepository(dao);
        ViewModelFactory factory = new ViewModelFactory(countryRepository);
        countryViewModel = new ViewModelProvider(this,factory).get(CountryViewModel.class);
        toolbar=findViewById(R.id.toolbar);
        progressBar=findViewById(R.id.progressBar);
        recyclerView=findViewById(R.id.recyclerView);
        noData=findViewById(R.id.txt_noData);
        activity=this;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        toolbar.inflateMenu(R.menu.appbar_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId()==R.id.menuRefresh)
                {
                    progressBar.setVisibility(View.VISIBLE);
                   try {
                        if(isConnected()) {
                            countryViewModel.deleteALl();
                            progressBar.setVisibility(View.VISIBLE);
                            noData.setVisibility(View.GONE);
                            getRemote();
                        }
                        else
                       {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(activity,"No Internet Available",Toast.LENGTH_SHORT).show();
                       }
                   } catch (InterruptedException | IOException e) {
                        e.printStackTrace();
                    }
                }
                else if(item.getItemId()==R.id.menuDelete)
                {
                    countryViewModel.deleteALl();
                    noData.setVisibility(View.VISIBLE);
                    state=1;
                }
                return false;
            }
        });

        if(state == 0)
        {
            state=1;
            getRemote();
        }

        countryViewModel.getCountryList().observe(this, new Observer<List<Country>>() {
            @Override
            public void onChanged(List<Country> countries) {
                if(countries.size()!=0)
                progressBar.setVisibility(View.GONE);
                else {
                    if(noData.getVisibility()!=View.VISIBLE)
                    progressBar.setVisibility(View.VISIBLE);
                }                recyclerView.setAdapter(new CountryAdapter(activity, countries));

            }
        });
    }


    private void getRemote() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Call<List<Country>> call = api.getCountryListRetro();
        call.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                countryViewModel.insertAll(response.body());
                Log.e("APIRespond",""+response.body());
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {

            }
        });
    }

    public boolean isConnected() throws InterruptedException, IOException {
        String command = "ping -c 1 google.com";
        return Runtime.getRuntime().exec(command).waitFor() == 0;
    }

}