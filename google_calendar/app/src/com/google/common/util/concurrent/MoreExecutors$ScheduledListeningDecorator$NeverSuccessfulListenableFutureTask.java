// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;

import com.google.common.base.Throwables;

// Referenced classes of package com.google.common.util.concurrent:
//            AbstractFuture

final class delegate extends AbstractFuture
    implements Runnable
{

    private final Runnable _flddelegate;

    public final void run()
    {
        try
        {
            _flddelegate.run();
            return;
        }
        catch (Throwable throwable)
        {
            setException(throwable);
            throw Throwables.propagate(throwable);
        }
    }

    public (Runnable runnable)
    {
        if (runnable == null)
        {
            throw new NullPointerException();
        } else
        {
            _flddelegate = (Runnable)runnable;
            return;
        }
    }
}
