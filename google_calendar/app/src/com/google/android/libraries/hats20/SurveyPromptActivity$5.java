// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20;

import android.animation.ValueAnimator;
import android.widget.FrameLayout;

// Referenced classes of package com.google.android.libraries.hats20:
//            SurveyPromptActivity

final class this._cls0
    implements android.animation.pdateListener
{

    private final SurveyPromptActivity this$0;

    public final void onAnimationUpdate(ValueAnimator valueanimator)
    {
        overallContainer.getLayoutParams().height = ((Integer)valueanimator.getAnimatedValue()).intValue();
        overallContainer.requestLayout();
    }

    ()
    {
        this$0 = SurveyPromptActivity.this;
        super();
    }
}
