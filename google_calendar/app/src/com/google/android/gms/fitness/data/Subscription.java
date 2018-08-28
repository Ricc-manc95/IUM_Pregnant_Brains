// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.fitness.data;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.Arrays;

// Referenced classes of package com.google.android.gms.fitness.data:
//            zzaa, DataSource, DataType

public class Subscription extends zza
{
    public static final class Builder
    {

        public DataType zzbhZ;
        public long zzbiF;
        public int zzbiG;

        public Builder()
        {
            zzbiF = -1L;
            zzbiG = 2;
        }
    }


    public static final android.os.Parcelable.Creator CREATOR = new zzaa();
    public final DataSource mDataSource;
    public final int mVersionCode;
    public final DataType zzbhZ;
    public final long zzbiF;
    public final int zzbiG;

    Subscription(int i, DataSource datasource, DataType datatype, long l, int j)
    {
        mVersionCode = i;
        mDataSource = datasource;
        zzbhZ = datatype;
        zzbiF = l;
        zzbiG = j;
    }

    public Subscription(Builder builder)
    {
        mVersionCode = 1;
        zzbhZ = builder.zzbhZ;
        mDataSource = null;
        zzbiF = builder.zzbiF;
        zzbiG = builder.zzbiG;
    }

    public boolean equals(Object obj)
    {
        boolean flag2 = false;
        if (this == obj) goto _L2; else goto _L1
_L1:
        boolean flag1 = flag2;
        if (!(obj instanceof Subscription)) goto _L4; else goto _L3
_L3:
        boolean flag;
        obj = (Subscription)obj;
        Object obj1 = mDataSource;
        Object obj2 = ((Subscription) (obj)).mDataSource;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L6; else goto _L5
_L5:
        obj1 = zzbhZ;
        obj2 = ((Subscription) (obj)).zzbhZ;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag || zzbiF != ((Subscription) (obj)).zzbiF || zzbiG != ((Subscription) (obj)).zzbiG) goto _L6; else goto _L7
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
            mDataSource, mDataSource, Long.valueOf(zzbiF), Integer.valueOf(zzbiG)
        });
    }

    public String toString()
    {
        return (new com.google.android.gms.common.internal.zzaa.zza(this)).zzh("dataSource", mDataSource).zzh("dataType", zzbhZ).zzh("samplingIntervalMicros", Long.valueOf(zzbiF)).zzh("accuracyMode", Integer.valueOf(zzbiG)).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        zzc.zza(parcel, 1, mDataSource, i, false);
        zzc.zza(parcel, 2, zzbhZ, i, false);
        long l = zzbiF;
        zzc.zzb(parcel, 3, 8);
        parcel.writeLong(l);
        i = zzbiG;
        zzc.zzb(parcel, 4, 4);
        parcel.writeInt(i);
        i = mVersionCode;
        zzc.zzb(parcel, 1000, 4);
        parcel.writeInt(i);
        zzc.zzJ(parcel, j);
    }

}
