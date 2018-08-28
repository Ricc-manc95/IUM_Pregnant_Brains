// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.ical;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import com.google.android.calendar.newapi.model.CalendarList;
import com.google.android.calendar.newapi.segment.calendar.CalendarDialog;

// Referenced classes of package com.google.android.calendar.ical:
//            ICalEventListFragment

final class this._cls0
    implements ImportAllView
{

    private final ICalEventListFragment this$0;

    public final void setEnabled(boolean flag)
    {
        Object obj = ICalEventListFragment.this;
        obj.importAllEnabled = flag;
        boolean flag1;
        if (((Fragment) (obj)).mHost != null && ((Fragment) (obj)).mAdded)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1)
        {
            if (((Fragment) (obj)).mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)((Fragment) (obj)).mHost.mActivity;
            }
            ((FragmentActivity) (obj)).invalidateOptionsMenu();
        }
    }

    public final void showCalendarChooser(CalendarList calendarlist)
    {
        new com.google.android.calendar.newapi.segment.calendar.PickerFactory();
        com.google.android.calendar.newapi.segment.calendar.PickerFactory.create(getContext(), calendarlist.calendars, ICalEventListFragment.this, -1).show(mFragmentManager, CalendarDialog.TAG);
    }

    darDialog()
    {
        this$0 = ICalEventListFragment.this;
        super();
    }
}
