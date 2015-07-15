package com.artistexplorer.communication.events;

import com.artistexplorer.data.Artist;

import java.util.List;

/**
 * Created by Marian Lungu on 12/07/15.
 */
public class ArtistsEvent {

    private List<Artist> artists;

    public ArtistsEvent(List<Artist> artists){
        this.artists = artists;
    }

    public List<Artist> getArtists() {
        return artists;
    }
}
