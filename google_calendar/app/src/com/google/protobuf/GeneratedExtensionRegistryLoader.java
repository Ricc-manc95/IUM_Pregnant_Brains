// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package com.google.protobuf:
//            CodedOutputStream, ExtensionRegistryLite

abstract class GeneratedExtensionRegistryLoader
{

    private static String LITE_CLASS_NAME = "com.google.protobuf.BlazeGeneratedExtensionRegistryLiteLoader";
    private static final Logger logger = Logger.getLogger(com/google/protobuf/CodedOutputStream.getName());

    GeneratedExtensionRegistryLoader()
    {
    }

    static ExtensionRegistryLite load(Class class1)
    {
        Object obj2;
        obj2 = com/google/protobuf/GeneratedExtensionRegistryLoader.getClassLoader();
        Object obj;
        if (class1.equals(com/google/protobuf/ExtensionRegistryLite))
        {
            obj = LITE_CLASS_NAME;
        } else
        {
            if (!class1.getPackage().equals(com/google/protobuf/GeneratedExtensionRegistryLoader.getPackage()))
            {
                throw new IllegalArgumentException(class1.getName());
            }
            obj = String.format("%s.BlazeGenerated%sLoader", new Object[] {
                class1.getPackage().getName(), class1.getSimpleName()
            });
        }
        obj = Class.forName(((String) (obj)), true, ((ClassLoader) (obj2)));
        obj = (GeneratedExtensionRegistryLoader)((Class) (obj)).getConstructor(new Class[0]).newInstance(new Object[0]);
        obj = (ExtensionRegistryLite)class1.cast(((GeneratedExtensionRegistryLoader) (obj)).getInstance());
        return ((ExtensionRegistryLite) (obj));
        NoSuchMethodException nosuchmethodexception;
        nosuchmethodexception;
        ArrayList arraylist;
        try
        {
            throw new IllegalStateException(nosuchmethodexception);
        }
        catch (ClassNotFoundException classnotfoundexception)
        {
            obj2 = ServiceLoader.load(com/google/protobuf/GeneratedExtensionRegistryLoader, ((ClassLoader) (obj2))).iterator();
        }
        arraylist = new ArrayList();
        while (((Iterator) (obj2)).hasNext()) 
        {
            try
            {
                arraylist.add((ExtensionRegistryLite)class1.cast(((GeneratedExtensionRegistryLoader)((Iterator) (obj2)).next()).getInstance()));
            }
            catch (ServiceConfigurationError serviceconfigurationerror)
            {
                Logger logger1 = logger;
                Level level = Level.SEVERE;
                String s = String.valueOf(class1.getSimpleName());
                Object obj1;
                if (s.length() != 0)
                {
                    s = "Unable to load ".concat(s);
                } else
                {
                    obj1 = new String("Unable to load ");
                }
                logger1.logp(level, "com.google.protobuf.GeneratedExtensionRegistryLoader", "load", s, serviceconfigurationerror);
            }
        }
        break MISSING_BLOCK_LABEL_275;
        obj1;
        throw new IllegalStateException(((Throwable) (obj1)));
        obj1;
        throw new IllegalStateException(((Throwable) (obj1)));
        obj1;
        throw new IllegalStateException(((Throwable) (obj1)));
        if (arraylist.size() == 1)
        {
            return (ExtensionRegistryLite)arraylist.get(0);
        }
        if (arraylist.size() == 0)
        {
            return null;
        }
        try
        {
            class1 = (ExtensionRegistryLite)class1.getMethod("combine", new Class[] {
                java/util/Collection
            }).invoke(null, new Object[] {
                arraylist
            });
        }
        // Misplaced declaration of an exception variable
        catch (Class class1)
        {
            throw new IllegalStateException(class1);
        }
        // Misplaced declaration of an exception variable
        catch (Class class1)
        {
            throw new IllegalStateException(class1);
        }
        // Misplaced declaration of an exception variable
        catch (Class class1)
        {
            throw new IllegalStateException(class1);
        }
        return class1;
    }

    protected abstract ExtensionRegistryLite getInstance();

}
