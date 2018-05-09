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

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ferran on 17/03/2017.
 */
public class GetAd {

    public static void Show(final Activity act) {

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

                                Log.e("sperads",jsonObject.getString("ad_package"));
                                Log.e("sperads",jsonObject.getString("ad_image"));
                                Log.e("sperads",jsonObject.getString("ad_name"));
                                Log.e("sperads",jsonObject.getString("ad_description"));


                                Intent myIntent = new Intent(act, AdDisplay.class);
                                myIntent.putExtra("adpackage", jsonObject.getString("ad_package")); //Optional parameters
                                myIntent.putExtra("adimage", jsonObject.getString("ad_image")); //Optional parameters
                                myIntent.putExtra("adname", jsonObject.getString("ad_name")); //Optional parameters
                                myIntent.putExtra("addescription", jsonObject.getString("ad_description")); //Optional parameters
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
