// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

// Referenced classes of package com.google.android.calendar.timely:
//            WeekRecyclerAdapter

public class WeekRecyclerView extends RecyclerView
{
    final class FlingListener extends android.view.GestureDetector.SimpleOnGestureListener
    {

        public int actionDownOffset;
        public int currentOffset;
        public boolean receivedOnTouchEvent;
        private final WeekRecyclerView this$0;

        public final boolean onFling(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
        {
            if (!receivedOnTouchEvent || Math.abs(f) < 2000F)
            {
                return false;
            }
            int i = getWidth();
            int j;
            if (f >= 0.0F)
            {
                i = -i;
            }
            j = actionDownOffset;
            smoothScrollBy((i + j) - currentOffset, 0);
            return true;
        }

        FlingListener()
        {
            this$0 = WeekRecyclerView.this;
            super();
        }
    }


    private final GestureDetectorCompat detector;
    private final FlingListener gestureListener = new FlingListener();
    public int offsetDay;
    public int positionToScroll;

    public WeekRecyclerView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        positionToScroll = -1;
        setScrollingTouchSlop(1);
        setItemViewCacheSize(0);
        setFocusable(true);
        detector = new GestureDetectorCompat(context, gestureListener);
    }

    public boolean dispatchTouchEvent(MotionEvent motionevent)
    {
        boolean flag;
        if (motionevent.getAction() == 0)
        {
            FlingListener flinglistener = gestureListener;
            flinglistener.actionDownOffset = computeHorizontalScrollOffset();
            flinglistener.receivedOnTouchEvent = false;
        } else
        {
            gestureListener.currentOffset = computeHorizontalScrollOffset();
        }
        flag = super.dispatchTouchEvent(motionevent);
        if (flag)
        {
            detector.mImpl.onTouchEvent(motionevent);
        }
        return flag;
    }

    protected void onMeasure(int i, int j)
    {
        super.onMeasure(i, j);
        i = android.view.View.MeasureSpec.getSize(i);
        WeekRecyclerAdapter weekrecycleradapter = (WeekRecyclerAdapter)super.mAdapter;
        weekrecycleradapter.pageWidth = (int)((float)i / 0.4285714F);
        if (positionToScroll != -1)
        {
            LinearLayoutManager linearlayoutmanager = (LinearLayoutManager)super.mLayout;
            i = positionToScroll;
            j = offsetDay;
            linearlayoutmanager.scrollToPositionWithOffset(i, (weekrecycleradapter.pageWidth / 7) * j);
            positionToScroll = -1;
        }
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        gestureListener.receivedOnTouchEvent = true;
        return super.onTouchEvent(motionevent);
    }
}
