// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import android.os.TransactionTooLargeException;
import com.google.android.gms.common.api.Status;

// Referenced classes of package com.google.android.gms.internal:
//            zzyy

public abstract class zzyl
{

    public zzyl(int i)
    {
    }

    static Status zzd(RemoteException remoteexception)
    {
        StringBuilder stringbuilder = new StringBuilder();
        if (remoteexception instanceof TransactionTooLargeException)
        {
            stringbuilder.append("TransactionTooLargeException: ");
        }
        stringbuilder.append(remoteexception.getLocalizedMessage());
        return new Status(8, stringbuilder.toString());
    }

    public abstract void zzK(Status status);

    public abstract void zza(zzyy zzyy, boolean flag);

    public abstract void zza(zzzk.zza zza1)
        throws DeadObjectException;
}
