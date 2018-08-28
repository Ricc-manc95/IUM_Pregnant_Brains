// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.calendarlist;

import com.google.android.apps.calendar.util.function.Consumer;
import com.google.common.collect.ImmutableMap;

// Referenced classes of package com.google.android.calendar.calendarlist:
//            DrawerFragment, SelectCalendarsAdapter

final class arg._cls1
    implements Consumer
{

    private final DrawerFragment arg$1;

    public final void accept(Object obj)
    {
        boolean flag = true;
        DrawerFragment drawerfragment = arg$1;
        drawerfragment.settings = (ImmutableMap)obj;
        drawerfragment.settingsLoaded = true;
        if (!drawerfragment.settingsLoaded || !drawerfragment.calendarsLoaded)
        {
            flag = false;
        }
        if (flag)
        {
            drawerfragment.adapter.settings = drawerfragment.settings;
        }
        drawerfragment.adapter.updateItemList();
    }

    (DrawerFragment drawerfragment)
    {
        arg$1 = drawerfragment;
    }
}
