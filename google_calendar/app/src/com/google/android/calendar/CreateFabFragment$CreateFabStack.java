// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.animation.ObjectAnimator;
import android.view.View;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.utils.animation.QuantumInterpolators;
import java.util.Stack;

// Referenced classes of package com.google.android.calendar:
//            SpeedDialLayout

public abstract class ors
{

    public final Stack stack = new Stack();

    abstract View findCreateFab();

    abstract Time getCreateFabDay();

    public final ObjectAnimator getHideAnimatorCreateFab()
    {
        class Scope
        {

            public final View createFab;
            public final View createFabParent;
            public final StartDay startDay;

            Scope(View view, View view1, StartDay startday)
            {
                createFab = view;
                createFabParent = view1;
                startDay = startday;
            }
        }

        Object obj;
        if (stack.empty())
        {
            obj = findCreateFab();
        } else
        {
            obj = ((Scope)stack.peek()).createFab;
        }
        if (obj == null)
        {
            return null;
        } else
        {
            int i = (int)((View) (obj)).getTranslationY();
            int j = ((View)((View) (obj)).getParent()).getHeight();
            int k = ((View) (obj)).getTop();
            obj = ObjectAnimator.ofFloat(obj, View.TRANSLATION_Y, new float[] {
                (float)i, (float)(j - k)
            });
            ((ObjectAnimator) (obj)).setInterpolator(QuantumInterpolators.FAST_OUT_SLOW_IN);
            return ((ObjectAnimator) (obj));
        }
    }

    public final ObjectAnimator getShowAnimatorCreateFab()
    {
        Object obj;
        if (stack.empty())
        {
            obj = findCreateFab();
        } else
        {
            obj = ((Scope)stack.peek()).createFab;
        }
        if (obj == null)
        {
            return null;
        }
        int i = (int)((View) (obj)).getTranslationY();
        if (i < 0)
        {
            return null;
        } else
        {
            obj = ObjectAnimator.ofFloat(obj, View.TRANSLATION_Y, new float[] {
                (float)i, 0.0F
            });
            ((ObjectAnimator) (obj)).setInterpolator(QuantumInterpolators.FAST_OUT_SLOW_IN);
            return ((ObjectAnimator) (obj));
        }
    }

    public SpeedDialLayout getSpeedDial()
    {
        View view;
        if (stack.empty())
        {
            view = null;
        } else
        {
            view = ((Scope)stack.peek()).createFabParent;
        }
        if (view == null)
        {
            return null;
        } else
        {
            return (SpeedDialLayout)view.findViewById(0x7f10013b);
        }
    }

    ors()
    {
    }
}
