// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.color;


// Referenced classes of package com.google.android.calendar.api.color:
//            NamedCalendarColor

abstract class $AutoValue_NamedCalendarColor extends NamedCalendarColor
{

    private final int nameIndex;
    private final int namesArray;
    public final int value;

    $AutoValue_NamedCalendarColor(int i, int j, int k)
    {
        value = i;
        namesArray = j;
        nameIndex = k;
    }

    public boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof NamedCalendarColor)
            {
                if (value != ((NamedCalendarColor) (obj = (NamedCalendarColor)obj)).getValue() || namesArray != ((NamedCalendarColor) (obj)).getNamesArray() || nameIndex != ((NamedCalendarColor) (obj)).getNameIndex())
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

    final int getNameIndex()
    {
        return nameIndex;
    }

    final int getNamesArray()
    {
        return namesArray;
    }

    public final int getValue()
    {
        return value;
    }

    public int hashCode()
    {
        return ((value ^ 0xf4243) * 0xf4243 ^ namesArray) * 0xf4243 ^ nameIndex;
    }

    public String toString()
    {
        int i = value;
        int j = namesArray;
        int k = nameIndex;
        return (new StringBuilder(84)).append("NamedCalendarColor{value=").append(i).append(", namesArray=").append(j).append(", nameIndex=").append(k).append("}").toString();
    }
}
