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
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.gms.fitness.data:
//            zzh, DataSource, RawDataPoint, DataPoint, 
//            RawDataSet

public final class DataSet extends zza
    implements ReflectedParcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new zzh();
    public final int versionCode;
    public final List zzbhJ;
    public final List zzbhK;
    public boolean zzbhL;
    public final DataSource zzbhl;

    DataSet(int i, DataSource datasource, List list, List list1, boolean flag)
    {
        zzbhL = false;
        versionCode = i;
        zzbhl = datasource;
        DataType datatype = datasource.zzbhk;
        zzbhL = flag;
        zzbhJ = new ArrayList(list.size());
        if (i < 2)
        {
            list1 = Collections.singletonList(datasource);
        }
        zzbhK = list1;
        for (datasource = list.iterator(); datasource.hasNext(); zzbhJ.add(new DataPoint(zzbhK, list)))
        {
            list = (RawDataPoint)datasource.next();
        }

    }

    private DataSet(DataSource datasource)
    {
        zzbhL = false;
        versionCode = 3;
        if (datasource == null)
        {
            throw new NullPointerException("null reference");
        } else
        {
            zzbhl = (DataSource)datasource;
            datasource = datasource.zzbhk;
            zzbhJ = new ArrayList();
            zzbhK = new ArrayList();
            zzbhK.add(zzbhl);
            return;
        }
    }

    public DataSet(RawDataSet rawdataset, List list)
    {
        zzbhL = false;
        versionCode = 3;
        int i = rawdataset.mDataSourceIndex;
        Object obj;
        if (i >= 0 && i < list.size())
        {
            obj = list.get(i);
        } else
        {
            obj = null;
        }
        zzbhl = (DataSource)obj;
        obj = zzbhl.zzbhk;
        zzbhK = list;
        zzbhL = rawdataset.zzbhA;
        rawdataset = rawdataset.zzbiA;
        zzbhJ = new ArrayList(rawdataset.size());
        for (rawdataset = rawdataset.iterator(); rawdataset.hasNext(); zzbhJ.add(new DataPoint(zzbhK, list)))
        {
            list = (RawDataPoint)rawdataset.next();
        }

    }

    public static DataSet create(DataSource datasource)
    {
        if (datasource == null)
        {
            throw new NullPointerException(String.valueOf("DataSource should be specified"));
        } else
        {
            return new DataSet(datasource);
        }
    }

    public final boolean equals(Object obj)
    {
        boolean flag2 = false;
        if (obj == this) goto _L2; else goto _L1
_L1:
        boolean flag1 = flag2;
        if (!(obj instanceof DataSet)) goto _L4; else goto _L3
_L3:
        boolean flag;
        obj = (DataSet)obj;
        Object obj1 = zzbhl.zzbhk;
        Object obj2 = ((DataSet) (obj)).zzbhl.zzbhk;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L6; else goto _L5
_L5:
        obj1 = zzbhl;
        obj2 = ((DataSet) (obj)).zzbhl;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L6; else goto _L7
_L7:
        obj1 = zzbhJ;
        obj2 = ((DataSet) (obj)).zzbhJ;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag || zzbhL != ((DataSet) (obj)).zzbhL) goto _L6; else goto _L8
_L8:
        flag = true;
_L10:
        flag1 = flag2;
        if (!flag) goto _L4; else goto _L2
_L2:
        flag1 = true;
_L4:
        return flag1;
_L6:
        flag = false;
        if (true) goto _L10; else goto _L9
_L9:
    }

    public final int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            zzbhl
        });
    }

    public final String toString()
    {
        Object obj = zzG(zzbhK);
        String s = zzbhl.toDebugString();
        if (zzbhJ.size() >= 10)
        {
            obj = String.format("%d data points, first 5: %s", new Object[] {
                Integer.valueOf(zzbhJ.size()), ((List) (obj)).subList(0, 5)
            });
        }
        return String.format("DataSet{%s %s}", new Object[] {
            s, obj
        });
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        zzc.zza(parcel, 1, zzbhl, i, false);
        zzc.zza(parcel, 2, zzbhl.zzbhk, i, false);
        zzc.zze(parcel, 3, zzG(zzbhK), false);
        zzc.zzc(parcel, 4, zzbhK, false);
        boolean flag1 = zzbhL;
        zzc.zzb(parcel, 5, 4);
        if (flag1)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        i = versionCode;
        zzc.zzb(parcel, 1000, 4);
        parcel.writeInt(i);
        zzc.zzJ(parcel, j);
    }

    final List zzG(List list)
    {
        ArrayList arraylist = new ArrayList(zzbhJ.size());
        for (Iterator iterator = zzbhJ.iterator(); iterator.hasNext(); arraylist.add(new RawDataPoint((DataPoint)iterator.next(), list))) { }
        return arraylist;
    }

}
