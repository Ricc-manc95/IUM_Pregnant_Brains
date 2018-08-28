// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public final class zzzm extends BroadcastReceiver
{

    public Context mContext;
    private final zza zzaMx;

    public zzzm(zza zza)
    {
        zzaMx = zza;
    }

    public final void onReceive(Context context, Intent intent)
    {
        intent = intent.getData();
        context = null;
        if (intent != null)
        {
            context = intent.getSchemeSpecificPart();
        }
        if ("com.google.android.gms".equals(context))
        {
            zzaMx.zzwN();
            unregister();
        }
    }

    public final void unregister()
    {
        this;
        JVM INSTR monitorenter ;
        if (mContext != null)
        {
            mContext.unregisterReceiver(this);
        }
        mContext = null;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private class zza
    {

        public abstract void zzwN();

        public zza()
        {
        }
    }

}
