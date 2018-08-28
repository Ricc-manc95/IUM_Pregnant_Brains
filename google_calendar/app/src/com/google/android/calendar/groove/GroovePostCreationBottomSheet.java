// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.AllInOneCalendarActivity;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.api.event.CpEventKey;
import com.google.android.calendar.api.event.EventKey;
import com.google.android.calendar.launch.LaunchIntentConstants;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.timely.BottomSheet;
import com.google.android.calendar.timely.TimelineItemUtil;
import com.google.common.base.Optional;

public class GroovePostCreationBottomSheet extends BottomSheet
    implements android.view.View.OnClickListener
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/groove/GroovePostCreationBottomSheet);
    private final EventKey eventKey;
    private final TextTileView textTile = (TextTileView)findViewById(0x7f1001e3);

    public GroovePostCreationBottomSheet(Context context, String s, EventKey eventkey)
    {
        super(context, null, 0);
        eventKey = eventkey;
        tweakLayout();
        inflate(mContext, 0x7f050084, this);
        textTile.setPrimaryText(new CharSequence[] {
            s
        });
        findViewById(0x7f1001e4).setOnClickListener(this);
        findViewById(0x7f1001e5).setOnClickListener(this);
    }

    public void onClick(View view)
    {
        boolean flag;
        if (view.getId() == 0x7f1001e4)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        hide(true, flag);
    }

    protected final void onNegativeButtonClicked()
    {
        Object obj = mContext;
        if (obj != null)
        {
            AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
            if (analyticslogger == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            ((AnalyticsLogger)analyticslogger).trackEvent(((Context) (obj)), "groove", "post_creation_sheet", "accepted", null);
        }
        if (!(getContext() instanceof AllInOneCalendarActivity))
        {
            LogUtils.wtf(TAG, "Post creation feedback only supports AllInOneActivity.", new Object[0]);
            return;
        }
        obj = getContext();
        if (LaunchIntentConstants.viewAction == null)
        {
            LaunchIntentConstants.viewAction = String.valueOf(((Context) (obj)).getPackageName()).concat(".EVENT_VIEW");
        }
        obj = new Intent(LaunchIntentConstants.viewAction);
        Bundle bundle;
        if (eventKey instanceof CpEventKey)
        {
            ((Intent) (obj)).setData((Uri)eventKey.uri().get());
        } else
        {
            ((Intent) (obj)).setData(ContentUris.withAppendedId(android.provider.CalendarContract.Events.CONTENT_URI, 0L));
            EventKey eventkey = eventKey;
            StringBuilder stringbuilder = (new StringBuilder(eventkey.getClass().getSimpleName())).append('|');
            eventkey.serializeInternal(stringbuilder);
            ((Intent) (obj)).putExtra("eventkey", stringbuilder.toString());
        }
        bundle = new Bundle();
        bundle.putBoolean("show_habits_post_creation_promo", true);
        ((AllInOneCalendarActivity)getContext()).launchInfoBubble(TimelineItemUtil.readTimelineItemFromIntent(getContext(), ((Intent) (obj))), null, bundle);
    }

    protected final void onPositiveButtonClicked()
    {
        Context context = mContext;
        if (context != null)
        {
            AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
            if (analyticslogger == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            ((AnalyticsLogger)analyticslogger).trackEvent(context, "groove", "post_creation_sheet", "dismissed", null);
        }
    }

    protected final void onShow()
    {
        super.onShow();
        announceForAccessibility(textTile.getContentDescription());
        Context context = mContext;
        if (context != null)
        {
            AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
            if (analyticslogger == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            ((AnalyticsLogger)analyticslogger).trackEvent(context, "groove", "post_creation_sheet", "displayed", null);
        }
    }

}
