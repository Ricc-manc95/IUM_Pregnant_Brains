// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.Arrays;

// Referenced classes of package com.google.android.gms.reminders.model:
//            RecurrenceEnd, zzx, zzl, DateTime

public class zzy extends zza
    implements RecurrenceEnd
{

    public static final android.os.Parcelable.Creator CREATOR = new zzx();
    public final int mVersionCode;
    public final Integer zzciJ;
    public final Boolean zzciK;
    public final zzl zzciM;
    public final zzl zzciN;

    zzy(int i, zzl zzl1, Integer integer, Boolean boolean1, zzl zzl2)
    {
        zzciM = zzl1;
        zzciJ = integer;
        zzciK = boolean1;
        zzciN = zzl2;
        mVersionCode = i;
    }

    public zzy(DateTime datetime, Integer integer, Boolean boolean1, DateTime datetime1, boolean flag)
    {
        mVersionCode = 1;
        zzciJ = integer;
        zzciK = boolean1;
        if (flag)
        {
            zzciM = (zzl)datetime;
            datetime = (zzl)datetime1;
        } else
        {
            if (datetime == null)
            {
                datetime = null;
            } else
            {
                datetime = new zzl(datetime);
            }
            zzciM = datetime;
            if (datetime1 == null)
            {
                datetime = null;
            } else
            {
                datetime = new zzl(datetime1);
            }
        }
        zzciN = datetime;
    }

    public zzy(RecurrenceEnd recurrenceend)
    {
        this(recurrenceend.getEndDateTime(), recurrenceend.getNumOccurrences(), recurrenceend.getAutoRenew(), recurrenceend.getAutoRenewUntil(), false);
    }

    public static int zza(RecurrenceEnd recurrenceend)
    {
        return Arrays.hashCode(new Object[] {
            recurrenceend.getEndDateTime(), recurrenceend.getNumOccurrences(), recurrenceend.getAutoRenew(), recurrenceend.getAutoRenewUntil()
        });
    }

    public static boolean zza(RecurrenceEnd recurrenceend, RecurrenceEnd recurrenceend1)
    {
        DateTime datetime = recurrenceend.getEndDateTime();
        DateTime datetime1 = recurrenceend1.getEndDateTime();
        boolean flag;
        if (datetime == datetime1 || datetime != null && datetime.equals(datetime1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            Integer integer = recurrenceend.getNumOccurrences();
            Integer integer1 = recurrenceend1.getNumOccurrences();
            if (integer == integer1 || integer != null && integer.equals(integer1))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                Boolean boolean1 = recurrenceend.getAutoRenew();
                Boolean boolean2 = recurrenceend1.getAutoRenew();
                if (boolean1 == boolean2 || boolean1 != null && boolean1.equals(boolean2))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    recurrenceend = recurrenceend.getAutoRenewUntil();
                    recurrenceend1 = recurrenceend1.getAutoRenewUntil();
                    if (recurrenceend == recurrenceend1 || recurrenceend != null && recurrenceend.equals(recurrenceend1))
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean equals(Object obj)
    {
        if (!(obj instanceof RecurrenceEnd))
        {
            return false;
        }
        if (this == obj)
        {
            return true;
        } else
        {
            return zza(this, (RecurrenceEnd)obj);
        }
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

    public final Boolean getAutoRenew()
    {
        return zzciK;
    }

    public final DateTime getAutoRenewUntil()
    {
        return zzciN;
    }

    public final DateTime getEndDateTime()
    {
        return zzciM;
    }

    public final Integer getNumOccurrences()
    {
        return zzciJ;
    }

    public int hashCode()
    {
        return zza(this);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        int j = 1;
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int k = parcel.dataPosition();
        int l = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(l);
        zzc.zza(parcel, 2, zzciM, i, false);
        Object obj = zzciJ;
        if (obj != null)
        {
            zzc.zzb(parcel, 4, 4);
            parcel.writeInt(((Integer) (obj)).intValue());
        }
        obj = zzciK;
        if (obj != null)
        {
            zzc.zzb(parcel, 5, 4);
            if (!((Boolean) (obj)).booleanValue())
            {
                j = 0;
            }
            parcel.writeInt(j);
        }
        zzc.zza(parcel, 6, zzciN, i, false);
        zzc.zzJ(parcel, k);
    }

}
