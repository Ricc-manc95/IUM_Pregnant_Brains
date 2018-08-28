// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20.view;


// Referenced classes of package com.google.android.libraries.hats20.view:
//            SurveyViewPager, BaseFragment

final class r extends android.support.v4.view.PageChangeListener
{

    private final SurveyViewPager this$0;

    public final void onPageSelected(int i)
    {
        super.onPageSelected(i);
        SurveyViewPager surveyviewpager = SurveyViewPager.this;
        surveyviewpager.getCurrentItemFragment().onPageScrolledIntoView();
        surveyviewpager.getCurrentItemFragment().animateFadeIn();
    }

    r()
    {
        this$0 = SurveyViewPager.this;
        super();
    }
}
