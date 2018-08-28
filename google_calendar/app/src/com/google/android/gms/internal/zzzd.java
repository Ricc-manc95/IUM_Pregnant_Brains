// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.DeadObjectException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzal;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

// Referenced classes of package com.google.android.gms.internal:
//            zzzh, zzzi, zzzg, zzaaq, 
//            zzys

public final class zzzd
    implements zzzh
{

    public final zzzi zzaKV;
    public boolean zzaKW;

    public zzzd(zzzi zzzi1)
    {
        zzaKW = false;
        zzaKV = zzzi1;
    }

    public final void begin()
    {
    }

    public final void connect()
    {
        if (zzaKW)
        {
            zzaKW = false;
            zzzi zzzi1 = zzaKV;
            Object obj = new _cls2(this);
            obj = zzzi1.zzaLQ.obtainMessage(1, obj);
            zzzi1.zzaLQ.sendMessage(((android.os.Message) (obj)));
        }
    }

    public final boolean disconnect()
    {
        if (!zzaKW)
        {
            if (zzaKV.zzaKo.zzxy())
            {
                zzaKW = true;
                Iterator iterator = zzaKV.zzaKo.zzaLH.iterator();
                if (iterator.hasNext())
                {
                    iterator.next();
                    throw new NoSuchMethodError();
                }
            } else
            {
                zzaKV.zzh(null);
                return true;
            }
        }
        return false;
    }

    public final void onConnected(Bundle bundle)
    {
    }

    public final void onConnectionSuspended(int i)
    {
        zzaKV.zzh(null);
        zzaKV.zzaLV.zze(i, zzaKW);
    }

    public final zzyq.zza zza(zzyq.zza zza1)
    {
        return zzb(zza1);
    }

    public final void zza(ConnectionResult connectionresult, Api api, int i)
    {
    }

    public final zzyq.zza zzb(zzyq.zza zza1)
    {
        Object obj2;
        Object obj = zzaKV.zzaKo.zzaLI;
        ((zzaaq) (obj)).zzaNo.add(zza1);
        obj = ((zzaaq) (obj)).zzaNp;
        ((zzys) (zza1)).zzaKd.set(obj);
        obj = zzaKV.zzaKo;
        obj2 = zza1.zzaJQ;
        obj2 = (com.google.android.gms.common.api.Api.zze)((zzzg) (obj)).zzaLC.get(obj2);
        if (obj2 == null)
        {
            zzzi zzzi1;
            try
            {
                throw new NullPointerException(String.valueOf("Appropriate Api was not requested."));
            }
            catch (DeadObjectException deadobjectexception)
            {
                zzzi1 = zzaKV;
            }
            obj2 = new _cls1(this);
            obj2 = zzzi1.zzaLQ.obtainMessage(1, obj2);
            zzzi1.zzaLQ.sendMessage(((android.os.Message) (obj2)));
            return zza1;
        }
        if (((com.google.android.gms.common.api.Api.zze) (obj2)).isConnected() || !zzaKV.zzaLR.containsKey(zza1.zzaJQ))
        {
            break MISSING_BLOCK_LABEL_159;
        }
        zza1.zzM(new Status(17));
        return zza1;
        Object obj1 = obj2;
        if (obj2 instanceof zzal)
        {
            obj1 = zzal.zzzA();
        }
        zza1.zzb(((com.google.android.gms.common.api.Api.zzb) (obj1)));
        return zza1;
    }

    private class _cls2 extends zzzi.zza
    {

        private final zzzd zzaKX;

        public final void zzxm()
        {
            zzaKX.zzaKV.zzaLV.zzu(null);
        }

        _cls2(zzzh zzzh1)
        {
            zzaKX = zzzd.this;
            super(zzzh1);
        }
    }


    private class _cls1 extends zzzi.zza
    {

        private final zzzd zzaKX;

        public final void zzxm()
        {
            zzzd zzzd1 = zzaKX;
            zzzd1.zzaKV.zzh(null);
            zzzd1.zzaKV.zzaLV.zze(1, zzzd1.zzaKW);
        }

        _cls1(zzzh zzzh1)
        {
            zzaKX = zzzd.this;
            super(zzzh1);
        }
    }

}
