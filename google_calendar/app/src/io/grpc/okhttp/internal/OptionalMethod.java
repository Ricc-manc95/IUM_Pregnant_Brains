// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.okhttp.internal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class OptionalMethod
{

    private final String methodName;
    private final Class methodParams[];
    private final Class returnType;

    public transient OptionalMethod(Class class1, String s, Class aclass[])
    {
        returnType = class1;
        methodName = s;
        methodParams = aclass;
    }

    private static Method getPublicMethod(Class class1, String s, Class aclass[])
    {
        if (class1 != null)
        {
            int i;
            try
            {
                if ((class1.getModifiers() & 1) == 0)
                {
                    return getPublicMethod(class1.getSuperclass(), s, aclass);
                }
                class1 = class1.getMethod(s, aclass);
            }
            // Misplaced declaration of an exception variable
            catch (Class class1)
            {
                return null;
            }
            try
            {
                i = class1.getModifiers();
            }
            // Misplaced declaration of an exception variable
            catch (String s)
            {
                return class1;
            }
            if ((i & 1) != 0)
            {
                return class1;
            }
        }
        return null;
    }

    private final transient Object invoke(Object obj, Object aobj[])
        throws InvocationTargetException
    {
        Method method = getMethod(obj.getClass());
        if (method == null)
        {
            aobj = methodName;
            obj = String.valueOf(obj);
            throw new AssertionError((new StringBuilder(String.valueOf(((Object) (aobj))).length() + 33 + String.valueOf(obj).length())).append("Method ").append(((String) (aobj))).append(" not supported for object ").append(((String) (obj))).toString());
        }
        try
        {
            obj = method.invoke(obj, aobj);
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            aobj = String.valueOf(method);
            aobj = new AssertionError((new StringBuilder(String.valueOf(((Object) (aobj))).length() + 29)).append("Unexpectedly could not call: ").append(((String) (aobj))).toString());
            ((AssertionError) (aobj)).initCause(((Throwable) (obj)));
            throw aobj;
        }
        return obj;
    }

    private final transient Object invokeOptional(Object obj, Object aobj[])
        throws InvocationTargetException
    {
        Method method = getMethod(obj.getClass());
        if (method == null)
        {
            return null;
        }
        try
        {
            obj = method.invoke(obj, aobj);
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            return null;
        }
        return obj;
    }

    final Method getMethod(Class class1)
    {
label0:
        {
            if (methodName != null)
            {
                class1 = getPublicMethod(class1, methodName, methodParams);
                if (class1 == null || returnType == null || returnType.isAssignableFrom(class1.getReturnType()))
                {
                    break label0;
                }
            }
            return null;
        }
        return class1;
    }

    public final transient Object invokeOptionalWithoutCheckedException(Object obj, Object aobj[])
    {
        try
        {
            obj = invokeOptional(obj, aobj);
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            obj = ((InvocationTargetException) (obj)).getTargetException();
            if (obj instanceof RuntimeException)
            {
                throw (RuntimeException)obj;
            } else
            {
                aobj = new AssertionError("Unexpected exception");
                ((AssertionError) (aobj)).initCause(((Throwable) (obj)));
                throw aobj;
            }
        }
        return obj;
    }

    public final transient Object invokeWithoutCheckedException(Object obj, Object aobj[])
    {
        try
        {
            obj = invoke(obj, aobj);
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            obj = ((InvocationTargetException) (obj)).getTargetException();
            if (obj instanceof RuntimeException)
            {
                throw (RuntimeException)obj;
            } else
            {
                aobj = new AssertionError("Unexpected exception");
                ((AssertionError) (aobj)).initCause(((Throwable) (obj)));
                throw aobj;
            }
        }
        return obj;
    }
}
