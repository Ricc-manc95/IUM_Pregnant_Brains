// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

// Referenced classes of package com.google.android.gms.reminders:
//            zzg

public class UpdateRecurrenceOptions extends zza
{
    public static final class Builder
    {

        public Long zzcgf;
        public Integer zzcgg;

        public Builder()
        {
        }
    }


    public static final UpdateRecurrenceOptions ALL_INSTANCES_OPTION;
    public static final android.os.Parcelable.Creator CREATOR = new zzg();
    public final int mVersionCode;
    public final boolean zzcfU;
    public final int zzcge;
    public final Long zzcgf;

    UpdateRecurrenceOptions(int i, Integer integer, Boolean boolean1, Long long1)
    {
        boolean flag = true;
        boolean flag1 = false;
        super();
        mVersionCode = i;
        i = ((flag) ? 1 : 0);
        if (integer != null)
        {
            i = ((flag) ? 1 : 0);
            if (integer.intValue() != 0)
            {
                if (integer.intValue() == 1)
                {
                    i = ((flag) ? 1 : 0);
                } else
                {
                    i = 0;
                }
            }
        }
        if (i == 0)
        {
            throw new IllegalArgumentException(String.valueOf("Invalid update mode"));
        }
        if (integer == null)
        {
            i = 0;
        } else
        {
            i = integer.intValue();
        }
        zzcge = i;
        if (boolean1 != null)
        {
            flag1 = boolean1.booleanValue();
        }
        zzcfU = flag1;
        zzcgf = long1;
    }

    public UpdateRecurrenceOptions(Integer integer, Boolean boolean1, Long long1)
    {
        this(1, integer, boolean1, long1);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        i = 1;
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        int k = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(k);
        k = zzcge;
        zzc.zzb(parcel, 2, 4);
        parcel.writeInt(k);
        boolean flag = zzcfU;
        zzc.zzb(parcel, 3, 4);
        Long long1;
        if (!flag)
        {
            i = 0;
        }
        parcel.writeInt(i);
        long1 = zzcgf;
        if (long1 != null)
        {
            zzc.zzb(parcel, 4, 8);
            parcel.writeLong(long1.longValue());
        }
        zzc.zzJ(parcel, j);
    }

    static 
    {
        Builder builder = new Builder();
        ALL_INSTANCES_OPTION = new UpdateRecurrenceOptions(builder.zzcgg, null, builder.zzcgf);
    }
}
