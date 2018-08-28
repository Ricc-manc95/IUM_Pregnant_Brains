// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.firebase.jobdispatcher;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;

// Referenced classes of package com.firebase.jobdispatcher:
//            IJobCallback

public interface IRemoteJobService
    extends IInterface
{

    public abstract void start(Bundle bundle, IJobCallback ijobcallback)
        throws RemoteException;

    public abstract void stop(Bundle bundle, boolean flag)
        throws RemoteException;
}
