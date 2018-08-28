// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.provider;

import android.os.Handler;
import android.os.HandlerThread;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public final class SelfDestructiveThread
{

    private android.os.Handler.Callback mCallback;
    public final int mDestructAfterMillisec = 10000;
    private int mGeneration;
    public Handler mHandler;
    public final Object mLock = new Object();
    private final int mPriority = 10;
    public HandlerThread mThread;
    private final String mThreadName;

    public SelfDestructiveThread(String s, int i, int j)
    {
        mCallback = new _cls1();
        mThreadName = s;
        mGeneration = 0;
    }

    final void post(Runnable runnable)
    {
        synchronized (mLock)
        {
            if (mThread == null)
            {
                mThread = new HandlerThread(mThreadName, mPriority);
                mThread.start();
                mHandler = new Handler(mThread.getLooper(), mCallback);
                mGeneration = mGeneration + 1;
            }
            mHandler.removeMessages(0);
            mHandler.sendMessage(mHandler.obtainMessage(1, runnable));
        }
        return;
        runnable;
        obj;
        JVM INSTR monitorexit ;
        throw runnable;
    }

    public final Object postAndWait(final Callable callable, int i)
        throws InterruptedException
    {
        final ReentrantLock lock;
        final Condition cond;
        final AtomicReference holder;
        final AtomicBoolean running;
        lock = new ReentrantLock();
        cond = lock.newCondition();
        holder = new AtomicReference();
        running = new AtomicBoolean(true);
        post(new _cls3());
        lock.lock();
        if (running.get())
        {
            break MISSING_BLOCK_LABEL_77;
        }
        callable = ((Callable) (holder.get()));
        lock.unlock();
        return callable;
        long l = TimeUnit.MILLISECONDS.toNanos(i);
_L4:
        long l1;
        try
        {
            l1 = cond.awaitNanos(l);
        }
        // Misplaced declaration of an exception variable
        catch (final Callable callable)
        {
            l1 = l;
        }
        if (running.get()) goto _L2; else goto _L1
_L1:
        callable = ((Callable) (holder.get()));
        lock.unlock();
        return callable;
_L2:
        l = l1;
        if (l1 > 0L) goto _L4; else goto _L3
_L3:
        throw new InterruptedException("timeout");
        callable;
        lock.unlock();
        throw callable;
    }

    private class _cls1
        implements android.os.Handler.Callback
    {

        private final SelfDestructiveThread this$0;

        public final boolean handleMessage(Message message)
        {
            switch (message.what)
            {
            default:
                return true;

            case 1: // '\001'
                SelfDestructiveThread selfdestructivethread = SelfDestructiveThread.this;
                ((Runnable)message.obj).run();
                synchronized (selfdestructivethread.mLock)
                {
                    selfdestructivethread.mHandler.removeMessages(0);
                    selfdestructivethread.mHandler.sendMessageDelayed(selfdestructivethread.mHandler.obtainMessage(0), selfdestructivethread.mDestructAfterMillisec);
                }
                return true;

            case 0: // '\0'
                obj = SelfDestructiveThread.this;
                break;
            }
            break MISSING_BLOCK_LABEL_97;
            exception;
            message;
            JVM INSTR monitorexit ;
            throw exception;
            synchronized (((SelfDestructiveThread) (obj)).mLock)
            {
                if (!((SelfDestructiveThread) (obj)).mHandler.hasMessages(1))
                {
                    break MISSING_BLOCK_LABEL_124;
                }
            }
            return true;
            obj;
            message;
            JVM INSTR monitorexit ;
            throw obj;
            ((SelfDestructiveThread) (obj)).mThread.quit();
            obj.mThread = null;
            obj.mHandler = null;
            message;
            JVM INSTR monitorexit ;
            return true;
        }

        _cls1()
        {
            this$0 = SelfDestructiveThread.this;
            super();
        }
    }


    private class _cls3
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
                holder.set(callable.call());
            }
            catch (Exception exception1) { }
            lock.lock();
            running.set(false);
            cond.signal();
            lock.unlock();
            return;
            exception;
            lock.unlock();
            throw exception;
        }

        _cls3()
        {
            holder = atomicreference;
            callable = callable1;
            lock = reentrantlock;
            running = atomicboolean;
            cond = condition;
            super();
        }
    }

}
