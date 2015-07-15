package com.artistexplorer.ui.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.artistexplorer.communication.errors.WebServiceError;
import com.artistexplorer.communication.events.GetImageWithListenerEvent;
import com.artistexplorer.data.Artist;
import com.artistexplorer.data.ArtistImage;
import com.artistexplorer.ui.widget.CustomNetworkImageView;

import java.util.List;

import de.greenrobot.event.EventBus;
import trocc.com.artistexplorer.R;


/**
 * Created by Marian Lungu on 11/07/15.
 */
public class ArtistsAdapter extends RecyclerView.Adapter<ArtistsAdapter.ArtistViewHolder> {

    private static final int DEFAULT_IMAGE_WIDTH = 300;
    private final String TAG = getClass().getSimpleName();

    List<Artist> mArtists;

    public ArtistsAdapter(List<Artist> artists) {
        this.mArtists = artists;
    }

    @Override
    public ArtistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //create the view and pass it to the view holder
        CardView cardView = (CardView)LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.artist_card_layout, parent, false);
        cardView.setPreventCornerOverlap(false);
        return new ArtistViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(final ArtistViewHolder holder, int position) {
        //bind the holder with the datasource
        Artist artist = mArtists.get(position);
        holder.artistTitle.setText(artist.getName());
        holder.artistImage.setLocalImageBitmap(null);

        //get Image for the current artist
        String imageUrl = null;
        for (ArtistImage image : artist.getImages()) {
            if (image.getWidth() <= DEFAULT_IMAGE_WIDTH) {
                imageUrl = image.getUrl();
                break;
            }
        }
        if (imageUrl == null && artist.getImages().size() > 0) {
            imageUrl = artist.getImages().get(0).getUrl();
        }
        //only get the image if we have an url, otherwise let the default picture
        if (imageUrl != null) {
            holder.requestURL = imageUrl;
            EventBus.getDefault().post(new GetImageWithListenerEvent(imageUrl,
                    new ImageLoader.ImageListener() {
                        @Override
                        public void onResponse(ImageLoader.ImageContainer imageContainer, boolean isImmediate) {
                            if (imageContainer.getBitmap() != null) {
                                if (imageContainer.getRequestUrl().equalsIgnoreCase(holder.requestURL)) {
                                    if (!isImmediate) {
                                        Animation a = new AlphaAnimation(0.00f, 1.00f);

                                        a.setDuration(700);
                                        final ImageLoader.ImageContainer container = imageContainer;
                                        a.setAnimationListener(new Animation.AnimationListener() {
                                            public void onAnimationStart(Animation animation) {
                                                holder.artistImage.setLocalImageBitmap(container.getBitmap());
                                            }

                                            public void onAnimationRepeat(Animation animation) {
                                            }

                                            public void onAnimationEnd(Animation animation) {
                                            }
                                        });

                                        holder.artistImage.startAnimation(a);
                                    } else {
                                        holder.artistImage.setLocalImageBitmap(imageContainer.getBitmap());

                                    }
                                }
                            }
                        }

                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            EventBus.getDefault().post(new WebServiceError(volleyError.getLocalizedMessage()));
                        }
                    }
            ));
        }

    }

    @Override
    public int getItemCount() {
        return this.mArtists.size();
    }

    public static class ArtistViewHolder extends RecyclerView.ViewHolder {

        private CustomNetworkImageView artistImage;
        private TextView artistTitle;
        private TextView artistGenres;
        private String requestURL;

        public ArtistViewHolder(View itemView) {
            super(itemView);
            artistImage = (CustomNetworkImageView) itemView.findViewById(R.id.artist_image);
            artistTitle = (TextView) itemView.findViewById(R.id.artist_name);
        }
    }
}
