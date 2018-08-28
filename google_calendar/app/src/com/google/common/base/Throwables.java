// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.base;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class Throwables
{

    private static final Object jla;

    private static Object getJLA()
    {
        Object obj;
        try
        {
            obj = Class.forName("sun.misc.SharedSecrets", false, null).getMethod("getJavaLangAccess", new Class[0]).invoke(null, new Object[0]);
        }
        catch (ThreadDeath threaddeath)
        {
            throw threaddeath;
        }
        catch (Throwable throwable)
        {
            return null;
        }
        return obj;
    }

    private static transient Method getJlaMethod(String s, Class aclass[])
        throws ThreadDeath
    {
        try
        {
            s = Class.forName("sun.misc.JavaLangAccess", false, null).getMethod(s, aclass);
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            throw s;
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            return null;
        }
        return s;
    }

    private static Method getSizeMethod()
    {
        Method method;
        try
        {
            method = getJlaMethod("getStackTraceDepth", new Class[] {
                java/lang/Throwable
            });
        }
        catch (UnsupportedOperationException unsupportedoperationexception)
        {
            return null;
        }
        catch (IllegalAccessException illegalaccessexception)
        {
            return null;
        }
        catch (InvocationTargetException invocationtargetexception)
        {
            return null;
        }
        if (method == null)
        {
            return null;
        }
        method.invoke(getJLA(), new Object[] {
            new Throwable()
        });
        return method;
    }

    public static String getStackTraceAsString(Throwable throwable)
    {
        StringWriter stringwriter = new StringWriter();
        PrintWriter printwriter = new PrintWriter(stringwriter);
        ThrowableExtension.STRATEGY.printStackTrace(throwable, printwriter);
        return stringwriter.toString();
    }

    public static RuntimeException propagate(Throwable throwable)
    {
        throwIfUnchecked(throwable);
        throw new RuntimeException(throwable);
    }

    public static void propagateIfPossible(Throwable throwable, Class class1)
        throws Throwable
    {
        if (throwable != null)
        {
            if (throwable == null)
            {
                throw new NullPointerException();
            }
            if (class1.isInstance(throwable))
            {
                throw (Throwable)class1.cast(throwable);
            }
        }
        if (throwable != null)
        {
            throwIfUnchecked(throwable);
        }
    }

    public static void throwIfUnchecked(Throwable throwable)
    {
        if (throwable == null)
        {
            throw new NullPointerException();
        }
        if (throwable instanceof RuntimeException)
        {
            throw (RuntimeException)throwable;
        }
        if (throwable instanceof Error)
        {
            throw (Error)throwable;
        } else
        {
            return;
        }
    }

    static 
    {
        Object obj = getJLA();
        jla = obj;
        if (obj != null)
        {
            getJlaMethod("getStackTraceElement", new Class[] {
                java/lang/Throwable, Integer.TYPE
            });
        }
        if (jla != null)
        {
            getSizeMethod();
        }
    }
}
