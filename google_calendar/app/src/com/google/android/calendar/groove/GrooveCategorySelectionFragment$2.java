// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import com.google.android.calendar.common.view.ObservableScrollView;

// Referenced classes of package com.google.android.calendar.groove:
//            GrooveCategorySelectionFragment

final class this._cls0
    implements android.view.tener
{

    private final GrooveCategorySelectionFragment this$0;

    public final void onGlobalLayout()
    {
        GrooveCategorySelectionFragment groovecategoryselectionfragment = GrooveCategorySelectionFragment.this;
        boolean flag;
        if (((Fragment) (groovecategoryselectionfragment)).mHost != null && ((Fragment) (groovecategoryselectionfragment)).mAdded)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            return;
        }
        groovecategoryselectionfragment = GrooveCategorySelectionFragment.this;
        Resources resources = groovecategoryselectionfragment.requireContext().getResources();
        int i = groovecategoryselectionfragment.scrollView.getScrollY();
        if (!groovecategoryselectionfragment.actionBarTitleShowing && i > resources.getDimensionPixelSize(0x7f0e01e1))
        {
            groovecategoryselectionfragment.actionBarTitleShowing = true;
            groovecategoryselectionfragment.actionBarTitleView.setVisibility(0);
            AnimationSet animationset = new AnimationSet(true);
            animationset.addAnimation(new AlphaAnimation(0.0F, 1.0F));
            TranslateAnimation translateanimation = new TranslateAnimation(0.0F, 0.0F, groovecategoryselectionfragment.requireContext().getResources().getDimensionPixelSize(0x7f0e01e4), 0.0F);
            translateanimation.setInterpolator(new FastOutSlowInInterpolator());
            animationset.addAnimation(translateanimation);
            animationset.setDuration(300L);
            groovecategoryselectionfragment.actionBarTitleView.startAnimation(animationset);
        } else
        if (groovecategoryselectionfragment.actionBarTitleShowing && i <= resources.getDimensionPixelSize(0x7f0e01e2))
        {
            groovecategoryselectionfragment.actionBarTitleShowing = false;
            AnimationSet animationset1 = new AnimationSet(true);
            animationset1.addAnimation(new AlphaAnimation(1.0F, 0.0F));
            TranslateAnimation translateanimation1 = new TranslateAnimation(0.0F, 0.0F, 0.0F, groovecategoryselectionfragment.requireContext().getResources().getDimensionPixelSize(0x7f0e01e4));
            translateanimation1.setInterpolator(new FastOutSlowInInterpolator());
            animationset1.addAnimation(translateanimation1);
            animationset1.setDuration(300L);
            animationset1.setAnimationListener(new <init>(groovecategoryselectionfragment));
            groovecategoryselectionfragment.actionBarTitleView.startAnimation(animationset1);
        }
        if (i > 0)
        {
            int j = resources.getDimensionPixelSize(0x7f0e01e3);
            groovecategoryselectionfragment.actionBar.setElevation(Math.min((float)i / 5F, j));
            return;
        } else
        {
            groovecategoryselectionfragment.actionBar.setElevation(0.0F);
            return;
        }
    }

    ()
    {
        this$0 = GrooveCategorySelectionFragment.this;
        super();
    }
}
