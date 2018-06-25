package com.example.ferran.myadslib.volley;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

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




  /*   public static void Show(final Activity act) {

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

                Log.e("sperads",error.toString());
            }
        });
    }*/




    public static void Load(final Activity act) {

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

                                Shared.set(act,"ad_name",jsonObject.getString("ad_name"));
                                Shared.set(act,"ad_package",jsonObject.getString("ad_package"));
                                Shared.set(act,"ad_icon",jsonObject.getString("ad_icon"));
                                Shared.set(act,"ad_description", jsonObject.getString("ad_description"));
                                Shared.set(act,"app_package",jsonObject.getString("app_package"));



                                Glide
                                        .with( act )
                                        .load(Urls.URL_AD_IMAGE + jsonObject.getString("ad_icon") )
                                        .downloadOnly(512, 512);





                            /*    Glide.with(act).load(Urls.URL_AD_IMAGE + jsonObject.getString("ad_icon")).asBitmap().centerCrop().into(new BitmapImageViewTarget(iv) {
                                    @Override
                                    protected void setResource(Bitmap resource) {
                                        RoundedBitmapDrawable circularBitmapDrawable =
                                                RoundedBitmapDrawableFactory.create(act.getResources(), resource);

                                        //   circularBitmapDrawable.setCircular(true);
                                        iv.setImageDrawable(circularBitmapDrawable);
                                    }
                                });
*/
                                /*

                                Intent myIntent = new Intent(act, AdDisplay.class);
                                myIntent.putExtra("ad_name", jsonObject.getString("ad_name")); //Optional parameters
                                myIntent.putExtra("ad_package", jsonObject.getString("ad_package")); //Optional parameters
                                myIntent.putExtra("ad_icon", jsonObject.getString("ad_icon")); //Optional parameters
                                myIntent.putExtra("ad_description", jsonObject.getString("ad_description")); //Optional parameters
                                myIntent.putExtra("app_package", jsonObject.getString("app_package")); //Optional parameters

                                act.startActivity(myIntent);

                                */

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







         public static void Show(final Activity act) {

             Intent myIntent = new Intent(act, AdDisplay.class);
             act.startActivity(myIntent);

         }




    public static void LoadAndShow(final Activity act) {

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
                                Shared.set(act,"","");



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
