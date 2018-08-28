// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.animation.ValueAnimator;
import android.view.View;
import java.util.ArrayList;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelyDayView

final class lastValue
    implements android.animation.ner
{

    private int lastValue;
    private final val.viewsToMove this$1;
    private final boolean val$shouldMoveNowLine;
    private final ArrayList val$viewsToMove;

    public final void onAnimationUpdate(ValueAnimator valueanimator)
    {
        int i = ((Integer)valueanimator.getAnimatedValue()).intValue();
        int k = i - lastValue;
        lastValue = i;
        valueanimator = (android.view.ew.AnimatorProvider._cls2.lastValue)getLayoutParams();
        valueanimator.omMargin = ((android.view.omMargin) (valueanimator)).omMargin + k;
        setLayoutParams(valueanimator);
        if (val$shouldMoveNowLine)
        {
            nowLineYAgenda = nowLineYAgenda + k;
        }
        int l = val$viewsToMove.size();
        for (int j = 0; j < l; j++)
        {
            valueanimator = (View)val$viewsToMove.get(j);
            valueanimator.setY(valueanimator.getY() + (float)k);
        }

    }

    I()
    {
        this$1 = final_i;
        val$shouldMoveNowLine = flag;
        val$viewsToMove = ArrayList.this;
        super();
        lastValue = 0;
    }
}
