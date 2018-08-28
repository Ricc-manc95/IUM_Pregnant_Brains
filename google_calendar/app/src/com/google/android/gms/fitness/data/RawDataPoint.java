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
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.gms.fitness.data:
//            zzu, DataPoint, Value

public final class RawDataPoint extends zza
{

    public static final android.os.Parcelable.Creator CREATOR = new zzu();
    public final int mDataSourceIndex;
    public final long mInsertionTimeMillis;
    public final int mOriginalDataSourceIndex;
    public final long mRawTimestamp;
    public final long mStartTimeNanos;
    public final long mTimestampNanos;
    public final Value mValues[];
    public final int versionCode;

    public RawDataPoint(int i, long l, long l1, Value avalue[], int j, 
            int k, long l2, long l3)
    {
        versionCode = i;
        mTimestampNanos = l;
        mStartTimeNanos = l1;
        mDataSourceIndex = j;
        mOriginalDataSourceIndex = k;
        mRawTimestamp = l2;
        mInsertionTimeMillis = l3;
        mValues = avalue;
    }

    RawDataPoint(DataPoint datapoint, List list)
    {
        versionCode = 4;
        mTimestampNanos = TimeUnit.NANOSECONDS.convert(datapoint.zzbhD, TimeUnit.NANOSECONDS);
        mStartTimeNanos = TimeUnit.NANOSECONDS.convert(datapoint.zzbhE, TimeUnit.NANOSECONDS);
        mValues = datapoint.zzbhF;
        mDataSourceIndex = zzaog.zza(datapoint.zzbhl, list);
        mOriginalDataSourceIndex = zzaog.zza(datapoint.zzbhG, list);
        mRawTimestamp = datapoint.zzbhH;
        mInsertionTimeMillis = datapoint.zzbhI;
    }

    public final boolean equals(Object obj)
    {
label0:
        {
            boolean flag2 = false;
            if (this != obj)
            {
                boolean flag1 = flag2;
                if (!(obj instanceof RawDataPoint))
                {
                    break label0;
                }
                obj = (RawDataPoint)obj;
                boolean flag;
                if (mTimestampNanos == ((RawDataPoint) (obj)).mTimestampNanos && mStartTimeNanos == ((RawDataPoint) (obj)).mStartTimeNanos && Arrays.equals(mValues, ((RawDataPoint) (obj)).mValues) && mDataSourceIndex == ((RawDataPoint) (obj)).mDataSourceIndex && mOriginalDataSourceIndex == ((RawDataPoint) (obj)).mOriginalDataSourceIndex && mRawTimestamp == ((RawDataPoint) (obj)).mRawTimestamp)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                flag1 = flag2;
                if (!flag)
                {
                    break label0;
                }
            }
            flag1 = true;
        }
        return flag1;
    }

    public final int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            Long.valueOf(mTimestampNanos), Long.valueOf(mStartTimeNanos)
        });
    }

    public final String toString()
    {
        return String.format("RawDataPoint{%s@[%s, %s](%d,%d)}", new Object[] {
            Arrays.toString(mValues), Long.valueOf(mStartTimeNanos), Long.valueOf(mTimestampNanos), Integer.valueOf(mDataSourceIndex), Integer.valueOf(mOriginalDataSourceIndex)
        });
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        long l = mTimestampNanos;
        zzc.zzb(parcel, 1, 8);
        parcel.writeLong(l);
        l = mStartTimeNanos;
        zzc.zzb(parcel, 2, 8);
        parcel.writeLong(l);
        zzc.zza(parcel, 3, mValues, i, false);
        i = mDataSourceIndex;
        zzc.zzb(parcel, 4, 4);
        parcel.writeInt(i);
        i = mOriginalDataSourceIndex;
        zzc.zzb(parcel, 5, 4);
        parcel.writeInt(i);
        l = mRawTimestamp;
        zzc.zzb(parcel, 6, 8);
        parcel.writeLong(l);
        l = mInsertionTimeMillis;
        zzc.zzb(parcel, 7, 8);
        parcel.writeLong(l);
        i = versionCode;
        zzc.zzb(parcel, 1000, 4);
        parcel.writeInt(i);
        zzc.zzJ(parcel, j);
    }

}
