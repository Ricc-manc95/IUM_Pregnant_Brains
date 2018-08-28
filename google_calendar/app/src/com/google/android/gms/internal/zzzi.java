// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.zzg;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

// Referenced classes of package com.google.android.gms.internal:
//            zzyu, zzzp, zzyt, zzzf, 
//            zzzh, zzzd, zzze, zzys, 
//            zzzg

public final class zzzi
    implements zzyu, zzzp
{

    public final Context mContext;
    public final com.google.android.gms.common.api.Api.zza zzaJk;
    public final zzg zzaKD;
    public final Map zzaKF;
    public final GoogleApiAvailabilityLight zzaKH;
    public final zzzg zzaKo;
    public final Lock zzaKy;
    public final Map zzaLC;
    public final Condition zzaLP;
    public final zzb zzaLQ;
    public final Map zzaLR = new HashMap();
    public volatile zzzh zzaLS;
    private ConnectionResult zzaLT;
    public int zzaLU;
    public final zzzp.zza zzaLV;

    public zzzi(Context context, zzzg zzzg, Lock lock, Looper looper, GoogleApiAvailabilityLight googleapiavailabilitylight, Map map, zzg zzg, 
            Map map1, com.google.android.gms.common.api.Api.zza zza1, ArrayList arraylist, zzzp.zza zza2)
    {
        zzaLT = null;
        mContext = context;
        zzaKy = lock;
        zzaKH = googleapiavailabilitylight;
        zzaLC = map;
        zzaKD = zzg;
        zzaKF = map1;
        zzaJk = zza1;
        zzaKo = zzzg;
        zzaLV = zza2;
        context = (ArrayList)arraylist;
        int j = context.size();
        for (int i = 0; i < j;)
        {
            zzzg = ((zzzg) (context.get(i)));
            i++;
            ((zzyt)zzzg).zzaKn = this;
        }

        zzaLQ = new zzb(looper);
        zzaLP = lock.newCondition();
        zzaLS = new zzzf(this);
    }

    public final ConnectionResult blockingConnect()
    {
        zzaLS.connect();
        do
        {
            if (!isConnecting())
            {
                break;
            }
            try
            {
                zzaLP.await();
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
        if (zzaLT != null)
        {
            return zzaLT;
        } else
        {
            return new ConnectionResult(13, null);
        }
    }

    public final ConnectionResult blockingConnect(long l, TimeUnit timeunit)
    {
        zzaLS.connect();
        l = timeunit.toNanos(l);
_L2:
        if (!isConnecting())
        {
            break; /* Loop/switch isn't completed */
        }
        if (l > 0L)
        {
            break MISSING_BLOCK_LABEL_60;
        }
        if (zzaLS.disconnect())
        {
            zzaLR.clear();
        }
        return new ConnectionResult(14, null);
        l = zzaLP.awaitNanos(l);
        if (true) goto _L2; else goto _L1
        timeunit;
        Thread.currentThread().interrupt();
        return new ConnectionResult(15, null);
_L1:
        if (isConnected())
        {
            return ConnectionResult.zzaIj;
        }
        if (zzaLT != null)
        {
            return zzaLT;
        } else
        {
            return new ConnectionResult(13, null);
        }
    }

    public final void connect()
    {
        zzaLS.connect();
    }

    public final void disconnect()
    {
        if (zzaLS.disconnect())
        {
            zzaLR.clear();
        }
    }

    public final void dump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        filedescriptor = String.valueOf(s).concat("  ");
        printwriter.append(s).append("mState=").println(zzaLS);
        for (as = zzaKF.keySet().iterator(); as.hasNext();)
        {
            Api api = (Api)as.next();
            printwriter.append(s).append(api.mName).println(":");
            Map map = zzaLC;
            if (api.zzaII != null)
            {
                ((com.google.android.gms.common.api.Api.zze)map.get(api.zzaII)).dump$5166KOBMC4NMOOBECSNL6T3ID5N6EEQCD9GNCO9FD5NIUHJ9DHIK8PBJCDP6IS3KDTP3MJ3AC5R62BR9DSNL0SJ9DPQ5ESJ9EHIN4EQR9HL62TJ15TM62RJ75T9N8SJ9DPJJMAAM0(filedescriptor, printwriter);
            } else
            {
                throw new IllegalStateException("This API was constructed with null client keys. This should not be possible.");
            }
        }

    }

    public final boolean isConnected()
    {
        return zzaLS instanceof zzzd;
    }

    public final boolean isConnecting()
    {
        return zzaLS instanceof zzze;
    }

    public final void onConnected(Bundle bundle)
    {
        zzaKy.lock();
        zzaLS.onConnected(bundle);
        zzaKy.unlock();
        return;
        bundle;
        zzaKy.unlock();
        throw bundle;
    }

    public final void onConnectionSuspended(int i)
    {
        zzaKy.lock();
        zzaLS.onConnectionSuspended(i);
        zzaKy.unlock();
        return;
        Exception exception;
        exception;
        zzaKy.unlock();
        throw exception;
    }

    public final zzyq.zza zza(zzyq.zza zza1)
    {
        boolean flag;
        if (((zzys) (zza1)).zzaKk || ((Boolean)zzys.zzaJY.get()).booleanValue())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        zza1.zzaKk = flag;
        return zzaLS.zza(zza1);
    }

    public final void zza(ConnectionResult connectionresult, Api api, int i)
    {
        zzaKy.lock();
        zzaLS.zza(connectionresult, api, i);
        zzaKy.unlock();
        return;
        connectionresult;
        zzaKy.unlock();
        throw connectionresult;
    }

    public final zzyq.zza zzb(zzyq.zza zza1)
    {
        boolean flag;
        if (((zzys) (zza1)).zzaKk || ((Boolean)zzys.zzaJY.get()).booleanValue())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        zza1.zzaKk = flag;
        return zzaLS.zzb(zza1);
    }

    final void zzh(ConnectionResult connectionresult)
    {
        zzaKy.lock();
        zzaLT = connectionresult;
        zzaLS = new zzzf(this);
        zzaLS.begin();
        zzaLP.signalAll();
        zzaKy.unlock();
        return;
        connectionresult;
        zzaKy.unlock();
        throw connectionresult;
    }

    private class zzb extends Handler
    {

        private final zzzi zzaLX;

        public final void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 2: default 28
        //                       1 63
        //                       2 138;
               goto _L1 _L2 _L3
_L1:
            int i = message.what;
            Log.w("GACStateManager", (new StringBuilder(31)).append("Unknown message id: ").append(i).toString());
            return;
_L2:
            zza zza1;
            zza1 = (zza)message.obj;
            message = zzaLX;
            ((zzzi) (message)).zzaKy.lock();
            zzzh zzzh1;
            zzzh zzzh2;
            zzzh1 = ((zzzi) (message)).zzaLS;
            zzzh2 = zza1.zzaLW;
            if (zzzh1 != zzzh2)
            {
                ((zzzi) (message)).zzaKy.unlock();
                return;
            }
            zza1.zzxm();
            ((zzzi) (message)).zzaKy.unlock();
            return;
            Exception exception;
            exception;
            ((zzzi) (message)).zzaKy.unlock();
            throw exception;
_L3:
            throw (RuntimeException)message.obj;
        }

        zzb(Looper looper)
        {
            zzaLX = zzzi.this;
            super(looper);
        }

        private class zza
        {

            public final zzzh zzaLW;

            protected abstract void zzxm();

            protected zza(zzzh zzzh1)
            {
                zzaLW = zzzh1;
            }
        }

    }

}
