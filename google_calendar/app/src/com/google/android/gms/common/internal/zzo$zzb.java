// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import com.google.android.gms.common.stats.zza;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

// Referenced classes of package com.google.android.gms.common.internal:
//            zzo

final class mState
{

    public int mState;
    public final zza zzaQu = new zza();
    public final Set zzaQv = new HashSet();
    public boolean zzaQw;
    private final zza zzaQx;
    public final zzo zzaQy;
    public ComponentName zzaql;
    public IBinder zzsE;

    public final void zza(ServiceConnection serviceconnection, String s)
    {
        zzo.zzc(zzaQy);
        zzo.zzb(zzaQy);
        zzaQx.zzzm();
        com.google.android.gms.common.stats.zza.zza$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D662RJ4E9NMIP1FCDNMST35DPQ2UKR5E9R6IOR58DNMSRJ5CDQ6IRRE7D66KOBMC4NMOOBECSNL6T3ID5N6EEQCC5N68SJFD5I2UORFDPQ6ARJK5T4MST35DPQ3MAAM0();
        zzaQv.add(serviceconnection);
    }

    public final void zzcV(String s)
    {
        mState = 3;
        zzo.zzc(zzaQy);
        s = zzo.zzb(zzaQy);
        android.content.Intent intent = zzaQx.zzzm();
        zza zza1 = zzaQu;
        boolean flag;
        if (com.google.android.gms.common.stats.zza.zzc(s, intent))
        {
            Log.w("ConnectionTracker", "Attempted to bind to a service in a STOPPED package.");
            flag = false;
        } else
        {
            flag = s.bindService(intent, zza1, 129);
        }
        zzaQw = flag;
        if (zzaQw)
        {
            break MISSING_BLOCK_LABEL_93;
        }
        mState = 2;
        zzo.zzc(zzaQy);
        com.google.android.gms.common.stats.zza.zza(zzo.zzb(zzaQy), zzaQu);
        return;
        s;
    }

    public zza.zzaQz(zzo zzo1, zza zza1)
    {
        zzaQy = zzo1;
        super();
        zzaQx = zza1;
        class zza
            implements ServiceConnection
        {

            private final zzo.zzb zzaQz;

            public final void onServiceConnected(ComponentName componentname, IBinder ibinder)
            {
                java.util.HashMap hashmap = com.google.android.gms.common.internal.zzo.zza(zzaQz.zzaQy);
                hashmap;
                JVM INSTR monitorenter ;
                zzaQz.zzsE = ibinder;
                zzaQz.zzaql = componentname;
                for (Iterator iterator = zzaQz.zzaQv.iterator(); iterator.hasNext(); ((ServiceConnection)iterator.next()).onServiceConnected(componentname, ibinder)) { }
                break MISSING_BLOCK_LABEL_78;
                componentname;
                hashmap;
                JVM INSTR monitorexit ;
                throw componentname;
                zzaQz.mState = 1;
                hashmap;
                JVM INSTR monitorexit ;
            }

            public final void onServiceDisconnected(ComponentName componentname)
            {
                java.util.HashMap hashmap = com.google.android.gms.common.internal.zzo.zza(zzaQz.zzaQy);
                hashmap;
                JVM INSTR monitorenter ;
                zzaQz.zzsE = null;
                zzaQz.zzaql = componentname;
                for (Iterator iterator = zzaQz.zzaQv.iterator(); iterator.hasNext(); ((ServiceConnection)iterator.next()).onServiceDisconnected(componentname)) { }
                break MISSING_BLOCK_LABEL_74;
                componentname;
                hashmap;
                JVM INSTR monitorexit ;
                throw componentname;
                zzaQz.mState = 2;
                hashmap;
                JVM INSTR monitorexit ;
            }

            public zza()
            {
                zzaQz = zzo.zzb.this;
                super();
            }
        }

        mState = 2;
    }
}
