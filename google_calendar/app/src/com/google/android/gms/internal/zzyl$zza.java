// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.zza;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.zzh;

// Referenced classes of package com.google.android.gms.internal:
//            zzyl, zzyy

abstract class zzakX extends zzyl
{

    public final TaskCompletionSource zzakX;

    public void zzK(Status status)
    {
        TaskCompletionSource taskcompletionsource = zzakX;
        status = new zza(status);
        taskcompletionsource.zzcAl.trySetException(status);
    }

    public void zza(zzyy zzyy, boolean flag)
    {
    }

    public final void zza(ception ception)
        throws DeadObjectException
    {
        try
        {
            zzb(ception);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (ception ception)
        {
            zzK(zzyl.zzd(ception));
            throw ception;
        }
        // Misplaced declaration of an exception variable
        catch (ception ception)
        {
            zzK(zzyl.zzd(ception));
        }
    }

    protected abstract void zzb(zzb zzb1)
        throws RemoteException;

    public onSource(int i, TaskCompletionSource taskcompletionsource)
    {
        super(4);
        zzakX = taskcompletionsource;
    }
}
