// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.title;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

// Referenced classes of package com.google.android.calendar.newapi.segment.title:
//            TitleEditSegment

final class val.color extends AnimatorListenerAdapter
{

    private final TitleEditSegment this$0;
    private final int val$color;

    public final void onAnimationEnd(Animator animator)
    {
        setBackgroundColor(val$color);
        rippleView.setVisibility(8);
        super.onAnimationEnd(animator);
    }

    public final void onAnimationStart(Animator animator)
    {
        super.onAnimationStart(animator);
        rippleView.setBackgroundColor(val$color);
        rippleView.setVisibility(0);
    }

    I()
    {
        this$0 = final_titleeditsegment;
        val$color = I.this;
        super();
    }
}
