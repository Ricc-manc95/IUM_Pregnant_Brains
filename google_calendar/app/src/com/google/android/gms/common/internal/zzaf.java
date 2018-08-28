// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

// Referenced classes of package com.google.android.gms.common.internal:
//            zzag

public class zzaf extends zza
{

    public static final android.os.Parcelable.Creator CREATOR = new zzag();
    public final int mVersionCode;
    public boolean zzaLi;
    public IBinder zzaPk;
    public ConnectionResult zzaQR;
    public boolean zzaQS;

    zzaf(int i, IBinder ibinder, ConnectionResult connectionresult, boolean flag, boolean flag1)
    {
        mVersionCode = i;
        zzaPk = ibinder;
        zzaQR = connectionresult;
        zzaLi = flag;
        zzaQS = flag1;
    }

    public boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (!(obj instanceof zzaf))
            {
                return false;
            }
            obj = (zzaf)obj;
            if (!zzaQR.equals(((zzaf) (obj)).zzaQR) || !zzr.zza.zzdc(zzaPk).equals(zzr.zza.zzdc(((zzaf) (obj)).zzaPk)))
            {
                return false;
            }
        }
        return true;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        int k = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(k);
        zzc.zza(parcel, 2, zzaPk, false);
        zzc.zza(parcel, 3, zzaQR, i, false);
        boolean flag1 = zzaLi;
        zzc.zzb(parcel, 4, 4);
        if (flag1)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        flag1 = zzaQS;
        zzc.zzb(parcel, 5, 4);
        if (flag1)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        zzc.zzJ(parcel, j);
    }

}
