// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.contract;

import android.content.res.Resources;
import android.support.v4.util.Pair;
import com.google.android.calendar.event.ReminderUtils;
import com.google.android.calendar.newapi.segment.common.IntegerChoiceCreator;
import java.util.ArrayList;

public final class DurationChoiceCreator extends IntegerChoiceCreator
{

    private final Resources resources;

    public DurationChoiceCreator(Resources resources1)
    {
        resources = resources1;
    }

    protected final Pair createFooter()
    {
        return new Pair(resources.getString(0x7f130149), Integer.valueOf(-1));
    }

    protected final String createLabel(Object obj)
    {
        obj = (Integer)obj;
        return ReminderUtils.constructTimeIntervalString(resources, ((Integer) (obj)).intValue());
    }

    public final com.google.android.calendar.newapi.segment.common.ChoiceCreator.ChoiceList createList(int i, int j)
    {
        Object obj = resources;
        int ai[];
        ArrayList arraylist;
        int k;
        if (i == 3)
        {
            i = 0x7f0b0032;
        } else
        {
            i = 0x7f0b0059;
        }
        ai = ((Resources) (obj)).getIntArray(i);
        arraylist = new ArrayList(ai.length);
        k = ai.length;
        for (i = 0; i < k; i++)
        {
            arraylist.add(Integer.valueOf(ai[i]));
        }

        if (!arraylist.contains(Integer.valueOf(j)))
        {
            arraylist.add(Integer.valueOf(j));
        }
        ai = super.createList(arraylist);
        ai.selectedPosition = ((com.google.android.calendar.newapi.segment.common.ChoiceCreator.ChoiceList) (ai)).values.indexOf(Integer.valueOf(j));
        return ai;
    }
}
