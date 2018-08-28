// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;

import com.google.common.base.Ascii;
import com.google.common.base.Platform;
import com.google.common.base.Strings;
import com.google.common.base.Throwables;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

// Referenced classes of package com.google.common.util.concurrent:
//            FluentFuture, Uninterruptibles, ListenableFuture

public class AbstractFuture extends FluentFuture
{
    static abstract class AtomicHelper
    {

        abstract boolean casListeners(AbstractFuture abstractfuture, Listener listener, Listener listener1);

        abstract boolean casValue(AbstractFuture abstractfuture, Object obj, Object obj1);

        abstract boolean casWaiters(AbstractFuture abstractfuture, Waiter waiter, Waiter waiter1);

        abstract void putNext(Waiter waiter, Waiter waiter1);

        abstract void putThread(Waiter waiter, Thread thread);

        AtomicHelper()
        {
        }
    }

    static final class Cancellation
    {

        public static final Cancellation CAUSELESS_CANCELLED;
        public static final Cancellation CAUSELESS_INTERRUPTED;
        public final Throwable cause;
        public final boolean wasInterrupted;

        static 
        {
            if (AbstractFuture.GENERATE_CANCELLATION_CAUSES)
            {
                CAUSELESS_CANCELLED = null;
                CAUSELESS_INTERRUPTED = null;
            } else
            {
                CAUSELESS_CANCELLED = new Cancellation(false, null);
                CAUSELESS_INTERRUPTED = new Cancellation(true, null);
            }
        }

        Cancellation(boolean flag, Throwable throwable)
        {
            wasInterrupted = flag;
            cause = throwable;
        }
    }

    static final class Failure
    {

        public static final Failure FALLBACK_INSTANCE = new Failure(new Throwable("Failure occurred while trying to finish a future.") {

            public Throwable fillInStackTrace()
            {
                this;
                JVM INSTR monitorenter ;
                return this;
            }

        });
        public final Throwable exception;


        Failure(Throwable throwable)
        {
            if (throwable == null)
            {
                throw new NullPointerException();
            } else
            {
                exception = (Throwable)throwable;
                return;
            }
        }
    }

    static final class Listener
    {

        public static final Listener TOMBSTONE = new Listener(null, null);
        public final Executor executor;
        public Listener next;
        public final Runnable task;


        Listener(Runnable runnable, Executor executor1)
        {
            task = runnable;
            executor = executor1;
        }
    }

    static final class SafeAtomicHelper extends AtomicHelper
    {

        public final AtomicReferenceFieldUpdater listenersUpdater;
        public final AtomicReferenceFieldUpdater valueUpdater;
        public final AtomicReferenceFieldUpdater waiterNextUpdater;
        public final AtomicReferenceFieldUpdater waiterThreadUpdater;
        public final AtomicReferenceFieldUpdater waitersUpdater;

        final boolean casListeners(AbstractFuture abstractfuture, Listener listener, Listener listener1)
        {
            return listenersUpdater.compareAndSet(abstractfuture, listener, listener1);
        }

        final boolean casValue(AbstractFuture abstractfuture, Object obj, Object obj1)
        {
            return valueUpdater.compareAndSet(abstractfuture, obj, obj1);
        }

        final boolean casWaiters(AbstractFuture abstractfuture, Waiter waiter, Waiter waiter1)
        {
            return waitersUpdater.compareAndSet(abstractfuture, waiter, waiter1);
        }

        final void putNext(Waiter waiter, Waiter waiter1)
        {
            waiterNextUpdater.lazySet(waiter, waiter1);
        }

        final void putThread(Waiter waiter, Thread thread)
        {
            waiterThreadUpdater.lazySet(waiter, thread);
        }

        SafeAtomicHelper(AtomicReferenceFieldUpdater atomicreferencefieldupdater, AtomicReferenceFieldUpdater atomicreferencefieldupdater1, AtomicReferenceFieldUpdater atomicreferencefieldupdater2, AtomicReferenceFieldUpdater atomicreferencefieldupdater3, AtomicReferenceFieldUpdater atomicreferencefieldupdater4)
        {
            waiterThreadUpdater = atomicreferencefieldupdater;
            waiterNextUpdater = atomicreferencefieldupdater1;
            waitersUpdater = atomicreferencefieldupdater2;
            listenersUpdater = atomicreferencefieldupdater3;
            valueUpdater = atomicreferencefieldupdater4;
        }
    }

    static final class SetFuture
        implements Runnable
    {

        public final ListenableFuture future;
        public final AbstractFuture owner;

        public final void run()
        {
            Object obj;
            if (owner.value == this)
            {
                if (AbstractFuture.ATOMIC_HELPER.casValue(owner, this, obj = AbstractFuture.getFutureValue(future)))
                {
                    AbstractFuture.complete(owner);
                    return;
                }
            }
        }

        SetFuture(AbstractFuture abstractfuture, ListenableFuture listenablefuture)
        {
            owner = abstractfuture;
            future = listenablefuture;
        }
    }

    static final class SynchronizedHelper extends AtomicHelper
    {

        final boolean casListeners(AbstractFuture abstractfuture, Listener listener, Listener listener1)
        {
            abstractfuture;
            JVM INSTR monitorenter ;
            if (abstractfuture.listeners != listener)
            {
                break MISSING_BLOCK_LABEL_19;
            }
            abstractfuture.listeners = listener1;
            return true;
            abstractfuture;
            JVM INSTR monitorexit ;
            return false;
            listener;
            abstractfuture;
            JVM INSTR monitorexit ;
            throw listener;
        }

        final boolean casValue(AbstractFuture abstractfuture, Object obj, Object obj1)
        {
            abstractfuture;
            JVM INSTR monitorenter ;
            if (abstractfuture.value != obj)
            {
                break MISSING_BLOCK_LABEL_19;
            }
            abstractfuture.value = obj1;
            return true;
            abstractfuture;
            JVM INSTR monitorexit ;
            return false;
            obj;
            abstractfuture;
            JVM INSTR monitorexit ;
            throw obj;
        }

        final boolean casWaiters(AbstractFuture abstractfuture, Waiter waiter, Waiter waiter1)
        {
            abstractfuture;
            JVM INSTR monitorenter ;
            if (abstractfuture.waiters != waiter)
            {
                break MISSING_BLOCK_LABEL_19;
            }
            abstractfuture.waiters = waiter1;
            return true;
            abstractfuture;
            JVM INSTR monitorexit ;
            return false;
            waiter;
            abstractfuture;
            JVM INSTR monitorexit ;
            throw waiter;
        }

        final void putNext(Waiter waiter, Waiter waiter1)
        {
            waiter.next = waiter1;
        }

        final void putThread(Waiter waiter, Thread thread)
        {
            waiter.thread = thread;
        }

        SynchronizedHelper()
        {
        }
    }

    public static class TrustedFuture extends AbstractFuture
    {

        public final Object get(long l, TimeUnit timeunit)
            throws InterruptedException, ExecutionException, TimeoutException
        {
            return get(l, timeunit);
        }

        TrustedFuture()
        {
        }
    }

    static final class UnsafeAtomicHelper extends AtomicHelper
    {

        public static final long LISTENERS_OFFSET;
        public static final Unsafe UNSAFE;
        public static final long VALUE_OFFSET;
        public static final long WAITERS_OFFSET;
        public static final long WAITER_NEXT_OFFSET;
        public static final long WAITER_THREAD_OFFSET;

        final boolean casListeners(AbstractFuture abstractfuture, Listener listener, Listener listener1)
        {
            return UNSAFE.compareAndSwapObject(abstractfuture, LISTENERS_OFFSET, listener, listener1);
        }

        final boolean casValue(AbstractFuture abstractfuture, Object obj, Object obj1)
        {
            return UNSAFE.compareAndSwapObject(abstractfuture, VALUE_OFFSET, obj, obj1);
        }

        final boolean casWaiters(AbstractFuture abstractfuture, Waiter waiter, Waiter waiter1)
        {
            return UNSAFE.compareAndSwapObject(abstractfuture, WAITERS_OFFSET, waiter, waiter1);
        }

        final void putNext(Waiter waiter, Waiter waiter1)
        {
            UNSAFE.putObject(waiter, WAITER_NEXT_OFFSET, waiter1);
        }

        final void putThread(Waiter waiter, Thread thread)
        {
            UNSAFE.putObject(waiter, WAITER_THREAD_OFFSET, thread);
        }

        static 
        {
            Unsafe unsafe;
            try
            {
                unsafe = Unsafe.getUnsafe();
            }
            catch (SecurityException securityexception)
            {
                try
                {
                    securityexception = (Unsafe)AccessController.doPrivileged(new PrivilegedExceptionAction() {

                        public Object run()
                            throws Exception
                        {
                            Field afield[] = sun/misc/Unsafe.getDeclaredFields();
                            int j = afield.length;
                            for (int i = 0; i < j; i++)
                            {
                                Object obj = afield[i];
                                ((Field) (obj)).setAccessible(true);
                                obj = ((Field) (obj)).get(null);
                                if (sun/misc/Unsafe.isInstance(obj))
                                {
                                    return (Unsafe)sun/misc/Unsafe.cast(obj);
                                }
                            }

                            throw new NoSuchFieldError("the Unsafe");
                        }

                    });
                }
                catch (PrivilegedActionException privilegedactionexception)
                {
                    throw new RuntimeException("Could not initialize intrinsics", privilegedactionexception.getCause());
                }
            }
            try
            {
                WAITERS_OFFSET = unsafe.objectFieldOffset(com/google/common/util/concurrent/AbstractFuture.getDeclaredField("waiters"));
                LISTENERS_OFFSET = unsafe.objectFieldOffset(com/google/common/util/concurrent/AbstractFuture.getDeclaredField("listeners"));
                VALUE_OFFSET = unsafe.objectFieldOffset(com/google/common/util/concurrent/AbstractFuture.getDeclaredField("value"));
                WAITER_THREAD_OFFSET = unsafe.objectFieldOffset(com/google/common/util/concurrent/AbstractFuture$Waiter.getDeclaredField("thread"));
                WAITER_NEXT_OFFSET = unsafe.objectFieldOffset(com/google/common/util/concurrent/AbstractFuture$Waiter.getDeclaredField("next"));
                UNSAFE = unsafe;
            }
            catch (Exception exception)
            {
                Throwables.throwIfUnchecked(exception);
                throw new RuntimeException(exception);
            }
        }

        UnsafeAtomicHelper()
        {
        }
    }

    static final class Waiter
    {

        public static final Waiter TOMBSTONE = new Waiter();
        public volatile Waiter next;
        public volatile Thread thread;


        Waiter()
        {
        }

        Waiter(byte byte0)
        {
            AbstractFuture.ATOMIC_HELPER.putThread(this, Thread.currentThread());
        }
    }


    public static final AtomicHelper ATOMIC_HELPER;
    public static final boolean GENERATE_CANCELLATION_CAUSES;
    public static final Object NULL;
    public static final Logger log;
    public volatile Listener listeners;
    public volatile Object value;
    public volatile Waiter waiters;

    public AbstractFuture()
    {
    }

    private final void addDoneString(StringBuilder stringbuilder)
    {
        ExecutionException executionexception;
        if (!isDone())
        {
            throw new IllegalStateException(Strings.lenientFormat("Future was expected to be done: %s", new Object[] {
                this
            }));
        }
        Object obj;
        StringBuilder stringbuilder1;
        obj = Uninterruptibles.getUninterruptibly(this);
        stringbuilder1 = stringbuilder.append("SUCCESS, result=[");
        if (obj != this)
        {
            break MISSING_BLOCK_LABEL_92;
        }
        obj = "this future";
_L1:
        try
        {
            stringbuilder1.append(((String) (obj))).append("]");
            return;
        }
        // Misplaced declaration of an exception variable
        catch (ExecutionException executionexception)
        {
            stringbuilder.append("FAILURE, cause=[").append(executionexception.getCause()).append("]");
            return;
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            stringbuilder.append("CANCELLED");
            return;
        }
        catch (RuntimeException runtimeexception)
        {
            stringbuilder.append("UNKNOWN, cause=[").append(runtimeexception.getClass()).append(" thrown from get()]");
        }
        break MISSING_BLOCK_LABEL_120;
        obj = String.valueOf(obj);
          goto _L1
    }

    static void complete(AbstractFuture abstractfuture)
    {
        AbstractFuture abstractfuture1;
        Object obj = null;
        abstractfuture1 = abstractfuture;
        abstractfuture = obj;
_L2:
        Object obj1;
        do
        {
            obj1 = abstractfuture1.waiters;
        } while (!ATOMIC_HELPER.casWaiters(abstractfuture1, ((Waiter) (obj1)), Waiter.TOMBSTONE));
        for (; obj1 != null; obj1 = ((Waiter) (obj1)).next)
        {
            Thread thread = ((Waiter) (obj1)).thread;
            if (thread != null)
            {
                obj1.thread = null;
                LockSupport.unpark(thread);
            }
        }

        abstractfuture1.afterDone();
        do
        {
            obj1 = abstractfuture1.listeners;
        } while (!ATOMIC_HELPER.casListeners(abstractfuture1, ((Listener) (obj1)), Listener.TOMBSTONE));
        abstractfuture1 = abstractfuture;
        for (abstractfuture = ((AbstractFuture) (obj1)); abstractfuture != null; abstractfuture = ((AbstractFuture) (obj1)))
        {
            obj1 = ((Listener) (abstractfuture)).next;
            abstractfuture.next = abstractfuture1;
            abstractfuture1 = abstractfuture;
        }

_L3:
        Object obj2;
        Object obj3;
        if (abstractfuture1 == null)
        {
            break MISSING_BLOCK_LABEL_180;
        }
        abstractfuture = ((Listener) (abstractfuture1)).next;
        obj2 = ((Listener) (abstractfuture1)).task;
        if (!(obj2 instanceof SetFuture))
        {
            break MISSING_BLOCK_LABEL_167;
        }
        obj2 = (SetFuture)obj2;
        abstractfuture1 = ((SetFuture) (obj2)).owner;
        if (abstractfuture1.value != obj2)
        {
            break; /* Loop/switch isn't completed */
        }
        obj3 = getFutureValue(((SetFuture) (obj2)).future);
        if (ATOMIC_HELPER.casValue(abstractfuture1, obj2, obj3)) goto _L2; else goto _L1
_L1:
        abstractfuture1 = abstractfuture;
          goto _L3
        executeListener(((Runnable) (obj2)), ((Listener) (abstractfuture1)).executor);
        abstractfuture1 = abstractfuture;
          goto _L3
    }

    private static void executeListener(Runnable runnable, Executor executor)
    {
        try
        {
            executor.execute(runnable);
            return;
        }
        catch (RuntimeException runtimeexception)
        {
            Logger logger = log;
            Level level = Level.SEVERE;
            runnable = String.valueOf(runnable);
            executor = String.valueOf(executor);
            logger.logp(level, "com.google.common.util.concurrent.AbstractFuture", "executeListener", (new StringBuilder(String.valueOf(runnable).length() + 57 + String.valueOf(executor).length())).append("RuntimeException while executing runnable ").append(runnable).append(" with executor ").append(executor).toString(), runtimeexception);
            return;
        }
    }

    private static Object getDoneValue(Object obj)
        throws ExecutionException
    {
        if (obj instanceof Cancellation)
        {
            obj = ((Cancellation)obj).cause;
            CancellationException cancellationexception = new CancellationException("Task was cancelled.");
            cancellationexception.initCause(((Throwable) (obj)));
            throw cancellationexception;
        }
        if (obj instanceof Failure)
        {
            throw new ExecutionException(((Failure)obj).exception);
        }
        Object obj1 = obj;
        if (obj == NULL)
        {
            obj1 = null;
        }
        return obj1;
    }

    static Object getFutureValue(ListenableFuture listenablefuture)
    {
        if (listenablefuture instanceof TrustedFuture)
        {
            Object obj = ((AbstractFuture)listenablefuture).value;
            listenablefuture = ((ListenableFuture) (obj));
            if (obj instanceof Cancellation)
            {
                Cancellation cancellation = (Cancellation)obj;
                listenablefuture = ((ListenableFuture) (obj));
                if (cancellation.wasInterrupted)
                {
                    if (cancellation.cause != null)
                    {
                        listenablefuture = new Cancellation(false, cancellation.cause);
                    } else
                    {
                        listenablefuture = Cancellation.CAUSELESS_CANCELLED;
                    }
                }
            }
            return listenablefuture;
        }
        if (!listenablefuture.isDone())
        {
            throw new IllegalStateException(Strings.lenientFormat("Future was expected to be done: %s", new Object[] {
                listenablefuture
            }));
        }
          goto _L1
_L3:
        return listenablefuture;
_L1:
        Object obj1 = Uninterruptibles.getUninterruptibly(listenablefuture);
        listenablefuture = ((ListenableFuture) (obj1));
        if (obj1 == null)
        {
            try
            {
                listenablefuture = ((ListenableFuture) (NULL));
            }
            // Misplaced declaration of an exception variable
            catch (ListenableFuture listenablefuture)
            {
                listenablefuture = new Failure(listenablefuture.getCause());
            }
            // Misplaced declaration of an exception variable
            catch (ListenableFuture listenablefuture)
            {
                listenablefuture = new Cancellation(false, listenablefuture);
            }
            // Misplaced declaration of an exception variable
            catch (ListenableFuture listenablefuture)
            {
                listenablefuture = new Failure(listenablefuture);
            }
        }
        if (true) goto _L3; else goto _L2
_L2:
    }

    private final void removeWaiter(Waiter waiter)
    {
        waiter.thread = null;
_L10:
        waiter = waiters;
        if (waiter != Waiter.TOMBSTONE) goto _L2; else goto _L1
_L1:
        return;
_L6:
        if (waiter == null) goto _L1; else goto _L3
_L3:
        Waiter waiter2 = waiter.next;
        if (waiter.thread == null) goto _L5; else goto _L4
_L4:
        Waiter waiter1;
        waiter1 = waiter;
        waiter = waiter2;
          goto _L6
_L5:
        if (waiter1 == null) goto _L8; else goto _L7
_L7:
        waiter1.next = waiter2;
        if (waiter1.thread == null) goto _L10; else goto _L9
_L9:
        waiter = waiter1;
          goto _L4
_L8:
        if (!ATOMIC_HELPER.casWaiters(this, waiter, waiter2)) goto _L10; else goto _L9
_L2:
        waiter1 = null;
          goto _L6
    }

    public void addListener(Runnable runnable, Executor executor)
    {
        if (runnable == null)
        {
            throw new NullPointerException(String.valueOf("Runnable was null."));
        }
        if (executor == null)
        {
            throw new NullPointerException(String.valueOf("Executor was null."));
        }
        Listener listener = listeners;
        if (listener != Listener.TOMBSTONE)
        {
            Listener listener2 = new Listener(runnable, executor);
            Listener listener1;
            do
            {
                listener2.next = listener;
                if (ATOMIC_HELPER.casListeners(this, listener, listener2))
                {
                    return;
                }
                listener1 = listeners;
                listener = listener1;
            } while (listener1 != Listener.TOMBSTONE);
        }
        executeListener(runnable, executor);
    }

    protected void afterDone()
    {
    }

    public boolean cancel(boolean flag)
    {
label0:
        {
            Object obj = value;
            Cancellation cancellation;
            AbstractFuture abstractfuture;
            boolean flag1;
            boolean flag2;
            if (obj == null)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (!(flag1 | (obj instanceof SetFuture)))
            {
                break label0;
            }
            if (GENERATE_CANCELLATION_CAUSES)
            {
                cancellation = new Cancellation(flag, new CancellationException("Future.cancel() was called."));
            } else
            if (flag)
            {
                cancellation = Cancellation.CAUSELESS_INTERRUPTED;
            } else
            {
                cancellation = Cancellation.CAUSELESS_CANCELLED;
            }
            flag2 = false;
            abstractfuture = this;
            Object obj1;
            do
            {
label1:
                {
label2:
                    {
                        do
                        {
                            if (!ATOMIC_HELPER.casValue(abstractfuture, obj, cancellation))
                            {
                                break label1;
                            }
                            if (flag)
                            {
                                abstractfuture.interruptTask();
                            }
                            complete(abstractfuture);
                            if (!(obj instanceof SetFuture))
                            {
                                break label2;
                            }
                            obj = ((SetFuture)obj).future;
                            if (!(obj instanceof TrustedFuture))
                            {
                                break;
                            }
                            abstractfuture = (AbstractFuture)obj;
                            obj = abstractfuture.value;
                            if (obj == null)
                            {
                                flag1 = true;
                            } else
                            {
                                flag1 = false;
                            }
                            if (!(flag1 | (obj instanceof SetFuture)))
                            {
                                break label2;
                            }
                            flag2 = true;
                        } while (true);
                        ((ListenableFuture) (obj)).cancel(flag);
                    }
                    return true;
                }
                obj1 = abstractfuture.value;
                obj = obj1;
            } while (obj1 instanceof SetFuture);
            return flag2;
        }
        return false;
    }

    public Object get()
        throws InterruptedException, ExecutionException
    {
        if (Thread.interrupted())
        {
            throw new InterruptedException();
        }
        Object obj = value;
        boolean flag;
        boolean flag2;
        if (obj != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!(obj instanceof SetFuture))
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        if (flag & flag2)
        {
            return getDoneValue(obj);
        }
        obj = waiters;
        if (obj != Waiter.TOMBSTONE)
        {
            Waiter waiter1 = new Waiter((byte)0);
            Waiter waiter;
            do
            {
                ATOMIC_HELPER.putNext(waiter1, ((Waiter) (obj)));
                if (ATOMIC_HELPER.casWaiters(this, ((Waiter) (obj)), waiter1))
                {
                    do
                    {
                        LockSupport.park(this);
                        if (Thread.interrupted())
                        {
                            removeWaiter(waiter1);
                            throw new InterruptedException();
                        }
                        obj = value;
                        boolean flag1;
                        boolean flag3;
                        if (obj != null)
                        {
                            flag1 = true;
                        } else
                        {
                            flag1 = false;
                        }
                        if (!(obj instanceof SetFuture))
                        {
                            flag3 = true;
                        } else
                        {
                            flag3 = false;
                        }
                    } while (!(flag1 & flag3));
                    return getDoneValue(obj);
                }
                waiter = waiters;
                obj = waiter;
            } while (waiter != Waiter.TOMBSTONE);
        }
        return getDoneValue(value);
    }

    public Object get(long l, TimeUnit timeunit)
        throws InterruptedException, TimeoutException, ExecutionException
    {
        Object obj;
        Waiter waiter1;
        long l1;
        long l2;
        l1 = timeunit.toNanos(l);
        if (Thread.interrupted())
        {
            throw new InterruptedException();
        }
        obj = value;
        boolean flag;
        boolean flag3;
        if (obj != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!(obj instanceof SetFuture))
        {
            flag3 = true;
        } else
        {
            flag3 = false;
        }
        if (flag & flag3)
        {
            return getDoneValue(obj);
        }
        if (l1 > 0L)
        {
            l2 = System.nanoTime() + l1;
        } else
        {
            l2 = 0L;
        }
        if (l1 < 1000L) goto _L2; else goto _L1
_L1:
        obj = waiters;
        if (obj == Waiter.TOMBSTONE) goto _L4; else goto _L3
_L3:
        waiter1 = new Waiter((byte)0);
_L7:
        ATOMIC_HELPER.putNext(waiter1, ((Waiter) (obj)));
        if (!ATOMIC_HELPER.casWaiters(this, ((Waiter) (obj)), waiter1)) goto _L6; else goto _L5
_L5:
        long l3;
        do
        {
            LockSupport.parkNanos(this, l1);
            if (Thread.interrupted())
            {
                removeWaiter(waiter1);
                throw new InterruptedException();
            }
            obj = value;
            boolean flag1;
            boolean flag4;
            if (obj != null)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (!(obj instanceof SetFuture))
            {
                flag4 = true;
            } else
            {
                flag4 = false;
            }
            if (flag1 & flag4)
            {
                return getDoneValue(obj);
            }
            l3 = l2 - System.nanoTime();
            l1 = l3;
        } while (l3 >= 1000L);
        removeWaiter(waiter1);
        l1 = l3;
_L2:
        if (l1 <= 0L)
        {
            break MISSING_BLOCK_LABEL_367;
        }
        obj = value;
        Waiter waiter;
        boolean flag2;
        boolean flag5;
        if (obj != null)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        if (!(obj instanceof SetFuture))
        {
            flag5 = true;
        } else
        {
            flag5 = false;
        }
        if (flag2 & flag5)
        {
            return getDoneValue(obj);
        }
        break MISSING_BLOCK_LABEL_342;
_L6:
        waiter = waiters;
        obj = waiter;
        if (waiter != Waiter.TOMBSTONE) goto _L7; else goto _L4
_L4:
        return getDoneValue(value);
        if (Thread.interrupted())
        {
            throw new InterruptedException();
        }
        l1 = l2 - System.nanoTime();
          goto _L2
        String s = toString();
        if (isDone())
        {
            timeunit = Ascii.toLowerCase(timeunit.toString());
            throw new TimeoutException((new StringBuilder(String.valueOf(timeunit).length() + 68)).append("Waited ").append(l).append(" ").append(timeunit).append(" but future completed as timeout expired").toString());
        } else
        {
            timeunit = Ascii.toLowerCase(timeunit.toString());
            throw new TimeoutException((new StringBuilder(String.valueOf(timeunit).length() + 33 + String.valueOf(s).length())).append("Waited ").append(l).append(" ").append(timeunit).append(" for ").append(s).toString());
        }
    }

    public void interruptTask()
    {
    }

    public boolean isCancelled()
    {
        return value instanceof Cancellation;
    }

    public boolean isDone()
    {
        boolean flag1 = true;
        Object obj = value;
        boolean flag;
        if (obj != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (obj instanceof SetFuture)
        {
            flag1 = false;
        }
        return flag & flag1;
    }

    final void maybePropagateCancellationTo(Future future)
    {
        boolean flag1 = true;
        boolean flag;
        if (future != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag & isCancelled())
        {
            Object obj = value;
            if (!(obj instanceof Cancellation) || !((Cancellation)obj).wasInterrupted)
            {
                flag1 = false;
            }
            future.cancel(flag1);
        }
    }

    public String pendingToString()
    {
        Object obj = value;
        if (obj instanceof SetFuture)
        {
            obj = ((SetFuture)obj).future;
            if (obj == this)
            {
                obj = "this future";
            } else
            {
                obj = String.valueOf(obj);
            }
            return (new StringBuilder(String.valueOf(obj).length() + 12)).append("setFuture=[").append(((String) (obj))).append("]").toString();
        }
        if (this instanceof ScheduledFuture)
        {
            long l = ((ScheduledFuture)this).getDelay(TimeUnit.MILLISECONDS);
            return (new StringBuilder(41)).append("remaining delay=[").append(l).append(" ms]").toString();
        } else
        {
            return null;
        }
    }

    public boolean set(Object obj)
    {
        Object obj1 = obj;
        if (obj == null)
        {
            obj1 = NULL;
        }
        if (ATOMIC_HELPER.casValue(this, null, obj1))
        {
            complete(this);
            return true;
        } else
        {
            return false;
        }
    }

    public boolean setException(Throwable throwable)
    {
        if (throwable == null)
        {
            throw new NullPointerException();
        }
        throwable = new Failure((Throwable)throwable);
        if (ATOMIC_HELPER.casValue(this, null, throwable))
        {
            complete(this);
            return true;
        } else
        {
            return false;
        }
    }

    public boolean setFuture(ListenableFuture listenablefuture)
    {
        if (listenablefuture == null)
        {
            throw new NullPointerException();
        }
        Object obj1 = value;
        Object obj = obj1;
        if (obj1 == null)
        {
            if (listenablefuture.isDone())
            {
                listenablefuture = ((ListenableFuture) (getFutureValue(listenablefuture)));
                if (ATOMIC_HELPER.casValue(this, null, listenablefuture))
                {
                    complete(this);
                    return true;
                } else
                {
                    return false;
                }
            }
            obj = new SetFuture(this, listenablefuture);
            if (ATOMIC_HELPER.casValue(this, null, obj))
            {
                try
                {
                    listenablefuture.addListener(((Runnable) (obj)), MoreExecutors.DirectExecutor.INSTANCE);
                }
                // Misplaced declaration of an exception variable
                catch (ListenableFuture listenablefuture)
                {
                    try
                    {
                        listenablefuture = new Failure(listenablefuture);
                    }
                    // Misplaced declaration of an exception variable
                    catch (ListenableFuture listenablefuture)
                    {
                        listenablefuture = Failure.FALLBACK_INSTANCE;
                    }
                    ATOMIC_HELPER.casValue(this, obj, listenablefuture);
                }
                return true;
            }
            obj = value;
        }
        if (obj instanceof Cancellation)
        {
            listenablefuture.cancel(((Cancellation)obj).wasInterrupted);
        }
        return false;
    }

    public String toString()
    {
        StringBuilder stringbuilder = (new StringBuilder()).append(super.toString()).append("[status=");
        if (isCancelled())
        {
            stringbuilder.append("CANCELLED");
        } else
        if (isDone())
        {
            addDoneString(stringbuilder);
        } else
        {
            String s;
            try
            {
                s = pendingToString();
            }
            catch (RuntimeException runtimeexception)
            {
                runtimeexception = String.valueOf(runtimeexception.getClass());
                runtimeexception = (new StringBuilder(String.valueOf(runtimeexception).length() + 38)).append("Exception thrown from implementation: ").append(runtimeexception).toString();
            }
            if (!Platform.stringIsNullOrEmpty(s))
            {
                stringbuilder.append("PENDING, info=[").append(s).append("]");
            } else
            if (isDone())
            {
                addDoneString(stringbuilder);
            } else
            {
                stringbuilder.append("PENDING");
            }
        }
        return stringbuilder.append("]").toString();
    }

    static 
    {
        Throwable throwable2;
        throwable2 = null;
        GENERATE_CANCELLATION_CAUSES = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));
        log = Logger.getLogger(com/google/common/util/concurrent/AbstractFuture.getName());
        Object obj = new UnsafeAtomicHelper();
        Throwable throwable1 = null;
_L2:
        ATOMIC_HELPER = ((AtomicHelper) (obj));
        if (throwable2 != null)
        {
            log.logp(Level.SEVERE, "com.google.common.util.concurrent.AbstractFuture", "<clinit>", "UnsafeAtomicHelper is broken!", throwable1);
            log.logp(Level.SEVERE, "com.google.common.util.concurrent.AbstractFuture", "<clinit>", "SafeAtomicHelper is broken!", throwable2);
        }
        NULL = new Object();
        return;
        Throwable throwable;
        throwable;
        SafeAtomicHelper safeatomichelper = new SafeAtomicHelper(AtomicReferenceFieldUpdater.newUpdater(com/google/common/util/concurrent/AbstractFuture$Waiter, java/lang/Thread, "thread"), AtomicReferenceFieldUpdater.newUpdater(com/google/common/util/concurrent/AbstractFuture$Waiter, com/google/common/util/concurrent/AbstractFuture$Waiter, "next"), AtomicReferenceFieldUpdater.newUpdater(com/google/common/util/concurrent/AbstractFuture, com/google/common/util/concurrent/AbstractFuture$Waiter, "waiters"), AtomicReferenceFieldUpdater.newUpdater(com/google/common/util/concurrent/AbstractFuture, com/google/common/util/concurrent/AbstractFuture$Listener, "listeners"), AtomicReferenceFieldUpdater.newUpdater(com/google/common/util/concurrent/AbstractFuture, java/lang/Object, "value"));
        throwable1 = throwable;
        throwable = safeatomichelper;
        continue; /* Loop/switch isn't completed */
        throwable2;
        SynchronizedHelper synchronizedhelper = new SynchronizedHelper();
        throwable1 = throwable;
        throwable = synchronizedhelper;
        if (true) goto _L2; else goto _L1
_L1:
    }
}
