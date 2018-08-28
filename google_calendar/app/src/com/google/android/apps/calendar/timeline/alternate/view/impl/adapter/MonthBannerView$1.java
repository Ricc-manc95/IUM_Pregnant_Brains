// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Paint;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            MonthBannerView

final class this._cls0 extends AnimatorListenerAdapter
{

    private final MonthBannerView this$0;

    public final void onAnimationEnd(Animator animator)
    {
        bitmapPaint.setAlpha(255);
        fadeInAnimator = null;
        invalidate();
    }

    ()
    {
        this$0 = MonthBannerView.this;
        super();
    }
}
