// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.tracking;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.calendar.Utils;
import com.google.android.calendar.groove.GrooveUtils;
import com.google.android.calendar.newapi.model.ColorHolder;
import com.google.android.calendar.newapi.model.GrooveTrackingDataHolder;
import com.google.android.calendar.newapi.screen.GrooveViewScreenListener;
import com.google.android.calendar.newapi.segment.common.ViewSegment;
import com.google.android.calendar.utils.AccessibilityUtils;
import java.util.ArrayList;

// Referenced classes of package com.google.android.calendar.newapi.segment.tracking:
//            GrooveTrackingData, TrackingIntervalData, TrackingView

public final class TrackingViewSegment extends FrameLayout
    implements ViewSegment
{

    private final LinearLayout container = (LinearLayout)findViewById(0x7f1002a5);
    private boolean firstUpdate;
    private final boolean inAccessibilityMode;
    public final GrooveViewScreenListener listener;
    private final GrooveTrackingDataHolder model;

    public TrackingViewSegment(Context context, GrooveTrackingDataHolder groovetrackingdataholder, GrooveViewScreenListener grooveviewscreenlistener)
    {
        super(context);
        firstUpdate = true;
        inflate(context, 0x7f0500f2, this);
        model = groovetrackingdataholder;
        listener = grooveviewscreenlistener;
        inAccessibilityMode = AccessibilityUtils.isAccessibilityEnabled(context);
        setImportantForAccessibility(2);
    }

    protected final void onMeasure(int i, int j)
    {
        boolean flag;
        if (getMeasuredWidth() != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        super.onMeasure(i, j);
        if (flag)
        {
            return;
        } else
        {
            i = android.view.View.MeasureSpec.getSize(i);
            j = getResources().getDimensionPixelOffset(0x7f0e0354);
            int k = getResources().getDimensionPixelSize(0x7f0e03cb);
            container.setPaddingRelative(container.getPaddingStart(), container.getPaddingTop(), i - j - k, container.getPaddingBottom());
            return;
        }
    }

    public final void updateUi()
    {
        GrooveTrackingData groovetrackingdata = model.getTrackingData();
        int i = groovetrackingdata.interval;
        if (i == 2 || i == 3)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (this != null)
        {
            if (i != 0)
            {
                i = 0;
            } else
            {
                i = 8;
            }
            setVisibility(i);
        }
        i = groovetrackingdata.interval;
        if (i == 2 || i == 3)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            return;
        }
        LinearLayout linearlayout = (LinearLayout)findViewById(0x7f1002a5);
        ArrayList arraylist = groovetrackingdata.intervalDataList;
        if (firstUpdate)
        {
            linearlayout.removeAllViews();
            int j = 0;
            while (j < arraylist.size()) 
            {
                Object obj;
                TrackingView trackingview;
                TrackingIntervalData trackingintervaldata1;
                int l;
                int j1;
                long l2;
                boolean flag;
                boolean flag2;
                if (inAccessibilityMode)
                {
                    l = j;
                } else
                {
                    l = arraylist.size() - j - 1;
                }
                trackingintervaldata1 = (TrackingIntervalData)arraylist.get(l);
                trackingview = new TrackingView(getContext());
                linearlayout.addView(trackingview);
                trackingview.setLayoutParams(new android.widget.LinearLayout.LayoutParams(getResources().getDimensionPixelSize(0x7f0e03cb), -2));
                if (l == 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                trackingview.setIsPrimary(flag);
                trackingview.setColor(((ColorHolder)model).getColor(getContext()));
                l = groovetrackingdata.interval;
                l2 = trackingintervaldata1.startMillis;
                if (l == 3)
                {
                    obj = Utils.formatDateTime(getContext(), l2, 48, Utils.getTimeZoneId(getContext()));
                } else
                if (GrooveUtils.isInThisWeek(getContext(), l2))
                {
                    obj = getResources().getString(0x7f130496);
                } else
                if (GrooveUtils.isInLastWeek(getContext(), l2))
                {
                    obj = getResources().getString(0x7f130495);
                } else
                {
                    obj = Utils.formatDateTime(getContext(), l2, 0x10010, Utils.getTimeZoneId(getContext()));
                }
                trackingview.bottomText.setText(((CharSequence) (obj)));
                l = trackingintervaldata1.completedInstances;
                j1 = groovetrackingdata.numInstancesPerInterval;
                if (firstUpdate && flag)
                {
                    flag2 = true;
                } else
                {
                    flag2 = false;
                }
                trackingview.setProgress(l, j1, flag2);
                if (flag && !inAccessibilityMode)
                {
                    trackingview.setOnClickListener(new _cls2());
                    Utils.addBorderlessTouchFeedback(trackingview.getContext(), trackingview.circle);
                }
                obj = (android.widget.LinearLayout.LayoutParams)trackingview.getLayoutParams();
                ((android.widget.LinearLayout.LayoutParams) (obj)).setMarginStart(getResources().getDimensionPixelOffset(0x7f0e03cd));
                trackingview.setLayoutParams(((android.view.ViewGroup.LayoutParams) (obj)));
                j++;
            }
            if (!inAccessibilityMode)
            {
                final HorizontalScrollView scrollView = (HorizontalScrollView)findViewById(0x7f100149);
                scrollView.post(new _cls1());
            }
        } else
        {
            int k = 0;
            while (k < arraylist.size()) 
            {
                TrackingIntervalData trackingintervaldata;
                TrackingView trackingview1;
                int i1;
                int k1;
                int l1;
                boolean flag1;
                if (inAccessibilityMode)
                {
                    i1 = k;
                } else
                {
                    i1 = arraylist.size() - k - 1;
                }
                trackingintervaldata = (TrackingIntervalData)arraylist.get(i1);
                trackingview1 = (TrackingView)linearlayout.getChildAt(k);
                k1 = trackingintervaldata.completedInstances;
                l1 = groovetrackingdata.numInstancesPerInterval;
                if (groovetrackingdata.shouldAnimateUpdate && i1 == 0)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                trackingview1.setProgress(k1, l1, flag1);
                trackingview1.setColor(((ColorHolder)model).getColor(getContext()));
                k++;
            }
        }
        firstUpdate = false;
        groovetrackingdata.shouldAnimateUpdate = false;
    }

    private class _cls2
        implements android.view.View.OnClickListener
    {

        private final TrackingViewSegment this$0;

        public final void onClick(View view)
        {
            listener.onMarkAsDoneClicked();
            view = getContext();
            if (view != null)
            {
                AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
                if (analyticslogger == null)
                {
                    throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
                }
                ((AnalyticsLogger)analyticslogger).trackEvent(view, "groove", "tracking_view_clicked", "", null);
            }
        }

        _cls2()
        {
            this$0 = TrackingViewSegment.this;
            super();
        }
    }


    private class _cls1
        implements Runnable
    {

        private final TrackingViewSegment this$0;
        private final HorizontalScrollView val$scrollView;

        public final void run()
        {
            HorizontalScrollView horizontalscrollview = scrollView;
            byte byte0;
            if (RtlUtils.isLayoutDirectionRtl(getContext()))
            {
                byte0 = 17;
            } else
            {
                byte0 = 66;
            }
            horizontalscrollview.fullScroll(byte0);
        }

        _cls1()
        {
            this$0 = TrackingViewSegment.this;
            scrollView = horizontalscrollview;
            super();
        }
    }

}
