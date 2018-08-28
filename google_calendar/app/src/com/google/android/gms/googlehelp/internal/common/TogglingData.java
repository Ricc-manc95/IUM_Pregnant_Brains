// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.googlehelp.internal.common;

import android.os.Parcel;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

// Referenced classes of package com.google.android.gms.googlehelp.internal.common:
//            zzi

public class TogglingData extends zza
    implements ReflectedParcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new zzi();
    public final int mVersionCode;
    public String zzbwe;
    public String zzbxa;
    public String zzbxb;

    private TogglingData()
    {
        mVersionCode = 1;
    }

    TogglingData(int i, String s, String s1, String s2)
    {
        mVersionCode = i;
        zzbwe = s;
        zzbxa = s1;
        zzbxb = s2;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        i = parcel.dataPosition();
        int j = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(j);
        zzc.zza(parcel, 2, zzbwe, false);
        zzc.zza(parcel, 3, zzbxa, false);
        zzc.zza(parcel, 4, zzbxb, false);
        zzc.zzJ(parcel, i);
    }

}
