// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

// Referenced classes of package com.google.android.gms.reminders:
//            zza

public class AccountState extends zza
{

    public static final android.os.Parcelable.Creator CREATOR = new com.google.android.gms.reminders.zza();
    public final int mVersionCode;
    public final boolean zzcfC[];
    public final boolean zzcfD[];

    AccountState(int i, boolean aflag[], boolean aflag1[])
    {
        mVersionCode = i;
        zzcfC = aflag;
        zzcfD = aflag1;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        i = parcel.dataPosition();
        int j = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(j);
        zzc.zza(parcel, 2, zzcfC, false);
        zzc.zza(parcel, 3, zzcfD, false);
        zzc.zzJ(parcel, i);
    }

}
