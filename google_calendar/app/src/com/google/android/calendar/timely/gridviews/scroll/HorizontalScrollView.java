// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews.scroll;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import com.google.android.calendar.timely.gridviews.GridDayView;
import com.google.android.calendar.timely.gridviews.GridViewFrame;
import com.google.android.calendar.timely.gridviews.allday.AttendeeAllDayHeaderView;
import com.google.android.calendar.timely.gridviews.allday.ExpandableChipColumnView;
import com.google.android.calendar.timely.gridviews.attendees.AttendeeInfoLayout;

public class HorizontalScrollView extends android.widget.HorizontalScrollView
{

    private AttendeeAllDayHeaderView allDayView;
    private GridViewFrame attendeeFrame;
    private AttendeeInfoLayout attendeeInfoLayout;
    private int maxColumns;

    public HorizontalScrollView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        maxColumns = context.getResources().getInteger(0x7f11001d);
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        attendeeFrame = (GridViewFrame)findViewById(0x7f100008);
        attendeeInfoLayout = (AttendeeInfoLayout)findViewById(0x7f10000a);
        allDayView = (AttendeeAllDayHeaderView)findViewById(0x7f100005);
    }

    protected void onMeasure(int i, int j)
    {
        int k = 0;
        if (android.view.View.MeasureSpec.getMode(i) != 0 && attendeeFrame != null)
        {
            GridViewFrame gridviewframe = attendeeFrame;
            if (gridviewframe.getChildCount() - gridviewframe.getChildrenBeforeGridDayViews() > 0)
            {
                int i1 = android.view.View.MeasureSpec.getSize(i);
                Object obj = attendeeFrame;
                int l = ((GridViewFrame) (obj)).getChildCount() - ((GridViewFrame) (obj)).getChildrenBeforeGridDayViews();
                i1 = (int)((double)i1 / Math.min((double)maxColumns + 0.5D, l));
                obj = attendeeFrame;
                obj = ((GridDayView)((GridViewFrame) (obj)).getChildAt(((GridViewFrame) (obj)).getChildrenBeforeGridDayViews() + 0)).getLayoutParams();
                obj.width = i1;
                for (; k < l; k++)
                {
                    GridViewFrame gridviewframe1 = attendeeFrame;
                    ((GridDayView)gridviewframe1.getChildAt(gridviewframe1.getChildrenBeforeGridDayViews() + k)).setLayoutParams(((android.view.ViewGroup.LayoutParams) (obj)));
                }

                obj = allDayView.getLayoutParams();
                obj.width = ((ExpandableChipColumnView) (allDayView)).columnCount * i1;
                allDayView.setLayoutParams(((android.view.ViewGroup.LayoutParams) (obj)));
                attendeeInfoLayout.setColumnWidth(i1);
            }
        }
        super.onMeasure(i, j);
    }
}
