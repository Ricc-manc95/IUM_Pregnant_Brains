// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.drive.zzk;

// Referenced classes of package com.google.android.gms.internal:
//            zzair

public class zzaiq extends zzk
{

    public static final android.os.Parcelable.Creator CREATOR = new zzair();
    public final int mVersionCode;
    public final boolean zzaZg;
    public final DataHolder zzbbj;

    zzaiq(int i, DataHolder dataholder, boolean flag)
    {
        mVersionCode = i;
        zzbbj = dataholder;
        zzaZg = flag;
    }

    protected final void zzK(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        int k = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(k);
        zzc.zza(parcel, 2, zzbbj, i, false);
        boolean flag1 = zzaZg;
        zzc.zzb(parcel, 3, 4);
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
