// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package io.grpc.internal:
//            LongCounter

public final class ReflectionLongAdderCounter
    implements LongCounter
{

    private static final Method addMethod;
    private static final Constructor defaultConstructor;
    public static final RuntimeException initializationException;
    private static final Logger logger;
    private final Object instance;

    ReflectionLongAdderCounter()
    {
        if (initializationException != null)
        {
            throw initializationException;
        }
        try
        {
            instance = defaultConstructor.newInstance(new Object[0]);
            return;
        }
        catch (InstantiationException instantiationexception)
        {
            throw new RuntimeException(instantiationexception);
        }
        catch (IllegalAccessException illegalaccessexception)
        {
            throw new RuntimeException(illegalaccessexception);
        }
        catch (InvocationTargetException invocationtargetexception)
        {
            throw new RuntimeException(invocationtargetexception);
        }
    }

    public final void add(long l)
    {
        try
        {
            addMethod.invoke(instance, new Object[] {
                Long.valueOf(1L)
            });
            return;
        }
        catch (IllegalAccessException illegalaccessexception)
        {
            throw new RuntimeException(illegalaccessexception);
        }
        catch (InvocationTargetException invocationtargetexception)
        {
            throw new RuntimeException(invocationtargetexception);
        }
    }

    static 
    {
        logger = Logger.getLogger(io/grpc/internal/ReflectionLongAdderCounter.getName());
        Object obj;
        Object obj1;
        obj1 = Class.forName("java.util.concurrent.atomic.LongAdder");
        obj = ((Class) (obj1)).getMethod("add", new Class[] {
            Long.TYPE
        });
        Constructor aconstructor[];
        int j;
        ((Class) (obj1)).getMethod("sum", new Class[0]);
        aconstructor = ((Class) (obj1)).getConstructors();
        j = aconstructor.length;
        int i = 0;
_L5:
        if (i >= j) goto _L2; else goto _L1
_L1:
        obj1 = aconstructor[i];
        int k = ((Constructor) (obj1)).getParameterTypes().length;
        if (k != 0) goto _L4; else goto _L3
_L3:
        Object obj2;
        Object obj3;
        obj2 = null;
        obj3 = obj;
_L6:
        if (obj2 == null && obj1 != null)
        {
            defaultConstructor = ((Constructor) (obj1));
            addMethod = ((Method) (obj3));
            initializationException = null;
            return;
        } else
        {
            defaultConstructor = null;
            addMethod = null;
            initializationException = new RuntimeException(((Throwable) (obj2)));
            return;
        }
_L4:
        i++;
          goto _L5
_L2:
        obj2 = null;
        obj1 = null;
        obj3 = obj;
          goto _L6
        obj;
        obj1 = null;
_L7:
        logger.logp(Level.FINE, "io.grpc.internal.ReflectionLongAdderCounter", "<clinit>", "LongAdder can not be found via reflection, this is normal for JDK7 and below", ((Throwable) (obj)));
        obj2 = null;
        obj3 = obj1;
        obj1 = obj2;
        obj2 = obj;
          goto _L6
        Throwable throwable;
        throwable;
        obj1 = obj;
        obj = throwable;
          goto _L7
    }
}
