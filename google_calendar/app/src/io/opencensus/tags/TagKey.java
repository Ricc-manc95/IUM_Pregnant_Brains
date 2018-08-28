// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.tags;

import io.opencensus.internal.StringUtils;

// Referenced classes of package io.opencensus.tags:
//            AutoValue_TagKey

public abstract class TagKey
{

    TagKey()
    {
    }

    public static TagKey create(String s)
    {
        String s1;
        boolean flag;
        if (!s.isEmpty() && s.length() <= 255 && StringUtils.isPrintableString(s))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        s1 = String.valueOf(s);
        if (s1.length() != 0)
        {
            s1 = "Invalid TagKey name: ".concat(s1);
        } else
        {
            s1 = new String("Invalid TagKey name: ");
        }
        if (!flag)
        {
            throw new IllegalArgumentException(s1);
        } else
        {
            return new AutoValue_TagKey(s);
        }
    }

    public abstract String getName();
}
