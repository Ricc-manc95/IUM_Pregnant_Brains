// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.tracking;

import android.widget.HorizontalScrollView;
import com.google.android.calendar.utils.rtl.RtlUtils;

// Referenced classes of package com.google.android.calendar.newapi.segment.tracking:
//            TrackingViewSegment

final class val.scrollView
    implements Runnable
{

    private final TrackingViewSegment this$0;
    private final HorizontalScrollView val$scrollView;

    public final void run()
    {
        HorizontalScrollView horizontalscrollview = val$scrollView;
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

    ()
    {
        this$0 = final_trackingviewsegment;
        val$scrollView = HorizontalScrollView.this;
        super();
    }
}
