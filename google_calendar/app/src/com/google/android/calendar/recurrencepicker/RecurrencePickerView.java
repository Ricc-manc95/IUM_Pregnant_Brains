// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.recurrencepicker;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.res.Resources;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableSet;
import java.text.DateFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

// Referenced classes of package com.google.android.calendar.recurrencepicker:
//            RecurrencePickerState

public class RecurrencePickerView extends ScrollView
    implements android.app.DatePickerDialog.OnDateSetListener, android.view.View.OnClickListener, android.view.View.OnFocusChangeListener, android.widget.AdapterView.OnItemSelectedListener, android.widget.CompoundButton.OnCheckedChangeListener
{
    static class MinMaxTextWatcher
        implements TextWatcher
    {

        private int mDefault;
        private int max;
        private int min;

        public void afterTextChanged(Editable editable)
        {
            int i;
            int j;
            int k;
            try
            {
                i = Integer.parseInt(editable.toString());
            }
            catch (NumberFormatException numberformatexception)
            {
                i = mDefault;
            }
            k = Math.min(max, Math.max(min, i));
            j = i;
            if (k != i)
            {
                editable.clear();
                editable.append(String.format("%d", new Object[] {
                    Integer.valueOf(k)
                }));
                j = k;
            }
            onChange(j);
        }

        public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
        {
        }

        void onChange(int i)
        {
        }

        public void onTextChanged(CharSequence charsequence, int i, int j, int k)
        {
        }

        MinMaxTextWatcher(int i, int j, int k)
        {
            min = i;
            max = k;
            mDefault = j;
        }
    }


    public EditText countDuration;
    public TextView countDurationPostTextView;
    public TextView dateDurationText;
    public TextView endOptionsLabel;
    public FrameLayout firstFrequencyHolder;
    public Spinner frequency;
    public ArrayAdapter frequencyAdapter;
    public EditText interval;
    public RadioButton mCountDurationRadio;
    public RadioButton mDateDurationRadio;
    public RadioButton mInfiniteDurationRadio;
    public Spinner monthlyFrequencyOptions;
    public TextView postDateText;
    private TextView postFrequencyText;
    private TextView postIntervalText;
    private TextView preIntervalText;
    public FrameLayout secondFrequencyHolder;
    public RecurrencePickerState state;
    public ToggleButton weekByDayButtons[];
    public String weekDayNames[];
    public LinearLayout weekGroupLayout;
    public TextView weekdayLabelText;

    public RecurrencePickerView(Context context)
    {
        super(context);
        weekByDayButtons = new ToggleButton[7];
        weekDayNames = DateFormatSymbols.getInstance().getWeekdays();
    }

    public RecurrencePickerView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        weekByDayButtons = new ToggleButton[7];
        weekDayNames = DateFormatSymbols.getInstance().getWeekdays();
    }

    public RecurrencePickerView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        weekByDayButtons = new ToggleButton[7];
        weekDayNames = DateFormatSymbols.getInstance().getWeekdays();
    }

    static int getIntervalStringResId(RecurrencePickerState.Frequency frequency1)
    {
        switch (frequency1.ordinal())
        {
        case 0: // '\0'
        default:
            return 0x7f12000d;

        case 1: // '\001'
            return 0x7f12004c;

        case 2: // '\002'
            return 0x7f12002c;

        case 3: // '\003'
            return 0x7f120006;
        }
    }

    public final List getFrequencyPluralityOptionsList(int i)
    {
        ArrayList arraylist = new ArrayList();
        arraylist.add(getResources().getQuantityString(0x7f120034, i));
        arraylist.add(getResources().getQuantityString(0x7f120036, i));
        arraylist.add(getResources().getQuantityString(0x7f120035, i));
        arraylist.add(getResources().getQuantityString(0x7f120033, i));
        return arraylist;
    }

    public void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
    {
        int i = Arrays.asList(weekByDayButtons).indexOf(compoundbutton);
        compoundbutton = state;
        int j = i + state.getFirstDayOfWeek().intValue();
        i = j;
        if (j > 7)
        {
            i = j - 7;
        }
        HashSet hashset = new HashSet(compoundbutton.getByDay());
        if (flag)
        {
            hashset.add(Integer.valueOf(i));
        } else
        {
            hashset.remove(Integer.valueOf(i));
        }
        refresh(compoundbutton.toBuilder().setByDay(ImmutableSet.copyOf(hashset)).build());
    }

    public void onClick(View view)
    {
        int i = view.getId();
        if (i != 0x7f100325) goto _L2; else goto _L1
_L1:
        view = (InputMethodManager)getContext().getSystemService("input_method");
        if (view != null && getFocusedChild() != null)
        {
            view.hideSoftInputFromWindow(getFocusedChild().getWindowToken(), 0);
        }
        view = Calendar.getInstance();
        view.setTimeInMillis(state.getUntilDateTimeMillis().longValue());
        refresh(state.toBuilder().setEnd(RecurrencePickerState.End.DATE).build());
        view = new DatePickerDialog(getContext(), this, view.get(1), view.get(2), view.get(5));
        i = state.getFirstDayOfWeek().intValue();
        view.getDatePicker().setFirstDayOfWeek(i);
        view.show();
_L4:
        return;
_L2:
        if (i != 0x7f100322)
        {
            break; /* Loop/switch isn't completed */
        }
        refresh(state.toBuilder().setEnd(RecurrencePickerState.End.INFINITE).build());
        clearFocus();
        view = (InputMethodManager)getContext().getSystemService("input_method");
        if (view != null && getFocusedChild() != null)
        {
            view.hideSoftInputFromWindow(getFocusedChild().getWindowToken(), 0);
            return;
        }
        if (true) goto _L4; else goto _L3
_L3:
        if (i != 0x7f100324 && i != 0x7f100326)
        {
            continue; /* Loop/switch isn't completed */
        }
        refresh(state.toBuilder().setEnd(RecurrencePickerState.End.DATE).build());
        dateDurationText.requestFocus();
        view = (InputMethodManager)getContext().getSystemService("input_method");
        if (view == null || getFocusedChild() == null) goto _L4; else goto _L5
_L5:
        view.hideSoftInputFromWindow(getFocusedChild().getWindowToken(), 0);
        return;
        if (i != 0x7f100328 && i != 0x7f10032a) goto _L4; else goto _L6
_L6:
        refresh(state.toBuilder().setEnd(RecurrencePickerState.End.COUNT).build());
        countDuration.requestFocus();
        return;
    }

    public void onDateSet(DatePicker datepicker, int i, int j, int k)
    {
        datepicker = Calendar.getInstance();
        datepicker.setTimeInMillis(0L);
        datepicker.set(1, i);
        datepicker.set(2, j);
        datepicker.set(5, k);
        refresh(state.toBuilder().setUntilDateTimeMillis(Long.valueOf(datepicker.getTimeInMillis())).setEnd(RecurrencePickerState.End.DATE).build());
    }

    public void onFocusChange(View view, boolean flag)
    {
        if (flag)
        {
            int i = view.getId();
            if (i == 0x7f100325)
            {
                refresh(state.toBuilder().setEnd(RecurrencePickerState.End.DATE).build());
                return;
            }
            if (i == 0x7f100329)
            {
                refresh(state.toBuilder().setEnd(RecurrencePickerState.End.COUNT).build());
                view = countDuration;
                InputMethodManager inputmethodmanager = (InputMethodManager)getContext().getSystemService("input_method");
                if (inputmethodmanager != null)
                {
                    inputmethodmanager.showSoftInput(view, 1);
                    return;
                }
            }
        }
    }

    public void onItemSelected(AdapterView adapterview, View view, int i, long l)
    {
        if (!adapterview.equals(frequency) || i == state.getFrequency().ordinal()) goto _L2; else goto _L1
_L1:
        refresh(state.toBuilder().setFrequency(RecurrencePickerState.Frequency.values()[i]).build());
_L4:
        return;
_L2:
        if (!adapterview.equals(monthlyFrequencyOptions)) goto _L4; else goto _L3
_L3:
        view = state;
        i;
        JVM INSTR tableswitch 0 2: default 92
    //                   0 122
    //                   1 142
    //                   2 166;
           goto _L5 _L6 _L7 _L8
_L5:
        throw new IndexOutOfBoundsException((new StringBuilder(29)).append("MonthFrequency at ").append(i).toString());
_L6:
        adapterview = RecurrencePickerState.MonthFrequency.MONTHDAY;
_L10:
        refresh(view.toBuilder().setMonthFrequency(adapterview).build());
        return;
_L7:
        if (view.getHasNthOption().booleanValue())
        {
            adapterview = RecurrencePickerState.MonthFrequency.WEEKDAY;
        } else
        {
            adapterview = RecurrencePickerState.MonthFrequency.LAST;
        }
        continue; /* Loop/switch isn't completed */
_L8:
        adapterview = RecurrencePickerState.MonthFrequency.LAST;
        if (true) goto _L10; else goto _L9
_L9:
    }

    public void onNothingSelected(AdapterView adapterview)
    {
    }

    final void refresh(RecurrencePickerState recurrencepickerstate)
    {
        Object obj2;
        state = recurrencepickerstate;
        obj2 = NumberFormat.getInstance(Locale.getDefault());
        interval.setText(((NumberFormat) (obj2)).format(recurrencepickerstate.getInterval()));
        recurrencepickerstate.getFrequency().ordinal();
        JVM INSTR tableswitch 0 3: default 68
    //                   0 477
    //                   1 506
    //                   2 671
    //                   3 785;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        Object obj;
        break; /* Loop/switch isn't completed */
_L2:
        weekGroupLayout.setVisibility(8);
        monthlyFrequencyOptions.setVisibility(8);
        frequency.setSelection(0);
          goto _L6
_L3:
        frequency.setSelection(1);
        obj = recurrencepickerstate.getByDay();
        i = 0;
        while (i < weekByDayButtons.length) 
        {
            int k = state.getFirstDayOfWeek().intValue() + i;
            int j = k;
            if (k > 7)
            {
                j = k - 7;
            }
            if (((ImmutableCollection) (obj)).contains(Integer.valueOf(j)))
            {
                weekByDayButtons[i].setChecked(true);
                weekByDayButtons[i].setTextColor(getResources().getColor(0x7f0d02c4));
            } else
            {
                weekByDayButtons[i].setChecked(false);
                weekByDayButtons[i].setTextColor(getResources().getColor(0x7f0d021c));
            }
            i++;
        }
        weekGroupLayout.setVisibility(0);
        monthlyFrequencyOptions.setVisibility(8);
          goto _L6
_L4:
        recurrencepickerstate.getMonthFrequency().ordinal();
        JVM INSTR tableswitch 0 2: default 704
    //                   0 732
    //                   1 749
    //                   2 760;
           goto _L7 _L8 _L9 _L10
_L7:
        weekGroupLayout.setVisibility(8);
        monthlyFrequencyOptions.setVisibility(0);
        frequency.setSelection(2);
          goto _L6
_L8:
        obj = monthlyFrequencyOptions;
        i = 0;
_L11:
        ((Spinner) (obj)).setSelection(i);
          goto _L7
_L9:
        obj = monthlyFrequencyOptions;
_L13:
        i = 1;
          goto _L11
_L10:
        Object obj1;
        obj1 = monthlyFrequencyOptions;
        obj = obj1;
        if (!recurrencepickerstate.getHasNthOption().booleanValue()) goto _L13; else goto _L12
_L12:
        obj = obj1;
        i = 2;
          goto _L11
_L5:
        weekGroupLayout.setVisibility(8);
        monthlyFrequencyOptions.setVisibility(8);
        frequency.setSelection(3);
_L6:
        obj = mInfiniteDurationRadio;
        StringBuilder stringbuilder;
        ArrayList arraylist;
        int i;
        boolean flag;
        if (recurrencepickerstate.getEnd() == RecurrencePickerState.End.INFINITE)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        ((RadioButton) (obj)).setChecked(flag);
        obj = mDateDurationRadio;
        if (recurrencepickerstate.getEnd() == RecurrencePickerState.End.DATE)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        ((RadioButton) (obj)).setChecked(flag);
        obj = mCountDurationRadio;
        if (recurrencepickerstate.getEnd() == RecurrencePickerState.End.COUNT)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        ((RadioButton) (obj)).setChecked(flag);
        countDuration.setText(((NumberFormat) (obj2)).format(recurrencepickerstate.getCount()));
        dateDurationText.setText(DateUtils.formatDateTime(getContext(), recurrencepickerstate.getUntilDateTimeMillis().longValue(), 0x20000));
        countDuration.setSelection(countDuration.getText().length());
        interval.setSelection(interval.getText().length());
        setCountDurationText(recurrencepickerstate.getCount().intValue());
        setFrequencyText();
        obj = getResources().getQuantityString(getIntervalStringResId(recurrencepickerstate.getFrequency()), recurrencepickerstate.getInterval().intValue(), new Object[] {
            recurrencepickerstate.getInterval(), frequency.getSelectedItem()
        });
        preIntervalText.setContentDescription(((CharSequence) (obj)));
        postIntervalText.setContentDescription(((CharSequence) (obj)));
        postFrequencyText.setContentDescription(((CharSequence) (obj)));
        stringbuilder = new StringBuilder(getResources().getString(0x7f1304c2));
        i = recurrencepickerstate.getFirstDayOfWeek().intValue();
        arraylist = new ArrayList(recurrencepickerstate.getByDay());
        Collections.sort(arraylist);
        if (arraylist.size() > 1)
        {
            Integer integer = (Integer)arraylist.get(0);
            obj = integer;
            do
            {
                if (((Integer) (obj)).intValue() >= i)
                {
                    break;
                }
                arraylist.add((Integer)arraylist.remove(0));
                obj2 = (Integer)arraylist.get(0);
                obj = obj2;
            } while (!integer.equals(obj2));
        }
        for (obj = arraylist.iterator(); ((Iterator) (obj)).hasNext(); stringbuilder.append(", ").append(weekDayNames[i]))
        {
            i = ((Integer)((Iterator) (obj)).next()).intValue();
        }

        weekdayLabelText.setContentDescription(stringbuilder);
        obj = getResources().getString(0x7f13030e);
        mInfiniteDurationRadio.setContentDescription(((CharSequence) (obj)));
        obj1 = getResources().getString(0x7f13014e, new Object[] {
            DateUtils.formatDateTime(getContext(), recurrencepickerstate.getUntilDateTimeMillis().longValue(), 0x20000)
        });
        mDateDurationRadio.setContentDescription(((CharSequence) (obj1)));
        dateDurationText.setContentDescription(((CharSequence) (obj1)));
        postDateText.setContentDescription(((CharSequence) (obj1)));
        dateDurationText.setContentDescription(DateUtils.formatDateTime(getContext(), recurrencepickerstate.getUntilDateTimeMillis().longValue(), 0x20000));
        obj2 = getResources().getQuantityString(0x7f12000a, recurrencepickerstate.getCount().intValue(), new Object[] {
            recurrencepickerstate.getCount()
        });
        mCountDurationRadio.setContentDescription(((CharSequence) (obj2)));
        countDurationPostTextView.setContentDescription(((CharSequence) (obj2)));
        countDuration.setContentDescription(recurrencepickerstate.getCount().toString());
        recurrencepickerstate.getEnd().ordinal();
        JVM INSTR tableswitch 0 2: default 1040
    //                   0 1051
    //                   1 1060
    //                   2 1065;
           goto _L14 _L15 _L16 _L17
_L15:
        break; /* Loop/switch isn't completed */
_L14:
        obj = String.valueOf(endOptionsLabel.getText());
_L19:
        endOptionsLabel.setContentDescription(((CharSequence) (obj)));
        return;
_L16:
        obj = obj1;
        continue; /* Loop/switch isn't completed */
_L17:
        obj = obj2;
        if (true) goto _L19; else goto _L18
_L18:
    }

    final void setCountDurationText(int i)
    {
        String s = getResources().getQuantityString(0x7f12000b, i);
        i = s.indexOf("%d");
        if (i != -1)
        {
            mCountDurationRadio.setText(s.substring(0, i).trim());
            countDurationPostTextView.setText(s.substring(i + 2, s.length()).trim());
        }
        countDurationPostTextView.setOnClickListener(this);
    }

    final void setFrequencyText()
    {
        String s2 = getResources().getQuantityString(getIntervalStringResId(state.getFrequency()), state.getInterval().intValue());
        preIntervalText = (TextView)findViewById(0x7f100317);
        postIntervalText = (TextView)findViewById(0x7f100319);
        postFrequencyText = (TextView)findViewById(0x7f10031b);
        String s = "%1$d";
        String s1 = "%2$s";
        int i = s2.indexOf("%1$d");
        int k = s2.indexOf("%2$s");
        int j;
        if (k < i)
        {
            j = k;
        } else
        {
            j = i;
            i = k;
            s1 = "%1$d";
            s = "%2$s";
        }
        if (j != -1 && i != -1)
        {
            k = s1.length();
            postIntervalText.setText(s2.substring(k + j, i).trim());
            preIntervalText.setText(s2.substring(0, j).trim());
            j = s.length();
            postFrequencyText.setText(s2.substring(i + j, s2.length()).trim());
            postIntervalText.setOnClickListener(this);
        }
    }
}
