// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import com.google.android.apps.calendar.timeline.alternate.view.api.ItemAdapter;
import com.google.common.collect.ComparisonChain;
import java.util.Comparator;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            EventComparators

public final class arg._cls1
    implements Comparator
{

    private final ItemAdapter arg$1;

    public final int compare(Object obj, Object obj1)
    {
        ItemAdapter itemadapter = arg$1;
        int j = EventComparators.compareTime(itemadapter, obj, obj1);
        int i = j;
        if (j == 0)
        {
            i = itemadapter.compareType(obj, obj1);
        }
        j = i;
        if (i == 0)
        {
            j = ComparisonChain.ACTIVE.compare(itemadapter.getKey(obj).hashCode(), itemadapter.getKey(obj1).hashCode()).result();
        }
        return j;
    }

    public (ItemAdapter itemadapter)
    {
        arg$1 = itemadapter;
    }
}
