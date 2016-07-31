package com.app.behin.fandogh;

import android.support.v7.widget.RecyclerView;

/**
 * Created by FarzanehTaheri on 7/25/2016.
 */
public abstract class MyRecyclerScroll extends RecyclerView.OnScrollListener {
    int scrollDist = 0;
    boolean isVisible = true;
    public static final float MINIMUM = 25;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (isVisible && scrollDist > MINIMUM) {
            hide();
            scrollDist = 0;
            isVisible = false;
        }
        else if (!isVisible && scrollDist < -MINIMUM) {
            show();
            scrollDist = 0;
            isVisible = true;
        }
        if ((isVisible && dy > 0) || (!isVisible && dy < 0)) {
            scrollDist += dy;
        }
    }

    public abstract void show();
    public abstract void hide();
}