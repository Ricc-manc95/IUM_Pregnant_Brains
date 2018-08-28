// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.lang.ref.WeakReference;

// Referenced classes of package com.google.android.gms.internal:
//            zzzg

final class zzaLO extends zzaLO
{

    private WeakReference zzaLO;

    public final void zzwN()
    {
        zzzg zzzg1 = (zzzg)zzaLO.get();
        if (zzzg1 == null)
        {
            return;
        } else
        {
            zzzg.zza(zzzg1);
            return;
        }
    }

    (zzzg zzzg1)
    {
        zzaLO = new WeakReference(zzzg1);
    }
}
