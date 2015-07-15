package com.artistexplorer.communication;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.artistexplorer.communication.errors.WebServiceError;
import com.artistexplorer.communication.events.ArtistsEvent;
import com.artistexplorer.communication.helpers.SpotifyURLHelper;
import com.artistexplorer.communication.requests.GSONRequest;
import com.artistexplorer.data.ArtistsSearchData;

import de.greenrobot.event.EventBus;

/**
 * Created by Marian Lungu on 11/07/15.
 */
public class APIManager {
    private final String TAG = getClass().getSimpleName();

    private RequestManager mRequestManager;
    private Context mContext;

    public static final int DEFAULT_LIMIT = 50;


    public APIManager(Context context) {
        mContext = context;
        //instantiate the request manager
        mRequestManager = RequestManager.getInstance(context);
    }

    //define API based methods

    /**
     * Get's the list of artists and broadcasts them through EventBus to whoever is listening
     *
     * @param offset
     */
    public void getArtists(int offset, String market, String year) {
        //construct the URL
        String artistUrl = SpotifyURLHelper.getPopularArtistsURL(offset, DEFAULT_LIMIT, market, year);
        //create the request and pass it to Volley
        GSONRequest<ArtistsSearchData> request = new GSONRequest<>(artistUrl,
                ArtistsSearchData.class,
                null,
                new Response.Listener<ArtistsSearchData>() {
                    @Override
                    public void onResponse(ArtistsSearchData response) {
                        Log.d(TAG, "SIZE IS "+ response.getArtists().getArtists().size());
                        EventBus.getDefault().post(new ArtistsEvent(response.getArtists().getArtists()));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        EventBus.getDefault().post(new WebServiceError(error.getLocalizedMessage()));
                    }
                });
        mRequestManager.getRequestQueue().add(request);
    }


    /**
     * Retrieve the image for imageURL and set's the ImageLoaders listener to listener
     *
     * @param imageURL
     * @param listener containing the network image view that is going to be populated
     */
    public void getImageForArtist(String imageURL, ImageLoader.ImageListener listener) {
        mRequestManager.getImageLoader().get(imageURL, listener);
    }


    /**
     * Get artist information
     *
     * @param artistURL
     */
    public void getArtistInfo(String artistURL) {

    }

    /**
     * Retrieves all related artists to a specific artist
     *
     * @param artistURL
     */
    public void getRelatedArtists(String artistURL) {

    }

    /**
     * Top Tracks for an artist
     *
     * @param artistURL
     */
    public void getTopTracks(String artistURL) {

    }

}
