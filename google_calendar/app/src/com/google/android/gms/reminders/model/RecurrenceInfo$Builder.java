// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.model;


// Referenced classes of package com.google.android.gms.reminders.model:
//            RecurrenceInfo, zzz, Recurrence

public static final class tional
{

    public Recurrence zzciU;
    public String zzciV;
    public Boolean zzciW;
    public Boolean zzciX;

    public ()
    {
    }

    public (RecurrenceInfo recurrenceinfo)
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
