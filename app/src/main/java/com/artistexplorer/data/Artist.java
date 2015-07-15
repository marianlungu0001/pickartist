package com.artistexplorer.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by Marian Lungu on 11/07/15.
 */
public class Artist {

    @SerializedName("name")
    private String name;

    @SerializedName("genres")
    private List<String> genres = new ArrayList<>();

    @SerializedName("href")
    private String spotify_url;

    @SerializedName("id")
    private String artist_id;

    public List<ArtistImage> getImages() {
        return images;
    }

    @SerializedName("images")
    private List<ArtistImage> images = new ArrayList<>();

    public Artist() {
    }

    public String getName() {
        return name;
    }

    public Collection<String> getGenres() {
        return genres;
    }

    public String getSpotify_url() {
        return spotify_url;
    }

    public String getArtist_id() {
        return artist_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Artist artist = (Artist) o;

        if (name != null ? !name.equals(artist.name) : artist.name != null) return false;
        if (genres != null ? !genres.equals(artist.genres) : artist.genres != null) return false;
        if (spotify_url != null ? !spotify_url.equals(artist.spotify_url) : artist.spotify_url != null)
            return false;
        if (artist_id != null ? !artist_id.equals(artist.artist_id) : artist.artist_id != null)
            return false;
        return !(images != null ? !images.equals(artist.images) : artist.images != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (genres != null ? genres.hashCode() : 0);
        result = 31 * result + (spotify_url != null ? spotify_url.hashCode() : 0);
        result = 31 * result + (artist_id != null ? artist_id.hashCode() : 0);
        result = 31 * result + (images != null ? images.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "name='" + name + '\'' +
                ", genres=" + genres +
                ", spotify_url='" + spotify_url + '\'' +
                ", artist_id='" + artist_id + '\'' +
                ", images=" + images +
                '}';
    }
}
