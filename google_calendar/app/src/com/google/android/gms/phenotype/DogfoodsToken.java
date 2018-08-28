// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.phenotype;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

// Referenced classes of package com.google.android.gms.phenotype:
//            zzc

public class DogfoodsToken extends zza
{

    public static final android.os.Parcelable.Creator CREATOR = new com.google.android.gms.phenotype.zzc();
    public final int mVersionCode;
    public final byte token[];

    DogfoodsToken(int i, byte abyte0[])
    {
        mVersionCode = i;
        token = abyte0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        i = parcel.dataPosition();
        int j = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(j);
        zzc.zza(parcel, 2, token, false);
        zzc.zzJ(parcel, i);
    }

}
