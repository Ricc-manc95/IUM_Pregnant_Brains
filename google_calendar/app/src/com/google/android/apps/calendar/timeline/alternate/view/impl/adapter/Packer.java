// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

abstract class Packer
{

    Packer()
    {
    }

    private final void assignSlots(List list)
    {
        for (int i = 0; i < list.size(); i++)
        {
            Object obj;
            for (Iterator iterator = ((List)list.get(i)).iterator(); iterator.hasNext(); assignSlot$5166KOBMC4NMOOBECSNKUOJACLHN8EQ9954IILG_0(obj, i))
            {
                obj = iterator.next();
                int k = 1;
                for (int j = i + 1; j < list.size() && !hasIntersectingEntries((List)list.get(j), obj); j++)
                {
                    k++;
                }

                list.size();
            }

        }

    }

    private final boolean hasIntersectingEntries(List list, Object obj)
    {
        for (list = list.iterator(); list.hasNext();)
        {
            Object obj1 = list.next();
            boolean flag;
            if (getStart(obj) < getEnd(obj1) && getStart(obj1) < getEnd(obj))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                return true;
            }
        }

        return false;
    }

    protected abstract void assignSlot$5166KOBMC4NMOOBECSNKUOJACLHN8EQ9954IILG_0(Object obj, int i);

    protected abstract long getEnd(Object obj);

    protected abstract long getStart(Object obj);

    public final void layoutEntries(Collection collection, Comparator comparator)
    {
        int i;
        int j;
        long l;
        ArrayList arraylist = new ArrayList(collection);
        Collections.sort(arraylist, comparator);
        collection = new ArrayList();
        l = 0x8000000000000000L;
        comparator = (ArrayList)arraylist;
        j = comparator.size();
        i = 0;
_L9:
        if (i >= j) goto _L2; else goto _L1
_L1:
        Object obj;
        Iterator iterator;
        long l1;
        obj = comparator.get(i);
        l1 = l;
        if (getStart(obj) >= l)
        {
            assignSlots(collection);
            collection.clear();
            l1 = 0x8000000000000000L;
        }
        iterator = collection.iterator();
_L6:
        if (!iterator.hasNext()) goto _L4; else goto _L3
_L3:
        List list = (List)iterator.next();
        if (hasIntersectingEntries(list, obj)) goto _L6; else goto _L5
_L5:
        boolean flag;
        list.add(obj);
        flag = true;
_L7:
        if (!flag)
        {
            ArrayList arraylist1 = new ArrayList(1);
            arraylist1.add(obj);
            collection.add(arraylist1);
        }
        l = Math.max(l1, getEnd(obj));
        i++;
        continue; /* Loop/switch isn't completed */
_L4:
        flag = false;
        if (true) goto _L7; else goto _L2
_L2:
        assignSlots(collection);
        return;
        if (true) goto _L9; else goto _L8
_L8:
    }
}
