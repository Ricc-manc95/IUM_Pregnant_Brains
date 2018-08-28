// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

// Referenced classes of package com.google.android.gms.drive:
//            zzc, DriveId

public class zzb extends zza
{

    public static final android.os.Parcelable.Creator CREATOR = new com.google.android.gms.drive.zzc();
    public final int mVersionCode;
    public final ParcelFileDescriptor zzaOG;
    public final int zzaWF;
    public final int zzaWG;
    public final DriveId zzaWH;
    public final boolean zzaWI;
    public final String zzxJ;

    zzb(int i, ParcelFileDescriptor parcelfiledescriptor, int j, int k, DriveId driveid, boolean flag, String s)
    {
        mVersionCode = i;
        zzaOG = parcelfiledescriptor;
        zzaWF = j;
        zzaWG = k;
        zzaWH = driveid;
        zzaWI = flag;
        zzxJ = s;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        int k = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(k);
        zzc.zza(parcel, 2, zzaOG, i, false);
        k = zzaWF;
        zzc.zzb(parcel, 3, 4);
        parcel.writeInt(k);
        k = zzaWG;
        zzc.zzb(parcel, 4, 4);
        parcel.writeInt(k);
        zzc.zza(parcel, 5, zzaWH, i, false);
        boolean flag1 = zzaWI;
        zzc.zzb(parcel, 7, 4);
        if (flag1)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        zzc.zza(parcel, 8, zzxJ, false);
        zzc.zzJ(parcel, j);
    }

}
