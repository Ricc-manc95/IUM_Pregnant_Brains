// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package dagger.internal;

import dagger.Lazy;
import javax.inject.Provider;

// Referenced classes of package dagger.internal:
//            MemoizedSentinel

public final class DoubleCheck
    implements Lazy, Provider
{

    private static final Object UNINITIALIZED = new Object();
    private volatile Object instance;
    private volatile Provider provider;

    private DoubleCheck(Provider provider1)
    {
        instance = UNINITIALIZED;
        provider = provider1;
    }

    public static Lazy lazy(Provider provider1)
    {
        if (provider1 instanceof Lazy)
        {
            return (Lazy)provider1;
        }
        if (provider1 == null)
        {
            throw new NullPointerException();
        } else
        {
            return new DoubleCheck((Provider)provider1);
        }
    }

    public static Provider provider(Provider provider1)
    {
        if (provider1 == null)
        {
            throw new NullPointerException();
        }
        if (provider1 instanceof DoubleCheck)
        {
            return provider1;
        } else
        {
            return new DoubleCheck(provider1);
        }
    }

    public static Object reentrantCheck(Object obj, Object obj1)
    {
        boolean flag;
        if (obj != UNINITIALIZED && !(obj instanceof MemoizedSentinel))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag && obj != obj1)
        {
            obj = String.valueOf(obj);
            obj1 = String.valueOf(obj1);
            throw new IllegalStateException((new StringBuilder(String.valueOf(obj).length() + 118 + String.valueOf(obj1).length())).append("Scoped provider was invoked recursively returning different results: ").append(((String) (obj))).append(" & ").append(((String) (obj1))).append(". This is likely due to a circular dependency.").toString());
        } else
        {
            return obj1;
        }
    }

    public final Object get()
    {
        Object obj;
        obj = instance;
        if (obj != UNINITIALIZED)
        {
            break MISSING_BLOCK_LABEL_64;
        }
        this;
        JVM INSTR monitorenter ;
        Object obj1 = instance;
        obj = obj1;
        if (obj1 == UNINITIALIZED)
        {
            obj = provider.get();
            instance = reentrantCheck(instance, obj);
            provider = null;
        }
        this;
        JVM INSTR monitorexit ;
        return obj;
        obj;
        this;
        JVM INSTR monitorexit ;
        throw obj;
        return obj;
    }

}
