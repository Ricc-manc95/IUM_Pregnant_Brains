// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.ParcelFileDescriptor;

// Referenced classes of package com.google.android.gms.internal:
//            zzayj, zzayx

final class zzbCp extends zzayj
{

    private final <init> zzbCp;

    public final void zza(int i, Bundle bundle, ParcelFileDescriptor parcelfiledescriptor, Bundle bundle1)
    {
        int j = 0;
        bundle = zzayx.zzb(i, null, bundle);
        boolean flag;
        if (bundle1 != null)
        {
            flag = bundle1.getBoolean("rewindable");
            i = bundle1.getInt("width");
            j = bundle1.getInt("height");
        } else
        {
            i = 0;
            flag = false;
        }
        zzbCp.tResult(new <init>(bundle, parcelfiledescriptor, flag, i, j));
    }

    public ( )
    {
        zzbCp = ;
    }
}
