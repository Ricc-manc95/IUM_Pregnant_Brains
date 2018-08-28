// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.people.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

// Referenced classes of package com.google.android.gms.people.model:
//            zzb

public final class AvatarReference extends zza
    implements ReflectedParcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new zzb();
    public final int mVersionCode;
    public final int zzAY;
    public final String zzbVD;

    AvatarReference(int i, int j, String s)
    {
        boolean flag;
        if (j != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException();
        } else
        {
            mVersionCode = i;
            zzAY = j;
            zzbVD = s;
            return;
        }
    }

    public AvatarReference(int i, String s)
    {
        this(1, i, s);
    }

    public final String toString()
    {
        return (new com.google.android.gms.common.internal.zzaa.zza(this)).zzh("source", Integer.valueOf(zzAY)).zzh("location", zzbVD).toString();
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        i = parcel.dataPosition();
        int j = zzAY;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(j);
        zzc.zza(parcel, 2, zzbVD, false);
        j = mVersionCode;
        zzc.zzb(parcel, 1000, 4);
        parcel.writeInt(j);
        zzc.zzJ(parcel, i);
    }

}
