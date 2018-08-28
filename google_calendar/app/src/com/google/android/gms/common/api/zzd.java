// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.api;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.internal.zzaaa;
import com.google.android.gms.internal.zzaah;
import com.google.android.gms.internal.zzaaj;
import com.google.android.gms.internal.zzym;
import com.google.android.gms.internal.zzyn;
import com.google.android.gms.internal.zzys;
import com.google.android.gms.internal.zzzk;
import com.google.android.gms.internal.zzzl;
import java.util.concurrent.atomic.AtomicInteger;

// Referenced classes of package com.google.android.gms.common.api:
//            Api, GoogleApiClient

public class zzd
{

    private final Context mContext;
    public final int mId;
    private final Api zzaGo;
    private final Api.ApiOptions zzaIS;
    public final zzyn zzaIT;
    public final GoogleApiClient zzaIU;
    public final zzzk zzaIW;
    public final Looper zzrI;

    public zzd(Context context, Api api, Looper looper)
    {
        if (context == null)
        {
            throw new NullPointerException(String.valueOf("Null context is not permitted."));
        }
        if (api == null)
        {
            throw new NullPointerException(String.valueOf("Api must not be null."));
        }
        if (looper == null)
        {
            throw new NullPointerException(String.valueOf("Looper must not be null."));
        } else
        {
            mContext = context.getApplicationContext();
            zzaGo = api;
            zzaIS = null;
            zzrI = looper;
            zzaIT = new zzyn(api);
            zzaIU = new zzzl(this);
            zzaIW = zzzk.zzaz(mContext);
            mId = zzaIW.zzaMe.getAndIncrement();
            new zzym();
            return;
        }
    }

    private zzd(Context context, Api api, Api.ApiOptions apioptions, Looper looper, zzaaj zzaaj)
    {
        if (context == null)
        {
            throw new NullPointerException(String.valueOf("Null context is not permitted."));
        }
        if (api == null)
        {
            throw new NullPointerException(String.valueOf("Api must not be null."));
        }
        if (looper == null)
        {
            throw new NullPointerException(String.valueOf("Looper must not be null."));
        } else
        {
            mContext = context.getApplicationContext();
            zzaGo = api;
            zzaIS = apioptions;
            zzrI = looper;
            zzaIT = new zzyn(zzaGo, zzaIS);
            zzaIU = new zzzl(this);
            zzaIW = zzzk.zzaz(mContext);
            mId = zzaIW.zzaMe.getAndIncrement();
            context = zzaIW;
            ((zzzk) (context)).mHandler.sendMessage(((zzzk) (context)).mHandler.obtainMessage(5, this));
            return;
        }
    }

    public zzd(Context context, Api api, Api.ApiOptions apioptions, zzaaj zzaaj)
    {
        if (Looper.myLooper() != null)
        {
            apioptions = Looper.myLooper();
        } else
        {
            apioptions = Looper.getMainLooper();
        }
        this(context, api, null, ((Looper) (apioptions)), zzaaj);
    }

    public Api.zze buildApiClient(Looper looper, com.google.android.gms.internal.zzzk.zza zza1)
    {
        return zzaGo.zzwr().zza(mContext, looper, (new GoogleApiClient.Builder(mContext)).zzwB(), zzaIS, zza1, zza1);
    }

    public zzaah createSignInCoordinator(Context context, Handler handler)
    {
        return new zzaah(context, handler);
    }

    public final com.google.android.gms.internal.zzyq.zza zza(int i, com.google.android.gms.internal.zzyq.zza zza1)
    {
        zzzk zzzk1;
        com.google.android.gms.internal.zzyl.zzb zzb;
        boolean flag;
        if (((zzys) (zza1)).zzaKk || ((Boolean)zzys.zzaJY.get()).booleanValue())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        zza1.zzaKk = flag;
        zzzk1 = zzaIW;
        zzb = new com.google.android.gms.internal.zzyl.zzb(i, zza1);
        zzzk1.mHandler.sendMessage(zzzk1.mHandler.obtainMessage(3, new zzaaa(zzb, zzzk1.zzaMf.get(), this)));
        return zza1;
    }
}
