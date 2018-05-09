package com.example.ferran.myadslib.volley;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Ferran on 17/03/2017.
 */
public class Shared {

    public static SharedPreferences p;

    public static void set(Context c,String tag,String value){

        p = PreferenceManager.getDefaultSharedPreferences(c);
        p.edit().putString(tag, value).commit();
    }

    public static String get(Context c,String tag){

        p = PreferenceManager.getDefaultSharedPreferences(c);
        return p.getString(tag, "");

    }

    public static void clearall(Context c){

        p = PreferenceManager.getDefaultSharedPreferences(c);


        p.edit().clear();
    //    return p.getString(tag, "");

    }

}
