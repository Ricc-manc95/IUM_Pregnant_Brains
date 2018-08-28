// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.timezonepicker.fullscreen;

import com.android.timezonepicker.TimeZoneManager;

// Referenced classes of package com.android.timezonepicker.fullscreen:
//            TimeZoneToolbarController

final class arg._cls2
    implements com.google.android.calendar.utils.text.ener
{

    private final TimeZoneToolbarController arg$1;
    private final TimeZoneManager arg$2;

    public final void onTextChanged(String s)
    {
        TimeZoneToolbarController timezonetoolbarcontroller = arg$1;
        TimeZoneManager timezonemanager = arg$2;
        timezonemanager.filteringRequest = s;
        if (timezonemanager.timeZoneGroupsList != null)
        {
            timezonemanager.filter.r(s);
        }
        timezonetoolbarcontroller.updateClearButtonVisibility();
    }

    tener(TimeZoneToolbarController timezonetoolbarcontroller, TimeZoneManager timezonemanager)
    {
        arg$1 = timezonetoolbarcontroller;
        arg$2 = timezonemanager;
    }
}
