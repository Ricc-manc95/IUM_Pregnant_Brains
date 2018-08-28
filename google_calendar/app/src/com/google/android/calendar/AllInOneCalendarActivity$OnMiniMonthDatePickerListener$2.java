// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.support.v4.view.ViewPager;
import android.view.View;

// Referenced classes of package com.google.android.calendar:
//            AllInOneCalendarActivity, MiniMonthInteractionController

final class state extends android.support.v4.view.r._cls2
{

    private float bottomDelta;
    private float bottomStart;
    private float positionDelta;
    private float positionStart;
    private int state;
    private final bottomStart this$1;
    private final MiniMonthInteractionController val$controller;

    public final void onPageScrollStateChanged(int i)
    {
        if (datePickerOpen && i == 2)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        state = i;
    }

    public final void onPageScrolled(int i, float f, int j)
    {
label0:
        {
            f = (float)i + f;
            if (state == 1)
            {
                state state1 = this._cls1.this;
                float f1 = state1.tionBarHeight + state1.tePickerContainer.getBottom();
                bottomStart = state1.tePickerContainer.getTranslationY() + f1;
                bottomDelta = (float)(tionBarHeight + val$controller.getCurrentMonthHeight()) - bottomStart;
                positionStart = f;
                positionDelta = (float)tePicker.getCurrentItem() - positionStart;
                if (bottomDelta != 0.0F)
                {
                    i = 2;
                } else
                {
                    i = 0;
                }
                state = i;
            }
            if (state == 2)
            {
                if (f != (float)tePicker.getCurrentItem())
                {
                    break label0;
                }
                tDatePickerBottom(bottomStart + bottomDelta, false);
            }
            return;
        }
        f = (bottomDelta * (f - positionStart)) / positionDelta;
        tDatePickerBottom(f + bottomStart, false);
    }

    ()
    {
        this$1 = final_;
        val$controller = MiniMonthInteractionController.this;
        super();
        state = 0;
    }
}
