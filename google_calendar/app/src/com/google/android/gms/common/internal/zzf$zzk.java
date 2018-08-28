// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;

// Referenced classes of package com.google.android.gms.common.internal:
//            zzf

public final class <init> extends <init>
{

    private final zzf zzaPH;

    protected final void zzn(ConnectionResult connectionresult)
    {
        zzaPH.zzaPw.zzg(connectionresult);
        zzf zzf1 = zzaPH;
        zzf1.zzaPr = connectionresult.zzaEP;
        zzf1.zzaPs = System.currentTimeMillis();
    }

    protected final boolean zzyS()
    {
        zzaPH.zzaPw.zzg(ConnectionResult.zzaIj);
        return true;
    }

    public (zzf zzf1, int i, Bundle bundle)
    {
        zzaPH = zzf1;
        super(zzf1, i, bundle);
    }
}
