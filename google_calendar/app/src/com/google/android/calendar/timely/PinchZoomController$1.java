// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.view.ScaleGestureDetector;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.timely.settings.data.CalendarProperties;

// Referenced classes of package com.google.android.calendar.timely:
//            PinchZoomController, PagedScrollView

final class this._cls0
    implements android.view.nScaleGestureListener
{

    private int adjustedMinimumCellHeight;
    private final PinchZoomController this$0;

    public final boolean onScale(ScaleGestureDetector scalegesturedetector)
    {
        boolean flag = false;
        float f1 = Math.max(minYSpan, Math.abs(scalegesturedetector.getCurrentSpanY()));
        int k = cellHeight;
        cellHeight = (int)((cellHeightBeforeScaleGesture * f1) / startingSpanY);
        float f = (scrollRemainder * f1) / startingSpanY;
        int i;
        int j;
        if (cellHeight <= adjustedMinimumCellHeight)
        {
            LogUtils.d(PinchZoomController.TAG, "Cell height <= Min Cell Height", new Object[0]);
            startingSpanY = f1;
            cellHeight = adjustedMinimumCellHeight;
            cellHeightBeforeScaleGesture = adjustedMinimumCellHeight;
            scrollRemainder = f;
        } else
        if (cellHeight >= maxCellHeight)
        {
            LogUtils.d(PinchZoomController.TAG, "Cell height >= Max Cell Height", new Object[0]);
            startingSpanY = f1;
            cellHeight = maxCellHeight;
            cellHeightBeforeScaleGesture = maxCellHeight;
            scrollRemainder = f;
        }
        f1 = (float)scrollViewHeight - scalegesturedetector.getFocusY();
        i = (int)(((float)(gestureCenterHourFromBottom * (cellHeight + gridlineHeight)) - f1) + (float)bottomPadding + f);
        j = ((cellHeight + gridlineHeight) * 24 + bottomPadding) - scrollViewHeight;
        if (i < 0)
        {
            LogUtils.d(PinchZoomController.TAG, "onScale: n<0: newScrollPosition: %d", new Object[] {
                Integer.valueOf(i)
            });
            i = 0;
        } else
        if (i > j)
        {
            LogUtils.d(PinchZoomController.TAG, "onScale: n>m: newScrollPosition: %d, maxScrollPosition: %d", new Object[] {
                Integer.valueOf(i), Integer.valueOf(j)
            });
            i = j;
        }
        LogUtils.d(PinchZoomController.TAG, "onScale: deltaY: %f, original cell height: %d, cell height: %d, day height: %d, new scroll position: %d", new Object[] {
            Float.valueOf(f1), Integer.valueOf(k), Integer.valueOf(cellHeight), Integer.valueOf((cellHeight + gridlineHeight) * 24 + bottomPadding), Integer.valueOf(i)
        });
        scalegesturedetector = scrollManager;
        if (k != cellHeight)
        {
            flag = true;
        }
        scalegesturedetector.isScaleInProgress = flag;
        calendarProperties.setPropertyValue(10, Integer.valueOf(cellHeight));
        scalegesturedetector = scrollManager;
        if (i != ((Manager) (scalegesturedetector)).verticalScrollPositionFromBottom)
        {
            scalegesturedetector.verticalScrollPositionFromBottom = i;
            scalegesturedetector.notifyListeners(null);
        }
        return true;
    }

    public final boolean onScaleBegin(ScaleGestureDetector scalegesturedetector)
    {
        if (scalegesturedetector.getFocusY() < scrollView.getY())
        {
            return false;
        }
        startingSpanY = Math.max(minYSpan, Math.abs(scalegesturedetector.getCurrentSpanY()));
        cellHeight = ((Integer)calendarProperties.getPropertyValue(10)).intValue();
        cellHeightBeforeScaleGesture = cellHeight;
        scrollViewHeight = scrollView.getHeight();
        float f;
        float f1;
        int i;
        if ((minCellHeight + gridlineHeight) * 24 + bottomPadding >= scrollViewHeight)
        {
            i = minCellHeight;
        } else
        {
            i = (int)Math.ceil((double)(scrollViewHeight - bottomPadding) / 24D);
        }
        adjustedMinimumCellHeight = i;
        f = (float)scrollViewHeight - scalegesturedetector.getFocusY();
        f1 = ((float)scrollView.getVerticalScrollPositionFromBottom() + f) - (float)bottomPadding;
        gestureCenterHourFromBottom = (int)(f1 / (float)(cellHeight + gridlineHeight));
        scrollRemainder = f1 - (float)(gestureCenterHourFromBottom * (cellHeight + gridlineHeight));
        LogUtils.d(PinchZoomController.TAG, "onScaleBegin: deltaY: %f, hourInPx: %d, focused hour from bottom: %d, scroll pos: %d", new Object[] {
            Float.valueOf(f), Integer.valueOf(cellHeight + gridlineHeight), Integer.valueOf(gestureCenterHourFromBottom), Integer.valueOf(scrollView.getVerticalScrollPositionFromBottom())
        });
        return true;
    }

    public final void onScaleEnd(ScaleGestureDetector scalegesturedetector)
    {
        LogUtils.d(PinchZoomController.TAG, "onScaleEnd", new Object[0]);
        scalegesturedetector = "preferences_grid_hour_height_p";
        if (orientation == 2)
        {
            scalegesturedetector = "preferences_grid_hour_height_l";
        }
        editor.putInt(scalegesturedetector, cellHeight);
        editor.apply();
        scrollMan_18_7_06__0A_.isScaleInProgress = false;
    }

    Properties()
    {
        this$0 = PinchZoomController.this;
        super();
    }
}
