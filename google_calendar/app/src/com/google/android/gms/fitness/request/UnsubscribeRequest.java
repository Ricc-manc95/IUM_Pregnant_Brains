// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.zzapr;
import java.util.Arrays;

// Referenced classes of package com.google.android.gms.fitness.request:
//            zzbm

public class UnsubscribeRequest extends zza
{

    public static final android.os.Parcelable.Creator CREATOR = new zzbm();
    public final DataSource mDataSource;
    public final int mVersionCode;
    public final DataType zzbhZ;
    public final zzapr zzbjR;

    UnsubscribeRequest(int i, DataType datatype, DataSource datasource, IBinder ibinder)
    {
        mVersionCode = i;
        zzbhZ = datatype;
        mDataSource = datasource;
        zzbjR = com.google.android.gms.internal.zzapr.zza.zzeF(ibinder);
    }

    public UnsubscribeRequest(DataType datatype, DataSource datasource, zzapr zzapr1)
    {
        mVersionCode = 3;
        zzbhZ = datatype;
        mDataSource = null;
        zzbjR = zzapr1;
    }

    public boolean equals(Object obj)
    {
        boolean flag2 = false;
        if (this == obj) goto _L2; else goto _L1
_L1:
        boolean flag1 = flag2;
        if (!(obj instanceof UnsubscribeRequest)) goto _L4; else goto _L3
_L3:
        boolean flag;
        obj = (UnsubscribeRequest)obj;
        Object obj1 = mDataSource;
        DataSource datasource = ((UnsubscribeRequest) (obj)).mDataSource;
        if (obj1 == datasource || obj1 != null && obj1.equals(datasource))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L6; else goto _L5
_L5:
        obj1 = zzbhZ;
        obj = ((UnsubscribeRequest) (obj)).zzbhZ;
        if (obj1 == obj || obj1 != null && obj1.equals(obj))
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

    public int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            mDataSource, zzbhZ
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        zzc.zza(parcel, 1, zzbhZ, i, false);
        zzc.zza(parcel, 2, mDataSource, i, false);
        IBinder ibinder;
        if (zzbjR == null)
        {
            ibinder = null;
        } else
        {
            ibinder = zzbjR.asBinder();
        }
        zzc.zza(parcel, 3, ibinder, false);
        i = mVersionCode;
        zzc.zzb(parcel, 1000, 4);
        parcel.writeInt(i);
        zzc.zzJ(parcel, j);
    }

}
