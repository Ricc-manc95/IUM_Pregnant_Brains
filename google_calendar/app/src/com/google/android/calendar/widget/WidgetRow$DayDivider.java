// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.widget;

import com.google.android.calendar.time.Time;

// Referenced classes of package com.google.android.calendar.widget:
//            WidgetDateUtils

final class dgetDayDividerText extends dgetDayDividerText
{

    public final String label;

    public final int getLayoutId(ontext ontext)
    {
        return ontext.widgetStyle != 1 ? 0x7f05018f : 0x7f05018e;
    }

    ontext(int i, Time time, int j)
    {
        WidgetDateUtils widgetdateutils = WidgetDateUtils.instance;
        if (widgetdateutils == null)
        {
            throw new NullPointerException(String.valueOf("WidgetDateUtils#initialize(...) must be called first"));
        } else
        {
            label = ((WidgetDateUtils)widgetdateutils).getWidgetDayDividerText(i, time, j);
            return;
        }
    }
}
