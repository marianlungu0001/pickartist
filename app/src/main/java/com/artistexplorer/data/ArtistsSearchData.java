package com.artistexplorer.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Marian Lungu on 12/07/15.
 */
public class ArtistsSearchData {

    @SerializedName("artists")
    private SearchResult artists;


    public SearchResult getArtists() {
        return artists;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArtistsSearchData that = (ArtistsSearchData) o;

        return !(artists != null ? !artists.equals(that.artists) : that.artists != null);

    }

    @Override
    public int hashCode() {
        return artists != null ? artists.hashCode() : 0;
    }
}
