// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultStore;
import com.google.android.gms.common.api.Status;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

// Referenced classes of package com.google.android.gms.internal:
//            zzys

public final class zzaaq
{

    public static final Status zzaNm = new Status(8, "The connection to Google Play services was lost");
    public static final zzys zzaNn[] = new zzys[0];
    private final Map zzaLC;
    public final Set zzaNo = Collections.synchronizedSet(Collections.newSetFromMap(new WeakHashMap()));
    public final zzb zzaNp = new _cls1();

    public zzaaq(Map map)
    {
        zzaLC = map;
    }

    static ResultStore zza(zzaaq zzaaq1)
    {
        return null;
    }

    public final void release()
    {
        zzys azzys[] = (zzys[])zzaNo.toArray(zzaNn);
        int j = azzys.length;
        int i = 0;
        while (i < j) 
        {
            zzys zzys1 = azzys[i];
            zzys1.zzaKd.set(null);
            if (zzys1.zzwD() == null)
            {
                if (zzys1.zzwO())
                {
                    zzaNo.remove(zzys1);
                }
            } else
            {
                zzys1.setResultCallback(null);
                Object obj = ((com.google.android.gms.common.api.Api.zze)zzaLC.get(((zzyq.zza)zzys1).zzaJQ)).zzwu();
                boolean flag;
                if (zzys1.zztA.getCount() == 0L)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    obj = new zza(zzys1, null, ((IBinder) (obj)));
                    zzys1.zzaKd.set(obj);
                } else
                if (obj != null && ((IBinder) (obj)).isBinderAlive())
                {
                    zza zza1 = new zza(zzys1, null, ((IBinder) (obj)));
                    zzys1.zzaKd.set(zza1);
                    try
                    {
                        ((IBinder) (obj)).linkToDeath(zza1, 0);
                    }
                    catch (RemoteException remoteexception)
                    {
                        zzys1.cancel();
                        zzys1.zzwD().intValue();
                        throw new NullPointerException();
                    }
                } else
                {
                    zzys1.zzaKd.set(null);
                    zzys1.cancel();
                    zzys1.zzwD().intValue();
                    throw new NullPointerException();
                }
                zzaNo.remove(zzys1);
            }
            i++;
        }
    }


    private class _cls1
        implements zzb
    {

        private final zzaaq zzaNr;

        public final void zzc(zzys zzys1)
        {
            zzaNr.zzaNo.remove(zzys1);
            if (zzys1.zzwD() != null && zzaaq.zza(zzaNr) != null)
            {
                ResultStore resultstore = zzaaq.zza(zzaNr);
                zzys1.zzwD().intValue();
                resultstore.remove$514IILG_0();
            }
        }

        _cls1()
        {
            zzaNr = zzaaq.this;
            super();
        }
    }


    private class zza
        implements android.os.IBinder.DeathRecipient, zzb
    {

        private final WeakReference zzaNs;
        private final WeakReference zzaNt;
        private final WeakReference zzaNu;

        private final void zzym()
        {
            Object obj = (zzys)zzaNs.get();
            ResultStore resultstore = (ResultStore)zzaNt.get();
            if (resultstore != null && obj != null)
            {
                ((PendingResult) (obj)).zzwD().intValue();
                resultstore.remove$514IILG_0();
            }
            obj = (IBinder)zzaNu.get();
            if (obj != null)
            {
                ((IBinder) (obj)).unlinkToDeath(this, 0);
            }
        }

        public final void binderDied()
        {
            zzym();
        }

        public final void zzc(zzys zzys1)
        {
            zzym();
        }

        zza(zzys zzys1, ResultStore resultstore, IBinder ibinder)
        {
            zzaNt = new WeakReference(resultstore);
            zzaNs = new WeakReference(zzys1);
            zzaNu = new WeakReference(ibinder);
        }
    }

}
