// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;

import android.animation.ValueAnimator;
import com.google.android.apps.calendar.timeline.alternate.view.impl.TimelineHostView;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column:
//            AllDayManager

final class arg._cls1
    implements android.animation.dateListener
{

    private final AllDayManager arg$1;

    public final void onAnimationUpdate(ValueAnimator valueanimator)
    {
        AllDayManager alldaymanager = arg$1;
        alldaymanager.animatedHeightPx = ((Integer)valueanimator.getAnimatedValue()).intValue();
        alldaymanager.hostView.invalidate();
        alldaymanager.hostView.requestLayout();
    }

    i(AllDayManager alldaymanager)
    {
        arg$1 = alldaymanager;
    }
}
