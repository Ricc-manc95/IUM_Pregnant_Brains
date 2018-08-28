// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.time;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.google.android.calendar.Utils;
import com.google.android.calendar.common.view.NinjaSwitch;
import com.google.android.calendar.newapi.model.RecurrenceHolder;
import com.google.android.calendar.newapi.model.edit.RecurrenceEditHolder;
import com.google.android.calendar.newapi.model.edit.TimeModification;
import com.google.android.calendar.newapi.segment.common.EditSegmentController;
import com.google.android.calendar.newapi.segment.common.SegmentController;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.time.CalendarUtils;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.viewedit.segment.edit.EditSegment;
import java.util.Calendar;
import java.util.TimeZone;

// Referenced classes of package com.google.android.calendar.newapi.segment.time:
//            DateTimePickerFactory, TimeUtils, ReminderTimeEditSegment

public class ReminderTimeEditSegmentController extends EditSegmentController
    implements com.android.datetimepicker.date.DatePickerCompat.OnDateSetListener, com.android.datetimepicker.time.TimePickerCompat.OnTimeSetListener, ReminderTimeEditSegment.Listener
{

    private boolean allDayToggled;
    private boolean instanceRestored;
    private DateTimePickerFactory pickerFactory;
    private Calendar startDateTime;

    public ReminderTimeEditSegmentController()
    {
        pickerFactory = new DateTimePickerFactory();
    }

    private final void updateEventTime(long l, boolean flag, boolean flag1)
    {
        Calendar calendar = (Calendar)startDateTime.clone();
        calendar.setTimeInMillis(l);
        Object obj = calendar;
        boolean flag2 = flag;
        if (flag)
        {
            obj = calendar;
            flag2 = flag;
            if (((RecurrenceHolder)(TimeModification)model).getRecurrence() != null)
            {
                obj = calendar;
                flag2 = flag;
                if (Utils.getTodayJulianDay(getContext()) == Utils.getJulianDay(getContext(), calendar.getTimeInMillis()))
                {
                    if (flag1)
                    {
                        obj = Utils.getTimeZone(getContext());
                        if (Clock.mockedTimestamp > 0L)
                        {
                            l = Clock.mockedTimestamp;
                        } else
                        {
                            l = System.currentTimeMillis();
                        }
                        obj = Calendar.getInstance(((TimeZone) (obj)));
                        ((Calendar) (obj)).setTimeInMillis(l);
                        obj = TimeUtils.getReminderDefaultStart(((Calendar) (obj)));
                        flag2 = false;
                    } else
                    {
                        calendar.add(5, 1);
                        obj = calendar;
                        flag2 = flag;
                    }
                }
            }
        }
        startDateTime.setTimeInMillis(((Calendar) (obj)).getTimeInMillis());
        if (flag2)
        {
            l = TimeUtils.toMidnight(startDateTime, false);
        } else
        {
            l = startDateTime.getTimeInMillis();
        }
        ((TimeModification)model).setTime$5154KMH9AO______0(l, flag2);
        updateUi();
    }

    private final void updateUi()
    {
        ReminderTimeEditSegment remindertimeeditsegment = (ReminderTimeEditSegment)super.view;
        Object obj = (Calendar)startDateTime.clone();
        boolean flag = ((TimeModification)model).isAllDay();
        Object obj1 = remindertimeeditsegment.allDaySwitch;
        obj1.stealth = true;
        ((NinjaSwitch) (obj1)).setChecked(flag);
        obj1.stealth = false;
        obj1 = remindertimeeditsegment.startTimeTextView;
        int i;
        if (!flag)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (obj1 != null)
        {
            long l;
            if (i != 0)
            {
                i = 0;
            } else
            {
                i = 8;
            }
            ((View) (obj1)).setVisibility(i);
        }
        if (!flag)
        {
            obj1 = DateFormat.getTimeFormat(remindertimeeditsegment.getContext());
            ((java.text.DateFormat) (obj1)).setTimeZone(((Calendar) (obj)).getTimeZone());
            obj1 = ((java.text.DateFormat) (obj1)).format(Long.valueOf(((Calendar) (obj)).getTimeInMillis()));
            remindertimeeditsegment.startTimeTextView.setText(((CharSequence) (obj1)));
            remindertimeeditsegment.startTimeTextView.setContentDescription(remindertimeeditsegment.getResources().getString(0x7f13006e, new Object[] {
                obj1
            }));
        }
        obj1 = remindertimeeditsegment.getContext();
        l = ((Calendar) (obj)).getTimeInMillis();
        if (((Context) (obj1)).getResources().getBoolean(0x7f0c0006))
        {
            i = 0x18016;
        } else
        {
            i = 0x10016;
        }
        obj = Utils.formatDateTime(((Context) (obj1)), l, i, ((Calendar) (obj)).getTimeZone().getID());
        remindertimeeditsegment.startDateTile.setPrimaryText(new CharSequence[] {
            obj
        });
        remindertimeeditsegment.startDateTile.setContentDescription(remindertimeeditsegment.getResources().getString(0x7f13006d, new Object[] {
            obj
        }));
    }

    public final View createView(LayoutInflater layoutinflater)
    {
        layoutinflater = (ReminderTimeEditSegment)layoutinflater.inflate(0x7f0500e2, null);
        layoutinflater.mListener = this;
        return layoutinflater;
    }

    public final void onAllDayToggled(boolean flag)
    {
        if (!allDayToggled && !flag)
        {
            long l = ((TimeModification)model).getStart(getContext());
            Object obj = Utils.getTimeZone(getContext());
            if (Time.getJulianDay(l, 0L) <= Utils.getTodayJulianDay(getContext()))
            {
                if (Clock.mockedTimestamp > 0L)
                {
                    l = Clock.mockedTimestamp;
                } else
                {
                    l = System.currentTimeMillis();
                }
                obj = Calendar.getInstance(((TimeZone) (obj)));
                ((Calendar) (obj)).setTimeInMillis(l);
            } else
            {
                obj = CalendarUtils.createTimeInNewTimeZoneRetainingFields(l, TimeZone.getTimeZone("UTC"), ((TimeZone) (obj)));
            }
            startDateTime = TimeUtils.getReminderDefaultStart(((Calendar) (obj)));
        }
        allDayToggled = true;
        updateEventTime(startDateTime.getTimeInMillis(), flag, false);
        notifyTimeChanged(false, false);
    }

    public final void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        if (bundle != null)
        {
            instanceRestored = true;
            startDateTime = TimeUtils.readCalendarFromBundle(bundle, "START_TIME");
            allDayToggled = bundle.getBoolean("ALL_DAY_TOGGLED");
        }
    }

    public final void onDateSet(int i, int j, int k)
    {
        Calendar calendar = (Calendar)startDateTime.clone();
        calendar.set(i, j, k);
        updateEventTime(calendar.getTimeInMillis(), ((TimeModification)model).isAllDay(), false);
        notifyTimeChanged(false, false);
    }

    protected final void onInitialize()
    {
        if (!instanceRestored)
        {
            Object obj = Utils.getTimeZone(getContext());
            long l = ((TimeModification)model).getStart(getContext());
            if (((TimeModification)model).isAllDay())
            {
                obj = TimeZone.getTimeZone("UTC");
            }
            obj = Calendar.getInstance(((TimeZone) (obj)));
            ((Calendar) (obj)).setTimeInMillis(l);
            startDateTime = ((Calendar) (obj));
        }
        updateUi();
    }

    public final void onSaveInstanceState(Bundle bundle)
    {
        TimeUtils.writeCalendarToBundle(bundle, "START_TIME", startDateTime);
        bundle.putBoolean("ALL_DAY_TOGGLED", allDayToggled);
        super.onSaveInstanceState(bundle);
    }

    public final void onStartDateClicked()
    {
        Calendar calendar;
        if (((TimeModification)model).isAllDay() && ((RecurrenceHolder)(TimeModification)model).getRecurrence() != null)
        {
            calendar = Calendar.getInstance(Utils.getTimeZone(getContext()));
            long l;
            if (Clock.mockedTimestamp > 0L)
            {
                l = Clock.mockedTimestamp;
            } else
            {
                l = System.currentTimeMillis();
            }
            calendar.setTimeInMillis(l);
            calendar.add(5, 1);
        } else
        {
            calendar = Calendar.getInstance(Utils.getTimeZone(getContext()));
            long l1;
            if (Clock.mockedTimestamp > 0L)
            {
                l1 = Clock.mockedTimestamp;
            } else
            {
                l1 = System.currentTimeMillis();
            }
            calendar.setTimeInMillis(l1);
        }
        pickerFactory.showDatePickerWithMinDate(this, startDateTime, calendar);
    }

    public final void onStartTimeClicked()
    {
        DateTimePickerFactory.showTimePicker(this, startDateTime);
    }

    public final void onTimeRelatedFieldChanged(boolean flag, boolean flag1)
    {
        if (flag1)
        {
            com.google.android.calendar.api.event.time.Recurrence recurrence = ((RecurrenceHolder)(TimeModification)model).getRecurrence();
            updateEventTime(startDateTime.getTimeInMillis(), ((TimeModification)model).isAllDay(), flag1);
            ((RecurrenceEditHolder)(TimeModification)model).setRecurrence(recurrence);
        }
    }

    public final void onTimeSet(int i, int j)
    {
        Calendar calendar = (Calendar)startDateTime.clone();
        calendar.set(11, i);
        calendar.set(12, j);
        updateEventTime(calendar.getTimeInMillis(), ((TimeModification)model).isAllDay(), false);
        notifyTimeChanged(false, false);
    }
}
