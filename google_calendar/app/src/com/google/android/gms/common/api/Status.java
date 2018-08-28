// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.api;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.Arrays;

// Referenced classes of package com.google.android.gms.common.api:
//            Result, zzh, CommonStatusCodes

public final class Status extends zza
    implements Result, ReflectedParcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new zzh();
    public static final Status zzaJt = new Status(0);
    public static final Status zzaJu = new Status(14);
    public static final Status zzaJv = new Status(8);
    public static final Status zzaJw = new Status(15);
    public static final Status zzaJx = new Status(16);
    public final PendingIntent mPendingIntent;
    public final int mVersionCode;
    public final int zzaEP;
    public final String zzaIk;

    public Status(int i)
    {
        this(i, null);
    }

    Status(int i, int j, String s, PendingIntent pendingintent)
    {
        mVersionCode = i;
        zzaEP = j;
        zzaIk = s;
        mPendingIntent = pendingintent;
    }

    public Status(int i, String s)
    {
        this(1, i, s, null);
    }

    public Status(int i, String s, PendingIntent pendingintent)
    {
        this(1, i, s, pendingintent);
    }

    public final boolean equals(Object obj)
    {
        if (obj instanceof Status)
        {
            if (mVersionCode == ((Status) (obj = (Status)obj)).mVersionCode && zzaEP == ((Status) (obj)).zzaEP)
            {
                String s = zzaIk;
                String s1 = ((Status) (obj)).zzaIk;
                boolean flag;
                if (s == s1 || s != null && s.equals(s1))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    PendingIntent pendingintent = mPendingIntent;
                    obj = ((Status) (obj)).mPendingIntent;
                    if (pendingintent == obj || pendingintent != null && pendingintent.equals(obj))
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public final Status getStatus()
    {
        return this;
    }

    public final int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            Integer.valueOf(mVersionCode), Integer.valueOf(zzaEP), zzaIk, mPendingIntent
        });
    }

    public final String toString()
    {
        com.google.android.gms.common.internal.zzaa.zza zza1 = new com.google.android.gms.common.internal.zzaa.zza(this);
        String s;
        if (zzaIk != null)
        {
            s = zzaIk;
        } else
        {
            s = CommonStatusCodes.getStatusCodeString(zzaEP);
        }
        return zza1.zzh("statusCode", s).zzh("resolution", mPendingIntent).toString();
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        int k = zzaEP;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(k);
        Object obj = zzaIk;
        if (obj != null)
        {
            parcel.writeInt(-65534);
            parcel.writeInt(0);
            int l = parcel.dataPosition();
            parcel.writeString(((String) (obj)));
            int j1 = parcel.dataPosition();
            parcel.setDataPosition(l - 4);
            parcel.writeInt(j1 - l);
            parcel.setDataPosition(j1);
        }
        obj = mPendingIntent;
        if (obj != null)
        {
            parcel.writeInt(-65533);
            parcel.writeInt(0);
            int i1 = parcel.dataPosition();
            ((Parcelable) (obj)).writeToParcel(parcel, i);
            i = parcel.dataPosition();
            parcel.setDataPosition(i1 - 4);
            parcel.writeInt(i - i1);
            parcel.setDataPosition(i);
        }
        i = mVersionCode;
        zzc.zzb(parcel, 1000, 4);
        parcel.writeInt(i);
        i = parcel.dataPosition();
        parcel.setDataPosition(j - 4);
        parcel.writeInt(i - j);
        parcel.setDataPosition(i);
    }

    static 
    {
        new Status(17);
        new Status(18);
    }
}
