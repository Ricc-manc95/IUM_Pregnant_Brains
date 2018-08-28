// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.recurrencepicker;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.utils.ColorUtils;
import com.google.android.calendar.utils.statusbar.StatusbarAnimatorCompat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

// Referenced classes of package com.google.android.calendar.recurrencepicker:
//            RecurrencePickerView, RecurrencePickerState

public class RecurrencePickerActivity extends AppCompatActivity
{

    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/recurrencepicker/RecurrencePickerActivity);
    private Toolbar toolbar;
    public RecurrencePickerView view;

    public RecurrencePickerActivity()
    {
    }

    final void hideKeyboard()
    {
        InputMethodManager inputmethodmanager = (InputMethodManager)getSystemService("input_method");
        inputmethodmanager.hideSoftInputFromWindow(findViewById(0x7f10014a).getWindowToken(), 0);
        inputmethodmanager.hideSoftInputFromWindow(findViewById(0x7f1001be).getWindowToken(), 0);
    }

    protected void onCreate(Bundle bundle)
    {
        RecurrencePickerView recurrencepickerview;
        Object obj2;
        LayoutInflater layoutinflater;
        SparseArray sparsearray;
        int i;
        super.onCreate(bundle);
        setContentView(0x7f050139);
        bundle = getIntent();
        toolbar = (Toolbar)findViewById(0x7f100315);
        class .Lambda._cls0
            implements android.view.View.OnClickListener
        {

            private final RecurrencePickerActivity arg$1;

            public final void onClick(View view1)
            {
                Intent intent;
                Object obj3;
                Object obj4;
                view1 = arg$1;
                view1.hideKeyboard();
                intent = new Intent();
                obj3 = ((RecurrencePickerActivity) (view1)).view.state;
                obj4 = ((RecurrencePickerState) (obj3)).getFrequency();
                ((RecurrencePickerState.Frequency) (obj4)).ordinal();
                JVM INSTR tableswitch 0 3: default 68
            //                           0 111
            //                           1 327
            //                           2 333
            //                           3 339;
                   goto _L1 _L2 _L3 _L4 _L5
_L1:
                view1 = String.valueOf(obj4);
                throw new IllegalStateException((new StringBuilder(String.valueOf(view1).length() + 28)).append("RecurrencePickerState.Freq: ").append(view1).toString());
_L2:
                int l1 = 3;
_L14:
                obj4 = new com.google.android.calendar.api.event.time.RecurRulePart.Builder(l1);
                ((RecurrencePickerState) (obj3)).getFrequency().ordinal();
                JVM INSTR tableswitch 0 2: default 160
            //                           0 160
            //                           1 346
            //                           2 451;
                   goto _L6 _L6 _L7 _L8
_L6:
                Object obj5;
                ArrayList arraylist;
                Object obj7;
                int i2;
                if (((RecurrencePickerState) (obj3)).getEnd().equals(RecurrencePickerState.End.DATE))
                {
                    obj4.untilDateTimeMillis = ((RecurrencePickerState) (obj3)).getUntilDateTimeMillis();
                } else
                if (((RecurrencePickerState) (obj3)).getEnd().equals(RecurrencePickerState.End.COUNT))
                {
                    ((com.google.android.calendar.api.event.time.RecurRulePart.Builder) (obj4)).setCount(((RecurrencePickerState) (obj3)).getCount());
                } else
                {
                    obj4.untilDateTimeMillis = null;
                    ((com.google.android.calendar.api.event.time.RecurRulePart.Builder) (obj4)).setCount(null);
                }
                ((com.google.android.calendar.api.event.time.RecurRulePart.Builder) (obj4)).setInterval(((RecurrencePickerState) (obj3)).getInterval()).build();
                obj4.wkst = Integer.valueOf(StateConverter.calendarWeekdayToApiWeekday(((RecurrencePickerState) (obj3)).getFirstDayOfWeek().intValue()));
                intent.putExtra("recurrence_result", ((com.google.android.calendar.api.event.time.RecurRulePart.Builder) (obj4)).build().toRfc5545String());
                obj3 = RecurrencePickerActivity.TAG;
                obj4 = ((RecurrencePickerActivity) (view1)).view.state;
                obj5 = ((RecurrencePickerState) (obj4)).getFrequency();
                ((RecurrencePickerState.Frequency) (obj5)).ordinal();
                JVM INSTR tableswitch 0 3: default 284
            //                           0 603
            //                           1 742
            //                           2 748
            //                           3 754;
                   goto _L9 _L10 _L11 _L12 _L13
_L9:
                view1 = String.valueOf(obj5);
                throw new IllegalStateException((new StringBuilder(String.valueOf(view1).length() + 28)).append("RecurrencePickerState.Freq: ").append(view1).toString());
_L3:
                l1 = 4;
                  goto _L14
_L4:
                l1 = 5;
                  goto _L14
_L5:
                l1 = 6;
                  goto _L14
_L7:
                arraylist = new ArrayList(((RecurrencePickerState) (obj3)).getByDay());
                Collections.sort(arraylist);
                obj5 = new ArrayList();
                arraylist = (ArrayList)arraylist;
                i2 = arraylist.size();
                for (l1 = 0; l1 < i2;)
                {
                    obj7 = arraylist.get(l1);
                    l1++;
                    ((ArrayList) (obj5)).add(new ByDayRecurrence(StateConverter.calendarWeekdayToApiWeekday(((Integer)obj7).intValue()), null));
                }

                ((com.google.android.calendar.api.event.time.RecurRulePart.Builder) (obj4)).setByDay(((java.util.List) (obj5)));
                  goto _L6
_L8:
                if (((RecurrencePickerState) (obj3)).getMonthFrequency() == RecurrencePickerState.MonthFrequency.MONTHDAY)
                {
                    ((com.google.android.calendar.api.event.time.RecurRulePart.Builder) (obj4)).setByMonthDay(ImmutableList.copyOf(((RecurrencePickerState) (obj3)).getByMonthDay()));
                } else
                {
                    i2 = StateConverter.calendarWeekdayToApiWeekday(((RecurrencePickerState) (obj3)).getStartWeekday().intValue());
                    if (((RecurrencePickerState) (obj3)).getMonthFrequency() == RecurrencePickerState.MonthFrequency.LAST)
                    {
                        l1 = -1;
                    } else
                    {
                        obj5 = Calendar.getInstance(((RecurrencePickerState) (obj3)).getTimeZone());
                        ((Calendar) (obj5)).setTimeInMillis(((RecurrencePickerState) (obj3)).getStartTimeInMillis().longValue());
                        l1 = ((Calendar) (obj5)).get(8);
                    }
                    ((com.google.android.calendar.api.event.time.RecurRulePart.Builder) (obj4)).setByDay(ImmutableList.of(new ByDayRecurrence(i2, Integer.valueOf(l1))));
                }
                  goto _L6
_L10:
                l1 = 3;
_L18:
                com.google.android.calendar.api.event.time.RecurRulePart.Builder builder = new com.google.android.calendar.api.event.time.RecurRulePart.Builder(l1);
                ((RecurrencePickerState) (obj4)).getFrequency().ordinal();
                JVM INSTR tableswitch 0 2: default 652
            //                           0 652
            //                           1 761
            //                           2 867;
                   goto _L15 _L15 _L16 _L17
_L15:
                Object obj6;
                ArrayList arraylist1;
                Object obj8;
                int j2;
                if (((RecurrencePickerState) (obj4)).getEnd().equals(RecurrencePickerState.End.DATE))
                {
                    builder.untilDateTimeMillis = ((RecurrencePickerState) (obj4)).getUntilDateTimeMillis();
                } else
                if (((RecurrencePickerState) (obj4)).getEnd().equals(RecurrencePickerState.End.COUNT))
                {
                    builder.setCount(((RecurrencePickerState) (obj4)).getCount());
                } else
                {
                    builder.untilDateTimeMillis = null;
                    builder.setCount(null);
                }
                builder.setInterval(((RecurrencePickerState) (obj4)).getInterval()).build();
                builder.wkst = Integer.valueOf(StateConverter.calendarWeekdayToApiWeekday(((RecurrencePickerState) (obj4)).getFirstDayOfWeek().intValue()));
                LogUtils.i(((String) (obj3)), "RRULE: %s", new Object[] {
                    builder.build().toRfc5545String()
                });
                view1.setResult(-1, intent);
                view1.finish();
                return;
_L11:
                l1 = 4;
                  goto _L18
_L12:
                l1 = 5;
                  goto _L18
_L13:
                l1 = 6;
                  goto _L18
_L16:
                arraylist1 = new ArrayList(((RecurrencePickerState) (obj4)).getByDay());
                Collections.sort(arraylist1);
                obj6 = new ArrayList();
                arraylist1 = (ArrayList)arraylist1;
                j2 = arraylist1.size();
                for (l1 = 0; l1 < j2;)
                {
                    obj8 = arraylist1.get(l1);
                    l1++;
                    ((ArrayList) (obj6)).add(new ByDayRecurrence(StateConverter.calendarWeekdayToApiWeekday(((Integer)obj8).intValue()), null));
                }

                builder.setByDay(((java.util.List) (obj6)));
                  goto _L15
_L17:
                if (((RecurrencePickerState) (obj4)).getMonthFrequency() == RecurrencePickerState.MonthFrequency.MONTHDAY)
                {
                    builder.setByMonthDay(ImmutableList.copyOf(((RecurrencePickerState) (obj4)).getByMonthDay()));
                } else
                {
                    j2 = StateConverter.calendarWeekdayToApiWeekday(((RecurrencePickerState) (obj4)).getStartWeekday().intValue());
                    if (((RecurrencePickerState) (obj4)).getMonthFrequency() == RecurrencePickerState.MonthFrequency.LAST)
                    {
                        l1 = -1;
                    } else
                    {
                        obj6 = Calendar.getInstance(((RecurrencePickerState) (obj4)).getTimeZone());
                        ((Calendar) (obj6)).setTimeInMillis(((RecurrencePickerState) (obj4)).getStartTimeInMillis().longValue());
                        l1 = ((Calendar) (obj6)).get(8);
                    }
                    builder.setByDay(ImmutableList.of(new ByDayRecurrence(j2, Integer.valueOf(l1))));
                }
                  goto _L15
            }

            .Lambda._cls0()
            {
                arg$1 = RecurrencePickerActivity.this;
            }
        }

        class .Lambda._cls1
            implements android.view.View.OnClickListener
        {

            private final RecurrencePickerActivity arg$1;

            public final void onClick(View view1)
            {
                view1 = arg$1;
                view1.setResult(0);
                view1.hideKeyboard();
                view1.finish();
            }

            .Lambda._cls1()
            {
                arg$1 = RecurrencePickerActivity.this;
            }
        }

        Object obj;
        SimpleDateFormat simpledateformat;
        GregorianCalendar gregoriancalendar;
        int l;
        int j1;
        if (bundle.hasExtra("event_color"))
        {
            setTheme(0x7f1401cf);
            Toolbar toolbar1 = toolbar;
            toolbar1.setTitleTextColor(getResources().getColor(0x7f0d02c4));
            toolbar1.setNavigationIcon(0x7f0201c5);
        } else
        {
            setTheme(0x7f1401d0);
            obj1 = toolbar;
            ((Toolbar) (obj1)).setTitleTextColor(getResources().getColor(0x7f0d021a));
            ((Toolbar) (obj1)).setNavigationIcon(0x7f0201c4);
        }
        bundle = Integer.valueOf(bundle.getIntExtra("event_color", Integer.valueOf(getResources().getColor(0x7f0d02c4)).intValue()));
        obj = StatusbarAnimatorCompat.createInstance(getWindow());
        ((StatusbarAnimatorCompat) (obj)).setStatusbarColor(ColorUtils.blend(bundle.intValue(), 0x33000000));
        ((StatusbarAnimatorCompat) (obj)).setLightStatusbar(false);
        toolbar.setBackgroundDrawable(new ColorDrawable(bundle.intValue()));
        toolbar.setTitle(0x7f1303da);
        setSupportActionBar(toolbar);
        findViewById(0x7f100316).setOnClickListener(new .Lambda._cls0());
        bundle = toolbar;
        obj = new .Lambda._cls1();
        bundle.ensureNavButtonView();
        ((Toolbar) (bundle)).mNavButtonView.setOnClickListener(((android.view.View.OnClickListener) (obj)));
        view = (RecurrencePickerView)findViewById(0x7f100314);
        bundle = getIntent();
        recurrencepickerview = view;
        recurrencepickerview.state = (RecurrencePickerState)bundle.getExtras().get("bundle_state");
        recurrencepickerview.firstFrequencyHolder = (FrameLayout)recurrencepickerview.findViewById(0x7f100318);
        recurrencepickerview.secondFrequencyHolder = (FrameLayout)recurrencepickerview.findViewById(0x7f10031a);
        bundle = recurrencepickerview.getResources().getQuantityString(RecurrencePickerView.getIntervalStringResId(recurrencepickerview.state.getFrequency()), recurrencepickerview.state.getInterval().intValue());
        if (bundle.indexOf("%2$s") < bundle.indexOf("%1$d"))
        {
            bundle = recurrencepickerview.firstFrequencyHolder;
            recurrencepickerview.frequency = (Spinner)((FrameLayout)LayoutInflater.from(recurrencepickerview.getContext()).inflate(0x7f05007c, bundle)).getChildAt(0);
            bundle = recurrencepickerview.secondFrequencyHolder;
            recurrencepickerview.interval = (EditText)((FrameLayout)LayoutInflater.from(recurrencepickerview.getContext()).inflate(0x7f05009d, bundle)).getChildAt(0);
        } else
        {
            bundle = recurrencepickerview.secondFrequencyHolder;
            recurrencepickerview.frequency = (Spinner)((FrameLayout)LayoutInflater.from(recurrencepickerview.getContext()).inflate(0x7f05007c, bundle)).getChildAt(0);
            bundle = recurrencepickerview.firstFrequencyHolder;
            recurrencepickerview.interval = (EditText)((FrameLayout)LayoutInflater.from(recurrencepickerview.getContext()).inflate(0x7f05009d, bundle)).getChildAt(0);
        }
        recurrencepickerview.interval.requestFocus();
        recurrencepickerview.setFrequencyText();
        recurrencepickerview.interval.addTextChangedListener(new RecurrencePickerView._cls1(recurrencepickerview, 1, 1, 99));
        recurrencepickerview.frequencyAdapter = new ArrayAdapter(recurrencepickerview.getContext(), 0x7f050166, recurrencepickerview.getFrequencyPluralityOptionsList(1));
        recurrencepickerview.frequencyAdapter.setDropDownViewResource(0x7f05016a);
        recurrencepickerview.frequency.setAdapter(recurrencepickerview.frequencyAdapter);
        recurrencepickerview.frequency.setOnItemSelectedListener(recurrencepickerview);
        recurrencepickerview.endOptionsLabel = (TextView)recurrencepickerview.findViewById(0x7f100320);
        recurrencepickerview.mInfiniteDurationRadio = (RadioButton)recurrencepickerview.findViewById(0x7f100322);
        recurrencepickerview.mDateDurationRadio = (RadioButton)recurrencepickerview.findViewById(0x7f100324);
        recurrencepickerview.dateDurationText = (TextView)recurrencepickerview.findViewById(0x7f100325);
        recurrencepickerview.mCountDurationRadio = (RadioButton)recurrencepickerview.findViewById(0x7f100328);
        recurrencepickerview.countDuration = (EditText)recurrencepickerview.findViewById(0x7f100329);
        recurrencepickerview.countDurationPostTextView = (TextView)recurrencepickerview.findViewById(0x7f10032a);
        recurrencepickerview.mInfiniteDurationRadio.setOnClickListener(recurrencepickerview);
        recurrencepickerview.mDateDurationRadio.setOnClickListener(recurrencepickerview);
        recurrencepickerview.mCountDurationRadio.setOnClickListener(recurrencepickerview);
        recurrencepickerview.dateDurationText.setOnClickListener(recurrencepickerview);
        bundle = NumberFormat.getInstance(Locale.getDefault()).format(1L);
        recurrencepickerview.countDuration.setText(bundle);
        recurrencepickerview.setCountDurationText(Integer.parseInt(bundle));
        recurrencepickerview.countDuration.addTextChangedListener(new RecurrencePickerView._cls2(recurrencepickerview, 1, 1, 730));
        recurrencepickerview.countDuration.setOnFocusChangeListener(recurrencepickerview);
        recurrencepickerview.monthlyFrequencyOptions = (Spinner)recurrencepickerview.findViewById(0x7f10031c);
        bundle = recurrencepickerview.getContext();
        obj = recurrencepickerview.state;
        obj2 = Calendar.getInstance(((RecurrencePickerState) (obj)).getTimeZone());
        ((Calendar) (obj2)).setTimeInMillis(((RecurrencePickerState) (obj)).getStartTimeInMillis().longValue());
        i = ((Calendar) (obj2)).get(5);
        obj = recurrencepickerview.state;
        obj2 = Calendar.getInstance(((RecurrencePickerState) (obj)).getTimeZone());
        ((Calendar) (obj2)).setTimeInMillis(((RecurrencePickerState) (obj)).getStartTimeInMillis().longValue());
        j1 = ((Calendar) (obj2)).get(7);
        obj = recurrencepickerview.state;
        obj2 = Calendar.getInstance(((RecurrencePickerState) (obj)).getTimeZone());
        ((Calendar) (obj2)).setTimeInMillis(((RecurrencePickerState) (obj)).getStartTimeInMillis().longValue());
        l = ((Calendar) (obj2)).get(8);
        obj = new ArrayList();
        ((ArrayList) (obj)).add(recurrencepickerview.getResources().getString(0x7f13033d, new Object[] {
            String.valueOf(i)
        }));
        i = 0x7f0b0036;
        j1;
        JVM INSTR tableswitch 1 7: default 844
    //                   1 1216
    //                   2 1224
    //                   3 1232
    //                   4 1240
    //                   5 1248
    //                   6 1256
    //                   7 1264;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
        break; /* Loop/switch isn't completed */
_L8:
        break MISSING_BLOCK_LABEL_1264;
_L9:
        if (recurrencepickerview.state.getHasNthOption().booleanValue())
        {
            ((ArrayList) (obj)).add(recurrencepickerview.getResources().getStringArray(i)[l - 1]);
        }
        if (recurrencepickerview.state.getHasLastOption().booleanValue())
        {
            ((ArrayList) (obj)).add(recurrencepickerview.getResources().getStringArray(i)[4]);
        }
        bundle = new ArrayAdapter(bundle, 0x7f050166, ((java.util.List) (obj)));
        bundle.setDropDownViewResource(0x7f05016a);
        recurrencepickerview.monthlyFrequencyOptions.setAdapter(bundle);
        recurrencepickerview.monthlyFrequencyOptions.setOnItemSelectedListener(recurrencepickerview);
        recurrencepickerview.weekdayLabelText = (TextView)recurrencepickerview.findViewById(0x7f10031e);
        obj2 = (LinearLayout)recurrencepickerview.findViewById(0x7f10031f);
        recurrencepickerview.weekGroupLayout = (LinearLayout)recurrencepickerview.findViewById(0x7f10031d);
        layoutinflater = LayoutInflater.from(recurrencepickerview.getContext());
        bundle = recurrencepickerview.getResources().getConfiguration().locale;
        if (android.os.Build.VERSION.SDK_INT >= 22)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        sparsearray = new SparseArray(7);
        simpledateformat = new SimpleDateFormat("EEEEE", bundle);
        gregoriancalendar = new GregorianCalendar(1970, 0, 4);
        l = 1;
        while (l <= 7) 
        {
            Object obj1 = simpledateformat.format(gregoriancalendar.getTime());
            bundle = ((Bundle) (obj1));
            if (i == 0)
            {
                if (((String) (obj1)).length() < 3)
                {
                    bundle = ((Bundle) (obj1));
                } else
                {
                    bundle = ((String) (obj1)).substring(0, 3);
                }
            }
            sparsearray.put(l, bundle);
            gregoriancalendar.add(5, 1);
            l++;
        }
        break MISSING_BLOCK_LABEL_1288;
_L2:
        i = 0x7f0b0036;
          goto _L9
_L3:
        i = 0x7f0b0034;
          goto _L9
_L4:
        i = 0x7f0b0038;
          goto _L9
_L5:
        i = 0x7f0b0039;
          goto _L9
_L6:
        i = 0x7f0b0037;
          goto _L9
_L7:
        i = 0x7f0b0033;
          goto _L9
        i = 0x7f0b0035;
          goto _L9
        for (int j = 0; j < 7; j++)
        {
            if (((LinearLayout) (obj2)).getChildCount() < 7)
            {
                layoutinflater.inflate(0x7f050185, ((android.view.ViewGroup) (obj2)));
            }
            int k1 = recurrencepickerview.state.getFirstDayOfWeek().intValue() + j;
            int i1 = k1;
            if (k1 > 7)
            {
                i1 = k1 - 7;
            }
            bundle = (String)sparsearray.get(i1);
            recurrencepickerview.weekByDayButtons[j] = (ToggleButton)((LinearLayout) (obj2)).getChildAt(j);
            recurrencepickerview.weekByDayButtons[j].setTextOff(bundle);
            recurrencepickerview.weekByDayButtons[j].setTextOn(bundle);
            recurrencepickerview.weekByDayButtons[j].setText(bundle);
            bundle = recurrencepickerview.weekByDayButtons[j];
            String as[] = recurrencepickerview.weekDayNames;
            k1 = recurrencepickerview.state.getFirstDayOfWeek().intValue() + j;
            i1 = k1;
            if (k1 > 7)
            {
                i1 = k1 - 7;
            }
            bundle.setContentDescription(as[i1]);
            if (android.os.Build.VERSION.SDK_INT >= 22)
            {
                recurrencepickerview.weekByDayButtons[j].setAllCaps(false);
            }
            recurrencepickerview.weekByDayButtons[j].setOnCheckedChangeListener(recurrencepickerview);
        }

        recurrencepickerview.dateDurationText = (TextView)recurrencepickerview.findViewById(0x7f100325);
        recurrencepickerview.dateDurationText.setOnClickListener(recurrencepickerview);
        recurrencepickerview.dateDurationText.setOnFocusChangeListener(recurrencepickerview);
        recurrencepickerview.postDateText = (TextView)recurrencepickerview.findViewById(0x7f100326);
        bundle = recurrencepickerview.getResources().getString(0x7f13014f);
        int k = bundle.indexOf("%s");
        if (k != -1)
        {
            recurrencepickerview.mDateDurationRadio.setText(bundle.substring(0, k).trim());
            recurrencepickerview.postDateText.setText(bundle.substring(k + 2, bundle.length()).trim());
            recurrencepickerview.postDateText.setOnClickListener(recurrencepickerview);
        }
        recurrencepickerview.refresh(recurrencepickerview.state);
        setTitle(0x7f1303da);
        return;
    }

    protected void onRestoreInstanceState(Bundle bundle)
    {
        super.onRestoreInstanceState(bundle);
        RecurrencePickerView recurrencepickerview = view;
        if (bundle != null)
        {
            bundle = (RecurrencePickerState)bundle.getParcelable("bundle_state");
            if (bundle != null)
            {
                recurrencepickerview.refresh(bundle);
            }
        }
    }

    protected void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("bundle_state", view.state);
    }

}
