// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.graphics.drawable;

import android.animation.ValueAnimator;
import com.google.android.apps.calendar.util.validator.DirectValidator;

// Referenced classes of package com.google.android.apps.calendar.graphics.drawable:
//            CustomAlphaDrawable

public final class arg._cls1
    implements android.animation.orUpdateListener
{

    private final CustomAlphaDrawable arg$1;

    public final void onAnimationUpdate(ValueAnimator valueanimator)
    {
        CustomAlphaDrawable customalphadrawable = arg$1;
        customalphadrawable.customAlpha = ((Integer)valueanimator.getAnimatedValue()).intValue();
        customalphadrawable.actualAlphaValidator.isValid = false;
        customalphadrawable.invalidateSelf();
    }

    public (CustomAlphaDrawable customalphadrawable)
    {
        arg$1 = customalphadrawable;
    }
}
