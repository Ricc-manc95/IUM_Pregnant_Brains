// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.tags;


// Referenced classes of package io.opencensus.tags:
//            TagKey

final class AutoValue_TagKey extends TagKey
{

    private final String name;

    AutoValue_TagKey(String s)
    {
        if (s == null)
        {
            throw new NullPointerException("Null name");
        } else
        {
            name = s;
            return;
        }
    }

    public final boolean equals(Object obj)
    {
        if (obj == this)
        {
            return true;
        }
        if (obj instanceof TagKey)
        {
            obj = (TagKey)obj;
            return name.equals(((TagKey) (obj)).getName());
        } else
        {
            return false;
        }
    }

    public final String getName()
    {
        return name;
    }

    public final int hashCode()
    {
        return 0xf4243 ^ name.hashCode();
    }

    public final String toString()
    {
        String s = name;
        return (new StringBuilder(String.valueOf(s).length() + 13)).append("TagKey{name=").append(s).append("}").toString();
    }
}
