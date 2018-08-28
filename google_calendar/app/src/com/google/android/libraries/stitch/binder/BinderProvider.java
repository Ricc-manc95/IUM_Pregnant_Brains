// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.stitch.binder;

import android.content.Context;

// Referenced classes of package com.google.android.libraries.stitch.binder:
//            Binder

public final class BinderProvider
{

    private final boolean attachRootBinder = false;
    private volatile Binder binder;
    private final Object binderInitLock = new Object();
    private final Initializer initializer;

    BinderProvider(boolean flag, Initializer initializer1)
    {
        initializer = initializer1;
    }

    public final Binder get(Context context)
    {
        if (binder == null)
        {
            synchronized (binderInitLock)
            {
                if (binder == null)
                {
                    Binder binder1 = new Binder(context);
                    if (attachRootBinder)
                    {
                        binder1.parent = Binder.rootBinderProvider.get(context.getApplicationContext());
                    }
                    if (initializer != null)
                    {
                        initializer.initBinder(context, binder1);
                    }
                    binder = binder1;
                }
            }
        }
        return binder;
        context;
        obj;
        JVM INSTR monitorexit ;
        throw context;
    }

    private class Initializer
    {

        public abstract void initBinder(Context context, Binder binder1);
    }

}
