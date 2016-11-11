package com.github.anastr.spongeboblistview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class Spongebob extends View {

    private float offset = 0f;

    private Paint bitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG)
            , linePaint = new Paint(Paint.ANTI_ALIAS_FLAG)
            , handPaint = new Paint(Paint.ANTI_ALIAS_FLAG)
            , handBorderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Bitmap body;
    private RectF bodyRect;
    private final float paddingLeft = dpTOpx(10f);
    private float bodyH = 0f;

    private Bitmap leftEye, rightEye;
    private RectF leftEyeRect, rightEyeRect;
    private float eyeW = 0f;
    private float eyeRotate = 0f;

    private Bitmap hand;
    private RectF handRect;
    private float maxHand = 0f;
    private Path handPath = new Path();

    public Spongebob(Context context) {
        super(context);
        init();
    }

    public Spongebob(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Spongebob(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeCap(Paint.Cap.ROUND);
        linePaint.setColor(Color.argb(150, 255, 0, 0));
        linePaint.setStrokeWidth(dpTOpx(5f));

        handPaint.setStyle(Paint.Style.STROKE);
        handPaint.setStrokeCap(Paint.Cap.ROUND);
        handPaint.setColor(Color.parseColor("#fff75f"));
        handPaint.setStrokeWidth(dpTOpx(2f));

        handBorderPaint.setStyle(Paint.Style.STROKE);
        handBorderPaint.setStrokeCap(Paint.Cap.ROUND);
        handBorderPaint.setColor(Color.parseColor("#86811f"));
        handBorderPaint.setStrokeWidth(dpTOpx(4f));

        body = BitmapFactory.decodeResource(getResources(), R.drawable.spongebob_body);
        bodyRect = new RectF();

        leftEye = BitmapFactory.decodeResource(getResources(), R.drawable.spongebob_left_eye);
        rightEye = BitmapFactory.decodeResource(getResources(), R.drawable.spongebob_right_eye);
        leftEyeRect = new RectF();
        rightEyeRect = new RectF();

        hand = BitmapFactory.decodeResource(getResources(), R.drawable.spongebob_hand);
        handRect = new RectF();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        float bodyW = w - paddingLeft;
        bodyH = bodyW * 2.5f;
        bodyRect.set(paddingLeft, h - bodyH, w, h);
        eyeW = bodyW / 10f;
        if (bodyRect.height() > h) {
            bodyH = h;
            eyeW = bodyH/2.5f /10f;
            bodyRect.set(paddingLeft, 0f, h / 2.5f + paddingLeft, h);
        }

        maxHand = (bodyRect.height()/3f);
        setOffset(offset);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPath(handPath, handBorderPaint);
        canvas.drawPath(handPath, handPaint);

        canvas.drawBitmap(body, null, bodyRect, bitmapPaint);

        canvas.save();
        canvas.rotate(eyeRotate, leftEyeRect.centerX(), leftEyeRect.centerY());
        canvas.drawBitmap(leftEye, null, leftEyeRect, bitmapPaint);
        canvas.restore();
        canvas.save();
        canvas.rotate(eyeRotate, rightEyeRect.centerX(), rightEyeRect.centerY());
        canvas.drawBitmap(rightEye, null, rightEyeRect, bitmapPaint);
        canvas.restore();

        canvas.drawBitmap(hand, null, handRect, bitmapPaint);

        canvas.drawLine(handRect.centerX(), bodyRect.top, handRect.centerX(), bodyRect.top + maxHand, linePaint);
    }

    public void setOffset(float o) {
        offset = o;
        float maxEye = (bodyH/11f);
        leftEyeRect.set(bodyRect.left + (bodyRect.width()*.75f), bodyRect.top + (bodyH/6.3f) + (o*maxEye)
                , bodyRect.left + (bodyRect.width()*.75f) + eyeW, bodyRect.top + (bodyH/6.3f) + (o*maxEye) + (eyeW*1.7f));
        rightEyeRect.set(bodyRect.left + (bodyRect.width()*.4f), bodyRect.top + (bodyH/7.6f) + (o*maxEye)
                , bodyRect.left + (bodyRect.width()*.4f) + eyeW, bodyRect.top + (bodyH/7.6f) + (o*maxEye) + (eyeW*1.7f));
        eyeRotate = - o*20f;

        float handW = bodyRect.width()/6f;
        handRect.set(0f, bodyRect.top + (o*maxHand), handW, bodyRect.top + (o*maxHand) + (handW*2));

        handPath.reset();
        handPath.moveTo(bodyRect.left + (bodyRect.width()/11f), bodyRect.centerY());
        handPath.quadTo(handRect.left, (handRect.bottom + bodyRect.centerY())/2f + (o*maxHand/2f)
                , handRect.centerX() - (handRect.width()/8f), handRect.bottom - (handRect.height()/8f));
        invalidate();
    }

    public void setHandWidth(float width) {
        handPaint.setStrokeWidth(width/2f);
        handBorderPaint.setStrokeWidth(width);
        invalidate();
    }

    public void setScrollLineWidth(float width) {
        linePaint.setStrokeWidth(width);
        invalidate();
    }

    public void setScrollLineColor(int color) {
        linePaint.setColor(color);
        invalidate();
    }

    public float dpTOpx(float dp) {
        return dp * getContext().getResources().getDisplayMetrics().density;
    }
}
