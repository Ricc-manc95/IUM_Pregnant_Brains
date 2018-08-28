// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.reminders.CreateReminderOptions;
import com.google.android.gms.reminders.model.Task;
import com.google.android.gms.reminders.zzb;

// Referenced classes of package com.google.android.gms.internal:
//            zzbbo, zzbbl, zzzv

final class piClient extends zzbbo
{

    private final zzzv zzcgA;
    private final CreateReminderOptions zzcgB;
    private final Task zzcgu;

    protected final void zza(com.google.android.gms.common.api.Client client)
        throws RemoteException
    {
        client = (zzbbl)client;
        if (zzcgB == null)
        {
            client.zza(this, zzcgu, null, zzb.zzcfK);
            return;
        } else
        {
            client = zzcgu;
            client = zzcgA;
            throw new NoSuchMethodError();
        }
    }

    public final Result zzb(Status status)
    {
        return status;
    }

    piClient(GoogleApiClient googleapiclient, CreateReminderOptions createreminderoptions, Task task, zzzv zzzv)
    {
        zzcgB = createreminderoptions;
        zzcgu = task;
        zzcgA = zzzv;
        super(googleapiclient);
    }
}
