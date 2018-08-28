// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.List;

// Referenced classes of package com.google.android.gms.internal:
//            zzake

public class zzakd extends zza
{

    public static final android.os.Parcelable.Creator CREATOR = new zzake();
    public final int mVersionCode;
    public final List zzbbt;

    zzakd(int i, List list)
    {
        mVersionCode = i;
        zzbbt = list;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        i = parcel.dataPosition();
        int j = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(j);
        zzc.zzb(parcel, 2, zzbbt, false);
        zzc.zzJ(parcel, i);
    }

}
