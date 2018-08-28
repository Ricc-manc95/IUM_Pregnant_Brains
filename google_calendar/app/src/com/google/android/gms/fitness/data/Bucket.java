// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.fitness.data;

import android.os.Parcel;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.gms.fitness.data:
//            zzd, RawBucket, DataSet, RawDataSet, 
//            Session

public class Bucket extends zza
    implements ReflectedParcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new zzd();
    public final int mVersionCode;
    public final long zzadP;
    private boolean zzbhA;
    public final Session zzbhn;
    public final long zzbhw;
    public final int zzbhx;
    public final List zzbhy;
    public final int zzbhz;

    Bucket(int i, long l, long l1, Session session, int j, 
            List list, int k, boolean flag)
    {
        zzbhA = false;
        mVersionCode = i;
        zzadP = l;
        zzbhw = l1;
        zzbhn = session;
        zzbhx = j;
        zzbhy = list;
        zzbhz = k;
        zzbhA = flag;
    }

    public Bucket(RawBucket rawbucket, List list)
    {
        long l = rawbucket.zzadP;
        long l1 = rawbucket.zzbhw;
        Session session = rawbucket.zzbhn;
        int i = rawbucket.zzbiy;
        Object obj = rawbucket.zzbhy;
        ArrayList arraylist = new ArrayList(((Collection) (obj)).size());
        for (obj = ((Collection) (obj)).iterator(); ((Iterator) (obj)).hasNext(); arraylist.add(new DataSet((RawDataSet)((Iterator) (obj)).next(), list))) { }
        this(2, l, l1, session, i, ((List) (arraylist)), rawbucket.zzbhz, rawbucket.zzbhA);
    }

    public static String getBucketString(int i)
    {
        switch (i)
        {
        default:
            return "bug";

        case 1: // '\001'
            return "time";

        case 3: // '\003'
            return "type";

        case 4: // '\004'
            return "segment";

        case 2: // '\002'
            return "session";

        case 0: // '\0'
            return "unknown";
        }
    }

    public boolean equals(Object obj)
    {
        boolean flag2 = false;
        if (obj == this) goto _L2; else goto _L1
_L1:
        boolean flag1 = flag2;
        if (!(obj instanceof Bucket)) goto _L4; else goto _L3
_L3:
        obj = (Bucket)obj;
        if (zzadP != ((Bucket) (obj)).zzadP || zzbhw != ((Bucket) (obj)).zzbhw || zzbhx != ((Bucket) (obj)).zzbhx) goto _L6; else goto _L5
_L5:
        boolean flag;
        List list = zzbhy;
        List list1 = ((Bucket) (obj)).zzbhy;
        if (list == list1 || list != null && list.equals(list1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag || zzbhz != ((Bucket) (obj)).zzbhz || zzbhA != ((Bucket) (obj)).zzbhA) goto _L6; else goto _L7
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

    public int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            Long.valueOf(zzadP), Long.valueOf(zzbhw), Integer.valueOf(zzbhx), Integer.valueOf(zzbhz)
        });
    }

    public final boolean serverHasMoreData()
    {
        if (zzbhA)
        {
            return true;
        }
        for (Iterator iterator = zzbhy.iterator(); iterator.hasNext();)
        {
            if (((DataSet)iterator.next()).zzbhL)
            {
                return true;
            }
        }

        return false;
    }

    public String toString()
    {
        return (new com.google.android.gms.common.internal.zzaa.zza(this)).zzh("startTime", Long.valueOf(zzadP)).zzh("endTime", Long.valueOf(zzbhw)).zzh("activity", Integer.valueOf(zzbhx)).zzh("dataSets", zzbhy).zzh("bucketType", getBucketString(zzbhz)).zzh("serverHasMoreData", Boolean.valueOf(zzbhA)).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
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
        i = zzbhx;
        zzc.zzb(parcel, 4, 4);
        parcel.writeInt(i);
        zzc.zzc(parcel, 5, zzbhy, false);
        i = zzbhz;
        zzc.zzb(parcel, 6, 4);
        parcel.writeInt(i);
        boolean flag1 = serverHasMoreData();
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
