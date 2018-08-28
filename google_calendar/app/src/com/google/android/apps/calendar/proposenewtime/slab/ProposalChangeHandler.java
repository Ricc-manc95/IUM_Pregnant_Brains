// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.proposenewtime.slab;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.format.DateFormat;
import com.android.datetimepicker.date.DatePickerSupportCompat;
import com.android.datetimepicker.time.TimePickerSupportCompat;
import com.google.android.apps.calendar.proposenewtime.state.AutoValue_TimeProposal;
import com.google.android.apps.calendar.proposenewtime.state.ProposeNewTimeState;
import com.google.android.apps.calendar.proposenewtime.state.StateHolder;
import com.google.android.apps.calendar.proposenewtime.state.TimeProposal;
import com.google.android.apps.calendar.util.preference.PreferenceUtils;
import java.util.Calendar;
import java.util.TimeZone;

public final class ProposalChangeHandler
    implements ProposeNewTimePagerAdapter.DateClickListener, com.google.android.apps.calendar.proposenewtime.slab.views.ProposeSlabItem.OnCommentChangeListener
{

    private final Context context;
    private final FragmentManager fragmentManager;
    public Runnable postUpdateAction;
    public final StateHolder stateHolder;
    public final TimeZone timeZone;

    public ProposalChangeHandler(Context context1, FragmentManager fragmentmanager, StateHolder stateholder, TimeZone timezone)
    {
        context = context1;
        fragmentManager = fragmentmanager;
        stateHolder = stateholder;
        timeZone = timezone;
    }

    private final void showDatePicker(Calendar calendar, com.android.datetimepicker.date.DatePickerCompat.OnDateSetListener ondatesetlistener)
    {
        ondatesetlistener = new DatePickerSupportCompat(ondatesetlistener);
        ondatesetlistener.setFirstDayOfWeek(PreferenceUtils.getFirstDayOfWeekAsCalendar(context));
        ondatesetlistener.setRtlEnabled(true);
        ondatesetlistener.initialize(calendar.get(1), calendar.get(2), calendar.get(5));
        calendar = fragmentManager;
        ondatesetlistener = ((DatePickerSupportCompat) (ondatesetlistener)).fragment;
        calendar.beginTransaction().add(ondatesetlistener, null).commitAllowingStateLoss();
    }

    private final void showTimePicker(Calendar calendar, com.android.datetimepicker.time.TimePickerCompat.OnTimeSetListener ontimesetlistener)
    {
        ontimesetlistener = new TimePickerSupportCompat(ontimesetlistener);
        ontimesetlistener.initialize(calendar.get(11), calendar.get(12), DateFormat.is24HourFormat(context));
        calendar = fragmentManager;
        ontimesetlistener = ((TimePickerSupportCompat) (ontimesetlistener)).fragment;
        calendar.beginTransaction().add(ontimesetlistener, null).commitAllowingStateLoss();
    }

    public final void onCommentChanged(String s)
    {
        Object obj = stateHolder.getState();
        StateHolder stateholder = stateHolder;
        com.google.android.apps.calendar.proposenewtime.state.ProposeNewTimeState.Builder builder = ((ProposeNewTimeState) (obj)).toBuilder();
        obj = ((ProposeNewTimeState) (obj)).getTimeProposal();
        stateholder.setState(builder.setTimeProposal(new AutoValue_TimeProposal(((TimeProposal) (obj)).getStartTimeMillis(), ((TimeProposal) (obj)).getEndTimeMillis(), s)).build());
    }

    public final void onEndDateClicked()
    {
        Object obj = stateHolder.getState().getTimeProposal();
        TimeZone timezone = timeZone;
        long l = ((TimeProposal) (obj)).getEndTimeMillis();
        obj = Calendar.getInstance(timezone);
        ((Calendar) (obj)).setTimeInMillis(l);
        class .Lambda._cls2
            implements com.android.datetimepicker.date.DatePickerCompat.OnDateSetListener
        {

            private final ProposalChangeHandler arg$1;

            public final void onDateSet(int i, int j, int k)
            {
                ProposalChangeHandler proposalchangehandler = arg$1;
                Object obj1 = proposalchangehandler.stateHolder.getState();
                StateHolder stateholder = proposalchangehandler.stateHolder;
                com.google.android.apps.calendar.proposenewtime.state.ProposeNewTimeState.Builder builder = ((ProposeNewTimeState) (obj1)).toBuilder();
                obj1 = ((ProposeNewTimeState) (obj1)).getTimeProposal();
                Object obj2 = proposalchangehandler.timeZone;
                long l1 = ((TimeProposal) (obj1)).getEndTimeMillis();
                obj2 = Calendar.getInstance(((TimeZone) (obj2)));
                ((Calendar) (obj2)).setTimeInMillis(l1);
                ((Calendar) (obj2)).set(i, j, k);
                stateholder.setState(builder.setTimeProposal(new AutoValue_TimeProposal(((TimeProposal) (obj1)).getStartTimeMillis(), ((Calendar) (obj2)).getTimeInMillis(), ((TimeProposal) (obj1)).getComment())).build());
                if (proposalchangehandler.postUpdateAction != null)
                {
                    proposalchangehandler.postUpdateAction.run();
                }
            }

            .Lambda._cls2()
            {
                arg$1 = ProposalChangeHandler.this;
            }
        }

        showDatePicker(((Calendar) (obj)), new .Lambda._cls2());
    }

    public final void onEndTimeClicked()
    {
        Object obj = stateHolder.getState().getTimeProposal();
        TimeZone timezone = timeZone;
        long l = ((TimeProposal) (obj)).getEndTimeMillis();
        obj = Calendar.getInstance(timezone);
        ((Calendar) (obj)).setTimeInMillis(l);
        class .Lambda._cls3
            implements com.android.datetimepicker.time.TimePickerCompat.OnTimeSetListener
        {

            private final ProposalChangeHandler arg$1;

            public final void onTimeSet(int i, int j)
            {
                ProposalChangeHandler proposalchangehandler = arg$1;
                Object obj1 = proposalchangehandler.stateHolder.getState();
                StateHolder stateholder = proposalchangehandler.stateHolder;
                com.google.android.apps.calendar.proposenewtime.state.ProposeNewTimeState.Builder builder = ((ProposeNewTimeState) (obj1)).toBuilder();
                obj1 = ((ProposeNewTimeState) (obj1)).getTimeProposal();
                Object obj2 = proposalchangehandler.timeZone;
                long l1 = ((TimeProposal) (obj1)).getEndTimeMillis();
                obj2 = Calendar.getInstance(((TimeZone) (obj2)));
                ((Calendar) (obj2)).setTimeInMillis(l1);
                ((Calendar) (obj2)).set(11, i);
                ((Calendar) (obj2)).set(12, j);
                stateholder.setState(builder.setTimeProposal(new AutoValue_TimeProposal(((TimeProposal) (obj1)).getStartTimeMillis(), ((Calendar) (obj2)).getTimeInMillis(), ((TimeProposal) (obj1)).getComment())).build());
                if (proposalchangehandler.postUpdateAction != null)
                {
                    proposalchangehandler.postUpdateAction.run();
                }
            }

            .Lambda._cls3()
            {
                arg$1 = ProposalChangeHandler.this;
            }
        }

        showTimePicker(((Calendar) (obj)), new .Lambda._cls3());
    }

    public final void onStartDateClicked()
    {
        Object obj = stateHolder.getState().getTimeProposal();
        TimeZone timezone = timeZone;
        long l = ((TimeProposal) (obj)).getStartTimeMillis();
        obj = Calendar.getInstance(timezone);
        ((Calendar) (obj)).setTimeInMillis(l);
        class .Lambda._cls0
            implements com.android.datetimepicker.date.DatePickerCompat.OnDateSetListener
        {

            private final ProposalChangeHandler arg$1;

            public final void onDateSet(int i, int j, int k)
            {
                ProposalChangeHandler proposalchangehandler = arg$1;
                Object obj1 = proposalchangehandler.stateHolder.getState();
                StateHolder stateholder = proposalchangehandler.stateHolder;
                com.google.android.apps.calendar.proposenewtime.state.ProposeNewTimeState.Builder builder = ((ProposeNewTimeState) (obj1)).toBuilder();
                obj1 = ((ProposeNewTimeState) (obj1)).getTimeProposal();
                Object obj2 = proposalchangehandler.timeZone;
                long l1 = ((TimeProposal) (obj1)).getEndTimeMillis() - ((TimeProposal) (obj1)).getStartTimeMillis();
                long l2 = ((TimeProposal) (obj1)).getStartTimeMillis();
                obj2 = Calendar.getInstance(((TimeZone) (obj2)));
                ((Calendar) (obj2)).setTimeInMillis(l2);
                ((Calendar) (obj2)).set(i, j, k);
                l2 = ((Calendar) (obj2)).getTimeInMillis();
                if (l1 >= 0L)
                {
                    l1 += ((Calendar) (obj2)).getTimeInMillis();
                } else
                {
                    l1 = ((TimeProposal) (obj1)).getEndTimeMillis();
                }
                stateholder.setState(builder.setTimeProposal(new AutoValue_TimeProposal(l2, l1, ((TimeProposal) (obj1)).getComment())).build());
                if (proposalchangehandler.postUpdateAction != null)
                {
                    proposalchangehandler.postUpdateAction.run();
                }
            }

            .Lambda._cls0()
            {
                arg$1 = ProposalChangeHandler.this;
            }
        }

        showDatePicker(((Calendar) (obj)), new .Lambda._cls0());
    }

    public final void onStartTimeClicked()
    {
        Object obj = stateHolder.getState().getTimeProposal();
        TimeZone timezone = timeZone;
        long l = ((TimeProposal) (obj)).getStartTimeMillis();
        obj = Calendar.getInstance(timezone);
        ((Calendar) (obj)).setTimeInMillis(l);
        class .Lambda._cls1
            implements com.android.datetimepicker.time.TimePickerCompat.OnTimeSetListener
        {

            private final ProposalChangeHandler arg$1;

            public final void onTimeSet(int i, int j)
            {
                ProposalChangeHandler proposalchangehandler = arg$1;
                ProposeNewTimeState proposenewtimestate = proposalchangehandler.stateHolder.getState();
                proposalchangehandler.stateHolder.setState(proposenewtimestate.toBuilder().setTimeProposal(proposenewtimestate.getTimeProposal().withNewStartTime(i, j, proposalchangehandler.timeZone)).build());
                if (proposalchangehandler.postUpdateAction != null)
                {
                    proposalchangehandler.postUpdateAction.run();
                }
            }

            .Lambda._cls1()
            {
                arg$1 = ProposalChangeHandler.this;
            }
        }

        showTimePicker(((Calendar) (obj)), new .Lambda._cls1());
    }
}
