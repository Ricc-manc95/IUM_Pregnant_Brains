// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import com.google.android.calendar.common.view.overlay.OverlayFragment;

// Referenced classes of package com.google.android.calendar:
//            AllInOneCalendarActivity, EventFragmentHostActivity

final class val.shouldShowFab extends AnimatorListenerAdapter
{

    private final AllInOneCalendarActivity this$0;
    private final OverlayFragment val$fragment;
    private final boolean val$shouldShowFab;

    public final void onAnimationEnd(Animator animator)
    {
        infoFragmentDismiss = null;
        animator = AllInOneCalendarActivity.this;
        OverlayFragment overlayfragment = val$fragment;
        if (!((AllInOneCalendarActivity) (animator)).onSaveInstanceStateCalled)
        {
            animator.finishDismissOverlay(overlayfragment);
        }
    }

    Fragment()
    {
        this$0 = final_allinonecalendaractivity;
        val$fragment = overlayfragment;
        val$shouldShowFab = Z.this;
        super();
    }
}
