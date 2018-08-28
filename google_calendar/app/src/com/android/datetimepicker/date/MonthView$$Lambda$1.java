// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.datetimepicker.date;


// Referenced classes of package com.android.datetimepicker.date:
//            MonthView

final class arg._cls1
    implements Dispatcher
{

    private final MonthView arg$1;

    public final void invoke(rDay rday)
    {
        arg$1.onDayLongClick(rday);
    }

    rDay(MonthView monthview)
    {
        arg$1 = monthview;
    }
}
