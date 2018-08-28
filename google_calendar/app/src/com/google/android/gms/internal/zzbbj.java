// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.reminders.LoadRemindersOptions;
import com.google.android.gms.reminders.UpdateRecurrenceOptions;
import com.google.android.gms.reminders.model.TaskEntity;
import com.google.android.gms.reminders.model.zzah;
import com.google.android.gms.reminders.zzb;
import java.util.List;

// Referenced classes of package com.google.android.gms.internal:
//            zzbbh

public interface zzbbj
    extends IInterface
{

    public abstract void zzTv()
        throws RemoteException;

    public abstract void zza(zzbbh zzbbh, LoadRemindersOptions loadremindersoptions)
        throws RemoteException;

    public abstract void zza(zzbbh zzbbh, TaskEntity taskentity, zzb zzb1)
        throws RemoteException;

    public abstract void zza(zzbbh zzbbh, zzah zzah)
        throws RemoteException;

    public abstract void zza(zzbbh zzbbh, String s, UpdateRecurrenceOptions updaterecurrenceoptions)
        throws RemoteException;

    public abstract void zza(zzbbh zzbbh, String s, TaskEntity taskentity, UpdateRecurrenceOptions updaterecurrenceoptions)
        throws RemoteException;

    public abstract void zza(zzbbh zzbbh, List list)
        throws RemoteException;

    public abstract void zzb(zzbbh zzbbh, String s, TaskEntity taskentity, UpdateRecurrenceOptions updaterecurrenceoptions)
        throws RemoteException;

    public abstract void zzc(zzbbh zzbbh, TaskEntity taskentity)
        throws RemoteException;

    public abstract void zzd(zzbbh zzbbh, TaskEntity taskentity)
        throws RemoteException;
}
