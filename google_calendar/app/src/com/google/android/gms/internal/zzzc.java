// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

public class zzzc extends GoogleApiClient
{

    private final UnsupportedOperationException zzaKU;

    public zzzc(String s)
    {
        zzaKU = new UnsupportedOperationException(s);
    }

    public final ConnectionResult blockingConnect()
    {
        throw zzaKU;
    }

    public final ConnectionResult blockingConnect(long l, TimeUnit timeunit)
    {
        throw zzaKU;
    }

    public final void connect()
    {
        throw zzaKU;
    }

    public final void disconnect()
    {
        throw zzaKU;
    }

    public final void dump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        throw zzaKU;
    }

    public final boolean isConnected()
    {
        throw zzaKU;
    }

    public final boolean isConnecting()
    {
        throw zzaKU;
    }

    public final void registerConnectionCallbacks(com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks)
    {
        throw zzaKU;
    }

    public final void registerConnectionFailedListener(com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener)
    {
        throw zzaKU;
    }
}
