// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

// Referenced classes of package com.google.android.gms.reminders:
//            zzc

public class zzb extends zza
{

    public static final android.os.Parcelable.Creator CREATOR = new com.google.android.gms.reminders.zzc();
    public static final zzb zzcfK = new zzb(null, null, false);
    public final int mVersionCode;
    public final String zzcfH;
    public final String zzcfI;
    public final boolean zzcfJ;

    zzb(int i, String s, String s1, boolean flag)
    {
        mVersionCode = i;
        zzcfH = s;
        zzcfI = s1;
        zzcfJ = flag;
    }

    private zzb(String s, String s1, boolean flag)
    {
        this(1, null, null, false);
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
        zzc.zza(parcel, 2, zzcfH, false);
        zzc.zza(parcel, 3, zzcfI, false);
        boolean flag = zzcfJ;
        zzc.zzb(parcel, 4, 4);
        if (!flag)
        {
            i = 0;
        }
        parcel.writeInt(i);
        zzc.zzJ(parcel, j);
    }

}
