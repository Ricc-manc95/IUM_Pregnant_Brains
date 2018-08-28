// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;

import java.lang.reflect.Constructor;
import java.util.ServiceConfigurationError;

final class ServiceProviders
{

    static Object create(Class class1, Class class2)
    {
        try
        {
            class1 = ((Class) (class2.asSubclass(class1).getConstructor(new Class[0]).newInstance(new Object[0])));
        }
        // Misplaced declaration of an exception variable
        catch (Class class1)
        {
            throw new ServiceConfigurationError(String.format("Provider %s could not be instantiated %s", new Object[] {
                class2.getName(), class1
            }), class1);
        }
        return class1;
    }

    static boolean isAndroid(ClassLoader classloader)
    {
        try
        {
            Class.forName("android.app.Application", false, classloader);
        }
        // Misplaced declaration of an exception variable
        catch (ClassLoader classloader)
        {
            return false;
        }
        return true;
    }
}
