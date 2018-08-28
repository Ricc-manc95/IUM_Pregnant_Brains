// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.event;

import android.os.Parcelable;
import com.google.android.calendar.api.settings.GoogleSettings;
import com.google.android.calendar.api.settings.Settings;

// Referenced classes of package com.google.android.calendar.newapi.screen.event:
//            AutoValue_EventDuration

public abstract class EventDuration
    implements Parcelable
{

    public EventDuration()
    {
    }

    public static EventDuration createDefault(Settings settings)
    {
        if (settings instanceof GoogleSettings)
        {
            settings = (GoogleSettings)settings;
        } else
        {
            settings = null;
        }
        if (settings == null)
        {
            return new AutoValue_EventDuration(0x36ee80L, false);
        } else
        {
            return new AutoValue_EventDuration(settings.getDefaultEventDurationMillis(), settings.isEndTimeUnspecifiedByDefault());
        }
    }

    public abstract long getMillis();

    public abstract boolean isEndTimeUnspecified();
}
