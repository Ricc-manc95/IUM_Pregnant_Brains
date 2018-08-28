// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.alternate;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.view.View;
import android.widget.TextView;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.timeline.alternate.view.api.ScreenType;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.Utils;
import com.google.android.calendar.material.Material;
import com.google.android.calendar.timely.AlternateTimelineAnimatedToolbarTitleHelperImpl;
import com.google.android.calendar.timely.MonthLabelProvider;
import com.google.android.calendar.timely.TrommelAnimationHelper;

public final class AlternateTimelineAnimatedToolbarTitleHelper extends ViewPropertyAnimatorListenerAdapter
    implements com.google.android.apps.calendar.timeline.alternate.view.api.TimelineSpi.ToolbarTitleHelper
{

    private final AlternateTimelineAnimatedToolbarTitleHelperImpl toolbarTitleHelper;

    AlternateTimelineAnimatedToolbarTitleHelper(Activity activity, ObservableReference observablereference)
    {
        boolean flag;
        boolean flag1;
        if ((ScreenType)observablereference.get() == ScreenType.PHONE)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        toolbarTitleHelper = new AlternateTimelineAnimatedToolbarTitleHelperImpl(activity, new MonthLabelProvider(activity, flag1, Utils.getCurrentYear(activity)));
    }

    public final void onVerticalScroll(int i, com.google.android.apps.calendar.timeline.alternate.view.api.TimelineSpi.ToolbarAnimation toolbaranimation, Consumer consumer)
    {
        AlternateTimelineAnimatedToolbarTitleHelperImpl alternatetimelineanimatedtoolbartitlehelperimpl;
label0:
        {
label1:
            {
                if (toolbarTitleHelper == null)
                {
                    break label1;
                }
                alternatetimelineanimatedtoolbartitlehelperimpl = toolbarTitleHelper;
                if (alternatetimelineanimatedtoolbartitlehelperimpl.secondaryDateView == null)
                {
                    Object obj = alternatetimelineanimatedtoolbartitlehelperimpl.activity.findViewById(0x7f100105);
                    if (obj == null)
                    {
                        break label1;
                    }
                    alternatetimelineanimatedtoolbartitlehelperimpl.secondaryDateView = (TextView)((View) (obj)).findViewById(0x7f100106);
                    alternatetimelineanimatedtoolbartitlehelperimpl.animationHelper = new TrommelAnimationHelper(alternatetimelineanimatedtoolbartitlehelperimpl.activity.findViewById(0x7f100101), ((View) (obj)), alternatetimelineanimatedtoolbartitlehelperimpl);
                    alternatetimelineanimatedtoolbartitlehelperimpl.secondaryAlternateCalendarView = (TextView)((View) (obj)).findViewById(0x7f100107);
                    obj = Features.instance;
                    if (obj == null)
                    {
                        throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
                    }
                    ((FeatureConfig)obj).google_material();
                    TextView textview = alternatetimelineanimatedtoolbartitlehelperimpl.secondaryDateView;
                    if (Material.robotoMedium != null)
                    {
                        obj = Material.robotoMedium;
                    } else
                    {
                        obj = Typeface.create("sans-serif-medium", 0);
                        Material.robotoMedium = ((Typeface) (obj));
                    }
                    textview.setTypeface(((Typeface) (obj)));
                }
                alternatetimelineanimatedtoolbartitlehelperimpl.targetDay = i;
                if (!alternatetimelineanimatedtoolbartitlehelperimpl.animationHelper.animating)
                {
                    alternatetimelineanimatedtoolbartitlehelperimpl.updateRangeAction = consumer;
                    if (!com.google.android.apps.calendar.timeline.alternate.view.api.TimelineSpi.ToolbarAnimation.NONE.equals(toolbaranimation))
                    {
                        break label0;
                    }
                    consumer.accept(Integer.valueOf(i));
                }
            }
            return;
        }
        alternatetimelineanimatedtoolbartitlehelperimpl.animationHelper.startAnimation(com.google.android.apps.calendar.timeline.alternate.view.api.TimelineSpi.ToolbarAnimation.BACKWARD.equals(toolbaranimation));
    }
}
