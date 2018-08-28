// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.android.calendarcommon2.LogUtils;
import java.util.Arrays;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout:
//            LayoutUpdaterImpl

final class arg._cls1
    implements android.support.v7.widget.erCallback
{

    private final LayoutUpdaterImpl arg$1;

    public final int onGetChildDrawingOrder(int i, int j)
    {
        LayoutUpdaterImpl layoutupdaterimpl;
        layoutupdaterimpl = arg$1;
        if (i != layoutupdaterimpl.expectedViewCount)
        {
            LogUtils.wtf(LayoutUpdaterImpl.TAG, "Unexpected child count %d, %d", new Object[] {
                Integer.valueOf(layoutupdaterimpl.expectedViewCount), Integer.valueOf(i)
            });
            layoutupdaterimpl.sortedViewsValid = false;
        }
        if (!layoutupdaterimpl.sortedViewsValid)
        {
            if (layoutupdaterimpl.sortedViews.length < i)
            {
                layoutupdaterimpl.sortedViews = new ms[i];
            }
            android.support.v7.widget.ew.impl.layout.LayoutUpdaterImpl layoutupdaterimpl1 = layoutupdaterimpl.recyclerView.mLayout;
            for (int k = 0; k < i; k++)
            {
                layoutupdaterimpl.sortedViews[k] = (ms)layoutupdaterimpl1.etChildAt(k).getLayoutParams();
                layoutupdaterimpl.sortedViews[k].index = k;
            }

            Arrays.sort(layoutupdaterimpl.sortedViews, 0, i, .instance);
            layoutupdaterimpl.sortedViewsValid = true;
        }
        if (j < layoutupdaterimpl.sortedViews.length) goto _L2; else goto _L1
_L1:
        LogUtils.wtf(LayoutUpdaterImpl.TAG, "Child index out of bounds %d, %d", new Object[] {
            Integer.valueOf(j), Integer.valueOf(layoutupdaterimpl.sortedViews.length)
        });
        j = 0;
_L4:
        return j;
_L2:
        int l;
        l = layoutupdaterimpl.sortedViews[j].index;
        if (l < 0)
        {
            break; /* Loop/switch isn't completed */
        }
        j = l;
        if (l < i) goto _L4; else goto _L3
_L3:
        LogUtils.wtf(LayoutUpdaterImpl.TAG, "Index out of bounds %d, %d", new Object[] {
            Integer.valueOf(l), Integer.valueOf(i)
        });
        return Math.min(Math.max(0, l), i - 1);
    }

    ms(LayoutUpdaterImpl layoutupdaterimpl)
    {
        arg$1 = layoutupdaterimpl;
    }
}
