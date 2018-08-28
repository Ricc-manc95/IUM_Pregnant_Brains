// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.base;

import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package com.google.common.base:
//            FinalizableReferenceQueue

static final class er
    implements er
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

    er()
    {
    }
}
