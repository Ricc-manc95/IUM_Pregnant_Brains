// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import android.animation.Animator;
import android.view.ViewGroup;
import com.google.android.calendar.timeline.chip.Chip;
import com.google.android.calendar.utils.animation.NoClipChildrenAnimatorListener;
import com.google.common.base.Function;

// Referenced classes of package com.google.android.calendar.timely.gridviews:
//            GridDayView

final class arg._cls2
    implements Function
{

    private final GridDayView arg$1;
    private final Function arg$2;

    public final Object apply(Object obj)
    {
        Object obj1 = arg$1;
        obj = (Animator)arg$2.apply((Chip)obj);
        obj1 = ((GridDayView) (obj1)).getParent();
        if (obj1 instanceof ViewGroup)
        {
            ((Animator) (obj)).addListener(new NoClipChildrenAnimatorListener((ViewGroup)obj1));
        }
        return obj;
    }

    istener(GridDayView griddayview, Function function)
    {
        arg$1 = griddayview;
        arg$2 = function;
    }
}
