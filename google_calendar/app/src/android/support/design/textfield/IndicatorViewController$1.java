// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.textfield;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.widget.TextView;

// Referenced classes of package android.support.design.textfield:
//            IndicatorViewController

final class val.captionViewToShow extends AnimatorListenerAdapter
{

    private final IndicatorViewController this$0;
    private final int val$captionToHide;
    private final int val$captionToShow;
    private final TextView val$captionViewToHide;
    private final TextView val$captionViewToShow;

    public final void onAnimationEnd(Animator animator)
    {
        captionDisplayed = val$captionToShow;
        captionAnimator = null;
        if (val$captionViewToHide != null)
        {
            val$captionViewToHide.setVisibility(4);
            if (val$captionToHide == 1 && errorView != null)
            {
                errorView.setText(null);
            }
        }
    }

    public final void onAnimationStart(Animator animator)
    {
        if (val$captionViewToShow != null)
        {
            val$captionViewToShow.setVisibility(0);
        }
    }

    ()
    {
        this$0 = final_indicatorviewcontroller;
        val$captionToShow = i;
        val$captionViewToHide = textview;
        val$captionToHide = j;
        val$captionViewToShow = TextView.this;
        super();
    }
}
