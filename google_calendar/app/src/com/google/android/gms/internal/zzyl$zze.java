// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.zza;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.zzh;
import java.util.Map;

// Referenced classes of package com.google.android.gms.internal:
//            zzaac, zzyy

public final class zzaJF extends zzaJF
{

    private final ception zzaJF;

    public final volatile void zzK(Status status)
    {
        super.zzK(status);
    }

    public final volatile void zza(zzyy zzyy, boolean flag)
    {
    }

    public final void zzb(zzK zzk)
        throws RemoteException
    {
        if ((zzaac)zzk.zzaMn.remove(zzaJF) != null)
        {
            throw new NoSuchMethodError();
        } else
        {
            Log.wtf("UnregisterListenerTask", "Received call to unregister a listener without a matching registration call.", new Exception());
            zzk = zzakX;
            zza zza1 = new zza(Status.zzaJv);
            ((TaskCompletionSource) (zzk)).zzcAl.trySetException(zza1);
            return;
        }
    }

    public onSource(onSource onsource, TaskCompletionSource taskcompletionsource)
    {
        super(4, taskcompletionsource);
        zzaJF = onsource;
    }
}
