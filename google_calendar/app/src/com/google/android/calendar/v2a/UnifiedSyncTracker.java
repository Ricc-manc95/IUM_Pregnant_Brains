// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.v2a;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

public final class UnifiedSyncTracker
{

    private final Context context;
    private final Listener listener;
    private com.google.calendar.v2a.shared.sync.SyncRequestTracker.Status syncStatus;
    private final List syncTrackers = new ArrayList();

    public UnifiedSyncTracker(Context context1, Listener listener1)
    {
        context = context1.getApplicationContext();
        listener = listener1;
    }

    public final boolean isPendingOrRunning()
    {
        this;
        JVM INSTR monitorenter ;
        if (syncStatus == com.google.calendar.v2a.shared.sync.SyncRequestTracker.Status.PENDING) goto _L2; else goto _L1
_L1:
        com.google.calendar.v2a.shared.sync.SyncRequestTracker.Status status;
        com.google.calendar.v2a.shared.sync.SyncRequestTracker.Status status1;
        status = syncStatus;
        status1 = com.google.calendar.v2a.shared.sync.SyncRequestTracker.Status.RUNNING;
        if (status != status1) goto _L3; else goto _L2
_L2:
        boolean flag = true;
_L5:
        this;
        JVM INSTR monitorexit ;
        return flag;
_L3:
        flag = false;
        if (true) goto _L5; else goto _L4
_L4:
        Exception exception;
        exception;
        throw exception;
    }
}
