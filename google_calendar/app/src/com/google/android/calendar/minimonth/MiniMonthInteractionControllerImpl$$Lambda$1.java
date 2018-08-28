// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.minimonth;

import android.support.v4.view.ViewPager;
import com.google.android.apps.calendar.timebox.TimeBoxUtil;
import com.google.android.apps.calendar.timeline.alternate.fragment.api.CalendarFragment;
import com.google.android.apps.calendar.timeline.alternate.minimonth.api.MiniMonthController;
import com.google.android.calendar.Utils;
import java.util.Calendar;

// Referenced classes of package com.google.android.calendar.minimonth:
//            MiniMonthInteractionControllerImpl

final class arg._cls1
    implements com.google.android.apps.calendar.timeline.alternate.minimonth.api.
{

    private final MiniMonthInteractionControllerImpl arg$1;

    public final void onMonthChanged(int i, int j)
    {
        MiniMonthInteractionControllerImpl minimonthinteractioncontrollerimpl = arg$1;
        java.util.TimeZone timezone = Utils.getTimeZone(minimonthinteractioncontrollerimpl.miniMonth.getContext());
        Calendar calendar = Calendar.getInstance(timezone);
        calendar.set(i, j, 1);
        i = TimeBoxUtil.msToJulianDay(timezone, calendar.getTimeInMillis());
        minimonthinteractioncontrollerimpl.updateActionBar(i);
        minimonthinteractioncontrollerimpl.controller.pointTo(i, false);
        minimonthinteractioncontrollerimpl.fragment.goToDay(i);
    }

    er(MiniMonthInteractionControllerImpl minimonthinteractioncontrollerimpl)
    {
        arg$1 = minimonthinteractioncontrollerimpl;
    }
}
