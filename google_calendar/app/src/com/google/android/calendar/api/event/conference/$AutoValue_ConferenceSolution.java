// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.conference;


// Referenced classes of package com.google.android.calendar.api.event.conference:
//            ConferenceSolution

abstract class $AutoValue_ConferenceSolution extends ConferenceSolution
{

    private final String iconUri;
    private final ConferenceSolution.Key key;
    private final String name;

    $AutoValue_ConferenceSolution(ConferenceSolution.Key key1, String s, String s1)
    {
        if (key1 == null)
        {
            throw new NullPointerException("Null key");
        }
        key = key1;
        if (s == null)
        {
            throw new NullPointerException("Null name");
        }
        name = s;
        if (s1 == null)
        {
            throw new NullPointerException("Null iconUri");
        } else
        {
            iconUri = s1;
            return;
        }
    }

    public boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof ConferenceSolution)
            {
                if (!key.equals(((ConferenceSolution) (obj = (ConferenceSolution)obj)).getKey()) || !name.equals(((ConferenceSolution) (obj)).getName()) || !iconUri.equals(((ConferenceSolution) (obj)).getIconUri()))
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

    public final String getIconUri()
    {
        return iconUri;
    }

    public final ConferenceSolution.Key getKey()
    {
        return key;
    }

    public final String getName()
    {
        return name;
    }

    public int hashCode()
    {
        return ((key.hashCode() ^ 0xf4243) * 0xf4243 ^ name.hashCode()) * 0xf4243 ^ iconUri.hashCode();
    }

    public String toString()
    {
        String s = String.valueOf(key);
        String s1 = name;
        String s2 = iconUri;
        return (new StringBuilder(String.valueOf(s).length() + 41 + String.valueOf(s1).length() + String.valueOf(s2).length())).append("ConferenceSolution{key=").append(s).append(", name=").append(s1).append(", iconUri=").append(s2).append("}").toString();
    }
}
