// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chip.image;

import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.calendar.timeline.chip.image:
//            ImageResolverContext

public interface ImageResolver
{

    public abstract ListenableFuture resolveImage(ImageResolverContext imageresolvercontext);
}
