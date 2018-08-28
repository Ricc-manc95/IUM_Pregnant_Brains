// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.graphics.drawable;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import com.google.android.apps.calendar.util.validator.DirectValidator;

// Referenced classes of package com.google.android.apps.calendar.graphics.drawable:
//            DrawableWrapper

public final class CustomAlphaDrawable extends DrawableWrapper
{

    public int actualAlpha;
    public final DirectValidator actualAlphaValidator = new DirectValidator(new .Lambda._cls0());
    public int alpha;
    public int customAlpha;

    public CustomAlphaDrawable(Drawable drawable)
    {
        super(drawable);
        alpha = 255;
        customAlpha = 255;
        actualAlpha = 255;
        class .Lambda._cls0
            implements Runnable
        {

            private final CustomAlphaDrawable arg$1;

            public final void run()
            {
                CustomAlphaDrawable customalphadrawable = arg$1;
                customalphadrawable.actualAlpha = (customalphadrawable.alpha * customalphadrawable.customAlpha) / 255;
                customalphadrawable.wrappedDrawable.setAlpha(customalphadrawable.actualAlpha);
            }

            .Lambda._cls0()
            {
                arg$1 = CustomAlphaDrawable.this;
            }
        }

    }

    public final void draw(Canvas canvas)
    {
        DirectValidator directvalidator = actualAlphaValidator;
        if (!directvalidator.isValid)
        {
            directvalidator.validateRunnable.run();
            directvalidator.isValid = true;
        }
        super.draw(canvas);
    }

    public final void setAlpha(int i)
    {
        alpha = i;
        actualAlphaValidator.isValid = false;
        invalidateSelf();
    }
}
