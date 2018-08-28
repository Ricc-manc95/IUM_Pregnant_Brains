// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.text.format.DateFormat;
import android.view.View;
import com.android.datetimepicker.date.MonthView;
import com.google.android.calendar.Utils;
import com.google.android.calendar.alternatecalendar.AlternateCalendarUtils;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.timely.settings.common.PreferencesConstants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelyColorMonthView, DataFactory, MonthData, TimelineItem, 
//            TimelineBirthday, TimelineHoliday, TimelineTask, TimelineTaskBundle, 
//            TimelineGroove

final class this._cls0 extends com.android.datetimepicker.date.er
{

    private final TimelyColorMonthView this$0;

    protected final void getItemBounds(int i, Rect rect)
    {
        int k1 = getInternalStartPadding();
        int l1 = interDayPadding / 2;
        int l = mRowHeight;
        int i1 = DAY_SELECTED_CIRCLE_SIZE;
        int j1 = mRowHeight / 2;
        int j = mRowHeight;
        int k = DAY_SELECTED_CIRCLE_SIZE * 2 + interDayPadding;
        int i2 = (i - 1) + findDayOffset();
        i = i2 / mNumDays;
        k1 = (k1 - l1) + getRtlCompatibleColumnIndex(i2 % mNumDays) * k;
        i = ((l + i1) - j1) + i * j;
        rect.set(k1, i, k + k1, j + i);
    }

    protected final CharSequence getItemDescription(int i)
    {
        Object obj;
        Resources resources;
        StringBuilder stringbuilder;
        Object obj1;
        obj = TimelyColorMonthView.this.time;
        int j = mMonth;
        int l = mYear;
        ((Time) (obj)).writeFieldsToImpl();
        ((Time) (obj)).impl.set(i, j, l);
        ((Time) (obj)).copyFieldsFromImpl();
        obj = TimelyColorMonthView.this.time;
        ((Time) (obj)).writeFieldsToImpl();
        ((Time) (obj)).impl.normalize(false);
        ((Time) (obj)).copyFieldsFromImpl();
        if (TimelyColorMonthView.this.time.allDay)
        {
            obj = TimelyColorMonthView.this.time;
            Time time = TimelyColorMonthView.this.time;
            TimelyColorMonthView.this.time.second = 0;
            time.minute = 0;
            obj.hour = 0;
        }
        obj = TimelyColorMonthView.this.time;
        ((Time) (obj)).writeFieldsToImpl();
        long l3 = ((Time) (obj)).impl.toMillis(true);
        obj = new StringBuilder();
        if (mHasToday && i == mToday)
        {
            ((StringBuilder) (obj)).append(getContext().getString(0x7f13048b));
            ((StringBuilder) (obj)).append(": ");
        }
        ((StringBuilder) (obj)).append(DateFormat.format("EEEE dd MMMM yyyy", l3));
        j = PreferencesConstants.getAlternateCalendarPref(getContext());
        if (j != 0)
        {
            i = Utils.getJulianDay(mYear, mMonth, i);
            ((StringBuilder) (obj)).append(", ");
            ((StringBuilder) (obj)).append(AlternateCalendarUtils.getAlternateFullDate(i, getResources(), j));
        }
        ((StringBuilder) (obj)).append(". ");
        i = android.text.format.Time.getJulianDay(l3, TimelyColorMonthView.this.time.gmtoff);
        obj1 = dataFactory.getData(i, false);
        resources = getResources();
        stringbuilder = new StringBuilder();
        if (obj1 == null || !((MonthData) (obj1)).isDataReady()) goto _L2; else goto _L1
_L1:
        int l1;
        int i2;
        int j2;
        int k2;
        int l2;
        obj1 = ((MonthData) (obj1)).getItems(i);
        if (obj1 != null && !((List) (obj1)).isEmpty())
        {
            obj1 = ((List) (obj1)).iterator();
            int k1 = 0;
            i = 0;
            int j1 = 0;
            int i1 = 0;
            int k = 0;
            do
            {
                l2 = k1;
                k2 = i;
                j2 = j1;
                i2 = i1;
                l1 = k;
                if (!((Iterator) (obj1)).hasNext())
                {
                    break;
                }
                TimelineItem timelineitem = (TimelineItem)((Iterator) (obj1)).next();
                if (timelineitem instanceof TimelineBirthday)
                {
                    j1++;
                } else
                if (timelineitem instanceof TimelineHoliday)
                {
                    i1++;
                } else
                if (timelineitem instanceof TimelineTask)
                {
                    i++;
                } else
                if (timelineitem instanceof TimelineTaskBundle)
                {
                    i = ((TimelineTaskBundle)timelineitem).timelineTaskList.size() + i;
                } else
                if (timelineitem instanceof TimelineGroove)
                {
                    k1++;
                } else
                {
                    k++;
                }
            } while (true);
        } else
        {
            l2 = 0;
            k2 = 0;
            j2 = 0;
            i2 = 0;
            l1 = 0;
        }
        if (j2 + i2 + k2 + l2 + l1 != 0) goto _L4; else goto _L3
_L3:
        stringbuilder.append(resources.getString(0x7f13034f));
_L2:
        ((StringBuilder) (obj)).append(stringbuilder.toString());
        ((StringBuilder) (obj)).append(getContext().getString(0x7f1300e7));
        ((StringBuilder) (obj)).append(". ");
        return ((StringBuilder) (obj)).toString();
_L4:
        if (j2 > 0)
        {
            stringbuilder.append(resources.getQuantityString(0x7f12002f, j2, new Object[] {
                Integer.valueOf(j2)
            }));
            stringbuilder.append(", ");
        }
        if (i2 > 0)
        {
            stringbuilder.append(resources.getQuantityString(0x7f120030, i2, new Object[] {
                Integer.valueOf(i2)
            }));
            stringbuilder.append(", ");
        }
        if (k2 > 0)
        {
            stringbuilder.append(resources.getQuantityString(0x7f120031, k2, new Object[] {
                Integer.valueOf(k2)
            }));
            stringbuilder.append(", ");
        }
        if (l2 > 0)
        {
            stringbuilder.append(resources.getQuantityString(0x7f12001e, l2, new Object[] {
                Integer.valueOf(l2)
            }));
            stringbuilder.append(", ");
        }
        if (l1 > 0)
        {
            stringbuilder.append(resources.getQuantityString(0x7f120014, l1, new Object[] {
                Integer.valueOf(l1)
            }));
            stringbuilder.append(", ");
        }
        if (true) goto _L2; else goto _L5
_L5:
    }

    public (View view)
    {
        this$0 = TimelyColorMonthView.this;
        super(TimelyColorMonthView.this, view);
    }
}
