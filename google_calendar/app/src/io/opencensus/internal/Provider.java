// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.internal;

import java.lang.reflect.Constructor;
import java.util.ServiceConfigurationError;

public final class Provider
{

    public static Object createInstance(Class class1, Class class2)
    {
        try
        {
            class2 = ((Class) (class1.asSubclass(class2).getConstructor(new Class[0]).newInstance(new Object[0])));
        }
        // Misplaced declaration of an exception variable
        catch (Class class2)
        {
            class1 = class1.getName();
            throw new ServiceConfigurationError((new StringBuilder(String.valueOf(class1).length() + 36)).append("Provider ").append(class1).append(" could not be instantiated.").toString(), class2);
        }
        return class2;
    }
}
