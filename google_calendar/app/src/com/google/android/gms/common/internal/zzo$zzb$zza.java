// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import java.util.Iterator;
import java.util.Set;

// Referenced classes of package com.google.android.gms.common.internal:
//            zzo

public final class zzaQz
    implements ServiceConnection
{

    private final te zzaQz;

    public final void onServiceConnected(ComponentName componentname, IBinder ibinder)
    {
        java.util.HashMap hashmap = zzo.zza(zzaQz.y);
        hashmap;
        JVM INSTR monitorenter ;
        zzaQz. = ibinder;
        zzaQz.l = componentname;
        for (Iterator iterator = zzaQz.v.iterator(); iterator.hasNext(); ((ServiceConnection)iterator.next()).onServiceConnected(componentname, ibinder)) { }
        break MISSING_BLOCK_LABEL_78;
        componentname;
        hashmap;
        JVM INSTR monitorexit ;
        throw componentname;
        zzaQz.te = 1;
        hashmap;
        JVM INSTR monitorexit ;
    }

    public final void onServiceDisconnected(ComponentName componentname)
    {
        java.util.HashMap hashmap = zzo.zza(zzaQz.y);
        hashmap;
        JVM INSTR monitorenter ;
        zzaQz. = null;
        zzaQz.l = componentname;
        for (Iterator iterator = zzaQz.v.iterator(); iterator.hasNext(); ((ServiceConnection)iterator.next()).onServiceDisconnected(componentname)) { }
        break MISSING_BLOCK_LABEL_74;
        componentname;
        hashmap;
        JVM INSTR monitorexit ;
        throw componentname;
        zzaQz.te = 2;
        hashmap;
        JVM INSTR monitorexit ;
    }

    public ( )
    {
        zzaQz = ;
        super();
    }
}
