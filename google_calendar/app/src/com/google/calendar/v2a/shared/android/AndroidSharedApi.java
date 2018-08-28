// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.v2a.shared.android;

import com.google.calendar.v2a.shared.AsyncSharedApi;
import com.google.calendar.v2a.shared.sync.DebugService;
import com.google.calendar.v2a.shared.sync.android.SyncDebugService;

public interface AndroidSharedApi
    extends AsyncSharedApi
{

    public abstract DebugService debugService();

    public abstract SyncDebugService syncDebugService();
}
