// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.app.Activity;
import com.google.android.calendar.utils.ActivitySingletonCache;

// Referenced classes of package com.google.android.calendar:
//            CalendarController

final class onCache extends ActivitySingletonCache
{

    protected final Object createInstance(Activity activity)
    {
        return new CalendarController(activity);
    }

    onCache()
    {
    }
}
