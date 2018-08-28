// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.base;

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package com.google.common.base:
//            FinalizableReference

public class FinalizableReferenceQueue
    implements Closeable
{
    static final class DecoupledLoader
        implements FinalizerLoader
    {

        public final Class loadFinalizer()
        {
            Object obj;
            URL url;
            obj = String.valueOf("com.google.common.base.internal.Finalizer".replace('.', '/')).concat(".class");
            url = getClass().getClassLoader().getResource(((String) (obj)));
            if (url == null)
            {
                try
                {
                    throw new FileNotFoundException(((String) (obj)));
                }
                // Misplaced declaration of an exception variable
                catch (Object obj)
                {
                    FinalizableReferenceQueue.logger.logp(Level.WARNING, "com.google.common.base.FinalizableReferenceQueue$DecoupledLoader", "loadFinalizer", "Could not load Finalizer in its own class loader. Loading Finalizer in the current class loader instead. As a result, you will not be able to garbage collect this class loader. To support reclaiming this class loader, either resolve the underlying issue, or move Guava to your system class path.", ((Throwable) (obj)));
                }
                return null;
            }
            String s;
            s = url.toString();
            if (!s.endsWith(((String) (obj))))
            {
                obj = String.valueOf(s);
                if (((String) (obj)).length() != 0)
                {
                    obj = "Unsupported path style: ".concat(((String) (obj)));
                } else
                {
                    obj = new String("Unsupported path style: ");
                }
                throw new IOException(((String) (obj)));
            }
            obj = (new URLClassLoader(new URL[] {
                new URL(url, s.substring(0, s.length() - ((String) (obj)).length()))
            }, null)).loadClass("com.google.common.base.internal.Finalizer");
            return ((Class) (obj));
        }

        DecoupledLoader()
        {
        }
    }

    static final class DirectLoader
        implements FinalizerLoader
    {

        public final Class loadFinalizer()
        {
            Class class1;
            try
            {
                class1 = Class.forName("com.google.common.base.internal.Finalizer");
            }
            catch (ClassNotFoundException classnotfoundexception)
            {
                throw new AssertionError(classnotfoundexception);
            }
            return class1;
        }

        DirectLoader()
        {
        }
    }

    static interface FinalizerLoader
    {

        public abstract Class loadFinalizer();
    }

    static final class SystemLoader
        implements FinalizerLoader
    {

        public final Class loadFinalizer()
        {
            Class class1 = null;
            ClassLoader classloader;
            try
            {
                classloader = ClassLoader.getSystemClassLoader();
            }
            catch (SecurityException securityexception)
            {
                FinalizableReferenceQueue.logger.logp(Level.INFO, "com.google.common.base.FinalizableReferenceQueue$SystemLoader", "loadFinalizer", "Not allowed to access system class loader.");
                return null;
            }
            if (classloader != null)
            {
                try
                {
                    class1 = classloader.loadClass("com.google.common.base.internal.Finalizer");
                }
                catch (ClassNotFoundException classnotfoundexception)
                {
                    return null;
                }
            }
            return class1;
        }

        SystemLoader()
        {
        }
    }


    public static final Logger logger = Logger.getLogger(com/google/common/base/FinalizableReferenceQueue.getName());
    private static final Method startFinalizer;
    private final PhantomReference frqRef;
    public final ReferenceQueue queue = new ReferenceQueue();
    private final boolean threadStarted;

    public FinalizableReferenceQueue()
    {
        boolean flag = true;
        super();
        frqRef = new PhantomReference(this, queue);
        try
        {
            startFinalizer.invoke(null, new Object[] {
                com/google/common/base/FinalizableReference, queue, frqRef
            });
        }
        catch (IllegalAccessException illegalaccessexception)
        {
            throw new AssertionError(illegalaccessexception);
        }
        catch (Throwable throwable)
        {
            logger.logp(Level.INFO, "com.google.common.base.FinalizableReferenceQueue", "<init>", "Failed to start reference finalizer thread. Reference cleanup will only occur when new references are created.", throwable);
            flag = false;
        }
        threadStarted = flag;
    }

    private static Method getStartFinalizer(Class class1)
    {
        try
        {
            class1 = class1.getMethod("startFinalizer", new Class[] {
                java/lang/Class, java/lang/ref/ReferenceQueue, java/lang/ref/PhantomReference
            });
        }
        // Misplaced declaration of an exception variable
        catch (Class class1)
        {
            throw new AssertionError(class1);
        }
        return class1;
    }

    final void cleanUp()
    {
        Reference reference;
        if (!threadStarted)
        {
            while ((reference = queue.poll()) != null) 
            {
                reference.clear();
                try
                {
                    ((FinalizableReference)reference).finalizeReferent();
                }
                catch (Throwable throwable)
                {
                    logger.logp(Level.SEVERE, "com.google.common.base.FinalizableReferenceQueue", "cleanUp", "Error cleaning up after reference.", throwable);
                }
            }
        }
    }

    public void close()
    {
        frqRef.enqueue();
        cleanUp();
    }

    static 
    {
        int i = 0;
        FinalizerLoader afinalizerloader[] = new FinalizerLoader[3];
        afinalizerloader[0] = new SystemLoader();
        afinalizerloader[1] = new DecoupledLoader();
        afinalizerloader[2] = new DirectLoader();
        for (int j = afinalizerloader.length; i < j; i++)
        {
            Class class1 = afinalizerloader[i].loadFinalizer();
            if (class1 != null)
            {
                startFinalizer = getStartFinalizer(class1);
                return;
            }
        }

        throw new AssertionError();
    }
}
