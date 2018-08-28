// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Binder;
import android.os.RemoteException;
import android.util.Log;

// Referenced classes of package com.google.android.gms.common.internal:
//            zzr

public final class zza extends zzr.zza
{

    public static Account zza(zzr zzr1)
    {
        Account account;
        long l;
        account = null;
        if (zzr1 == null)
        {
            break MISSING_BLOCK_LABEL_21;
        }
        l = Binder.clearCallingIdentity();
        account = zzr1.getAccount();
        Binder.restoreCallingIdentity(l);
        return account;
        zzr1;
        Log.w("AccountAccessor", "Remote account accessor probably died");
        Binder.restoreCallingIdentity(l);
        return null;
        zzr1;
        Binder.restoreCallingIdentity(l);
        throw zzr1;
    }

    public final boolean equals(Object obj)
    {
        throw new NoSuchMethodError();
    }

    public final Account getAccount()
    {
        throw new NoSuchMethodError();
    }
}
