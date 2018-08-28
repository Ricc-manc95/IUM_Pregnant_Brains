// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.timezonepicker.fullscreen;


final class AutoValue_TimeZonePickerHelper_Result extends TimeZonePickerHelper.Result
{

    private final String getId;
    private final String getName;
    private final boolean timeZoneWasSelected;

    AutoValue_TimeZonePickerHelper_Result(String s, String s1, boolean flag)
    {
        if (s == null)
        {
            throw new NullPointerException("Null getId");
        }
        getId = s;
        if (s1 == null)
        {
            throw new NullPointerException("Null getName");
        } else
        {
            getName = s1;
            timeZoneWasSelected = flag;
            return;
        }
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof TimeZonePickerHelper.Result)
            {
                if (!getId.equals(((TimeZonePickerHelper.Result) (obj = (TimeZonePickerHelper.Result)obj)).getId()) || !getName.equals(((TimeZonePickerHelper.Result) (obj)).getName()) || timeZoneWasSelected != ((TimeZonePickerHelper.Result) (obj)).timeZoneWasSelected())
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

    public final String getId()
    {
        return getId;
    }

    public final String getName()
    {
        return getName;
    }

    public final int hashCode()
    {
        int i = getId.hashCode();
        int j = getName.hashCode();
        char c;
        if (timeZoneWasSelected)
        {
            c = '\u04CF';
        } else
        {
            c = '\u04D5';
        }
        return c ^ ((i ^ 0xf4243) * 0xf4243 ^ j) * 0xf4243;
    }

    public final boolean timeZoneWasSelected()
    {
        return timeZoneWasSelected;
    }

    public final String toString()
    {
        String s = getId;
        String s1 = getName;
        boolean flag = timeZoneWasSelected;
        return (new StringBuilder(String.valueOf(s).length() + 51 + String.valueOf(s1).length())).append("Result{getId=").append(s).append(", getName=").append(s1).append(", timeZoneWasSelected=").append(flag).append("}").toString();
    }
}
