// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.graphics.drawable;

import android.graphics.drawable.Drawable;

// Referenced classes of package android.support.graphics.drawable:
//            AnimatedVectorDrawableCompat

final class this._cls0
    implements android.graphics.drawable.ompat._cls1
{

    private final AnimatedVectorDrawableCompat this$0;

    public final void invalidateDrawable(Drawable drawable)
    {
        invalidateSelf();
    }

    public final void scheduleDrawable(Drawable drawable, Runnable runnable, long l)
    {
        scheduleSelf(runnable, l);
    }

    public final void unscheduleDrawable(Drawable drawable, Runnable runnable)
    {
        unscheduleSelf(runnable);
    }

    ()
    {
        this$0 = AnimatedVectorDrawableCompat.this;
        super();
    }
}
