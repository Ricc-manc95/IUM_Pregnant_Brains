// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.base.internal;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Finalizer
    implements Runnable
{

    private static final Constructor bigThreadConstructor;
    private static final Field inheritableThreadLocals;
    private static final Logger logger = Logger.getLogger(com/google/common/base/internal/Finalizer.getName());
    private final WeakReference finalizableReferenceClassReference;
    private final PhantomReference frqReference;
    private final ReferenceQueue queue;

    private Finalizer(Class class1, ReferenceQueue referencequeue, PhantomReference phantomreference)
    {
        queue = referencequeue;
        finalizableReferenceClassReference = new WeakReference(class1);
        frqReference = phantomreference;
    }

    private final boolean cleanUp(Reference reference)
    {
        Method method = getFinalizeReferentMethod();
        if (method == null)
        {
            return false;
        }
        do
        {
            reference.clear();
            if (reference == frqReference)
            {
                return false;
            }
            Reference reference1;
            try
            {
                method.invoke(reference, new Object[0]);
            }
            // Misplaced declaration of an exception variable
            catch (Reference reference)
            {
                logger.logp(Level.SEVERE, "com.google.common.base.internal.Finalizer", "cleanUp", "Error cleaning up after reference.", reference);
            }
            reference1 = queue.poll();
            reference = reference1;
        } while (reference1 != null);
        return true;
    }

    private static Constructor getBigThreadConstructor()
    {
        Constructor constructor;
        try
        {
            constructor = java/lang/Thread.getConstructor(new Class[] {
                java/lang/ThreadGroup, java/lang/Runnable, java/lang/String, Long.TYPE, Boolean.TYPE
            });
        }
        catch (Throwable throwable)
        {
            return null;
        }
        return constructor;
    }

    private final Method getFinalizeReferentMethod()
    {
        Object obj = (Class)finalizableReferenceClassReference.get();
        if (obj == null)
        {
            return null;
        }
        try
        {
            obj = ((Class) (obj)).getMethod("finalizeReferent", new Class[0]);
        }
        catch (NoSuchMethodException nosuchmethodexception)
        {
            throw new AssertionError(nosuchmethodexception);
        }
        return ((Method) (obj));
    }

    private static Field getInheritableThreadLocalsField()
    {
        Field field;
        try
        {
            field = java/lang/Thread.getDeclaredField("inheritableThreadLocals");
            field.setAccessible(true);
        }
        catch (Throwable throwable)
        {
            logger.logp(Level.INFO, "com.google.common.base.internal.Finalizer", "getInheritableThreadLocalsField", "Couldn't access Thread.inheritableThreadLocals. Reference finalizer threads will inherit thread local values.");
            return null;
        }
        return field;
    }

    public static void startFinalizer(Class class1, ReferenceQueue referencequeue, PhantomReference phantomreference)
    {
        if (!class1.getName().equals("com.google.common.base.FinalizableReference"))
        {
            throw new IllegalArgumentException("Expected com.google.common.base.FinalizableReference.");
        }
        referencequeue = new Finalizer(class1, referencequeue, phantomreference);
        phantomreference = com/google/common/base/internal/Finalizer.getName();
        if (bigThreadConstructor == null)
        {
            break MISSING_BLOCK_LABEL_141;
        }
        class1 = (Thread)bigThreadConstructor.newInstance(new Object[] {
            null, referencequeue, phantomreference, Long.valueOf(0L), Boolean.valueOf(false)
        });
_L1:
        if (class1 == null)
        {
            class1 = new Thread(null, referencequeue, phantomreference);
        }
        class1.setDaemon(true);
        try
        {
            if (inheritableThreadLocals != null)
            {
                inheritableThreadLocals.set(class1, null);
            }
        }
        // Misplaced declaration of an exception variable
        catch (ReferenceQueue referencequeue)
        {
            logger.logp(Level.INFO, "com.google.common.base.internal.Finalizer", "startFinalizer", "Failed to clear thread local values inherited by reference finalizer thread.", referencequeue);
        }
        class1.start();
        return;
        class1;
        logger.logp(Level.INFO, "com.google.common.base.internal.Finalizer", "startFinalizer", "Failed to create a thread without inherited thread-local values", class1);
        class1 = null;
          goto _L1
    }

    public void run()
    {
_L2:
        boolean flag;
        do
        {
            flag = cleanUp(queue.remove());
        } while (flag);
        return;
        InterruptedException interruptedexception;
        interruptedexception;
        if (true) goto _L2; else goto _L1
_L1:
    }

    static 
    {
        Object obj = getBigThreadConstructor();
        bigThreadConstructor = ((Constructor) (obj));
        if (obj == null)
        {
            obj = getInheritableThreadLocalsField();
        } else
        {
            obj = null;
        }
        inheritableThreadLocals = ((Field) (obj));
    }
}
