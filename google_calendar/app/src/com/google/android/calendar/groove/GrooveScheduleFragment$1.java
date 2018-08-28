// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

// Referenced classes of package com.google.android.calendar.groove:
//            GrooveScheduleFragment

final class this._cls0 extends PagerAdapter
{

    private final GrooveScheduleFragment this$0;

    public final void destroyItem(ViewGroup viewgroup, int i, Object obj)
    {
        viewgroup.removeView((View)obj);
    }

    public final int getCount()
    {
        return 5;
    }

    public final int getItemPosition(Object obj)
    {
        int i = 0;
_L11:
        if (i >= screenList.size()) goto _L2; else goto _L1
_L1:
        Object obj1;
        obj1 = GrooveScheduleFragment.this;
        GrooveScheduleFragment grooveschedulefragment = GrooveScheduleFragment.this;
        int j;
        if (i >= grooveschedulefragment.screenList.size())
        {
            j = -1;
        } else
        {
            j = ((Integer)grooveschedulefragment.screenList.get(i)).intValue();
        }
        j;
        JVM INSTR tableswitch 0 4: default 80
    //                   0 110
    //                   1 118
    //                   2 126
    //                   3 134
    //                   4 142;
           goto _L3 _L4 _L5 _L6 _L7 _L8
_L3:
        obj1 = null;
_L10:
        if (obj == obj1)
        {
            return i;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        obj1 = ((GrooveScheduleFragment) (obj1)).frequencyView;
        continue; /* Loop/switch isn't completed */
_L5:
        obj1 = ((GrooveScheduleFragment) (obj1)).durationView;
        continue; /* Loop/switch isn't completed */
_L6:
        obj1 = ((GrooveScheduleFragment) (obj1)).preferredTimesView;
        continue; /* Loop/switch isn't completed */
_L7:
        obj1 = ((GrooveScheduleFragment) (obj1)).belongIntegrationView;
        continue; /* Loop/switch isn't completed */
_L8:
        obj1 = ((GrooveScheduleFragment) (obj1)).summaryView;
        if (true) goto _L10; else goto _L9
_L9:
        i++;
          goto _L11
_L2:
        return -2;
    }

    public final Object instantiateItem(ViewGroup viewgroup, int i)
    {
        Object obj;
        obj = GrooveScheduleFragment.this;
        GrooveScheduleFragment grooveschedulefragment = GrooveScheduleFragment.this;
        if (i >= grooveschedulefragment.screenList.size())
        {
            i = -1;
        } else
        {
            i = ((Integer)grooveschedulefragment.screenList.get(i)).intValue();
        }
        i;
        JVM INSTR tableswitch 0 4: default 60
    //                   0 99
    //                   1 107
    //                   2 115
    //                   3 123
    //                   4 131;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        obj = null;
_L8:
        if (obj != null && ((View) (obj)).getParent() == null)
        {
            viewgroup.addView(((View) (obj)));
        }
        return obj;
_L2:
        obj = ((GrooveScheduleFragment) (obj)).frequencyView;
        continue; /* Loop/switch isn't completed */
_L3:
        obj = ((GrooveScheduleFragment) (obj)).durationView;
        continue; /* Loop/switch isn't completed */
_L4:
        obj = ((GrooveScheduleFragment) (obj)).preferredTimesView;
        continue; /* Loop/switch isn't completed */
_L5:
        obj = ((GrooveScheduleFragment) (obj)).belongIntegrationView;
        continue; /* Loop/switch isn't completed */
_L6:
        obj = ((GrooveScheduleFragment) (obj)).summaryView;
        if (true) goto _L8; else goto _L7
_L7:
    }

    public final boolean isViewFromObject(View view, Object obj)
    {
        return view == obj;
    }

    A()
    {
        this$0 = GrooveScheduleFragment.this;
        super();
    }
}
