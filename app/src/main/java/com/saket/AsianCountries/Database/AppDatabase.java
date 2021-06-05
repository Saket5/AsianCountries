package com.saket.AsianCountries.Database;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.saket.AsianCountries.Dao.CountryDao;
import com.saket.AsianCountries.model.Country;

import org.jetbrains.annotations.NotNull;

@Database(entities = Country.class,exportSchema = false, version = 1)
@TypeConverters(Converter.class)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CountryDao countryDao();

    private static volatile AppDatabase INSTANCE;
    public static AppDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "CountryDatabase")
                            .allowMainThreadQueries()
                            .addCallback(callback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    static Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull @NotNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateAsyncTask(INSTANCE);
        }
    };
    static  class PopulateAsyncTask extends AsyncTask<Void,Void,Void>{

        private CountryDao countryDao;

        PopulateAsyncTask(AppDatabase appDatabase)
        {
            countryDao =appDatabase.countryDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            countryDao.deleteTable();
            return null;
        }
    }
}
