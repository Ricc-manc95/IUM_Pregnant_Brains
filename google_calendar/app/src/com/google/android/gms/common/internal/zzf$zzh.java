// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import com.google.android.gms.common.ConnectionResult;

// Referenced classes of package com.google.android.gms.common.internal:
//            zzf, zzv

public final class zzaPK
    implements ServiceConnection
{

    private final zzf zzaPH;
    private final int zzaPK;

    public final void onServiceConnected(ComponentName componentname, IBinder ibinder)
    {
        if (ibinder == null)
        {
            zzf.zza(zzaPH, new ConnectionResult(8, null, "ServiceBroker IBinder is null"));
            return;
        }
        Object obj = zzf.zza(zzaPH);
        obj;
        JVM INSTR monitorenter ;
        zzf zzf1 = zzaPH;
        if (ibinder != null) goto _L2; else goto _L1
_L1:
        componentname = null;
_L4:
        zzf.zza(zzf1, componentname);
        obj;
        JVM INSTR monitorexit ;
        componentname = zzaPH;
        int i = zzaPK;
        ((zzf) (componentname)).mHandler.sendMessage(((zzf) (componentname)).mHandler.obtainMessage(5, i, -1, new <init>(componentname, 0, null)));
        return;
_L2:
        componentname = ibinder.queryLocalInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
        if (componentname == null)
        {
            break MISSING_BLOCK_LABEL_124;
        }
        if (componentname instanceof zzv)
        {
            componentname = (zzv)componentname;
            continue; /* Loop/switch isn't completed */
        }
        componentname = new zza(ibinder);
        if (true) goto _L4; else goto _L3
_L3:
        componentname;
        obj;
        JVM INSTR monitorexit ;
        throw componentname;
    }

    public final void onServiceDisconnected(ComponentName componentname)
    {
        synchronized (zzf.zza(zzaPH))
        {
            zzf.zza(zzaPH, null);
        }
        zzaPH.mHandler.sendMessage(zzaPH.mHandler.obtainMessage(4, zzaPK, 1));
        return;
        exception;
        componentname;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public zza(zzf zzf1, int i)
    {
        zzaPH = zzf1;
        super();
        zzaPK = i;
    }
}
