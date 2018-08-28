// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzs;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

// Referenced classes of package com.google.android.gms.internal:
//            zzaap

public abstract class zzys extends PendingResult
{

    public static final ThreadLocal zzaJY = new _cls1();
    private boolean zzI;
    public final Object zzaJZ;
    private Result zzaJp;
    private final zza zzaKa;
    private final ArrayList zzaKb;
    private ResultCallback zzaKc;
    public final AtomicReference zzaKd;
    private volatile boolean zzaKf;
    private boolean zzaKg;
    public zzs zzaKh;
    private volatile zzaap zzaKj;
    public boolean zzaKk;
    private final WeakReference zzayV;
    public final CountDownLatch zztA;

    zzys()
    {
        zzaJZ = new Object();
        zztA = new CountDownLatch(1);
        zzaKb = new ArrayList();
        zzaKd = new AtomicReference();
        zzaKk = false;
        zzaKa = new zza(Looper.getMainLooper());
        zzayV = new WeakReference(null);
    }

    protected zzys(Looper looper)
    {
        zzaJZ = new Object();
        zztA = new CountDownLatch(1);
        zzaKb = new ArrayList();
        zzaKd = new AtomicReference();
        zzaKk = false;
        zzaKa = new zza(looper);
        zzayV = new WeakReference(null);
    }

    protected zzys(GoogleApiClient googleapiclient)
    {
        zzaJZ = new Object();
        zztA = new CountDownLatch(1);
        zzaKb = new ArrayList();
        zzaKd = new AtomicReference();
        zzaKk = false;
        Looper looper;
        if (googleapiclient != null)
        {
            looper = googleapiclient.getLooper();
        } else
        {
            looper = Looper.getMainLooper();
        }
        zzaKa = new zza(looper);
        zzayV = new WeakReference(googleapiclient);
    }

    private final Result get()
    {
        boolean flag2 = true;
        Object obj = zzaJZ;
        obj;
        JVM INSTR monitorenter ;
        Exception exception;
        boolean flag;
        if (!zzaKf)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            break MISSING_BLOCK_LABEL_46;
        }
        throw new IllegalStateException(String.valueOf("Result has already been consumed."));
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        Result result;
        boolean flag1;
        if (zztA.getCount() == 0L)
        {
            flag1 = flag2;
        } else
        {
            flag1 = false;
        }
        if (flag1)
        {
            break MISSING_BLOCK_LABEL_78;
        }
        throw new IllegalStateException(String.valueOf("Result is not ready."));
        result = zzaJp;
        zzaJp = null;
        zzaKc = null;
        zzaKf = true;
        obj;
        JVM INSTR monitorexit ;
        obj = (zzaaq.zzb)zzaKd.getAndSet(null);
        if (obj != null)
        {
            ((zzaaq.zzb) (obj)).zzc(this);
        }
        return result;
    }

    static Result zza(zzys zzys1)
    {
        return zzys1.zzaJp;
    }

    private final void zzc(Result result)
    {
        zzaJp = result;
        zzaKh = null;
        zztA.countDown();
        zzaJp.getStatus();
        if (!zzI) goto _L2; else goto _L1
_L1:
        zzaKc = null;
_L4:
        result = (ArrayList)zzaKb;
        int j = result.size();
        for (int i = 0; i < j;)
        {
            Object obj = result.get(i);
            i++;
            ((com.google.android.gms.common.api.PendingResult.zza)obj).zzJ$51666RRD5TJMURR7DHIIUOBECHP6UQB45TJMQSPFCDNMQRBFDONM2S395T9N8OBKELPJMAAM0();
        }

        break; /* Loop/switch isn't completed */
_L2:
        if (zzaKc == null)
        {
            if (zzaJp instanceof Releasable)
            {
                new zzb();
            }
        } else
        {
            zzaKa.removeMessages(2);
            result = zzaKa;
            result.sendMessage(result.obtainMessage(1, new Pair(zzaKc, get())));
        }
        if (true) goto _L4; else goto _L3
_L3:
        zzaKb.clear();
        return;
    }

    public static void zzd(Result result)
    {
        if (!(result instanceof Releasable))
        {
            break MISSING_BLOCK_LABEL_16;
        }
        ((Releasable)result).release();
        return;
        RuntimeException runtimeexception;
        runtimeexception;
        result = String.valueOf(result);
        Log.w("BasePendingResult", (new StringBuilder(String.valueOf(result).length() + 18)).append("Unable to release ").append(result).toString(), runtimeexception);
        return;
    }

    public final Result await()
    {
        boolean flag1 = true;
        boolean flag;
        if (Looper.myLooper() != Looper.getMainLooper())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("await must not be called on the UI thread"));
        }
        if (!zzaKf)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("Result has already been consumed"));
        }
        zzaap zzaap = zzaKj;
        try
        {
            zztA.await();
        }
        catch (InterruptedException interruptedexception)
        {
            zzN(Status.zzaJu);
        }
        if (zztA.getCount() == 0L)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("Result is not ready."));
        } else
        {
            return get();
        }
    }

    public final Result await(long l, TimeUnit timeunit)
    {
        boolean flag1 = true;
        boolean flag;
        if (l <= 0L || Looper.myLooper() != Looper.getMainLooper())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("await must not be called on the UI thread when time is greater than zero."));
        }
        if (!zzaKf)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("Result has already been consumed."));
        }
        zzaap zzaap = zzaKj;
        try
        {
            if (!zztA.await(l, timeunit))
            {
                zzN(Status.zzaJw);
            }
        }
        // Misplaced declaration of an exception variable
        catch (TimeUnit timeunit)
        {
            zzN(Status.zzaJu);
        }
        if (zztA.getCount() == 0L)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("Result is not ready."));
        } else
        {
            return get();
        }
    }

    public final void cancel()
    {
label0:
        {
            synchronized (zzaJZ)
            {
                if (!zzI && !zzaKf)
                {
                    break label0;
                }
            }
            return;
        }
        zzs zzs1 = zzaKh;
        if (zzs1 == null)
        {
            break MISSING_BLOCK_LABEL_42;
        }
        try
        {
            zzaKh.cancel();
        }
        catch (RemoteException remoteexception) { }
        zzd(zzaJp);
        zzI = true;
        zzc(zzb(Status.zzaJx));
        obj;
        JVM INSTR monitorexit ;
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final boolean isCanceled()
    {
        boolean flag;
        synchronized (zzaJZ)
        {
            flag = zzI;
        }
        return flag;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final void setResultCallback(ResultCallback resultcallback)
    {
        boolean flag2 = true;
        Object obj = zzaJZ;
        obj;
        JVM INSTR monitorenter ;
        if (resultcallback != null)
        {
            break MISSING_BLOCK_LABEL_22;
        }
        zzaKc = null;
        obj;
        JVM INSTR monitorexit ;
        return;
        boolean flag;
        if (!zzaKf)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            break MISSING_BLOCK_LABEL_61;
        }
        throw new IllegalStateException(String.valueOf("Result has already been consumed."));
        resultcallback;
        obj;
        JVM INSTR monitorexit ;
        throw resultcallback;
        zzaap zzaap = zzaKj;
        if (!isCanceled())
        {
            break MISSING_BLOCK_LABEL_76;
        }
        obj;
        JVM INSTR monitorexit ;
        return;
        zza zza1;
        boolean flag1;
        if (zztA.getCount() == 0L)
        {
            flag1 = flag2;
        } else
        {
            flag1 = false;
        }
        if (!flag1)
        {
            break MISSING_BLOCK_LABEL_127;
        }
        zza1 = zzaKa;
        zza1.sendMessage(zza1.obtainMessage(1, new Pair(resultcallback, get())));
_L2:
        obj;
        JVM INSTR monitorexit ;
        return;
        zzaKc = resultcallback;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public final void zzN(Status status)
    {
        boolean flag = true;
        Object obj = zzaJZ;
        obj;
        JVM INSTR monitorenter ;
        if (zztA.getCount() != 0L)
        {
            flag = false;
        }
        if (flag)
        {
            break MISSING_BLOCK_LABEL_39;
        }
        zzb(zzb(status));
        zzaKg = true;
        obj;
        JVM INSTR monitorexit ;
        return;
        status;
        obj;
        JVM INSTR monitorexit ;
        throw status;
    }

    public final void zza(com.google.android.gms.common.api.PendingResult.zza zza1)
    {
        boolean flag2;
        flag2 = true;
        boolean flag;
        if (!zzaKf)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("Result has already been consumed."));
        }
        Object obj = zzaJZ;
        obj;
        JVM INSTR monitorenter ;
        boolean flag1;
        if (zztA.getCount() == 0L)
        {
            flag1 = flag2;
        } else
        {
            flag1 = false;
        }
        if (!flag1)
        {
            break MISSING_BLOCK_LABEL_79;
        }
        zzaJp.getStatus();
        zza1.zzJ$51666RRD5TJMURR7DHIIUOBECHP6UQB45TJMQSPFCDNMQRBFDONM2S395T9N8OBKELPJMAAM0();
_L2:
        obj;
        JVM INSTR monitorexit ;
        return;
        zzaKb.add(zza1);
        if (true) goto _L2; else goto _L1
_L1:
        zza1;
        obj;
        JVM INSTR monitorexit ;
        throw zza1;
    }

    public abstract Result zzb(Status status);

    public final void zzb(Result result)
    {
        boolean flag1 = true;
        Object obj = zzaJZ;
        obj;
        JVM INSTR monitorenter ;
        boolean flag;
        if (zzaKg || zzI) goto _L2; else goto _L1
_L5:
        if (zztA.getCount() == 0L)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        break MISSING_BLOCK_LABEL_135;
_L6:
        if (flag) goto _L4; else goto _L3
_L3:
        throw new IllegalStateException(String.valueOf("Results have already been set"));
        result;
        obj;
        JVM INSTR monitorexit ;
        throw result;
_L2:
        zzd(result);
        obj;
        JVM INSTR monitorexit ;
        return;
_L4:
        if (!zzaKf)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            break MISSING_BLOCK_LABEL_120;
        }
        throw new IllegalStateException(String.valueOf("Result has already been consumed"));
        zzc(result);
        obj;
        JVM INSTR monitorexit ;
        return;
_L1:
        if (zztA.getCount() == 0L)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag);
          goto _L5
        if (!flag)
        {
            flag = true;
        } else
        {
            flag = false;
        }
          goto _L6
    }

    public final Integer zzwD()
    {
        return null;
    }

    public final boolean zzwO()
    {
        boolean flag;
        synchronized (zzaJZ)
        {
            if ((GoogleApiClient)zzayV.get() == null || !zzaKk)
            {
                cancel();
            }
            flag = isCanceled();
        }
        return flag;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }


    private class zza extends Handler
    {

        public final void handleMessage(Message message)
        {
            switch (message.what)
            {
            default:
                int i = message.what;
                Log.wtf("BasePendingResult", (new StringBuilder(45)).append("Don't know how to handle message: ").append(i).toString(), new Exception());
                return;

            case 1: // '\001'
                Object obj = (Pair)message.obj;
                message = (ResultCallback)((Pair) (obj)).first;
                obj = (Result)((Pair) (obj)).second;
                try
                {
                    message.onResult(((Result) (obj)));
                    return;
                }
                // Misplaced declaration of an exception variable
                catch (Message message)
                {
                    zzys.zzd(((Result) (obj)));
                }
                throw message;

            case 2: // '\002'
                ((zzys)message.obj).zzN(Status.zzaJw);
                return;
            }
        }

        public zza()
        {
            this(Looper.getMainLooper());
        }

        public zza(Looper looper)
        {
            super(looper);
        }
    }


    private class zzb
    {

        private final zzys zzaKl;

        protected final void finalize()
            throws Throwable
        {
            zzys.zzd(zzys.zza(zzaKl));
            super.finalize();
        }

        zzb()
        {
            zzaKl = zzys.this;
            super();
        }
    }


    private class _cls1 extends ThreadLocal
    {

        protected final Object initialValue()
        {
            return Boolean.valueOf(false);
        }

        _cls1()
        {
        }
    }

}
