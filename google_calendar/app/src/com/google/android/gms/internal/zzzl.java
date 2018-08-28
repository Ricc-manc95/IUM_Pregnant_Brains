// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Looper;
import com.google.android.gms.common.api.zzd;

// Referenced classes of package com.google.android.gms.internal:
//            zzzc

public final class zzzl extends zzzc
{

    private final zzd zzaMw;

    public zzzl(zzd zzd1)
    {
        super("Method is not supported by connectionless client. APIs supporting connectionless client must not call this method.");
        zzaMw = zzd1;
    }

    public final Looper getLooper()
    {
        return zzaMw.zzrI;
    }

    public final zzyq.zza zza(zzyq.zza zza1)
    {
        return zzaMw.zza(0, zza1);
    }

    public final zzyq.zza zzb(zzyq.zza zza1)
    {
        return zzaMw.zza(1, zza1);
    }
}
