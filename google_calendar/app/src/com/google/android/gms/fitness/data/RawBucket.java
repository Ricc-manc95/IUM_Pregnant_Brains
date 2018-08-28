// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.fitness.data;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.gms.fitness.data:
//            zzt, Bucket, DataSet, RawDataSet, 
//            Session

public final class RawBucket extends zza
{

    public static final android.os.Parcelable.Creator CREATOR = new zzt();
    public final int mVersionCode;
    public final long zzadP;
    public final boolean zzbhA;
    public final Session zzbhn;
    public final long zzbhw;
    public final List zzbhy;
    public final int zzbhz;
    public final int zzbiy;

    public RawBucket(int i, long l, long l1, Session session, int j, 
            List list, int k, boolean flag)
    {
        mVersionCode = i;
        zzadP = l;
        zzbhw = l1;
        zzbhn = session;
        zzbiy = j;
        zzbhy = list;
        zzbhz = k;
        zzbhA = flag;
    }

    public RawBucket(Bucket bucket, List list, List list1)
    {
        mVersionCode = 2;
        zzadP = TimeUnit.MILLISECONDS.convert(bucket.zzadP, TimeUnit.MILLISECONDS);
        zzbhw = TimeUnit.MILLISECONDS.convert(bucket.zzbhw, TimeUnit.MILLISECONDS);
        zzbhn = bucket.zzbhn;
        zzbiy = bucket.zzbhx;
        zzbhz = bucket.zzbhz;
        zzbhA = bucket.serverHasMoreData();
        bucket = bucket.zzbhy;
        zzbhy = new ArrayList(bucket.size());
        DataSet dataset;
        for (bucket = bucket.iterator(); bucket.hasNext(); zzbhy.add(new RawDataSet(dataset, list, list1)))
        {
            dataset = (DataSet)bucket.next();
        }

    }

    public final boolean equals(Object obj)
    {
        boolean flag2 = false;
        if (this == obj) goto _L2; else goto _L1
_L1:
        boolean flag1 = flag2;
        if (!(obj instanceof RawBucket)) goto _L4; else goto _L3
_L3:
        obj = (RawBucket)obj;
        if (zzadP != ((RawBucket) (obj)).zzadP || zzbhw != ((RawBucket) (obj)).zzbhw || zzbiy != ((RawBucket) (obj)).zzbiy) goto _L6; else goto _L5
_L5:
        boolean flag;
        List list = zzbhy;
        List list1 = ((RawBucket) (obj)).zzbhy;
        if (list == list1 || list != null && list.equals(list1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag || zzbhz != ((RawBucket) (obj)).zzbhz || zzbhA != ((RawBucket) (obj)).zzbhA) goto _L6; else goto _L7
_L7:
        flag = true;
_L9:
        flag1 = flag2;
        if (!flag) goto _L4; else goto _L2
_L2:
        flag1 = true;
_L4:
        return flag1;
_L6:
        flag = false;
        if (true) goto _L9; else goto _L8
_L8:
    }

    public final int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            Long.valueOf(zzadP), Long.valueOf(zzbhw), Integer.valueOf(zzbhz)
        });
    }

    public final String toString()
    {
        return (new com.google.android.gms.common.internal.zzaa.zza(this)).zzh("startTime", Long.valueOf(zzadP)).zzh("endTime", Long.valueOf(zzbhw)).zzh("activity", Integer.valueOf(zzbiy)).zzh("dataSets", zzbhy).zzh("bucketType", Integer.valueOf(zzbhz)).zzh("serverHasMoreData", Boolean.valueOf(zzbhA)).toString();
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        long l = zzadP;
        zzc.zzb(parcel, 1, 8);
        parcel.writeLong(l);
        l = zzbhw;
        zzc.zzb(parcel, 2, 8);
        parcel.writeLong(l);
        zzc.zza(parcel, 3, zzbhn, i, false);
        i = zzbiy;
        zzc.zzb(parcel, 4, 4);
        parcel.writeInt(i);
        zzc.zzc(parcel, 5, zzbhy, false);
        i = zzbhz;
        zzc.zzb(parcel, 6, 4);
        parcel.writeInt(i);
        boolean flag1 = zzbhA;
        zzc.zzb(parcel, 7, 4);
        if (flag1)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        i = mVersionCode;
        zzc.zzb(parcel, 1000, 4);
        parcel.writeInt(i);
        zzc.zzJ(parcel, j);
    }

}
