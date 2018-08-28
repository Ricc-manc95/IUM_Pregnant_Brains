// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.auth;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.internal.zzbz;
import java.io.IOException;

// Referenced classes of package com.google.android.gms.auth:
//            GoogleAuthException, GoogleAuthUtilLight

final class zzagX
    implements a
{

    private final Bundle zzagX;
    private final String zzajD;

    public final Object exec(IBinder ibinder)
        throws RemoteException, IOException, GoogleAuthException
    {
        ibinder = (Bundle)GoogleAuthUtilLight.zzr(com.google.android.gms.internal.X(ibinder).zza(zzajD, zzagX));
        String s = ibinder.getString("Error");
        if (!ibinder.getBoolean("booleanResult"))
        {
            throw new GoogleAuthException(s);
        } else
        {
            return null;
        }
    }

    a(String s, Bundle bundle)
    {
        zzajD = s;
        zzagX = bundle;
        super();
    }
}
