// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.stats;

import io.opencensus.internal.StringUtils;

// Referenced classes of package io.opencensus.stats:
//            Measure, AutoValue_Measure_MeasureLong

public abstract class asureLong extends Measure
{

    public static asureLong create(String s, String s1, String s2)
    {
        boolean flag;
        if (StringUtils.isPrintableString(s) && s.length() <= 255)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException("Name should be a ASCII string with a length no greater than 255 characters.");
        } else
        {
            return new AutoValue_Measure_MeasureLong(s, s1, s2);
        }
    }

    public abstract String getDescription();

    public abstract String getName();

    public abstract String getUnit();

    asureLong()
    {
        super((byte)0);
    }
}
