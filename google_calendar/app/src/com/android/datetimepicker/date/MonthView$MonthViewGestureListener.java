// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.datetimepicker.date;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;

// Referenced classes of package com.android.datetimepicker.date:
//            MonthView

final class this._cls0 extends android.view.ener
{

    public final MonthView this$0;

    public final boolean onDown(MotionEvent motionevent)
    {
        float f = motionevent.getX();
        float f1 = motionevent.getY();
        selectorRect.setEmpty();
        int j = getInternalEndPadding();
        int k = getInternalStartPadding();
        int i = getMonthHeaderSize();
        if (f >= (float)k && f <= (float)(mWidth - j) && f1 >= (float)i)
        {
            float f2 = (float)(mWidth - k - j) / (float)mNumDays;
            float f3 = k;
            j = (int)((float)(int)((f - (float)k) / f2) * f2 + f3);
            i += (int)((f1 - (float)i) / (float)mRowHeight) * mRowHeight;
            selectorRect.set(j, i, (int)(f2 + (float)j), mRowHeight + i);
            if (mSelector != null)
            {
                mSelector.setBounds(selectorRect);
                updateSelectorState();
                if (mSelectorHasRipples)
                {
                    mSelector.setHotspot(f, f1);
                    return true;
                }
            }
        }
        return true;
    }

    public final void onLongPress(MotionEvent motionevent)
    {
        MonthView monthview = MonthView.this;
        if (!monthview.selectorRect.isEmpty())
        {
            monthview.dispatchClickOnDay(motionevent.getX(), motionevent.getY(), new this._cls0(monthview));
        }
    }

    public final boolean onScroll(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
    {
        if (selectorRect.isEmpty())
        {
            return false;
        }
        f = motionevent1.getX();
        f1 = motionevent1.getY();
        if (!selectorRect.contains((int)f, (int)f1))
        {
            selectorRect.setEmpty();
            updateSelectorState();
            return false;
        }
        if (mSelectorHasRipples)
        {
            mSelector.setHotspot(f, f1);
        }
        return true;
    }

    public final boolean onSingleTapUp(MotionEvent motionevent)
    {
        class .Lambda._cls0
            implements MonthView.OnDayClickDispatcher
        {

            private final MonthView.MonthViewGestureListener arg$1;

            public final void invoke(MonthAdapter.CalendarDay calendarday)
            {
                MonthView monthview;
                boolean flag1;
                flag1 = false;
                monthview = arg$1.this$0;
                if (monthview.mController == null) goto _L2; else goto _L1
_L1:
                java.util.Calendar calendar = monthview.mController.getMinDate();
                if (calendar == null || calendarday.compareTo(new MonthAdapter.CalendarDay(calendar)) >= 0) goto _L2; else goto _L3
_L3:
                boolean flag = true;
_L7:
                if (flag) goto _L5; else goto _L4
_L4:
                if (monthview.mController == null)
                {
                    break MISSING_BLOCK_LABEL_145;
                }
                java.util.Calendar calendar1 = monthview.mController.getMaxDate();
                if (calendar1 == null || calendarday.compareTo(new MonthAdapter.CalendarDay(calendar1)) <= 0)
                {
                    break MISSING_BLOCK_LABEL_145;
                }
                flag = true;
_L8:
                if (!flag) goto _L6; else goto _L5
_L5:
                flag1 = true;
_L6:
                if (!flag1)
                {
                    if (monthview.mOnDayClickListener != null)
                    {
                        monthview.mOnDayClickListener.onDayClick(monthview, calendarday);
                    }
                    monthview.mTouchHelper.sendEventForVirtualView(calendarday.day, 1);
                }
                return;
_L2:
                flag = false;
                  goto _L7
                flag = false;
                  goto _L8
            }

            .Lambda._cls0()
            {
                arg$1 = MonthView.MonthViewGestureListener.this;
            }
        }

        if (selectorRect.isEmpty())
        {
            return false;
        } else
        {
            return dispatchClickOnDay(motionevent.getX(), motionevent.getY(), new .Lambda._cls0());
        }
    }

    .Lambda._cls0()
    {
        this$0 = MonthView.this;
        super();
    }
}
