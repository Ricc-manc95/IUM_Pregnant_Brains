// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.calendar;

import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import com.google.common.collect.Lists;

// Referenced classes of package com.google.android.calendar.newapi.segment.calendar:
//            CalendarFormatter, CalendarDialog

public final class .Lambda._cls0
{

    public static DialogFragment create(Context context, Iterable iterable, Fragment fragment, int i)
    {
        class .Lambda._cls0
            implements Function
        {

            private final CalendarFormatter arg$1;

            public final Object apply(Object obj)
            {
                CalendarFormatter calendarformatter = arg$1;
                obj = (CalendarListEntry)obj;
                return new AutoValue_UiCalendarUtils_UiCalendarListEntry(Utils.getCalendarNameToDisplay(((CalendarListEntry) (obj)).isPrimary(), ((CalendarListEntry) (obj)).getDisplayName(), ((CalendarListEntry) (obj)).getDescriptor().account.type, calendarformatter.defaultEventsTitle), ((CalendarListEntry) (obj)).getDescriptor().account.name, ((CalendarListEntry) (obj)).getColor().getValue(), ((CalendarListEntry) (obj)));
            }

            .Lambda._cls0(CalendarFormatter calendarformatter)
            {
                arg$1 = calendarformatter;
            }
        }

        .Lambda._cls0 _lcls0 = new .Lambda._cls0(new CalendarFormatter(context.getResources()));
        if (iterable == null)
        {
            throw new NullPointerException();
        }
        if (_lcls0 == null)
        {
            throw new NullPointerException();
        } else
        {
            return CalendarDialog.newInstance(context, Lists.newArrayList(new com.google.common.collect.og.newInstance(iterable, _lcls0)), fragment, i);
        }
    }

    public .Lambda._cls0()
    {
    }
}
