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
import com.google.android.gms.reminders.model.TaskId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.gms.internal:
//            zzbbo, zzbbl, zzbbj

final class piClient extends zzbbo
{

    private final List zzcgC;

    protected final void zza(com.google.android.gms.common.api.Client client)
        throws RemoteException
    {
        zzbbl zzbbl1 = (zzbbl)client;
        Object obj = zzcgC;
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
        client = new ArrayList();
        Task task;
        for (obj = ((List) (obj)).iterator(); ((Iterator) (obj)).hasNext(); client.add(new TaskEntity(task)))
        {
            task = (Task)((Iterator) (obj)).next();
            if (task.getTaskId().getClientAssignedId() == null)
            {
                throw new NullPointerException("null reference");
            }
        }

        ((zzbbj)zzbbl1.zzyP()).zza(new (this), client);
    }

    public final Result zzb(Status status)
    {
        return status;
    }

    piClient(GoogleApiClient googleapiclient, List list)
    {
        zzcgC = list;
        super(googleapiclient);
    }
}
