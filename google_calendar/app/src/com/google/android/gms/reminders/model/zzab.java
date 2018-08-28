// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.Arrays;

// Referenced classes of package com.google.android.gms.reminders.model:
//            RecurrenceInfo, zzaa, zzz, Recurrence

public class zzab extends zza
    implements RecurrenceInfo
{

    public static final android.os.Parcelable.Creator CREATOR = new zzaa();
    public final int mVersionCode;
    public final String zzciV;
    public final Boolean zzciW;
    public final Boolean zzciX;
    public final zzz zzciY;

    zzab(int i, zzz zzz1, String s, Boolean boolean1, Boolean boolean2)
    {
        zzciY = zzz1;
        zzciV = s;
        zzciW = boolean1;
        zzciX = boolean2;
        mVersionCode = i;
    }

    public zzab(Recurrence recurrence, String s, Boolean boolean1, Boolean boolean2, boolean flag)
    {
        mVersionCode = 1;
        zzciV = s;
        zzciW = boolean1;
        zzciX = boolean2;
        if (flag)
        {
            recurrence = (zzz)recurrence;
        } else
        if (recurrence == null)
        {
            recurrence = null;
        } else
        {
            recurrence = new zzz(recurrence);
        }
        zzciY = recurrence;
    }

    public zzab(RecurrenceInfo recurrenceinfo)
    {
        this(recurrenceinfo.getRecurrence(), recurrenceinfo.getRecurrenceId(), recurrenceinfo.getMaster(), recurrenceinfo.getExceptional(), false);
    }

    public static int zza(RecurrenceInfo recurrenceinfo)
    {
        return Arrays.hashCode(new Object[] {
            recurrenceinfo.getRecurrence(), recurrenceinfo.getRecurrenceId(), recurrenceinfo.getMaster(), recurrenceinfo.getExceptional()
        });
    }

    public static boolean zza(RecurrenceInfo recurrenceinfo, RecurrenceInfo recurrenceinfo1)
    {
        Recurrence recurrence = recurrenceinfo.getRecurrence();
        Recurrence recurrence1 = recurrenceinfo1.getRecurrence();
        boolean flag;
        if (recurrence == recurrence1 || recurrence != null && recurrence.equals(recurrence1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            String s = recurrenceinfo.getRecurrenceId();
            String s1 = recurrenceinfo1.getRecurrenceId();
            if (s == s1 || s != null && s.equals(s1))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                Boolean boolean1 = recurrenceinfo.getMaster();
                Boolean boolean2 = recurrenceinfo1.getMaster();
                if (boolean1 == boolean2 || boolean1 != null && boolean1.equals(boolean2))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    recurrenceinfo = recurrenceinfo.getExceptional();
                    recurrenceinfo1 = recurrenceinfo1.getExceptional();
                    if (recurrenceinfo == recurrenceinfo1 || recurrenceinfo != null && recurrenceinfo.equals(recurrenceinfo1))
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
        if (!(obj instanceof RecurrenceInfo))
        {
            return false;
        }
        if (this == obj)
        {
            return true;
        } else
        {
            return zza(this, (RecurrenceInfo)obj);
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

    public final Boolean getExceptional()
    {
        return zzciX;
    }

    public final Boolean getMaster()
    {
        return zzciW;
    }

    public final Recurrence getRecurrence()
    {
        return zzciY;
    }

    public final String getRecurrenceId()
    {
        return zzciV;
    }

    public int hashCode()
    {
        return zza(this);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        int k = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(k);
        zzc.zza(parcel, 2, zzciY, i, false);
        zzc.zza(parcel, 3, zzciV, false);
        Boolean boolean1 = zzciW;
        if (boolean1 != null)
        {
            zzc.zzb(parcel, 4, 4);
            if (boolean1.booleanValue())
            {
                i = 1;
            } else
            {
                i = 0;
            }
            parcel.writeInt(i);
        }
        boolean1 = zzciX;
        if (boolean1 != null)
        {
            zzc.zzb(parcel, 5, 4);
            if (boolean1.booleanValue())
            {
                i = ((flag) ? 1 : 0);
            } else
            {
                i = 0;
            }
            parcel.writeInt(i);
        }
        zzc.zzJ(parcel, j);
    }

}
