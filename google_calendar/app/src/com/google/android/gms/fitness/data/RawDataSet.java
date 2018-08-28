// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.fitness.data;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.internal.zzaog;
import java.util.Arrays;
import java.util.List;

// Referenced classes of package com.google.android.gms.fitness.data:
//            zzv, DataSet, DataSource

public final class RawDataSet extends zza
{

    public static final android.os.Parcelable.Creator CREATOR = new zzv();
    public final int mDataSourceIndex;
    public final int mVersionCode;
    public final boolean zzbhA;
    public final List zzbiA;
    public final int zzbiz;

    public RawDataSet(int i, int j, int k, List list, boolean flag)
    {
        mVersionCode = i;
        mDataSourceIndex = j;
        zzbiz = k;
        zzbiA = list;
        zzbhA = flag;
    }

    public RawDataSet(DataSet dataset, List list, List list1)
    {
        mVersionCode = 3;
        zzbiA = dataset.zzG(list);
        zzbhA = dataset.zzbhL;
        mDataSourceIndex = zzaog.zza(dataset.zzbhl, list);
        zzbiz = zzaog.zza(dataset.zzbhl.zzbhk, list1);
    }

    public final boolean equals(Object obj)
    {
        boolean flag2 = false;
        if (this == obj) goto _L2; else goto _L1
_L1:
        boolean flag1 = flag2;
        if (!(obj instanceof RawDataSet)) goto _L4; else goto _L3
_L3:
        Object obj1 = (RawDataSet)obj;
        if (mDataSourceIndex != ((RawDataSet) (obj1)).mDataSourceIndex || zzbhA != ((RawDataSet) (obj1)).zzbhA) goto _L6; else goto _L5
_L5:
        boolean flag;
        obj = zzbiA;
        obj1 = ((RawDataSet) (obj1)).zzbiA;
        if (obj == obj1 || obj != null && obj.equals(obj1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L6; else goto _L7
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
            Integer.valueOf(mDataSourceIndex)
        });
    }

    public final String toString()
    {
        return String.format("RawDataSet{%s@[%s]}", new Object[] {
            Integer.valueOf(mDataSourceIndex), zzbiA
        });
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        i = 1;
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        int k = mDataSourceIndex;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(k);
        k = zzbiz;
        zzc.zzb(parcel, 2, 4);
        parcel.writeInt(k);
        zzc.zzc(parcel, 3, zzbiA, false);
        boolean flag = zzbhA;
        zzc.zzb(parcel, 4, 4);
        if (!flag)
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
