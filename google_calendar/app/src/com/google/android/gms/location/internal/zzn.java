// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.location.internal;

import android.content.Context;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.location.zzm;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

// Referenced classes of package com.google.android.gms.location.internal:
//            zzb, zzm, zzv, zzk, 
//            zzq

public final class zzn extends zzb
{

    public final com.google.android.gms.location.internal.zzm zzbCo;

    public zzn(Context context, Looper looper, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener, String s, zzg zzg)
    {
        super(context, looper, connectioncallbacks, onconnectionfailedlistener, s, zzg);
        zzbCo = new com.google.android.gms.location.internal.zzm(context, zzbBW);
    }

    public final void disconnect()
    {
        com.google.android.gms.location.internal.zzm zzm1 = zzbCo;
        zzm1;
        JVM INSTR monitorenter ;
        boolean flag = isConnected();
        if (!flag) goto _L2; else goto _L1
_L1:
        Object obj = zzbCo;
        Map map = ((com.google.android.gms.location.internal.zzm) (obj)).zzbkB;
        map;
        JVM INSTR monitorenter ;
        Iterator iterator = ((com.google.android.gms.location.internal.zzm) (obj)).zzbkB.values().iterator();
_L6:
        if (!iterator.hasNext()) goto _L4; else goto _L3
_L3:
        Object obj1 = (zzm.zzb)iterator.next();
        if (obj1 == null) goto _L6; else goto _L5
_L5:
        ((zzk)((com.google.android.gms.location.internal.zzm) (obj)).zzbBW.zzyP()).zza(new zzq(1, 2, null, ((com.google.android.gms.location.zzn) (obj1)).asBinder(), null, null, null));
          goto _L6
        obj;
        map;
        JVM INSTR monitorexit ;
        throw obj;
        obj;
        try
        {
            throw new IllegalStateException(((Throwable) (obj)));
        }
        // Misplaced declaration of an exception variable
        catch (Object obj) { }
        finally { }
        Log.e("LocationClientImpl", "Client disconnected before listeners could be cleaned up", ((Throwable) (obj)));
_L2:
        super.disconnect();
        zzm1;
        JVM INSTR monitorexit ;
        return;
_L4:
        ((com.google.android.gms.location.internal.zzm) (obj)).zzbkB.clear();
        map;
        JVM INSTR monitorexit ;
        map = ((com.google.android.gms.location.internal.zzm) (obj)).zzbCl;
        map;
        JVM INSTR monitorenter ;
        iterator = ((com.google.android.gms.location.internal.zzm) (obj)).zzbCl.values().iterator();
_L8:
        do
        {
            if (!iterator.hasNext())
            {
                break MISSING_BLOCK_LABEL_256;
            }
            obj1 = (zzm.zza)iterator.next();
        } while (obj1 == null);
        ((zzk)((com.google.android.gms.location.internal.zzm) (obj)).zzbBW.zzyP()).zza(new zzq(1, 2, null, null, null, ((zzm) (obj1)).asBinder(), null));
        if (true) goto _L8; else goto _L7
_L7:
        obj;
        map;
        JVM INSTR monitorexit ;
        throw obj;
        zzm1;
        JVM INSTR monitorexit ;
        throw obj;
        ((com.google.android.gms.location.internal.zzm) (obj)).zzbCl.clear();
        map;
        JVM INSTR monitorexit ;
        obj = zzbCo;
        flag = ((com.google.android.gms.location.internal.zzm) (obj)).zzbCk;
        if (!flag) goto _L2; else goto _L9
_L9:
        ((com.google.android.gms.location.internal.zzm) (obj)).zzbBW.zzyO();
        ((zzk)((com.google.android.gms.location.internal.zzm) (obj)).zzbBW.zzyP()).zzaK(false);
        obj.zzbCk = false;
          goto _L2
        RemoteException remoteexception;
        remoteexception;
        throw new IllegalStateException(remoteexception);
    }
}
