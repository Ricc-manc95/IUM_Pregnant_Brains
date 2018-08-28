// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.widget;


// Referenced classes of package com.google.android.calendar.widget:
//            CalendarAppWidgetModel, WidgetDataMachine

final class this._cls0
    implements com.google.android.calendar.task.ssor
{

    private final WidgetDataMachine this$0;

    public final int getEndDay()
    {
        return maxJulianDay;
    }

    public final int getStartDay()
    {
        return monthStartJulianDay;
    }

    public final com.google.android.calendar.timely.lts getStorage()
    {
        return CalendarAppWidgetModel.this.getStorage();
    }

    lts()
    {
        this$0 = WidgetDataMachine.this;
        super();
    }
}
