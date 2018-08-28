// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.googlehelp.internal.common;

import android.content.Intent;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

// Referenced classes of package com.google.android.gms.googlehelp.internal.common:
//            zzf

public final class OverflowMenuItem extends zza
{

    public static final android.os.Parcelable.Creator CREATOR = new zzf();
    public final int mId;
    public final Intent mIntent;
    public final int mVersionCode;
    public final String zzavt;

    OverflowMenuItem(int i, int j, String s, Intent intent)
    {
        mVersionCode = i;
        mId = j;
        zzavt = s;
        mIntent = intent;
    }

    public OverflowMenuItem(int i, String s, Intent intent)
    {
        this(1, i, s, intent);
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        int k = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(k);
        k = mId;
        zzc.zzb(parcel, 2, 4);
        parcel.writeInt(k);
        zzc.zza(parcel, 3, zzavt, false);
        zzc.zza(parcel, 4, mIntent, i, false);
        zzc.zzJ(parcel, j);
    }

}
