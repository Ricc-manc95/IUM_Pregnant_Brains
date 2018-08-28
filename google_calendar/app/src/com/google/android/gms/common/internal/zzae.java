// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Parcel;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.internal.safeparcel.zzb;

// Referenced classes of package com.google.android.gms.common.internal:
//            zzad

public final class zzae
    implements android.os.Parcelable.Creator
{

    public zzae()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        int k = zzb.zzdE(parcel);
        int j = 0;
        Account account = null;
        int i = 0;
        GoogleSignInAccount googlesigninaccount = null;
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
                    account = (Account)zzb.zza(parcel, l, Account.CREATOR);
                    break;

                case 3: // '\003'
                    zzb.zza(parcel, l, 4);
                    j = parcel.readInt();
                    break;

                case 4: // '\004'
                    googlesigninaccount = (GoogleSignInAccount)zzb.zza(parcel, l, GoogleSignInAccount.CREATOR);
                    break;
                }
            } else
            if (parcel.dataPosition() != k)
            {
                throw new com.google.android.gms.common.internal.safeparcel.zzb.zza((new StringBuilder(37)).append("Overread allowed size end=").append(k).toString(), parcel);
            } else
            {
                return new zzad(i, account, j, googlesigninaccount);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new zzad[i];
    }
}
