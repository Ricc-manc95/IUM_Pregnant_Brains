// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

// Referenced classes of package com.google.android.gms.common:
//            GoogleApiAvailabilityLight, GoogleApiAvailability

final class zzws extends Handler
{

    private final GoogleApiAvailability zzaIn;
    private final Context zzws;

    public final void handleMessage(Message message)
    {
        message.what;
        JVM INSTR tableswitch 1 1: default 24
    //                   1 57;
           goto _L1 _L2
_L1:
        int i = message.what;
        Log.w("GoogleApiAvailability", (new StringBuilder(50)).append("Don't know how to handle this message: ").append(i).toString());
_L4:
        return;
_L2:
        int j = zzaIn.isGooglePlayServicesAvailable(zzws);
        if (zzaIn.isUserResolvableError(j))
        {
            zzaIn.showErrorNotification(zzws, j);
            return;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public (GoogleApiAvailability googleapiavailability, Context context)
    {
        zzaIn = googleapiavailability;
        if (Looper.myLooper() == null)
        {
            googleapiavailability = Looper.getMainLooper();
        } else
        {
            googleapiavailability = Looper.myLooper();
        }
        super(googleapiavailability);
        zzws = context.getApplicationContext();
    }
}
