// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.datetimepicker.time;

import android.animation.ValueAnimator;

// Referenced classes of package com.android.datetimepicker.time:
//            RadialSelectorView

final class this._cls0
    implements android.animation.pdateListener
{

    private final RadialSelectorView this$0;

    public final void onAnimationUpdate(ValueAnimator valueanimator)
    {
        invalidate();
    }

    Q()
    {
        this$0 = RadialSelectorView.this;
        super();
    }
}
