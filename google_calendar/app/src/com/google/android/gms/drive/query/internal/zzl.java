// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzc;

// Referenced classes of package com.google.android.gms.drive.query.internal:
//            zza, zzm, zzj

public class zzl extends zza
{

    public static final android.os.Parcelable.Creator CREATOR = new zzm();
    public final String mValue;
    public final int mVersionCode;

    zzl(int i, String s)
    {
        mVersionCode = i;
        mValue = s;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        i = parcel.dataPosition();
        zzc.zza(parcel, 1, mValue, false);
        int j = mVersionCode;
        zzc.zzb(parcel, 1000, 4);
        parcel.writeInt(j);
        zzc.zzJ(parcel, i);
    }

    public final Object zza(zzj zzj1)
    {
        return zzj1.zzdB(mValue);
    }

}
