package com.example.ferran.myadslib.volley;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

public class VolleyRequest extends StringRequest {

    private Map<String, String> mParams;

    public VolleyRequest(Context context, int method, String url, Map<String, String> params,
                         Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
        this.mParams = params;

        /*setRetryPolicy(new DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));**/
        setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {
                if (error.getMessage() != null) {
                    Log.e("Error in volley request", error.getMessage());
                } else {
                    Log.e("Error in volley request", "Error in volley request");
                    error.printStackTrace();
                }
            }
        });
        VolleyInstance.getInstance(context).addToRequestQueue(this);

    }

    @Override
    protected Map<String, String> getParams() {
        return mParams;
    }
}
