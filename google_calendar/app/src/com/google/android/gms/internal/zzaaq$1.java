// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultStore;
import java.util.Set;

// Referenced classes of package com.google.android.gms.internal:
//            zzaaq, zzys

final class zzaNr
    implements b
{

    private final zzaaq zzaNr;

    public final void zzc(zzys zzys)
    {
        zzaNr.zzaNo.remove(zzys);
        if (zzys.zzwD() != null && zzaaq.zza(zzaNr) != null)
        {
            ResultStore resultstore = zzaaq.zza(zzaNr);
            zzys.zzwD().intValue();
            resultstore.remove$514IILG_0();
        }
    }

    Store(zzaaq zzaaq1)
    {
        zzaNr = zzaaq1;
        super();
    }
}
