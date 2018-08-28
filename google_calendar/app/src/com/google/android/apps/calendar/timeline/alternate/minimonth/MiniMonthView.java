// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.minimonth;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.util.YearMonth;
import com.google.android.calendar.alternatecalendar.AlternateCalendarHelper;
import java.util.Calendar;
import java.util.List;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.minimonth:
//            MiniMonthGeometry, MiniMonthDrawable

final class MiniMonthView extends View
{

    public final AlternateCalendarHelper alternateCalendarHelper;
    public final Calendar calendar = Calendar.getInstance();
    public final MiniMonthDrawable drawable;
    public int firstDayIndex;
    public int firstJulianDay;
    public final MiniMonthGeometry geometry;
    public List monthDayData;
    public com.google.android.apps.calendar.timeline.alternate.minimonth.api.MiniMonthController.OnDayClickedListener onDayClickedListener;
    public int selectedJulianDay;
    public final TimeUtils timeUtils;
    public int todayJulianDay;
    public int totalDays;
    private final ExploreByTouchHelper touchHelper = new MiniMonthExploreByTouchHelper(this);
    public YearMonth yearMonth;

    MiniMonthView(Context context, MiniMonthDrawable minimonthdrawable, MiniMonthGeometry minimonthgeometry, TimeUtils timeutils, AlternateCalendarHelper alternatecalendarhelper)
    {
        super(context);
        drawable = minimonthdrawable;
        geometry = minimonthgeometry;
        timeUtils = timeutils;
        alternateCalendarHelper = alternatecalendarhelper;
        setBackground(minimonthdrawable);
        class .Lambda._cls0
            implements android.view.View.OnTouchListener
        {

            private final GestureDetector arg$1;

            public final boolean onTouch(View view, MotionEvent motionevent)
            {
                return arg$1.onTouchEvent(motionevent);
            }

            .Lambda._cls0(GestureDetector gesturedetector)
            {
                arg$1 = gesturedetector;
            }
        }

        setOnTouchListener(new .Lambda._cls0(new GestureDetector(context, new _cls1())));
        ViewCompat.setAccessibilityDelegate(this, touchHelper);
    }

    public final boolean dispatchHoverEvent(MotionEvent motionevent)
    {
        return touchHelper.dispatchHoverEvent(motionevent) || super.dispatchHoverEvent(motionevent);
    }

    protected final void onMeasure(int i, int j)
    {
        i = android.view.View.MeasureSpec.getSize(i);
        MiniMonthGeometry minimonthgeometry = geometry;
        j = (int)Math.ceil((float)(firstDayIndex + totalDays) / 7F);
        float f = minimonthgeometry.weekdayHeaderHeightPx;
        float f1 = j;
        float f2 = minimonthgeometry.rowSizePx;
        setMeasuredDimension(i, Math.round(minimonthgeometry.bottomPaddingPx + (f1 * f2 + f)));
    }

    private class _cls1 extends android.view.GestureDetector.SimpleOnGestureListener
    {

        private final MiniMonthView this$0;

        public final boolean onDown(MotionEvent motionevent)
        {
            return true;
        }

        public final boolean onSingleTapUp(MotionEvent motionevent)
        {
            Object obj;
            com.google.android.apps.calendar.timeline.alternate.minimonth.api.MiniMonthController.OnDayClickedListener ondayclickedlistener;
            int j;
            j = -1;
            obj = null;
            ondayclickedlistener = onDayClickedListener;
            if (ondayclickedlistener == null || motionevent == null) goto _L2; else goto _L1
_L1:
            float f;
            MiniMonthView minimonthview;
            int i;
            minimonthview = MiniMonthView.this;
            float f1 = motionevent.getX();
            f = motionevent.getY();
            motionevent = minimonthview.geometry;
            f1 = motionevent.toLeft(f1);
            if (f1 < ((MiniMonthGeometry) (motionevent)).sidePaddingPx + ((MiniMonthGeometry) (motionevent)).weekNumPaddingPx || (float)((MiniMonthGeometry) (motionevent)).bounds.width() - f1 < ((MiniMonthGeometry) (motionevent)).sidePaddingPx)
            {
                i = -1;
            } else
            {
                float f2 = ((MiniMonthGeometry) (motionevent)).sidePaddingPx;
                float f3 = ((MiniMonthGeometry) (motionevent)).weekNumPaddingPx;
                float f4 = ((MiniMonthGeometry) (motionevent)).columnWidthPx;
                i = (int)((f1 - f2 - f3) / (((MiniMonthGeometry) (motionevent)).columnSpacingPx + f4));
            }
            motionevent = obj;
            if (i < 0) goto _L4; else goto _L3
_L3:
            if (i < 7) goto _L6; else goto _L5
_L5:
            motionevent = obj;
_L4:
            if (motionevent != null)
            {
                ondayclickedlistener.onDayClicked(motionevent.intValue());
                return true;
            }
            break; /* Loop/switch isn't completed */
_L6:
            motionevent = minimonthview.geometry;
            if (f >= ((MiniMonthGeometry) (motionevent)).weekdayHeaderHeightPx)
            {
                j = (int)((f - ((MiniMonthGeometry) (motionevent)).weekdayHeaderHeightPx) / ((MiniMonthGeometry) (motionevent)).rowSizePx);
            }
            motionevent = obj;
            if (j >= 0)
            {
                i = (j * 7 + i) - minimonthview.firstDayIndex;
                motionevent = obj;
                if (i >= 0)
                {
                    motionevent = obj;
                    if (i < minimonthview.totalDays)
                    {
                        motionevent = Integer.valueOf(minimonthview.firstJulianDay + i);
                    }
                }
            }
            if (true) goto _L4; else goto _L2
_L2:
            return false;
        }

        _cls1()
        {
            this$0 = MiniMonthView.this;
            super();
        }
    }


    private class MiniMonthExploreByTouchHelper extends ExploreByTouchHelper
    {

        private final SimpleDateFormat a11yFullDateFormat = new SimpleDateFormat("EEEE dd MMMM yyyy", Locale.getDefault());
        private final MiniMonthView this$0;

        protected final int getVirtualViewAt(float f, float f1)
        {
            Object obj;
            Object obj1;
            MiniMonthView minimonthview;
            int i;
            obj1 = null;
            byte byte0 = -1;
            minimonthview = MiniMonthView.this;
            obj = minimonthview.geometry;
            f = ((MiniMonthGeometry) (obj)).toLeft(f);
            if (f < ((MiniMonthGeometry) (obj)).sidePaddingPx + ((MiniMonthGeometry) (obj)).weekNumPaddingPx || (float)((MiniMonthGeometry) (obj)).bounds.width() - f < ((MiniMonthGeometry) (obj)).sidePaddingPx)
            {
                i = -1;
            } else
            {
                float f2 = ((MiniMonthGeometry) (obj)).sidePaddingPx;
                float f3 = ((MiniMonthGeometry) (obj)).weekNumPaddingPx;
                float f4 = ((MiniMonthGeometry) (obj)).columnWidthPx;
                i = (int)((f - f2 - f3) / (((MiniMonthGeometry) (obj)).columnSpacingPx + f4));
            }
            obj = obj1;
            if (i < 0) goto _L2; else goto _L1
_L1:
            if (i < 7) goto _L4; else goto _L3
_L3:
            obj = obj1;
_L2:
            i = byte0;
            if (obj != null)
            {
                i = ((Integer) (obj)).intValue();
            }
            return i;
_L4:
            obj = minimonthview.geometry;
            int j;
            if (f1 < ((MiniMonthGeometry) (obj)).weekdayHeaderHeightPx)
            {
                j = -1;
            } else
            {
                j = (int)((f1 - ((MiniMonthGeometry) (obj)).weekdayHeaderHeightPx) / ((MiniMonthGeometry) (obj)).rowSizePx);
            }
            obj = obj1;
            if (j >= 0)
            {
                i = (j * 7 + i) - minimonthview.firstDayIndex;
                obj = obj1;
                if (i >= 0)
                {
                    obj = obj1;
                    if (i < minimonthview.totalDays)
                    {
                        obj = Integer.valueOf(minimonthview.firstJulianDay + i);
                    }
                }
            }
            if (true) goto _L2; else goto _L5
_L5:
        }

        protected final void getVisibleVirtualViews(List list)
        {
            int i = firstJulianDay;
            for (int j = 0; j < totalDays;)
            {
                list.add(Integer.valueOf(i));
                j++;
                i++;
            }

        }

        protected final boolean onPerformActionForVirtualView(int i, int j, Bundle bundle)
        {
            if (j == 16)
            {
                onDayClickedListener.onDayClicked(i);
            }
            return false;
        }

        protected final void onPopulateNodeForVirtualView(int i, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
        {
            if (i == selectedJulianDay)
            {
                accessibilitynodeinfocompat.mInfo.setSelected(true);
            }
            Object obj = Calendar.getInstance((TimeZone)timeUtils.timeZone.get());
            Object obj1 = timeUtils;
            long l = TimeUtils.utcJulianDayToMs(i);
            ((Calendar) (obj)).setTimeInMillis(l - (long)((TimeZone)((TimeUtils) (obj1)).timeZone.get()).getOffset(l));
            obj1 = a11yFullDateFormat.format(((Calendar) (obj)).getTime());
            obj = obj1;
            if (alternateCalendarHelper.isEnabled())
            {
                obj = String.valueOf(obj1);
                obj1 = alternateCalendarHelper.getMonthAndDay(i);
                obj = (new StringBuilder(String.valueOf(obj).length() + 2 + String.valueOf(obj1).length())).append(((String) (obj))).append(", ").append(((String) (obj1))).toString();
            }
            obj1 = obj;
            if (todayJulianDay == i)
            {
                obj1 = getContext().getString(0x7f13048d, new Object[] {
                    obj
                });
            }
            accessibilitynodeinfocompat.mInfo.setContentDescription(((CharSequence) (obj1)));
            i = (i - firstJulianDay) + firstDayIndex;
            int j = i / 7;
            geometry.updateDrawDimens(drawable.getBounds());
            obj = geometry;
            float f = ((MiniMonthGeometry) (obj)).getLeft(i % 7);
            float f1 = (((MiniMonthGeometry) (obj)).columnWidthPx + ((MiniMonthGeometry) (obj)).columnSpacingPx) / 2.0F;
            float f2 = ((MiniMonthGeometry) (obj)).weekdayHeaderHeightPx;
            float f3 = ((MiniMonthGeometry) (obj)).rowSizePx;
            f2 = (float)j * f3 + f2 + ((MiniMonthGeometry) (obj)).rowSizePx / 2.0F;
            obj = new Rect(Math.round(f - f1), Math.round(f2 - ((MiniMonthGeometry) (obj)).rowSizePx / 2.0F), Math.round(f + f1), Math.round(f2 + ((MiniMonthGeometry) (obj)).rowSizePx / 2.0F));
            accessibilitynodeinfocompat.mInfo.setBoundsInParent(((Rect) (obj)));
            accessibilitynodeinfocompat.mInfo.addAction(16);
        }

        MiniMonthExploreByTouchHelper(View view)
        {
            this$0 = MiniMonthView.this;
            super(view);
            a11yFullDateFormat.setTimeZone((TimeZone)timeUtils.timeZone.get());
        }
    }

}
