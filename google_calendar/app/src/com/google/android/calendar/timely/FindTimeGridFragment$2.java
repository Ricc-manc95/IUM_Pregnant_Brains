// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.view.View;
import com.google.android.calendar.timely.gridviews.allday.AllDayHeaderArrow;
import com.google.android.calendar.timely.gridviews.allday.ExpandableChipColumnView;

// Referenced classes of package com.google.android.calendar.timely:
//            FindTimeGridFragment

final class this._cls0
    implements android.view.indTimeGridFragment._cls2
{

    private final FindTimeGridFragment this$0;

    public final void onClick(View view)
    {
        int i = 1;
        boolean flag;
        if (!((ExpandableChipColumnView) (allDayEventView)).isExpanded)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        view = allDayHeaderArrow;
        if (!flag)
        {
            i = 2;
        }
        view.setState(i);
        view = allDayEventView;
        if (flag != ((ExpandableChipColumnView) (view)).isExpanded)
        {
            view.isExpanded = flag;
            view.applyExpandedOrCollapsedState();
            view.onExpandStateChanged$51D2ILG_0();
        }
    }

    HeaderArrow()
    {
        this$0 = FindTimeGridFragment.this;
        super();
    }
}
