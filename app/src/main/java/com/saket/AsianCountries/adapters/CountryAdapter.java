package com.saket.AsianCountries.adapters;

import android.app.Activity;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.saket.AsianCountries.model.Country;
import com.saket.AsianCountries.R;
import com.saket.AsianCountries.model.Language;

import org.jetbrains.annotations.NotNull;

import java.net.URI;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter< CountryAdapter.CountryViewHolder> {

    private List<Country> countryList;
    private Activity activity;


    public CountryAdapter(Activity activity, List<Country> country) {
        this.activity=activity;
        this.countryList = country;
    }

    @NonNull
    @NotNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view= inflater.inflate(R.layout.country_item, parent, false);
        return new CountryAdapter.CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CountryViewHolder holder, int position) {

            Country country = countryList.get(position);
            holder.name.setText("Name: "+ country.getName());
            holder.region.setText("Region: "+ country.getRegion());
            holder.capital.setText("Capital: "+ country.getCapital());
            holder.subregion.setText("SubRegion: "+ country.getSubregion());
            holder.population.setText("Population: "+country.getPopulation());
            String borderText="";
            if(country.getBorders().size()==0 ){
                borderText=borderText+" No Borders";
                holder.border.setText(borderText);
            }
            else
            {
                for (String item :country.getBorders())
                {
                    borderText=borderText+","+item;
                }
                borderText=borderText.substring(1);
                holder.border.setText("Borders: "+borderText);
            }
            String langText="";
            for(Language lang : country.getLanguages())
            {
                langText=langText+","+lang.getName();
            }
            langText=langText.substring(1);
            holder.languages.setText("Languages: "+langText);

            //Glide.with(activity).load(country.getFlag()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.flag);
           // Glide.with(activity).load("https://restcountries.eu/data/afg.svg")
                   // .diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.flag);
        GlideToVectorYou.justLoadImage(activity, Uri.parse(country.getFlag()),holder.flag);

    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public class CountryViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView capital;
        TextView region;
        TextView subregion;
        TextView border;
        TextView population;
        ImageView flag;
        TextView languages;
        public CountryViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            this.name=itemView.findViewById(R.id.txt_Name);
            this.capital=itemView.findViewById(R.id.txt_Capital);
            this.region=itemView.findViewById(R.id.txt_Region);
            this.subregion=itemView.findViewById(R.id.txt_SubRegion);
            this.border=itemView.findViewById(R.id.txt_Borders);
            this.population=itemView.findViewById(R.id.txt_Population);
            this.flag=itemView.findViewById(R.id.flagImage);
            this.languages=itemView.findViewById(R.id.txt_Lang);
        }
    }
}
