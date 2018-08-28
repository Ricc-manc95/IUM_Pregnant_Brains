// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

// Referenced classes of package com.google.android.gms.internal:
//            zzajd

public class zzajc extends zza
{

    public static final android.os.Parcelable.Creator CREATOR = new zzajd();
    public final int mVersionCode;
    public final ParcelFileDescriptor zzbav;
    public final IBinder zzbbm;
    public final String zzxJ;

    zzajc(int i, ParcelFileDescriptor parcelfiledescriptor, IBinder ibinder, String s)
    {
        mVersionCode = i;
        zzbav = parcelfiledescriptor;
        zzbbm = ibinder;
        zzxJ = s;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        int k = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(k);
        zzc.zza(parcel, 2, zzbav, i | 1, false);
        zzc.zza(parcel, 3, zzbbm, false);
        zzc.zza(parcel, 4, zzxJ, false);
        zzc.zzJ(parcel, j);
    }

}
