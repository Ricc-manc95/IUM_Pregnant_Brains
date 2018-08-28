// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.drive.ChangeSequenceNumber;
import com.google.android.gms.drive.zzk;
import java.util.List;

// Referenced classes of package com.google.android.gms.internal:
//            zzaid

public class zzaic extends zzk
{

    public static final android.os.Parcelable.Creator CREATOR = new zzaid();
    public final int mVersionCode;
    public final List zzaZc;
    public final ChangeSequenceNumber zzaZd;
    public final boolean zzaZe;
    public final DataHolder zzbaW;

    zzaic(int i, DataHolder dataholder, List list, ChangeSequenceNumber changesequencenumber, boolean flag)
    {
        mVersionCode = i;
        zzbaW = dataholder;
        zzaZc = list;
        zzaZd = changesequencenumber;
        zzaZe = flag;
    }

    protected final void zzK(Parcel parcel, int i)
    {
        boolean flag = true;
        i |= 1;
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        int k = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(k);
        zzc.zza(parcel, 2, zzbaW, i, false);
        zzc.zzc(parcel, 3, zzaZc, false);
        zzc.zza(parcel, 4, zzaZd, i, false);
        boolean flag1 = zzaZe;
        zzc.zzb(parcel, 5, 4);
        if (flag1)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        zzc.zzJ(parcel, j);
    }

}
