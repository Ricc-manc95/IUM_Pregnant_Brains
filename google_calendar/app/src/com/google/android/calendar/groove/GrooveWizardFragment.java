// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.Utils;
import com.google.android.calendar.utils.statusbar.StatusbarAnimatorCompat;

// Referenced classes of package com.google.android.calendar.groove:
//            AnimatorHelper

public class GrooveWizardFragment extends Fragment
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/groove/GrooveWizardFragment);
    public ViewGroup mFrame;

    public GrooveWizardFragment()
    {
    }

    protected static Transition createFadeTransition()
    {
        Fade fade = new Fade();
        fade.setDuration(105);
        return fade;
    }

    protected void adjustCardUi()
    {
        Object obj1 = null;
        if (mFrame == null)
        {
            LogUtils.e(TAG, "Fragment frame is not initialized", new Object[0]);
        } else
        {
            Object obj;
            if (super.mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)super.mHost.mActivity;
            }
            if (((Context) (obj)).getResources().getBoolean(0x7f0c0016))
            {
                if (super.mHost == null)
                {
                    obj = obj1;
                } else
                {
                    obj = (FragmentActivity)super.mHost.mActivity;
                }
                if (!Utils.getConfigBool(((Context) (obj)), 0x7f0c0017))
                {
                    int i = requireContext().getResources().getDimensionPixelSize(0x7f0e038f);
                    ((android.view.ViewGroup.MarginLayoutParams)mFrame.getLayoutParams()).setMargins(0, i, 0, 0);
                }
                mFrame.setElevation(requireContext().getResources().getDimensionPixelSize(0x7f0e007f));
                obj = mFrame;
                ((View) (obj)).setOutlineProvider(new _cls1());
                ((View) (obj)).setClipToOutline(true);
                mFrame.requestLayout();
                return;
            }
        }
    }

    protected final TransitionSet createReenterTransition()
    {
        TransitionSet transitionset = new TransitionSet();
        Object obj;
        Fade fade;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        obj = AnimatorHelper.createSlideTransition(((android.app.Activity) (obj)), true);
        ((TransitionSet) (obj)).addTarget(0x7f100144);
        fade = new Fade();
        fade.setDuration(105);
        transitionset.addTransition(fade.addTarget(0x7f1001ee));
        transitionset.addTransition(((Transition) (obj)));
        transitionset.setOrdering(0);
        return transitionset;
    }

    public final void onViewCreated(View view, Bundle bundle)
    {
        super.onViewCreated(view, bundle);
        view = view.findViewById(0x7f10013e);
        if (view != null)
        {
            view.requestApplyInsets();
        }
    }

    protected final void setStatusBarTheme(int i)
    {
        Object obj;
        boolean flag;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        obj = StatusbarAnimatorCompat.createInstance(((FragmentActivity) (obj)).getWindow());
        if (i != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        ((StatusbarAnimatorCompat) (obj)).tryApplyLightStatusbar(flag, requireContext().getResources().getColor(0x7f0d031f), requireContext().getResources().getColor(0x7f0d0320), 105);
    }


    private class _cls1 extends ViewOutlineProvider
    {

        private final int val$radius;

        public final void getOutline(View view, Outline outline)
        {
            outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), radius);
        }

        _cls1()
        {
            radius = i;
            super();
        }
    }

}
