// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;

public interface zzbz
    extends IInterface
{

    public abstract Bundle zza(Account account, String s, Bundle bundle)
        throws RemoteException;

    public abstract Bundle zza(String s, Bundle bundle)
        throws RemoteException;
}
