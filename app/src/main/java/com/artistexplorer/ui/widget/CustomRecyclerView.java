package com.artistexplorer.ui.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.artistexplorer.ui.widget.listeners.OnDetectScrollListener;

/**
 * Created by Marian Lungu on 14/07/15.
 */
public class CustomRecyclerView extends RecyclerView {

    private OnDetectScrollListener onDetectScrollListener;

    public CustomRecyclerView(Context context) {
        super(context);
    }

    public CustomRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setOnDetectScrollListener(OnDetectScrollListener onDetectScrollListener) {
        this.onDetectScrollListener = onDetectScrollListener;
    }

    public OnDetectScrollListener getOnDetectScrollListener() {
        return onDetectScrollListener;
    }
}
