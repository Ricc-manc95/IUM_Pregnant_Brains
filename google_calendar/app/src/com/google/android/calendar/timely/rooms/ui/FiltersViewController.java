// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.ui;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentController;
import android.support.v4.app.FragmentHostCallback;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import com.google.android.calendar.common.dialog.SingleChoiceDialogListener;
import com.google.android.calendar.common.dialog.SingleChoiceTextDialog;
import com.google.android.calendar.timely.rooms.data.AutoValue_RoomBookingFilterParams;
import com.google.android.calendar.timely.rooms.data.RoomBookingFilterParams;
import com.google.android.calendar.utils.fragment.FragmentUtils;
import java.util.ArrayList;
import java.util.Arrays;

public final class FiltersViewController
    implements android.view.View.OnClickListener, android.widget.CompoundButton.OnCheckedChangeListener
{
    public static class RecurrenceDialogListener extends Fragment
        implements SingleChoiceDialogListener
    {

        public FiltersViewController outer;

        public final void onDialogItemChosen(Object obj, int i)
        {
            obj = (Integer)obj;
            if (outer != null)
            {
                FiltersViewController filtersviewcontroller = outer;
                i = ((Integer) (obj)).intValue();
                filtersviewcontroller.params = new AutoValue_RoomBookingFilterParams(filtersviewcontroller.params.showOnlyAvailable(), Integer.valueOf(i));
                filtersviewcontroller.updateUi();
            }
        }

        public RecurrenceDialogListener()
        {
        }
    }


    private final FragmentActivity activity;
    public boolean allowChangeRecurrenceOption;
    public final ViewGroup container;
    public final View contentView;
    public final Switch onlyAvailableSwitch;
    public RoomBookingFilterParams params;
    public final RecurrenceDialogListener recurrenceOptionListener;
    private final View showRoomsForTile;

    public FiltersViewController(FragmentActivity fragmentactivity, ViewGroup viewgroup)
    {
        activity = fragmentactivity;
        container = viewgroup;
        contentView = LayoutInflater.from(activity).inflate(0x7f050144, container, false);
        onlyAvailableSwitch = (Switch)contentView.findViewById(0x7f100344);
        showRoomsForTile = contentView.findViewById(0x7f100345);
        class .Lambda._cls0
            implements android.view.View.OnClickListener
        {

            private final FiltersViewController arg$1;

            public final void onClick(View view)
            {
                arg$1.onlyAvailableSwitch.toggle();
            }

            .Lambda._cls0()
            {
                arg$1 = FiltersViewController.this;
            }
        }

        contentView.findViewById(0x7f100343).setOnClickListener(new .Lambda._cls0());
        onlyAvailableSwitch.setOnCheckedChangeListener(this);
        showRoomsForTile.setOnClickListener(this);
        recurrenceOptionListener = (RecurrenceDialogListener)FragmentUtils.attachFragment(activity, activity.mFragments.mHost.mFragmentManager, com/google/android/calendar/timely/rooms/ui/FiltersViewController$RecurrenceDialogListener, null, null);
    }

    public final void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
    {
        params = new AutoValue_RoomBookingFilterParams(flag, params.getRecurrenceOption());
        updateUi();
    }

    public final void onClick(View view)
    {
        view = new ArrayList(Arrays.asList(new String[] {
            activity.getString(0x7f13040c), activity.getString(0x7f13040d)
        }));
        ArrayList arraylist = new ArrayList(Arrays.asList(new Integer[] {
            Integer.valueOf(1), Integer.valueOf(2)
        }));
        SingleChoiceTextDialog.newIntegerBasedInstance(view, arraylist, arraylist.indexOf(params.getRecurrenceOption()), recurrenceOptionListener, -1).show(activity.mFragments.mHost.mFragmentManager, "SingleChoiceTextDialog");
    }

    public final void updateUi()
    {
        onlyAvailableSwitch.setChecked(params.showOnlyAvailable());
        View view = showRoomsForTile;
        boolean flag = allowChangeRecurrenceOption;
        if (view != null)
        {
            int i;
            if (flag)
            {
                i = 0;
            } else
            {
                i = 8;
            }
            view.setVisibility(i);
        }
        if (!flag)
        {
            return;
        }
        int j;
        if (params.getRecurrenceOption().intValue() == 1)
        {
            j = 0x7f13040c;
        } else
        {
            j = 0x7f13040d;
        }
        ((TextView)showRoomsForTile.findViewById(0x7f100346)).setText(j);
    }
}
