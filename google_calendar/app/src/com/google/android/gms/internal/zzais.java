// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.drive.zzk;

// Referenced classes of package com.google.android.gms.internal:
//            zzait

public class zzais extends zzk
{

    public static final android.os.Parcelable.Creator CREATOR = new zzait();
    public final int mVersionCode;
    public final DataHolder zzbbk;

    zzais(int i, DataHolder dataholder)
    {
        mVersionCode = i;
        zzbbk = dataholder;
    }

    protected final void zzK(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        int k = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(k);
        zzc.zza(parcel, 2, zzbbk, i, false);
        zzc.zzJ(parcel, j);
    }

}
