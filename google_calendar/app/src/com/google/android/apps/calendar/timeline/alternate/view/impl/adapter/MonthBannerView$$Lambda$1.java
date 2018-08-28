// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import android.animation.ValueAnimator;
import android.graphics.Paint;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            MonthBannerView

final class arg._cls1
    implements android.animation.teListener
{

    private final MonthBannerView arg$1;

    public final void onAnimationUpdate(ValueAnimator valueanimator)
    {
        MonthBannerView monthbannerview = arg$1;
        monthbannerview.bitmapPaint.setAlpha(((Integer)valueanimator.getAnimatedValue()).intValue());
        monthbannerview.invalidate();
    }

    (MonthBannerView monthbannerview)
    {
        arg$1 = monthbannerview;
    }
}
