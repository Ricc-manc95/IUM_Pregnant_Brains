// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.image;

import com.google.android.calendar.timeline.chip.image.ImageResolver;
import com.google.android.calendar.timeline.chip.image.ImageResolverContext;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.ListenableFuture;

public final class arg._cls1
    implements ImageResolver
{

    private final ListenableFuture arg$1;

    public final ListenableFuture resolveImage(ImageResolverContext imageresolvercontext)
    {
        return AbstractTransformFuture.create(arg$1, new <init>(imageresolvercontext), com.google.common.util.concurrent.NSTANCE);
    }

    public (ListenableFuture listenablefuture)
    {
        arg$1 = listenablefuture;
    }
}
