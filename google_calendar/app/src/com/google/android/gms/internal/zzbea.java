// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.common.internal.zzaf;

// Referenced classes of package com.google.android.gms.internal:
//            zzbeb

public class zzbea extends zza
{

    public static final android.os.Parcelable.Creator CREATOR = new zzbeb();
    public final int mVersionCode;
    public final ConnectionResult zzaQR;
    public final zzaf zzcmt;

    public zzbea(int i)
    {
        this(new ConnectionResult(8, null), null);
    }

    zzbea(int i, ConnectionResult connectionresult, zzaf zzaf)
    {
        mVersionCode = i;
        zzaQR = connectionresult;
        zzcmt = zzaf;
    }

    private zzbea(ConnectionResult connectionresult, zzaf zzaf)
    {
        this(1, connectionresult, null);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        int k = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(k);
        zzc.zza(parcel, 2, zzaQR, i, false);
        zzc.zza(parcel, 3, zzcmt, i, false);
        zzc.zzJ(parcel, j);
    }

}
