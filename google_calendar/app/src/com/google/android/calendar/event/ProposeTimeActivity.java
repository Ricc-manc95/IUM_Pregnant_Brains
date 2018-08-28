// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentController;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import com.android.datetimepicker.SupportDialogFragmentListener;
import com.android.datetimepicker.SupportDialogFragmentWithListener;
import com.android.datetimepicker.date.DatePickerSupportCompat;
import com.android.datetimepicker.date.DatePickerSupportDialog;
import com.android.datetimepicker.time.TimePickerBaseDialog;
import com.android.datetimepicker.time.TimePickerSupportCompat;
import com.android.datetimepicker.time.TimePickerSupportDialog;
import com.google.android.apps.calendar.primes.api.PerformanceMetricCollector;
import com.google.android.apps.calendar.primes.api.PerformanceMetricCollectorHolder;
import com.google.android.apps.calendar.util.preference.PreferenceUtils;
import com.google.android.calendar.Utils;
import com.google.android.calendar.common.activity.CalendarSupportActivity;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.timely.FindTimeUtil;
import com.google.android.calendar.timely.ProposeTimeAddNoteFragment;
import java.util.Calendar;

// Referenced classes of package com.google.android.calendar.event:
//            ProposedTimeConfirmationDialog

public class ProposeTimeActivity extends CalendarSupportActivity
    implements SupportDialogFragmentListener, com.android.datetimepicker.date.DatePickerCompat.OnDateSetListener, com.android.datetimepicker.time.TimePickerCompat.OnTimeSetListener, ProposedTimeConfirmationDialog.OnProposedTimeConfirmListener, com.google.android.calendar.timely.ProposeTimeAddNoteFragment.Listener
{

    private boolean dateOrTimeSet;
    private int eventColor;
    private long eventEndMillis;
    private long eventStartMillis;
    private String proposedNote;
    private Time proposedStartTime;

    public ProposeTimeActivity()
    {
        dateOrTimeSet = false;
    }

    private final void launchDatePicker()
    {
        DatePickerSupportCompat datepickersupportcompat = new DatePickerSupportCompat(this);
        datepickersupportcompat.setRtlEnabled(true);
        datepickersupportcompat.dialogFragmentListener = this;
        Calendar calendar = FindTimeUtil.getMinDateForDatePicker(this);
        long l = proposedStartTime.toMillisWithFallback();
        long l1 = calendar.getTimeInMillis();
        int i = PreferenceUtils.getFirstDayOfWeekAsCalendar(this);
        datepickersupportcompat.setMinDate(calendar);
        datepickersupportcompat.setFirstDayOfWeek(i);
        if (l1 <= l)
        {
            datepickersupportcompat.initialize(proposedStartTime.year, proposedStartTime.month, proposedStartTime.monthDay);
        } else
        {
            datepickersupportcompat.initialize(calendar.get(1), calendar.get(2), calendar.get(5));
        }
        datepickersupportcompat.fragment.show(super.mFragments.mHost.mFragmentManager, "datePickerDialogFragment");
    }

    private final void launchProposedTimeConfirmationDialog()
    {
        Object obj = proposedStartTime;
        ((Time) (obj)).writeFieldsToImpl();
        long l = ((Time) (obj)).impl.toMillis(true);
        long l1 = eventEndMillis;
        long l2 = eventStartMillis;
        obj = proposedNote;
        ProposedTimeConfirmationDialog proposedtimeconfirmationdialog = new ProposedTimeConfirmationDialog();
        Bundle bundle = new Bundle(3);
        bundle.putLong("proposed_start_time", l);
        bundle.putLong("proposed_end_time", (l1 - l2) + l);
        bundle.putString("proposed_note", ((String) (obj)));
        proposedtimeconfirmationdialog.setArguments(bundle);
        proposedtimeconfirmationdialog.listener = this;
        proposedtimeconfirmationdialog.show(super.mFragments.mHost.mFragmentManager, "confirmDialogFragment");
    }

    public void finish()
    {
        super.finish();
        overridePendingTransition(0, 0);
    }

    public void onBackPressed()
    {
        if (super.mFragments.mHost.mFragmentManager.popBackStackImmediate("addNoteFragment", 1))
        {
            launchProposedTimeConfirmationDialog();
            return;
        } else
        {
            super.onBackPressed();
            return;
        }
    }

    public final void onCancelled$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NL0SJFE1NN6PAKD5MMAGB4CH76UT358PP62PRDCLN78EP9AO______0()
    {
        if (super.mFragments.mHost.mFragmentManager.popBackStackImmediate("addNoteFragment", 1))
        {
            launchProposedTimeConfirmationDialog();
        }
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f050134);
        eventStartMillis = getIntent().getLongExtra("start_millis", 0L);
        eventEndMillis = getIntent().getLongExtra("end_millis", 0L);
        eventColor = getIntent().getIntExtra("event_color", getResources().getColor(0x7f0d0084));
        proposedStartTime = new Time(Utils.getTimeZoneId(this));
        if (bundle != null) goto _L2; else goto _L1
_L1:
        Time time = proposedStartTime;
        long l = eventStartMillis;
        time.impl.timezone = time.timezone;
        time.impl.set(l);
        time.impl.toMillis(true);
        time.copyFieldsFromImpl();
        launchDatePicker();
_L4:
        Object obj = PerformanceMetricCollectorHolder.instance;
        Object obj1;
        TimePickerSupportDialog timepickersupportdialog;
        com.android.datetimepicker.time.TimePickerCompat.LibraryTimeSetListenerWrapper librarytimesetlistenerwrapper;
        long l1;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("PrimesLogger not set"));
        }
        obj = (PerformanceMetricCollector)obj;
        if (bundle == null)
        {
            bundle = "ProposeTimeActivity.Created";
        } else
        {
            bundle = "ProposeTimeActivity.Recreated";
        }
        ((PerformanceMetricCollector) (obj)).recordMemory(bundle);
        return;
        continue; /* Loop/switch isn't completed */
_L2:
        obj = proposedStartTime;
        l1 = bundle.getLong("proposed_start_time", 0L);
        ((Time) (obj)).impl.timezone = ((Time) (obj)).timezone;
        ((Time) (obj)).impl.set(l1);
        ((Time) (obj)).impl.toMillis(true);
        ((Time) (obj)).copyFieldsFromImpl();
        obj = (DialogFragment)super.mFragments.mHost.mFragmentManager.findFragmentByTag("datePickerDialogFragment");
        if (obj != null)
        {
            obj1 = new DatePickerSupportCompat(this);
            ((DatePickerSupportCompat) (obj1)).setRtlEnabled(true);
            if (((DatePickerSupportCompat) (obj1)).fragment != null)
            {
                ((DatePickerSupportCompat) (obj1)).fragment.dismiss();
            }
            if (obj instanceof com.android.datetimepicker.date.DatePickerSupportCompat.DefaultSupportDatePickerFragment)
            {
                ((com.android.datetimepicker.date.DatePickerSupportCompat.DefaultSupportDatePickerFragment)obj).dateListener = new com.android.datetimepicker.date.DatePickerCompat.DefaultDateSetListenerWrapper(((DatePickerSupportCompat) (obj1)).dateListener);
            } else
            if (obj instanceof DatePickerSupportDialog)
            {
                ((DatePickerSupportDialog)obj).callBack = new com.android.datetimepicker.date.DatePickerSupportCompat.LibraryDateSetSupportListenerWrapper(((DatePickerSupportCompat) (obj1)).dateListener);
            }
            obj1.fragment = ((DialogFragment) (obj));
            if ((obj instanceof com.android.datetimepicker.date.DatePickerSupportCompat.DefaultSupportDatePickerFragment) || (obj instanceof DatePickerSupportDialog))
            {
                ((SupportDialogFragmentWithListener)obj).dialogListener = this;
            }
        }
        obj = (DialogFragment)super.mFragments.mHost.mFragmentManager.findFragmentByTag("timePickerDialogFragment");
        if (obj != null)
        {
            obj1 = new TimePickerSupportCompat(this);
            if (((TimePickerSupportCompat) (obj1)).fragment != null)
            {
                ((TimePickerSupportCompat) (obj1)).fragment.dismiss();
            }
            if (obj instanceof com.android.datetimepicker.time.TimePickerSupportCompat.DefaultTimePickerSupportFragment)
            {
                ((com.android.datetimepicker.time.TimePickerSupportCompat.DefaultTimePickerSupportFragment)obj).timeListener = new com.android.datetimepicker.time.TimePickerCompat.DefaultTimeSetListenerWrapper(((TimePickerSupportCompat) (obj1)).onTimeSetListener);
            } else
            if (obj instanceof TimePickerSupportDialog)
            {
                timepickersupportdialog = (TimePickerSupportDialog)obj;
                librarytimesetlistenerwrapper = new com.android.datetimepicker.time.TimePickerCompat.LibraryTimeSetListenerWrapper(((TimePickerSupportCompat) (obj1)).onTimeSetListener);
                timepickersupportdialog.baseDialog.callback = librarytimesetlistenerwrapper;
            }
            obj1.fragment = ((DialogFragment) (obj));
            if ((obj instanceof com.android.datetimepicker.time.TimePickerSupportCompat.DefaultTimePickerSupportFragment) || (obj instanceof TimePickerSupportDialog))
            {
                ((SupportDialogFragmentWithListener)obj).dialogListener = this;
            }
        }
        obj = (ProposedTimeConfirmationDialog)super.mFragments.mHost.mFragmentManager.findFragmentByTag("confirmDialogFragment");
        if (obj != null)
        {
            obj.listener = this;
        }
        obj = (ProposeTimeAddNoteFragment)super.mFragments.mHost.mFragmentManager.findFragmentByTag("addNoteFragment");
        if (obj != null)
        {
            obj.listener = this;
        }
        continue; /* Loop/switch isn't completed */
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final void onDateSet(int i, int j, int k)
    {
        dateOrTimeSet = true;
        proposedStartTime.year = i;
        proposedStartTime.month = j;
        proposedStartTime.monthDay = k;
        proposedStartTime.normalizeSafe();
        TimePickerSupportCompat timepickersupportcompat = new TimePickerSupportCompat(this);
        timepickersupportcompat.dialogFragmentListener = this;
        timepickersupportcompat.initialize(proposedStartTime.hour, proposedStartTime.minute, DateFormat.is24HourFormat(this));
        timepickersupportcompat.fragment.show(super.mFragments.mHost.mFragmentManager, "timePickerDialogFragment");
    }

    protected void onDestroy()
    {
        PerformanceMetricCollector performancemetriccollector = PerformanceMetricCollectorHolder.instance;
        if (performancemetriccollector == null)
        {
            throw new NullPointerException(String.valueOf("PrimesLogger not set"));
        } else
        {
            ((PerformanceMetricCollector)performancemetriccollector).recordMemory("ProposeTimeActivity.Destroyed");
            super.onDestroy();
            return;
        }
    }

    public final volatile void onDialogCancelled(Object obj)
    {
    }

    public final volatile void onDialogCreated(Object obj)
    {
    }

    public final void onDialogDestroyed(Object obj)
    {
        if (!isChangingConfigurations() && !dateOrTimeSet)
        {
            setResult(0);
            finish();
        }
        dateOrTimeSet = false;
    }

    public final void onNoteAdded$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NL0SJFE1NN6PAKD5MMAGB4CH76UT358PP62PRDCLN78EQA9966KOBMC4NMOOBECSNL6T3ID5N6EEP9AO______0(String s)
    {
        proposedNote = s;
        if (super.mFragments.mHost.mFragmentManager.popBackStackImmediate("addNoteFragment", 1))
        {
            launchProposedTimeConfirmationDialog();
        }
    }

    public final void onProposedNoteEditRequest()
    {
        Object obj = proposedStartTime;
        ((Time) (obj)).writeFieldsToImpl();
        long l = ((Time) (obj)).impl.toMillis(true);
        int i = eventColor;
        long l1 = eventEndMillis;
        long l2 = eventStartMillis;
        obj = proposedNote;
        ProposeTimeAddNoteFragment proposetimeaddnotefragment = new ProposeTimeAddNoteFragment();
        Bundle bundle = new Bundle(4);
        bundle.putInt("event_color", i);
        bundle.putLong("start_millis", l);
        bundle.putLong("end_millis", (l1 - l2) + l);
        bundle.putString("note", ((String) (obj)));
        proposetimeaddnotefragment.setArguments(bundle);
        proposetimeaddnotefragment.listener = this;
        super.mFragments.mHost.mFragmentManager.beginTransaction().addToBackStack("addNoteFragment").add(0x7f10013c, proposetimeaddnotefragment, "addNoteFragment").commit();
    }

    public final void onProposedTimeCancel()
    {
        setResult(0);
        finish();
    }

    public final void onProposedTimeConfirm()
    {
        Object obj = proposedStartTime;
        ((Time) (obj)).writeFieldsToImpl();
        long l = ((Time) (obj)).impl.toMillis(true);
        obj = new Intent();
        ((Intent) (obj)).putExtra("start_millis", l);
        ((Intent) (obj)).putExtra("end_millis", l + (eventEndMillis - eventStartMillis));
        ((Intent) (obj)).putExtra("intent_key_note", proposedNote);
        setResult(-1, ((Intent) (obj)));
        finish();
    }

    public final void onProposedTimeEditRequest()
    {
        launchDatePicker();
    }

    public void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        Time time = proposedStartTime;
        time.writeFieldsToImpl();
        bundle.putLong("proposed_start_time", time.impl.toMillis(true));
    }

    public final void onTimeSet(int i, int j)
    {
        dateOrTimeSet = true;
        proposedStartTime.hour = i;
        proposedStartTime.minute = j;
        proposedStartTime.normalizeSafe();
        launchProposedTimeConfirmationDialog();
    }
}
