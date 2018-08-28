// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.model;


// Referenced classes of package com.google.android.gms.reminders.model:
//            RecurrenceEnd, zzl, DateTime

public static final class zzciL
{

    public DateTime zzciI;
    public Integer zzciJ;
    public Boolean zzciK;
    public DateTime zzciL;

    public ()
    {
    }

    public (RecurrenceEnd recurrenceend)
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
