// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.googlehelp;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

// Referenced classes of package com.google.android.gms.googlehelp:
//            zzd

public final class OfflineSuggestion extends zza
{

    public static final android.os.Parcelable.Creator CREATOR = new zzd();
    public final int mVersionCode;
    public final String zzFM;
    public final String zzGm;
    public final String zzavt;
    public final String zzbwM;

    OfflineSuggestion(int i, String s, String s1, String s2, String s3)
    {
        mVersionCode = i;
        zzGm = s;
        zzavt = s1;
        zzFM = s2;
        zzbwM = s3;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        i = parcel.dataPosition();
        int j = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(j);
        zzc.zza(parcel, 2, zzGm, false);
        zzc.zza(parcel, 3, zzavt, false);
        zzc.zza(parcel, 4, zzFM, false);
        zzc.zza(parcel, 5, zzbwM, false);
        zzc.zzJ(parcel, i);
    }

}
