package com.example.ferran.myadslib.volley;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by Ferran on 14/06/2018.
 */

public class ReceiveInstall extends BroadcastReceiver {




    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e("xixi","bbbb");


        String packag = intent.getStringExtra("packa");
        String apppacka = intent.getStringExtra("apppacka");



        Log.e("",packag);

        final PackageManager pm = context.getPackageManager();
         List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo packageInfo : packages) {

            if(packag.equals(packageInfo.packageName)){

                // SUMA ISNTALACIO


               Log.e("xi", "suma inst");

              /*  try {
                    Log.e("xi","xipirona");
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
                    Log.e("xi","xipironb");
                }*/

                Map<String, String> params = new HashMap<>();
                params.put("package",apppacka );

                new VolleyRequest(context, Request.Method.POST, Urls.UPDATEINSTALLS, params, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("xi","a");
                        Log.e("xi",response);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("xi","b");
                        Log.e("xi",error.toString());

                    }
                });


        /*        PackageManager pm  = Re_editActivity.this.getPackageManager();
                ComponentName componentName = new ComponentName(currentActivity.this, name_of_your_receiver.class);
                pm.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                        PackageManager.DONT_KILL_APP);*/




            }

        //    Log.e("xi", "Installed package :" + packageInfo.packageName);

        }



        Log.e("xi","xipiron");





}



}

