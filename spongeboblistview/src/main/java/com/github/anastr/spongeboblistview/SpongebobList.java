package com.github.anastr.spongeboblistview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class SpongebobList extends LinearLayout {

    private Spongebob spongebob;
    private RecyclerView recyclerView;

    public SpongebobList(Context context) {
        super(context);
        init(context);
        initLayout(context, dpTOpx(70f));
    }

    public SpongebobList(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        initAttributeSet(context, attrs);
    }

    public SpongebobList(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        initAttributeSet(context, attrs);
    }

    private void init(Context context) {
    }

    private void initAttributeSet(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SpongebobList, 0, 0);

        initLayout(context, a.getDimension(R.styleable.SpongebobList_spongebob_width, dpTOpx(70f)));
        spongebob.setHandWidth(a.getDimension(R.styleable.SpongebobList_hand_width, dpTOpx(4f)));
        spongebob.setScrollLineWidth(a.getDimension(R.styleable.SpongebobList_scroll_line_width, dpTOpx(5f)));
        spongebob.setScrollLineColor(a.getColor(R.styleable.SpongebobList_scroll_line_color, Color.argb(150, 255, 0, 0)));
    }

    private void initLayout(Context context, float spongebob_width) {
        spongebob = new Spongebob(context);
        LayoutParams spongeParams
                = new LinearLayout.LayoutParams((int)dpTOpx(spongebob_width), ViewGroup.LayoutParams.MATCH_PARENT);
        recyclerView = new RecyclerView(context);
        LayoutParams listParams
                = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        listParams.weight = 1f;

        addView(recyclerView, listParams);
        addView(spongebob, spongeParams);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                spongebob.setOffset(getOffset());
            }
        });
    }

    private float getOffset() {
        return (float) recyclerView.computeVerticalScrollOffset()
                / (float)(recyclerView.computeVerticalScrollRange() - recyclerView.computeVerticalScrollExtent());
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public float dpTOpx(float dp) {
        return dp * getContext().getResources().getDisplayMetrics().density;
    }
}
