// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.model;

import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;

// Referenced classes of package com.google.android.gms.reminders.model:
//            DateTime, zzl

public interface RecurrenceEnd
    extends Parcelable, Freezable
{
    public static final class Builder
    {

        public DateTime zzciI;
        public Integer zzciJ;
        public Boolean zzciK;
        public DateTime zzciL;

        public Builder()
        {
        }

        public Builder(RecurrenceEnd recurrenceend)
        {
            Object obj1 = null;
            super();
            Object obj;
            if (recurrenceend.getEndDateTime() == null)
            {
                obj = null;
            } else
            {
                obj = new zzl(recurrenceend.getEndDateTime());
            }
            zzciI = ((DateTime) (obj));
            zzciJ = recurrenceend.getNumOccurrences();
            zzciK = recurrenceend.getAutoRenew();
            if (recurrenceend.getAutoRenewUntil() == null)
            {
                recurrenceend = obj1;
            } else
            {
                recurrenceend = new zzl(recurrenceend.getAutoRenewUntil());
            }
            zzciL = recurrenceend;
        }
    }


    public abstract Boolean getAutoRenew();

    public abstract DateTime getAutoRenewUntil();

    public abstract DateTime getEndDateTime();

    public abstract Integer getNumOccurrences();
}
