// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.media.impl.basic;

import android.graphics.Bitmap;
import android.widget.ImageView;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Logger;
import com.google.common.util.concurrent.FutureCallback;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.media.impl.basic:
//            BasicImageCache

final class val.imageView
    implements FutureCallback
{

    private final ImageView val$imageView;

    public final void onFailure(Throwable throwable)
    {
        BasicImageCache.logger.w(throwable, "Failed to load image", new Object[0]);
        val$imageView.setVisibility(8);
    }

    public final void onSuccess(Object obj)
    {
        obj = (Bitmap)obj;
        if (obj != null)
        {
            val$imageView.setImageBitmap(((Bitmap) (obj)));
            val$imageView.setVisibility(0);
            return;
        } else
        {
            val$imageView.setVisibility(8);
            return;
        }
    }

    ()
    {
        val$imageView = imageview;
        super();
    }
}
