// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.location.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;

// Referenced classes of package com.google.android.gms.location.internal:
//            zzd

public class zzc extends zza
{

    public static final android.os.Parcelable.Creator CREATOR = new zzd();
    public final int mVersionCode;
    public final String packageName;
    public final int uid;

    zzc(int i, int j, String s)
    {
        mVersionCode = i;
        uid = j;
        packageName = s;
    }

    public boolean equals(Object obj)
    {
        if (obj != this) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (obj == null || !(obj instanceof zzc))
        {
            return false;
        }
        obj = (zzc)obj;
        if (((zzc) (obj)).uid != uid)
        {
            break; /* Loop/switch isn't completed */
        }
        obj = ((zzc) (obj)).packageName;
        String s = packageName;
        boolean flag;
        if (obj == s || obj != null && obj.equals(s))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L1; else goto _L3
_L3:
        return false;
    }

    public int hashCode()
    {
        return uid;
    }

    public String toString()
    {
        return String.format("%d:%s", new Object[] {
            Integer.valueOf(uid), packageName
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        i = parcel.dataPosition();
        int j = uid;
        com.google.android.gms.common.internal.safeparcel.zzc.zzb(parcel, 1, 4);
        parcel.writeInt(j);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 2, packageName, false);
        j = mVersionCode;
        com.google.android.gms.common.internal.safeparcel.zzc.zzb(parcel, 1000, 4);
        parcel.writeInt(j);
        com.google.android.gms.common.internal.safeparcel.zzc.zzJ(parcel, i);
    }

}
