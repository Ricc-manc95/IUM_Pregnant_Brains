// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.image;

import android.content.Context;
import com.android.bitmap.drawable.BasicBitmapDrawable;
import com.google.android.calendar.timeline.chip.image.ImageResolverContext;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;

// Referenced classes of package com.google.android.calendar.event.image:
//            EventImageRequestKey, BitmapCacheHolder

final class arg._cls1
    implements AsyncFunction
{

    private final ImageResolverContext arg$1;

    public final ListenableFuture apply(Object obj)
    {
        Object obj2 = arg$1;
        obj = (EventImageRequestKey)obj;
        Object obj1 = ((ImageResolverContext) (obj2)).context().getResources();
        int i = ((ImageResolverContext) (obj2)).preferredWidth();
        int j = ((ImageResolverContext) (obj2)).preferredHeight();
        EventImageCache eventimagecache = BitmapCacheHolder.getEventImageCache();
        obj2 = new SettableFuture();
        obj1 = new com.google.android.apps.calendar.bitmap.eCache(((android.content.res.Resources) (obj1)), eventimagecache, false, ((SettableFuture) (obj2)), 1.0F, 0.49F);
        ((BasicBitmapDrawable) (obj1)).setDecodeDimensions(i, j);
        ((BasicBitmapDrawable) (obj1)).bind(((com.android.bitmap.RequestKey) (obj)));
        ((AbstractFuture) (obj2)).addListener(new com.google.android.apps.calendar.bitmap.t>(((SettableFuture) (obj2)), ((BasicBitmapDrawable) (obj1))), com.google.common.util.concurrent.NSTANCE);
        return AbstractTransformFuture.create(((ListenableFuture) (obj2)), new <init>(((EventImageRequestKey) (obj))), com.google.common.util.concurrent.NSTANCE);
    }

    (ImageResolverContext imageresolvercontext)
    {
        arg$1 = imageresolvercontext;
    }
}
