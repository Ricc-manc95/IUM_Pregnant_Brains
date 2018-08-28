// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.people.model.AutocompleteBuffer;

// Referenced classes of package com.google.android.gms.internal:
//            zzayj, zzayx

final class zzbCp extends zzayj
{

    private final mpleteBuffer zzbCp;

    public final void zza(int i, Bundle bundle, DataHolder dataholder)
    {
        Object obj = null;
        com.google.android.gms.common.api.Status status = zzayx.zzb(i, null, bundle);
        if (dataholder == null)
        {
            bundle = obj;
        } else
        {
            bundle = new AutocompleteBuffer(dataholder);
        }
        zzbCp.etResult(new <init>(status, bundle));
    }

    public der(der der)
    {
        zzbCp = der;
    }
}
