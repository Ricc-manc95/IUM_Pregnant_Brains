// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.schedule;


// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.schedule:
//            AutoValue_MonthLabelThresholdEvaluator_State

final class  extends 
{

    private Integer currentOffsetPx;
    private Integer currentScrollDeltaYPx;
    private Boolean firstDayOfMonth;
    private Integer switchPointOffsetPx;

    final  build()
    {
        String s1 = "";
        if (firstDayOfMonth == null)
        {
            s1 = String.valueOf("").concat(" firstDayOfMonth");
        }
        String s = s1;
        if (currentOffsetPx == null)
        {
            s = String.valueOf(s1).concat(" currentOffsetPx");
        }
        s1 = s;
        if (currentScrollDeltaYPx == null)
        {
            s1 = String.valueOf(s).concat(" currentScrollDeltaYPx");
        }
        s = s1;
        if (switchPointOffsetPx == null)
        {
            s = String.valueOf(s1).concat(" switchPointOffsetPx");
        }
        if (!s.isEmpty())
        {
            s = String.valueOf(s);
            if (s.length() != 0)
            {
                s = "Missing required properties:".concat(s);
            } else
            {
                s = new String("Missing required properties:");
            }
            throw new IllegalStateException(s);
        } else
        {
            return new AutoValue_MonthLabelThresholdEvaluator_State(firstDayOfMonth.booleanValue(), currentOffsetPx.intValue(), currentScrollDeltaYPx.intValue(), switchPointOffsetPx.intValue());
        }
    }

    final switchPointOffsetPx setCurrentOffsetPx(int i)
    {
        currentOffsetPx = Integer.valueOf(i);
        return this;
    }

    final currentOffsetPx setCurrentScrollDeltaYPx(int i)
    {
        currentScrollDeltaYPx = Integer.valueOf(i);
        return this;
    }

    final currentScrollDeltaYPx setFirstDayOfMonth(boolean flag)
    {
        firstDayOfMonth = Boolean.valueOf(flag);
        return this;
    }

    final firstDayOfMonth setSwitchPointOffsetPx(int i)
    {
        switchPointOffsetPx = Integer.valueOf(i);
        return this;
    }

    ()
    {
    }
}
