// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

// Referenced classes of package com.google.android.gms.drive:
//            zzd

public class DriveFileRange extends zza
{

    public static final android.os.Parcelable.Creator CREATOR = new zzd();
    public final int mVersionCode;
    public final long zzaWO;
    public final long zzaWP;

    DriveFileRange(int i, long l, long l1)
    {
        mVersionCode = i;
        zzaWO = l;
        zzaWP = l1;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        i = parcel.dataPosition();
        int j = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(j);
        long l = zzaWO;
        zzc.zzb(parcel, 2, 8);
        parcel.writeLong(l);
        l = zzaWP;
        zzc.zzb(parcel, 3, 8);
        parcel.writeLong(l);
        zzc.zzJ(parcel, i);
    }

}
