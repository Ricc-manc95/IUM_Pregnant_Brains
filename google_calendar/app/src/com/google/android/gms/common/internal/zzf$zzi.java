// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.internal;

import com.google.android.gms.common.ConnectionResult;

// Referenced classes of package com.google.android.gms.common.internal:
//            zzf

public final class zzaPH
    implements zzaPH
{

    private final zzf zzaPH;

    public final void zzg(ConnectionResult connectionresult)
    {
        boolean flag;
        if (connectionresult.zzaEP == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            zzaPH.zza(null, zzaPH.zzyR());
        } else
        if (zzf.zzd(zzaPH) != null)
        {
            zzf.zzd(zzaPH).onConnectionFailed(connectionresult);
            return;
        }
    }

    public (zzf zzf1)
    {
        zzaPH = zzf1;
        super();
    }
}
