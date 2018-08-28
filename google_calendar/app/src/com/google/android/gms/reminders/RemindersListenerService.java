// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.util.zzx;
import com.google.android.gms.reminders.model.ReminderEvent;
import com.google.android.gms.reminders.model.ReminderEventBuffer;
import com.google.android.gms.reminders.model.ReminderEventEntity;
import com.google.android.gms.reminders.model.SnoozePresetChangedEventBuffer;
import com.google.android.gms.reminders.model.Task;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class RemindersListenerService extends Service
{

    public String mPackageName;
    private List zzblv;
    private Handler zzcgc;
    private volatile int zzsC;
    private IBinder zzsE;
    public Object zzsF;
    public boolean zzsG;

    public RemindersListenerService()
    {
        zzsC = -1;
        zzsF = new Object();
        zzblv = Collections.synchronizedList(new ArrayList());
    }

    static void zzb(RemindersListenerService reminderslistenerservice)
        throws SecurityException
    {
label0:
        {
            int i = Binder.getCallingUid();
            if (i != reminderslistenerservice.zzsC)
            {
                if (!zzx.zze(reminderslistenerservice, i))
                {
                    break label0;
                }
                reminderslistenerservice.zzsC = i;
            }
            return;
        }
        throw new SecurityException("Caller is not GooglePlayServices");
    }

    private final DataHolder zztl(int i)
    {
        List list = zzblv;
        list;
        JVM INSTR monitorenter ;
        if (i < 0)
        {
            break MISSING_BLOCK_LABEL_42;
        }
        DataHolder dataholder;
        if (i >= zzblv.size())
        {
            break MISSING_BLOCK_LABEL_42;
        }
        dataholder = (DataHolder)zzblv.get(i);
        return dataholder;
        list;
        JVM INSTR monitorexit ;
        return null;
        Exception exception;
        exception;
        list;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final IBinder onBind(Intent intent)
    {
        if ("com.google.android.gms.reminders.BIND_LISTENER".equals(intent.getAction()))
        {
            return zzsE;
        } else
        {
            return null;
        }
    }

    public void onCreate()
    {
        super.onCreate();
        Object obj = String.valueOf(getPackageName());
        if (((String) (obj)).length() != 0)
        {
            "onCreate: ".concat(((String) (obj)));
        } else
        {
            new String("onCreate: ");
        }
        mPackageName = getPackageName();
        obj = new HandlerThread("RemindersLS");
        ((HandlerThread) (obj)).start();
        zzcgc = new zzb(((HandlerThread) (obj)).getLooper());
        zzsE = new zza();
    }

    public void onDestroy()
    {
        synchronized (zzsF)
        {
            zzsG = true;
            zzcgc.getLooper().quit();
        }
        super.onDestroy();
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final void onHandleIntentInternal(Intent intent)
    {
        int i;
        int j;
        i = 0;
        j = intent.getIntExtra("api_id", 0);
        if (j != 1) goto _L2; else goto _L1
_L1:
        intent = (ReminderEventEntity)intent.getParcelableExtra("reminder_event");
        String s = String.valueOf(((ReminderEventEntity) (intent)).zzcjb.getTitle());
        if (s.length() != 0)
        {
            "onReminderFiredInternal Handling thread:".concat(s);
        } else
        {
            new String("onReminderFiredInternal Handling thread:");
        }
        onReminderFired(intent);
_L4:
        return;
_L2:
        if (j != 2)
        {
            continue; /* Loop/switch isn't completed */
        }
        i = intent.getIntExtra("data_holder_id", -1);
        if (zztl(i) == null) goto _L4; else goto _L3
_L3:
        intent = new ReminderEventBuffer((DataHolder)zzblv.get(i));
        if (((AbstractDataBuffer) (intent)).zzaKT == null)
        {
            i = 0;
        } else
        {
            i = ((AbstractDataBuffer) (intent)).zzaKT.zzaNZ;
        }
        (new StringBuilder(54)).append("onRemindersChangedInternal Handling thread:").append(i);
        onRemindersChanged(intent);
        if (((AbstractDataBuffer) (intent)).zzaKT == null) goto _L4; else goto _L5
_L5:
        ((AbstractDataBuffer) (intent)).zzaKT.close();
        return;
        Exception exception;
        exception;
        if (((AbstractDataBuffer) (intent)).zzaKT != null)
        {
            ((AbstractDataBuffer) (intent)).zzaKT.close();
        }
        throw exception;
        if (j != 3) goto _L4; else goto _L6
_L6:
        j = intent.getIntExtra("data_holder_id", -1);
        if (zztl(j) == null) goto _L4; else goto _L7
_L7:
        intent = new SnoozePresetChangedEventBuffer((DataHolder)zzblv.get(j));
        if (((AbstractDataBuffer) (intent)).zzaKT != null)
        {
            i = ((AbstractDataBuffer) (intent)).zzaKT.zzaNZ;
        }
        (new StringBuilder(54)).append("onRemindersChangedInternal Handling thread:").append(i);
        onSnoozePresetChanged$51666RRD5TJMURR7DHIIUOBECHP6UQB45TJMQSPFE9IMQQBECHIN4SPFDLNM8PBC5T9MSRRFF9IL0SJ5EDIN8GR8C5N6EPB48LR6ARJK89QMCPJ5E8TIILG_0();
        if (((AbstractDataBuffer) (intent)).zzaKT == null) goto _L4; else goto _L8
_L8:
        ((AbstractDataBuffer) (intent)).zzaKT.close();
        return;
        exception;
        if (((AbstractDataBuffer) (intent)).zzaKT != null)
        {
            ((AbstractDataBuffer) (intent)).zzaKT.close();
        }
        throw exception;
    }

    public abstract void onReminderFired(ReminderEvent reminderevent);

    public abstract void onRemindersChanged(ReminderEventBuffer remindereventbuffer);

    public abstract void onSnoozePresetChanged$51666RRD5TJMURR7DHIIUOBECHP6UQB45TJMQSPFE9IMQQBECHIN4SPFDLNM8PBC5T9MSRRFF9IL0SJ5EDIN8GR8C5N6EPB48LR6ARJK89QMCPJ5E8TIILG_0();

    public int onStartCommand(Intent intent, int i, int j)
    {
        Object obj = String.valueOf(intent);
        (new StringBuilder(String.valueOf(obj).length() + 52)).append("onStartCommand:").append(((String) (obj))).append(" flag:").append(i).append(" startId:").append(j);
        obj = zzcgc.obtainMessage();
        obj.arg1 = j;
        obj.obj = intent;
        zzcgc.sendMessage(((Message) (obj)));
        return 3;
    }

    final int zzav(DataHolder dataholder)
    {
        int i;
        synchronized (zzblv)
        {
            i = zzblv.size();
            zzblv.add(dataholder);
        }
        return i;
        dataholder;
        list;
        JVM INSTR monitorexit ;
        throw dataholder;
    }

    private class zzb extends Handler
    {

        private final RemindersListenerService zzcgd;

        public final void handleMessage(Message message)
        {
            zzcgd.onHandleIntentInternal((Intent)message.obj);
            zzcgd.stopSelf(message.arg1);
        }

        public zzb(Looper looper)
        {
            zzcgd = RemindersListenerService.this;
            super(looper);
        }
    }


    private class zza extends com.google.android.gms.internal.zzbbi.zza
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

        zza()
        {
            zzcgd = RemindersListenerService.this;
            super();
        }
    }

}
