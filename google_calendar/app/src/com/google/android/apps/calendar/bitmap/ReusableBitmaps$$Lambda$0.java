// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.bitmap;

import com.android.bitmap.drawable.BasicBitmapDrawable;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.SettableFuture;

public final class arg._cls2
    implements Runnable
{

    private final SettableFuture arg$1;
    private final BasicBitmapDrawable arg$2;

    public final void run()
    {
        SettableFuture settablefuture = arg$1;
        BasicBitmapDrawable basicbitmapdrawable = arg$2;
        if (settablefuture.isCancelled())
        {
            basicbitmapdrawable.unbind();
        }
    }

    public (SettableFuture settablefuture, BasicBitmapDrawable basicbitmapdrawable)
    {
        arg$1 = settablefuture;
        arg$2 = basicbitmapdrawable;
    }
}
