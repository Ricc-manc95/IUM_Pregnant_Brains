// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive;

import android.os.Parcel;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

// Referenced classes of package com.google.android.gms.drive:
//            zzj

public class UserMetadata extends zza
    implements ReflectedParcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new zzj();
    public final int mVersionCode;
    public final String zzaXw;
    public final String zzaXx;
    public final boolean zzaXy;
    public final String zzaXz;
    public final String zzahI;

    UserMetadata(int i, String s, String s1, String s2, boolean flag, String s3)
    {
        mVersionCode = i;
        zzaXw = s;
        zzahI = s1;
        zzaXx = s2;
        zzaXy = flag;
        zzaXz = s3;
    }

    public String toString()
    {
        return String.format("Permission ID: '%s', Display Name: '%s', Picture URL: '%s', Authenticated User: %b, Email: '%s'", new Object[] {
            zzaXw, zzahI, zzaXx, Boolean.valueOf(zzaXy), zzaXz
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        i = 1;
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        int k = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(k);
        zzc.zza(parcel, 2, zzaXw, false);
        zzc.zza(parcel, 3, zzahI, false);
        zzc.zza(parcel, 4, zzaXx, false);
        boolean flag = zzaXy;
        zzc.zzb(parcel, 5, 4);
        if (!flag)
        {
            i = 0;
        }
        parcel.writeInt(i);
        zzc.zza(parcel, 6, zzaXz, false);
        zzc.zzJ(parcel, j);
    }

}
