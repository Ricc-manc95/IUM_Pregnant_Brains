// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.api;

import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

public abstract class GoogleApiClient
{

    public static final Set zzaIY = Collections.newSetFromMap(new WeakHashMap());

    public GoogleApiClient()
    {
    }

    public abstract ConnectionResult blockingConnect();

    public abstract ConnectionResult blockingConnect(long l, TimeUnit timeunit);

    public abstract void connect();

    public void connect(int i)
    {
        throw new UnsupportedOperationException();
    }

    public abstract void disconnect();

    public abstract void dump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[]);

    public Looper getLooper()
    {
        throw new UnsupportedOperationException();
    }

    public abstract boolean isConnected();

    public abstract boolean isConnecting();

    public abstract void registerConnectionCallbacks(ConnectionCallbacks connectioncallbacks);

    public abstract void registerConnectionFailedListener(OnConnectionFailedListener onconnectionfailedlistener);

    public Api.zze zza(Api.zzc zzc)
    {
        throw new UnsupportedOperationException();
    }

    public com.google.android.gms.internal.zzyq.zza zza(com.google.android.gms.internal.zzyq.zza zza1)
    {
        throw new UnsupportedOperationException();
    }

    public com.google.android.gms.internal.zzyq.zza zzb(com.google.android.gms.internal.zzyq.zza zza1)
    {
        throw new UnsupportedOperationException();
    }

}
