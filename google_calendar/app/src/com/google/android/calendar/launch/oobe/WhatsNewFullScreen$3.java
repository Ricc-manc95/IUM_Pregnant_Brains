// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.launch.oobe;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.calendar.utils.AccessibilityUtils;
import com.google.android.calendar.utils.viewpager.LayoutDirectionAwareViewPager;

// Referenced classes of package com.google.android.calendar.launch.oobe:
//            WhatsNewFullScreen

final class val.sameTop extends AnimatorListenerAdapter
{

    private final WhatsNewFullScreen this$0;
    private final boolean val$sameTop;

    public final void onAnimationEnd(Animator animator)
    {
        super.onAnimationEnd(animator);
        if (val$sameTop)
        {
            basePage.findViewById(0x7f1000d1).setAlpha(0.0F);
            animator = basePage.findViewById(0x7f1002ca);
            if (animator != null)
            {
                animator.setAlpha(0.0F);
            }
        }
        viewPager.setEnabled(true);
        footer.setEnabled(true);
        animator = WhatsNewFullScreen.this;
        if (AccessibilityUtils.isAccessibilityEnabled(animator.getApplicationContext()))
        {
            ((WhatsNewFullScreen) (animator)).viewPager.post(new <init>(animator));
        }
    }

    areViewPager()
    {
        this$0 = final_whatsnewfullscreen;
        val$sameTop = Z.this;
        super();
    }
}
