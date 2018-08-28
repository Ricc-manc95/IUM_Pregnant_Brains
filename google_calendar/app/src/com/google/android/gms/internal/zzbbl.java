// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzl;
import com.google.android.gms.reminders.model.Task;
import com.google.android.gms.reminders.model.TaskEntity;
import com.google.android.gms.reminders.zzb;

// Referenced classes of package com.google.android.gms.internal:
//            zzbbj, zzzv

public final class zzbbl extends zzl
{

    public final zzg zzaKD;

    public zzbbl(Context context, Looper looper, zzg zzg1, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener)
    {
        super(context, looper, 18, zzg1, connectioncallbacks, onconnectionfailedlistener);
        zzaKD = zzg1;
    }

    public final void disconnect()
    {
        if (isConnected())
        {
            try
            {
                ((zzbbj)zzyP()).zzTv();
            }
            catch (DeadObjectException deadobjectexception)
            {
                Log.e("Reminders", "Dead object exception when clearing listeners", deadobjectexception);
            }
            catch (RemoteException remoteexception)
            {
                Log.e("Reminders", "Remote exception when clearing listeners", remoteexception);
            }
        }
        super.disconnect();
    }

    public final void zza(zzyq.zzb zzb1, Task task, zzzv zzzv, zzb zzb2)
        throws RemoteException
    {
        Object obj = zzaKD;
        if (((zzg) (obj)).zzafe != null)
        {
            obj = ((zzg) (obj)).zzafe.name;
        } else
        {
            obj = null;
        }
        if (TextUtils.isEmpty(((CharSequence) (obj))))
        {
            throw new IllegalArgumentException("Given String is empty or null");
        }
        if (task == null)
        {
            throw new NullPointerException("null reference");
        }
        if (zzb2 == null)
        {
            throw new NullPointerException("null reference");
        } else
        {
            ((zzbbj)zzyP()).zza(new zzb(zzb1, zzzv), new TaskEntity(task), zzb2);
            return;
        }
    }

    protected final String zzeD()
    {
        return "com.google.android.gms.reminders.service.START";
    }

    protected final String zzeE()
    {
        return "com.google.android.gms.reminders.internal.IRemindersService";
    }

    protected final IInterface zzi(IBinder ibinder)
    {
        if (ibinder == null)
        {
            return null;
        }
        IInterface iinterface = ibinder.queryLocalInterface("com.google.android.gms.reminders.internal.IRemindersService");
        if (iinterface != null && (iinterface instanceof zzbbj))
        {
            return (zzbbj)iinterface;
        } else
        {
            return new zzbbj.zza.zza(ibinder);
        }
    }

    public final boolean zzyQ()
    {
        return true;
    }

    private class zzb extends zzd
    {
        private class zzd extends zzbbg
        {

            private final zzyq.zzb zzcgn;

            public final void zzc(Status status)
            {
                zzcgn.setResult(status);
            }

            public zzd(zzyq.zzb zzb1)
            {
                zzcgn = zzb1;
            }
        }


        public final zzzv zzaZF;

        public final void onReminderCreated(String s, String s1)
        {
            if (zzaZF != null)
            {
                zzzv zzzv1 = zzaZF;
                class _cls1
                    implements zzzv.zzc
                {

                    private final String zzcgj;
                    private final String zzcgk;
                    private final zzb zzcgl;

                    public final void zzw(Object obj)
                    {
                        ((com.google.android.gms.reminders.RemindersApi.ReminderCreatedListener)obj).onReminderCreated$5166KOBMC4NMOOBECSNL6T3ID5N6EEQCD9GNCO9FDHGMSPPFADQ74QBECSTIILG_0();
                        zzcgl.zzaZF.mListener = null;
                    }

                    public final void zzxk()
                    {
                        Log.e("Reminders", "Notify reminder created listener failed");
                    }

                _cls1(String s, String s1)
                {
                    zzcgl = zzb.this;
                    zzcgj = s;
                    zzcgk = s1;
                    super();
                }
                }

                s = new _cls1(s, s1);
                if (s == null)
                {
                    throw new NullPointerException(String.valueOf("Notifier must not be null"));
                }
                s = zzzv1.zzaMJ.obtainMessage(1, s);
                zzzv1.zzaMJ.sendMessage(s);
            }
        }

        public zzb(zzyq.zzb zzb1, zzzv zzzv1)
        {
            super(zzb1);
            zzaZF = zzzv1;
        }
    }

}
