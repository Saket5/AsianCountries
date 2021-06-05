package com.saket.AsianCountries.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName="Country_Table")
public class Country {

    @PrimaryKey(autoGenerate = true)
    private int countryId;

    @SerializedName("name")
    @ColumnInfo(name="name")
    private String name;

    @SerializedName("capital")
    @ColumnInfo(name="capital")
    private String capital;

    @SerializedName("flag")
    @ColumnInfo(name="flag")
    private String flag;

    @SerializedName("population")
    @ColumnInfo(name="population")
    private int population;

    @SerializedName("region")
    @ColumnInfo(name ="region")
    private String region;

    @SerializedName("subregion")
    @ColumnInfo(name ="subregion")
    private String subregion;

    @SerializedName("borders")
    @ColumnInfo(name ="borders")
    private List<String> borders= null;

    @SerializedName("languages")
    @ColumnInfo(name ="languages")
    private List<Language> languages;

    public Country(String name, String capital, String flag, int population, String region, String subregion, List<String> borders, List<Language> languages) {
        this.name = name;
        this.capital = capital;
        this.flag = flag;
        this.population = population;
        this.region = region;
        this.subregion = subregion;
        this.borders = borders;
        this.languages = languages;
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryId=" + countryId +
                ", name='" + name + '\'' +
                ", capital='" + capital + '\'' +
                ", flag='" + flag + '\'' +
                ", population=" + population +
                ", region='" + region + '\'' +
                ", subregion='" + subregion + '\'' +
                ", borders=" + borders +
                ", languages=" + languages +
                '}';
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public List<String> getBorders() {
        return borders;
    }

    public void setBorders(List<String> borders) {
        this.borders = borders;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }
}
