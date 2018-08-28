// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.Collections;
import java.util.List;

// Referenced classes of package com.google.android.gms.internal:
//            zzaij

public class zzaii extends zza
{

    public static final android.os.Parcelable.Creator CREATOR = new zzaij();
    public final int mVersionCode;
    public final int zzIZ;
    public final long zzbba;
    public final long zzbbb;
    public final List zzbbc;

    zzaii(int i, long l, long l1, int j, List list)
    {
        mVersionCode = i;
        zzbba = l;
        zzbbb = l1;
        zzIZ = j;
        zzbbc = list;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        i = parcel.dataPosition();
        int j = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(j);
        long l = zzbba;
        zzc.zzb(parcel, 2, 8);
        parcel.writeLong(l);
        l = zzbbb;
        zzc.zzb(parcel, 3, 8);
        parcel.writeLong(l);
        j = zzIZ;
        zzc.zzb(parcel, 4, 4);
        parcel.writeInt(j);
        zzc.zzc(parcel, 5, zzbbc, false);
        zzc.zzJ(parcel, i);
    }

    static 
    {
        Collections.emptyList();
    }
}
