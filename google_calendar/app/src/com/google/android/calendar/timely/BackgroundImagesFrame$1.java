// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

// Referenced classes of package com.google.android.calendar.timely:
//            BackgroundImagesFrame, BackgroundImageView

final class this._cls0 extends AnimatorListenerAdapter
{

    private final BackgroundImagesFrame this$0;

    public final void onAnimationEnd(Animator animator)
    {
        animator = secondaryBackgroundImageView;
        secondaryBackgroundImageView = primaryBackgroundImageView;
        primaryBackgroundImageView = animator;
        secondaryBackgroundImageView.setVisibility(8);
        if (pendingMonth)
        {
            pendingMonth = false;
            loadSelectedMonth();
            return;
        } else
        {
            doingFade = false;
            return;
        }
    }

    public final void onAnimationStart(Animator animator)
    {
        secondaryBackgroundImageView.setVisibility(0);
    }

    ()
    {
        this$0 = BackgroundImagesFrame.this;
        super();
    }
}
