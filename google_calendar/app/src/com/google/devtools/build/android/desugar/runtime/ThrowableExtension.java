// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.devtools.build.android.desugar.runtime;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;

public final class ThrowableExtension
{
    static final class NullDesugaringStrategy extends AbstractDesugaringStrategy
    {
        private class AbstractDesugaringStrategy
        {

            public static final Throwable EMPTY_THROWABLE_ARRAY[] = new Throwable[0];

            public abstract void addSuppressed(Throwable throwable, Throwable throwable1);

            public abstract Throwable[] getSuppressed(Throwable throwable);

            public abstract void printStackTrace(Throwable throwable);

            public abstract void printStackTrace(Throwable throwable, PrintStream printstream);

            public abstract void printStackTrace(Throwable throwable, PrintWriter printwriter);


            AbstractDesugaringStrategy()
            {
            }
        }


        public final void addSuppressed(Throwable throwable, Throwable throwable1)
        {
        }

        public final Throwable[] getSuppressed(Throwable throwable)
        {
            return EMPTY_THROWABLE_ARRAY;
        }

        public final void printStackTrace(Throwable throwable)
        {
            throwable.printStackTrace();
        }

        public final void printStackTrace(Throwable throwable, PrintStream printstream)
        {
            throwable.printStackTrace(printstream);
        }

        public final void printStackTrace(Throwable throwable, PrintWriter printwriter)
        {
            throwable.printStackTrace(printwriter);
        }

        NullDesugaringStrategy()
        {
        }
    }


    public static final AbstractDesugaringStrategy STRATEGY;

    public static void printStackTrace(Throwable throwable, PrintStream printstream)
    {
        STRATEGY.printStackTrace(throwable, printstream);
    }

    private static Integer readApiLevelFromBuildVersion()
    {
        Integer integer;
        try
        {
            integer = (Integer)Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);
        }
        catch (Exception exception)
        {
            System.err.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
            exception.printStackTrace(System.err);
            return null;
        }
        return integer;
    }

    static 
    {
        Integer integer = readApiLevelFromBuildVersion();
        if (integer == null) goto _L2; else goto _L1
_L1:
        if (integer.intValue() < 19) goto _L2; else goto _L3
_L3:
        Object obj = new ReuseDesugaringStrategy();
_L6:
        STRATEGY = ((AbstractDesugaringStrategy) (obj));
        if (integer != null)
        {
            integer.intValue();
        }
        return;
_L2:
        boolean flag;
        if (!Boolean.getBoolean("com.google.devtools.build.android.desugar.runtime.twr_disable_mimic"))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L5; else goto _L4
_L4:
        obj = new MimicDesugaringStrategy();
          goto _L6
        obj;
_L7:
        PrintStream printstream = System.err;
        String s = com/google/devtools/build/android/desugar/runtime/ThrowableExtension$NullDesugaringStrategy.getName();
        printstream.println((new StringBuilder(String.valueOf(s).length() + 132)).append("An error has occured when initializing the try-with-resources desuguring strategy. The default strategy ").append(s).append("will be used. The error is: ").toString());
        ((Throwable) (obj)).printStackTrace(System.err);
        obj = new NullDesugaringStrategy();
          goto _L6
_L5:
        obj = new NullDesugaringStrategy();
          goto _L6
        obj;
        integer = null;
          goto _L7
    }

    private class ReuseDesugaringStrategy extends AbstractDesugaringStrategy
    {

        public final void addSuppressed(Throwable throwable, Throwable throwable1)
        {
            throwable.addSuppressed(throwable1);
        }

        public final Throwable[] getSuppressed(Throwable throwable)
        {
            return throwable.getSuppressed();
        }

        public final void printStackTrace(Throwable throwable)
        {
            throwable.printStackTrace();
        }

        public final void printStackTrace(Throwable throwable, PrintStream printstream)
        {
            throwable.printStackTrace(printstream);
        }

        public final void printStackTrace(Throwable throwable, PrintWriter printwriter)
        {
            throwable.printStackTrace(printwriter);
        }

        ReuseDesugaringStrategy()
        {
        }
    }


    private class MimicDesugaringStrategy extends AbstractDesugaringStrategy
    {
        private class ConcurrentWeakIdentityHashMap
        {

            private final ConcurrentHashMap map = new ConcurrentHashMap(16, 0.75F, 10);
            private final ReferenceQueue referenceQueue = new ReferenceQueue();

            public final List get(Throwable throwable, boolean flag)
            {
                for (java.lang.ref.Reference reference = referenceQueue.poll(); reference != null; reference = referenceQueue.poll())
                {
                    map.remove(reference);
                }

                class WeakKey extends WeakReference
                {

                    private final int hash;

                    public final boolean equals(Object obj1)
                    {
                        boolean flag2 = true;
                        if (obj1 != null && obj1.getClass() == getClass()) goto _L2; else goto _L1
_L1:
                        boolean flag1 = false;
_L4:
                        return flag1;
_L2:
                        flag1 = flag2;
                        if (this == obj1) goto _L4; else goto _L3
_L3:
                        obj1 = (WeakKey)obj1;
                        if (hash != ((WeakKey) (obj1)).hash)
                        {
                            break; /* Loop/switch isn't completed */
                        }
                        flag1 = flag2;
                        if (get() == ((WeakKey) (obj1)).get()) goto _L4; else goto _L5
_L5:
                        return false;
                    }

                    public final int hashCode()
                    {
                        return hash;
                    }

                    public WeakKey(Throwable throwable, ReferenceQueue referencequeue)
                    {
                        super(throwable, referencequeue);
                        if (throwable == null)
                        {
                            throw new NullPointerException("The referent cannot be null");
                        } else
                        {
                            hash = System.identityHashCode(throwable);
                            return;
                        }
                    }
                }

                Object obj = new WeakKey(throwable, null);
                List list = (List)map.get(obj);
                if (!flag)
                {
                    obj = list;
                } else
                {
                    obj = list;
                    if (list == null)
                    {
                        Vector vector = new Vector(2);
                        throwable = (List)map.putIfAbsent(new WeakKey(throwable, referenceQueue), vector);
                        obj = throwable;
                        if (throwable == null)
                        {
                            return vector;
                        }
                    }
                }
                return ((List) (obj));
            }

            ConcurrentWeakIdentityHashMap()
            {
            }
        }


        private final ConcurrentWeakIdentityHashMap map = new ConcurrentWeakIdentityHashMap();

        public final void addSuppressed(Throwable throwable, Throwable throwable1)
        {
            if (throwable1 == throwable)
            {
                throw new IllegalArgumentException("Self suppression is not allowed.", throwable1);
            }
            if (throwable1 == null)
            {
                throw new NullPointerException("The suppressed exception cannot be null.");
            } else
            {
                map.get(throwable, true).add(throwable1);
                return;
            }
        }

        public final Throwable[] getSuppressed(Throwable throwable)
        {
            throwable = map.get(throwable, false);
            if (throwable == null || throwable.isEmpty())
            {
                return EMPTY_THROWABLE_ARRAY;
            } else
            {
                return (Throwable[])throwable.toArray(EMPTY_THROWABLE_ARRAY);
            }
        }

        public final void printStackTrace(Throwable throwable)
        {
            throwable.printStackTrace();
            throwable = map.get(throwable, false);
            if (throwable == null)
            {
                return;
            }
            throwable;
            JVM INSTR monitorenter ;
            Throwable throwable1;
            for (Iterator iterator = throwable.iterator(); iterator.hasNext(); throwable1.printStackTrace())
            {
                throwable1 = (Throwable)iterator.next();
                System.err.print("Suppressed: ");
            }

            break MISSING_BLOCK_LABEL_67;
            Exception exception;
            exception;
            throwable;
            JVM INSTR monitorexit ;
            throw exception;
            throwable;
            JVM INSTR monitorexit ;
        }

        public final void printStackTrace(Throwable throwable, PrintStream printstream)
        {
            throwable.printStackTrace(printstream);
            throwable = map.get(throwable, false);
            if (throwable == null)
            {
                return;
            }
            throwable;
            JVM INSTR monitorenter ;
            Throwable throwable1;
            for (Iterator iterator = throwable.iterator(); iterator.hasNext(); throwable1.printStackTrace(printstream))
            {
                throwable1 = (Throwable)iterator.next();
                printstream.print("Suppressed: ");
            }

            break MISSING_BLOCK_LABEL_69;
            printstream;
            throwable;
            JVM INSTR monitorexit ;
            throw printstream;
            throwable;
            JVM INSTR monitorexit ;
        }

        public final void printStackTrace(Throwable throwable, PrintWriter printwriter)
        {
            throwable.printStackTrace(printwriter);
            throwable = map.get(throwable, false);
            if (throwable == null)
            {
                return;
            }
            throwable;
            JVM INSTR monitorenter ;
            Throwable throwable1;
            for (Iterator iterator = throwable.iterator(); iterator.hasNext(); throwable1.printStackTrace(printwriter))
            {
                throwable1 = (Throwable)iterator.next();
                printwriter.print("Suppressed: ");
            }

            break MISSING_BLOCK_LABEL_69;
            printwriter;
            throwable;
            JVM INSTR monitorexit ;
            throw printwriter;
            throwable;
            JVM INSTR monitorexit ;
        }

        MimicDesugaringStrategy()
        {
        }
    }

}
