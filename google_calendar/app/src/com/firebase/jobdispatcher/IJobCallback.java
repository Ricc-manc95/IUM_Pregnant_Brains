// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.firebase.jobdispatcher;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;

public interface IJobCallback
    extends IInterface
{

    public abstract void jobFinished(Bundle bundle, int i)
        throws RemoteException;
}
