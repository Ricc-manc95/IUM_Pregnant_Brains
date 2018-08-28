// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.widgetmonth;


// Referenced classes of package com.google.android.calendar.widgetmonth:
//            MonthViewWidgetUpdateManager

final class this._cls0
    implements Runnable
{

    private final MonthViewWidgetUpdateManager this$0;

    public final void run()
    {
        if (MonthViewWidgetUpdateManager.instance == MonthViewWidgetUpdateManager.this)
        {
            applyUpdateIfAny();
        }
    }

    ()
    {
        this$0 = MonthViewWidgetUpdateManager.this;
        super();
    }
}
