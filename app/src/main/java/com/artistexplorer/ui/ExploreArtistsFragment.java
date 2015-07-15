package com.artistexplorer.ui;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.artistexplorer.communication.APIManager;
import com.artistexplorer.communication.events.ArtistsEvent;
import com.artistexplorer.communication.events.GetImageWithListenerEvent;
import com.artistexplorer.communication.events.WidgetScrollEvent;
import com.artistexplorer.data.Artist;
import com.artistexplorer.ui.adapters.ArtistsAdapter;
import com.artistexplorer.ui.widget.CustomRecyclerView;
import com.artistexplorer.ui.widget.listeners.OnDetectScrollListener;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;
import trocc.com.artistexplorer.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ExploreArtistsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ExploreArtistsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExploreArtistsFragment extends Fragment implements OnDetectScrollListener {
    private final String TAG = getClass().getSimpleName();

    private APIManager mApiManager;
    private ArrayList<Artist> mDataSource = new ArrayList<>();
    private ArtistsAdapter mAdapter;

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;

    private int mLastOffset = 0;
    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ExploreArtistsFragment.
     */
    public static ExploreArtistsFragment newInstance() {
        ExploreArtistsFragment fragment = new ExploreArtistsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public ExploreArtistsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        setRetainInstance(true);
        mLastOffset = 0;
        mApiManager = new APIManager(this.getActivity());
        mApiManager.getArtists(mLastOffset, "RO", "2015");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.artist_explore_fragment, container, false);

        mRecyclerView = (CustomRecyclerView) view.findViewById(R.id.artistList);


        if (mRecyclerView != null) {
            mListener.actionBarAutoHide(mRecyclerView);
            ((CustomRecyclerView)mRecyclerView).setOnDetectScrollListener(this);
        }

        mAdapter = new ArtistsAdapter(mDataSource);
        mRecyclerView.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(this.getActivity());

        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        return view;
    }

    public void onEvent(ArtistsEvent event) {
        //this is done on the UI thread because it comes from Volley
        mDataSource.addAll(event.getArtists());
        mAdapter.notifyDataSetChanged();

    }

    public void onEvent(GetImageWithListenerEvent event) {
        mApiManager.getImageForArtist(event.getImageURL(), event.getListener());
    }

    public void onEvent(WidgetScrollEvent event) {

        if (event.getEvent() == WidgetScrollEvent.Event.END) {
            mLastOffset += APIManager.DEFAULT_LIMIT;
            mApiManager.getArtists(mLastOffset, "RO", "2015");
        }
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
            EventBus.getDefault().register(this);

        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        EventBus.getDefault().unregister(this);

        mListener = null;
    }

    @Override
    public void onScrollUp() {
        int first = mLayoutManager.findFirstVisibleItemPosition();
        int last = mLayoutManager.findLastVisibleItemPosition();
        for (int i = 0; i < (last - first); i++) {

            ImageView imageView = (android.widget.ImageView) ((CardView)mRecyclerView.getChildAt(i)).getChildAt(0);

            Matrix imageMatrix = imageView.getImageMatrix();
            imageMatrix.postTranslate(0, -0.5f);
            imageView.setImageMatrix(imageMatrix);
            imageView.invalidate();
        }
    }

    @Override
    public void onScrollDown() {
        int first = mLayoutManager.findFirstVisibleItemPosition();
        int last = mLayoutManager.findLastVisibleItemPosition();
        for (int i = 0; i < (last - first); i++) {
            ImageView imageView = (android.widget.ImageView) ((CardView)mRecyclerView.getChildAt(i)).getChildAt(0);

            Matrix imageMatrix = imageView.getImageMatrix();
            imageMatrix.postTranslate(0, 0.5f);
            imageView.setImageMatrix(imageMatrix);
            imageView.invalidate();
        }
    }

    public interface OnFragmentInteractionListener {
        public void actionBarAutoHide(RecyclerView view);
    }

}
