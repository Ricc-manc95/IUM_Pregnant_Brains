// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.googlehelp.internal.common;

import android.content.Intent;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;

// Referenced classes of package com.google.android.gms.googlehelp.internal.common:
//            OverflowMenuItem

public final class zzf
    implements android.os.Parcelable.Creator
{

    public zzf()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        Intent intent = null;
        int k = zzb.zzdE(parcel);
        int j = 0;
        int i = 0;
        String s = null;
        do
        {
            if (parcel.dataPosition() < k)
            {
                int l = parcel.readInt();
                switch (0xffff & l)
                {
                default:
                    zzb.zzb(parcel, l);
                    break;

                case 1: // '\001'
                    zzb.zza(parcel, l, 4);
                    i = parcel.readInt();
                    break;

                case 2: // '\002'
                    zzb.zza(parcel, l, 4);
                    j = parcel.readInt();
                    break;

                case 3: // '\003'
                    s = zzb.zzq(parcel, l);
                    break;

                case 4: // '\004'
                    intent = (Intent)zzb.zza(parcel, l, Intent.CREATOR);
                    break;
                }
            } else
            if (parcel.dataPosition() != k)
            {
                throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(k).toString(), parcel);
            } else
            {
                return new OverflowMenuItem(i, j, s, intent);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new OverflowMenuItem[i];
    }
}
