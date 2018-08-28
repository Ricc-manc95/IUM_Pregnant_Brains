// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.fitness.data;

import android.os.Parcel;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.Arrays;
import java.util.List;

// Referenced classes of package com.google.android.gms.fitness.data:
//            zzg, RawDataPoint, DataSource, Value

public final class DataPoint extends zza
    implements ReflectedParcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new zzg();
    public final int versionCode;
    public long zzbhD;
    public long zzbhE;
    public final Value zzbhF[];
    public DataSource zzbhG;
    public long zzbhH;
    public long zzbhI;
    public final DataSource zzbhl;

    DataPoint(int i, DataSource datasource, long l, long l1, Value avalue[], 
            DataSource datasource1, long l2, long l3)
    {
        versionCode = i;
        zzbhl = datasource;
        zzbhG = datasource1;
        zzbhD = l;
        zzbhE = l1;
        zzbhF = avalue;
        zzbhH = l2;
        zzbhI = l3;
    }

    private DataPoint(DataSource datasource, DataSource datasource1, RawDataPoint rawdatapoint)
    {
        Long long1 = Long.valueOf(rawdatapoint.mTimestampNanos);
        Value avalue[];
        Long long2;
        long l;
        long l1;
        long l2;
        long l3;
        if (long1 != null)
        {
            l = long1.longValue();
        } else
        {
            l = 0L;
        }
        long1 = Long.valueOf(rawdatapoint.mStartTimeNanos);
        if (long1 != null)
        {
            l1 = long1.longValue();
        } else
        {
            l1 = 0L;
        }
        avalue = rawdatapoint.mValues;
        long2 = Long.valueOf(rawdatapoint.mRawTimestamp);
        if (long2 != null)
        {
            l2 = long2.longValue();
        } else
        {
            l2 = 0L;
        }
        rawdatapoint = Long.valueOf(rawdatapoint.mInsertionTimeMillis);
        if (rawdatapoint != null)
        {
            l3 = rawdatapoint.longValue();
        } else
        {
            l3 = 0L;
        }
        this(4, datasource, l, l1, avalue, datasource1, l2, l3);
    }

    DataPoint(List list, RawDataPoint rawdatapoint)
    {
        int i = rawdatapoint.mDataSourceIndex;
        DataSource datasource;
        if (i >= 0 && i < list.size())
        {
            datasource = (DataSource)list.get(i);
        } else
        {
            datasource = null;
        }
        i = rawdatapoint.mOriginalDataSourceIndex;
        if (i >= 0 && i < list.size())
        {
            list = (DataSource)list.get(i);
        } else
        {
            list = null;
        }
        this(datasource, ((DataSource) (list)), rawdatapoint);
    }

    public final boolean equals(Object obj)
    {
        if (this == obj) goto _L2; else goto _L1
_L1:
        if (!(obj instanceof DataPoint)) goto _L4; else goto _L3
_L3:
        Object obj1;
        boolean flag;
        obj1 = (DataPoint)obj;
        obj = zzbhl;
        DataSource datasource = ((DataPoint) (obj1)).zzbhl;
        if (obj == datasource || obj != null && obj.equals(datasource))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag || zzbhD != ((DataPoint) (obj1)).zzbhD || zzbhE != ((DataPoint) (obj1)).zzbhE || !Arrays.equals(zzbhF, ((DataPoint) (obj1)).zzbhF)) goto _L6; else goto _L5
_L5:
        if (zzbhG != null)
        {
            obj = zzbhG;
        } else
        {
            obj = zzbhl;
        }
        if (((DataPoint) (obj1)).zzbhG != null)
        {
            obj1 = ((DataPoint) (obj1)).zzbhG;
        } else
        {
            obj1 = ((DataPoint) (obj1)).zzbhl;
        }
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
_L8:
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
_L2:
        return true;
_L6:
        flag = false;
        if (true) goto _L8; else goto _L4
_L4:
        return false;
    }

    public final int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            zzbhl, Long.valueOf(zzbhD), Long.valueOf(zzbhE)
        });
    }

    public final String toString()
    {
        String s1 = Arrays.toString(zzbhF);
        long l = zzbhE;
        long l1 = zzbhD;
        long l2 = zzbhH;
        long l3 = zzbhI;
        String s2 = zzbhl.toDebugString();
        String s;
        if (zzbhG != null)
        {
            s = zzbhG.toDebugString();
        } else
        {
            s = "N/A";
        }
        return String.format("DataPoint{%s@[%s, %s,raw=%s,insert=%s](%s %s)}", new Object[] {
            s1, Long.valueOf(l), Long.valueOf(l1), Long.valueOf(l2), Long.valueOf(l3), s2, s
        });
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        zzc.zza(parcel, 1, zzbhl, i, false);
        long l = zzbhD;
        zzc.zzb(parcel, 3, 8);
        parcel.writeLong(l);
        l = zzbhE;
        zzc.zzb(parcel, 4, 8);
        parcel.writeLong(l);
        zzc.zza(parcel, 5, zzbhF, i, false);
        zzc.zza(parcel, 6, zzbhG, i, false);
        l = zzbhH;
        zzc.zzb(parcel, 7, 8);
        parcel.writeLong(l);
        i = versionCode;
        zzc.zzb(parcel, 1000, 4);
        parcel.writeInt(i);
        l = zzbhI;
        zzc.zzb(parcel, 8, 8);
        parcel.writeLong(l);
        zzc.zzJ(parcel, j);
    }

}
