// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

// Referenced classes of package com.google.android.gms.common.internal:
//            zzf

public final class zzaPK extends zzaPK
{

    private zzf zzaPJ;
    private final int zzaPK;

    public final void zza(int i, Bundle bundle)
    {
        Log.wtf("GmsClient", "received deprecated onAccountValidationComplete callback, ignoring", new Exception());
    }

    public final void zza(int i, IBinder ibinder, Bundle bundle)
    {
        if (zzaPJ == null)
        {
            throw new NullPointerException(String.valueOf("onPostInitComplete can be called only once per call to getRemoteService"));
        } else
        {
            zzaPJ.zza(i, ibinder, bundle, zzaPK);
            zzaPJ = null;
            return;
        }
    }

    public (zzf zzf1, int i)
    {
        zzaPJ = zzf1;
        zzaPK = i;
    }
}
