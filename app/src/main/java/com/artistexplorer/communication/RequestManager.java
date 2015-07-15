package com.artistexplorer.communication;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by Marian Lungu on 11/07/15.
 */
public class RequestManager {
    private final String TAG = getClass().getSimpleName();

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private Context mContext;
    private static RequestManager sInstance;

    private RequestManager(Context context){
        mContext = context;
        mRequestQueue = getRequestQueue();

        mImageLoader = new ImageLoader(mRequestQueue,
                new ImageLoader.ImageCache(){

                    private final LruCache<String, Bitmap> cache = new LruCache<>(50);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });

    }

    public static synchronized RequestManager getInstance(Context context){
        if (sInstance == null){
            sInstance = new RequestManager(context);
        }
        return sInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null){
            return Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;

    }

}
