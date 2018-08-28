// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import android.animation.ValueAnimator;
import android.util.SparseArray;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

// Referenced classes of package com.google.android.calendar.timely.gridviews:
//            GridHourDrawable

public final class GridHourViewHighlightController
{

    public final Set gridHourDrawables = new HashSet();
    public final SparseArray highlightFadeAnimators = new SparseArray();
    public int lastHighlightedMinute;

    public GridHourViewHighlightController()
    {
        lastHighlightedMinute = -1;
    }

    final void setHighlightedMinute(final int minuteOfDay)
    {
        boolean flag;
        boolean flag1;
        flag = false;
        flag1 = true;
        if (minuteOfDay != lastHighlightedMinute) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int i = lastHighlightedMinute;
        lastHighlightedMinute = minuteOfDay;
        if (i != -1)
        {
            ((ValueAnimator)highlightFadeAnimators.get(i)).reverse();
            Object obj;
            if (highlightFadeAnimators.size() == 1)
            {
                flag = true;
            } else
            {
                flag = false;
            }
        }
        if (minuteOfDay != -1)
        {
            obj = (ValueAnimator)highlightFadeAnimators.get(minuteOfDay);
            if (obj != null)
            {
                ((ValueAnimator) (obj)).reverse();
                minuteOfDay = ((flag) ? 1 : 0);
            } else
            {
                ValueAnimator valueanimator = ValueAnimator.ofInt(new int[] {
                    0, 255
                });
                valueanimator.setDuration(100L);
                valueanimator.addListener(new _cls1());
                valueanimator.start();
                highlightFadeAnimators.put(minuteOfDay, valueanimator);
                minuteOfDay = ((flag1) ? 1 : 0);
            }
        } else
        {
            minuteOfDay = ((flag) ? 1 : 0);
        }
        if (minuteOfDay != 0)
        {
            obj = gridHourDrawables.iterator();
            while (((Iterator) (obj)).hasNext()) 
            {
                ((GridHourDrawable)((Iterator) (obj)).next()).invalidateSelf();
            }
        }
        if (true) goto _L1; else goto _L3
_L3:
    }

    private class _cls1 extends AnimatorListenerAdapter
    {

        private final GridHourViewHighlightController this$0;
        private final int val$minuteOfDay;

        public final void onAnimationEnd(Animator animator)
        {
            if (minuteOfDay != lastHighlightedMinute)
            {
                highlightFadeAnimators.remove(minuteOfDay);
            }
        }

        _cls1()
        {
            this$0 = GridHourViewHighlightController.this;
            minuteOfDay = i;
            super();
        }
    }

}
