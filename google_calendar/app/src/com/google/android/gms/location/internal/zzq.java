// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.location.internal;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.location.zzm;
import com.google.android.gms.location.zzn;

// Referenced classes of package com.google.android.gms.location.internal:
//            zzr, zzi, zzo

public class zzq extends zza
{

    public static final android.os.Parcelable.Creator CREATOR = new zzr();
    public PendingIntent mPendingIntent;
    public final int mVersionCode;
    public int zzbCt;
    public zzo zzbCu;
    public zzn zzbCv;
    public zzm zzbCw;
    public zzi zzbCx;

    zzq(int i, int j, zzo zzo, IBinder ibinder, PendingIntent pendingintent, IBinder ibinder1, IBinder ibinder2)
    {
        Object obj;
        obj = null;
        super();
        mVersionCode = i;
        zzbCt = j;
        zzbCu = zzo;
        if (ibinder == null)
        {
            zzo = null;
        } else
        if (ibinder == null)
        {
            zzo = null;
        } else
        {
            zzo = ibinder.queryLocalInterface("com.google.android.gms.location.ILocationListener");
            if (zzo != null && (zzo instanceof zzn))
            {
                zzo = (zzn)zzo;
            } else
            {
                zzo = new com.google.android.gms.location.zzn.zza.zza(ibinder);
            }
        }
        zzbCv = zzo;
        mPendingIntent = pendingintent;
        if (ibinder1 == null)
        {
            zzo = null;
        } else
        if (ibinder1 == null)
        {
            zzo = null;
        } else
        {
            zzo = ibinder1.queryLocalInterface("com.google.android.gms.location.ILocationCallback");
            if (zzo != null && (zzo instanceof zzm))
            {
                zzo = (zzm)zzo;
            } else
            {
                zzo = new com.google.android.gms.location.zzm.zza.zza(ibinder1);
            }
        }
        zzbCw = zzo;
        if (ibinder2 != null) goto _L2; else goto _L1
_L1:
        zzo = obj;
_L4:
        zzbCx = zzo;
        return;
_L2:
        zzo = obj;
        if (ibinder2 != null)
        {
            zzo = ibinder2.queryLocalInterface("com.google.android.gms.location.internal.IFusedLocationProviderCallback");
            if (zzo != null && (zzo instanceof zzi))
            {
                zzo = (zzi)zzo;
            } else
            {
                zzo = new zzi.zza.zza(ibinder2);
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        Object obj = null;
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        int k = zzbCt;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(k);
        zzc.zza(parcel, 2, zzbCu, i, false);
        IBinder ibinder;
        if (zzbCv == null)
        {
            ibinder = null;
        } else
        {
            ibinder = zzbCv.asBinder();
        }
        zzc.zza(parcel, 3, ibinder, false);
        zzc.zza(parcel, 4, mPendingIntent, i, false);
        if (zzbCw == null)
        {
            ibinder = null;
        } else
        {
            ibinder = zzbCw.asBinder();
        }
        zzc.zza(parcel, 5, ibinder, false);
        if (zzbCx == null)
        {
            ibinder = obj;
        } else
        {
            ibinder = zzbCx.asBinder();
        }
        zzc.zza(parcel, 6, ibinder, false);
        i = mVersionCode;
        zzc.zzb(parcel, 1000, 4);
        parcel.writeInt(i);
        zzc.zzJ(parcel, j);
    }

}
