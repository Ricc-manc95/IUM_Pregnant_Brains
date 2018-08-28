// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import com.google.android.apps.calendar.timebox.TimeBoxUtil;
import com.google.android.apps.calendar.timeline.alternate.fragment.api.CalendarFragment;
import com.google.android.apps.calendar.timeline.alternate.store.CalendarStoreInvalidator;
import com.google.android.calendar.time.Time;
import com.google.common.base.Optional;
import java.util.TimeZone;

// Referenced classes of package com.google.android.calendar:
//            AllInOneCalendarActivity, MiniMonthInteractionController

final class gment.api.CalendarFragment
    implements Handler
{

    private final AllInOneCalendarActivity this$0;
    private final CalendarFragment val$fragment;

    public final long getSupportedCommands()
    {
        return 160L;
    }

    public final void handleCommand(Handler handler)
    {
        if (handler.type != 32L) goto _L2; else goto _L1
_L1:
        boolean flag;
        if ((handler.extraLong & 8L) != 0L)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L4; else goto _L3
_L3:
        val$fragment.goToNow();
        miniMonthInteractionController.pointTo(TimeBoxUtil.msToJulianDay(TimeZone.getTimeZone(timeZone), handler.selectedTime.toMillisWithFallback()));
_L6:
        return;
_L4:
        val$fragment.goToTime(handler.selectedTime.toMillisWithFallback());
        return;
_L2:
        if (handler.type == 128L && calendarStoreInvalidator.isPresent())
        {
            ((CalendarStoreInvalidator)calendarStoreInvalidator.get()).onEventsChanged();
            return;
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    gment.api.CalendarFragment()
    {
        this$0 = final_allinonecalendaractivity;
        val$fragment = CalendarFragment.this;
        super();
    }
}
