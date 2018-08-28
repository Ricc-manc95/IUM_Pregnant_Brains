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
import com.google.android.gms.reminders.LoadRemindersOptions;

// Referenced classes of package com.google.android.gms.internal:
//            zzbbo, zzbbl, zzbbj

final class ApiClient extends zzbbo
{

    private final LoadRemindersOptions zzcgq;

    protected final void zza(com.google.android.gms.common.api.iClient iclient)
        throws RemoteException
    {
        zzbbl zzbbl1 = (zzbbl)iclient;
        class _cls1 extends zzbbg
        {

            private final zzbbp._cls1 zzcgr;

            public final void zza(DataHolder dataholder, Status status)
            {
                if (dataholder == null)
                {
                    dataholder = null;
                } else
                {
                    dataholder = new RemindersBuffer(dataholder);
                }
                zzcgr.zzb(new zzbbp.zzb(dataholder, status));
            }

            _cls1()
            {
                zzcgr = zzbbp._cls1.this;
                super();
            }
        }

        _cls1 _lcls1 = new _cls1();
        Object obj;
        if (zzcgq == null)
        {
            iclient = LoadRemindersOptions.zzcfL;
        } else
        {
            iclient = zzcgq;
        }
        obj = zzbbl1.zzaKD;
        if (((zzg) (obj)).zzafe != null)
        {
            obj = ((zzg) (obj)).zzafe.name;
        } else
        {
            obj = null;
        }
        if (TextUtils.isEmpty(((CharSequence) (obj))))
        {
            throw new IllegalArgumentException("Given String is empty or null");
        } else
        {
            ((zzbbj)zzbbl1.zzyP()).zza(_lcls1, iclient);
            return;
        }
    }

    protected final Result zzb(Status status)
    {
        return new b(null, status);
    }

    ApiClient(GoogleApiClient googleapiclient, LoadRemindersOptions loadremindersoptions)
    {
        zzcgq = loadremindersoptions;
        super(googleapiclient);
    }
}
