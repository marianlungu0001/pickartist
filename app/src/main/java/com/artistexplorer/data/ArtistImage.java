package com.artistexplorer.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Marian Lungu on 12/07/15.
 */
public class ArtistImage {

    @SerializedName("width")
    private int width;

    @SerializedName("height")
    private int height;

    @SerializedName("url")
    private String url;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArtistImage that = (ArtistImage) o;

        if (width != that.width) return false;
        if (height != that.height) return false;
        return !(url != null ? !url.equals(that.url) : that.url != null);

    }

    @Override
    public int hashCode() {
        int result = width;
        result = 31 * result + height;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ArtistImage{" +
                "width=" + width +
                ", height=" + height +
                ", url='" + url + '\'' +
                '}';
    }
}
