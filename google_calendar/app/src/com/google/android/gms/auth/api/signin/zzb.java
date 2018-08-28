// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import com.google.android.gms.common.api.Scope;

// Referenced classes of package com.google.android.gms.auth.api.signin:
//            GoogleSignInOptions

public final class zzb
    implements android.os.Parcelable.Creator
{

    public zzb()
    {
    }

    public final Object createFromParcel(Parcel parcel)
    {
        int k = com.google.android.gms.common.internal.safeparcel.zzb.zzdE(parcel);
        int i = 0;
        java.util.ArrayList arraylist = null;
        Account account = null;
        boolean flag2 = false;
        boolean flag1 = false;
        boolean flag = false;
        String s1 = null;
        String s = null;
        do
        {
            if (parcel.dataPosition() < k)
            {
                int j = parcel.readInt();
                switch (0xffff & j)
                {
                default:
                    if ((0xffff0000 & j) != 0xffff0000)
                    {
                        j = j >> 16 & 0xffff;
                    } else
                    {
                        j = parcel.readInt();
                    }
                    parcel.setDataPosition(j + parcel.dataPosition());
                    break;

                case 1: // '\001'
                    com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, j, 4);
                    i = parcel.readInt();
                    break;

                case 2: // '\002'
                    arraylist = com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, j, Scope.CREATOR);
                    break;

                case 3: // '\003'
                    account = (Account)com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, j, Account.CREATOR);
                    break;

                case 4: // '\004'
                    com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, j, 4);
                    if (parcel.readInt() != 0)
                    {
                        flag2 = true;
                    } else
                    {
                        flag2 = false;
                    }
                    break;

                case 5: // '\005'
                    com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, j, 4);
                    if (parcel.readInt() != 0)
                    {
                        flag1 = true;
                    } else
                    {
                        flag1 = false;
                    }
                    break;

                case 6: // '\006'
                    com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, j, 4);
                    if (parcel.readInt() != 0)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    break;

                case 7: // '\007'
                    s1 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(parcel, j);
                    break;

                case 8: // '\b'
                    s = com.google.android.gms.common.internal.safeparcel.zzb.zzq(parcel, j);
                    break;
                }
            } else
            if (parcel.dataPosition() != k)
            {
                throw new com.google.android.gms.common.internal.safeparcel.zza((new StringBuilder(37)).append("Overread allowed size end=").append(k).toString(), parcel);
            } else
            {
                return new GoogleSignInOptions(i, arraylist, account, flag2, flag1, flag, s1, s);
            }
        } while (true);
    }

    public final Object[] newArray(int i)
    {
        return new GoogleSignInOptions[i];
    }
}
