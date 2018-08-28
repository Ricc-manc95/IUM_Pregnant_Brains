// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.utils.account.AccountUtil;

// Referenced classes of package com.google.android.calendar:
//            AllInOneCalendarActivity

final class arg._cls2
    implements Consumer
{

    private final AllInOneCalendarActivity arg$1;
    private final boolean arg$2;

    public final void accept(Object obj)
    {
        AllInOneCalendarActivity allinonecalendaractivity;
        int i;
        int j;
        boolean flag1;
        boolean flag2;
        flag1 = true;
        allinonecalendaractivity = arg$1;
        flag2 = arg$2;
        obj = (CalendarListEntry[])obj;
        j = obj.length;
        i = 0;
_L3:
        if (i >= j)
        {
            break MISSING_BLOCK_LABEL_119;
        }
        if (!AccountUtil.isGoogleAccount(obj[i].getDescriptor().account)) goto _L2; else goto _L1
_L1:
        boolean flag = true;
_L4:
        allinonecalendaractivity.speedDialSupported = flag;
        if (!allinonecalendaractivity.receivedFirstCalendar)
        {
            allinonecalendaractivity.receivedFirstCalendar = true;
            if (allinonecalendaractivity.speedDialSupported && flag2)
            {
                flag = flag1;
            } else
            {
                flag = false;
            }
            allinonecalendaractivity.restoreExpandedSpeedDial = flag;
        }
        obj = Features.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        } else
        {
            ((FeatureConfig)obj).uss();
            return;
        }
_L2:
        i++;
          goto _L3
        flag = false;
          goto _L4
    }

    (AllInOneCalendarActivity allinonecalendaractivity, boolean flag)
    {
        arg$1 = allinonecalendaractivity;
        arg$2 = flag;
    }
}
