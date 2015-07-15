package com.artistexplorer.communication.events;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by Marian Lungu on 12/07/15.
 */
public class GetImageWithListenerEvent {

    String imageURL;
    ImageLoader.ImageListener listener;

    public GetImageWithListenerEvent(String imageURL, ImageLoader.ImageListener listener){
        this.imageURL = imageURL;
        this.listener = listener;
    }

    public String getImageURL() {
        return imageURL;
    }

    public ImageLoader.ImageListener getListener() {
        return listener;
    }
}
