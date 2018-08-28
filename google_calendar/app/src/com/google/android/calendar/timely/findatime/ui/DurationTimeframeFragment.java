// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.findatime.ui;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import com.google.android.apps.calendar.util.preference.PreferenceUtils;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.event.CustomDurationDialog;
import com.google.android.calendar.material.Material;
import com.google.android.calendar.time.DateTimeUtils;
import com.google.android.calendar.timely.FindTimeUtil;
import com.google.calendar.v2.client.service.api.time.DateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

// Referenced classes of package com.google.android.calendar.timely.findatime.ui:
//            DurationTimeframe, LabelsUtil

public final class DurationTimeframeFragment extends Fragment
    implements android.app.DatePickerDialog.OnDateSetListener, android.content.DialogInterface.OnCancelListener, android.view.ViewTreeObserver.OnScrollChangedListener, android.widget.RadioGroup.OnCheckedChangeListener, com.google.android.calendar.event.CustomDurationDialog.OnCustomDurationSetListener
{

    private int barElevation;
    private RadioGroup durationRadioGroup;
    public DurationTimeframe durationTimeframe;
    public int eventColor;
    private View roomOptionsContainer;
    public ScrollView scrollView;
    private FrameLayout timeframeDurationHeader;
    private TextView timeframeDurationView;
    public RadioGroup timeframeRadioGroup;
    private TimeZone timezone;
    private Toolbar toolbar;

    public DurationTimeframeFragment()
    {
    }

    private final void logDurationSelected()
    {
        Object obj;
        AnalyticsLogger analyticslogger;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        obj = ((FragmentActivity) (obj)).getApplicationContext();
        analyticslogger = AnalyticsLoggerHolder.instance;
        if (analyticslogger == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        } else
        {
            ((AnalyticsLogger)analyticslogger).trackEvent(((Context) (obj)), "find_a_time", "filter_duration_v2", String.format("selected:%s", new Object[] {
                String.valueOf(durationTimeframe.durationInMinutes)
            }), null);
            return;
        }
    }

    private final void logTimeframeSelected()
    {
        Object obj;
        AnalyticsLogger analyticslogger;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        obj = ((FragmentActivity) (obj)).getApplicationContext();
        analyticslogger = AnalyticsLoggerHolder.instance;
        if (analyticslogger == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        } else
        {
            ((AnalyticsLogger)analyticslogger).trackEvent(((Context) (obj)), "find_a_time", "filter_timeframe_v2", String.format("selected:%s", new Object[] {
                durationTimeframe.getTimeframeTag()
            }), null);
            return;
        }
    }

    private final void redrawDurationOptions()
    {
        durationRadioGroup.setOnCheckedChangeListener(null);
        durationRadioGroup.removeAllViews();
        durationRadioGroup.clearCheck();
        int i = 0;
        while (i < durationTimeframe.durationLabels.size()) 
        {
            Object obj;
            if (super.mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)super.mHost.mActivity;
            }
            obj = (RadioButton)LayoutInflater.from(((Context) (obj))).inflate(0x7f050065, null);
            ((RadioButton) (obj)).setId(i + 200);
            ((RadioButton) (obj)).setText((CharSequence)durationTimeframe.durationLabels.get(i));
            durationRadioGroup.addView(((View) (obj)));
            i++;
        }
        i = durationTimeframe.getSelectedDurationIndex();
        durationRadioGroup.check(i + 200);
        durationRadioGroup.setOnCheckedChangeListener(this);
    }

    private final void redrawTimeframeOptions()
    {
        timeframeRadioGroup.setOnCheckedChangeListener(null);
        timeframeRadioGroup.removeAllViews();
        timeframeRadioGroup.clearCheck();
        int i = 0;
        while (i < durationTimeframe.timeframeLabels.size()) 
        {
            Object obj;
            if (super.mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)super.mHost.mActivity;
            }
            obj = (RadioButton)LayoutInflater.from(((Context) (obj))).inflate(0x7f050065, null);
            ((RadioButton) (obj)).setId(i + 100);
            ((RadioButton) (obj)).setText((CharSequence)durationTimeframe.timeframeLabels.get(i));
            timeframeRadioGroup.addView(((View) (obj)));
            i++;
        }
        i = durationTimeframe.getSelectedTimeframeIndex();
        timeframeRadioGroup.check(i + 100);
        timeframeRadioGroup.setOnCheckedChangeListener(this);
    }

    private final void restoreCheckboxSelections()
    {
        int i = durationTimeframe.getSelectedDurationIndex();
        int j = durationTimeframe.getSelectedTimeframeIndex();
        RadioGroup radiogroup = durationRadioGroup;
        radiogroup.setOnCheckedChangeListener(null);
        radiogroup.check(i + 200);
        radiogroup.setOnCheckedChangeListener(this);
        radiogroup = timeframeRadioGroup;
        radiogroup.setOnCheckedChangeListener(null);
        radiogroup.check(j + 100);
        radiogroup.setOnCheckedChangeListener(this);
    }

    private final void setTimeframeDurationLabel()
    {
        String s = LabelsUtil.getTimeframeDurationLabel(this, durationTimeframe, false);
        timeframeDurationView.setText(s);
        s = requireContext().getResources().getString(0x7f130065, new Object[] {
            s
        });
        timeframeDurationView.setContentDescription(s);
    }

    public final void onCancel(final DialogInterface group)
    {
        restoreCheckboxSelections();
        setTimeframeDurationLabel();
        group = timeframeRadioGroup;
        (new Handler()).post(new _cls5());
        AnalyticsLogger analyticslogger;
        if (super.mHost == null)
        {
            group = null;
        } else
        {
            group = (FragmentActivity)super.mHost.mActivity;
        }
        group = group.getApplicationContext();
        analyticslogger = AnalyticsLoggerHolder.instance;
        if (analyticslogger == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        } else
        {
            ((AnalyticsLogger)analyticslogger).trackEvent(group, "find_a_time", "filter_timeframe_v2", "cancelled", null);
            return;
        }
    }

    public final void onCheckedChanged(RadioGroup radiogroup, int i)
    {
        if (radiogroup.getId() != timeframeRadioGroup.getId()) goto _L2; else goto _L1
_L1:
        int j = i - 100;
        if (!DurationTimeframe.isDateBasedTimeframe(j)) goto _L4; else goto _L3
_L3:
        Object obj = DateTimeUtils.getNowDateTime(timezone.getID());
        DatePicker datepicker;
        if (super.mHost == null)
        {
            radiogroup = null;
        } else
        {
            radiogroup = (FragmentActivity)super.mHost.mActivity;
        }
        obj = new DatePickerDialog(radiogroup, this, ((DateTime) (obj)).getYear(), ((DateTime) (obj)).getMonthOfYear() - 1, ((DateTime) (obj)).getDayOfMonth());
        ((DatePickerDialog) (obj)).setOnCancelListener(this);
        datepicker = ((DatePickerDialog) (obj)).getDatePicker();
        if (super.mHost == null)
        {
            radiogroup = null;
        } else
        {
            radiogroup = (FragmentActivity)super.mHost.mActivity;
        }
        datepicker.setMinDate(FindTimeUtil.getMinDateForDatePicker(radiogroup).getTimeInMillis());
        if (super.mHost == null)
        {
            radiogroup = null;
        } else
        {
            radiogroup = (FragmentActivity)super.mHost.mActivity;
        }
        datepicker.setFirstDayOfWeek(PreferenceUtils.getFirstDayOfWeekAsCalendar(radiogroup));
        ((DatePickerDialog) (obj)).setTitle(null);
        ((DatePickerDialog) (obj)).show();
_L6:
        return;
_L4:
        radiogroup = durationTimeframe;
        i = j;
        if (j >= 5)
        {
            i = ((DurationTimeframe) (radiogroup)).customTimeframeOption;
        }
        radiogroup.timeframeOption = i;
        setTimeframeDurationLabel();
        logTimeframeSelected();
        return;
_L2:
        if (radiogroup.getId() == durationRadioGroup.getId())
        {
            int k = i - 200;
            radiogroup = (CustomDurationDialog)super.mFragmentManager.findFragmentByTag(CustomDurationDialog.TAG);
            if (k >= durationTimeframe.durationValues.size())
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i == 0)
            {
                radiogroup = durationTimeframe;
                radiogroup.durationInMinutes = ((Integer)((DurationTimeframe) (radiogroup)).durationValues.get(k)).intValue();
                setTimeframeDurationLabel();
                logDurationSelected();
                return;
            }
            if (radiogroup == null || !radiogroup.getShowsDialog())
            {
                radiogroup = new com.google.android.calendar.event.CustomDurationDialog.Builder(durationTimeframe.durationInMinutes);
                ((com.google.android.calendar.event.CustomDurationDialog.Builder) (radiogroup)).args.putInt("max_duration_in_minutes", 1440);
                ((com.google.android.calendar.event.CustomDurationDialog.Builder) (radiogroup)).args.putInt("max_duration_error_msg", 0x7f1301fd);
                ((com.google.android.calendar.event.CustomDurationDialog.Builder) (radiogroup)).args.putInt("min_duration_in_minutes", 1);
                ((com.google.android.calendar.event.CustomDurationDialog.Builder) (radiogroup)).args.putInt("min_duration_error_msg", 0x7f1301fe);
                CustomDurationDialog customdurationdialog = new CustomDurationDialog();
                customdurationdialog.setArguments(((com.google.android.calendar.event.CustomDurationDialog.Builder) (radiogroup)).args);
                customdurationdialog.setTargetFragment(this, 0);
                customdurationdialog.show(super.mFragmentManager, CustomDurationDialog.TAG);
                return;
            }
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    public final void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        barElevation = requireContext().getResources().getDimensionPixelSize(0x7f0e0147);
        if (bundle != null)
        {
            durationTimeframe = (DurationTimeframe)bundle.getParcelable("duration_timeframe");
        } else
        {
            durationTimeframe = (DurationTimeframe)getArguments().getParcelable("duration_timeframe");
            int i = durationTimeframe.timeframeOption;
            if (i == 3 || i == 4)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                bundle = LabelsUtil.getDateBasedTimeframeLabel(this, durationTimeframe, false);
                if (!durationTimeframe.timeframeLabels.contains(bundle))
                {
                    durationTimeframe.removeCustomTimeframeLabel();
                    DurationTimeframe durationtimeframe = durationTimeframe;
                    durationtimeframe.timeframeLabels.add(bundle);
                    durationtimeframe.timeframeComposableWithDurationLabels.add(bundle);
                    durationtimeframe.customTimeframeOption = durationtimeframe.timeframeOption;
                }
            }
            bundle = durationTimeframe;
            i = durationTimeframe.durationInMinutes;
            if (!((DurationTimeframe) (bundle)).durationValues.contains(Integer.valueOf(i)))
            {
                durationTimeframe.addDurationValue(durationTimeframe.durationInMinutes);
                return;
            }
        }
    }

    public final View onCreateView(final LayoutInflater considerExistingRooms, ViewGroup viewgroup, Bundle bundle)
    {
        boolean flag = false;
        viewgroup = (ViewGroup)considerExistingRooms.inflate(0x7f050064, viewgroup, false);
        timezone = TimeZone.getTimeZone(getArguments().getString("timezone"));
        eventColor = getArguments().getInt("event_color");
        toolbar = (Toolbar)viewgroup.findViewById(0x7f100113);
        durationRadioGroup = (RadioGroup)viewgroup.findViewById(0x7f100193);
        timeframeRadioGroup = (RadioGroup)viewgroup.findViewById(0x7f100195);
        timeframeDurationView = (TextView)viewgroup.findViewById(0x7f100191);
        scrollView = (ScrollView)viewgroup.findViewById(0x7f100149);
        timeframeDurationHeader = (FrameLayout)viewgroup.findViewById(0x7f100190);
        durationRadioGroup.setOnCheckedChangeListener(this);
        timeframeRadioGroup.setOnCheckedChangeListener(this);
        scrollView.getViewTreeObserver().addOnScrollChangedListener(this);
        int i;
        boolean flag1;
        if (Material.robotoMedium != null)
        {
            considerExistingRooms = Material.robotoMedium;
        } else
        {
            considerExistingRooms = Typeface.create("sans-serif-medium", 0);
            Material.robotoMedium = considerExistingRooms;
        }
        ((TextView)viewgroup.findViewById(0x7f100194)).setTypeface(considerExistingRooms);
        ((TextView)viewgroup.findViewById(0x7f100192)).setTypeface(considerExistingRooms);
        ((TextView)viewgroup.findViewById(0x7f100196)).setTypeface(considerExistingRooms);
        toolbar.setBackgroundColor(eventColor);
        considerExistingRooms = toolbar;
        (new SupportMenuInflater(considerExistingRooms.getContext())).inflate(0x7f150004, considerExistingRooms.getMenu());
        considerExistingRooms = toolbar;
        bundle = new _cls1();
        considerExistingRooms.ensureNavButtonView();
        ((Toolbar) (considerExistingRooms)).mNavButtonView.setOnClickListener(bundle);
        toolbar.mOnMenuItemClickListener = new _cls2();
        roomOptionsContainer = viewgroup.findViewById(0x7f100197);
        considerExistingRooms = roomOptionsContainer;
        flag1 = durationTimeframe.hasRooms;
        if (considerExistingRooms != null)
        {
            if (flag1)
            {
                i = 0;
            } else
            {
                i = 8;
            }
            considerExistingRooms.setVisibility(i);
        }
        considerExistingRooms = viewgroup.findViewById(0x7f100196);
        flag1 = durationTimeframe.hasRooms;
        if (considerExistingRooms != null)
        {
            if (flag1)
            {
                i = ((flag) ? 1 : 0);
            } else
            {
                i = 8;
            }
            considerExistingRooms.setVisibility(i);
        }
        if (durationTimeframe.hasRooms)
        {
            considerExistingRooms = (Switch)viewgroup.findViewById(0x7f100198);
            considerExistingRooms.setChecked(durationTimeframe.considerExistingRooms);
            roomOptionsContainer.setOnClickListener(new _cls3());
            considerExistingRooms.setOnCheckedChangeListener(new _cls4());
        }
        durationTimeframe.durationLabels.clear();
        durationTimeframe.durationLabels.addAll(LabelsUtil.getDurationLabels(requireContext().getResources(), durationTimeframe.durationValues, true));
        redrawTimeframeOptions();
        redrawDurationOptions();
        setTimeframeDurationLabel();
        return viewgroup;
    }

    public final void onCustomDurationDialogCancel()
    {
        restoreCheckboxSelections();
        setTimeframeDurationLabel();
        final RadioGroup group = durationRadioGroup;
        (new Handler()).post(new _cls5());
        AnalyticsLogger analyticslogger;
        if (super.mHost == null)
        {
            group = null;
        } else
        {
            group = (FragmentActivity)super.mHost.mActivity;
        }
        group = group.getApplicationContext();
        analyticslogger = AnalyticsLoggerHolder.instance;
        if (analyticslogger == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        } else
        {
            ((AnalyticsLogger)analyticslogger).trackEvent(group, "find_a_time", "filter_duration_v2", "cancelled", null);
            return;
        }
    }

    public final void onCustomDurationSet(int i)
    {
        if (!durationTimeframe.durationValues.contains(Integer.valueOf(i)))
        {
            DurationTimeframe durationtimeframe = durationTimeframe;
            if (durationtimeframe.customDurationIndex != -1)
            {
                durationtimeframe.durationValues.remove(durationtimeframe.customDurationIndex);
                durationtimeframe.customDurationIndex = -1;
            }
            durationTimeframe.addDurationValue(i);
            durationTimeframe.durationLabels.clear();
            durationTimeframe.durationLabels.addAll(LabelsUtil.getDurationLabels(requireContext().getResources(), durationTimeframe.durationValues, true));
            redrawDurationOptions();
        }
        durationTimeframe.durationInMinutes = i;
        final RadioGroup group = durationRadioGroup;
        i = durationTimeframe.getSelectedDurationIndex();
        group.setOnCheckedChangeListener(null);
        group.check(i + 200);
        group.setOnCheckedChangeListener(this);
        group = durationRadioGroup;
        (new Handler()).post(new _cls5());
        setTimeframeDurationLabel();
        logDurationSelected();
    }

    public final void onDateSet(final DatePicker group, int i, int j, int k)
    {
        group = durationTimeframe;
        int i1 = timeframeRadioGroup.getCheckedRadioButtonId() - 100;
        int l = i1;
        if (i1 >= 5)
        {
            l = ((DurationTimeframe) (group)).customTimeframeOption;
        }
        group.timeframeOption = l;
        durationTimeframe.timeframeOption;
        JVM INSTR tableswitch 0 4: default 80
    //                   0 80
    //                   1 80
    //                   2 80
    //                   3 136
    //                   4 136;
           goto _L1 _L1 _L1 _L1 _L2 _L2
_L1:
        redrawTimeframeOptions();
        setTimeframeDurationLabel();
        (new Handler()).post(new _cls6());
        group = timeframeRadioGroup;
        (new Handler()).post(new _cls5());
        logTimeframeSelected();
        return;
_L2:
        durationTimeframe.removeCustomTimeframeLabel();
        durationTimeframe.customDate = DateTimeUtils.getNowDateTime(timezone.getID()).withDate(i, j + 1, k);
        group = durationTimeframe;
        String s = LabelsUtil.getDateBasedTimeframeLabel(this, durationTimeframe, false);
        ((DurationTimeframe) (group)).timeframeLabels.add(s);
        ((DurationTimeframe) (group)).timeframeComposableWithDurationLabels.add(s);
        group.customTimeframeOption = ((DurationTimeframe) (group)).timeframeOption;
        if (true) goto _L1; else goto _L3
_L3:
    }

    public final void onSaveInstanceState(Bundle bundle)
    {
        bundle.putParcelable("duration_timeframe", durationTimeframe);
        super.onSaveInstanceState(bundle);
    }

    public final void onScrollChanged()
    {
        int i = scrollView.getScrollY();
        float f = timeframeDurationHeader.getElevation();
        if (f == 0.0F && i > 0)
        {
            timeframeDurationHeader.setElevation(barElevation);
            toolbar.setElevation(barElevation);
        } else
        if (i <= 0 && f != 0.0F)
        {
            timeframeDurationHeader.setElevation(0.0F);
            toolbar.setElevation(0.0F);
            return;
        }
    }

    private class _cls5
        implements Runnable
    {

        private final RadioGroup val$group;

        public final void run()
        {
            RadioButton radiobutton = (RadioButton)group.findViewById(group.getCheckedRadioButtonId());
            radiobutton.requestFocus();
            AccessibilityUtils.requestAccessibilityFocus(radiobutton);
        }

        _cls5()
        {
            group = radiogroup;
            super();
        }
    }


    private class _cls1
        implements android.view.View.OnClickListener
    {

        private final DurationTimeframeFragment this$0;

        public final void onClick(View view)
        {
            ((Listener)mTarget).onFilterBack();
        }

        _cls1()
        {
            this$0 = DurationTimeframeFragment.this;
            super();
        }

        private class Listener
        {

            public abstract void onFilterApply(DurationTimeframe durationtimeframe);

            public abstract void onFilterBack();
        }

    }


    private class _cls2
        implements android.support.v7.widget.Toolbar.OnMenuItemClickListener
    {

        private final DurationTimeframeFragment this$0;

        public final boolean onMenuItemClick(MenuItem menuitem)
        {
            ((Listener)mTarget).onFilterApply(durationTimeframe);
            return false;
        }

        _cls2()
        {
            this$0 = DurationTimeframeFragment.this;
            super();
        }
    }


    private class _cls3
        implements android.view.View.OnClickListener
    {

        private final Switch val$considerExistingRooms;

        public final void onClick(View view)
        {
            considerExistingRooms.toggle();
        }

        _cls3()
        {
            considerExistingRooms = switch1;
            super();
        }
    }


    private class _cls4
        implements android.widget.CompoundButton.OnCheckedChangeListener
    {

        private final DurationTimeframeFragment this$0;

        public final void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
        {
            durationTimeframe.considerExistingRooms = flag;
        }

        _cls4()
        {
            this$0 = DurationTimeframeFragment.this;
            super();
        }
    }


    private class _cls6
        implements Runnable
    {

        private final DurationTimeframeFragment this$0;

        public final void run()
        {
            scrollView.scrollTo(0, timeframeRadioGroup.getBottom());
        }

        _cls6()
        {
            this$0 = DurationTimeframeFragment.this;
            super();
        }
    }

}
