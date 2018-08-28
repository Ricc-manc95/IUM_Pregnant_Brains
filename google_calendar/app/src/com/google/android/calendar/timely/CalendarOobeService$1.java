// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import com.google.android.calendar.timely.settings.PreferencesUtils;

// Referenced classes of package com.google.android.calendar.timely:
//            CalendarOobeService

final class it> extends com.google.android.calendar.tub
{

    private final CalendarOobeService this$0;

    public final boolean isOobeCompleted()
    {
        return PreferencesUtils.hasOobeBeenSeen(CalendarOobeService.this);
    }

    ils()
    {
        this$0 = CalendarOobeService.this;
        super();
    }
}
