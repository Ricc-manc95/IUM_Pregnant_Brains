// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.recurrence;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.util.Pair;
import com.google.android.apps.calendar.util.preference.PreferenceUtils;
import com.google.android.calendar.api.event.time.RecurRulePart;
import com.google.android.calendar.api.event.time.Recurrence;
import com.google.android.calendar.newapi.segment.common.ChoiceCreator;
import com.google.android.calendar.newapi.segment.time.TimeUtils;
import java.util.ArrayList;

// Referenced classes of package com.google.android.calendar.newapi.segment.recurrence:
//            RecurrenceUtils

public final class RecurrenceChoiceCreator extends ChoiceCreator
{

    private static final Recurrence CUSTOM_RECURRENCE = new Recurrence(new RecurRulePart[0]);
    private ArrayList defaultRecurrences;
    private final Resources resources;

    public RecurrenceChoiceCreator(Context context)
    {
        int i;
        resources = context.getResources();
        i = PreferenceUtils.getFirstDayOfWeekAsCalendar(context);
        i;
        JVM INSTR tableswitch 1 7: default 60
    //                   1 89
    //                   2 154
    //                   3 159
    //                   4 164
    //                   5 169
    //                   6 174
    //                   7 179;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
        throw new IllegalArgumentException((new StringBuilder(39)).append("Received unexpected weekday ").append(i).toString());
_L2:
        i = 7;
_L10:
        context = new ArrayList(5);
        context.add(null);
        context.add(RecurrenceUtils.createRecurrence(3, i));
        context.add(RecurrenceUtils.createRecurrence(4, i));
        context.add(RecurrenceUtils.createRecurrence(5, i));
        context.add(RecurrenceUtils.createRecurrence(6, i));
        defaultRecurrences = context;
        return;
_L3:
        i = 1;
        continue; /* Loop/switch isn't completed */
_L4:
        i = 2;
        continue; /* Loop/switch isn't completed */
_L5:
        i = 3;
        continue; /* Loop/switch isn't completed */
_L6:
        i = 4;
        continue; /* Loop/switch isn't completed */
_L7:
        i = 5;
        continue; /* Loop/switch isn't completed */
_L8:
        i = 6;
        if (true) goto _L10; else goto _L9
_L9:
    }

    static boolean isCustomRecurrence(Recurrence recurrence)
    {
        return recurrence == CUSTOM_RECURRENCE;
    }

    public final int compare(Object obj, Object obj1)
    {
        obj = (Recurrence)obj;
        obj1 = (Recurrence)obj1;
        return defaultRecurrences.indexOf(obj) - defaultRecurrences.indexOf(obj1);
    }

    protected final Pair createFooter()
    {
        return new Pair(resources.getString(0x7f1301a5), CUSTOM_RECURRENCE);
    }

    protected final String createLabel(Object obj)
    {
        obj = (Recurrence)obj;
        return TimeUtils.getSimplifiedRecurrenceString(resources, ((Recurrence) (obj)), com.google.android.calendar.newapi.segment.time.TimeUtils.DisplayForm.LONG);
    }

    public final com.google.android.calendar.newapi.segment.common.ChoiceCreator.ChoiceList createList(Recurrence recurrence)
    {
        Object obj = new ArrayList(defaultRecurrences);
        if (!((ArrayList) (obj)).contains(recurrence))
        {
            ((ArrayList) (obj)).add(recurrence);
        }
        obj = super.createList(((ArrayList) (obj)));
        if (recurrence != null)
        {
            obj.selectedPosition = ((com.google.android.calendar.newapi.segment.common.ChoiceCreator.ChoiceList) (obj)).values.indexOf(recurrence);
            return ((com.google.android.calendar.newapi.segment.common.ChoiceCreator.ChoiceList) (obj));
        } else
        {
            obj.selectedPosition = 0;
            return ((com.google.android.calendar.newapi.segment.common.ChoiceCreator.ChoiceList) (obj));
        }
    }

}
