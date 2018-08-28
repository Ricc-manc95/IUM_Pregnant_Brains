// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.feedback;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

// Referenced classes of package com.google.android.gms.feedback:
//            zze

public class ThemeSettings extends zza
{

    public static final android.os.Parcelable.Creator CREATOR = new zze();
    public final int mVersionCode;
    public int zzbhg;
    public int zzbhh;

    public ThemeSettings()
    {
        this(1, 0, 0);
    }

    ThemeSettings(int i, int j, int k)
    {
        mVersionCode = i;
        zzbhg = j;
        zzbhh = k;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        i = parcel.dataPosition();
        int j = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(j);
        j = zzbhg;
        zzc.zzb(parcel, 2, 4);
        parcel.writeInt(j);
        j = zzbhh;
        zzc.zzb(parcel, 3, 4);
        parcel.writeInt(j);
        zzc.zzJ(parcel, i);
    }

}
