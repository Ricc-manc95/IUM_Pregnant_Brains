// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.android.bitmap.BitmapCache;
import com.google.android.calendar.event.image.BitmapCacheHolder;
import com.google.android.calendar.event.image.EventImageCache;
import com.google.android.calendar.event.image.cache.contactphoto.ContactPhotoCacheHolder;
import com.google.android.calendar.time.Time;

// Referenced classes of package com.google.android.calendar.timely:
//            DualTimelineGridFragment, TimelineRecyclerView, AnimatedToolbarTitleHelper, TrommelAnimationHelper, 
//            TimelyDayView, AnimationThresholdEvaluator

final class  extends android.support.v7.widget.er
{

    private final DualTimelineGridFragment this$0;

    public final void onScrollStateChanged$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN7D4IILG_0(int i)
    {
        DualTimelineGridFragment dualtimelinegridfragment = DualTimelineGridFragment.this;
        boolean flag;
        if (i == 2)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        BitmapCacheHolder.getEventImageCache().setBlocking(flag);
        BitmapCacheHolder.getMonthHeaderBitmapCache().setBlocking(flag);
        ContactPhotoCacheHolder.getContactPhotoCache().setBlocking(flag);
        dualtimelinegridfragment.list.setTimelineScrollState(i);
    }

    public final void onScrolled$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TRMIP37CLQ2UKJ5CDSM6R35E9B6IPBN7D4KIAAM0(RecyclerView recyclerview, int i)
    {
        AnimatedToolbarTitleHelper animatedtoolbartitlehelper;
        int i1;
        boolean flag2;
        int l = -1;
        boolean flag = false;
        flag2 = true;
        boolean flag1 = true;
        DualTimelineGridFragment dualtimelinegridfragment = DualTimelineGridFragment.this;
        recyclerview = dualtimelinegridfragment.list;
        int k;
        if (recyclerview.getChildCount() > 0)
        {
            k = ((TimelineRecyclerView) (recyclerview)).layoutManager.findFirstVisibleItemPosition();
        } else
        {
            k = -1;
        }
        i1 = 0x253d8c + k;
        if (dualtimelinegridfragment.julianDayOnTop != i1)
        {
            TimelineRecyclerView timelinerecyclerview;
            int j;
            if (dualtimelinegridfragment.julianDayOnTop == -1)
            {
                j = 0;
            } else
            if (i1 > dualtimelinegridfragment.julianDayOnTop)
            {
                j = 1;
            } else
            {
                j = -1;
            }
            dualtimelinegridfragment.list.setScrollingDirection(j, k);
            if (!dualtimelinegridfragment.isActionbarTitleAnimated())
            {
                dualtimelinegridfragment.onNewDayOnTop(i1);
            } else
            {
                dualtimelinegridfragment.julianDayOnTop = i1;
            }
        }
        if (!dualtimelinegridfragment.isActionbarTitleAnimated()) goto _L2; else goto _L1
_L1:
        animatedtoolbartitlehelper = dualtimelinegridfragment.animatedToolbarTitleHelper;
        timelinerecyclerview = dualtimelinegridfragment.list;
        if (animatedtoolbartitlehelper.animationHelper.animating) goto _L2; else goto _L3
_L3:
        animatedtoolbartitlehelper.time.setJulianDaySafe(i1);
        j = l;
        if (timelinerecyclerview.getChildCount() > 0)
        {
            j = timelinerecyclerview.layoutManager.findFirstVisibleItemPosition();
        }
        recyclerview = timelinerecyclerview.findViewHolderForAdapterPosition(j);
        if (!(((android.support.v7.widget.ForAdapterPosition) (recyclerview)).mView instanceof TimelyDayView))
        {
            recyclerview = null;
        } else
        {
            recyclerview = (TimelyDayView)((android.support.v7.widget.mView) (recyclerview)).mView;
        }
        if (recyclerview == null) goto _L2; else goto _L4
_L4:
        if (animatedtoolbartitlehelper.dayView == null || animatedtoolbartitlehelper.time.monthDay == 1)
        {
            animatedtoolbartitlehelper.dayView = recyclerview;
        }
        recyclerview = ((RecyclerView) (timelinerecyclerview)).mLayout;
        recyclerview = animatedtoolbartitlehelper.dayView;
        j = recyclerview.getTop();
        k = ((android.support.v7.widget.leHelper.dayView)recyclerview.getLayoutParams()).DecorInsets.top;
        l = animatedtoolbartitlehelper.dayView.getMonthLabelBottom();
        recyclerview = new r.State(animatedtoolbartitlehelper.time, i1, i, j - k, l);
        if (animatedtoolbartitlehelper.thresholdEvaluator.canAnimate(recyclerview)) goto _L6; else goto _L5
_L5:
        if (((r.State) (recyclerview)).time.monthDay == 1)
        {
            i = ((flag1) ? 1 : 0);
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            animatedtoolbartitlehelper.listener.onDayChanged(i1);
        }
_L2:
        if (dualtimelinegridfragment.list != null)
        {
            for (i = ((flag) ? 1 : 0); i < dualtimelinegridfragment.list.getChildCount(); i++)
            {
                recyclerview = dualtimelinegridfragment.list.getChildAt(i);
                if (recyclerview instanceof mpleOnScrollListener)
                {
                    ((mpleOnScrollListener)recyclerview).onScrolled(i);
                }
            }

        }
        break; /* Loop/switch isn't completed */
_L6:
        if (i1 != animatedtoolbartitlehelper.firstDayOfMonthJulianDay)
        {
            animatedtoolbartitlehelper.firstDayOfMonthJulianDay = i1;
        }
        if (animatedtoolbartitlehelper.thresholdEvaluator.isAnimationThresholdMet(recyclerview))
        {
            if (((r.State) (recyclerview)).scrollDeltaY >= 0.0F)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                flag2 = false;
            }
            animatedtoolbartitlehelper.animationHelper.startAnimation(flag2);
        }
        if (true) goto _L2; else goto _L7
_L7:
    }

    mpleOnScrollListener()
    {
        this$0 = DualTimelineGridFragment.this;
        super();
    }
}
