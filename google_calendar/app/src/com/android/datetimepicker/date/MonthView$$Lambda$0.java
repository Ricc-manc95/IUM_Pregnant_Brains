// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.datetimepicker.date;


// Referenced classes of package com.android.datetimepicker.date:
//            MonthView

final class arg._cls3
    implements Runnable
{

    private final MonthView arg$1;
    private final Dispatcher arg$2;
    private final rDay arg$3;

    public final void run()
    {
        MonthView monthview = arg$1;
        arg$2.invoke(arg$3);
        monthview.mActiveLaunchDayRunnable = null;
    }

    rDay(MonthView monthview, Dispatcher dispatcher, rDay rday)
    {
        arg$1 = monthview;
        arg$2 = dispatcher;
        arg$3 = rday;
    }
}
