// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.tags;


// Referenced classes of package io.opencensus.tags:
//            TagValue

final class AutoValue_TagValue extends TagValue
{

    private final String asString;

    AutoValue_TagValue(String s)
    {
        if (s == null)
        {
            throw new NullPointerException("Null asString");
        } else
        {
            asString = s;
            return;
        }
    }

    public final String asString()
    {
        return asString;
    }

    public final boolean equals(Object obj)
    {
        if (obj == this)
        {
            return true;
        }
        if (obj instanceof TagValue)
        {
            obj = (TagValue)obj;
            return asString.equals(((TagValue) (obj)).asString());
        } else
        {
            return false;
        }
    }

    public final int hashCode()
    {
        return 0xf4243 ^ asString.hashCode();
    }

    public final String toString()
    {
        String s = asString;
        return (new StringBuilder(String.valueOf(s).length() + 19)).append("TagValue{asString=").append(s).append("}").toString();
    }
}
