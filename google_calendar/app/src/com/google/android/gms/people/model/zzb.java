// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.people.model;

import android.os.Parcel;

// Referenced classes of package com.google.android.gms.people.model:
//            AvatarReference

public final class zzb
    implements android.os.Parcelable.Creator
{

    public zzb()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        int j = 0;
        int k = com.google.android.gms.common.internal.safeparcel.zzb.zzdE(parcel);
        String s = null;
        int i = 0;
        do
        {
            if (parcel.dataPosition() < k)
            {
                int l = parcel.readInt();
                switch (0xffff & l)
                {
                default:
                    com.google.android.gms.common.internal.safeparcel.zzb.zzb(parcel, l);
                    break;

                case 1: // '\001'
                    com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, l, 4);
                    j = parcel.readInt();
                    break;

                case 2: // '\002'
                    s = com.google.android.gms.common.internal.safeparcel.zzb.zzq(parcel, l);
                    break;

                case 1000: 
                    com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, l, 4);
                    i = parcel.readInt();
                    break;
                }
            } else
            {
                if (parcel.dataPosition() != k)
                {
                    throw new com.google.android.gms.common.internal.safeparcel.zza((new StringBuilder(37)).append("Overread allowed size end=").append(k).toString(), parcel);
                }
                return new AvatarReference(i, j, s);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new AvatarReference[i];
    }
}
