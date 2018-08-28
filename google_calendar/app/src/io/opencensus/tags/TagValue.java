// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.tags;

import io.opencensus.internal.StringUtils;

// Referenced classes of package io.opencensus.tags:
//            AutoValue_TagValue

public abstract class TagValue
{

    TagValue()
    {
    }

    public static TagValue create(String s)
    {
        String s1;
        boolean flag;
        if (s.length() <= 255 && StringUtils.isPrintableString(s))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        s1 = String.valueOf(s);
        if (s1.length() != 0)
        {
            s1 = "Invalid TagValue: ".concat(s1);
        } else
        {
            s1 = new String("Invalid TagValue: ");
        }
        if (!flag)
        {
            throw new IllegalArgumentException(s1);
        } else
        {
            return new AutoValue_TagValue(s);
        }
    }

    public abstract String asString();
}
