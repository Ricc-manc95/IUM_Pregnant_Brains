// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.zzb;
import com.google.android.gms.common.api.zzd;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

// Referenced classes of package com.google.android.gms.internal:
//            zzyx, zzzg

final class zzaKM
    implements OnFailureListener, OnSuccessListener
{

    private final zzyx zzaKM;

    private final void zzxf()
    {
        if (zzaKM.zzaKD == null)
        {
            zzaKM.zzaKG.zzaLD = Collections.emptySet();
            return;
        }
        HashSet hashset = new HashSet(zzaKM.zzaKD.zzaIZ);
        Map map = zzaKM.zzaKD.zzaPN;
        for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();)
        {
            Api api = (Api)iterator.next();
            Object obj = zzaKM.zzaKK;
            Map map1 = zzaKM.zzaKE;
            if (api.zzaII != null)
            {
                obj = (ConnectionResult)((Map) (obj)).get(((zzd)map1.get(api.zzaII)).zzaIT);
                if (obj != null)
                {
                    boolean flag;
                    if (((ConnectionResult) (obj)).zzaEP == 0)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag)
                    {
                        hashset.addAll(((com.google.android.gms.common.internal.zaEP)map.get(api)).zalx);
                    }
                }
            } else
            {
                throw new IllegalStateException("This API was constructed with null client keys. This should not be possible.");
            }
        }

        zzaKM.zzaKG.zzaLD = hashset;
    }

    public final void onFailure(Exception exception)
    {
        Object obj;
        obj = null;
        exception = (zzb)exception;
        zzaKM.zzaKy.lock();
        zzyx zzyx1;
        Iterator iterator;
        zzaKM.zzaKK = ((zzb) (exception)).zzaIK;
        zzyx1 = zzaKM;
        iterator = zzaKM.zzaKF.keySet().iterator();
        int i;
        i = 0;
        exception = ((Exception) (obj));
_L3:
        Api api;
        if (!iterator.hasNext())
        {
            break MISSING_BLOCK_LABEL_262;
        }
        api = (Api)iterator.next();
        obj = zzaKM.zzaKK;
        Map map = zzaKM.zzaKE;
        if (api.zzaII == null)
        {
            break MISSING_BLOCK_LABEL_225;
        }
        obj = (ConnectionResult)((Map) (obj)).get(((zzd)map.get(api.zzaII)).zzaIT);
        int j;
        if (((ConnectionResult) (obj)).zzaEP == 0)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j != 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        j = ((Integer)zzaKM.zzaKF.get(api)).intValue();
        if (j == 2)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (j != 1)
        {
            break MISSING_BLOCK_LABEL_348;
        }
        if (((ConnectionResult) (obj)).zzaEP != 0 && ((ConnectionResult) (obj)).mPendingIntent != null)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j != 0)
        {
            break MISSING_BLOCK_LABEL_348;
        }
        if (!zzaKM.zzaKH.isUserResolvableError(((ConnectionResult) (obj)).zzaEP))
        {
            continue; /* Loop/switch isn't completed */
        }
        break MISSING_BLOCK_LABEL_348;
        throw new IllegalStateException("This API was constructed with null client keys. This should not be possible.");
        exception;
        zzaKM.zzaKy.unlock();
        throw exception;
        zzyx1.zzaKL = exception;
        if (zzaKM.zzaKL != null)
        {
            break MISSING_BLOCK_LABEL_317;
        }
        zzxf();
        zzaKM.zzaKG.zzu(null);
_L1:
        zzaKM.zzaKI.signalAll();
        zzaKM.zzaKy.unlock();
        return;
        zzaKM.zzaKJ = false;
        zzaKM.zzaKG.zzc(zzaKM.zzaKL);
          goto _L1
        if (exception == null || i > 0x7fffffff)
        {
            i = 0x7fffffff;
            exception = ((Exception) (obj));
        }
        if (true) goto _L3; else goto _L2
_L2:
    }

    public final void onSuccess(Object obj)
    {
        zzaKM.zzaKy.lock();
        zzaKM.zzaKK = new ArrayMap(zzaKM.zzaKE.size());
        com.google.android.gms.common.api.aKM akm;
        for (obj = zzaKM.zzaKE.keySet().iterator(); ((Iterator) (obj)).hasNext(); zzaKM.zzaKK.put(((zzd)zzaKM.zzaKE.get(akm)).zzaIT, ConnectionResult.zzaIj))
        {
            akm = (com.google.android.gms.common.api.ult.zzaIj)((Iterator) (obj)).next();
        }

        break MISSING_BLOCK_LABEL_128;
        obj;
        zzaKM.zzaKy.unlock();
        throw obj;
        zzxf();
        zzaKM.zzaKG.zzu(null);
        zzaKM.zzaKI.signalAll();
        zzaKM.zzaKy.unlock();
        return;
    }

    (zzyx zzyx1)
    {
        zzaKM = zzyx1;
        super();
    }
}
