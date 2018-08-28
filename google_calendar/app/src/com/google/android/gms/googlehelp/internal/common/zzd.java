// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.googlehelp.internal.common;

import android.graphics.Bitmap;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.googlehelp.GoogleHelp;

// Referenced classes of package com.google.android.gms.googlehelp.internal.common:
//            IGoogleHelpCallbacks

public interface zzd
    extends IInterface
{

    public abstract void zza(GoogleHelp googlehelp, Bitmap bitmap, IGoogleHelpCallbacks igooglehelpcallbacks)
        throws RemoteException;
}
