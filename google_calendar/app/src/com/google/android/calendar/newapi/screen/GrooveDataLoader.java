// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen;

import android.content.Context;
import com.google.android.calendar.api.habit.Habit;
import com.google.android.calendar.newapi.common.AsyncTaskLoader;
import com.google.android.calendar.newapi.common.CompositeLoader;
import com.google.android.calendar.newapi.common.GrooveTrackingLoader;
import com.google.android.calendar.newapi.common.Loader;
import com.google.android.calendar.newapi.common.SerialLoader;
import com.google.android.calendar.newapi.common.TimelineGrooveLoader;
import com.google.android.calendar.newapi.segment.tracking.GrooveTrackingData;
import com.google.android.calendar.timely.TimelineGroove;
import java.util.Collections;
import java.util.List;

public final class GrooveDataLoader extends SerialLoader
{

    private final Context context;
    private final TimelineGroove timelineGroove;

    public GrooveDataLoader(Context context1, TimelineGroove timelinegroove)
    {
        super(new GrooveDataResult());
        context = context1.getApplicationContext();
        timelineGroove = timelinegroove;
        context1 = new TimelineGrooveLoader(context, timelinegroove);
        super.loaders.add(context1);
    }

    protected final void onLoaderFinished(int i, Object obj)
    {
        obj = (GrooveDataResult)obj;
        TimelineGrooveLoader timelinegrooveloader = (TimelineGrooveLoader)(Loader)Collections.unmodifiableList(super.loaders).get(0);
        if (i == 0)
        {
            obj.habit = (Habit)timelinegrooveloader.getResult();
            if (((GrooveDataResult) (obj)).habit != null)
            {
                obj = new GrooveTrackingLoader(context, ((GrooveDataResult) (obj)).habit, timelineGroove);
                super.loaders.add(obj);
            }
            return;
        } else
        {
            obj.trackingData = (GrooveTrackingData)((Loader)Collections.unmodifiableList(super.loaders).get(1)).getResult();
            return;
        }
    }

    private class GrooveDataResult
    {

        public Habit habit;
        public GrooveTrackingData trackingData;

        public GrooveDataResult()
        {
        }
    }

}
