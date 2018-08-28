// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.base;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package com.google.common.base:
//            FinalizableReferenceQueue

static final class 
    implements 
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

    ()
    {
    }
}
