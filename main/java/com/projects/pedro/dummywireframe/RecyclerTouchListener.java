package com.projects.pedro.dummywireframe;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Class created by : https://www.androidhive.info/2016/01/android-working-with-recycler-view/
 * RecyclerView doesn't have OnItemClickListeners to identify item clicked so
 * this will make our recycler view content clickable and able to trigger actions out of the click.
 */
public class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

    private GestureDetector gestureDetector;
    private ClickListener clickListener;

    /**
     * Touch listener that will extract information of where we touched in the screen and if it coincides with
     * a component.
     * @param context The contect in which this will run.
     * @param recyclerView we are working with.
     * @param clickListener an object of the interface created.
     */
    RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener){
        this.clickListener = clickListener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if(child != null && clickListener != null){
                    clickListener.onLongClick(child, recyclerView.getChildLayoutPosition(child));
                }
            }
        });

    }

    /**
     * Intercepts the touch and if it was on a component then add a click on it.
     * @param rv RecyclerView object.
     * @param e MotionEvent object.
     * @return false;
     */
    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View child = rv.findChildViewUnder(e.getX(), e.getY());
        if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
            clickListener.onClick(child, rv.getChildLayoutPosition(child));
        }
        return false;
    }


    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    /**
     * Interface that will allow to define what to do upon a long click or just a click.
     */
    public interface ClickListener{
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }
}
