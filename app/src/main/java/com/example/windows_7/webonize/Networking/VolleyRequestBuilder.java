package com.example.windows_7.webonize.Networking;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by windows-7 on 8/22/2016.
 */
public class VolleyRequestBuilder {

    private static VolleyRequestBuilder mInstance;
    private RequestQueue requestQueue;
    private static Context context;

    private VolleyRequestBuilder(Context context){
        this.context=context;
    }

    public static synchronized VolleyRequestBuilder getVolleyInstance(Context context){
        if(mInstance==null){
            mInstance=new VolleyRequestBuilder(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue(){
        if(requestQueue==null){
            return requestQueue=Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}
