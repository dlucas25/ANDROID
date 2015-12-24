package com.example.drh3cker.ihebski_swip;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by daniel on 24/12/15.
 */
public class SharedPreference {
    public static final String PREFS_NAME = "News_APP";
    public static final String FAVORITES = "News_Favorite";

    public SharedPreference() {
        super();
    }

    // This four methods are used for maintaining favorites.
    public void saveFavorites(Context context, List<News> favorites) {
        SharedPreferences settings;
        Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);

        editor.putString(FAVORITES, jsonFavorites);

        editor.commit();
    }

    public void addFavorite(Context context, News item) {
        List<News> favorites = getFavorites(context);
        if (favorites == null)
            favorites = new ArrayList<News>();
        favorites.add(item);
        saveFavorites(context, favorites);
    }

    public void removeFavorite(Context context, News item) {
        ArrayList<News> favorites = getFavorites(context);
        if (favorites != null) {
            favorites.remove(item);
            saveFavorites(context, favorites);
        }
    }

    public ArrayList<News> getFavorites(Context context) {
        SharedPreferences settings;
        List<News> favorites;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains(FAVORITES)) {
            String jsonFavorites = settings.getString(FAVORITES, null);
            Gson gson = new Gson();
            News[] favoriteItems = gson.fromJson(jsonFavorites,
                    News[].class);

            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<News>(favorites);
        } else
            return null;

        return (ArrayList<News>) favorites;
    }
}
