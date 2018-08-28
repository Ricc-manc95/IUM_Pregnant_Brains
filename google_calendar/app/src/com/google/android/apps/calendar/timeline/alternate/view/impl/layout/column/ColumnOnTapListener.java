// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;

import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.view.api.CreationHandler;
import com.google.android.apps.calendar.timeline.alternate.view.impl.TimelineHostView;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.common.base.Optional;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column:
//            ColumnViewport, ColumnDimens

public final class ColumnOnTapListener
    implements android.view.View.OnTouchListener
{

    private final ColumnDimens dimens;
    private final Point gridOffset;
    private final CreationHandler handler;
    private final TimelineHostView hostView;
    private final ObservableReference isRtl;
    private final ObservableReference isTalkBackEnabled;
    private final TimeUtils timeUtils;
    private final ColumnViewport viewport;

    ColumnOnTapListener(TimelineHostView timelinehostview, ColumnViewport columnviewport, Point point, CreationHandler creationhandler, TimeUtils timeutils, ObservableReference observablereference, ColumnDimens columndimens, 
            ObservableReference observablereference1)
    {
        hostView = timelinehostview;
        viewport = columnviewport;
        gridOffset = point;
        handler = creationhandler;
        timeUtils = timeutils;
        isRtl = observablereference;
        dimens = columndimens;
        isTalkBackEnabled = observablereference1;
    }

    public final boolean onTouch(View view, MotionEvent motionevent)
    {
        if (!((Boolean)isTalkBackEnabled.get()).booleanValue())
        {
            view = viewport;
            float f = motionevent.getX();
            int i;
            if (((Boolean)isRtl.get()).booleanValue())
            {
                i = 0;
            } else
            {
                i = gridOffset.x;
            }
            view = view.gridPxToFp16(Math.round(f - (float)i), Math.round(motionevent.getY() - (float)gridOffset.y));
            if (view.isPresent())
            {
                i = Math.round(motionevent.getX());
                motionevent = viewport;
                int j = dimens.multiDayGridStartMarginPx;
                int k = dimens.oneDayGridStartMarginPx;
                j = Math.round(((ColumnViewport) (motionevent)).oneDayRatio * (float)(k - j) + (float)j);
                if (((Boolean)isRtl.get()).booleanValue())
                {
                    if (i > hostView.getMeasuredWidth() - j)
                    {
                        i = 1;
                    } else
                    {
                        i = 0;
                    }
                } else
                if (i < j)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (i == 0)
                {
                    handler.onTap(timeUtils.fp16ToMs(((Long)view.get()).longValue()));
                    return true;
                }
            }
        }
        return false;
    }
}
