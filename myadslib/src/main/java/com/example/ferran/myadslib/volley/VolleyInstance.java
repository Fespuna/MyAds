package com.example.ferran.myadslib.volley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleyInstance {

    private static VolleyInstance volleyInstance;
    private static Context mContext;

    private RequestQueue mRequestQueue;

    public VolleyInstance(Context context) {
        mContext = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized VolleyInstance getInstance(Context context) {
        if (volleyInstance == null) {
            volleyInstance = new VolleyInstance(context);
        }

        return volleyInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext);
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setShouldCache(false);
        getRequestQueue().add(req);
    }
}