// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.gplus;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.newapi.model.EventHolder;
import com.google.android.calendar.newapi.model.GooglePlusHolder;
import com.google.android.calendar.newapi.segment.common.ViewSegment;

// Referenced classes of package com.google.android.calendar.newapi.segment.gplus:
//            CustomAppViewSegment

public final class GooglePlusViewSegment extends CustomAppViewSegment
    implements android.view.View.OnClickListener, ViewSegment
{

    public GooglePlusViewSegment(Context context, EventHolder eventholder)
    {
        super(context, eventholder);
    }

    protected final Drawable getIconDrawable()
    {
        return getResources().getDrawable(0x7f0201ff);
    }

    public final void onClick(View view)
    {
        AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
        if (analyticslogger == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        } else
        {
            ((AnalyticsLogger)analyticslogger).trackEvent(getContext(), "event_action", "open_in_plus");
            super.onClick(view);
            return;
        }
    }

    protected final boolean showSegment()
    {
        return ((GooglePlusHolder)super.model).isGooglePlusEvent();
    }
}
