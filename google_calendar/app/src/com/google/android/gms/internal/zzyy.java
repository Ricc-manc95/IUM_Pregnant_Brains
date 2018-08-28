// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.zza;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.zzh;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

// Referenced classes of package com.google.android.gms.internal:
//            zzys

public final class zzyy
{

    public final Map zzaKN = Collections.synchronizedMap(new WeakHashMap());
    public final Map zzaKO = Collections.synchronizedMap(new WeakHashMap());

    public zzyy()
    {
    }

    final void zza(boolean flag, Status status)
    {
        Object obj1;
        synchronized (zzaKN)
        {
            obj1 = new HashMap(zzaKN);
        }
        synchronized (zzaKO)
        {
            obj = new HashMap(zzaKO);
        }
        obj1 = ((Map) (obj1)).entrySet().iterator();
        do
        {
            if (!((Iterator) (obj1)).hasNext())
            {
                break;
            }
            obj3 = (java.util.Map.Entry)((Iterator) (obj1)).next();
            if (flag || ((Boolean)((java.util.Map.Entry) (obj3)).getValue()).booleanValue())
            {
                ((zzys)((java.util.Map.Entry) (obj3)).getKey()).zzN(status);
            }
        } while (true);
        break MISSING_BLOCK_LABEL_130;
        status;
        obj;
        JVM INSTR monitorexit ;
        throw status;
        status;
        obj3;
        JVM INSTR monitorexit ;
        throw status;
        Iterator iterator = ((Map) (obj)).entrySet().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Object obj2 = (java.util.Map.Entry)iterator.next();
            if (flag || ((Boolean)((java.util.Map.Entry) (obj2)).getValue()).booleanValue())
            {
                obj2 = (TaskCompletionSource)((java.util.Map.Entry) (obj2)).getKey();
                zza zza1 = new zza(status);
                ((TaskCompletionSource) (obj2)).zzcAl.trySetException(zza1);
            }
        } while (true);
        return;
    }
}
