// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.fitness.data;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.Arrays;

// Referenced classes of package com.google.android.gms.fitness.data:
//            zzx, Application

public class Session extends zza
{

    public static final android.os.Parcelable.Creator CREATOR = new zzx();
    public final String mDescription;
    public final String mName;
    public final int mVersionCode;
    public final long zzadP;
    public final long zzbhw;
    public final int zzbhx;
    public final String zzbiB;
    public final Application zzbiC;
    public final Long zzbiD;

    Session(int i, long l, long l1, String s, String s1, 
            String s2, int j, Application application, Long long1)
    {
        mVersionCode = i;
        zzadP = l;
        zzbhw = l1;
        mName = s;
        zzbiB = s1;
        mDescription = s2;
        zzbhx = j;
        zzbiC = application;
        zzbiD = long1;
    }

    public boolean equals(Object obj)
    {
        boolean flag2 = false;
        if (obj == this) goto _L2; else goto _L1
_L1:
        boolean flag1 = flag2;
        if (!(obj instanceof Session)) goto _L4; else goto _L3
_L3:
        obj = (Session)obj;
        if (zzadP != ((Session) (obj)).zzadP || zzbhw != ((Session) (obj)).zzbhw) goto _L6; else goto _L5
_L5:
        boolean flag;
        Object obj1 = mName;
        Object obj2 = ((Session) (obj)).mName;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L6; else goto _L7
_L7:
        obj1 = zzbiB;
        obj2 = ((Session) (obj)).zzbiB;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L6; else goto _L8
_L8:
        obj1 = mDescription;
        obj2 = ((Session) (obj)).mDescription;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L6; else goto _L9
_L9:
        obj1 = zzbiC;
        obj2 = ((Session) (obj)).zzbiC;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag || zzbhx != ((Session) (obj)).zzbhx) goto _L6; else goto _L10
_L10:
        flag = true;
_L12:
        flag1 = flag2;
        if (!flag) goto _L4; else goto _L2
_L2:
        flag1 = true;
_L4:
        return flag1;
_L6:
        flag = false;
        if (true) goto _L12; else goto _L11
_L11:
    }

    public int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            Long.valueOf(zzadP), Long.valueOf(zzbhw), zzbiB
        });
    }

    public String toString()
    {
        return (new com.google.android.gms.common.internal.zzaa.zza(this)).zzh("startTime", Long.valueOf(zzadP)).zzh("endTime", Long.valueOf(zzbhw)).zzh("name", mName).zzh("identifier", zzbiB).zzh("description", mDescription).zzh("activity", Integer.valueOf(zzbhx)).zzh("application", zzbiC).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        long l = zzadP;
        zzc.zzb(parcel, 1, 8);
        parcel.writeLong(l);
        l = zzbhw;
        zzc.zzb(parcel, 2, 8);
        parcel.writeLong(l);
        zzc.zza(parcel, 3, mName, false);
        zzc.zza(parcel, 4, zzbiB, false);
        zzc.zza(parcel, 5, mDescription, false);
        int k = zzbhx;
        zzc.zzb(parcel, 7, 4);
        parcel.writeInt(k);
        k = mVersionCode;
        zzc.zzb(parcel, 1000, 4);
        parcel.writeInt(k);
        zzc.zza(parcel, 8, zzbiC, i, false);
        Long long1 = zzbiD;
        if (long1 != null)
        {
            zzc.zzb(parcel, 9, 8);
            parcel.writeLong(long1.longValue());
        }
        zzc.zzJ(parcel, j);
    }

}
