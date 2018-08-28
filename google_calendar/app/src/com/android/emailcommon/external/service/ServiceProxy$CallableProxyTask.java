// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.emailcommon.external.service;

import android.os.RemoteException;
import java.util.concurrent.Callable;

public abstract class result
    implements result, Callable
{

    public Object result;

    public abstract Object call()
        throws RemoteException;

    public final void run()
        throws RemoteException
    {
        result = call();
    }

    protected ()
    {
        result = null;
    }
}
