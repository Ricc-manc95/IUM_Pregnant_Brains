// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.calendar;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.ListAdapter;
import com.google.android.calendar.common.dialog.SingleChoiceDialog;
import java.util.ArrayList;
import java.util.List;

public final class CalendarDialog extends SingleChoiceDialog
{

    public List calendarList;

    public CalendarDialog()
    {
    }

    static CalendarDialog newInstance(Context context, List list, Fragment fragment, int i)
    {
        CalendarDialog calendardialog = new CalendarDialog();
        calendardialog.calendarList = list;
        calendardialog.setTargetFragment(fragment, i);
        calendardialog.title = context.getString(0x7f1300ea);
        return calendardialog;
    }

    protected final ListAdapter createListAdapter(int i)
    {
        return new CalendarAdapter();
    }

    protected final Object getValue(int i)
    {
        return (UiCalendarUtils.UiCalendar)calendarList.get(i);
    }

    public final void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        if (bundle != null)
        {
            calendarList = bundle.getParcelableArrayList("instance_calendar_list");
        }
    }

    public final void onSaveInstanceState(Bundle bundle)
    {
        bundle.putParcelableArrayList("instance_calendar_list", new ArrayList(calendarList));
        super.onSaveInstanceState(bundle);
    }

    private class CalendarAdapter extends BaseAdapter
    {

        private final CalendarDialog this$0;

        public final int getCount()
        {
            return calendarList.size();
        }

        public final Object getItem(int i)
        {
            return (UiCalendarUtils.UiCalendar)calendarList.get(i);
        }

        public final long getItemId(int i)
        {
            return (long)i;
        }

        public final View getView(int i, View view, ViewGroup viewgroup)
        {
            Context context = viewgroup.getContext();
            viewgroup = (TextTileView)view;
            view = viewgroup;
            if (viewgroup == null)
            {
                view = new TextTileView(context);
                view.setHorizontalIncrement(0x7f110022).setIconDrawable(new ColorCircle(context.getResources(), 0x7f0e007c));
            }
            view.setLayoutDirection(3);
            viewgroup = (UiCalendarUtils.UiCalendar)getItem(i);
            view.setPrimaryText(new CharSequence[] {
                viewgroup.displayName()
            });
            view.setSecondaryText(new CharSequence[] {
                viewgroup.accountName()
            });
            ((ColorCircle)view.getIcon().getDrawable()).setColor(viewgroup.color());
            view.getIcon().invalidate();
            return view;
        }

        CalendarAdapter()
        {
            this$0 = CalendarDialog.this;
            super();
        }
    }

}
