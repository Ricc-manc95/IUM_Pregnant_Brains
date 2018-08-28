// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.contract;

import android.content.res.Resources;
import com.google.android.calendar.groove.GrooveUtils;
import com.google.android.calendar.newapi.segment.common.ChoiceCreator;
import java.util.ArrayList;
import java.util.Comparator;

// Referenced classes of package com.google.android.calendar.newapi.segment.contract:
//            FrequencyChoice

public final class FrequencyChoiceCreator extends ChoiceCreator
    implements Comparator
{

    private static final int MONTHLY_FREQUENCIES[] = {
        1, 2
    };
    private static final int WEEKLY_FREQUENCIES[] = {
        1, 2, 3, 4, 5, 6, 7
    };
    public final Resources resources;

    public FrequencyChoiceCreator(Resources resources1)
    {
        resources = resources1;
    }

    private static ArrayList getValues()
    {
        boolean flag = false;
        ArrayList arraylist = new ArrayList(WEEKLY_FREQUENCIES.length + MONTHLY_FREQUENCIES.length);
        int ai[] = MONTHLY_FREQUENCIES;
        int k = ai.length;
        for (int i = 0; i < k; i++)
        {
            arraylist.add(new FrequencyChoice(3, ai[i]));
        }

        ai = WEEKLY_FREQUENCIES;
        k = ai.length;
        for (int j = ((flag) ? 1 : 0); j < k; j++)
        {
            arraylist.add(new FrequencyChoice(2, ai[j]));
        }

        return arraylist;
    }

    public final int compare(Object obj, Object obj1)
    {
        obj = (FrequencyChoice)obj;
        obj1 = (FrequencyChoice)obj1;
        if (((FrequencyChoice) (obj)).interval == ((FrequencyChoice) (obj1)).interval)
        {
            return ((FrequencyChoice) (obj)).numInstances - ((FrequencyChoice) (obj1)).numInstances;
        } else
        {
            return ((FrequencyChoice) (obj1)).interval - ((FrequencyChoice) (obj)).interval;
        }
    }

    protected final String createLabel(Object obj)
    {
        obj = (FrequencyChoice)obj;
        return GrooveUtils.getFrequencyString(resources, ((FrequencyChoice) (obj)).interval, ((FrequencyChoice) (obj)).numInstances);
    }

    public final com.google.android.calendar.newapi.segment.common.ChoiceCreator.ChoiceList createList(FrequencyChoice frequencychoice)
    {
        Object obj = getValues();
        if (!((ArrayList) (obj)).contains(frequencychoice))
        {
            ((ArrayList) (obj)).add(frequencychoice);
        }
        obj = super.createList(getValues());
        obj.selectedPosition = ((com.google.android.calendar.newapi.segment.common.ChoiceCreator.ChoiceList) (obj)).values.indexOf(frequencychoice);
        return ((com.google.android.calendar.newapi.segment.common.ChoiceCreator.ChoiceList) (obj));
    }

}
