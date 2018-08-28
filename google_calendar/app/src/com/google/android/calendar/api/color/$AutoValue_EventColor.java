// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.color;


// Referenced classes of package com.google.android.calendar.api.color:
//            EventColor

abstract class $AutoValue_EventColor extends EventColor
{

    private final String key;
    private final String name;
    public final int value;

    $AutoValue_EventColor(int i, String s, String s1)
    {
        value = i;
        if (s == null)
        {
            throw new NullPointerException("Null key");
        }
        key = s;
        if (s1 == null)
        {
            throw new NullPointerException("Null name");
        } else
        {
            name = s1;
            return;
        }
    }

    public boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof EventColor)
            {
                if (value != ((EventColor) (obj = (EventColor)obj)).getValue() || !key.equals(((EventColor) (obj)).getKey()) || !name.equals(((EventColor) (obj)).getName()))
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

    public final String getKey()
    {
        return key;
    }

    public final String getName()
    {
        return name;
    }

    public final int getValue()
    {
        return value;
    }

    public int hashCode()
    {
        return ((value ^ 0xf4243) * 0xf4243 ^ key.hashCode()) * 0xf4243 ^ name.hashCode();
    }

    public String toString()
    {
        int i = value;
        String s = key;
        String s1 = name;
        return (new StringBuilder(String.valueOf(s).length() + 42 + String.valueOf(s1).length())).append("EventColor{value=").append(i).append(", key=").append(s).append(", name=").append(s1).append("}").toString();
    }
}
