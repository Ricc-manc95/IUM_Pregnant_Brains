// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;

// Referenced classes of package com.google.android.gms.common.internal:
//            zzo

public abstract class zzn
{

    private static final Object zzaQo = new Object();
    private static zzn zzaQp;

    public zzn()
    {
    }

    public static zzn zzaF(Context context)
    {
        synchronized (zzaQo)
        {
            if (zzaQp == null)
            {
                zzaQp = new zzo(context.getApplicationContext());
            }
        }
        return zzaQp;
        context;
        obj;
        JVM INSTR monitorexit ;
        throw context;
    }

    public abstract boolean zza(ComponentName componentname, ServiceConnection serviceconnection, String s);

    public abstract boolean zza(String s, String s1, ServiceConnection serviceconnection, String s2);

    public abstract void zzb(ComponentName componentname, ServiceConnection serviceconnection, String s);

    public abstract void zzb(String s, String s1, ServiceConnection serviceconnection, String s2);

}
