// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzc;
import com.google.android.gms.reminders.internal.ref.TaskRef;
import com.google.android.gms.reminders.internal.ref.zzo;
import com.google.android.gms.reminders.model.ReminderEvent;
import com.google.android.gms.reminders.model.ReminderEventEntity;
import com.google.android.gms.reminders.model.Task;

public final class zzbbk extends zzo
    implements ReminderEvent
{

    public zzbbk(DataHolder dataholder, int i)
    {
        super(dataholder, i);
    }

    public final Object freeze()
    {
        return new ReminderEventEntity(this);
    }

    public final String getAccountName()
    {
        return getString("account_name");
    }

    public final Task getTask()
    {
        return new TaskRef(zzaKT, zzaNQ);
    }

    public final int getType()
    {
        byte byte0 = 1;
        if (getInteger("deleted") == 1)
        {
            byte0 = 2;
        }
        return byte0;
    }
}
