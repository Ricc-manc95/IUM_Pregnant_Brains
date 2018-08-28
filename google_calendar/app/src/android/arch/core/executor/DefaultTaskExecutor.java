// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.arch.core.executor;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Referenced classes of package android.arch.core.executor:
//            TaskExecutor

public final class DefaultTaskExecutor extends TaskExecutor
{

    private ExecutorService mDiskIO;
    private final Object mLock = new Object();
    private volatile Handler mMainHandler;

    public DefaultTaskExecutor()
    {
        mDiskIO = Executors.newFixedThreadPool(2);
    }

    public final void executeOnDiskIO(Runnable runnable)
    {
        mDiskIO.execute(runnable);
    }

    public final boolean isMainThread()
    {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    public final void postToMainThread(Runnable runnable)
    {
        if (mMainHandler == null)
        {
            synchronized (mLock)
            {
                if (mMainHandler == null)
                {
                    mMainHandler = new Handler(Looper.getMainLooper());
                }
            }
        }
        mMainHandler.post(runnable);
        return;
        runnable;
        obj;
        JVM INSTR monitorexit ;
        throw runnable;
    }
}
