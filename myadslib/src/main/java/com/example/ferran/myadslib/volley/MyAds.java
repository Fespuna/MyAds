package com.example.ferran.myadslib.volley;

import android.annotation.SuppressLint;
import android.app.Activity;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by Ferran on 07/12/2017.
 */

public class MyAds {


    public static void Load(Activity act){

        GetAd.Load(act);
     //   GetAd.Show(act);
    }

    public static void ShowInterstitial(Activity act){

        GetAd.Show(act);
    }


    public static void LoadAndShowSplash(Activity act){

        GetAd.LoadAndShow(act);
    }



}
