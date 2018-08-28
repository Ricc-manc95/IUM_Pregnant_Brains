// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.datetimepicker.date;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.StateListDrawable;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

// Referenced classes of package com.android.datetimepicker.date:
//            DatePickerController, TextViewWithCircularIndicator

public final class YearPickerView extends ListView
    implements android.widget.AdapterView.OnItemClickListener, DatePickerDialog.OnDateChangedListener
{

    private YearAdapter adapter;
    private int childSize;
    public final DatePickerController controller;
    public TextViewWithCircularIndicator selectedView;
    private int viewSize;

    public YearPickerView(Context context, DatePickerController datepickercontroller)
    {
        super(context);
        controller = datepickercontroller;
        controller.registerOnDateChangedListener(this);
        setLayoutParams(new android.view.ViewGroup.LayoutParams(-1, -2));
        datepickercontroller = context.getResources();
        viewSize = datepickercontroller.getDimensionPixelOffset(0x7f0e00d2);
        childSize = datepickercontroller.getDimensionPixelOffset(0x7f0e0439);
        setVerticalFadingEdgeEnabled(true);
        setFadingEdgeLength(childSize / 3);
        datepickercontroller = new ArrayList();
        for (int i = controller.getMinYear(); i <= controller.getMaxYear(); i++)
        {
            datepickercontroller.add(String.format("%d", new Object[] {
                Integer.valueOf(i)
            }));
        }

        adapter = new YearAdapter(context, 0x7f0501ba, datepickercontroller);
        setAdapter(adapter);
        setOnItemClickListener(this);
        setSelector(new StateListDrawable());
        setDividerHeight(0);
        onDateChanged();
    }

    public final void onDateChanged()
    {
        adapter.notifyDataSetChanged();
        post(new _cls1());
    }

    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        super.onInitializeAccessibilityEvent(accessibilityevent);
        if (accessibilityevent.getEventType() == 4096)
        {
            accessibilityevent.setFromIndex(0);
            accessibilityevent.setToIndex(0);
        }
    }

    public final void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        controller.tryVibrate();
        adapterview = (TextViewWithCircularIndicator)view;
        if (adapterview != null)
        {
            if (adapterview != selectedView)
            {
                if (selectedView != null)
                {
                    selectedView.drawCircle = false;
                    selectedView.requestLayout();
                }
                adapterview.drawCircle = true;
                adapterview.requestLayout();
                selectedView = adapterview;
            }
            controller.onYearSelected(Integer.valueOf(adapterview.getText().toString()).intValue());
            adapter.notifyDataSetChanged();
        }
    }

    private class YearAdapter extends ArrayAdapter
    {

        private final YearPickerView this$0;

        public final View getView(int i, View view, ViewGroup viewgroup)
        {
            view = (TextViewWithCircularIndicator)super.getView(i, view, viewgroup);
            view.requestLayout();
            i = Integer.valueOf(view.getText().toString()).intValue();
            boolean flag;
            if (controller.getSelectedDay().year == i)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            view.drawCircle = flag;
            if (flag)
            {
                selectedView = view;
            }
            return view;
        }

        public YearAdapter(Context context, int i, List list)
        {
            this$0 = YearPickerView.this;
            super(context, i, list);
        }
    }


    private class _cls1
        implements Runnable
    {

        private final YearPickerView this$0;
        private final int val$offset;
        private final int val$position;

        public final void run()
        {
            setSelectionFromTop(position, offset);
            requestLayout();
        }

        _cls1()
        {
            this$0 = YearPickerView.this;
            position = i;
            offset = j;
            super();
        }
    }

}
