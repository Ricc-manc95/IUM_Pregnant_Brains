// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import com.google.android.calendar.LaunchInfoActivityUtils;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.TimelineItemUtil;
import com.google.android.calendar.utils.ColorUtils;

// Referenced classes of package com.google.android.calendar.widget:
//            WidgetUtils

public abstract class item extends item
{

    public fo dayInfo;
    public boolean isFirst;
    public final TimelineItem item;

    final Intent createTimelineItemFillIntent(Context context, Object obj)
    {
        context = LaunchInfoActivityUtils.getLaunchFillInIntent(context, obj);
        TimelineItemUtil.setLaunchTimelineItem(context, item);
        context.putExtra("beginTime", item.getStartMillis());
        context.putExtra("allDay", true);
        return context;
    }

    protected String getTitleDescription(tViewContext tviewcontext, boolean flag)
    {
        return item.getTitle();
    }

    public abstract void setOnClickFillInIntent(Context context, RemoteViews remoteviews);

    public void updateStatus(tViewContext tviewcontext, RemoteViews remoteviews)
    {
        int i;
        int j;
        boolean flag = false;
        super.pdateStatus(tviewcontext, remoteviews);
        com.google.android.calendar.timeline.chip.olorStyle olorstyle;
        int k;
        if (TimelineItemUtil.isOutOfOfficeEvent(item))
        {
            i = TimelineItemUtil.getTimelineItemColor(item);
        } else
        {
            i = ColorUtils.getDisplayColorFromColor(item.getColor());
        }
        olorstyle = TimelineItemUtil.getColorStyle(item);
        switch (olorstyle.ordinal())
        {
        default:
            j = 0x7f020281;
            break;

        case 1: // '\001'
            break MISSING_BLOCK_LABEL_193;
        }
_L1:
        k = WidgetUtils.getChipTextColor(olorstyle, i, tviewcontext.whiteTextColor, tviewcontext.greyTextColor);
        remoteviews.setImageViewResource(0x7f100399, j);
        remoteviews.setInt(0x7f100399, "setColorFilter", i);
        updateTextViews(tviewcontext, remoteviews, k);
        remoteviews.setContentDescription(0x7f100398, TimelineItemUtil.createContentDescription(tviewcontext.context, item, false, "", "", getTitleDescription(tviewcontext, true)));
        if (isFirst)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 8;
        }
        remoteviews.setViewVisibility(0x7f1003a0, i);
        remoteviews.setViewVisibility(0x7f1003a1, i);
        if (i == 0)
        {
            dayInfo.updateStatus(tviewcontext, remoteviews);
        }
        return;
        j = 0x7f020283;
          goto _L1
    }

    public abstract void updateTextViews(tViewContext tviewcontext, RemoteViews remoteviews, int i);

    tViewContext(TimelineItem timelineitem)
    {
        item = timelineitem;
    }
}
