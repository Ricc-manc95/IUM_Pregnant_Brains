// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;


final class AutoValue_AdapterEvent_Key extends AdapterEvent.Key
{

    private final int day;
    private final Object key;

    AutoValue_AdapterEvent_Key(Object obj, int i)
    {
        if (obj == null)
        {
            throw new NullPointerException("Null key");
        } else
        {
            key = obj;
            day = i;
            return;
        }
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof AdapterEvent.Key)
            {
                if (!key.equals(((AdapterEvent.Key) (obj = (AdapterEvent.Key)obj)).getKey()) || day != ((AdapterEvent.Key) (obj)).getDay())
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

    final int getDay()
    {
        return day;
    }

    final Object getKey()
    {
        return key;
    }

    public final int hashCode()
    {
        return (key.hashCode() ^ 0xf4243) * 0xf4243 ^ day;
    }

    public final String toString()
    {
        String s = String.valueOf(key);
        int i = day;
        return (new StringBuilder(String.valueOf(s).length() + 26)).append("Key{key=").append(s).append(", day=").append(i).append("}").toString();
    }
}
