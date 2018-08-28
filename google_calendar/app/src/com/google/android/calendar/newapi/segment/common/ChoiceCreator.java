// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.common;

import android.support.v4.util.Pair;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public abstract class ChoiceCreator
    implements Comparator
{

    public ChoiceCreator()
    {
    }

    public Pair createFooter()
    {
        return null;
    }

    public abstract String createLabel(Object obj);

    public final ChoiceList createList(ArrayList arraylist)
    {
        Collections.sort(arraylist, this);
        Object obj = new ArrayList(arraylist.size());
        for (int i = 0; i < arraylist.size(); i++)
        {
            ((ArrayList) (obj)).add(createLabel(arraylist.get(i)));
        }

        obj = new ChoiceList(((ArrayList) (obj)), arraylist);
        if (false)
        {
            arraylist = ((ChoiceList) (obj)).labels;
            throw new NullPointerException();
        }
        arraylist = createFooter();
        if (arraylist != null)
        {
            ((ChoiceList) (obj)).labels.add((String)((Pair) (arraylist)).first);
            ((ChoiceList) (obj)).values.add(((Pair) (arraylist)).second);
        }
        return ((ChoiceList) (obj));
    }

    private class ChoiceList
    {

        public ArrayList labels;
        public int selectedPosition;
        public ArrayList values;

        public ChoiceList(ArrayList arraylist, ArrayList arraylist1)
        {
            selectedPosition = -1;
            labels = arraylist;
            values = arraylist1;
        }
    }

}
