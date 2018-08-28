// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.zzd;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.zzh;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

// Referenced classes of package com.google.android.gms.internal:
//            zzzp, zzyt, zzyw, zzzk, 
//            zzabu, zzyp, zzzg, zzaaq, 
//            zzys

public final class zzyx
    implements zzzp
{

    private final zzzk zzaIW = zzzk.zzxE();
    public final zzg zzaKD;
    public final Map zzaKE = new HashMap();
    public final Map zzaKF;
    public final zzzg zzaKG;
    public final GoogleApiAvailabilityLight zzaKH;
    public final Condition zzaKI;
    public boolean zzaKJ;
    public Map zzaKK;
    public ConnectionResult zzaKL;
    public final Lock zzaKy;
    private final Looper zzrI;

    public zzyx(Context context, Lock lock, Looper looper, GoogleApiAvailabilityLight googleapiavailabilitylight, Map map, zzg zzg, Map map1, 
            com.google.android.gms.common.api.Api.zza zza1, ArrayList arraylist, zzzg zzzg1)
    {
        zzaKy = lock;
        zzrI = looper;
        zzaKI = lock.newCondition();
        zzaKH = googleapiavailabilitylight;
        zzaKG = zzzg1;
        zzaKF = map1;
        zzaKD = zzg;
        lock = new HashMap();
        for (googleapiavailabilitylight = map1.keySet().iterator(); googleapiavailabilitylight.hasNext();)
        {
            map1 = (Api)googleapiavailabilitylight.next();
            if (((Api) (map1)).zzaII != null)
            {
                lock.put(((Api) (map1)).zzaII, map1);
            } else
            {
                throw new IllegalStateException("This API was constructed with null client keys. This should not be possible.");
            }
        }

        googleapiavailabilitylight = new HashMap();
        map1 = (ArrayList)arraylist;
        int j = map1.size();
        for (int i = 0; i < j;)
        {
            arraylist = ((ArrayList) (map1.get(i)));
            i++;
            arraylist = (zzyt)arraylist;
            googleapiavailabilitylight.put(((zzyt) (arraylist)).zzaGo, arraylist);
        }

        zzyt zzyt1;
        for (map = map.entrySet().iterator(); map.hasNext(); zzaKE.put((com.google.android.gms.common.api.Api.zzc)map1.getKey(), new zzyw(context, arraylist, looper, zzzg1, zzyt1, zzg, zza1)))
        {
            map1 = (java.util.Map.Entry)map.next();
            arraylist = (Api)lock.get(map1.getKey());
            zzzg1 = (com.google.android.gms.common.api.Api.zze)map1.getValue();
            zzyt1 = (zzyt)googleapiavailabilitylight.get(arraylist);
        }

    }

    public final ConnectionResult blockingConnect()
    {
        connect();
        do
        {
            if (!isConnecting())
            {
                break;
            }
            try
            {
                zzaKI.await();
            }
            catch (InterruptedException interruptedexception)
            {
                Thread.currentThread().interrupt();
                return new ConnectionResult(15, null);
            }
        } while (true);
        if (isConnected())
        {
            return ConnectionResult.zzaIj;
        }
        if (zzaKL != null)
        {
            return zzaKL;
        } else
        {
            return new ConnectionResult(13, null);
        }
    }

    public final ConnectionResult blockingConnect(long l, TimeUnit timeunit)
    {
        connect();
        l = timeunit.toNanos(l);
_L2:
        if (!isConnecting())
        {
            break; /* Loop/switch isn't completed */
        }
        if (l > 0L)
        {
            break MISSING_BLOCK_LABEL_38;
        }
        disconnect();
        return new ConnectionResult(14, null);
        l = zzaKI.awaitNanos(l);
        if (true) goto _L2; else goto _L1
        timeunit;
        Thread.currentThread().interrupt();
        return new ConnectionResult(15, null);
_L1:
        if (isConnected())
        {
            return ConnectionResult.zzaIj;
        }
        if (zzaKL != null)
        {
            return zzaKL;
        } else
        {
            return new ConnectionResult(13, null);
        }
    }

    public final void connect()
    {
        zzaKy.lock();
        boolean flag = zzaKJ;
        if (flag)
        {
            zzaKy.unlock();
            return;
        }
        Object obj;
        zza zza1;
        zzabu zzabu1;
        zzyp zzyp1;
        Object obj1;
        zzaKJ = true;
        zzaKK = null;
        zzaKL = null;
        zza1 = new zza();
        zzabu1 = new zzabu(zzrI);
        obj = zzaIW;
        obj1 = zzaKE.values();
        zzyp1 = new zzyp(((Iterable) (obj1)));
        obj1 = ((Iterable) (obj1)).iterator();
_L2:
        Object obj2;
        if (!((Iterator) (obj1)).hasNext())
        {
            break MISSING_BLOCK_LABEL_210;
        }
        obj2 = (zzd)((Iterator) (obj1)).next();
        obj2 = (zzzk.zza)((zzzk) (obj)).zzaKE.get(((zzd) (obj2)).zzaIT);
        if (obj2 == null)
        {
            break; /* Loop/switch isn't completed */
        }
        if (((zzzk.zza) (obj2)).zzaKB.isConnected()) goto _L2; else goto _L1
_L1:
        ((zzzk) (obj)).mHandler.sendMessage(((zzzk) (obj)).mHandler.obtainMessage(1, zzyp1));
        obj = zzyp1.zzaJN.zzcAl;
_L3:
        ((Task) (obj)).addOnSuccessListener(zzabu1, zza1).addOnFailureListener(zzabu1, zza1);
        zzaKy.unlock();
        return;
        zzyp1.zzaJN.zzcAl.setResult(null);
        obj = zzyp1.zzaJN.zzcAl;
          goto _L3
        Exception exception;
        exception;
        zzaKy.unlock();
        throw exception;
    }

    public final void disconnect()
    {
        zzaKy.lock();
        zzaKJ = false;
        zzaKK = null;
        zzaKL = null;
        zzaKI.signalAll();
        zzaKy.unlock();
        return;
        Exception exception;
        exception;
        zzaKy.unlock();
        throw exception;
    }

    public final void dump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
    }

    public final boolean isConnected()
    {
        zzaKy.lock();
        if (zzaKK == null) goto _L2; else goto _L1
_L1:
        ConnectionResult connectionresult = zzaKL;
        if (connectionresult != null) goto _L2; else goto _L3
_L3:
        boolean flag = true;
_L5:
        zzaKy.unlock();
        return flag;
_L2:
        flag = false;
        if (true) goto _L5; else goto _L4
_L4:
        Exception exception;
        exception;
        zzaKy.unlock();
        throw exception;
    }

    public final boolean isConnecting()
    {
        zzaKy.lock();
        if (zzaKK != null) goto _L2; else goto _L1
_L1:
        boolean flag = zzaKJ;
        if (!flag) goto _L2; else goto _L3
_L3:
        flag = true;
_L5:
        zzaKy.unlock();
        return flag;
_L2:
        flag = false;
        if (true) goto _L5; else goto _L4
_L4:
        Exception exception;
        exception;
        zzaKy.unlock();
        throw exception;
    }

    public final zzyq.zza zza(zzyq.zza zza1)
    {
        Object obj = zzaKG.zzaLI;
        ((zzaaq) (obj)).zzaNo.add(zza1);
        obj = ((zzaaq) (obj)).zzaNp;
        ((zzys) (zza1)).zzaKd.set(obj);
        return ((zzd)zzaKE.get(zza1.zzaJQ)).zza(0, zza1);
    }

    public final zzyq.zza zzb(zzyq.zza zza1)
    {
        Object obj = zzaKG.zzaLI;
        ((zzaaq) (obj)).zzaNo.add(zza1);
        obj = ((zzaaq) (obj)).zzaNp;
        ((zzys) (zza1)).zzaKd.set(obj);
        return ((zzd)zzaKE.get(zza1.zzaJQ)).zza(1, zza1);
    }

    private class zza
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
                            hashset.addAll(((com.google.android.gms.common.internal.zzg.zza)map.get(api)).zzalx);
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
            com.google.android.gms.common.api.Api.zzc zzc;
            for (obj = zzaKM.zzaKE.keySet().iterator(); ((Iterator) (obj)).hasNext(); zzaKM.zzaKK.put(((zzd)zzaKM.zzaKE.get(zzc)).zzaIT, ConnectionResult.zzaIj))
            {
                zzc = (com.google.android.gms.common.api.Api.zzc)((Iterator) (obj)).next();
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

        zza()
        {
            zzaKM = zzyx.this;
            super();
        }
    }

}
