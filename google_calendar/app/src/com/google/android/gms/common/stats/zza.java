// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.stats;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import com.google.android.gms.common.util.zzd;
import com.google.android.gms.internal.zzaau;
import java.util.Arrays;

// Referenced classes of package com.google.android.gms.common.stats:
//            zzc, zzd

public final class zza
{

    private static final Object zzaQo = new Object();
    private static zza zzaSo;
    private static Integer zzaSu;

    private zza()
    {
        if (getLogLevel() == zzc.LOG_LEVEL_OFF)
        {
            return;
        }
        String s = (String)zzb.zza.zzaSz.get();
        if (s != null)
        {
            Arrays.asList(s.split(","));
        }
        s = (String)zzb.zza.zzaSA.get();
        if (s != null)
        {
            Arrays.asList(s.split(","));
        }
        s = (String)zzb.zza.zzaSB.get();
        if (s != null)
        {
            Arrays.asList(s.split(","));
        }
        s = (String)zzb.zza.zzaSC.get();
        if (s != null)
        {
            Arrays.asList(s.split(","));
        }
        new com.google.android.gms.common.stats.zzd(1024, ((Long)zzb.zza.zzaSD.get()).longValue());
        new com.google.android.gms.common.stats.zzd(1024, ((Long)zzb.zza.zzaSD.get()).longValue());
    }

    private static int getLogLevel()
    {
        if (zzaSu == null)
        {
            try
            {
                zzaSu = Integer.valueOf(zzc.LOG_LEVEL_OFF);
            }
            catch (SecurityException securityexception)
            {
                zzaSu = Integer.valueOf(zzc.LOG_LEVEL_OFF);
            }
        }
        return zzaSu.intValue();
    }

    public static void zza(Context context, ServiceConnection serviceconnection)
    {
        context.unbindService(serviceconnection);
    }

    public static void zza$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D662RJ4E9NMIP1FCDNMST35DPQ2UKR5E9R6IOR58DNMSRJ5CDQ6IRRE7D66KOBMC4NMOOBECSNL6T3ID5N6EEQCC5N68SJFD5I2UORFDPQ6ARJK5T4MST35DPQ3MAAM0()
    {
    }

    public static void zzb$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D662RJ4E9NMIP1FCDNMST35DPQ2UKR5E9R6IOR58DNMSRJ5CDQ6IRRE7CKLC___0()
    {
    }

    public static boolean zzc(Context context, Intent intent)
    {
        intent = intent.getComponent();
        if (intent == null)
        {
            return false;
        } else
        {
            return zzd.zzy(context, intent.getPackageName());
        }
    }

    public static zza zzzN()
    {
        synchronized (zzaQo)
        {
            if (zzaSo == null)
            {
                zzaSo = new zza();
            }
        }
        return zzaSo;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

}
