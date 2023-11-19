package com.android.starchat.ui.uiMain.mainActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.android.starchat.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BackgroundCanvas extends View {

    private Path path;
    private Paint linePaint;
    private Paint starPaint;
    private int screenWidth;
    private int screenHeight;
    private int startY = 10;
    private int lineCount;
    private int lineSpacing;
    private int lineSpacingSmall;
    private List<int[]> stars;
    private boolean drawn;
    private int lineSpacingX;
    private int lineCountX;

    public BackgroundCanvas(Context context) {
        super(context);
        init(null);
    }

    public BackgroundCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);

    }

    public BackgroundCanvas(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);

    }


    private void init(AttributeSet attrs) {
        path = new Path();
        starPaint = new Paint();
        starPaint.setColor(Color.GRAY);
        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeJoin(Paint.Join.ROUND);
        linePaint.setStrokeWidth(1f);

        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;
        lineSpacing = 40;
        lineSpacingX = 40;
        lineCount = screenHeight/lineSpacing;
        lineCountX = screenWidth/lineSpacingX;
        linePaint.setColor(getResources().getColor(R.color.opaque_green));


        Random random = new Random();
        stars = new ArrayList<>();
        for(int i=0; i<200; i++){
            int x = random.nextInt(screenWidth);
            int y = random.nextInt(screenHeight);
            int w = random.nextInt(3);
            stars.add(new int[]{x,y,w});
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        path.reset();
        canvas.drawColor(Color.BLACK);
        for (int i = 0; i < lineCount; i++) {
            path.moveTo(0, startY + i * lineSpacing);
            path.lineTo(screenWidth, startY + i * lineSpacing);
        }
        for (int i=0; i<lineCountX; i++){
            path.moveTo(i*lineSpacingX,0);
            path.lineTo(i*lineSpacingX, screenHeight);
        }
        canvas.drawPath(path, linePaint);
        for(int i=0; i<stars.size(); i++){
            int[] star = stars.get(i);
            canvas.drawCircle(star[0],star[1],star[2],starPaint);
        }

    }


}
