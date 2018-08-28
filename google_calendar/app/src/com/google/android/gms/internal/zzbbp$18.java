// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.accounts.Account;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.reminders.model.Task;
import com.google.android.gms.reminders.model.TaskEntity;

// Referenced classes of package com.google.android.gms.internal:
//            zzbbo, zzbbl, zzbbj

final class piClient extends zzbbo
{

    private final Task zzcgu;

    protected final void zza(com.google.android.gms.common.api.Client client)
        throws RemoteException
    {
        zzbbl zzbbl1 = (zzbbl)client;
        Task task = zzcgu;
        client = zzbbl1.zzaKD;
        if (((zzg) (client)).zzafe != null)
        {
            client = ((zzg) (client)).zzafe.name;
        } else
        {
            client = null;
        }
        if (TextUtils.isEmpty(client))
        {
            throw new IllegalArgumentException("Given String is empty or null");
        }
        if (task == null)
        {
            throw new NullPointerException("null reference");
        } else
        {
            ((zzbbj)zzbbl1.zzyP()).zzc(new (this), new TaskEntity(task));
            return;
        }
    }

    public final Result zzb(Status status)
    {
        return status;
    }

    piClient(GoogleApiClient googleapiclient, Task task)
    {
        zzcgu = task;
        super(googleapiclient);
    }
}
