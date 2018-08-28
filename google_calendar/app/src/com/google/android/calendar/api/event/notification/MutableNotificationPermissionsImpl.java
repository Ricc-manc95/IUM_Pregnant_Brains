// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.notification;

import com.google.android.calendar.api.event.Event;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.calendar.api.event.notification:
//            ReadOnlyNotificationPermissionsImpl

public final class MutableNotificationPermissionsImpl extends ReadOnlyNotificationPermissionsImpl
{

    public MutableNotificationPermissionsImpl(Event event)
    {
        if (event == null)
        {
            throw new NullPointerException();
        } else
        {
            return;
        }
    }

    public final boolean isReadOnly()
    {
        return false;
    }

    static 
    {
        TimeUnit.DAYS.toMinutes(1L);
        TimeUnit.DAYS.toMinutes(28L);
    }
}
