package com.artistexplorer.communication.helpers;

import android.net.Uri;

/**
 * Created by Marian Lungu on 12/07/15.
 */
public class SpotifyURLHelper {

    private final String TAG = getClass().getSimpleName();

    private static final String BASE_URL = "https://api.spotify.com/v1/";
    private static final String SEARCH_URL = "search";
    private static final String OFFSET = "offset";
    private static final String LIMIT = "limit";
    private static final String MARKET = "market";
    private static final String QUERY = "q";
    private static final String YEAR = "year:";
    private static final String TYPE = "type";

    private static final int DEFAULT_OFFSET = 0;


    public static String getPopularArtistsURL(int offset, int limit, String market, String year) {
        Uri.Builder uriBuilder = Uri.parse(BASE_URL).buildUpon()
                .appendPath(SEARCH_URL)
                .appendQueryParameter(LIMIT, ""+limit)
                .appendQueryParameter(OFFSET, ""+offset)
                .appendQueryParameter(MARKET, market)
                .appendQueryParameter(QUERY, YEAR+year)
                .appendQueryParameter(TYPE, "artist");

        return uriBuilder.build().toString();
    }

    public static String getTopArtists(int offset, int limit, String artistID) {
        return null;
    }

    public static String getRelatedArtists(String artistID) {
        return null;
    }
}
