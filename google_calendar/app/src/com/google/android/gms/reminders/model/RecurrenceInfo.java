// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.model;

import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;

// Referenced classes of package com.google.android.gms.reminders.model:
//            Recurrence, zzz

public interface RecurrenceInfo
    extends Parcelable, Freezable
{
    public static final class Builder
    {

        public Recurrence zzciU;
        public String zzciV;
        public Boolean zzciW;
        public Boolean zzciX;

        public Builder()
        {
        }

        public Builder(RecurrenceInfo recurrenceinfo)
        {
            Object obj;
            if (recurrenceinfo.getRecurrence() == null)
            {
                obj = null;
            } else
            {
                obj = new zzz(recurrenceinfo.getRecurrence());
            }
            zzciU = ((Recurrence) (obj));
            zzciV = recurrenceinfo.getRecurrenceId();
            zzciW = recurrenceinfo.getMaster();
            zzciX = recurrenceinfo.getExceptional();
        }
    }


    public abstract Boolean getExceptional();

    public abstract Boolean getMaster();

    public abstract Recurrence getRecurrence();

    public abstract String getRecurrenceId();
}
