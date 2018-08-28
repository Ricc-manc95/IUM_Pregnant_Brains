// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.identity.accounts.api;

import android.os.Parcel;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

// Referenced classes of package com.google.android.gms.identity.accounts.api:
//            zza

public final class AccountData extends zza
{

    public static final android.os.Parcelable.Creator CREATOR = new com.google.android.gms.identity.accounts.api.zza();
    public final int mVersionCode;
    public final String zzajr;
    public final String zzbxJ;

    public AccountData(int i, String s, String s1)
    {
        if (TextUtils.isEmpty(s))
        {
            throw new IllegalArgumentException(String.valueOf("Account name must not be empty."));
        } else
        {
            mVersionCode = i;
            zzajr = s;
            zzbxJ = s1;
            return;
        }
    }

    private AccountData(String s, String s1)
    {
        this(1, s, null);
    }

    public static AccountData forAccount(String s)
    {
        if (TextUtils.isEmpty(s))
        {
            throw new IllegalArgumentException(String.valueOf("Account name must not be empty."));
        } else
        {
            return new AccountData(s, null);
        }
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        i = parcel.dataPosition();
        zzc.zza(parcel, 1, zzajr, false);
        zzc.zza(parcel, 2, zzbxJ, false);
        int j = mVersionCode;
        zzc.zzb(parcel, 1000, 4);
        parcel.writeInt(j);
        zzc.zzJ(parcel, i);
    }

}
