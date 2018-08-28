// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.timezonepicker;

import com.android.calendarcommon2.LogUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TimeZone;
import java.util.concurrent.Callable;

// Referenced classes of package com.android.timezonepicker:
//            TimeZoneManager, TimeZoneLoader, TimeZoneParams

public final class arg._cls1
    implements Callable
{

    private final TimeZoneManager arg$1;

    public final Object call()
    {
        TimeZoneLoader timezoneloader = arg$1.loader;
        timezoneloader.countryCodeToNameMap = timezoneloader.loadArraysToMap(0x7f0b0012, 0x7f0b0013);
        timezoneloader.timeZoneIdToLabelMap = timezoneloader.loadArraysToMap(0x7f0b0055, 0x7f0b0056);
        ArrayList arraylist = new ArrayList(700);
        HashSet hashset = timezoneloader.loadTimeZonesFromBackwardAndZoneTab(arraylist);
        String as[] = TimeZone.getAvailableIDs();
        int j = as.length;
        int i = 0;
        while (i < j) 
        {
            String s = as[i];
            if (!hashset.contains(s) && s.startsWith("Etc/GMT"))
            {
                TimeZone timezone = TimeZone.getTimeZone(s);
                if (timezone == null)
                {
                    LogUtils.e(TimeZoneLoader.TAG, "Timezone not found: %s", new Object[] {
                        s
                    });
                } else
                {
                    arraylist.add(TimeZoneParams.builder(timezone, timezoneloader.timeToDisplay, null, (String)timezoneloader.timeZoneIdToLabelMap.get(timezone.getID())).ld());
                }
            }
            i++;
        }
        Collections.sort(arraylist);
        return arraylist;
    }

    public (TimeZoneManager timezonemanager)
    {
        arg$1 = timezonemanager;
    }
}
