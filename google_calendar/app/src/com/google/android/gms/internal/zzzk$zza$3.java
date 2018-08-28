// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import com.google.android.gms.common.ConnectionResult;

final class zzaMt
    implements Runnable
{

    private final zzaMt zzaMs;
    private final ConnectionResult zzaMt;

    public final void run()
    {
        zzaMs.ConnectionFailed(zzaMt);
    }

    ult(ult ult, ConnectionResult connectionresult)
    {
        zzaMs = ult;
        zzaMt = connectionresult;
        super();
    }
}
