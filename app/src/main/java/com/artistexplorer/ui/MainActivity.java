package com.artistexplorer.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.artistexplorer.ui.widget.CustomRecyclerView;

import trocc.com.artistexplorer.R;


public class MainActivity extends BaseActivity implements ExploreArtistsFragment.OnFragmentInteractionListener {
    private static final String TAG_TASK_FRAGMENT = "task_fragment";
    private Fragment mExploreArtistsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        FragmentManager fm = getFragmentManager();

        mExploreArtistsFragment = (ExploreArtistsFragment) fm.findFragmentByTag(TAG_TASK_FRAGMENT);

        // If the Fragment is non-null, then it is currently being
        // retained across a configuration change.
        if (mExploreArtistsFragment == null) {
            mExploreArtistsFragment = new ExploreArtistsFragment();
            fm.beginTransaction().add(R.id.fragment_container, mExploreArtistsFragment, TAG_TASK_FRAGMENT).commit();
        }

        Toolbar toolbar = getActionBarToolbar();
        toolbar.setTitle("Artists");
        registerHideableHeaderView(findViewById(R.id.headerbar));

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_artist_explore, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void actionBarAutoHide(RecyclerView view) {
        enableActionBarAutoHide(view);
    }
}
