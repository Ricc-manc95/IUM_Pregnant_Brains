// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.concurrent;

import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.concurrent:
//            AsyncCloseable

final class arg._cls1
    implements Runnable
{

    private final AsyncCloseable arg$1;

    public final void run()
    {
        Object obj = arg$1;
        ListenableFuture listenablefuture = ((AsyncCloseable) (obj)).future;
        obj = ((AsyncCloseable) (obj)).closeableList;
        obj.getClass();
        listenablefuture.addListener(new (((ist) (obj))), com.google.common.util.concurrent.tor.INSTANCE);
    }

    (AsyncCloseable asynccloseable)
    {
        arg$1 = asynccloseable;
    }
}
