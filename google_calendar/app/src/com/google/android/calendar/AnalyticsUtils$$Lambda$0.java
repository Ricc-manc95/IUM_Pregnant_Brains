// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.content.Context;
import android.content.Intent;
import com.google.android.apps.calendar.util.function.Consumer;

// Referenced classes of package com.google.android.calendar:
//            AnalyticsUtils

final class arg._cls3
    implements Consumer
{

    private final Context arg$1;
    private final Intent arg$2;
    private final boolean arg$3;

    public final void accept(Object obj)
    {
        AnalyticsUtils.lambda$postAppOpenAnalytics$0$AnalyticsUtils(arg$1, arg$2, arg$3, (com.google.android.calendar.api.calendarlist.CalendarListEntry[])obj);
    }

    er(Context context, Intent intent, boolean flag)
    {
        arg$1 = context;
        arg$2 = intent;
        arg$3 = flag;
    }
}
