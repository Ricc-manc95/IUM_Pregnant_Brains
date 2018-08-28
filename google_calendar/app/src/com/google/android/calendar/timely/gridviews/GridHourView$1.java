// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import com.google.android.calendar.timely.settings.common.OnPropertyChangedListener;

// Referenced classes of package com.google.android.calendar.timely.gridviews:
//            GridHourView, GridHourDrawable

final class this._cls0
    implements OnPropertyChangedListener
{

    private final GridHourView this$0;

    public final void onCalendarPropertyChanged(int i, Object obj)
    {
        if (i == 10)
        {
            GridHourDrawable gridhourdrawable = gridHourDrawable;
            gridhourdrawable.intervalHeight = ((Integer)obj).intValue();
            gridhourdrawable.invalidateSelf();
            requestLayout();
        }
    }

    e()
    {
        this$0 = GridHourView.this;
        super();
    }
}
