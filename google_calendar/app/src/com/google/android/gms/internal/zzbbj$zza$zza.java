// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.reminders.LoadRemindersOptions;
import com.google.android.gms.reminders.UpdateRecurrenceOptions;
import com.google.android.gms.reminders.model.TaskEntity;
import com.google.android.gms.reminders.model.zzah;
import com.google.android.gms.reminders.zzb;
import java.util.List;

// Referenced classes of package com.google.android.gms.internal:
//            zzbbj, zzbbh

final class zzro
    implements zzbbj
{

    private IBinder zzro;

    public final IBinder asBinder()
    {
        return zzro;
    }

    public final void zzTv()
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("com.google.android.gms.reminders.internal.IRemindersService");
        zzro.transact(14, parcel, parcel1, 0);
        parcel1.readException();
        parcel1.recycle();
        parcel.recycle();
        return;
        Exception exception;
        exception;
        parcel1.recycle();
        parcel.recycle();
        throw exception;
    }

    public final void zza(zzbbh zzbbh1, LoadRemindersOptions loadremindersoptions)
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("com.google.android.gms.reminders.internal.IRemindersService");
        if (zzbbh1 == null) goto _L2; else goto _L1
_L1:
        zzbbh1 = zzbbh1.asBinder();
_L5:
        parcel.writeStrongBinder(zzbbh1);
        if (loadremindersoptions == null) goto _L4; else goto _L3
_L3:
        parcel.writeInt(1);
        loadremindersoptions.writeToParcel(parcel, 0);
_L6:
        zzro.transact(1, parcel, parcel1, 0);
        parcel1.readException();
        parcel1.recycle();
        parcel.recycle();
        return;
_L2:
        zzbbh1 = null;
          goto _L5
_L4:
        parcel.writeInt(0);
          goto _L6
        zzbbh1;
        parcel1.recycle();
        parcel.recycle();
        throw zzbbh1;
          goto _L5
    }

    public final void zza(zzbbh zzbbh1, TaskEntity taskentity, zzb zzb1)
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("com.google.android.gms.reminders.internal.IRemindersService");
        parcel.writeStrongBinder(zzbbh1.asBinder());
        parcel.writeInt(1);
        taskentity.writeToParcel(parcel, 0);
        if (zzb1 == null)
        {
            break MISSING_BLOCK_LABEL_91;
        }
        parcel.writeInt(1);
        zzb1.writeToParcel(parcel, 0);
_L1:
        zzro.transact(16, parcel, parcel1, 0);
        parcel1.readException();
        parcel1.recycle();
        parcel.recycle();
        return;
        parcel.writeInt(0);
          goto _L1
        zzbbh1;
        parcel1.recycle();
        parcel.recycle();
        throw zzbbh1;
    }

    public final void zza(zzbbh zzbbh1, zzah zzah1)
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("com.google.android.gms.reminders.internal.IRemindersService");
        parcel.writeStrongBinder(zzbbh1.asBinder());
        parcel.writeInt(1);
        zzah1.writeToParcel(parcel, 0);
        zzro.transact(5, parcel, parcel1, 0);
        parcel1.readException();
        parcel1.recycle();
        parcel.recycle();
        return;
        zzbbh1;
        parcel1.recycle();
        parcel.recycle();
        throw zzbbh1;
    }

    public final void zza(zzbbh zzbbh1, String s, UpdateRecurrenceOptions updaterecurrenceoptions)
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("com.google.android.gms.reminders.internal.IRemindersService");
        parcel.writeStrongBinder(zzbbh1.asBinder());
        parcel.writeString(s);
        if (updaterecurrenceoptions == null)
        {
            break MISSING_BLOCK_LABEL_84;
        }
        parcel.writeInt(1);
        updaterecurrenceoptions.writeToParcel(parcel, 0);
_L1:
        zzro.transact(10, parcel, parcel1, 0);
        parcel1.readException();
        parcel1.recycle();
        parcel.recycle();
        return;
        parcel.writeInt(0);
          goto _L1
        zzbbh1;
        parcel1.recycle();
        parcel.recycle();
        throw zzbbh1;
    }

    public final void zza(zzbbh zzbbh1, String s, TaskEntity taskentity, UpdateRecurrenceOptions updaterecurrenceoptions)
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("com.google.android.gms.reminders.internal.IRemindersService");
        parcel.writeStrongBinder(zzbbh1.asBinder());
        parcel.writeString(s);
        parcel.writeInt(1);
        taskentity.writeToParcel(parcel, 0);
        if (updaterecurrenceoptions == null)
        {
            break MISSING_BLOCK_LABEL_99;
        }
        parcel.writeInt(1);
        updaterecurrenceoptions.writeToParcel(parcel, 0);
_L1:
        zzro.transact(9, parcel, parcel1, 0);
        parcel1.readException();
        parcel1.recycle();
        parcel.recycle();
        return;
        parcel.writeInt(0);
          goto _L1
        zzbbh1;
        parcel1.recycle();
        parcel.recycle();
        throw zzbbh1;
    }

    public final void zza(zzbbh zzbbh1, List list)
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("com.google.android.gms.reminders.internal.IRemindersService");
        parcel.writeStrongBinder(zzbbh1.asBinder());
        parcel.writeTypedList(list);
        zzro.transact(15, parcel, parcel1, 0);
        parcel1.readException();
        parcel1.recycle();
        parcel.recycle();
        return;
        zzbbh1;
        parcel1.recycle();
        parcel.recycle();
        throw zzbbh1;
    }

    public final void zzb(zzbbh zzbbh1, String s, TaskEntity taskentity, UpdateRecurrenceOptions updaterecurrenceoptions)
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("com.google.android.gms.reminders.internal.IRemindersService");
        parcel.writeStrongBinder(zzbbh1.asBinder());
        parcel.writeString(s);
        parcel.writeInt(1);
        taskentity.writeToParcel(parcel, 0);
        if (updaterecurrenceoptions == null)
        {
            break MISSING_BLOCK_LABEL_99;
        }
        parcel.writeInt(1);
        updaterecurrenceoptions.writeToParcel(parcel, 0);
_L1:
        zzro.transact(11, parcel, parcel1, 0);
        parcel1.readException();
        parcel1.recycle();
        parcel.recycle();
        return;
        parcel.writeInt(0);
          goto _L1
        zzbbh1;
        parcel1.recycle();
        parcel.recycle();
        throw zzbbh1;
    }

    public final void zzc(zzbbh zzbbh1, TaskEntity taskentity)
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("com.google.android.gms.reminders.internal.IRemindersService");
        parcel.writeStrongBinder(zzbbh1.asBinder());
        parcel.writeInt(1);
        taskentity.writeToParcel(parcel, 0);
        zzro.transact(8, parcel, parcel1, 0);
        parcel1.readException();
        parcel1.recycle();
        parcel.recycle();
        return;
        zzbbh1;
        parcel1.recycle();
        parcel.recycle();
        throw zzbbh1;
    }

    public final void zzd(zzbbh zzbbh1, TaskEntity taskentity)
        throws RemoteException
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("com.google.android.gms.reminders.internal.IRemindersService");
        parcel.writeStrongBinder(zzbbh1.asBinder());
        parcel.writeInt(1);
        taskentity.writeToParcel(parcel, 0);
        zzro.transact(12, parcel, parcel1, 0);
        parcel1.readException();
        parcel1.recycle();
        parcel.recycle();
        return;
        zzbbh1;
        parcel1.recycle();
        parcel.recycle();
        throw zzbbh1;
    }

    ity(IBinder ibinder)
    {
        zzro = ibinder;
    }
}
