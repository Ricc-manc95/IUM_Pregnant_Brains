// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.hats;

import android.content.Context;

// Referenced classes of package com.google.android.calendar.hats:
//            CalendarHatsService

final class arg._cls2
    implements Runnable
{

    private final Context arg$1;
    private final String arg$2;

    public final void run()
    {
        CalendarHatsService.lambda$downloadSurveyIfSupported$1$CalendarHatsService(arg$1, arg$2);
    }

    (Context context, String s)
    {
        arg$1 = context;
        arg$2 = s;
    }
}
