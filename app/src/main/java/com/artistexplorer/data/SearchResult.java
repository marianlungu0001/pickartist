package com.artistexplorer.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marian Lungu on 12/07/15.
 */
public class SearchResult {

    @SerializedName("href")
    private String listUrl;

    @SerializedName("next")
    private String nextUrl;

    @SerializedName("previous")
    private String previousUrl;

    @SerializedName("total")
    private int totalResults;

    @SerializedName("limit")
    private int limit;

    public List<Artist> getArtists() {
        return artists;
    }

    @SerializedName("items")
    private List<Artist> artists = new ArrayList<>();

    public String getListUrl() {
        return listUrl;
    }

    public String getNextUrl() {
        return nextUrl;
    }

    public String getPreviousUrl() {
        return previousUrl;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public int getLimit() {
        return limit;
    }

}
