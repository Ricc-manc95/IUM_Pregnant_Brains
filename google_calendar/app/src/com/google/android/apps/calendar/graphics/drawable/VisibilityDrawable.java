// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.graphics.drawable;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

// Referenced classes of package com.google.android.apps.calendar.graphics.drawable:
//            DrawableWrapper

public final class VisibilityDrawable extends DrawableWrapper
{

    public boolean isHidden;

    public VisibilityDrawable(Drawable drawable)
    {
        super(drawable);
    }

    public final void draw(Canvas canvas)
    {
        if (!isHidden)
        {
            wrappedDrawable.draw(canvas);
        }
    }
}
