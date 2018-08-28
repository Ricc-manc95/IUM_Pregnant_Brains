// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.zzg;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.android.gms.internal:
//            zzze, zzzi, zzzg

final class zzaLr extends zzaLr
{

    private final zzze zzaLl;
    private final ArrayList zzaLr;

    public final void zzxm()
    {
        zzzg zzzg1 = zzaLl.zzaKV.zzaKo;
        zzze zzze1 = zzaLl;
        Object obj;
        int j;
        if (zzze1.zzaKD == null)
        {
            obj = Collections.emptySet();
        } else
        {
            obj = new HashSet(zzze1.zzaKD.zzaIZ);
            Map map = zzze1.zzaKD.zzaPN;
            Iterator iterator = map.keySet().iterator();
            while (iterator.hasNext()) 
            {
                Api api = (Api)iterator.next();
                Map map1 = zzze1.zzaKV.zzaLR;
                if (api.zzaII != null)
                {
                    if (!map1.containsKey(api.zzaII))
                    {
                        ((Set) (obj)).addAll(((com.google.android.gms.common.internal.)map.get(api)).zalx);
                    }
                } else
                {
                    throw new IllegalStateException("This API was constructed with null client keys. This should not be possible.");
                }
            }
        }
        zzzg1.zzaLD = ((Set) (obj));
        obj = (ArrayList)zzaLr;
        j = ((ArrayList) (obj)).size();
        for (int i = 0; i < j;)
        {
            Object obj1 = ((ArrayList) (obj)).get(i);
            i++;
            ((com.google.android.gms.common.api.aLr)obj1).za(zzaLl.zzaLh, zzaLl.zzaKV.zzaKo.zzaLD);
        }

    }

    public g.zza(zzze zzze1, ArrayList arraylist)
    {
        zzaLl = zzze1;
        super(zzze1);
        zzaLr = arraylist;
    }
}
