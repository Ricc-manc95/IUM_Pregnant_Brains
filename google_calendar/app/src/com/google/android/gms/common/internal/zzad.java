// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Parcel;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

// Referenced classes of package com.google.android.gms.common.internal:
//            zzae

public class zzad extends zza
{

    public static final android.os.Parcelable.Creator CREATOR = new zzae();
    public final int mVersionCode;
    public final int zzaQP;
    public final GoogleSignInAccount zzaQQ;
    public final Account zzafe;

    zzad(int i, Account account, int j, GoogleSignInAccount googlesigninaccount)
    {
        mVersionCode = i;
        zzafe = account;
        zzaQP = j;
        zzaQQ = googlesigninaccount;
    }

    public zzad(Account account, int i, GoogleSignInAccount googlesigninaccount)
    {
        this(2, account, i, googlesigninaccount);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        int k = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(k);
        zzc.zza(parcel, 2, zzafe, i, false);
        k = zzaQP;
        zzc.zzb(parcel, 3, 4);
        parcel.writeInt(k);
        zzc.zza(parcel, 4, zzaQQ, i, false);
        zzc.zzJ(parcel, j);
    }

}
