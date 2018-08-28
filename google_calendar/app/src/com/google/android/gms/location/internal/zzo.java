// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.location.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.location.LocationRequest;
import java.util.Collections;
import java.util.List;

// Referenced classes of package com.google.android.gms.location.internal:
//            zzp

public class zzo extends zza
{

    public static final android.os.Parcelable.Creator CREATOR = new zzp();
    public static final List zzbCq = Collections.emptyList();
    public String mTag;
    public final int mVersionCode;
    public List zzbAF;
    public boolean zzbAt;
    public boolean zzbCr;
    public boolean zzbCs;
    public LocationRequest zzbkR;

    zzo(int i, LocationRequest locationrequest, boolean flag, List list, String s, boolean flag1, boolean flag2)
    {
        mVersionCode = i;
        zzbkR = locationrequest;
        zzbAt = flag;
        zzbAF = list;
        mTag = s;
        zzbCr = flag1;
        zzbCs = flag2;
    }

    public boolean equals(Object obj)
    {
        if (obj instanceof zzo)
        {
            obj = (zzo)obj;
            LocationRequest locationrequest = zzbkR;
            LocationRequest locationrequest1 = ((zzo) (obj)).zzbkR;
            boolean flag;
            if (locationrequest == locationrequest1 || locationrequest != null && locationrequest.equals(locationrequest1))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag && zzbAt == ((zzo) (obj)).zzbAt && zzbCr == ((zzo) (obj)).zzbCr)
            {
                List list = zzbAF;
                List list1 = ((zzo) (obj)).zzbAF;
                if (list == list1 || list != null && list.equals(list1))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag && zzbCs == ((zzo) (obj)).zzbCs)
                {
                    return true;
                }
            }
        }
        return false;
    }

    public int hashCode()
    {
        return zzbkR.hashCode();
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(zzbkR.toString());
        if (mTag != null)
        {
            stringbuilder.append(" tag=").append(mTag);
        }
        stringbuilder.append(" trigger=").append(zzbAt);
        stringbuilder.append(" hideAppOps=").append(zzbCr);
        stringbuilder.append(" clients=").append(zzbAF);
        stringbuilder.append(" forceCoarseLocation=").append(zzbCs);
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        zzc.zza(parcel, 1, zzbkR, i, false);
        boolean flag1 = zzbAt;
        zzc.zzb(parcel, 4, 4);
        if (flag1)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        zzc.zzc(parcel, 5, zzbAF, false);
        zzc.zza(parcel, 6, mTag, false);
        flag1 = zzbCr;
        zzc.zzb(parcel, 7, 4);
        if (flag1)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        i = mVersionCode;
        zzc.zzb(parcel, 1000, 4);
        parcel.writeInt(i);
        flag1 = zzbCs;
        zzc.zzb(parcel, 8, 4);
        if (flag1)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        zzc.zzJ(parcel, j);
    }

}
