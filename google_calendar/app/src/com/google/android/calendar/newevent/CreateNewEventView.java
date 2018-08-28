// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newevent;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewParent;
import android.widget.TextView;
import com.google.android.calendar.DateTimeFormatHelper;
import com.google.android.calendar.Utils;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.timeline.chip.ChipScalingRatios;
import com.google.android.calendar.timely.settings.PreferencesUtils;
import com.google.android.calendar.timely.settings.common.OnPropertyChangedListener;
import com.google.android.calendar.timely.settings.data.CalendarProperties;
import com.google.android.calendar.viewedit.callbacks.OnLaunchEdit;

// Referenced classes of package com.google.android.calendar.newevent:
//            NewEventTimeChangedListenerHolder

public class CreateNewEventView extends TextView
    implements OnPropertyChangedListener
{
    final class CreateNewEventViewGestureListener extends android.view.GestureDetector.SimpleOnGestureListener
    {

        private final CreateNewEventView this$0;

        public final boolean onSingleTapUp(MotionEvent motionevent)
        {
            long l = startTime;
            class .Lambda._cls0
                implements Runnable
            {

                public static final Runnable $instance = new .Lambda._cls0();

                public final void run()
                {
                    CreateNewEventView.removeSelectedTime();
                }


                private .Lambda._cls0()
                {
                }
            }

            Context context;
            if (defaultDuration == -1L)
            {
                motionevent = null;
            } else
            {
                motionevent = Long.valueOf(startTime + defaultDuration * 60000L);
            }
            motionevent = Utils.getExtraEventBundle(l, motionevent, false);
            motionevent.putString("createEditSource", "grid");
            context = getContext();
            if (context instanceof OnLaunchEdit)
            {
                ((OnLaunchEdit)context).onLaunchInsertOrEdit(motionevent);
            }
            motionevent = getHandler();
            if (motionevent != null)
            {
                motionevent.postDelayed(.Lambda._cls0..instance, 1000L);
            }
            return true;
        }

        CreateNewEventViewGestureListener()
        {
            this$0 = CreateNewEventView.this;
            super();
        }
    }


    private static int defaultTextSize;
    private static boolean isTablet;
    private static final Time recycleTime = new Time();
    private static ChipScalingRatios scalingRatios;
    private PaintDrawable background;
    private final CalendarProperties calendarProperties;
    public long defaultDuration;
    private GestureDetector detector;
    private int gridHourHeight;
    private boolean isMultiDayView;
    public long startTime;

    public CreateNewEventView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        attributeset = context.getResources();
        CalendarProperties calendarproperties = CalendarProperties.instance;
        if (calendarproperties == null)
        {
            throw new NullPointerException(String.valueOf("CalendarProperties#initialize(...) must be called first"));
        }
        calendarProperties = (CalendarProperties)calendarproperties;
        detector = new GestureDetector(context, new CreateNewEventViewGestureListener());
        background = new PaintDrawable();
        background.setCornerRadius(attributeset.getDimensionPixelSize(0x7f0e008b));
        setBackground(background);
        setAlpha(0.8F);
        defaultDuration = ((Integer)calendarProperties.getPropertyValue(3)).intValue();
        int i = ((Integer)calendarProperties.getPropertyValue(4)).intValue();
        background.setColorFilter(i, android.graphics.PorterDuff.Mode.SRC_ATOP);
        if (scalingRatios == null)
        {
            scalingRatios = new ChipScalingRatios(attributeset);
            defaultTextSize = attributeset.getDimensionPixelSize(0x7f0e039f);
            isTablet = getContext().getResources().getBoolean(0x7f0c0016);
        }
        i = PreferencesUtils.getLastUsedView(context, isTablet);
        boolean flag;
        if (i == 0x7f100026 || i == 0x7f100050)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        isMultiDayView = flag;
        if (isMultiDayView)
        {
            setText(attributeset.getString(0x7f1304c1));
            setGravity(17);
            setPadding(0, 0, 0, 0);
            return;
        } else
        {
            setText(attributeset.getString(0x7f130156));
            setGravity(0x800003);
            setPadding(attributeset.getDimensionPixelOffset(0x7f0e0093), attributeset.getDimensionPixelOffset(0x7f0e0095), attributeset.getDimensionPixelOffset(0x7f0e0093), 0);
            return;
        }
    }

    public static int getSelectedHourInDay(Context context, int i)
    {
        long l = NewEventTimeChangedListenerHolder.INSTANCE.createNewEventTime;
        if (l != -1L)
        {
            recycleTime.timezone = Utils.getTimeZoneId(context);
            context = recycleTime;
            ((Time) (context)).impl.timezone = ((Time) (context)).timezone;
            ((Time) (context)).impl.set(l);
            ((Time) (context)).impl.toMillis(true);
            context.copyFieldsFromImpl();
            recycleTime.normalizeSafe();
            if (android.text.format.Time.getJulianDay(l, recycleTime.gmtoff) == i)
            {
                return recycleTime.hour;
            }
        }
        return -1;
    }

    public static void removeSelectedTime()
    {
        NewEventTimeChangedListenerHolder.INSTANCE.setCreateNewEventTime(-1L);
    }

    public static void setSelectedTime(Context context, int i, int j)
    {
        recycleTime.timezone = Utils.getTimeZoneId(context);
        recycleTime.setJulianDaySafe(i);
        recycleTime.allDay = false;
        recycleTime.hour = j;
        recycleTime.minute = 0;
        recycleTime.second = 0;
        NewEventTimeChangedListenerHolder.INSTANCE.setCreateNewEventTime(recycleTime.toMillisWithFallback());
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        onCalendarPropertyChanged(10, CalendarProperties.getIntProperty(10));
        calendarProperties.registerListener(4, this);
        calendarProperties.registerListener(3, this);
        calendarProperties.registerListener(10, this);
    }

    public final void onCalendarPropertyChanged(int i, Object obj)
    {
        i;
        JVM INSTR lookupswitch 3: default 36
    //                   3: 37
    //                   4: 66
    //                   10: 84;
           goto _L1 _L2 _L3 _L4
_L1:
        return;
_L2:
        defaultDuration = ((Integer)obj).intValue();
        if (getParent() != null)
        {
            getParent().requestLayout();
            return;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        background.setColorFilter(((Integer)obj).intValue(), android.graphics.PorterDuff.Mode.SRC_ATOP);
        return;
_L4:
        gridHourHeight = ((Integer)obj).intValue();
        float f = defaultTextSize;
        obj = scalingRatios;
        i = gridHourHeight;
        boolean flag;
        if (!isTablet && isMultiDayView)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        i = (int)(((ChipScalingRatios) (obj)).getScalingRatio(i, flag) * f);
        if (i != (int)getTextSize())
        {
            setTextSize(0, i);
            invalidate();
            return;
        }
        if (true) goto _L1; else goto _L5
_L5:
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        calendarProperties.unregisterListener(4, this);
        calendarProperties.unregisterListener(3, this);
        calendarProperties.unregisterListener(10, this);
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        detector.onTouchEvent(motionevent);
        return true;
    }

    public final void refreshFromModel()
    {
        defaultDuration = ((Integer)calendarProperties.getPropertyValue(3)).intValue();
        int i = ((Integer)calendarProperties.getPropertyValue(4)).intValue();
        background.setColorFilter(i, android.graphics.PorterDuff.Mode.SRC_ATOP);
        long l1 = startTime;
        Object obj;
        long l;
        if (defaultDuration == -1L)
        {
            l = 60L;
        } else
        {
            l = defaultDuration;
        }
        obj = DateTimeFormatHelper.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("DateTimeFormatHelper#initialize(...) must be called first"));
        } else
        {
            obj = ((DateTimeFormatHelper)obj).getDateRangeText(startTime, l1 + l * 60000L, 0x80011);
            setContentDescription(getResources().getString(0x7f130347, new Object[] {
                obj
            }));
            return;
        }
    }

    public void setStartTime(long l)
    {
        startTime = l;
    }

}
