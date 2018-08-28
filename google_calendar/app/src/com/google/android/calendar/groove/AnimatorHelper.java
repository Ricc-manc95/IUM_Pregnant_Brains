// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.transition.TransitionSet;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collection;

// Referenced classes of package com.google.android.calendar.groove:
//            GrooveScheduleView

public final class AnimatorHelper
{

    public static AnimatorSet createFadeOutFadeInAnimator(View view, final FadeOutFadeInAnimatorListener listener)
    {
        ObjectAnimator objectanimator = ObjectAnimator.ofFloat(view, "alpha", new float[] {
            1.0F, 0.0F
        });
        objectanimator.addListener(new _cls1());
        objectanimator.setDuration(52L);
        view = ObjectAnimator.ofFloat(view, "alpha", new float[] {
            0.0F, 1.0F
        });
        view.setDuration(52L);
        listener = new AnimatorSet();
        listener.setInterpolator(new LinearInterpolator());
        listener.playSequentially(new Animator[] {
            objectanimator, view
        });
        return listener;
    }

    public static TransitionSet createSlideTransition(Activity activity, boolean flag)
    {
        Object obj;
        byte byte0;
        int i;
        if (activity.getResources().getConfiguration().getLayoutDirection() == 1)
        {
            byte0 = 1;
        } else
        {
            byte0 = 0;
        }
        if (byte0 ^ flag)
        {
            byte0 = -1;
        } else
        {
            byte0 = 1;
        }
        obj = ((WindowManager)activity.getSystemService("window")).getDefaultDisplay();
        if (activity.getResources().getBoolean(0x7f0c0016))
        {
            activity = new Point();
            ((Display) (obj)).getRealSize(activity);
            i = Math.max(((Point) (activity)).x, ((Point) (activity)).y) / 2;
        } else
        {
            i = ((Display) (obj)).getWidth();
        }
        activity = new _cls2();
        obj = new TransitionSet();
        ((TransitionSet) (obj)).addTransition(activity).setDuration(125L);
        return ((TransitionSet) (obj));
    }

    public static void runColorChangeAnimation(GrooveScheduleView groovescheduleview, ViewGroup viewgroup, int i, int j)
    {
        if (i == j)
        {
            return;
        }
        ArrayList arraylist = new ArrayList();
        ArgbEvaluator argbevaluator = new ArgbEvaluator();
        int k = 0;
        while (k < viewgroup.getChildCount()) 
        {
            Object obj = viewgroup.getChildAt(k);
            if (obj instanceof ViewGroup)
            {
                runColorChangeAnimation(groovescheduleview, (ViewGroup)obj, i, j);
            } else
            if (groovescheduleview.shouldChangeColor(((View) (obj))) && (obj instanceof TextView))
            {
                obj = (TextView)obj;
                ((TextView) (obj)).setTextColor(j);
                obj = ObjectAnimator.ofInt(obj, "textColor", new int[] {
                    i, j
                });
                ((ObjectAnimator) (obj)).setEvaluator(argbevaluator);
                arraylist.add(obj);
            }
            k++;
        }
        groovescheduleview = new AnimatorSet();
        groovescheduleview.setInterpolator(new FastOutSlowInInterpolator());
        groovescheduleview.setDuration(120L);
        groovescheduleview.playTogether(arraylist);
        groovescheduleview.start();
    }

    private class _cls1
        implements android.animation.Animator.AnimatorListener
    {

        private final FadeOutFadeInAnimatorListener val$listener;

        public final void onAnimationCancel(Animator animator)
        {
        }

        public final void onAnimationEnd(Animator animator)
        {
            listener.fadeOutAnimationEnd();
        }

        public final void onAnimationRepeat(Animator animator)
        {
        }

        public final void onAnimationStart(Animator animator)
        {
        }

        _cls1()
        {
            listener = fadeoutfadeinanimatorlistener;
            super();
        }

        private class FadeOutFadeInAnimatorListener
        {

            public abstract void fadeOutAnimationEnd();
        }

    }


    private class _cls2 extends Visibility
    {

        private final float val$translateX;

        public final Animator onAppear(ViewGroup viewgroup, View view, TransitionValues transitionvalues, TransitionValues transitionvalues1)
        {
            if (transitionvalues1 == null)
            {
                return null;
            } else
            {
                viewgroup = ObjectAnimator.ofFloat(view, "translationX", new float[] {
                    translateX, 0.0F
                });
                viewgroup.setInterpolator(new FastOutSlowInInterpolator());
                return viewgroup;
            }
        }

        public final Animator onDisappear(ViewGroup viewgroup, View view, TransitionValues transitionvalues, TransitionValues transitionvalues1)
        {
            if (transitionvalues == null)
            {
                return null;
            } else
            {
                viewgroup = ObjectAnimator.ofFloat(view, "translationX", new float[] {
                    0.0F, translateX
                });
                viewgroup.setInterpolator(new FastOutSlowInInterpolator());
                return viewgroup;
            }
        }

        _cls2()
        {
            translateX = f;
            super();
        }
    }

}
