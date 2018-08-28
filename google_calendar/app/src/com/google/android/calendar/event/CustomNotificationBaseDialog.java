// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.Utils;
import com.google.android.calendar.material.Material;
import com.google.android.calendar.time.Time;
import java.util.ArrayList;
import java.util.List;

public final class CustomNotificationBaseDialog
    implements TextWatcher, com.android.datetimepicker.time.TimePickerCompat.OnTimeSetListener
{

    public AlertDialog alertDialog;
    private boolean allowNotificationsAfterEvent;
    private String atTime;
    private Context context;
    public int count;
    public int defaultTextColor;
    public final TimePickerShowingDialog dialog;
    private TextView doneButton;
    public boolean doneButtonPressed;
    public EditText editText;
    private TextView errorTextView;
    private boolean isAllDay;
    public CustomNotificationDialog.OnNotificationSetListener listener;
    public ArrayList methodLabels;
    public OptionSet methodOptions;
    public List methodValues;
    public Resources resources;
    public Typeface robotoMedium;
    public ScrollView scrollView;
    public int selectedTextColor;
    public Time time;
    private TextView timePickerButton;
    private List unitMaxValues;
    public OptionSet unitOptions;
    private List unitValues;

    CustomNotificationBaseDialog(TimePickerShowingDialog timepickershowingdialog)
    {
        methodValues = new ArrayList();
        methodLabels = new ArrayList();
        unitMaxValues = new ArrayList();
        unitValues = new ArrayList();
        doneButtonPressed = false;
        unitOptions = new _cls1();
        methodOptions = new _cls2();
        dialog = timepickershowingdialog;
    }

    private final void setTime()
    {
        Time time1 = time;
        time1.writeFieldsToImpl();
        long l = time1.impl.toMillis(false);
        char c = '\u1401';
        if (DateFormat.is24HourFormat(context))
        {
            c = '\u1481';
        }
        timePickerButton.setText(String.format(atTime, new Object[] {
            DateUtils.formatDateTime(context, l, c)
        }));
    }

    private final void validateNotificationTime()
    {
        if (!TextUtils.isEmpty(editText.getText().toString()))
        {
            int i = editText.getPaddingTop();
            int j = editText.getPaddingBottom();
            if (!allowNotificationsAfterEvent && getMinutes() < 0)
            {
                errorTextView.setVisibility(0);
                editText.setBackgroundResource(0x7f020105);
                doneButton.setEnabled(false);
            } else
            {
                errorTextView.setVisibility(8);
                editText.setBackground(null);
                editText.setBackgroundResource(0x7f020106);
                ViewCompat.setBackgroundTintList(editText, ContextCompat.getColorStateList(context, 0x7f0d01d7));
                doneButton.setEnabled(true);
            }
            editText.setPadding(0, i, 0, j);
        }
    }

    public final void afterTextChanged(Editable editable)
    {
        validateIntervalText(editable.toString());
    }

    public final void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
    {
    }

    final int getMinutes()
    {
        int i = Integer.parseInt(editText.getText().toString());
        int j = ((Integer)unitValues.get(unitOptions.selectedIndex)).intValue() * i;
        i = j;
        if (isAllDay)
        {
            i = j - (time.hour * 60 + time.minute);
        }
        return i;
    }

    final Dialog onCreateDialog$51662RJ4E9NMIP1FC5O70BQ1CDQ6ITJ9EHSJMJ31DPI74RR9CGNMUSPF89QMSP3CCKTKOOBECHP6UQB45TNN6BQ2ELN68R357D662RJ4E9NMIP1FC5O70BQ4D5GMORR77CKKOOBECHP6UQB45TGN0S1F8HKM2R3FCSTG____0(Activity activity, Bundle bundle, Bundle bundle1)
    {
        context = activity;
        resources = activity.getResources();
        activity.getWindow().setSoftInputMode(5);
        class .Lambda._cls0
            implements android.view.View.OnClickListener
        {

            private final CustomNotificationBaseDialog arg$1;

            public final void onClick(View view1)
            {
                view1 = arg$1;
                view1.doneButtonPressed = true;
                if (((CustomNotificationBaseDialog) (view1)).alertDialog != null)
                {
                    ((CustomNotificationBaseDialog) (view1)).alertDialog.hide();
                    ((CustomNotificationBaseDialog) (view1)).alertDialog.dismiss();
                }
            }

            .Lambda._cls0()
            {
                arg$1 = CustomNotificationBaseDialog.this;
            }
        }

        class .Lambda._cls1
            implements android.view.View.OnClickListener
        {

            private final CustomNotificationBaseDialog arg$1;

            public final void onClick(View view1)
            {
                view1 = arg$1;
                ((CustomNotificationBaseDialog) (view1)).dialog.showTimePicker(((CustomNotificationBaseDialog) (view1)).time);
            }

            .Lambda._cls1()
            {
                arg$1 = CustomNotificationBaseDialog.this;
            }

            private class TimePickerShowingDialog
            {

                public abstract void showTimePicker(Time time1);
            }

        }

        Object obj;
        View view;
        String as[];
        Object obj1;
        List list;
        int ai[];
        int i;
        int j;
        int k;
        int l;
        int i1;
        long l1;
        if (bundle1 != null)
        {
            l1 = bundle1.getLong("atTime");
            i = bundle1.getInt("selectedUnitsIndex");
            j = bundle1.getInt("selectedMethodIndex");
        } else
        {
            l1 = 0L;
            j = 0;
            i = 0;
        }
        view = LayoutInflater.from(activity).inflate(0x7f050038, null);
        atTime = resources.getString(0x7f1300c0);
        isAllDay = bundle.getBoolean("all_day", false);
        if (Material.robotoMedium != null)
        {
            obj = Material.robotoMedium;
        } else
        {
            obj = Typeface.create("sans-serif-medium", 0);
            Material.robotoMedium = ((Typeface) (obj));
        }
        robotoMedium = ((Typeface) (obj));
        ((TextView)view.findViewById(0x7f100047)).setTypeface(robotoMedium);
        doneButton = (TextView)view.findViewById(0x7f100156);
        doneButton.setOnClickListener(new .Lambda._cls0());
        doneButton.setTypeface(robotoMedium);
        scrollView = (ScrollView)view.findViewById(0x7f100149);
        errorTextView = (TextView)view.findViewById(0x7f10014b);
        editText = (EditText)view.findViewById(0x7f10014a);
        editText.addTextChangedListener(this);
        editText.setTypeface(robotoMedium);
        timePickerButton = (TextView)view.findViewById(0x7f100151);
        timePickerButton.setOnClickListener(new .Lambda._cls1());
        timePickerButton.setTypeface(robotoMedium);
        defaultTextColor = resources.getColor(0x7f0d00aa);
        selectedTextColor = resources.getColor(0x7f0d01d7);
        methodValues = Utils.loadIntegerArray(resources, 0x7f0b004c);
        obj1 = Utils.loadStringArray(resources, 0x7f0b004b);
        obj = bundle.getString("allowed_reminders");
        if (obj == null) goto _L2; else goto _L1
_L1:
        list = methodValues;
        as = ((String) (obj)).split(",");
        ai = new int[as.length];
        k = 0;
_L4:
        if (k >= ai.length)
        {
            break; /* Loop/switch isn't completed */
        }
        ai[k] = Integer.parseInt(as[k], 10);
        k++;
        if (true) goto _L4; else goto _L3
        obj1;
        LogUtils.w("ReminderUtils", "Bad allowed-strings list: '%s' in '%s'", new Object[] {
            as[k], obj
        });
_L2:
        if (methodValues.contains(Integer.valueOf(1)))
        {
            methodOptions.add((ViewGroup)view.findViewById(0x7f100154));
            methodLabels.add(resources.getString(0x7f1300b8));
        }
        if (methodValues.contains(Integer.valueOf(2)))
        {
            methodOptions.add((ViewGroup)view.findViewById(0x7f100155));
            methodLabels.add(resources.getString(0x7f1300b7));
        }
        if (methodOptions.viewList.size() > 0)
        {
            obj = methodOptions;
            if (((OptionSet) (obj)).selectedIndex != j)
            {
                ((OptionSet) (obj)).setSelected((View)((OptionSet) (obj)).viewList.get(j));
            }
        }
        if (methodOptions.viewList.size() < 2)
        {
            view.findViewById(0x7f100153).setVisibility(8);
            if (isAllDay)
            {
                view.findViewById(0x7f100152).setVisibility(8);
            } else
            {
                view.findViewById(0x7f100150).setVisibility(8);
            }
        }
        unitValues = Utils.loadIntegerArray(resources, 0x7f0b001c);
        unitMaxValues = Utils.loadIntegerArray(resources, 0x7f0b001b);
        time = new Time();
        if (isAllDay)
        {
            view.findViewById(0x7f10014c).setVisibility(8);
            view.findViewById(0x7f10014d).setVisibility(8);
            unitValues.remove(0);
            unitValues.remove(0);
            unitMaxValues.remove(0);
            unitMaxValues.remove(0);
            if (l1 != 0L)
            {
                obj = time;
                ((Time) (obj)).impl.timezone = ((Time) (obj)).timezone;
                ((Time) (obj)).impl.set(l1);
                ((Time) (obj)).impl.toMillis(true);
                ((Time) (obj)).copyFieldsFromImpl();
            } else
            {
                time.hour = 9;
            }
            setTime();
        } else
        {
            timePickerButton.setVisibility(8);
            view.findViewById(0x7f100152).setVisibility(8);
            unitOptions.add((ViewGroup)view.findViewById(0x7f10014c));
            unitOptions.add((ViewGroup)view.findViewById(0x7f10014d));
        }
        unitOptions.add((ViewGroup)view.findViewById(0x7f10014e));
        unitOptions.add((ViewGroup)view.findViewById(0x7f10014f));
        obj = unitOptions;
        if (((OptionSet) (obj)).selectedIndex != i)
        {
            ((OptionSet) (obj)).setSelected((View)((OptionSet) (obj)).viewList.get(i));
        }
        if (bundle1 == null)
        {
            editText.setText(Integer.valueOf(10).toString());
        }
        allowNotificationsAfterEvent = bundle.getBoolean("allow_notifications_after_event");
        alertDialog = (new android.app.AlertDialog.Builder(activity)).setView(view).create();
        alertDialog.setCanceledOnTouchOutside(true);
        return alertDialog;
_L3:
        k = list.size() - 1;
        while (k >= 0) 
        {
            i1 = ((Integer)list.get(k)).intValue();
            for (l = ai.length - 1; l >= 0 && i1 != ai[l]; l--) { }
            if (l < 0)
            {
                list.remove(k);
                ((ArrayList) (obj1)).remove(k);
            }
            k--;
        }
          goto _L2
    }

    public final void onTextChanged(CharSequence charsequence, int i, int j, int k)
    {
    }

    public final void onTimeSet(int i, int j)
    {
        time.hour = i;
        time.minute = j;
        setTime();
        validateNotificationTime();
    }

    final void validateIntervalText(String s)
    {
        int i;
        int k;
        i = ((Integer)unitMaxValues.get(unitOptions.selectedIndex)).intValue();
        k = Integer.parseInt(s);
        if (k <= i) goto _L2; else goto _L1
_L1:
        editText.setText(Integer.valueOf(i).toString());
        editText.announceForAccessibility(resources.getString(0x7f13014c, new Object[] {
            editText.getText()
        }));
        doneButton.setEnabled(true);
_L3:
        count = Integer.valueOf(editText.getText().toString()).intValue();
_L4:
        s = unitOptions;
        int j = 0;
        while (j < ((OptionSet) (s)).viewList.size()) 
        {
            View view = (View)((OptionSet) (s)).viewList.get(j);
            TextView textview = ((OptionViewHolder)view.getTag()).textView;
            k = view.getId();
            boolean flag;
            if (((OptionSet) (s)).selectedIndex == j)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            textview.setText(s.getViewText(k, j, flag));
            j++;
        }
        break MISSING_BLOCK_LABEL_260;
_L2:
        if (k >= 0)
        {
            break MISSING_BLOCK_LABEL_247;
        }
        editText.setText(Integer.valueOf(1).toString());
        doneButton.setEnabled(true);
          goto _L3
        s;
        count = 0;
        doneButton.setEnabled(false);
          goto _L4
        validateNotificationTime();
          goto _L3
        ((OptionSet) (s))._fld0.scrollView.requestLayout();
        return;
    }

    private class _cls1 extends OptionSet
    {
        private class OptionSet
            implements android.view.View.OnClickListener
        {

            public int selectedIndex;
            private View selectedView;
            public final CustomNotificationBaseDialog this$0;
            public List viewList;

            public final void add(ViewGroup viewgroup)
            {
                viewgroup.setTag(new OptionViewHolder(viewgroup));
                viewgroup.setOnClickListener(this);
                viewList.add(viewgroup);
            }

            protected abstract String getViewText(int i, int j, boolean flag);

            public void onClick(View view)
            {
                if (view != selectedView)
                {
                    setSelected(view);
                }
            }

            protected void onItemSelected()
            {
            }

            final void setSelected(View view)
            {
                Iterator iterator = viewList.iterator();
                int i = 0;
                while (iterator.hasNext()) 
                {
                    ViewGroup viewgroup = (ViewGroup)iterator.next();
                    OptionViewHolder optionviewholder = (OptionViewHolder)viewgroup.getTag();
                    Object obj;
                    int j;
                    boolean flag;
                    if (viewgroup == view)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    obj = optionviewholder.textView;
                    if (flag)
                    {
                        j = selectedTextColor;
                    } else
                    {
                        j = defaultTextColor;
                    }
                    ((TextView) (obj)).setTextColor(j);
                    obj = optionviewholder.check;
                    if (flag)
                    {
                        j = 0;
                    } else
                    {
                        j = 8;
                    }
                    ((ImageView) (obj)).setVisibility(j);
                    if (flag)
                    {
                        selectedIndex = i;
                        selectedView = viewgroup;
                    }
                    optionviewholder.textView.setText(getViewText(view.getId(), i, flag));
                    i++;
                }
                onItemSelected();
                scrollView.requestLayout();
            }

            private OptionSet()
            {
                this$0 = CustomNotificationBaseDialog.this;
                super();
                viewList = new ArrayList();
                selectedIndex = -1;
            }

            OptionSet(byte byte0)
            {
                this();
            }
        }


        private final CustomNotificationBaseDialog this$0;

        protected final String getViewText(int i, int j, boolean flag)
        {
            if (flag)
            {
                if (i == 0x7f10014c)
                {
                    return resources.getQuantityString(0x7f120029, count);
                }
                if (i == 0x7f10014d)
                {
                    return resources.getQuantityString(0x7f120023, count);
                }
                if (i == 0x7f10014e)
                {
                    return resources.getQuantityString(0x7f120010, count);
                }
                if (i == 0x7f10014f)
                {
                    return resources.getQuantityString(0x7f12004f, count);
                }
            } else
            {
                if (i == 0x7f10014c)
                {
                    return resources.getQuantityString(0x7f120028, count);
                }
                if (i == 0x7f10014d)
                {
                    return resources.getQuantityString(0x7f120022, count);
                }
                if (i == 0x7f10014e)
                {
                    return resources.getQuantityString(0x7f12000f, count);
                }
                if (i == 0x7f10014f)
                {
                    return resources.getQuantityString(0x7f12004e, count);
                }
            }
            return "";
        }

        protected final void onItemSelected()
        {
            validateIntervalText(editText.getText().toString());
        }

        _cls1()
        {
            this$0 = CustomNotificationBaseDialog.this;
            super((byte)0);
        }
    }


    private class _cls2 extends OptionSet
    {

        private final CustomNotificationBaseDialog this$0;

        protected final String getViewText(int i, int j, boolean flag)
        {
            return (String)methodLabels.get(j);
        }

        _cls2()
        {
            this$0 = CustomNotificationBaseDialog.this;
            super((byte)0);
        }
    }


    private class OptionViewHolder
    {

        public ImageView check;
        public TextView textView;

        public OptionViewHolder(View view)
        {
            textView = (TextView)view.findViewById(0x7f100042);
            textView.setText(" ");
            textView.setTypeface(robotoMedium);
            check = (ImageView)view.findViewById(0x7f100148);
        }
    }

}
