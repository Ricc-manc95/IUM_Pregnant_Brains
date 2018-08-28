// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.alerts;

import android.app.AlarmManager;
import android.app.PendingIntent;
import com.google.android.calendar.utils.version.MncUtil;

// Referenced classes of package com.google.android.calendar.alerts:
//            AlarmManagerInterface

final class val.mgr
    implements AlarmManagerInterface
{

    private final AlarmManager val$mgr;

    public final void cancel(PendingIntent pendingintent)
    {
        val$mgr.cancel(pendingintent);
    }

    public final void setExact(int i, long l, PendingIntent pendingintent)
    {
        val$mgr.setExact(i, l, pendingintent);
    }

    public final void setExactAndAllowWhileIdle(int i, long l, PendingIntent pendingintent)
    {
        AlarmManager alarmmanager = val$mgr;
        if (MncUtil.isMnc())
        {
            alarmmanager.setExactAndAllowWhileIdle(i, l, pendingintent);
            return;
        } else
        {
            alarmmanager.setExact(i, l, pendingintent);
            return;
        }
    }

    l()
    {
        val$mgr = alarmmanager;
        super();
    }
}
