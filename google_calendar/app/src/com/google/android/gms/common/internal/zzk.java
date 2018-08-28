// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Parcel;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zzb;

// Referenced classes of package com.google.android.gms.common.internal:
//            zzj

public final class zzk
    implements android.os.Parcelable.Creator
{

    public zzk()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        int i = 0;
        Account account = null;
        int l = zzb.zzdE(parcel);
        long l1 = 0L;
        android.os.Bundle bundle = null;
        Scope ascope[] = null;
        android.os.IBinder ibinder = null;
        String s = null;
        int j = 0;
        int k = 0;
        do
        {
            if (parcel.dataPosition() < l)
            {
                int i1 = parcel.readInt();
                switch (0xffff & i1)
                {
                default:
                    zzb.zzb(parcel, i1);
                    break;

                case 1: // '\001'
                    zzb.zza(parcel, i1, 4);
                    k = parcel.readInt();
                    break;

                case 2: // '\002'
                    zzb.zza(parcel, i1, 4);
                    j = parcel.readInt();
                    break;

                case 3: // '\003'
                    zzb.zza(parcel, i1, 4);
                    i = parcel.readInt();
                    break;

                case 4: // '\004'
                    s = zzb.zzq(parcel, i1);
                    break;

                case 5: // '\005'
                    ibinder = zzb.zzr(parcel, i1);
                    break;

                case 6: // '\006'
                    ascope = (Scope[])zzb.zzb(parcel, i1, Scope.CREATOR);
                    break;

                case 7: // '\007'
                    bundle = zzb.zzs(parcel, i1);
                    break;

                case 8: // '\b'
                    account = (Account)zzb.zza(parcel, i1, Account.CREATOR);
                    break;

                case 9: // '\t'
                    zzb.zza(parcel, i1, 8);
                    l1 = parcel.readLong();
                    break;
                }
            } else
            {
                if (parcel.dataPosition() != l)
                {
                    throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(l).toString(), parcel);
                }
                return new zzj(k, j, i, s, ibinder, ascope, bundle, account, l1);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new zzj[i];
    }
}
