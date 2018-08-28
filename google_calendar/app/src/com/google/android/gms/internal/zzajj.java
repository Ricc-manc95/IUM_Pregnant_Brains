// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.query.internal.FilterHolder;

// Referenced classes of package com.google.android.gms.internal:
//            zzajk

public class zzajj extends zza
{

    public static final android.os.Parcelable.Creator CREATOR = new zzajk();
    public final int mVersionCode;
    public final String zzaXi[];
    public final DriveId zzaXk;
    public final String zzavt;
    public final FilterHolder zzbbp;

    zzajj(int i, String s, String as[], DriveId driveid, FilterHolder filterholder)
    {
        mVersionCode = i;
        zzavt = s;
        zzaXi = as;
        zzaXk = driveid;
        zzbbp = filterholder;
    }

    public zzajj(String s, String as[], DriveId driveid, FilterHolder filterholder)
    {
        this(1, s, as, driveid, filterholder);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        int k = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(k);
        zzc.zza(parcel, 2, zzavt, false);
        zzc.zza(parcel, 3, zzaXi, false);
        zzc.zza(parcel, 4, zzaXk, i, false);
        zzc.zza(parcel, 5, zzbbp, i, false);
        zzc.zzJ(parcel, j);
    }

}
