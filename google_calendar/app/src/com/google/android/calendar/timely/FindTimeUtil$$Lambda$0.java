// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.Context;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.common.base.Predicate;

// Referenced classes of package com.google.android.calendar.timely:
//            FindTimeScenario, FindTimeUtil

public final class arg._cls2
    implements Predicate
{

    private final Context arg$1;
    private final CalendarListEntry arg$2;

    public final boolean apply(Object obj)
    {
        return FindTimeUtil.lambda$isFindTimeFeatureSupported$0$FindTimeUtil(arg$1, arg$2, (FindTimeScenario)obj);
    }

    public ntry(Context context, CalendarListEntry calendarlistentry)
    {
        arg$1 = context;
        arg$2 = calendarlistentry;
    }
}
