// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.color;

import android.graphics.Color;

// Referenced classes of package com.google.android.calendar.api.color:
//            CalendarColor

public abstract class CustomCalendarColor
    implements CalendarColor
{

    public CustomCalendarColor()
    {
    }

    public abstract String getColorId();

    public abstract int getOriginalValue();

    public final int getValue()
    {
        int i = getOriginalValue();
        float af[] = new float[3];
        Color.colorToHSV(i, af);
        if (af[2] > 0.79F)
        {
            af[1] = Math.min(af[1] * 1.3F, 1.0F);
            af[2] = af[2] * 0.8F;
        }
        return Color.HSVToColor(Color.alpha(i), af) | 0xff000000;
    }
}
