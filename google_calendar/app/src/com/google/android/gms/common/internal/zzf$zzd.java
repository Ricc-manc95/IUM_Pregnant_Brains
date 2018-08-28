// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.internal;

import android.app.PendingIntent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import java.util.concurrent.atomic.AtomicInteger;

// Referenced classes of package com.google.android.gms.common.internal:
//            zzf

final class zzaPH extends Handler
{

    private final zzf zzaPH;

    public final void handleMessage(Message message)
    {
        Object obj = null;
        boolean flag2 = true;
        if (zzaPH.zzaPF.get() != message.arg1)
        {
            boolean flag = flag2;
            if (message.what != 2)
            {
                flag = flag2;
                if (message.what != 1)
                {
                    if (message.what == 5)
                    {
                        flag = flag2;
                    } else
                    {
                        flag = false;
                    }
                }
            }
            if (flag)
            {
                message = (F)message.obj;
                message.zzyT();
                message.unregister();
            }
            return;
        }
        if ((message.what == 1 || message.what == 5) && !zzaPH.isConnecting())
        {
            message = (nnecting)message.obj;
            message.zzyT();
            message.unregister();
            return;
        }
        if (message.what == 3)
        {
            if (message.obj instanceof PendingIntent)
            {
                obj = (PendingIntent)message.obj;
            }
            message = new ConnectionResult(message.arg2, ((PendingIntent) (obj)));
            zzaPH.zzaPw.zzg(message);
            obj = zzaPH;
            obj.zzaPr = ((ConnectionResult) (message)).zzaEP;
            obj.zzaPs = System.currentTimeMillis();
            return;
        }
        if (message.what == 4)
        {
            zzf.zza(zzaPH, 4, null);
            if (zzf.zzb(zzaPH) != null)
            {
                zzf.zzb(zzaPH).onConnectionSuspended(message.arg2);
            }
            obj = zzaPH;
            obj.zzaPo = message.arg2;
            obj.zzaPp = System.currentTimeMillis();
            zzf.zza(zzaPH, 4, 1, null);
            return;
        }
        if (message.what == 2 && !zzaPH.isConnected())
        {
            message = (nnected)message.obj;
            message.zzyT();
            message.unregister();
            return;
        }
        boolean flag1;
        if (message.what == 2 || message.what == 1 || message.what == 5)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!flag1)
        {
            break MISSING_BLOCK_LABEL_466;
        }
        synchronized ((unregister)message.obj)
        {
            obj = ((unregister) (message)).mListener;
            if (((mListener) (message)).zzaPI)
            {
                String s = String.valueOf(message);
                Log.w("GmsClient", (new StringBuilder(String.valueOf(s).length() + 47)).append("Callback proxy ").append(s).append(" being reused. This is not safe.").toString());
            }
        }
        if (obj != null)
        {
            try
            {
                message.zzA(obj);
            }
            catch (RuntimeException runtimeexception)
            {
                message.zzyT();
                throw runtimeexception;
            }
        } else
        {
            message.zzyT();
        }
        message;
        JVM INSTR monitorenter ;
        message.zzaPI = true;
        message;
        JVM INSTR monitorexit ;
        message.unregister();
        return;
        exception;
        message;
        JVM INSTR monitorexit ;
        throw exception;
        Exception exception1;
        exception1;
        message;
        JVM INSTR monitorexit ;
        throw exception1;
        int i = message.what;
        Log.wtf("GmsClient", (new StringBuilder(45)).append("Don't know how to handle message: ").append(i).toString(), new Exception());
        return;
    }

    public (zzf zzf1, Looper looper)
    {
        zzaPH = zzf1;
        super(looper);
    }
}
