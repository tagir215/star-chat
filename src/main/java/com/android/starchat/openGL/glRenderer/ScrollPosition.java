package com.android.starchat.openGL.glRenderer;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.android.starchat.R;
import com.android.starchat.ui.uiChat.ChatActivity;
import com.android.starchat.util.Constants;

public class ScrollPosition {
    float distance = -3;
    float targetDistance = 0;
    float end;
    float y;
    float start=0;
    private ConstraintLayout constraintLayout;
    private ConstraintSet constraintSet;
    private View timeline;
    private View timelineFull;
    private GLRenderer renderer;
    private Activity activity;
    private TextView textView;

    public ScrollPosition(Activity activity,GLRenderer renderer,ConstraintLayout constraintLayout, View timeline, View timelineFull) {
        this.activity = activity;
        this.renderer = renderer;
        this.constraintLayout = constraintLayout;
        this.timeline = timeline;
        this.timelineFull = timelineFull;
        constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        textView = activity.findViewById(R.id.chatPrecentage);
    }

    void updateDistance(){
        if(targetDistance<distance){
            distance-=0.01f;
            setTimeLineWidth();
        }
    }

    public void scrollDistance(float dy){
        distance += dy* Constants.SCALE;
        targetDistance = distance;
        if(distance<end)
            distance=end;
        if(distance>start)
            distance=start;
    }


    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public float getTargetDistance() {
        return targetDistance;
    }

    public void setTargetDistance(float targetDistance) {
        this.targetDistance = targetDistance;
    }

    public float getPercentage(){
        float precentage = distance / (start+end);
        textView.setText(Math.round(precentage*100f) + "%");
        return distance / (start+end);
    }

    public void setTimeLineWidth(){
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                float percentage = 1-renderer.getScrollPosition().getPercentage();
                int margin =(int)(percentage*(float)timeline.getWidth());
                constraintSet.setMargin(timelineFull.getId(), ConstraintSet.END,margin);
                constraintSet.applyTo(constraintLayout);
            }
        });

    }

}
