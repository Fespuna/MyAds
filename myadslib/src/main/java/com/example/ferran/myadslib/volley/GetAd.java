package com.example.ferran.myadslib.volley;


import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by Ferran on 17/03/2017.
 */
public class GetAd {




    public static void Show(final Activity act) {

        try {
            TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() {
                            X509Certificate[] myTrustedAnchors = new X509Certificate[0];
                            return myTrustedAnchors;
                        }

                        @Override
                        public void checkClientTrusted(X509Certificate[] certs, String authType) {}

                        @Override
                        public void checkServerTrusted(X509Certificate[] certs, String authType) {}
                    }
            };

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
        } catch (Exception e) {
        }



        Log.e("sperads","entra");

        Map<String, String> params = new HashMap<>();
        params.put("package", act.getPackageName());

        new VolleyRequest(act, Request.Method.POST, Urls.GET_AD, params, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("sperads",response);

                if (response.equals("NoAds")) {

                } else{

                    JSONArray jsonArray = null;
                    try {
                        jsonArray = new JSONArray(response);

                        for(int i=0;i<jsonArray.length();i++){

                            try {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                Intent myIntent = new Intent(act, AdDisplay.class);
                                myIntent.putExtra("ad_name", jsonObject.getString("ad_name")); //Optional parameters
                                myIntent.putExtra("ad_package", jsonObject.getString("ad_package")); //Optional parameters
                                myIntent.putExtra("ad_icon", jsonObject.getString("ad_icon")); //Optional parameters
                                myIntent.putExtra("ad_description", jsonObject.getString("ad_description")); //Optional parameters
                                myIntent.putExtra("app_package", jsonObject.getString("app_package")); //Optional parameters

                               act.startActivity(myIntent);

                            } catch (JSONException e) {

                                e.printStackTrace();
                            }
                        }

                    } catch (JSONException e) {

                        Log.e("sperads",e.toString());
                        e.printStackTrace();


                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
              //  e.printStackTrace();

                Log.e("sperads",error.toString());
            }
        });
    }
}
