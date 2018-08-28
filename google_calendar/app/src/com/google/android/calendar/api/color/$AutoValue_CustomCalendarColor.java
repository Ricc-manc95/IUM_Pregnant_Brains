// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.color;


// Referenced classes of package com.google.android.calendar.api.color:
//            CustomCalendarColor

abstract class $AutoValue_CustomCalendarColor extends CustomCalendarColor
{

    private final String colorId;
    private final int originalValue;

    $AutoValue_CustomCalendarColor(int i, String s)
    {
        originalValue = i;
        if (s == null)
        {
            throw new NullPointerException("Null colorId");
        } else
        {
            colorId = s;
            return;
        }
    }

    public boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof CustomCalendarColor)
            {
                if (originalValue != ((CustomCalendarColor) (obj = (CustomCalendarColor)obj)).getOriginalValue() || !colorId.equals(((CustomCalendarColor) (obj)).getColorId()))
                {
                    return false;
                }
            } else
            {
                return false;
            }
        }
        return true;
    }

    public final String getColorId()
    {
        return colorId;
    }

    public final int getOriginalValue()
    {
        return originalValue;
    }

    public int hashCode()
    {
        return (originalValue ^ 0xf4243) * 0xf4243 ^ colorId.hashCode();
    }

    public String toString()
    {
        int i = originalValue;
        String s = colorId;
        return (new StringBuilder(String.valueOf(s).length() + 56)).append("CustomCalendarColor{originalValue=").append(i).append(", colorId=").append(s).append("}").toString();
    }
}
