// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.zzaoy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.gms.fitness.request:
//            zzi

public class DataReadRequest extends zza
{
    public static final class Builder
    {

        private int mLimit;
        public long zzadP;
        public List zzbhv;
        public long zzbhw;
        private int zzbhz;
        public List zzbjU;
        public List zzbjZ;
        public List zzbka;
        private long zzbkb;
        private boolean zzbkd;
        private boolean zzbke;
        public final List zzbkg = new ArrayList();
        public final List zzbkh = new ArrayList();

        public Builder()
        {
            zzbhv = new ArrayList();
            zzbjU = new ArrayList();
            zzbjZ = new ArrayList();
            zzbka = new ArrayList();
            zzbhz = 0;
            zzbkb = 0L;
            mLimit = 0;
            zzbkd = false;
            zzbke = false;
        }
    }


    public static final android.os.Parcelable.Creator CREATOR = new zzi();
    public final int mLimit;
    public final int mVersionCode;
    public final long zzadP;
    public final List zzbhv;
    public final long zzbhw;
    public final int zzbhz;
    public final List zzbjU;
    public final List zzbjZ;
    public final List zzbka;
    public final long zzbkb;
    public final DataSource zzbkc;
    public final boolean zzbkd;
    public final boolean zzbke;
    public final zzaoy zzbkf;
    public final List zzbkg;
    public final List zzbkh;

    DataReadRequest(int i, List list, List list1, long l, long l1, 
            List list2, List list3, int j, long l2, DataSource datasource, int k, 
            boolean flag, boolean flag1, IBinder ibinder, List list4, List list5)
    {
        mVersionCode = i;
        zzbhv = list;
        zzbjU = list1;
        zzadP = l;
        zzbhw = l1;
        zzbjZ = list2;
        zzbka = list3;
        zzbhz = j;
        zzbkb = l2;
        zzbkc = datasource;
        mLimit = k;
        zzbkd = flag;
        zzbke = flag1;
        if (ibinder == null)
        {
            list = null;
        } else
        if (ibinder == null)
        {
            list = null;
        } else
        {
            list = ibinder.queryLocalInterface("com.google.android.gms.fitness.internal.IDataReadCallback");
            if (list != null && (list instanceof zzaoy))
            {
                list = (zzaoy)list;
            } else
            {
                list = new com.google.android.gms.internal.zzaoy.zza.zza(ibinder);
            }
        }
        zzbkf = list;
        list = list4;
        if (list4 == null)
        {
            list = Collections.emptyList();
        }
        zzbkg = list;
        list = list5;
        if (list5 == null)
        {
            list = Collections.emptyList();
        }
        zzbkh = list;
    }

    public DataReadRequest(Builder builder)
    {
        this(builder.zzbhv, builder.zzbjU, builder.zzadP, builder.zzbhw, builder.zzbjZ, builder.zzbka, 0, 0L, null, 0, false, false, null, builder.zzbkg, builder.zzbkh);
    }

    public DataReadRequest(DataReadRequest datareadrequest, zzaoy zzaoy1)
    {
        this(datareadrequest.zzbhv, datareadrequest.zzbjU, datareadrequest.zzadP, datareadrequest.zzbhw, datareadrequest.zzbjZ, datareadrequest.zzbka, datareadrequest.zzbhz, datareadrequest.zzbkb, datareadrequest.zzbkc, datareadrequest.mLimit, datareadrequest.zzbkd, datareadrequest.zzbke, zzaoy1, datareadrequest.zzbkg, datareadrequest.zzbkh);
    }

    private DataReadRequest(List list, List list1, long l, long l1, List list2, 
            List list3, int i, long l2, DataSource datasource, int j, boolean flag, 
            boolean flag1, zzaoy zzaoy1, List list4, List list5)
    {
        if (zzaoy1 == null)
        {
            zzaoy1 = null;
        } else
        {
            zzaoy1 = zzaoy1.asBinder();
        }
        this(6, list, list1, l, l1, list2, list3, i, l2, datasource, j, flag, flag1, ((IBinder) (zzaoy1)), list4, list5);
    }

    public boolean equals(Object obj)
    {
        boolean flag2 = false;
        if (this == obj) goto _L2; else goto _L1
_L1:
        boolean flag1 = flag2;
        if (!(obj instanceof DataReadRequest)) goto _L4; else goto _L3
_L3:
        obj = (DataReadRequest)obj;
        if (!zzbhv.equals(((DataReadRequest) (obj)).zzbhv) || !zzbjU.equals(((DataReadRequest) (obj)).zzbjU) || zzadP != ((DataReadRequest) (obj)).zzadP || zzbhw != ((DataReadRequest) (obj)).zzbhw || zzbhz != ((DataReadRequest) (obj)).zzbhz || !zzbka.equals(((DataReadRequest) (obj)).zzbka) || !zzbjZ.equals(((DataReadRequest) (obj)).zzbjZ)) goto _L6; else goto _L5
_L5:
        boolean flag;
        Object obj1 = zzbkc;
        Object obj2 = ((DataReadRequest) (obj)).zzbkc;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag || zzbkb != ((DataReadRequest) (obj)).zzbkb || zzbke != ((DataReadRequest) (obj)).zzbke || mLimit != ((DataReadRequest) (obj)).mLimit || zzbkd != ((DataReadRequest) (obj)).zzbkd) goto _L6; else goto _L7
_L7:
        obj1 = zzbkf;
        obj2 = ((DataReadRequest) (obj)).zzbkf;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L6; else goto _L8
_L8:
        obj1 = zzbkg;
        obj2 = ((DataReadRequest) (obj)).zzbkg;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L6; else goto _L9
_L9:
        obj1 = zzbkh;
        obj = ((DataReadRequest) (obj)).zzbkh;
        if (obj1 == obj || obj1 != null && obj1.equals(obj))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L6; else goto _L10
_L10:
        flag = true;
_L12:
        flag1 = flag2;
        if (!flag) goto _L4; else goto _L2
_L2:
        flag1 = true;
_L4:
        return flag1;
_L6:
        flag = false;
        if (true) goto _L12; else goto _L11
_L11:
    }

    public int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            Integer.valueOf(zzbhz), Long.valueOf(zzadP), Long.valueOf(zzbhw)
        });
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("DataReadRequest{");
        if (!zzbhv.isEmpty())
        {
            Iterator iterator3 = zzbhv.iterator();
            while (iterator3.hasNext()) 
            {
                Object obj = (DataType)iterator3.next();
                if (((DataType) (obj)).name.startsWith("com.google."))
                {
                    obj = ((DataType) (obj)).name.substring(11);
                } else
                {
                    obj = ((DataType) (obj)).name;
                }
                stringbuilder.append(((String) (obj))).append(" ");
            }
        }
        if (!zzbjU.isEmpty())
        {
            for (Iterator iterator = zzbjU.iterator(); iterator.hasNext(); stringbuilder.append(((DataSource)iterator.next()).toDebugString()).append(" ")) { }
        }
        if (zzbhz != 0)
        {
            stringbuilder.append("bucket by ").append(Bucket.getBucketString(zzbhz));
            if (zzbkb > 0L)
            {
                stringbuilder.append(" >").append(zzbkb).append("ms");
            }
            stringbuilder.append(": ");
        }
        if (!zzbjZ.isEmpty())
        {
            Iterator iterator4 = zzbjZ.iterator();
            while (iterator4.hasNext()) 
            {
                Object obj1 = (DataType)iterator4.next();
                if (((DataType) (obj1)).name.startsWith("com.google."))
                {
                    obj1 = ((DataType) (obj1)).name.substring(11);
                } else
                {
                    obj1 = ((DataType) (obj1)).name;
                }
                stringbuilder.append(((String) (obj1))).append(" ");
            }
        }
        if (!zzbka.isEmpty())
        {
            for (Iterator iterator1 = zzbka.iterator(); iterator1.hasNext(); stringbuilder.append(((DataSource)iterator1.next()).toDebugString()).append(" ")) { }
        }
        stringbuilder.append(String.format("(%tF %tT - %tF %tT)", new Object[] {
            Long.valueOf(zzadP), Long.valueOf(zzadP), Long.valueOf(zzbhw), Long.valueOf(zzbhw)
        }));
        if (zzbkc != null)
        {
            stringbuilder.append("activities: ").append(zzbkc.toDebugString());
        }
        if (!zzbkh.isEmpty())
        {
            stringbuilder.append("quality: ");
            for (Iterator iterator2 = zzbkh.iterator(); iterator2.hasNext(); stringbuilder.append(DataSource.zzjU(((Integer)iterator2.next()).intValue())).append(" ")) { }
        }
        if (zzbke)
        {
            stringbuilder.append(" +server");
        }
        stringbuilder.append("}");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        zzc.zzc(parcel, 1, zzbhv, false);
        zzc.zzc(parcel, 2, zzbjU, false);
        long l = zzadP;
        zzc.zzb(parcel, 3, 8);
        parcel.writeLong(l);
        l = zzbhw;
        zzc.zzb(parcel, 4, 8);
        parcel.writeLong(l);
        zzc.zzc(parcel, 5, zzbjZ, false);
        zzc.zzc(parcel, 6, zzbka, false);
        int k = zzbhz;
        zzc.zzb(parcel, 7, 4);
        parcel.writeInt(k);
        k = mVersionCode;
        zzc.zzb(parcel, 1000, 4);
        parcel.writeInt(k);
        l = zzbkb;
        zzc.zzb(parcel, 8, 8);
        parcel.writeLong(l);
        zzc.zza(parcel, 9, zzbkc, i, false);
        i = mLimit;
        zzc.zzb(parcel, 10, 4);
        parcel.writeInt(i);
        boolean flag1 = zzbkd;
        zzc.zzb(parcel, 12, 4);
        IBinder ibinder;
        if (flag1)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        flag1 = zzbke;
        zzc.zzb(parcel, 13, 4);
        if (flag1)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        if (zzbkf == null)
        {
            ibinder = null;
        } else
        {
            ibinder = zzbkf.asBinder();
        }
        zzc.zza(parcel, 14, ibinder, false);
        zzc.zzc(parcel, 16, zzbkg, false);
        zzc.zza(parcel, 17, zzbkh, false);
        zzc.zzJ(parcel, j);
    }

}
