// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.animation.ValueAnimator;

// Referenced classes of package com.google.android.calendar.timely:
//            BackgroundImagesFrame, BackgroundImageView

final class this._cls0
    implements android.animation.dateListener
{

    private final BackgroundImagesFrame this$0;

    public final void onAnimationUpdate(ValueAnimator valueanimator)
    {
        float f = ((Float)valueanimator.getAnimatedValue()).floatValue();
        secondaryBackgroundImageView.setAlpha(f);
        primaryBackgroundImageView.setAlpha(1.0F - f);
    }

    ()
    {
        this$0 = BackgroundImagesFrame.this;
        super();
    }
}
