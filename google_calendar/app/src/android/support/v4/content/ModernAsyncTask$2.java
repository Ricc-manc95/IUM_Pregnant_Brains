// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.content;

import android.os.Binder;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import java.util.concurrent.atomic.AtomicBoolean;

// Referenced classes of package android.support.v4.content:
//            ModernAsyncTask

final class rkerRunnable extends rkerRunnable
{

    private final ModernAsyncTask this$0;

    public final Object call()
        throws Exception
    {
        Object obj;
        Object obj1;
        Object obj2;
        Object obj3;
        mTaskInvoked.set(true);
        obj3 = null;
        obj2 = null;
        obj1 = obj2;
        obj = obj3;
        Process.setThreadPriority(10);
        obj1 = obj2;
        obj = obj3;
        obj2 = doInBackground(mParams);
        obj1 = obj2;
        obj = obj2;
        Binder.flushPendingCommands();
        obj = ModernAsyncTask.this;
        ModernAsyncTask.getHandler().obtainMessage(1, new yncTaskResult(((ModernAsyncTask) (obj)), new Object[] {
            obj2
        })).sendToTarget();
        return obj2;
        Throwable throwable;
        throwable;
        obj = obj1;
        mCancelled.set(true);
        obj = obj1;
        throw throwable;
        Exception exception;
        exception;
        ModernAsyncTask modernasynctask = ModernAsyncTask.this;
        ModernAsyncTask.getHandler().obtainMessage(1, new yncTaskResult(modernasynctask, new Object[] {
            obj
        })).sendToTarget();
        throw exception;
    }

    yncTaskResult()
    {
        this$0 = ModernAsyncTask.this;
        super();
    }
}
