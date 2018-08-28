// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20.view;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentController;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.libraries.hats20.view:
//            BaseFragment

public class SurveyViewPager extends ViewPager
{

    public SurveyViewPager(Context context)
    {
        super(context);
        setPageMargin(getResources().getDimensionPixelSize(0x7f0e023f));
        setOffscreenPageLimit(0x7fffffff);
        addOnPageChangeListener(new _cls1());
    }

    public SurveyViewPager(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        setPageMargin(getResources().getDimensionPixelSize(0x7f0e023f));
        setOffscreenPageLimit(0x7fffffff);
        addOnPageChangeListener(new _cls1());
    }

    public final BaseFragment getCurrentItemFragment()
    {
        if (!(getContext() instanceof FragmentActivity))
        {
            Log.e("HatsLibSurveyViewPager", "Context is not a SurveyPromptActivity, something is very wrong.");
            return null;
        }
        int i = getCurrentItem();
        for (Iterator iterator = ((FragmentActivity)getContext()).mFragments.mHost.mFragmentManager.getFragments().iterator(); iterator.hasNext();)
        {
            Fragment fragment = (Fragment)iterator.next();
            if (fragment.getArguments().getInt("QuestionIndex", -1) == i && (fragment instanceof BaseFragment))
            {
                return (BaseFragment)fragment;
            }
        }

        Log.e("HatsLibSurveyViewPager", "No Fragment found for the current item, something is very wrong.");
        return null;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionevent)
    {
        return false;
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        return false;
    }

    private class _cls1 extends android.support.v4.view.ViewPager.SimpleOnPageChangeListener
    {

        private final SurveyViewPager this$0;

        public final void onPageSelected(int i)
        {
            super.onPageSelected(i);
            SurveyViewPager surveyviewpager = SurveyViewPager.this;
            surveyviewpager.getCurrentItemFragment().onPageScrolledIntoView();
            surveyviewpager.getCurrentItemFragment().animateFadeIn();
        }

        _cls1()
        {
            this$0 = SurveyViewPager.this;
            super();
        }
    }

}
