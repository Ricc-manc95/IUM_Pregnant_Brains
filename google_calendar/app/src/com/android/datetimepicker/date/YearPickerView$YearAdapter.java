// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.datetimepicker.date;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

// Referenced classes of package com.android.datetimepicker.date:
//            TextViewWithCircularIndicator, YearPickerView, DatePickerController

final class this._cls0 extends ArrayAdapter
{

    private final YearPickerView this$0;

    public final View getView(int i, View view, ViewGroup viewgroup)
    {
        view = (TextViewWithCircularIndicator)super.getView(i, view, viewgroup);
        view.requestLayout();
        i = Integer.valueOf(view.getText().toString()).intValue();
        boolean flag;
        if (controller.getSelectedDay().ar == i)
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

    public or(Context context, int i, List list)
    {
        this$0 = YearPickerView.this;
        super(context, i, list);
    }
}
