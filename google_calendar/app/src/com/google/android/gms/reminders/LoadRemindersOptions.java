// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.reminders.model.TaskId;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.android.gms.reminders:
//            zzd

public class LoadRemindersOptions extends zza
{
    public static final class Builder
    {

        public int zzbSV;
        public List zzcfN;
        public Long zzcfO;
        public Long zzcfP;
        private Long zzcfQ;
        private Long zzcfR;
        public boolean zzcfS;
        public int zzcfT;
        private boolean zzcfU;
        private boolean zzcfV;
        private int zzcfW;
        public List zzcfX;
        private Long zzcfY;
        private Long zzcfZ;
        public TaskId zzcga[];

        public final LoadRemindersOptions build()
        {
            if (zzcga == null)
            {
                return new LoadRemindersOptions(null, zzcfN, zzcfO, zzcfP, null, null, zzcfS, zzcfT, false, false, zzcfW, zzbSV, zzcfX, null, null);
            }
            ArrayList arraylist = new ArrayList();
            TaskId ataskid[] = zzcga;
            int j = ataskid.length;
            for (int i = 0; i < j; i++)
            {
                arraylist.add(ataskid[i].getClientAssignedId());
            }

            return new LoadRemindersOptions(arraylist, zzcfN, zzcfO, zzcfP, null, null, zzcfS, zzcfT, false, false, zzcfW, zzbSV, zzcfX, null, null);
        }

        public final transient Builder setLoadReminderType(int ai[])
        {
            if (ai == null)
            {
                throw new NullPointerException(String.valueOf(" The types should not be null"));
            }
            boolean flag;
            if (ai.length != 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalArgumentException(String.valueOf("The types should not be empty"));
            }
            zzcfW = 0;
            int j = ai.length;
            int i = 0;
            while (i < j) 
            {
                int k = ai[i];
                String s;
                boolean flag1;
                if (k == -1 || k == 0 || k == 1 || k == 2)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                s = (new StringBuilder(38)).append("Invalid load reminder type:").append(k).toString();
                if (!flag1)
                {
                    throw new IllegalArgumentException(String.valueOf(s));
                }
                if (k == -1)
                {
                    zzcfW = -1;
                } else
                {
                    zzcfW = zzcfW | 1 << k;
                }
                i++;
            }
            return this;
        }

        public Builder()
        {
            zzcfO = null;
            zzcfP = null;
            zzcfQ = null;
            zzcfR = null;
            zzcfS = false;
            zzcfT = 0;
            zzcfU = false;
            zzcfV = false;
            zzcfW = -1;
            zzbSV = 0;
            zzcfX = null;
            zzcfY = null;
            zzcfZ = null;
        }
    }


    public static final android.os.Parcelable.Creator CREATOR = new zzd();
    public static final LoadRemindersOptions zzcfL = (new Builder()).build();
    public final int mVersionCode;
    public final int zzbSV;
    public final List zzcfM;
    public final List zzcfN;
    public final Long zzcfO;
    public final Long zzcfP;
    public final Long zzcfQ;
    public final Long zzcfR;
    public final boolean zzcfS;
    public final int zzcfT;
    public final boolean zzcfU;
    public final boolean zzcfV;
    public final int zzcfW;
    public final List zzcfX;
    public final Long zzcfY;
    public final Long zzcfZ;

    LoadRemindersOptions(int i, List list, List list1, Long long1, Long long2, Long long3, Long long4, 
            boolean flag, int j, boolean flag1, boolean flag2, int k, int l, List list2, 
            Long long5, Long long6)
    {
        mVersionCode = i;
        zzcfM = list;
        zzcfN = list1;
        zzcfO = long1;
        zzcfP = long2;
        zzcfQ = long3;
        zzcfR = long4;
        zzcfS = flag;
        zzcfT = j;
        zzcfU = flag1;
        zzcfV = flag2;
        zzcfW = k;
        zzbSV = l;
        zzcfX = list2;
        zzcfY = long5;
        zzcfZ = long6;
    }

    LoadRemindersOptions(List list, List list1, Long long1, Long long2, Long long3, Long long4, boolean flag, 
            int i, boolean flag1, boolean flag2, int j, int k, List list2, Long long5, 
            Long long6)
    {
        this(5, list, list1, long1, long2, long3, long4, flag, i, flag1, flag2, j, k, list2, long5, long6);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        i = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(i);
        zzc.zzb(parcel, 3, zzcfM, false);
        zzc.zza(parcel, 4, zzcfN, false);
        Long long1 = zzcfO;
        if (long1 != null)
        {
            zzc.zzb(parcel, 5, 8);
            parcel.writeLong(long1.longValue());
        }
        long1 = zzcfP;
        if (long1 != null)
        {
            zzc.zzb(parcel, 6, 8);
            parcel.writeLong(long1.longValue());
        }
        long1 = zzcfQ;
        if (long1 != null)
        {
            zzc.zzb(parcel, 7, 8);
            parcel.writeLong(long1.longValue());
        }
        long1 = zzcfR;
        if (long1 != null)
        {
            zzc.zzb(parcel, 8, 8);
            parcel.writeLong(long1.longValue());
        }
        boolean flag1 = zzcfS;
        zzc.zzb(parcel, 9, 4);
        if (flag1)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        i = zzcfT;
        zzc.zzb(parcel, 10, 4);
        parcel.writeInt(i);
        flag1 = zzcfU;
        zzc.zzb(parcel, 11, 4);
        if (flag1)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        flag1 = zzcfV;
        zzc.zzb(parcel, 12, 4);
        if (flag1)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        i = zzcfW;
        zzc.zzb(parcel, 13, 4);
        parcel.writeInt(i);
        i = zzbSV;
        zzc.zzb(parcel, 14, 4);
        parcel.writeInt(i);
        zzc.zzb(parcel, 15, zzcfX, false);
        long1 = zzcfY;
        if (long1 != null)
        {
            zzc.zzb(parcel, 16, 8);
            parcel.writeLong(long1.longValue());
        }
        long1 = zzcfZ;
        if (long1 != null)
        {
            zzc.zzb(parcel, 17, 8);
            parcel.writeLong(long1.longValue());
        }
        zzc.zzJ(parcel, j);
    }

}
