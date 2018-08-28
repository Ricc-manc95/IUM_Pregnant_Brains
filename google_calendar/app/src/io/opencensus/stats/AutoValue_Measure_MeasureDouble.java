// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.stats;


final class AutoValue_Measure_MeasureDouble extends Measure.MeasureDouble
{

    private final String description;
    private final String name;
    private final String unit;

    AutoValue_Measure_MeasureDouble(String s, String s1, String s2)
    {
        if (s == null)
        {
            throw new NullPointerException("Null name");
        }
        name = s;
        if (s1 == null)
        {
            throw new NullPointerException("Null description");
        }
        description = s1;
        if (s2 == null)
        {
            throw new NullPointerException("Null unit");
        } else
        {
            unit = s2;
            return;
        }
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof Measure.MeasureDouble)
            {
                if (!name.equals(((Measure.MeasureDouble) (obj = (Measure.MeasureDouble)obj)).getName()) || !description.equals(((Measure.MeasureDouble) (obj)).getDescription()) || !unit.equals(((Measure.MeasureDouble) (obj)).getUnit()))
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

    public final String getDescription()
    {
        return description;
    }

    public final String getName()
    {
        return name;
    }

    public final String getUnit()
    {
        return unit;
    }

    public final int hashCode()
    {
        return ((name.hashCode() ^ 0xf4243) * 0xf4243 ^ description.hashCode()) * 0xf4243 ^ unit.hashCode();
    }

    public final String toString()
    {
        String s = name;
        String s1 = description;
        String s2 = unit;
        return (new StringBuilder(String.valueOf(s).length() + 41 + String.valueOf(s1).length() + String.valueOf(s2).length())).append("MeasureDouble{name=").append(s).append(", description=").append(s1).append(", unit=").append(s2).append("}").toString();
    }
}
