// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public final class zzm
    implements android.os.Handler.Callback
{

    public final Handler mHandler;
    public final zza zzaQh;
    public final ArrayList zzaQi = new ArrayList();
    public final ArrayList zzaQj = new ArrayList();
    public final ArrayList zzaQk = new ArrayList();
    public volatile boolean zzaQl;
    public final AtomicInteger zzaQm = new AtomicInteger(0);
    public boolean zzaQn;
    public final Object zzrY = new Object();

    public zzm(Looper looper, zza zza)
    {
        zzaQl = false;
        zzaQn = false;
        zzaQh = zza;
        mHandler = new Handler(looper, this);
    }

    public final boolean handleMessage(Message message)
    {
        if (message.what == 1)
        {
            com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks = (com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks)message.obj;
            synchronized (zzrY)
            {
                if (zzaQl && zzaQh.isConnected() && zzaQi.contains(connectioncallbacks))
                {
                    zzaQh.zzvJ();
                    connectioncallbacks.onConnected(null);
                }
            }
            return true;
        } else
        {
            int i = message.what;
            Log.wtf("GmsClientEvents", (new StringBuilder(45)).append("Don't know how to handle message: ").append(i).toString(), new Exception());
            return false;
        }
        exception;
        message;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final void registerConnectionCallbacks(com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks)
    {
        if (connectioncallbacks == null)
        {
            throw new NullPointerException("null reference");
        }
        Object obj = zzrY;
        obj;
        JVM INSTR monitorenter ;
        if (!zzaQi.contains(connectioncallbacks))
        {
            break MISSING_BLOCK_LABEL_109;
        }
        String s = String.valueOf(connectioncallbacks);
        Log.w("GmsClientEvents", (new StringBuilder(String.valueOf(s).length() + 62)).append("registerConnectionCallbacks(): listener ").append(s).append(" is already registered").toString());
_L2:
        if (zzaQh.isConnected())
        {
            mHandler.sendMessage(mHandler.obtainMessage(1, connectioncallbacks));
        }
        return;
        zzaQi.add(connectioncallbacks);
        if (true) goto _L2; else goto _L1
_L1:
        connectioncallbacks;
        obj;
        JVM INSTR monitorexit ;
        throw connectioncallbacks;
    }

    public final void registerConnectionFailedListener(com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener)
    {
        if (onconnectionfailedlistener == null)
        {
            throw new NullPointerException("null reference");
        }
        Object obj = zzrY;
        obj;
        JVM INSTR monitorenter ;
        if (!zzaQk.contains(onconnectionfailedlistener))
        {
            break MISSING_BLOCK_LABEL_80;
        }
        onconnectionfailedlistener = String.valueOf(onconnectionfailedlistener);
        Log.w("GmsClientEvents", (new StringBuilder(String.valueOf(onconnectionfailedlistener).length() + 67)).append("registerConnectionFailedListener(): listener ").append(onconnectionfailedlistener).append(" is already registered").toString());
_L2:
        return;
        zzaQk.add(onconnectionfailedlistener);
        if (true) goto _L2; else goto _L1
_L1:
        onconnectionfailedlistener;
        obj;
        JVM INSTR monitorexit ;
        throw onconnectionfailedlistener;
    }

    private class zza
    {

        public abstract boolean isConnected();

        public abstract Bundle zzvJ();
    }

}
