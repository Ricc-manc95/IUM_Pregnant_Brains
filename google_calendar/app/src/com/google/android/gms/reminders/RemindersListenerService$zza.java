// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders;

import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.zzbbk;
import com.google.android.gms.reminders.model.ReminderEvent;
import com.google.android.gms.reminders.model.ReminderEventBuffer;
import com.google.android.gms.reminders.model.ReminderEventEntity;

// Referenced classes of package com.google.android.gms.reminders:
//            RemindersListenerService

final class zzcgd extends com.google.android.gms.internal.
{

    private final RemindersListenerService zzcgd;

    public final void zzaw(DataHolder dataholder)
        throws RemoteException
    {
        Object obj;
        obj = String.valueOf(zzcgd.mPackageName);
        String s;
        boolean flag;
        if (((String) (obj)).length() != 0)
        {
            "onReminderFired: ".concat(((String) (obj)));
        } else
        {
            new String("onReminderFired: ");
        }
        RemindersListenerService.zzb(zzcgd);
        obj = zzcgd.zzsF;
        obj;
        JVM INSTR monitorenter ;
        s = zzcgd.mPackageName;
        flag = zzcgd.zzsG;
        (new StringBuilder(String.valueOf(s).length() + 34)).append("onReminderFired: ").append(s).append(": shutdown? ").append(flag);
        if (!zzcgd.zzsG)
        {
            break MISSING_BLOCK_LABEL_130;
        }
        if (dataholder == null)
        {
            break MISSING_BLOCK_LABEL_114;
        }
        dataholder.close();
        obj;
        JVM INSTR monitorexit ;
        return;
        String s1 = zzcgd.mPackageName;
        int i = dataholder.zzaNZ;
        (new StringBuilder(String.valueOf(s1).length() + 30)).append("onReminderFired: ").append(s1).append(": ").append(i);
        dataholder = new ReminderEventBuffer(dataholder);
        ReminderEventEntity reminderevententity = new ReminderEventEntity((ReminderEvent)new zzbbk(((ReminderEventBuffer) (dataholder)).zzaKT, 0));
        Intent intent = new Intent(zzcgd, zzcgd.getClass());
        intent.putExtra("api_id", 1);
        intent.putExtra("reminder_event", reminderevententity);
        zzcgd.startService(intent);
        if (((AbstractDataBuffer) (dataholder)).zzaKT != null)
        {
            ((AbstractDataBuffer) (dataholder)).zzaKT.close();
        }
        obj;
        JVM INSTR monitorexit ;
        return;
        dataholder;
        obj;
        JVM INSTR monitorexit ;
        throw dataholder;
        Exception exception;
        exception;
        if (((AbstractDataBuffer) (dataholder)).zzaKT != null)
        {
            ((AbstractDataBuffer) (dataholder)).zzaKT.close();
        }
        throw exception;
    }

    public final void zzax(DataHolder dataholder)
        throws RemoteException
    {
        Object obj;
        obj = String.valueOf(zzcgd.mPackageName);
        String s;
        boolean flag;
        if (((String) (obj)).length() != 0)
        {
            "onReminderChangeEvents: ".concat(((String) (obj)));
        } else
        {
            new String("onReminderChangeEvents: ");
        }
        RemindersListenerService.zzb(zzcgd);
        obj = zzcgd.zzsF;
        obj;
        JVM INSTR monitorenter ;
        s = zzcgd.mPackageName;
        flag = zzcgd.zzsG;
        (new StringBuilder(String.valueOf(s).length() + 41)).append("onReminderChangeEvents: ").append(s).append(": shutdown? ").append(flag);
        if (!zzcgd.zzsG)
        {
            break MISSING_BLOCK_LABEL_130;
        }
        if (dataholder == null)
        {
            break MISSING_BLOCK_LABEL_114;
        }
        dataholder.close();
        obj;
        JVM INSTR monitorexit ;
        return;
        String s1 = zzcgd.mPackageName;
        int i = dataholder.zzaNZ;
        (new StringBuilder(String.valueOf(s1).length() + 37)).append("onReminderChangeEvents: ").append(s1).append(": ").append(i);
        i = zzcgd.zzav(dataholder);
        dataholder = new Intent(zzcgd, zzcgd.getClass());
        dataholder.putExtra("api_id", 2);
        dataholder.putExtra("data_holder_id", i);
        zzcgd.startService(dataholder);
        obj;
        JVM INSTR monitorexit ;
        return;
        dataholder;
        obj;
        JVM INSTR monitorexit ;
        throw dataholder;
    }

    public final void zzay(DataHolder dataholder)
        throws RemoteException
    {
        Object obj;
        obj = String.valueOf(zzcgd.mPackageName);
        String s;
        boolean flag;
        if (((String) (obj)).length() != 0)
        {
            "onSnoozePresetChangedEvents: ".concat(((String) (obj)));
        } else
        {
            new String("onSnoozePresetChangedEvents: ");
        }
        RemindersListenerService.zzb(zzcgd);
        obj = zzcgd.zzsF;
        obj;
        JVM INSTR monitorenter ;
        s = zzcgd.mPackageName;
        flag = zzcgd.zzsG;
        (new StringBuilder(String.valueOf(s).length() + 46)).append("onSnoozePresetChangedEvents: ").append(s).append(": shutdown? ").append(flag);
        if (!zzcgd.zzsG)
        {
            break MISSING_BLOCK_LABEL_130;
        }
        if (dataholder == null)
        {
            break MISSING_BLOCK_LABEL_114;
        }
        dataholder.close();
        obj;
        JVM INSTR monitorexit ;
        return;
        String s1 = zzcgd.mPackageName;
        int i = dataholder.zzaNZ;
        (new StringBuilder(String.valueOf(s1).length() + 42)).append("onSnoozePresetChangedEvents: ").append(s1).append(": ").append(i);
        i = zzcgd.zzav(dataholder);
        dataholder = new Intent(zzcgd, zzcgd.getClass());
        dataholder.putExtra("api_id", 3);
        dataholder.putExtra("data_holder_id", i);
        zzcgd.startService(dataholder);
        obj;
        JVM INSTR monitorexit ;
        return;
        dataholder;
        obj;
        JVM INSTR monitorexit ;
        throw dataholder;
    }

    (RemindersListenerService reminderslistenerservice)
    {
        zzcgd = reminderslistenerservice;
        super();
    }
}
