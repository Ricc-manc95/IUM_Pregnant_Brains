// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.search;

import com.google.android.calendar.timely.TimelineItem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class items
    implements com.google.android.calendar.timely.items
{

    public List items;

    public final void addTimelineItem(TimelineItem timelineitem)
    {
        items.add(timelineitem);
    }

    I(int i)
    {
        items = new ArrayList(i);
    }

    items(items items1, items items2)
    {
        int j = 0;
        super();
        int i;
        if (items1 != null)
        {
            i = items1.items.size();
        } else
        {
            i = 0;
        }
        if (items2 != null)
        {
            j = items2.items.size();
        }
        i = j + i;
        if (i == 0)
        {
            items = null;
        } else
        {
            items = new ArrayList(i);
            if (items1 != null)
            {
                items.addAll(items1.items);
            }
            if (items2 != null)
            {
                items.addAll(items2.items);
                return;
            }
        }
    }

    public items(Collection collection)
    {
        items = new ArrayList(collection);
    }
}
