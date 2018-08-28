// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.arch.core.executor;


// Referenced classes of package android.arch.core.executor:
//            TaskExecutor, DefaultTaskExecutor

public class ArchTaskExecutor extends TaskExecutor
{

    private static volatile ArchTaskExecutor sInstance;
    private TaskExecutor mDefaultTaskExecutor;
    private TaskExecutor mDelegate;

    private ArchTaskExecutor()
    {
        mDefaultTaskExecutor = new DefaultTaskExecutor();
        mDelegate = mDefaultTaskExecutor;
    }

    public static ArchTaskExecutor getInstance()
    {
        if (sInstance != null)
        {
            return sInstance;
        }
        android/arch/core/executor/ArchTaskExecutor;
        JVM INSTR monitorenter ;
        if (sInstance == null)
        {
            sInstance = new ArchTaskExecutor();
        }
        android/arch/core/executor/ArchTaskExecutor;
        JVM INSTR monitorexit ;
        return sInstance;
        Exception exception;
        exception;
        android/arch/core/executor/ArchTaskExecutor;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final void executeOnDiskIO(Runnable runnable)
    {
        mDelegate.executeOnDiskIO(runnable);
    }

    public final boolean isMainThread()
    {
        return mDelegate.isMainThread();
    }

    public final void postToMainThread(Runnable runnable)
    {
        mDelegate.postToMainThread(runnable);
    }

    static 
    {
        new _cls1();
        new _cls2();
    }

    private class _cls1
        implements Executor
    {

        public final void execute(Runnable runnable)
        {
            ArchTaskExecutor.getInstance().postToMainThread(runnable);
        }

        _cls1()
        {
        }
    }


    private class _cls2
        implements Executor
    {

        public final void execute(Runnable runnable)
        {
            ArchTaskExecutor.getInstance().executeOnDiskIO(runnable);
        }

        _cls2()
        {
        }
    }

}
