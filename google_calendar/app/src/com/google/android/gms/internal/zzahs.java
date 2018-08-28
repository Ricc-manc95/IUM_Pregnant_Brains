// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.List;

// Referenced classes of package com.google.android.gms.internal:
//            zzaht

public class zzahs extends zza
{

    public static final android.os.Parcelable.Creator CREATOR = new zzaht();
    public final int mVersionCode;
    public final int zzHB;
    public final List zzbas;

    zzahs(int i, List list, int j)
    {
        mVersionCode = i;
        zzbas = list;
        zzHB = j;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        i = parcel.dataPosition();
        int j = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(j);
        zzc.zzc(parcel, 2, zzbas, false);
        j = zzHB;
        zzc.zzb(parcel, 3, 4);
        parcel.writeInt(j);
        zzc.zzJ(parcel, i);
    }

}
