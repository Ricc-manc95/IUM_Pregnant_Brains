// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.fitness.result;

import android.os.Parcel;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.RawBucket;
import com.google.android.gms.fitness.data.RawDataSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.gms.fitness.result:
//            zzd

public class DataReadResult extends zza
    implements Result
{

    public static final android.os.Parcelable.Creator CREATOR = new zzd();
    public final int mVersionCode;
    public final Status zzahG;
    public final List zzbhy;
    public final List zzblm;
    public int zzbln;
    public final List zzblo;
    public final List zzblp;

    DataReadResult(int i, List list, Status status, List list1, int j, List list2, List list3)
    {
        mVersionCode = i;
        zzahG = status;
        zzbln = j;
        zzblo = list2;
        zzblp = list3;
        zzbhy = new ArrayList(list.size());
        for (list = list.iterator(); list.hasNext(); zzbhy.add(new DataSet(status, list2)))
        {
            status = (RawDataSet)list.next();
        }

        zzblm = new ArrayList(list1.size());
        for (list = list1.iterator(); list.hasNext(); zzblm.add(new Bucket(status, list2)))
        {
            status = (RawBucket)list.next();
        }

    }

    private DataReadResult(List list, List list1, Status status)
    {
        mVersionCode = 5;
        zzbhy = list;
        zzahG = status;
        zzblm = list1;
        zzbln = 1;
        zzblo = new ArrayList();
        zzblp = new ArrayList();
    }

    public static DataReadResult createFailed(Status status, List list, List list1)
    {
        ArrayList arraylist = new ArrayList();
        for (list1 = list1.iterator(); list1.hasNext(); arraylist.add(DataSet.create((DataSource)list1.next()))) { }
        com.google.android.gms.fitness.data.DataSource.Builder builder;
        for (list = list.iterator(); list.hasNext(); arraylist.add(DataSet.create(builder.build())))
        {
            list1 = (DataType)list.next();
            builder = new com.google.android.gms.fitness.data.DataSource.Builder();
            builder.zzbhk = list1;
            builder.type = 1;
            builder.name = "Default";
        }

        return new DataReadResult(arraylist, Collections.emptyList(), status);
    }

    public static void zza(DataSet dataset, List list)
    {
label0:
        {
            for (Iterator iterator = list.iterator(); iterator.hasNext();)
            {
                DataSet dataset1 = (DataSet)iterator.next();
                if (dataset1.zzbhl.equals(dataset.zzbhl))
                {
                    list = Collections.unmodifiableList(dataset.zzbhJ).iterator();
                    do
                    {
                        if (!list.hasNext())
                        {
                            break;
                        }
                        dataset = (DataPoint)list.next();
                        dataset1.zzbhJ.add(dataset);
                        if (((DataPoint) (dataset)).zzbhG != null)
                        {
                            dataset = ((DataPoint) (dataset)).zzbhG;
                        } else
                        {
                            dataset = ((DataPoint) (dataset)).zzbhl;
                        }
                        if (dataset != null && !dataset1.zzbhK.contains(dataset))
                        {
                            dataset1.zzbhK.add(dataset);
                        }
                    } while (true);
                    break label0;
                }
            }

            list.add(dataset);
        }
    }

    public boolean equals(Object obj)
    {
        boolean flag2 = false;
        if (this == obj) goto _L2; else goto _L1
_L1:
        boolean flag1 = flag2;
        if (!(obj instanceof DataReadResult)) goto _L4; else goto _L3
_L3:
        obj = (DataReadResult)obj;
        if (!zzahG.equals(((DataReadResult) (obj)).zzahG)) goto _L6; else goto _L5
_L5:
        boolean flag;
        List list = zzbhy;
        List list1 = ((DataReadResult) (obj)).zzbhy;
        if (list == list1 || list != null && list.equals(list1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L6; else goto _L7
_L7:
        list = zzblm;
        obj = ((DataReadResult) (obj)).zzblm;
        if (list == obj || list != null && list.equals(obj))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L6; else goto _L8
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

    public final Status getStatus()
    {
        return zzahG;
    }

    public int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            zzahG, zzbhy, zzblm
        });
    }

    public String toString()
    {
        com.google.android.gms.common.internal.zzaa.zza zza1 = (new com.google.android.gms.common.internal.zzaa.zza(this)).zzh("status", zzahG);
        Object obj;
        if (zzbhy.size() > 5)
        {
            int i = zzbhy.size();
            obj = (new StringBuilder(21)).append(i).append(" data sets").toString();
        } else
        {
            obj = zzbhy;
        }
        zza1 = zza1.zzh("dataSets", obj);
        if (zzblm.size() > 5)
        {
            int j = zzblm.size();
            obj = (new StringBuilder(19)).append(j).append(" buckets").toString();
        } else
        {
            obj = zzblm;
        }
        return zza1.zzh("buckets", obj).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        ArrayList arraylist = new ArrayList(zzbhy.size());
        for (Iterator iterator = zzbhy.iterator(); iterator.hasNext(); arraylist.add(new RawDataSet((DataSet)iterator.next(), zzblo, zzblp))) { }
        zzc.zze(parcel, 1, arraylist, false);
        zzc.zza(parcel, 2, zzahG, i, false);
        arraylist = new ArrayList(zzblm.size());
        for (Iterator iterator1 = zzblm.iterator(); iterator1.hasNext(); arraylist.add(new RawBucket((Bucket)iterator1.next(), zzblo, zzblp))) { }
        zzc.zze(parcel, 3, arraylist, false);
        i = zzbln;
        zzc.zzb(parcel, 5, 4);
        parcel.writeInt(i);
        zzc.zzc(parcel, 6, zzblo, false);
        zzc.zzc(parcel, 7, zzblp, false);
        i = mVersionCode;
        zzc.zzb(parcel, 1000, 4);
        parcel.writeInt(i);
        zzc.zzJ(parcel, j);
    }

}
