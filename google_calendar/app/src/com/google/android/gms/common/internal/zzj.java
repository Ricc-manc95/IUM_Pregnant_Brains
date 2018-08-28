// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

// Referenced classes of package com.google.android.gms.common.internal:
//            zzk, zza

public class zzj extends zza
{

    public static final android.os.Parcelable.Creator CREATOR = new zzk();
    public final int version;
    public final int zzaPX;
    public int zzaPY;
    public String zzaPZ;
    public IBinder zzaQa;
    public Scope zzaQb[];
    public Bundle zzaQc;
    public Account zzaQd;
    public long zzaQe;

    public zzj(int i)
    {
        version = 3;
        zzaPY = GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
        zzaPX = i;
    }

    zzj(int i, int j, int k, String s, IBinder ibinder, Scope ascope[], Bundle bundle, 
            Account account, long l)
    {
        version = i;
        zzaPX = j;
        zzaPY = k;
        if ("com.google.android.gms".equals(s))
        {
            zzaPZ = "com.google.android.gms";
        } else
        {
            zzaPZ = s;
        }
        if (i < 2)
        {
            s = null;
            if (ibinder != null)
            {
                s = com.google.android.gms.common.internal.zza.zza(zzr.zza.zzdc(ibinder));
            }
            zzaQd = s;
        } else
        {
            zzaQa = ibinder;
            zzaQd = account;
        }
        zzaQb = ascope;
        zzaQc = bundle;
        zzaQe = l;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        int k = version;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(k);
        k = zzaPX;
        zzc.zzb(parcel, 2, 4);
        parcel.writeInt(k);
        k = zzaPY;
        zzc.zzb(parcel, 3, 4);
        parcel.writeInt(k);
        zzc.zza(parcel, 4, zzaPZ, false);
        zzc.zza(parcel, 5, zzaQa, false);
        zzc.zza(parcel, 6, zzaQb, i, false);
        zzc.zza(parcel, 7, zzaQc, false);
        zzc.zza(parcel, 8, zzaQd, i, false);
        long l = zzaQe;
        zzc.zzb(parcel, 9, 8);
        parcel.writeLong(l);
        zzc.zzJ(parcel, j);
    }

}
