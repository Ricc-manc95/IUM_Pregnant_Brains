// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import com.google.android.apps.calendar.util.function.Consumer;

// Referenced classes of package com.google.android.calendar:
//            AllInOneCalendarActivity, LaunchScreenManager

final class arg._cls1
    implements Consumer
{

    private final AllInOneCalendarActivity arg$1;

    public final void accept(Object obj)
    {
        AllInOneCalendarActivity allinonecalendaractivity = arg$1;
        if (((Boolean)obj).booleanValue())
        {
            obj = allinonecalendaractivity.launchScreenManager;
            obj.dataLoadedAndViewsUpdated = true;
            ((LaunchScreenManager) (obj)).hideLaunchScreen(false);
        }
    }

    (AllInOneCalendarActivity allinonecalendaractivity)
    {
        arg$1 = allinonecalendaractivity;
    }
}
