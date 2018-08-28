// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;

import com.google.common.base.Throwables;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import sun.misc.Unsafe;

// Referenced classes of package com.google.common.util.concurrent:
//            AbstractFuture

static final class _cls1 extends _cls1
{

    public static final long LISTENERS_OFFSET;
    public static final Unsafe UNSAFE;
    public static final long VALUE_OFFSET;
    public static final long WAITERS_OFFSET;
    public static final long WAITER_NEXT_OFFSET;
    public static final long WAITER_THREAD_OFFSET;

    final boolean casListeners(AbstractFuture abstractfuture, _cls1 _pcls1, _cls1 _pcls1_1)
    {
        return UNSAFE.compareAndSwapObject(abstractfuture, LISTENERS_OFFSET, _pcls1, _pcls1_1);
    }

    final boolean casValue(AbstractFuture abstractfuture, Object obj, Object obj1)
    {
        return UNSAFE.compareAndSwapObject(abstractfuture, VALUE_OFFSET, obj, obj1);
    }

    final boolean casWaiters(AbstractFuture abstractfuture, VALUE_OFFSET value_offset, VALUE_OFFSET value_offset1)
    {
        return UNSAFE.compareAndSwapObject(abstractfuture, WAITERS_OFFSET, value_offset, value_offset1);
    }

    final void putNext(WAITERS_OFFSET waiters_offset, WAITERS_OFFSET waiters_offset1)
    {
        UNSAFE.putObject(waiters_offset, WAITER_NEXT_OFFSET, waiters_offset1);
    }

    final void putThread(WAITER_NEXT_OFFSET waiter_next_offset, Thread thread)
    {
        UNSAFE.putObject(waiter_next_offset, WAITER_THREAD_OFFSET, thread);
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

    _cls1()
    {
    }
}
