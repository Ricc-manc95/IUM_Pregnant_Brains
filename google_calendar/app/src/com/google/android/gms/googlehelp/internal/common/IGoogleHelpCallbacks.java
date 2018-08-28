// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.googlehelp.internal.common;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.googlehelp.GoogleHelp;

// Referenced classes of package com.google.android.gms.googlehelp.internal.common:
//            TogglingData

public interface IGoogleHelpCallbacks
    extends IInterface
{

    public abstract void onAsyncPsbdSaved()
        throws RemoteException;

    public abstract void onAsyncPsdSaved()
        throws RemoteException;

    public abstract void onGoogleHelpProcessed(GoogleHelp googlehelp)
        throws RemoteException;

    public abstract void onPipClick()
        throws RemoteException;

    public abstract void onPipInCallingAppDisabled()
        throws RemoteException;

    public abstract void onPipInCallingAppHidden()
        throws RemoteException;

    public abstract void onPipShown()
        throws RemoteException;

    public abstract void onTogglingPipProcessed(TogglingData togglingdata)
        throws RemoteException;
}
