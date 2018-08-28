// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;


// Referenced classes of package com.google.android.calendar:
//            AllInOneCalendarActivity, LaunchScreenManager

final class arg._cls1
    implements com.google.android.apps.calendar.timeline.alternate.view.api.
{

    private final AllInOneCalendarActivity arg$1;

    public final boolean get()
    {
        boolean flag;
        if (!arg$1.launchScreenManager.launchScreenIsDismissed)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        return !flag;
    }

    TimelineSpi.IsVisibleSupplier(AllInOneCalendarActivity allinonecalendaractivity)
    {
        arg$1 = allinonecalendaractivity;
    }
}
