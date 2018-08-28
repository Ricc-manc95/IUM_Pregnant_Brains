// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.Arrays;

// Referenced classes of package com.google.android.gms.reminders.model:
//            RecurrenceStart, zzac, zzl, DateTime

public class zzad extends zza
    implements RecurrenceStart
{

    public static final android.os.Parcelable.Creator CREATOR = new zzac();
    public final int mVersionCode;
    public final zzl zzcja;

    zzad(int i, zzl zzl1)
    {
        zzcja = zzl1;
        mVersionCode = i;
    }

    public zzad(DateTime datetime, boolean flag)
    {
        mVersionCode = 1;
        if (flag)
        {
            datetime = (zzl)datetime;
        } else
        if (datetime == null)
        {
            datetime = null;
        } else
        {
            datetime = new zzl(datetime);
        }
        zzcja = datetime;
    }

    public zzad(RecurrenceStart recurrencestart)
    {
        this(recurrencestart.getStartDateTime(), false);
    }

    public static int zza(RecurrenceStart recurrencestart)
    {
        return Arrays.hashCode(new Object[] {
            recurrencestart.getStartDateTime()
        });
    }

    public static boolean zza(RecurrenceStart recurrencestart, RecurrenceStart recurrencestart1)
    {
        recurrencestart = recurrencestart.getStartDateTime();
        recurrencestart1 = recurrencestart1.getStartDateTime();
        return recurrencestart == recurrencestart1 || recurrencestart != null && recurrencestart.equals(recurrencestart1);
    }

    public boolean equals(Object obj)
    {
        if (obj instanceof RecurrenceStart)
        {
            if (this == obj)
            {
                return true;
            }
            Object obj1 = (RecurrenceStart)obj;
            obj = getStartDateTime();
            obj1 = ((RecurrenceStart) (obj1)).getStartDateTime();
            if (obj == obj1 || obj != null && obj.equals(obj1))
            {
                return true;
            }
        }
        return false;
    }

    public final Object freeze()
    {
        if (this == null)
        {
            throw null;
        } else
        {
            return this;
        }
    }

    public final DateTime getStartDateTime()
    {
        return zzcja;
    }

    public int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            getStartDateTime()
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        int k = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(k);
        zzc.zza(parcel, 2, zzcja, i, false);
        zzc.zzJ(parcel, j);
    }

}
