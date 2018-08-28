// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.calendar.Utils;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.timely.NowLineDrawable;
import com.google.android.calendar.timely.settings.common.OnPropertyChangedListener;
import com.google.android.calendar.timely.settings.data.CalendarProperties;
import com.google.android.calendar.utils.rtl.RtlUtils;

// Referenced classes of package com.google.android.calendar.timely.gridviews:
//            GridHourDrawable, GridDrawable, GridDayView

public class GridViewFrame extends ViewGroup
    implements OnPropertyChangedListener
{

    private int dayViewWidths[];
    public int firstJulianDay;
    public final GridDrawable gridDrawable = new GridDrawable(getContext());
    public int gridEndPadding;
    public final GridHourDrawable gridHourDrawable;
    public int gridStartPadding;
    private int hoursWidth;
    public NowLineDrawable nowLineDrawable;
    private final boolean shouldIndicateToday;
    public int todayIndex;
    public boolean useNowLineOneDayPadding;

    public GridViewFrame(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        Resources resources = context.getResources();
        nowLineDrawable = new NowLineDrawable(android.graphics.Paint.Style.FILL, resources.getColor(0x7f0d01d7), resources.getDimensionPixelSize(0x7f0e01de), resources.getDimensionPixelOffset(0x7f0e01dd), 0);
        setBackgroundColor(resources.getColor(0x7f0d0313));
        attributeset = context.obtainStyledAttributes(attributeset, com.google.android.calendar.R.styleable.GridViewFrame);
        boolean flag;
        if (attributeset != null)
        {
            flag = attributeset.getBoolean(com.google.android.calendar.R.styleable.GridViewFrame_draw_hours, true);
            shouldIndicateToday = attributeset.getBoolean(com.google.android.calendar.R.styleable.GridViewFrame_indicate_today, true);
            attributeset.recycle();
        } else
        {
            shouldIndicateToday = true;
            flag = true;
        }
        if (flag)
        {
            gridHourDrawable = new GridHourDrawable(context, resources.getDimensionPixelSize(0x7f0e03e5), getResources().getDimensionPixelOffset(0x7f0e01dc));
            gridHourDrawable.setCallback(this);
            setHourViewWidth(resources.getDimensionPixelSize(0x7f0e0299));
        } else
        {
            gridHourDrawable = null;
        }
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return layoutparams instanceof android.view.ViewGroup.MarginLayoutParams;
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        return new android.view.ViewGroup.MarginLayoutParams(-2, -2);
    }

    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return new android.view.ViewGroup.MarginLayoutParams(getContext(), attributeset);
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return new android.view.ViewGroup.MarginLayoutParams(layoutparams);
    }

    public int getChildrenBeforeGridDayViews()
    {
        return 0;
    }

    final float getNowLinePosition()
    {
        Time time = new Time(Utils.getTimeZoneId(getContext()));
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        time.set(l);
        return getTimeLinePosition(time);
    }

    final float getTimeLinePosition(Time time)
    {
        float f = time.hour * 60 + time.minute;
        time = gridDrawable;
        int i = ((GridDrawable) (time)).intervalHeight;
        return (f * ((float)(((GridDrawable) (time)).gridLineHeight + i) * 24F)) / 1440F - nowLineDrawable.paint.getStrokeWidth() / 2.0F;
    }

    public void invalidateDrawable(Drawable drawable)
    {
        if (verifyDrawable(drawable))
        {
            invalidate();
        }
    }

    public void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        onCalendarPropertyChanged(10, CalendarProperties.getIntProperty(10));
        CalendarProperties calendarproperties = CalendarProperties.instance;
        if (calendarproperties == null)
        {
            throw new NullPointerException(String.valueOf("CalendarProperties#initialize(...) must be called first"));
        } else
        {
            ((CalendarProperties)calendarproperties).registerListener(10, this);
            return;
        }
    }

    public final void onCalendarPropertyChanged(int i, Object obj)
    {
        if (i == 10)
        {
            gridDrawable.intervalHeight = ((Integer)obj).intValue();
            if (gridHourDrawable != null)
            {
                GridHourDrawable gridhourdrawable = gridHourDrawable;
                gridhourdrawable.intervalHeight = ((Integer)obj).intValue();
                gridhourdrawable.invalidateSelf();
            }
            requestLayout();
        }
    }

    public void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        CalendarProperties calendarproperties = CalendarProperties.instance;
        if (calendarproperties == null)
        {
            throw new NullPointerException(String.valueOf("CalendarProperties#initialize(...) must be called first"));
        } else
        {
            ((CalendarProperties)calendarproperties).unregisterListener(10, this);
            return;
        }
    }

    protected void onDraw(Canvas canvas)
    {
        boolean flag = RtlUtils.isLayoutDirectionRtl(getContext());
        if (gridHourDrawable != null)
        {
            gridHourDrawable.draw(canvas);
        }
        gridDrawable.columnCount = getChildCount() - getChildrenBeforeGridDayViews();
        GridDrawable griddrawable = gridDrawable;
        int i;
        int j;
        int k;
        if (shouldIndicateToday && getChildCount() - getChildrenBeforeGridDayViews() > 1)
        {
            i = todayIndex;
        } else
        {
            i = -1;
        }
        griddrawable.highlightColumn = i;
        griddrawable = gridDrawable;
        if (flag)
        {
            i = gridEndPadding;
        } else
        {
            i = hoursWidth + gridStartPadding;
        }
        if (flag)
        {
            j = getWidth() - hoursWidth - gridStartPadding;
        } else
        {
            j = getWidth() - gridEndPadding;
        }
        k = getHeight();
        griddrawable.rect.set(i, 0, j, k);
        gridDrawable.draw(canvas);
        if (shouldIndicateToday && todayIndex >= 0 && todayIndex < getChildCount() - getChildrenBeforeGridDayViews())
        {
            Time time = new Time(Utils.getTimeZoneId(getContext()));
            int i1;
            long l1;
            if (Clock.mockedTimestamp > 0L)
            {
                l1 = Clock.mockedTimestamp;
            } else
            {
                l1 = System.currentTimeMillis();
            }
            time.set(l1);
            i = (int)getTimeLinePosition(time);
            i1 = ((GridDayView)getChildAt(todayIndex + getChildrenBeforeGridDayViews())).getWidth();
            j = ((GridDayView)getChildAt(todayIndex + getChildrenBeforeGridDayViews())).getLeft();
            if (useNowLineOneDayPadding)
            {
                int l = nowLineDrawable.oneDayStartPadding;
                if (flag)
                {
                    NowLineDrawable nowlinedrawable = nowLineDrawable;
                    i1 = getWidth();
                    nowlinedrawable.y = i;
                    nowlinedrawable.startX = i1 - l;
                    nowlinedrawable.endX = j;
                } else
                {
                    NowLineDrawable nowlinedrawable1 = nowLineDrawable;
                    nowlinedrawable1.y = i;
                    nowlinedrawable1.startX = l;
                    nowlinedrawable1.endX = j + i1;
                }
            } else
            if (flag)
            {
                NowLineDrawable nowlinedrawable2 = nowLineDrawable;
                nowlinedrawable2.y = i;
                nowlinedrawable2.startX = i1 + j;
                nowlinedrawable2.endX = j;
            } else
            {
                NowLineDrawable nowlinedrawable3 = nowLineDrawable;
                nowlinedrawable3.y = i;
                nowlinedrawable3.startX = j;
                nowlinedrawable3.endX = i1 + j;
            }
            nowLineDrawable.draw(canvas);
        }
    }

    public void onLayout(boolean flag, int i, int j, int k, int l)
    {
        flag = RtlUtils.isLayoutDirectionRtl(getContext());
        i = hoursWidth;
        j = 0;
        while (j < getChildCount() - getChildrenBeforeGridDayViews()) 
        {
            GridDayView griddayview = (GridDayView)getChildAt(getChildrenBeforeGridDayViews() + j);
            android.view.ViewGroup.MarginLayoutParams marginlayoutparams = (android.view.ViewGroup.MarginLayoutParams)griddayview.getLayoutParams();
            int i1;
            int j1;
            int k1;
            if (flag)
            {
                i1 = k - i - marginlayoutparams.getMarginStart();
                j1 = i1 - dayViewWidths[j];
            } else
            {
                j1 = i + marginlayoutparams.getMarginStart();
                i1 = dayViewWidths[j] + j1;
            }
            griddayview.layout(j1, 0, i1, l);
            i1 = dayViewWidths[j];
            j1 = marginlayoutparams.getMarginStart();
            k1 = marginlayoutparams.getMarginEnd();
            j++;
            i += i1 + j1 + k1;
        }
        if (gridHourDrawable != null)
        {
            if (flag)
            {
                i = getWidth() - hoursWidth;
            } else
            {
                i = 0;
            }
            gridHourDrawable.setBounds(i, 0, hoursWidth + i, getHeight());
        }
    }

    public void onMeasure(int i, int j)
    {
        float f;
        int k;
        int l;
        int j1;
        int k1;
        if (android.view.View.MeasureSpec.getMode(j) == 0)
        {
            k = 0;
        } else
        {
            k = android.view.View.MeasureSpec.getSize(j);
        }
        j1 = android.view.View.MeasureSpec.getSize(i);
        dayViewWidths = new int[getChildCount() - getChildrenBeforeGridDayViews()];
        k1 = hoursWidth;
        f = (float)(android.view.View.MeasureSpec.getSize(i) - k1) / (float)(getChildCount() - getChildrenBeforeGridDayViews());
        l = hoursWidth;
        for (i = 0; i < getChildCount() - getChildrenBeforeGridDayViews();)
        {
            GridDayView griddayview = (GridDayView)getChildAt(getChildrenBeforeGridDayViews() + i);
            android.view.ViewGroup.MarginLayoutParams marginlayoutparams = (android.view.ViewGroup.MarginLayoutParams)griddayview.getLayoutParams();
            int i1 = (int)((float)k1 + (float)(i + 1) * f);
            l = i1 - l - marginlayoutparams.getMarginStart() - marginlayoutparams.getMarginEnd();
            griddayview.measure(android.view.View.MeasureSpec.makeMeasureSpec(l, 0x40000000), j);
            dayViewWidths[i] = l;
            k = Math.max(k, griddayview.getMeasuredHeight());
            i++;
            l = i1;
        }

        setMeasuredDimension(j1, k);
    }

    public final void removeGridDayViews()
    {
        for (int i = getChildCount() - getChildrenBeforeGridDayViews() - 1; i >= 0; i--)
        {
            GridDayView griddayview = (GridDayView)getChildAt(getChildrenBeforeGridDayViews() + i);
            griddayview.clearChips();
            removeView(griddayview);
        }

    }

    public void setFirstJulianDay(int i)
    {
        firstJulianDay = i;
        todayIndex = Utils.getTodayJulianDay(getContext()) - firstJulianDay;
        invalidate();
    }

    public void setHourViewWidth(int i)
    {
        if (gridHourDrawable == null)
        {
            throw new NullPointerException(String.valueOf("View does not draw hours"));
        } else
        {
            setPaddingRelative(i, 0, 0, 0);
            hoursWidth = i;
            return;
        }
    }

    protected boolean verifyDrawable(Drawable drawable)
    {
        return drawable == gridHourDrawable || drawable == gridDrawable || drawable == nowLineDrawable || super.verifyDrawable(drawable);
    }
}
