// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.provider;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

// Referenced classes of package android.support.v4.provider:
//            SelfDestructiveThread

final class val.cond
    implements Runnable
{

    private final Callable val$callable;
    private final Condition val$cond;
    private final AtomicReference val$holder;
    private final ReentrantLock val$lock;
    private final AtomicBoolean val$running;

    public final void run()
    {
        Exception exception;
        try
        {
            val$holder.set(val$callable.call());
        }
        catch (Exception exception1) { }
        val$lock.lock();
        val$running.set(false);
        val$cond.signal();
        val$lock.unlock();
        return;
        exception;
        val$lock.unlock();
        throw exception;
    }

    _cls9(Condition condition)
    {
        val$holder = atomicreference;
        val$callable = callable1;
        val$lock = reentrantlock;
        val$running = atomicboolean;
        val$cond = condition;
        super();
    }
}
