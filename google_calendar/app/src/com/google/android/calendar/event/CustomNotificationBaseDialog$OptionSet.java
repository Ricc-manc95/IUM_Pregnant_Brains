// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.calendar.event:
//            CustomNotificationBaseDialog

abstract class <init>
    implements android.view.BaseDialog.OptionSet
{

    public int selectedIndex;
    private View selectedView;
    public final CustomNotificationBaseDialog this$0;
    public List viewList;

    public final void add(ViewGroup viewgroup)
    {
        viewgroup.setTag(new Holder(CustomNotificationBaseDialog.this, viewgroup));
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
            Holder holder = (Holder)viewgroup.getTag();
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
            obj = holder.textView;
            if (flag)
            {
                j = selectedTextColor;
            } else
            {
                j = defaultTextColor;
            }
            ((TextView) (obj)).setTextColor(j);
            obj = holder.check;
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
            holder.textView.setText(getViewText(view.getId(), i, flag));
            i++;
        }
        onItemSelected();
        scrollView.requestLayout();
    }

    private Holder()
    {
        this$0 = CustomNotificationBaseDialog.this;
        super();
        viewList = new ArrayList();
        selectedIndex = -1;
    }

    selectedIndex(byte byte0)
    {
        this();
    }
}
