// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task;

import com.android.calendarcommon2.LogUtils;

// Referenced classes of package com.google.android.calendar.task:
//            ArpTaskConnection

final class arg._cls1
    implements Runnable
{

    private final ArpTaskConnection arg$1;

    public final void run()
    {
        ArpTaskConnection arptaskconnection = arg$1;
        arptaskconnection.timeoutFuture = null;
        LogUtils.v("ArpTaskConnection", "Idle timeout - disconnecting", new Object[0]);
        arptaskconnection.disconnectAllClients();
    }

    (ArpTaskConnection arptaskconnection)
    {
        arg$1 = arptaskconnection;
    }
}
