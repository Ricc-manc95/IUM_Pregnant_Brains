// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.security;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import java.lang.reflect.Method;

public final class ProviderInstaller
{

    private static final GoogleApiAvailabilityLight zzcmb;
    private static Method zzcmc = null;
    private static final Object zzun = new Object();

    public static void installIfNeeded(Context context)
        throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException
    {
        if (context == null)
        {
            throw new NullPointerException(String.valueOf("Context must not be null"));
        }
        GoogleApiAvailabilityLight.zzar(context);
        context = GooglePlayServicesUtilLight.getRemoteContext(context);
        if (context == null)
        {
            Log.e("ProviderInstaller", "Failed to get remote context");
            throw new GooglePlayServicesNotAvailableException(8);
        }
        if (true) goto _L2; else goto _L1
_L1:
        obj;
        JVM INSTR monitorenter ;
_L2:
        synchronized (zzun)
        {
            if (zzcmc == null)
            {
                zzcmc = context.getClassLoader().loadClass("com.google.android.gms.common.security.ProviderInstallerImpl").getMethod("insertProvider", new Class[] {
                    android/content/Context
                });
            }
            zzcmc.invoke(null, new Object[] {
                context
            });
        }
        return;
        context;
        context = String.valueOf(context.getMessage());
        if (context.length() == 0)
        {
            break MISSING_BLOCK_LABEL_150;
        }
        context = "Failed to install provider: ".concat(context);
_L3:
        Log.e("ProviderInstaller", context);
        throw new GooglePlayServicesNotAvailableException(8);
        context;
        obj;
        JVM INSTR monitorexit ;
        throw context;
        context = new String("Failed to install provider: ");
          goto _L3
    }

    static 
    {
        zzcmb = GoogleApiAvailabilityLight.zzaIo;
    }
}
