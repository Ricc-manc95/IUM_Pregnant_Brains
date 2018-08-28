// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.support.v4.util.ArrayMap;
import android.support.v4.util.SimpleArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.zzb;
import com.google.android.gms.common.api.zzd;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.zzh;
import java.util.Iterator;
import java.util.Set;

// Referenced classes of package com.google.android.gms.internal:
//            zzyn

public final class zzyp
{

    public final ArrayMap zzaIK = new ArrayMap();
    public final TaskCompletionSource zzaJN = new TaskCompletionSource();
    private int zzaJO;
    private boolean zzaJP;

    public zzyp(Iterable iterable)
    {
        zzaJP = false;
        zzd zzd1;
        for (iterable = iterable.iterator(); iterable.hasNext(); zzaIK.put(zzd1.zzaIT, null))
        {
            zzd1 = (zzd)iterable.next();
        }

        zzaJO = zzaIK.keySet().size();
    }

    public final void zza(zzyn zzyn, ConnectionResult connectionresult)
    {
label0:
        {
            zzaIK.put(zzyn, connectionresult);
            zzaJO = zzaJO - 1;
            boolean flag;
            if (connectionresult.zzaEP == 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                zzaJP = true;
            }
            if (zzaJO == 0)
            {
                if (!zzaJP)
                {
                    break label0;
                }
                zzyn = new zzb(zzaIK);
                zzaJN.zzcAl.setException(zzyn);
            }
            return;
        }
        zzaJN.zzcAl.setResult(null);
    }
}
