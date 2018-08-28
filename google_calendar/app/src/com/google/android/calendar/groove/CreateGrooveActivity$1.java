// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentController;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.habit.HabitFactory;
import com.google.android.calendar.api.habit.HabitModifications;
import com.google.common.util.concurrent.FutureCallback;

// Referenced classes of package com.google.android.calendar.groove:
//            GrooveUtils, CreateGrooveActivity, GrooveCategorySelectionFragment

final class this._cls0
    implements FutureCallback
{

    private final CreateGrooveActivity this$0;

    public final void onFailure(Throwable throwable)
    {
        throwable = CreateGrooveActivity.this;
        CalendarListEntry calendarlistentry = GrooveUtils.getGrooveSupportedCalendar(throwable, null);
        if (calendarlistentry == null)
        {
            Toast.makeText(throwable, 0x7f1301ac, 0).show();
            throwable.finish();
            return;
        } else
        {
            throwable.habitModifications = CalendarApi.HabitFactory.newHabit(calendarlistentry.getDescriptor());
            throwable.habitContractModifications = ((CreateGrooveActivity) (throwable)).habitModifications.getContractModifications();
            throwable.categoryFragment = new GrooveCategorySelectionFragment();
            ((FragmentActivity) (throwable)).mFragments.mHost.mFragmentManager.beginTransaction().add(0x7f10013c, ((CreateGrooveActivity) (throwable)).categoryFragment, GrooveCategorySelectionFragment.TAG).addToBackStack(GrooveCategorySelectionFragment.TAG).commit();
            return;
        }
    }

    public final void onSuccess(Object obj)
    {
        CalendarListEntry acalendarlistentry[] = (CalendarListEntry[])obj;
        obj = CreateGrooveActivity.this;
        CalendarListEntry calendarlistentry = GrooveUtils.getGrooveSupportedCalendar(((android.content.Context) (obj)), acalendarlistentry);
        if (calendarlistentry == null)
        {
            Toast.makeText(((android.content.Context) (obj)), 0x7f1301ac, 0).show();
            ((CreateGrooveActivity) (obj)).finish();
            return;
        } else
        {
            obj.habitModifications = CalendarApi.HabitFactory.newHabit(calendarlistentry.getDescriptor());
            obj.habitContractModifications = ((CreateGrooveActivity) (obj)).habitModifications.getContractModifications();
            obj.categoryFragment = new GrooveCategorySelectionFragment();
            ((FragmentActivity) (obj)).mFragments.mHost.mFragmentManager.beginTransaction().add(0x7f10013c, ((CreateGrooveActivity) (obj)).categoryFragment, GrooveCategorySelectionFragment.TAG).addToBackStack(GrooveCategorySelectionFragment.TAG).commit();
            return;
        }
    }

    Fragment()
    {
        this$0 = CreateGrooveActivity.this;
        super();
    }
}
