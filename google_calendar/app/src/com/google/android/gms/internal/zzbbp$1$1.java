// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.reminders.model.RemindersBuffer;

// Referenced classes of package com.google.android.gms.internal:
//            zzbbg, zzys

final class t> extends zzbbg
{

    private final indersBuffer zzcgr;

    public final void zza(DataHolder dataholder, Status status)
    {
        if (dataholder == null)
        {
            dataholder = null;
        } else
        {
            dataholder = new RemindersBuffer(dataholder);
        }
        zzcgr.zzb(new <init>(dataholder, status));
    }

    der(der der)
    {
        zzcgr = der;
        super();
    }
}
