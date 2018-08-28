// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.time;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.google.android.apps.calendar.util.string.StringUtils;
import com.google.android.calendar.Utils;
import com.google.android.calendar.api.event.EventModifications;
import com.google.android.calendar.newapi.model.TimeZoneHolder;
import com.google.android.calendar.newapi.model.edit.EventModificationsHolder;
import com.google.android.calendar.newapi.segment.common.EditSegmentController;
import com.google.android.calendar.newapi.segment.common.SegmentController;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.tiles.view.TileView;
import com.google.android.calendar.viewedit.segment.edit.EditSegment;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

// Referenced classes of package com.google.android.calendar.newapi.segment.time:
//            DateTimePickerFactory, GrooveTimeEditSegment

public class GrooveTimeEditSegmentController extends EditSegmentController
    implements com.android.datetimepicker.date.DatePickerCompat.OnDateSetListener, com.android.datetimepicker.time.TimePickerCompat.OnTimeSetListener, GrooveTimeEditSegment.Listener
{

    private DateTimePickerFactory pickerFactory;
    private Calendar startDateTime;

    public GrooveTimeEditSegmentController()
    {
        pickerFactory = new DateTimePickerFactory();
    }

    private final void updateEventTime()
    {
        long l = ((EventModificationsHolder)super.model).getEventModifications().getEndMillis();
        long l1 = ((EventModificationsHolder)super.model).getEventModifications().getStartMillis();
        Object obj = (Calendar)startDateTime.clone();
        long l2 = ((Calendar) (obj)).getTimeInMillis();
        ((EventModificationsHolder)super.model).getEventModifications().setStartMillis(l2);
        l2 = ((Calendar) (obj)).getTimeInMillis();
        ((EventModificationsHolder)super.model).getEventModifications().setEndMillis((l - l1) + l2);
        obj = (GrooveTimeEditSegment)super.view;
        Object obj1 = (Calendar)startDateTime.clone();
        Object obj2 = DateFormat.getTimeFormat(((GrooveTimeEditSegment) (obj)).getContext());
        ((java.text.DateFormat) (obj2)).setTimeZone(((Calendar) (obj1)).getTimeZone());
        obj2 = ((java.text.DateFormat) (obj2)).format(Long.valueOf(((Calendar) (obj1)).getTimeInMillis()));
        TextView textview = (TextView)((TileView) (((GrooveTimeEditSegment) (obj)).tile)).rightActionView;
        textview.setText(StringUtils.capitalizeStandalone(((String) (obj2)), Locale.getDefault()));
        textview.setContentDescription(((GrooveTimeEditSegment) (obj)).getResources().getString(0x7f13006e, new Object[] {
            obj2
        }));
        obj2 = ((GrooveTimeEditSegment) (obj)).getContext();
        l = ((Calendar) (obj1)).getTimeInMillis();
        int i;
        if (((Context) (obj2)).getResources().getBoolean(0x7f0c0006))
        {
            i = 0x18016;
        } else
        {
            i = 0x10016;
        }
        obj1 = Utils.formatDateTime(((Context) (obj2)), l, i, ((Calendar) (obj1)).getTimeZone().getID());
        ((GrooveTimeEditSegment) (obj)).tile.setPrimaryText(new CharSequence[] {
            StringUtils.capitalizeStandalone(((String) (obj1)), Locale.getDefault())
        });
        ((GrooveTimeEditSegment) (obj)).tile.setContentDescription(((GrooveTimeEditSegment) (obj)).getResources().getString(0x7f13006d, new Object[] {
            obj1
        }));
        notifyTimeChanged(false, false);
    }

    public final View createView(LayoutInflater layoutinflater)
    {
        layoutinflater = (GrooveTimeEditSegment)layoutinflater.inflate(0x7f0500d7, null);
        layoutinflater.mListener = this;
        return layoutinflater;
    }

    public final void onDateClicked()
    {
        pickerFactory.showDatePickerWithMinDate(this, startDateTime, null);
    }

    public final void onDateSet(int i, int j, int k)
    {
        startDateTime.set(i, j, k);
        updateEventTime();
    }

    protected final void onInitialize()
    {
        Object obj1 = (TimeZoneHolder)(EventModificationsHolder)super.model;
        Object obj;
        Object obj2;
        TextView textview;
        int i;
        long l;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        startDateTime = Calendar.getInstance(TimeZone.getTimeZone(((TimeZoneHolder) (obj1)).getDefaultTimeZoneId(((Context) (obj)))));
        startDateTime.setTimeInMillis(((EventModificationsHolder)super.model).getEventModifications().getStartMillis());
        obj = (GrooveTimeEditSegment)super.view;
        obj1 = (Calendar)startDateTime.clone();
        obj2 = DateFormat.getTimeFormat(((GrooveTimeEditSegment) (obj)).getContext());
        ((java.text.DateFormat) (obj2)).setTimeZone(((Calendar) (obj1)).getTimeZone());
        obj2 = ((java.text.DateFormat) (obj2)).format(Long.valueOf(((Calendar) (obj1)).getTimeInMillis()));
        textview = (TextView)((TileView) (((GrooveTimeEditSegment) (obj)).tile)).rightActionView;
        textview.setText(StringUtils.capitalizeStandalone(((String) (obj2)), Locale.getDefault()));
        textview.setContentDescription(((GrooveTimeEditSegment) (obj)).getResources().getString(0x7f13006e, new Object[] {
            obj2
        }));
        obj2 = ((GrooveTimeEditSegment) (obj)).getContext();
        l = ((Calendar) (obj1)).getTimeInMillis();
        if (((Context) (obj2)).getResources().getBoolean(0x7f0c0006))
        {
            i = 0x18016;
        } else
        {
            i = 0x10016;
        }
        obj1 = Utils.formatDateTime(((Context) (obj2)), l, i, ((Calendar) (obj1)).getTimeZone().getID());
        ((GrooveTimeEditSegment) (obj)).tile.setPrimaryText(new CharSequence[] {
            StringUtils.capitalizeStandalone(((String) (obj1)), Locale.getDefault())
        });
        ((GrooveTimeEditSegment) (obj)).tile.setContentDescription(((GrooveTimeEditSegment) (obj)).getResources().getString(0x7f13006d, new Object[] {
            obj1
        }));
    }

    public final void onTimeClicked()
    {
        DateTimePickerFactory.showTimePicker(this, startDateTime);
    }

    public final void onTimeSet(int i, int j)
    {
        startDateTime.set(11, i);
        startDateTime.set(12, j);
        updateEventTime();
    }
}
