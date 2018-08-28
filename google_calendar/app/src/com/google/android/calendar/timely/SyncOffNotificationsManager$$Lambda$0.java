// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.widget.Toast;

// Referenced classes of package com.google.android.calendar.timely:
//            SyncOffNotificationsManager

final class arg._cls2
    implements Runnable
{

    private final SyncOffNotificationsManager arg$1;
    private final int arg$2;

    public final void run()
    {
        SyncOffNotificationsManager syncoffnotificationsmanager = arg$1;
        int i = arg$2;
        Toast.makeText(syncoffnotificationsmanager.context, i, 0).show();
    }

    (SyncOffNotificationsManager syncoffnotificationsmanager, int i)
    {
        arg$1 = syncoffnotificationsmanager;
        arg$2 = i;
    }
}
